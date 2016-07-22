package com.ezdi.multitenency;

import java.util.List;

import com.ezdi.multitenency.bean.Patient;
import com.ezdi.multitenency.service.PatientService;
import com.ezdi.multitenency.util.HospitalConstants;

public class Main {

	public static void main(String[] args) {
		PatientService patientService = new PatientService();
		/*
		System.out.println("=========================Adding Patients ================================");
		Patient patient= new Patient("Raju Bhai", 26, "Male", "Ahemdabad", HospitalConstants.HCA);
		patientService.addPatient(patient, HospitalConstants.APPOLO);
		System.out.println("=========================Successfully Added  ================================");

		*/
		
		
		List<Patient> patientList = patientService.getAllPatients(HospitalConstants.HCA);
		System.out.println("List of all remained persisted Patients for >>>"+HospitalConstants.RUMC);
		for (Patient patient : patientList) {
			System.out.println(patient);
		}
     
	
		/*
		System.out.println("=========================Updating Patients ================================");
		patientService.updatePatient(1, "Chennai",HospitalConstants.RUMC );
		System.out.println("=========================Successfully Updated  ================================");
	*/
	}

}

