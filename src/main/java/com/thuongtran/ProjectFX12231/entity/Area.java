package com.thuongtran.ProjectFX12231.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KhuBan")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ma_ban")
	private int areaID;
	
	@Column(name="ten_ban")
	private String name;
	
	@Column(name="trang_thai")
	private String status;

	
	public Area() {
		super();
	}

	public Area(int areaID, String name, String status) {
		super();
		this.areaID = areaID;
		this.name = name;
		this.status = status;
	}

	public int getAreaID() {
		return areaID;
	}

	public void setAreaID(int areaID) {
		this.areaID = areaID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
