package com.thuongtran.ProjectFX12231.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thuongtran.ProjectFX12231.dao.AreaDAO;
import com.thuongtran.ProjectFX12231.entity.Area;

@Controller
public class AreaController {
	
	/**
	 * Controller chỉnh sửa thông tin bàn với id truyền vào
	 */
	@RequestMapping(value = "admin/edit-area", method = RequestMethod.POST)
	public String editArea(@RequestParam("areaID")int id,
						 @RequestParam("areaName")String name) {
		Area area = new AreaDAO().getAreaByID(id);
		area.setName(name);
		new AreaDAO().updateArea(area);
		return "admin/area";
	}
}
