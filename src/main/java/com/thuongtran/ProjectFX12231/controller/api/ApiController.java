package com.thuongtran.ProjectFX12231.controller.api;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thuongtran.ProjectFX12231.check.Check;
import com.thuongtran.ProjectFX12231.dao.AreaDAO;
import com.thuongtran.ProjectFX12231.dao.BillDetailDAO;
import com.thuongtran.ProjectFX12231.dao.CustomerDAO;
import com.thuongtran.ProjectFX12231.dao.EmployeeDAO;
import com.thuongtran.ProjectFX12231.dao.ProductDAO;
import com.thuongtran.ProjectFX12231.dao.UserDAO;
import com.thuongtran.ProjectFX12231.entity.Area;
import com.thuongtran.ProjectFX12231.entity.Customer;
import com.thuongtran.ProjectFX12231.entity.Employee;
import com.thuongtran.ProjectFX12231.entity.User;

/**
 * Controller API thực hiện các thao tác nhận từ Jquery
 * 
 * @author ADMIN
 *
 */
@Controller
@RequestMapping
public class ApiController {

	/**
	 * Controller thay đổi mật khẩu
	 */
	@PostMapping("api/change-pass")
	@ResponseBody
	public String changePass(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("newPassword") String newPass, @RequestParam("repeatPassword") String repeatPass,
			HttpSession session) {
		// kiểm tra thông tin nhập vào
		Boolean check = new Check().checkUser(userName, password, session);
		System.out.println(newPass);
		if (userName == "" || password == "" || newPass == "" || repeatPass == "") {
			return "blank";
		} else {
			if (!newPass.equals(repeatPass)) {
				return "repeatFail";
			} else {
				// cập nhật thông tin tài khoản lên csdl
				User user = (User) session.getAttribute("userLogin");
				newPass = DigestUtils.md5Hex(newPass).toUpperCase();
				user.setPassword(newPass);
				new UserDAO().saveUser(user);
				return "" + check;
			}
		}
	}
	
	/**
	 * Controller xóa nhân viên
	 */
	@PostMapping("api/del-employee")
	@ResponseBody
	public void delEmployee(@RequestParam("eID")int eID) {
		Employee employee = new EmployeeDAO().getEmployeeByID(eID);
		new EmployeeDAO().delEmployee(employee);
	}
	/**
	 * COntroller xóa nhiều sản phẩm
	 */
	@PostMapping("api/del-product")
	@ResponseBody
	public String delAllProduct(@RequestParam("pID") String listPID) {
		String result = "";
		boolean check = false;
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = objectMapper.readTree(listPID);
			//kiểm tra sản phẩm trên hóa đơn
			for (int i = 0; i < jsonNode.size(); i++) {
				check = new BillDetailDAO().checkProduct(jsonNode.get(i).asInt());
				if (check) {
					result = "fail";
					break;
				}
			}
			//nếu sản phẩm chưa bán ra cho phép xóa
			if (!check) {
				for (int i = 0; i < jsonNode.size(); i++) {
					new ProductDAO().delProduct(jsonNode.get(i).asInt());
				}
				result = "success";
			}

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	/**
	 * controller tạo bàn mới
	 */
	@PostMapping("api/create-area")
	@ResponseBody
	public String createArea(@RequestParam("areaName") String name) {
		Area area = new Area();
		area.setName(name);
		area.setStatus("Trống");
		new AreaDAO().updateArea(area);
		return "succes";
	}

	/**
	 * Controller kiểm tra sđt nhập vào
	 */
	@GetMapping("api/check-phone")
	@ResponseBody
	public void checkNumberPhone(@RequestParam("numberPhone") String number, HttpSession session) {
		// kết nối cơ sở dữ liệu. lấy thông tin và đẩy lên session
		CustomerDAO cusDAO = new CustomerDAO();
		Customer cus = cusDAO.getCusomerByNumber(number);
		if (cus != null) {
			// nếu đúng sđt lấy ra thông tin giảm giá. tính thực thu đẩy lên session
			if (session.getAttribute("totalAmount") != null) {
				double totalAmount = (double) session.getAttribute("totalAmount");
				double discount = new CustomerDAO().getDiscountByID(cus.getCustomerID());
				double receivableAmount = totalAmount - totalAmount * discount;
				session.setAttribute("discount", discount * 100);
				session.setAttribute("receivableAmount", receivableAmount);
			}
			session.setAttribute("customer", cus);
		} else {
			Customer c = new Customer();
			c.setName("nocheck");
			session.setAttribute("customer", c);
		}
	}
}
