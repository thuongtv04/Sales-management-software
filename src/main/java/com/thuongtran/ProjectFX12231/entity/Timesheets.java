package com.thuongtran.ProjectFX12231.entity;



import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LuongNhanVien")
public class Timesheets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ma_nv")
	private Employee employee;
	
	@Column(name="ngay")
	private Date date;
	
	@Column(name="gio_vao")
	private Time inTime;
	
	@Column(name="gio_ra")
	private Time outTime;
	
	@Column(name="gio_cong")
	private double timeWork;
	
	public Timesheets() {
		super();
	}

	public Timesheets(Employee employee, Date date, Time inTime, Time outTime) {
		super();
		this.employee = employee;
		this.date = date;
		this.inTime = inTime;
		this.outTime = outTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getInTime() {
		return inTime;
	}

	public void setInTime(Time inTime) {
		this.inTime = inTime;
	}

	public Time getOutTime() {
		return outTime;
	}

	public void setOutTime(Time outTime) {
		this.outTime = outTime;
	}

	public double getTimeWork() {
		return timeWork;
	}

	public void setTimeWork(double timeWork) {
		this.timeWork = timeWork;
	}
	
}
