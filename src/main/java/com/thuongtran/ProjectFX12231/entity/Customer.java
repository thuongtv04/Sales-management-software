package com.thuongtran.ProjectFX12231.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="KhachHang")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ma_kh")
	private int customerID;
	
	@Column(name="ten_kh")
	private String name;
	
	@Column(name="so_dien_thoai")
	private String numberPhone;
	
	@Column(name="diem_tich_luy")
	private int points;
	
	@ManyToOne
	@JoinColumn(name = "ma_loaiKhachHang")
	private CustomerType customerType;
	
	public Customer() {
		super();
	}
	
	public Customer(String name, String numberPhone, int points) {
		super();
		this.name = name;
		this.numberPhone = numberPhone;
		this.points = points;
	}
	
	public Customer(int customerID, String name, String numberPhone, int points) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.numberPhone = numberPhone;
		this.points = points;
	}

	

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	
}
