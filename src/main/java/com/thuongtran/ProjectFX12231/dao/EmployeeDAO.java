package com.thuongtran.ProjectFX12231.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.entity.Employee;

public class EmployeeDAO {

		private static SessionFactory FACTORY = null;

		/**
		 * hàm trả về danh sách toàn bộ nhân viên từ csdl
		 */
		public List<Employee> getAllEmployee() {
			List<Employee> listE = new ArrayList<>();
			FACTORY = HibernateUtils.getSessionFactory();

			Session session = FACTORY.openSession();
			try {
				System.out.println("connected");
				session.beginTransaction();
				String hql = "FROM Employee";
				listE = session.createQuery(hql, Employee.class).list();
				session.getTransaction().commit();

			} catch (HibernateError e) {
				session.getTransaction().rollback();
				System.out.println(e.getMessage());
			} finally {
				session.close();
			}
			return listE;
		}
		
		/**
		 * hàm xem chi tiết thông tin nhân viên
		 */
		public List<Object[]> getDetailEmployee(int id) {
			FACTORY = HibernateUtils.getSessionFactory();
			Session session = FACTORY.openSession();
			
			List<Object[]> list = new ArrayList<Object[]>();
			try {
				session.beginTransaction();
			String hql = "FROM Employee e INNER JOIN e.users  WHERE e.id = :id";
			 list = session.createQuery(hql).setParameter("id", id).list();
			session.getTransaction().commit();
	        
			}catch (HibernateError e) {
				session.getTransaction().rollback();
				System.out.println(e.getMessage());
			} finally {
				session.close();
			}
			return list;
		}
		
		/**
		 * hàm trả về nhân viên theo id
		 */
		public Employee getEmployeeByID(int id) {
			List<Object[]>  listE = getDetailEmployee(id);
			Employee employee = new Employee();
			for(Object[] o : listE) {
				employee = (Employee) o[0];
			}
			return employee;
		}
		
		/**
		 * hàm xóa nhân viên
		 */
		public void delEmployee(Employee em) {
			FACTORY = HibernateUtils.getSessionFactory();
			Session session = FACTORY.openSession();
			try {
				session.beginTransaction();
				session.delete(em);
				session.getTransaction().commit();
		        
			}catch (HibernateError e) {
				session.getTransaction().rollback();
				System.out.println(e.getMessage());
			} finally {
				session.close();
			}
		}

}
