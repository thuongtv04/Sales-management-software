package com.thuongtran.ProjectFX12231.dao;

import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.entity.Area;


public class AreaDAO {
	private static SessionFactory FACTORY = null;
	
	/**
	 * hàm trả về danh sách toàn bộ bàn trong khu vực
	 */
	public List<Area> getAllArea() {
		FACTORY = HibernateUtils.getSessionFactory();
		Session session = FACTORY.openSession();
		try {
			System.out.println("connected");
			session.beginTransaction();
			String hql = "FROM Area";
			List<Area> listA = session.createQuery(hql, Area.class).list();
			session.getTransaction().commit();
			return listA;

		} catch (HibernateError e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	
	/**
	 * lấy ra thông tin bàn theo id
	 */
	public Area getAreaByID(int id) {
		FACTORY = HibernateUtils.getSessionFactory();

		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
			String hql = "FROM Area A WHERE A.id =" + id;
			List<Area> listA = session.createQuery(hql, Area.class).list();
			Area area = listA.get(0);
			session.getTransaction().commit();
			return area;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	
	/**
	 * cập nhật bàn
	 */
	public void updateArea(Area area) {
		FACTORY = HibernateUtils.getSessionFactory();

		Session session = FACTORY.openSession();
		try {
			session.beginTransaction();
            session.saveOrUpdate(area);
            session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}
}
