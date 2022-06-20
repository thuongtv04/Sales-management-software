package com.thuongtran.ProjectFX12231.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.entity.User;


public class UserDAO {
	private static SessionFactory FACTORY = null;
	/**
	 * Lưu thông tin tài khoản vào csdl
	 */
	public void saveUser(User user) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
	/**
	 * trả về danh sách user
	 */
	public List<User> getAllUser() {
		FACTORY = HibernateUtils.getSessionFactory();
		List<User> listU = new ArrayList<>();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			String hql = "FROM User";
			listU = session.createQuery(hql, User.class).list();
			session.getTransaction().commit();
			
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return listU;
	}
	
	/**
	 * trả về tài khoản theo id truyền vào
	 */
	public User getUserByID(int id) {
		List<User> listU = new ArrayList<>();
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			String hql = "FROM User WHERE userID = :id";
			listU = session.createQuery(hql, User.class).setParameter("id", id).list();

			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return listU.get(0);
	}
	
	/**
	 * Xóa tài khoản
	 */
	public void delUser(User user) {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
}
