package com.cg.patient.domain;
/**
 * 
 * @author Sai Chaitanya Krishna
 * Description : Class contains details of doctor
 *
 */
public class Doctor{
	/**
	 * Name of the doctor
	 */
	private String doctorName;
	/**
	 * Specialization of the doctor
	 */
	private String specialization;
	/**
	 * Phone number of the doctor
	 */
	private long doctorPhNo;
	
	/**
	 * Create an instance of doctor with given parameters.
	 * @param doctorId
	 * @param doctorName
	 * @param specialization
	 * @param department
	 * @param doctorPhNo
	 *  
	 */
	public Doctor(String doctorName, String specialization, long doctorPhNo) {
		super();
		
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.doctorPhNo = doctorPhNo;
	}
	
	public Doctor()
	{
		super();
	}
	/**
	 * @return the doctor name present in the database.
	 */

	public String getDoctorName() {
		return doctorName;
	}
	/**
	 * set the name of the doctor.
	 * @param doctorName
	 */

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	/**
	 * @return the specialization of the doctor.
	 */

	public String getSpecialization() {
		return specialization;
	}
	/**
	 * set the specialization of the doctor.
	 * @param specialization
	 */

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	/**
	 * @return the phone number of the doctor.
	 */

	public long getDoctorPhNo() {
		return doctorPhNo;
	}

	/**
	 * set the phone number of the doctor.
	 * @param doctorPhNo
	 */
	public void setDoctorPhNo(long doctorPhNo) {
		this.doctorPhNo = doctorPhNo;
	}

	@Override
	public String toString() {
		return "Doctor [doctorName=" + doctorName + ", specialization=" + specialization
				+ ", doctorPhNo=" + doctorPhNo + "]";
	}
	
	
}
