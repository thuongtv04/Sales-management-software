package com.thuongtran.ProjectFX12231.check;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.thuongtran.ProjectFX12231.dao.UserDAO;
import com.thuongtran.ProjectFX12231.entity.User;

public class Check {
	/**
	 * Hàm kiểm tra thông tin tài khoản
	 */
	public boolean checkUser(String userName, String password, HttpSession session) {
		String mes = "";
		List<User> listU = new UserDAO().getAllUser();
		for (int i = 0; i < listU.size(); i++) {
			if (userName.equals(listU.get(i).getUserName())) {
				//băm mật khẩu nhập vào
				String md5Pass = DigestUtils.md5Hex(password).toUpperCase();
				if (md5Pass.equals(listU.get(i).getPassword())) {
					session.setAttribute("userLogin", listU.get(i));
					return true;
				} else {
					mes = "mật khẩu không đúng";
					session.setAttribute("mes", mes);
					return false;
				}
			} else {
				mes = "Tài khoản không đúng";
				session.setAttribute("mes", mes);

			}
		}
		return false;
	}

	/**
	 * Hàm kiểm tra thông tin đăng nhập tài khoản admin
	 */
	public boolean checkAdmin(HttpSession session) {
		if (session.getAttribute("userLogin") == null) {
			return false;
		} else {
			User user = (User) session.getAttribute("userLogin");
			if (user.getRole() != 1) {
				return false;
			} else {
				return true;
			}
		}
	}
}
