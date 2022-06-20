package com.thuongtran.ProjectFX12231.controller.admin;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongtran.ProjectFX12231.dao.EmployeeDAO;
import com.thuongtran.ProjectFX12231.dao.TimesheetsDAO;
import com.thuongtran.ProjectFX12231.dao.UserDAO;
import com.thuongtran.ProjectFX12231.entity.Employee;
import com.thuongtran.ProjectFX12231.entity.Timesheets;
import com.thuongtran.ProjectFX12231.entity.User;

@Controller
public class EmployeeController {
	
	/**
	 * Controller đăng ký nhân viên mới
	 */
	@RequestMapping(value = "admin/add-employee", method = RequestMethod.GET)
	public String AddEmployee(Model model, String name, Byte sex,  Date startDay, String position, String numberPhone) {
		int age = 18;
		double salary = 0;
		Employee employee = new Employee(name, sex, age, startDay, position, salary, numberPhone);
		model.addAttribute("newEmployee", employee);
		model.addAttribute("typeBTN", "Thêm nhân viên mới");
		model.addAttribute("action", "add-employee");
		return "admin/addemployee";
	}

	/**
	 * COntroller xử lý thêm nhân viên mới
	 */

    @Autowired
    JavaMailSender mailSender;
	@RequestMapping(value = "admin/add-employee", method = RequestMethod.POST)
	public String insertEmployee(@ModelAttribute("newEmployee") Employee employee, Model model) {
		//tự động tạo tài khoản khi thêm nhân viên mới với pass random		
		UserDAO userDAO = new UserDAO();
		String uName = employee.getName().substring(0, 2) + employee.getNumberPhone();
		String uPass = RandomStringUtils.randomAlphanumeric(6);
		//băm mật khẩu
		uPass = DigestUtils.md5Hex(uPass).toUpperCase();
		User user = new User();
		user.setUserName(uName);
		user.setRole(0);
		user.setPassword(uPass);
		user.setEmployee(employee);
		
		//Tạo e mail
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("thuongtvfx12231@funix.edu.vn");
        email.setTo("webfunix04@gmail.com");
        email.setSubject("Subject");
        email.setText("Mật khẩu đăng nhập cho tài khoản:"+uName+" là: "+uPass);
        //Gửi e-mail
//        mailSender.send(email);
        
        userDAO.saveUser(user);
		model.addAttribute("mesage", "<h6><i>Thêm nhân viên mới thành công.</i></h6>"
				+ "<br> Tài khoản: "+uName+ "<br> Mật khẩu: "+ uPass);
        
        System.out.println("Sending text done!");
		return "admin/addemployee";
	}
	
	/**
	 * controller xem toàn bộ nhân viên
	 */
	@RequestMapping(value = "admin/show-employee", method = RequestMethod.GET)
	public String showEmployee(Model model) {
		EmployeeDAO employee = new EmployeeDAO();
		List<Employee> listE = employee.getAllEmployee();
		model.addAttribute("listE", listE);
		return "admin/showemployee";
	}
	
	/**
	 * controller chi tiết nhân viên
	 */
	@RequestMapping(value ="/admin/detail-employee", method = RequestMethod.GET)
	public String detailEmployee(Model model, @RequestParam("id") int id) {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		//lấy ra thông tin nhân viên và thông tin tài khoản
		List<Object[]> listE = employeeDAO.getDetailEmployee(id);
		User user = new User();
		Employee employee = new Employee();
		for(Object[] o : listE) {
			employee = (Employee) o[0];
			user = (User) o[1];
		}
		model.addAttribute("employee", employee);
		model.addAttribute("user", user);
		return "admin/detailemployee";
	}
	
	/**
	 * Controller check-in
	 */
	@RequestMapping(value = "web/check-in", method = RequestMethod.GET)
	public String checkIn(@RequestParam("id")int employeeID,HttpSession session) {
		//lấy ngày giờ hiện tại
		long millis = System.currentTimeMillis();
		Employee employee = new EmployeeDAO().getEmployeeByID(employeeID);
		Date date = new Date(millis);
		Time time = new Time(millis);
		Timesheets timesheets = new Timesheets();
		timesheets.setDate(date);
		timesheets.setInTime(time);
		timesheets.setEmployee(employee);
		session.setAttribute("inTime", time);
		session.setAttribute("timesheets", timesheets);
		//lưu giờ đến vào csdl
		new TimesheetsDAO().saveCheck(timesheets);
		
		return "web/selling";
	}
	
	/**
	 * controller  check-out 
	 */
	@RequestMapping(value = "web/check-out", method = RequestMethod.GET)
	public String checkOut(HttpSession session) {
		//lấy ra giờ vào và giờ hiện tại
		Time inTime = (Time) session.getAttribute("inTime");
		long millis = System.currentTimeMillis();
		Time outTime = new Time(millis);
		
		//tính toán chênh lệch khi nhân viên check out
		Calendar cal = Calendar.getInstance();
		cal.setTime(inTime);
		double in  = (double)(cal.get(Calendar.HOUR)*60 + cal.get(Calendar.MINUTE))/60;
		cal.setTime(outTime);
		double out = (double)(cal.get(Calendar.HOUR)*60 + cal.get(Calendar.MINUTE))/60;
		double timeWork = (double) Math.round((out - in) * 100) / 100;
		if(session.getAttribute("timesheets") != null) {
			Timesheets timesheets = (Timesheets) session.getAttribute("timesheets");
			timesheets.setOutTime(outTime);
			timesheets.setTimeWork(timeWork);
			
			//sau khi tính toán giờ công.Lưu vào csdl
			new TimesheetsDAO().saveCheck(timesheets);
			session.removeAttribute("inTime");
		}
		return "web/selling";
	}
	
	/**
	 * Controller xóa nhân viên theo id
	 */
	@RequestMapping("admin/del-employee")
	public String delEmployee(int id) {
		EmployeeDAO eDAO = new EmployeeDAO();
		Employee employee = eDAO.getEmployeeByID(id);
		eDAO.delEmployee(employee);
		return "admin/showemployee";
	}
}
