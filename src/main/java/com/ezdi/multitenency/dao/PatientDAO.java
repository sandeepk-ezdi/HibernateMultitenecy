package com.ezdi.multitenency.dao;

import java.util.List;

import org.hibernate.Session;

import com.ezdi.multitenency.bean.Patient;
import com.ezdi.multitenency.util.HibernateUtil;



public class PatientDAO {
	
	/**
	 * This method saves a Patient object in database
	 */
	public int addPatient(Patient patient, String hospitalId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.enableFilter("hospitalFilter").setParameter("hospitalId", hospitalId);
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.enableFilter("hospitalFilter").setParameter("hospitalId", hospitalId);
		session.beginTransaction();

		
		List<Patient> patients = (List<Patient>) session.createQuery(
				"FROM Patient s ORDER BY s.name ASC").list();

		session.getTransaction().commit();
		session.close();
		return patients;
	}

	/**
	 * This method updates a specific Patient object
	 */
	public void updatePatient(int id, String city,String hospitalId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.enableFilter("hospitalFilter").setParameter("hospitalId", hospitalId);
		session.beginTransaction();

		Patient patient = (Patient) session.get(Patient.class, id);
		patient.setCity(city);
		//session.update(student);//No need to update manually as it will be updated automatically on transaction close.
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * This method deletes a specific Patient object
	 */
	public void deletePatient(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Patient patient = (Patient) session.get(Patient.class, id);
		session.delete(patient);
		session.getTransaction().commit();
		session.close();

	}

}
