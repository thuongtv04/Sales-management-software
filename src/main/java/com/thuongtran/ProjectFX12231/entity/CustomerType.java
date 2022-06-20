package com.thuongtran.ProjectFX12231.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LoaiKH")
public class CustomerType {
	@Id
	@Column(name="ma_loaiKhachHang")
	private int customerTypeID;
	
	@Column(name="ten_loaiKhachHang")
	private String name;
	
	@Column(name="giam_gia")
	private double discount;

	public CustomerType() {
		super();
	}

	public CustomerType(int customerTypeID, String name, double discount) {
		super();
		this.customerTypeID = customerTypeID;
		this.name = name;
		this.discount = discount;
	}

	public int getCustomerTypeID() {
		return customerTypeID;
	}

	public void setCustomerTypeID(int customerTypeID) {
		this.customerTypeID = customerTypeID;
	}


//	public List<Customer> getCustomers() {
//		return customers;
//	}
//
//	public void setCustomers(List<Customer> customers) {
//		this.customers = customers;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}
