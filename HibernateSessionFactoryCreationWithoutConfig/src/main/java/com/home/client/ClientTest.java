package com.home.client;

import org.hibernate.Session;

import com.home.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			String SQL="Select version();";
			String result = (String) session.createNativeQuery(SQL).getSingleResult();
			System.out.println("MySql version is: "+result);
			session.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
