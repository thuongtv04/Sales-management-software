package com.thuongtran.ProjectFX12231.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.entity.Product;

public class ProductDAO{

	private static SessionFactory FACTORY = null;
	
	/**
	 * hàm tìm kiếm sản phẩm theo id
	 */
	public Product getProductByID(int id) {
		Product p = new Product();
		FACTORY = HibernateUtils.getSessionFactory();

		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			String hql = "FROM Product A WHERE A.id =" + id;
			List<Product> listP = session.createQuery(hql, Product.class).list();
			p = listP.get(0);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		
		return p;
	}
	/**
	 * hàm xóa sản phẩm theo id
	 */
	public void delProduct(int id) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Product product = (Product) session.get(Product.class, id);
			session.delete(product);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	

	/**
	 * hàm trả về danh sách toàn bộ sản phẩm từ csdl

	 */
	public List<Product> getAllProduct() {
		List<Product> listP = new ArrayList<>();
		FACTORY = HibernateUtils.getSessionFactory();

		Session session = FACTORY.openSession();
		try {
			System.out.println("connected");
			session.beginTransaction();
			String hql = "FROM Product";
			listP = session.createQuery(hql, Product.class).list();
			session.getTransaction().commit();
			

		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return listP;
	}

	/**
	 * 
	 * @param index : số trang truyền vào
	 * @param maxP: số sản phẩm tối đa trên 1 trang
	 */
	public List<Product> listPageProduct(int index, int maxP) {
		List<Product> listP = new ArrayList<Product>();
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			String hql = "FROM Product ORDER BY productID";
			Query<Product> query = session.createQuery(hql, Product.class);
			query.setFirstResult((index - 1) * maxP);
			query.setMaxResults(maxP);
			listP = query.list();
			session.getTransaction().commit();
			
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return listP;
	}

	/**
	 * hàm thêm sản phẩm mới vào hệ thống csdl
	 */
	public void addProduct(Product p) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			System.out.println("connected");
			session.beginTransaction();
			session.saveOrUpdate(p);
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * hàm cập nhật thông tin sản phẩm
	 */
//	public void editProduct(Product p) {
//		FACTORY = HibernateUtils.getSessionFactory();
//		Session session = FACTORY.openSession();
//		try {
//			session.beginTransaction();
//			session.saveOrUpdate(p);
//			session.getTransaction().commit();
//
//		} catch (HibernateError e) {
//			session.getTransaction().rollback();
//			System.out.println(e.getMessage());
//		} finally {
//			session.close();
//		}
//	}
}
