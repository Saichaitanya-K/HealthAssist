package com.cg.patient.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @author Sai Chaitanya Krishna
 * Description : Class contains patient details
 */
@Entity
public class Patient {
	/**
	 * Patient id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long patientId;
	/**
	 * Patient identifier which should be unique
	 */
	@Column(unique = true,updatable = false)
	@NotBlank(message="Patient Identifier can't be blank")
	@Size(min=4, max=5,message = "Size must be between 4 to 5 characters")
	private String patientIdentifier;
	/**
	 * Name of the patient
	 */
	@NotBlank(message="Patient Name is requried")
	private String patientName;
	/**
	 * patient age
	 */
	@NotNull(message="Patient Age is required")
	private int patientAge;
	/**
	 *Phone number of a patient
	 */
	@NotNull(message="Phone number can't be blank")
	private long phoneNumber;
	/**
	 * patient address
	 */
	@NotBlank(message="Patient address can't be blank")
	private String patientAddress;
	/**
	 * Patient prescription given by doctor
	 */
	private String prescription;
	
	
	 /**
	  * Medical history of patient
	  */
	private String medicalHistory;
	/**
	 * Map holding the medical test as a key and reports as value
	 * Patient medical test history and reports
	 */
	@ElementCollection  
	private Map<String,String> medicalTestHistoryAndReportsMap;

	/**
	 * Parameterized Constructor.
	 * @param patientIdentifier
	 * @param patientName
	 * @param patientAge
	 * @param phoneNumber
	 * @param patientAddress
	 */
	public Patient(String patientIdentifier,String patientName, int patientAge, long phoneNumber, String patientAddress) {
		super();
		this.patientIdentifier=patientIdentifier;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.phoneNumber = phoneNumber;
		this.patientAddress = patientAddress;
		this.medicalTestHistoryAndReportsMap=new HashMap<>();
	}
	
	public Patient() {
		super();
	}

	/**
	 * @return the patient id present in the database.
	 */
	public long getPatientId() {
		return patientId;
	}
	
	/**
	 * Set the id of the patient.
	 * @param patientId
	 */
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	
	/**
	 * @return the patient name.
	 */
	public String getPatientName() {
		return patientName;
	}
	
	/**
	 * Set the name of the patient.
	 * @param patientName
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	/**
	 * @return the age of the patient.
	 */
	public int getPatientAge() {
		return patientAge;
	}
	
	/**
	 * Set the age of the patient.
	 * @param patientAge
	 */
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	
	/**
	 * @return the phone number of the patient.
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phone number of the patient.
	 * @param phoneNumber
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the address of the patient.
	 */
	public String getPatientAddress() {
		return patientAddress;
	}
	
	/**
	 * set the address of the patient.
	 * @param patientAddress
	 */
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	
	/**
	 * @return the prescription of the patient.
	 */
	public String getPrescription() {
		return prescription;
	}
	
	/**
	 * Set the prescription of the patient.
	 * @param prescription
	 */
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
	/**
	 * @return the patient identifier.
	 */
	public String getPatientIdentifier() {
		return patientIdentifier;
	}

	/**
	 * Set the patient identifier.
	 * @param patientIdentifier
	 */
	public void setPatientIdentifier(String patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
	}
	/**
	 * @return map of medical test as a key and reports as value
	 */
	public Map<String, String> getMedicalTestHistoryAndReportsMap() {
		return medicalTestHistoryAndReportsMap;
	}
	/**
	 * Sets medical test and report to the map
	 * @param medicalTestHistoryAndReportsMap
	 */
	public void setMedicalTestHistoryAndReportsMap(Map<String, String> medicalTestHistoryAndReportsMap) {
		this.medicalTestHistoryAndReportsMap = medicalTestHistoryAndReportsMap;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", patientAge=" + patientAge
				+ ", phoneNumber=" + phoneNumber + ", patientAddress=" + patientAddress + ", prescription="
				+ prescription + ", patientIdentifier=" + patientIdentifier + ", medicalTestHistoryAndReportsMap="
				+ medicalTestHistoryAndReportsMap + "]";
	}

	

	

		
	
}

