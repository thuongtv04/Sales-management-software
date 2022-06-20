package com.thuongtran.ProjectFX12231.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.entity.DetailBill;

public class BillDetailDAO {
	private static SessionFactory FACTORY = null;
	
	/**
	 * hàm kiểm tra sự tồn tại của sản phẩm trên hóa đơn
	 * @return false: chưa tồn tại; true: đã tồn tại
	 */
	public boolean checkProduct(int productID) {
		FACTORY = HibernateUtils.getSessionFactory();

		Session session = FACTORY.openSession();
		List<DetailBill> list = new ArrayList<>();
		try {
			session.beginTransaction();
			String hql = "FROM DetailBill A WHERE A.product.productID =" + productID;
			list = session.createQuery(hql, DetailBill.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		if(list.size() >0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Hàm lấy ra sản phẩm bán chạy nhất
	 */
	public List<Object[]> getTopProduct(){
		FACTORY = HibernateUtils.getSessionFactory();
		List<Object[]> listBD = new ArrayList<>();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			String hql ="select product.name,product.src, product.price, sum(quantity)"
					+ "From DetailBill GROUP BY product.name,product.src, product.price ORDER BY sum(quantity) DESC";
			listBD = session.createQuery(hql).setFirstResult(0).setMaxResults(5).list();
			session.getTransaction().commit();

		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		
		return listBD;
	}
}
