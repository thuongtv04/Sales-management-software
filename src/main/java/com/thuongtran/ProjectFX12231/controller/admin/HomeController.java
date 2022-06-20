package com.thuongtran.ProjectFX12231.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.thuongtran.ProjectFX12231.check.Check;
import com.thuongtran.ProjectFX12231.dao.AreaDAO;
import com.thuongtran.ProjectFX12231.dao.BillDetailDAO;
import com.thuongtran.ProjectFX12231.dao.CustomerDAO;
import com.thuongtran.ProjectFX12231.entity.Area;
import com.thuongtran.ProjectFX12231.entity.Customer;


@Controller
public class HomeController {
	/**
	 * Controller trang chủ dành cho admin. hiển thị top 5 sản phẩm bán chạy nhất
	 */
	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView HomePage(HttpSession session) {
		ModelAndView mav;
		if(new Check().checkAdmin(session)) {
			List<Object[]> listBD = new BillDetailDAO().getTopProduct();
			mav = new ModelAndView("admin/home");
			mav.addObject("listBD", listBD);
		}else {
			mav = new ModelAndView("redirect:/login");
		}
		return mav;
	}
	
	private List<Area> getAllArea() {
		AreaDAO areaDAO = new AreaDAO();
		List<Area> listA = areaDAO.getAllArea();
		return listA;
	}
	
	/**
	 * Controller hiển thị danh sách các bàn
	 */
	@RequestMapping(value = "/admin/area", method = RequestMethod.GET)
	public ModelAndView Table(HttpSession session) {
		ModelAndView mav;
		//kiểm tra đăng nhập
		if(!new Check().checkAdmin(session)) {
				mav = new ModelAndView("redirect:/login");
			}else {
				session.setAttribute("listA", getAllArea());
				mav = new ModelAndView("admin/area");
			}
		return mav;
	}
	
	/**
	 * Controller các sản phẩm
	 */
	@RequestMapping(value = "/admin/products", method = RequestMethod.GET)
	public ModelAndView Product(HttpSession session) {
		ModelAndView mav;
		//kiểm tra đăng nhập
		if(!new Check().checkAdmin(session)) {
			mav = new ModelAndView("redirect:/login");
		}else{
				mav = new ModelAndView("admin/products");
			}
		
		return mav;
	}
	
	/**
	 * controller hiển thị danh sách toàn bộ khách hàng
	 */
	@RequestMapping(value = "/admin/customers", method = RequestMethod.GET)
	public ModelAndView getAllCustomers(HttpSession session) {
		ModelAndView mav;
		//kiểm tra đăng nhập
		if(!new Check().checkAdmin(session)) {
			mav = new ModelAndView("redirect:/login");
		}else {
				List<Customer> listC = new CustomerDAO().getAllCustomer();
				mav = new ModelAndView("admin/customers");
				mav.addObject("listC", listC);
			}
		
		return mav;
	}
	
	/**
	 * COntroller hiển thị danh sách nhân viên
	 */
	@RequestMapping(value = "/admin/employee", method = RequestMethod.GET)
	public ModelAndView Employee(HttpSession session) {
		ModelAndView mav;
		//kiểm tra đăng nhập
		if(!new Check().checkAdmin(session)) {
			mav = new ModelAndView("redirect:/login");
		}else {
				mav = new ModelAndView("admin/employee");
		}
		return mav;
	}
	
	
}
