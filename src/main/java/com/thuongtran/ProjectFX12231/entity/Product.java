package com.thuongtran.ProjectFX12231.entity;

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
@Table(name = "SanPham")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_sp")
	private int productID;
	
	@Column(name = "ten_sp")
	private String name;
	
	@Column(name = "loai_sp")
	private String type;
	
	@Column(name = "gia_ban")
	private double price;
	
	@Column(name = "src_anh")
	private String src;
	
	@Column(name = "mo_ta")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ma_sp")
	private List<DetailBill> listdb = new ArrayList<>();
	
	
	public List<DetailBill> getListdb() {
		return listdb;
	}

	public void setListdb(List<DetailBill> listdb) {
		this.listdb = listdb;
	}

	public Product() {
		super();
	}

	public Product(int productID, String name, String type, double price, String src, String description) {
		super();
		this.productID = productID;
		this.name = name;
		this.type = type;
		this.price = price;
		this.src = src;
		this.description = description;
	}
	public Product( String name, String type, double price, String src, String description) {

		this.name = name;
		this.type = type;
		this.price = price;
		this.src = src;
		this.description = description;
	}

	

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
