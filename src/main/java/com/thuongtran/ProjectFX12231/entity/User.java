package com.thuongtran.ProjectFX12231.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TaiKhoan")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_tk")
	private int userID;

	@Column(name = "ten_dang_nhap")
	private String userName;

	@Column(name = "mat_khau")
	private String password;

	@Column(name = "phan_quyen")
	private int role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ma_nv")
	private Employee employee;

	public User() {
		super();
	}

	public User(int userID, String userName, String password, int role, Employee employee) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.employee = employee;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
