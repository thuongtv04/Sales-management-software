package com.thuongtran.ProjectFX12231.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutControler {
	/**
	 * controller đăng xuất tài khoản admin
	 */
	@RequestMapping(value = "admin/logout", method = RequestMethod.GET)
	public String logoutAdmin(HttpSession session) {
		session.removeAttribute("userLogin");
		return "redirect:/login";
	}
	
	/**
	 * Controller đăng xuất tài khoản nhân viên
	 */
	@RequestMapping(value = "web/logout", method = RequestMethod.GET)
	public String logoutWeb(HttpSession session) {
		session.removeAttribute("userLogin");
		session.removeAttribute("inTime");
		return "redirect:/login";
	}
}
