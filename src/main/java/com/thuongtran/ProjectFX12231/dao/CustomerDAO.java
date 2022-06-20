package com.thuongtran.ProjectFX12231.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.entity.Customer;
import com.thuongtran.ProjectFX12231.entity.CustomerType;

public class CustomerDAO {
	private static SessionFactory FACTORY = null;
	//thêm khách hàng mới
	public void addCustomer(Customer customer) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			CustomerType cusType = new CustomerType();
			cusType.setName("Khách mới");
			cusType.setDiscount(0.02);
			cusType.setCustomerTypeID(0);
			
			customer.setCustomerType(cusType);
			session.save(customer);
			
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	/**
	 * Hàm trả về thông tin chi tiết toàn bộ khách hàng
	 */
	public List<Customer> getAllCustomer() {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		List<Customer> list = new ArrayList<>();
		try {
			session.beginTransaction();
			String hql = "FROM Customer";
			list = session.createQuery(hql,Customer.class).list();
			session.getTransaction().commit();
			return list;
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}
		
	/**
	 * Tìm kiếm khách hàng theo sđt
	 */
	public Customer getCusomerByNumber(String number) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		
		List<Customer> listC = new ArrayList<>();
		try {
			session.beginTransaction();
			String hql = "from Customer C where C.numberPhone = :number";
			listC = session.createQuery(hql, Customer.class).setParameter("number", number).list();
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if(listC.size()>0) {
			return listC.get(0);
		}else {
			return null;
		}
	}
	/**
	 * hàm tìm kiếm khách hàng theo ID
	 */
	public Customer getCustomerByID(int id) {
		FACTORY = HibernateUtils.getSessionFactory();

		Session session = FACTORY.openSession();
		List<Customer> listC = new ArrayList<>();
		try {
			session.beginTransaction();
			String hql = "FROM Customer C WHERE C.id =" + id;
			listC = session.createQuery(hql, Customer.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.clear();
			session.close();
		}
		
		return listC.get(0);
	}
	
	/**
	 * hàm kiểm tra giảm giá theo ID
	 */
	public double getDiscountByID(int id) {
		List<Customer> listC = getAllCustomer();
		for(int i = 0; i < listC.size(); i++) {
			if(listC.get(i).getCustomerID()== id) {
				if(listC.get(i).getCustomerType() == null) {
					break;
				}else
				return listC.get(i).getCustomerType().getDiscount();
			}
		}
		
		return 0;
	}
	/**
	 * hàm cập nhật khách hàng
	 */
	public void updateCustomer(Customer c) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(c);
			session.getTransaction().commit();

		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
}
