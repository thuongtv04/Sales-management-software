package com.thuongtran.ProjectFX12231.controller.web;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thuongtran.ProjectFX12231.dao.BillDAO;
import com.thuongtran.ProjectFX12231.entity.Bill;
import com.thuongtran.ProjectFX12231.entity.Employee;
import com.thuongtran.ProjectFX12231.entity.User;

@Controller
public class ReportController {
	
	@RequestMapping("web/report")
	public String reportDay(HttpSession session, Model model) {
		//lấy ra thông tin nhân viên thực hiện báo cáo
		User user = (User) session.getAttribute("userLogin");
		Employee employee = user.getEmployee();
		//lấy ngày hiện tại
		long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        //lấy hóa đơn trong ngày
        BillDAO bd = new BillDAO();
        List<Bill> listB = bd.getBillByDay(date);
        
        //tính tổng doanh thu
        double totalSales = 0;
        for(Bill b: listB) {
        	totalSales += b.getReceivableAmount();
        }
		model.addAttribute("listB", listB);
		model.addAttribute("employee", employee);
		model.addAttribute("date", s);
		model.addAttribute("totalSales", (double)Math.round(totalSales * 1000) / 1000);
		return "web/reportday";
	}	
}
