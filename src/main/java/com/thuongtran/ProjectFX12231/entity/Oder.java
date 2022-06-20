package com.thuongtran.ProjectFX12231.entity;

import java.util.ArrayList;
import java.util.List;

public class Oder {
	private int oderID;
	private List<Items> listItems;
	
	
	
	public Oder() {
		listItems = new ArrayList<>();
	}
	public Oder(int oderID) {
		this.oderID = oderID;
		listItems = new ArrayList<>();
	}
		
	
	public int getOderID() {
		return oderID;
	}
	public void setOderID(int oderID) {
		this.oderID = oderID;
	}
	//thêm sản phẩm vào oder
	public void addProduct(Product p) {
		for (Items o : listItems) {
			if (p.getProductID() == o.getProduct().getProductID()) {
				o.setQuantity(o.getQuantity() + 1);
				System.out.println(o.getQuantity());
				return;
			}
		}
		Items item = new Items(p, 1);
		listItems.add(item);
	}
	
	//bớt số lượng sản phẩm trên oder
	public void delProduct(Product p) {
		for (Items o : listItems) {
			if (p.getProductID() == o.getProduct().getProductID()) {
				if(o.getQuantity() == 1) {
					removeFromOder(p.getProductID());
				}				
				o.setQuantity(o.getQuantity() - 1);
				return;
			}
		}
	}
	
	//xoá toàn bộ sản phẩm khỏi oder
	public void removeFromOder(int id) {
		for(Items o : listItems) {
			if(o.getProduct().getProductID() == id) {
				listItems.remove(o);
				return;
			}
		}
	}
	//tạm tính hóa đơn
	public double getAmount() {
		double result = 0;
		for(Items o: listItems) {
			result += o.getProduct().getPrice() * o.getQuantity();
		}
		return Math.round(result*100.0)/100.0;
	}
	
	// trả về danh sách sản phẩm có trong oder
	public List<Items> getListItems(){
		return listItems;
	}

	@Override
	public String toString() {
//		return listItems.toString();
//	}
		String result ="";
		for(Items it: listItems) {
			result += oderID+";"+it.toString()+"\n";
		}
		
		return result;
	}
	
}
