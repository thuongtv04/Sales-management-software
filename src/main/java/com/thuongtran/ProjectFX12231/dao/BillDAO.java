package com.thuongtran.ProjectFX12231.dao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.entity.Bill;

public class BillDAO {
private static SessionFactory FACTORY = null;
	
	/**
	 * Hàm lưu hóa đơn vào csdl
	 */
	public void addBill(Bill bill) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(bill);
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	/**
	 * hàm lấy ra danh sách hóa đơn theo ngày
	 */
	public List<Bill> getBillByDay(Date day) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		
		List<Bill> listB = new ArrayList<>();
		try {
			session.beginTransaction();
			String hql = "FROM Bill where receivedDate = :date";
			listB = session.createQuery(hql, Bill.class).setParameter("date", day).list();
			
			
			session.getTransaction().commit();
			
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		
		return listB;
	}
	
	/**
	 * hàm lấy ra tất cả hóa đơn
	 */
	public List<Object[]> getBill(Date date) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		List<Object[]> listB = new ArrayList<>();
		try {
			session.beginTransaction();
			String hql = "SELECT receivedDate,sum(totalAmount), sum(receivableAmount) "
					+ "FROM Bill WHERE month(receivedDate)= month("+date+")"
							+ "GROUP BY receivedDate";
			listB = session.createQuery(hql).list();
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return listB;
	}
}
