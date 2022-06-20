package com.thuongtran.ProjectFX12231.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.entity.Timesheets;

public class TimesheetsDAO {
	private static SessionFactory FACTORY = null;
	
	/**
	 * Lưu chấm công nhân viên 
	 */
	public void saveCheck(Timesheets s) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(s);
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
	
	/**
	 * lấy thông tin bảng công theo ID
	 */
	public List<Timesheets> getSalaryByEmployeeID(int id) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		List<Timesheets> listS = new ArrayList<>();
		try {
			session.beginTransaction();
			String hql = "FROM Timesheets S WHERE S.employee.employeeID ="+id;
			listS = session.createQuery(hql, Timesheets.class).list();
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
			return listS;
	}
	
	/**
	 * trả về toàn bộ bảng công trong tháng
	 */
	public List<Object[]> getAllTimesheets(Date date) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		List<Object[]> listT = new ArrayList<>();
		try {
			session.beginTransaction();
			String hql = "select employee.employeeID, employee.name,employee.salary, sum(timeWork) From Timesheets where month(date)= month("+date+")"
					+ "group by employee.employeeID,employee.name,employee.salary";
			listT = session.createQuery(hql).list();
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
			return listT;
	}

}
