package com.thuongtran.ProjectFX12231.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thuongtran.ProjectFX12231.check.Check;
import com.thuongtran.ProjectFX12231.entity.User;

@Controller
public class LoginController {
	/**
	 * controller đăng nhập. đẩy về trang đăng nhập khi khởi động chương trình
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login/login");
		return mav;
	}
	
	/**
	 * Controller xử lý đăng nhập
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String checkLogin(@RequestParam("userName") String userName, @RequestParam("password") String password,
			HttpSession session) {
		//Gọi hàm kiểm tra id và pas
		boolean check = new Check().checkUser(userName, password, session);
		User user = (User) session.getAttribute("userLogin");
		//kiểm tra quyền đăng nhập. 1 => admin
		if (check) {
			if (user.getRole() == 1) {
				return "redirect:admin/home";
			} else {
				return "redirect:web/home";
			}
		} else {
			return "redirect:login";
		}

	}

}
