package com.thuongtran.ProjectFX12231;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.ResourceUtils;

import com.thuongtran.ProjectFX12231.Utils.HibernateUtils;
import com.thuongtran.ProjectFX12231.dao.TimesheetsDAO;
import com.thuongtran.ProjectFX12231.entity.Oder;
import com.thuongtran.ProjectFX12231.entity.Product;
import com.thuongtran.ProjectFX12231.entity.Timesheets;

public class App 
{	
	private static SessionFactory FACTORY = null;
	
	
	public static void insertData(List<Oder> listO) {
		try {
			File file = ResourceUtils.getFile("classpath:file/data.txt");
        	FileWriter fw = new FileWriter(file);
        	BufferedWriter bw = new BufferedWriter(fw);
        	for(Oder od : listO) {
        		bw.write(od.toString());
        		bw.newLine();
        	}
        	bw.close();
        	fw.close();
        }catch (Exception e) {
        	e.printStackTrace();
		}
	}
	
	 public static List<Oder> read(){
 		 List<Oder> listO = new ArrayList<>();
		 try {
			 File file = ResourceUtils.getFile("classpath:file/data.txt");
	        	FileReader fr = new FileReader(file);
	        	BufferedReader br = new BufferedReader(fr);
	        	String line ="";
	        	int id = 0;
	        	Oder od = new Oder();
	        	while(true) {
	        		line = br.readLine();
	        		if(line == null) {
	        			break;
	        		}
	        		String[]txt = line.split(";");	
	        		int oderID = Integer.parseInt(txt[0]);
	        		int proID = Integer.parseInt(txt[1]);
	        		String name = txt[2];
	        		
	        		
	        		Product pz = new Product();
	        		pz.setProductID(proID);
	        		pz.setName(name);
	        		
	        		if(id != oderID) {
	        		id = oderID;
	        		 od = new Oder(oderID);
	        		 listO.add(od);
	        		}
	        		od.addProduct(pz);
	        		
	        	}
	        	br.close();
	        	fr.close();
	        	
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
		return listO; 
	 }
	 
	 public static void readFileUsingResourceUtils() throws IOException {
		  System.out.println("Read file from resource folder using Spring ResourceUtils");
		  File file = ResourceUtils.getFile("classpath:file/data.txt");
		  // Read File Content
		  String content = new String(Files.readAllBytes(file.toPath()));
		  System.out.println(content);
		}
	 
    public static void main( String[] args){
    	long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        System.out.println(date);
        String hash = "21232F297A57A5A743894A0E4A801FC3";
        String password = "admin";
        String md5Hex = DigestUtils.md5Hex(password).toUpperCase();
        System.out.println("Hash: " + md5Hex);
        System.out.println("Verify: " + hash.equals(md5Hex));
       
//        Calendar cal = Calendar.getInstance();
//         	cal.setTime(date);
//        FACTORY = HibernateUtils.getSessionFactory();
//		Session session = FACTORY.openSession();
//		List<Object[]> listT = new ArrayList<>();
//		try {
//			session.beginTransaction();
//			String hql = "select employee.employeeID, employee.name, sum(timeWork) From Timesheets where month(date)= month("+date+")"
//					+ "group by employee.employeeID,employee.name";
//			listT = session.createQuery(hql).list();
//			session.getTransaction().commit();
//		} catch (HibernateError e) {
//			session.getTransaction().rollback();
//			System.out.println(e.getMessage());
//		} finally {
//			session.close();
//		}
//
//        	System.out.println("thanh cong");
//        	for(Object[] o : listT) {
//        		System.out.println(""+o[0] + o[1] + o[2]);
//        	}

//        cal.setTime();
//        	double in = (double)(cal.get(Calendar.HOUR)*60 + cal.get(Calendar.MINUTE))/60;
//        	cal.setTime();
//        	double out = cal.get(Calendar.HOUR)*60 + cal.get(Calendar.MINUTE);

        
        
//    	Customer c = new CustomerDAO().getCusomerByID("0983382531");
//    	FACTORY = HibernateUtils.getSessionFactory();
//		Session session = FACTORY.openSession();
//		try {
//			session.beginTransaction();
//			
//			
//			NguoiThan nt = new NguoiThan();
//			nt.setName("Tâm");
//			nt.setCustomer(c);
//			session.persist(nt);
//			session.getTransaction().commit();
//			
//		} catch (HibernateError e) {
//			session.getTransaction().rollback();
//			System.out.println(e.getMessage());
//		} finally {
//			session.close();
//		
//		}

        
    }
}
