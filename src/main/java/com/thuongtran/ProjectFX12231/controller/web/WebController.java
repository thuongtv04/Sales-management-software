package com.thuongtran.ProjectFX12231.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	/**
	 *COntroller trang chủ web bán hàng 
	 */
	@RequestMapping(value="web/home", method = RequestMethod.GET)
	public ModelAndView HomePage() {
		ModelAndView mav = new ModelAndView("web/home");
		return mav;
	}
}
