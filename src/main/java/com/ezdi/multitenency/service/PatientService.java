package com.ezdi.multitenency.service;

import java.util.List;
import com.ezdi.multitenency.bean.Patient;
import com.ezdi.multitenency.dao.PatientDAO;


public class PatientService {
	
	PatientDAO dao=new PatientDAO();
	
	
	/**
	 * This method saves a Patient object in database
	 */
	public int addPatient(Patient patient, String hospitalId) {
		
		return dao.addPatient(patient, hospitalId);
	}

	/**
	 * This method returns list of all persisted Patient objects/tuples from
	 * database for Particular Hospital
	 */
	public List<Patient> getAllPatients(String hospitalId) {
		
		return dao.getAllPatients(hospitalId);
	}

	/**
	 * This method updates a specific Patient object
	 */
	public void updatePatient(int id, String city,String hospitalId) {
		
		dao.updatePatient(id, city, hospitalId);
	}

	/**
	 * This method deletes a specific Patient object
	 */
	public void deletePatient(int id) {
		
		dao.deletePatient(id);

	}

}
