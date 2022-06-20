package com.thuongtran.ProjectFX12231.controller.user;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongtran.ProjectFX12231.dao.UserDAO;
import com.thuongtran.ProjectFX12231.entity.User;

@Controller
public class UserController {
	
	/**
	 * controler rs mật khẩu
	 */
	@RequestMapping(value = "admin/rs-user", method = RequestMethod.GET)
	public String rsUser(@RequestParam("id")int id, Model model) {
		//lấy thông tin tài khoản từ csdl
		System.out.println("mã nhân viên: "+id);
		User user = new UserDAO().getUserByID(id);
		// thay đổi mk về mặc định vào cập nhật trên cơ sở dữ liệu
		String uPass = RandomStringUtils.randomAlphanumeric(6);
		String md5Pass= DigestUtils.md5Hex(uPass).toUpperCase();
		user.setPassword(md5Pass);
		new UserDAO().saveUser(user);
		model.addAttribute("mesage", "reset mật khẩu thành công: "+ uPass);
		return "admin/detailemployee";
	}
	
}
