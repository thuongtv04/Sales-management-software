package com.thuongtran.ProjectFX12231.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="HoaDon")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ma_hd")
	private int billID;
	
	@ManyToOne
	@JoinColumn(name = "ma_kh")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "ma_ban")
	private Area area;
	
	@ManyToOne
	@JoinColumn(name = "ma_nv")
	private Employee employee;
	
	@Column(name="ngay_lap_hd")
	private Date receivedDate;
	
	@Column(name="tong_tien")
	private double totalAmount;
	
	@Column(name="thuc_thu")
	private double receivableAmount;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ma_hd")
	private List<DetailBill> listBill;
	
	public Bill() {
		super();
	}

	public int getBillID() {
		return billID;
	}


	public void setBillID(int billID) {
		this.billID = billID;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Area getArea() {
		return area;
	}


	public void setArea(Area area) {
		this.area = area;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Date getReceivedDate() {
		return receivedDate;
	}


	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(double receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public List<DetailBill> getListBill() {
		return listBill;
	}


	public void setListBill(List<DetailBill> listBill) {
		this.listBill = listBill;
	}

}
