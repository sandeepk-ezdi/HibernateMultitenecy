package com.ezdi.multitenency;

import java.util.List;



import com.ezdi.multitenency.bean.Patient;
import com.ezdi.multitenency.service.PatientService;
import com.ezdi.multitenency.util.HospitalConstants;

public class Main {

	public static void main(String[] args) {
		PatientService patientService = new PatientService();
	
		/*System.out.println("=========================Adding Patients ================================");
		Patient patient= new Patient("Neeraj", 27, "Male", "Bombay", HospitalConstants.HCA);
		patientService.addPatient(patient);
		System.out.println("=========================Successfully Added  ================================");
*/
		
		
		List<Patient> patientList = patientService.getAllPatients(HospitalConstants.APPOLO);
		System.out.println("List of all remained persisted Patients >>>");
		for (Patient patient : patientList) {
			System.out.println(patient);
		}
     
	
	/*
		System.out.println("=========================Updating Patients ================================");
		patientService.updatePatient(11, "Patna" );
		System.out.println("=========================Successfully Updated  ================================");
		
		*/

		/*System.out.println("=========================Deleting Patients ================================");
		patientService.deletePatient(11);
		System.out.println("=========================Successfully Deleted  ================================");
	*/
	
	}

}

