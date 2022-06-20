package com.thuongtran.ProjectFX12231.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NhanVien")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ma_nv")
	private int employeeID;
	
	@Column(name="ten_nv")
	private String name;
	
	@Column(name="gioi_tinh")
	private Byte sex;
	
	@Column(name="tuoi")
	private int age;
	
	@Column(name="ngay_vao_lam")
	private Date startDay;
	
	@Column(name="vi_tri")
	private String position;
	
	@Column(name="luong_co_ban")
	private double salary;
	
	@Column(name="so_dien_thoai")
	private String numberPhone;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_nv")
	private List<User> users = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_nv")
	private List<Bill> bills = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ma_nv")
	private List<Timesheets> Listtimesheets = new ArrayList<>();
	
	public Employee() {
		super();
	}

	public Employee(String name, Byte sex, int age, Date startDay, String position, double salary, String numberPhone) {
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.startDay = startDay;
		this.position = position;
		this.salary = salary;
		this.numberPhone = numberPhone;
	}

	public Employee(int employeeID, String name, Byte sex, int age, Date startDay, String position, double salary,
			String numberPhone, List<User> users) {
		super();
		this.employeeID = employeeID;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.startDay = startDay;
		this.position = position;
		this.salary = salary;
		this.numberPhone = numberPhone;
//		this.users = users;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
}
