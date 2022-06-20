package com.thuongtran.ProjectFX12231.controller.admin;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thuongtran.ProjectFX12231.check.Check;
import com.thuongtran.ProjectFX12231.dao.BillDAO;
import com.thuongtran.ProjectFX12231.dao.TimesheetsDAO;
import com.thuongtran.ProjectFX12231.entity.Bill;
import com.thuongtran.ProjectFX12231.entity.Timesheets;

@Controller
public class IntegrationsController {
	/**
	 * Thống kê doanh thu và lương nhân viên trong tháng
	 */
	@RequestMapping(value = "/admin/integrations", method = RequestMethod.GET)
	public ModelAndView interations(HttpSession session, Model model) {
		ModelAndView mav;
		// kiểm tra đăng nhập
		if (!new Check().checkAdmin(session)) {
			mav = new ModelAndView("redirect:/login");
		} else {
			//gọi hàm lấy dữ liệu và add vào modelandview
			mav = new ModelAndView(interations(model));
		}
		return mav;
	}

	/**
	 * Hàm lấy dữ liệu từ csdl
	 */
//	@RequestMapping(value = "admin/integrations", method = RequestMethod.POST)
	private String interations(Model model) {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		List<Object[]> listB = new BillDAO().getBill(date);
		List<Object[]> listT = new TimesheetsDAO().getAllTimesheets(date);
		double totalSales = 0;
		double totalSalary = 0;
		// tính tổng doanh thu
		for (Object[] o : listB) {
			double x = (double) o[2];
			totalSales += x;
		}
		// tinh tổng lương nhân viên
		for (Object[] o : listT) {
			System.out.println(o[3]);
			double x = (double) o[3] * (double) o[2];
			totalSalary += x;
		}
		model.addAttribute("totalSalary", totalSalary);
		model.addAttribute("totalSales", totalSales);
		model.addAttribute("listB", listB);
		model.addAttribute("listT", listT);
		return "admin/integrations";
	}

	/**
	 * Controller xem chi tiết hóa đơn theo ngày
	 */
	@RequestMapping(value = "admin/detail-bill", method = RequestMethod.GET)
	public String getBillByDay(@RequestParam("date") Date date, Model model) {
		List<Bill> listB = new BillDAO().getBillByDay(date);
		model.addAttribute("listB", listB);
		return "admin/detailbill";
	}
	
	/**
	 * Controller xem bảng lương theo nhân viên
	 */
	@RequestMapping(value = "admin/detail-salary", method = RequestMethod.GET)
	public String getTimesheetsByID(@RequestParam("id") int id, Model model) {
		List<Timesheets> listS = new TimesheetsDAO().getSalaryByEmployeeID(id);
		model.addAttribute("listS", listS);
		return "admin/detailsalary";
	}
}
