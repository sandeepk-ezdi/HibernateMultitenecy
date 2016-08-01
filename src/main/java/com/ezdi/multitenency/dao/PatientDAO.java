package com.ezdi.multitenency.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.ezdi.multitenency.bean.Patient;
import com.ezdi.multitenency.interceptor.TenantInterceptor;
import com.ezdi.multitenency.util.HibernateUtil;
import com.ezdi.multitenency.util.HospitalConstants;




public class PatientDAO {
	
	/**
	 * This method saves a Patient object in database
	 */
	public int addPatient(Patient patient) {
	
		SessionFactory sf=HibernateUtil.getSessionFactory();
		
		//Start-Need to Uncomment following two statements to Enable Session Level Intercepter
		
		/*TenantInterceptor interceptor= new TenantInterceptor();
		Session session = sf.withOptions().interceptor(interceptor).openSession(); */
		
		//session.enableFilter("hospitalFilter").setParameter("hospitalId", hospitalId);
		Session session = sf.openSession();
		session.beginTransaction();
		int id = (Integer) session.save(patient);
		session.getTransaction().commit();
		session.close();
		return id;
	}

	/**
	 * This method returns list of all persisted Patient objects/tuples from
	 * database for Particular Hospital
	 */
	public List<Patient> getAllPatients(String hospitalId) {
		
		List<Patient> patients=null;
		SessionFactory sf=HibernateUtil.getSessionFactory();
		//Start-Need to Uncomment following two statements to Enable Session Level Intercepter
		
		/*TenantInterceptor interceptor= new TenantInterceptor();
		Session session = sf.withOptions().interceptor(interceptor).openSession();*/
		
		Session session = sf.openSession();
		
		//session.enableFilter("hospitalFilter").setParameter("hospitalId",hospitalId);
		session.beginTransaction();

        final Criteria criteria = session.createCriteria(Patient.class,"patient");
        criteria.add(Restrictions.eq("patient.name", "Runit"));
        patients=criteria.list();
		
		
		session.getTransaction().commit();
		session.close();
		return patients;
	}

	/**
	 * This method updates a specific Patient object
	 */
	public void updatePatient(int id, String city) {
		
		SessionFactory sf=HibernateUtil.getSessionFactory();
		//Start-Need to Uncomment following two statements to Enable Session Level Intercepter
		
		/*TenantInterceptor interceptor= new TenantInterceptor();
		Session session = sf.withOptions().interceptor(interceptor).openSession();*/
		
		Session session = sf.openSession();
		session.beginTransaction();

		Patient patient = (Patient) session.get(Patient.class, id);
		patient.setCity(city);
		
		session.update(patient);//No need to update manually as it will be updated automatically on transaction close.
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * This method deletes a specific Patient object
	 */
	public void deletePatient(int id) {
		SessionFactory sf=HibernateUtil.getSessionFactory();
		//Start-Need to Uncomment following two statements to Enable Session Level Intercepter
		
		/*TenantInterceptor interceptor= new TenantInterceptor();
		Session session = sf.withOptions().interceptor(interceptor).openSession();*/
		
		Session session = sf.openSession();
		session.beginTransaction();

		Patient patient = (Patient) session.get(Patient.class, id);
		
		session.delete(patient);
		session.getTransaction().commit();
		session.close();

	}

}
