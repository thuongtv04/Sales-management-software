package com.thuongtran.ProjectFX12231.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongtran.ProjectFX12231.dao.CustomerDAO;
import com.thuongtran.ProjectFX12231.entity.Customer;

@Controller
public class CustomerController {
	/**
	 * controler thêm khách hàng mới
	 */
	@RequestMapping(value = "web/add-customer", method = RequestMethod.POST)
	public String insertCustomer(@RequestParam("customerName")String name,
								@RequestParam("numberPhone") String numberPhone) {
		CustomerDAO customerDAO = new CustomerDAO();
		customerDAO.addCustomer(new Customer(name, numberPhone, 0));
		return "web/home";
	}
	
	/**
	 * controller thay đổi thông tin khách hàng
	 */
	@RequestMapping(value = "admin/edit-customer", method = RequestMethod.GET)
	public String editCustomer(@RequestParam("id")int id, Model model) {
		Customer customer = new CustomerDAO().getCustomerByID(id);
		model.addAttribute("customer", customer);
		return "admin/editcustomer";
	}
	
	/**
	 * Controller xử lý thay đổi thông tin khách hàng
	 */
	@RequestMapping(value = "admin/edit-customer", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("customer")Customer customer, Model model) {
		System.out.println(customer.getPoints());
		System.out.println(customer.getCustomerType());
		if (customer.getPoints() >= 300) {
			// điểm tích lũy từ 300 cập nhật khách vip
			customer.getCustomerType().setCustomerTypeID(3);
		} else if (customer.getPoints() >= 200) {
			// điểm tích lũy từ 200 cập nhật lên khách hàng thân thiết
			customer.getCustomerType().setCustomerTypeID(2);
		} else if (customer.getPoints() >= 100) {
			// điểm tích lũy từ 100 cập nhật khách hàng thành viên
			customer.getCustomerType().setCustomerTypeID(1);
		}else {
			customer.getCustomerType().setCustomerTypeID(0);
		}
		System.out.println(customer.getCustomerType().getCustomerTypeID());
		new CustomerDAO().updateCustomer(customer);
		model.addAttribute("mesage", "Cập nhật thông tin thành công");
		return "admin/editcustomer";
	}
	
	
	
}
