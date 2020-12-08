package com.cg.patient.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Sai Chaitanya Krishna
 * description : Laboratory class which have details about the lab name and medical test
 */
public class Laboratory{
	/**
	 * Name of the Laboratory
	 */
	private String laboratoryName;
	/**
	 * Which contains list of available doctors in the laboratory
	 */
	private  List<Doctor> doctorList;
	/**
	 * which contains list of available medical tests in the laboratory
	 */
	private  List<String> medicalTestList;
	/**
	 * Contains number of appointments accepted per day
	 */
	private static int noOfMedicalTestAppointments=5;
	/**
	 * No argument constructor
	 */
	public Laboratory() {
		super();
	}
	/**
	 * Create an instance of laboratory with given parameters.
	 * @param laboratoryName
	 * @param doctorList
	 * @param medicalTestList
	 */
	public Laboratory(String laboratoryName, List<Doctor> doctorList, List<String> medicalTestList) {
		super();
		this.laboratoryName = laboratoryName;
		this.doctorList = doctorList;
		this.medicalTestList = medicalTestList;
	}
	/**
	 * @return laboratory name
	 */
	public String getLaboratoryName() {
		return laboratoryName;
	}
	/**
	 * Sets the name of the laboratory
	 * @param laboratoryName
	 */
	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}
	/**
	 * @return the list of doctors in the laboratory
	 */
	public List<Doctor> getDoctorList() {
		return doctorList;
	}
	/**
	 * Sets doctors to the doctor list
	 * @param doctorList
	 */
	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}
	/**
	 * @return list of available medical tests in the laboratory 
	 */
	public List<String> getMedicalTestList() {
		return medicalTestList;
	}
	/**
	 * Set available medical tests to the list
	 * @param medicalTestList
	 */
	public void setMedicalTestList(List<String> medicalTestList) {
		this.medicalTestList = medicalTestList;
	}
	/**
	 * @return number of appointments taken per day
	 */
	public static int getNoOfMedicalTestAppointments() {
		return noOfMedicalTestAppointments;
	}
	/**
	 * Sets number of appointments
	 * @param noOfMedicalTestAppointments
	 */
	public static void setNoOfMedicalTestAppointments(int noOfMedicalTestAppointments) {
		Laboratory.noOfMedicalTestAppointments = noOfMedicalTestAppointments;
	}
	@Override
	public String toString() {
		return "Laboratory [laboratoryName=" + laboratoryName + ", doctorList=" + doctorList + ", medicalTestList="
				+ medicalTestList + "]";
	}
	
	
}
