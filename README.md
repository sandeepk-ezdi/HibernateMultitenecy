# HibernateMultitenecy
For multitenecy using Session Level Interceptor and SessionFactory Level Interceptor

NOTE:-For this example currently SessionFactory Level Interceptor is Enabled

How to Use Session Level Interceptor:-
=====================================
1. You need to comment following lines inside class com.ezdi.multitenency.util.HibernateUtil
   
   TenantInterceptor interceptor= new TenantInterceptor();
	 configuration.setInterceptor(interceptor);
	 
2. You need to uncommit following lines inside class com.ezdi.multitenency.dao.PatientDAO

    /*TenantInterceptor interceptor= new TenantInterceptor();
		Session session = sf.withOptions().interceptor(interceptor).openSession(); */


How to Use SessionFactory Level Interceptor:-
=====================================
1. You need to uncomment following lines inside class com.ezdi.multitenency.util.HibernateUtil
   
   TenantInterceptor interceptor= new TenantInterceptor();
	 configuration.setInterceptor(interceptor);
	 
2. You need to comment following lines inside class com.ezdi.multitenency.dao.PatientDAO

    /*TenantInterceptor interceptor= new TenantInterceptor();
		Session session = sf.withOptions().interceptor(interceptor).openSession(); */

