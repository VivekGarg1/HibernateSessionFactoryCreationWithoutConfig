package com.home.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	
	static {
		try {
			if(sessionFactory == null) {
				//Creating StandardServiceRegistryBuilder
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
				
				//Hibernate settings which is equivallent to hibernate.cfg.xml properties
				Map <String, String> dbSetting= new HashMap<>();
				dbSetting.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernatedb");
				dbSetting.put(Environment.USER, "root");
				dbSetting.put(Environment.PASS, "root");
				dbSetting.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				dbSetting.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
				
				//Applying database setting
				registryBuilder.applySettings(dbSetting);
				
				//creating registry
				
				standardServiceRegistry=registryBuilder.build();
				
				//Applying metadata resource
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				Metadata metadata=metadataSources.getMetadataBuilder().build();
				sessionFactory=metadata.getSessionFactoryBuilder().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(standardServiceRegistry != null){
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			}
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
