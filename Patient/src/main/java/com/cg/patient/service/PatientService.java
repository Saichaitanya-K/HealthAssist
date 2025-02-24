package com.cg.patient.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Laboratory;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.LaboratoryException;
import com.cg.patient.exception.PatientException;
import com.cg.patient.repository.PatientRepository;
/**
 * 
 * @author Sai Chaitanya Krishna
 * Description : Services that are provided to patient
 *
 */
@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	/**
	 * Object of doctor
	 */
	Doctor doctor = new Doctor("fgh","ent",3427852452L);
	List<Doctor> doctorList = Arrays.asList(new Doctor[]{doctor});
	/**
	 * List of medical tests
	 */
	List<String> medicalTestList = Arrays.asList(new String[] {"Blood test","X-ray"});
	/**
	 * Object of Laboratory
	 */
	Laboratory laboratory = new Laboratory("abc",doctorList,medicalTestList);
	/**
	 * Method to save or to update patient details
	 * @param patient
	 * @return patient object
	 */
	public Patient saveOrUpdate(Patient patient) {

		try {
			patient.setPatientIdentifier(patient.getPatientIdentifier().toUpperCase());
			return patientRepository.save(patient);
		} catch (Exception e) {
			throw new PatientException("PatientIdentifier " + patient.getPatientIdentifier() + " already available");
		}

	}
	/**
	 * Method to find patient by patient identifier
	 * @param patientIdentifier
	 * @return patient object
	 */
	public Patient findPatientByPatientIdentifier(String patientIdentifier) {
		Patient patient = patientRepository.findByPatientIdentifier(patientIdentifier.toUpperCase());
		if (patient == null) {
			throw new PatientException("PatientIdentifier " + patientIdentifier + " not available");
			
		}
		return patient;

	}
	/**
	 * Method to find all patients in database
	 * @return 
	 */
	public Iterable<Patient> findAllPatients(){
		return patientRepository.findAll();
		
	}
	/**
	 * Method to delete a patient from database using patient identifier
	 * @param patientIdentifier
	 */
	public void deletePatientByPatientIdentifier(String patientIdentifier) {
		Patient patient=findPatientByPatientIdentifier(patientIdentifier.toUpperCase());
		if(patient==null) {
			throw new PatientException("PatientIdentifier " + patientIdentifier + " not available");
		}
		patientRepository.delete(patient);
	}
	/**
	 * Method to search laboratory with specified laboratory name and medical test name
	 * @param laboratoryName
	 * @param medicalTestName
	 * @return true if laboratory is present with specified details or else false
	 */
	public boolean searchLaboratoryByMedicalTest(String laboratoryName, String medicalTestName) {
		if(laboratory.getLaboratoryName().equalsIgnoreCase(laboratoryName)) {
			for(String testName:laboratory.getMedicalTestList()) {
				if(testName.equalsIgnoreCase(medicalTestName))
					return true;
			}	
		}
		return false;
	}
	/**
	 * Method to book an appointment for the medical test
	 * @param laboratoryName
	 * @param medicalTestName
	 * @return true if appointment is booked or else false
	 */
	public boolean bookAppointmentForMedicalTest(String laboratoryName, String medicalTestName) {
		try {
			if(laboratory.getLaboratoryName().equalsIgnoreCase(laboratoryName)) {
				for(String testName:laboratory.getMedicalTestList()) {
					if(testName.equalsIgnoreCase(medicalTestName)) {
						if(laboratory.getNoOfMedicalTestAppointments()!=0) {
							laboratory.setNoOfMedicalTestAppointments(laboratory.getNoOfMedicalTestAppointments()-1);
							return true;
						}
					}
				}
				return false;
			}
			else {
				throw new LaboratoryException("Laboratory name is not available");
			}
		}
		catch(LaboratoryException e) {
			throw e;
		}
	}
}
