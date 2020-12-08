package com.cg.patient.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.patient.domain.Laboratory;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.PatientException;
import com.cg.patient.service.MapValidationErrorService;
import com.cg.patient.service.PatientService;
/**
 * 
 * @author Sai Chaitanya Krishna
 *
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	/**
	 * Save patient object to database
	 * @param patient
	 * @param result
	 * @return patient object
	 */
	@PostMapping("")
	public ResponseEntity<?> createNewPatient(@Valid @RequestBody Patient patient, BindingResult result) {
		
		ResponseEntity<?> errorMap =  mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) 
			return errorMap;
		Patient newPatient = patientService.saveOrUpdate(patient);
		return new ResponseEntity<Patient>(newPatient, HttpStatus.CREATED);
	}
	/**
	 * Get the patient details by patient identifier
	 * @param patientIdentifier
	 * @return patient object
	 */
	@GetMapping("/{patientIdentifier}")
	public ResponseEntity<?> getPatientById(@PathVariable String patientIdentifier){
		return new ResponseEntity<Patient>( patientService.findPatientByPatientIdentifier(patientIdentifier),HttpStatus.OK);
	}
	/**
	 * Get all the patient details in database
	 */
	@GetMapping("/all")
	public Iterable<Patient> getAllPatients(){
		return patientService.findAllPatients();
	}
	/**
	 * Delete the patient object by patient identifier
	 * @param patientIdentifier
	 * @return the message
	 */
	@DeleteMapping("/{patientIdentifier}")
	public ResponseEntity<?> deletePatient(@PathVariable String patientIdentifier){
		patientService.deletePatientByPatientIdentifier(patientIdentifier);
		return new ResponseEntity<String> ("Patient with Id : "+patientIdentifier.toUpperCase()+" Deleted!",HttpStatus.OK);
	}
	/**
	 * Search laboratory with specified laboratory name and medical test name
	 * @param searchLaboratoryMap
	 * @return message
	 */
	@RequestMapping(value = "/searchLaboratoryByMedicalTest", method = RequestMethod.POST)
	private ResponseEntity<?> laboratoryAvailability(@RequestBody Map<String, String> searchLaboratoryMap){
		String laboratoryName = searchLaboratoryMap.get("laboratoryName");
		String medicalTestName = searchLaboratoryMap.get("medicalTestName");
		boolean medicalTestAvailablity = patientService.searchLaboratoryByMedicalTest(laboratoryName,medicalTestName);
		if(medicalTestAvailablity) {
			return new ResponseEntity<String>(medicalTestName+" is available in the lab "+laboratoryName,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(medicalTestName+" is not available in the lab "+laboratoryName,HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Book the laboratory with specified laboratory name and medical test name
	 * @param searchLaboratoryMap
	 * @return message
	 */
	@RequestMapping(value = "/bookAppointmentForMedicalTest", method = RequestMethod.POST)
	private ResponseEntity<?> bookAppointmentForMedicalTest(@RequestBody Map<String, String> searchLaboratoryMap){
		String laboratoryName = searchLaboratoryMap.get("laboratoryName");
		String medicalTestName = searchLaboratoryMap.get("medicalTestName");
		boolean medicalTestAppointment = patientService.bookAppointmentForMedicalTest(laboratoryName,medicalTestName);
		if(medicalTestAppointment) {
			return new ResponseEntity<String>("Appointment is booked for the test name "+medicalTestName+" in the laboratory "+laboratoryName,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Appointments are full",HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Upload medical test history and reports 
	 * @param patientIdentifier
	 * @param medicalTestHistoryAndReportsMapFromJson
	 * @return message
	 */
	@RequestMapping(value = "/uploadMedicalTestHistoryAndReports/{patientIdentifier}", method = RequestMethod.PATCH)
	private ResponseEntity<?> uploadMedicalTestHistoryAndReports(@PathVariable String patientIdentifier, @RequestBody Map<String, String> medicalTestHistoryAndReportsMapFromJson){
		System.out.println(medicalTestHistoryAndReportsMapFromJson);
		Patient patient = patientService.findPatientByPatientIdentifier(patientIdentifier.toUpperCase());
		if(patient==null) {
			throw new PatientException("Patient with identifier "+patientIdentifier+" not found");
		}
		else {
			if(patient.getMedicalTestHistoryAndReportsMap().isEmpty()) {
				patient.getMedicalTestHistoryAndReportsMap().put(medicalTestHistoryAndReportsMapFromJson.get("medicalTestName"), medicalTestHistoryAndReportsMapFromJson.get("reports"));
				System.out.println(patient.getMedicalTestHistoryAndReportsMap());
				patientService.saveOrUpdate(patient);
				return new ResponseEntity<String>("Medical test history and reports for the patient " + patientIdentifier+" successfylly uploaded", HttpStatus.OK);
				
			}
			else
			{
				patient.getMedicalTestHistoryAndReportsMap().put(medicalTestHistoryAndReportsMapFromJson.get("medicalTestName"),medicalTestHistoryAndReportsMapFromJson.get("reports"));
				System.out.println(patient.getMedicalTestHistoryAndReportsMap());
			}
			
		}
		return null;
		
	}
	/**
	 * Get the medical test history and reports from database
	 * @param patientIdentifier
	 * @return medical test history and reports map
	 */
	@RequestMapping(value= "/viewMedicalTestHistoryAndReports/{patientIdentifier}", method = RequestMethod.GET)
	public ResponseEntity<?> viewMedicalTestHistoryAndReports(@PathVariable String  patientIdentifier){
		Patient existingPatient = patientService.findPatientByPatientIdentifier(patientIdentifier);
		
		if(existingPatient.getMedicalTestHistoryAndReportsMap()==null) {
			return new ResponseEntity<String>("Test history is not available",HttpStatus.BAD_REQUEST);
			
		}else
		{
			return new ResponseEntity<Map<String,String>> (existingPatient.getMedicalTestHistoryAndReportsMap(),HttpStatus.OK);
		}
	}
}

