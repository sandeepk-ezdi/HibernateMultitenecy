package com.ezdi.multitenency.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.ezdi.multitenency.interceptor.TenantInterceptor;



public class HibernateUtil {
	private static ServiceRegistry serviceRegistry;

	private static  SessionFactory sessionFactory;

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			
			//Start-Need to comment following two statements to Enable Session Level Intercepter
			TenantInterceptor interceptor= new TenantInterceptor();
			configuration.setInterceptor(interceptor);
			//End
			
			serviceRegistry = new StandardServiceRegistryBuilder(). applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.configure().buildSessionFactory(serviceRegistry);
			return sessionFactory;
		}
		catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

    public static SessionFactory getSessionFactory() {
        return buildSessionFactory();
    }
    
    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }
	
}
