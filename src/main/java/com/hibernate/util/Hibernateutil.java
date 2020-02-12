package com.hibernate.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;

import com.hibernate.pojo.Student;

public class Hibernateutil {

	private static StandardServiceRegistry standardServiceRegistry;

	private static SessionFactory sessionFactory;


	// Utility method to return SessionFactory
	public static <T> SessionFactory getSessionFactory(Class<T> entity) {

		if (sessionFactory == null) {
			try {
				// Creating StandardServiceRegistryBuilder
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				Map<String, String> dbSettings = new HashMap<>();
				dbSettings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/practice");
				dbSettings.put(AvailableSettings.USER, "root");
				dbSettings.put(AvailableSettings.PASS, "test");
				dbSettings.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
				dbSettings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				dbSettings.put(AvailableSettings.HBM2DDL_AUTO, "update");
				dbSettings.put(AvailableSettings.SHOW_SQL, "true");
				// Apply database settings
				registryBuilder.applySettings(dbSettings);
				// Creating registry
				standardServiceRegistry = registryBuilder.build();
				// Creating MetadataSources
				MetadataSources sources = new MetadataSources(standardServiceRegistry);
				sources.addAnnotatedClass(entity);
				// Creating Metadata
				Metadata metadata = sources.getMetadataBuilder().build();
				// Creating SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (standardServiceRegistry != null) {
					StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
				}
			}
		}
	
		return sessionFactory;
	}
}
