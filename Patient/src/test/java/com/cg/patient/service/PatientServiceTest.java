package com.cg.patient.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Laboratory;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.LaboratoryException;
import com.cg.patient.exception.PatientException;
import com.cg.patient.repository.PatientRepository;

/**
 * 
 * @author Sai Chaitanya Krishna
 * Description : Junit test cases for patient service class
 *
 */
class PatientServiceTest {

	@Mock
	PatientRepository patientRepository;

	@InjectMocks
	PatientService patientService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	/**
	 * Test case to check patient object is present in database
	 */
	@Test
	void test1_saveOrUpdate()
	{
		Patient patient = new Patient("PA03","sai", 22,11232L,"as");
		BDDMockito.given(patientRepository.save(patient)).willReturn(patient);
		Patient p = patientService.saveOrUpdate(patient);
		assertNotNull(p);
		assertEquals("sai",p.getPatientName());
		assertEquals("PA03",p.getPatientIdentifier());
		assertEquals(22,p.getPatientAge());
		assertEquals(11232L,p.getPhoneNumber());
		assertEquals("as",p.getPatientAddress());

	}
	/**
	 * Test case that throws exception when student object is not there in database
	 */
	@Test
	void test2_saveOrUpdate_ThrowPatientException()
	{
		Patient patient = new Patient("PA03","sa1", 22,11232L,"as");
		BDDMockito.given(patientRepository.save(patient)).willThrow(new PatientException());
		assertThrows(PatientException.class, ()->patientService.saveOrUpdate(patient));
	}
	/**
	 * Test case to check the patient by patient identifier present in database
	 */
	@Test
	void test3_findPatientByPatientIdentifier() {
		BDDMockito.given(patientRepository.findByPatientIdentifier("PA03")).willReturn((new Patient("pa03","sai",22,11232L,"as")));
		Patient patient= patientService.findPatientByPatientIdentifier("PA03");
		assertNotNull(patient);
		assertEquals("sai", patient.getPatientName());
		assertEquals(22, patient.getPatientAge());
		assertEquals(11232L,patient.getPhoneNumber());
		assertEquals("as",patient.getPatientAddress());
		
	}
	/**
	 * Test case that throws exception when patient with patient identifier is not present
	 */
	@Test
	void test4_findPatientByPatientIDentifier()
	{
		BDDMockito.given(patientRepository.findByPatientIdentifier("PA03")).willThrow(new PatientException());
		assertThrows(PatientException.class, ()->patientService.findPatientByPatientIdentifier("PA03"));
	}
	
	@Test
	void test5_searchLaboratoryByMedicalTest() {
		List<String> medicalTestList = Arrays.asList(new String[] {"Blood test","X-ray"});
		assertNotNull(medicalTestList);
		assertEquals("abc",patientService.laboratory.getLaboratoryName());
			for(String test:medicalTestList) {
				if(test.equalsIgnoreCase("Blood test")) {
					assertEquals("Blood test",test);
				}
				else if(test.equalsIgnoreCase("x-ray")) {
					assertEquals("X-ray",test);
				}
			}
		}
	@Test
	void test6_bookAppointmentForMedicalTest() {
		Doctor doctor = new Doctor("fgh","ent",3427852452L);
		List<Doctor> doctorList = Arrays.asList(new Doctor[]{doctor});
		List<String> medicalTestList = Arrays.asList(new String[] {"Blood test","X-ray"});
		Laboratory laboratory = new Laboratory("abc",doctorList,medicalTestList);
		assertNotNull(medicalTestList);
		assertEquals("abc",patientService.laboratory.getLaboratoryName());
			for(String test:medicalTestList) {
				if(test.equalsIgnoreCase("Blood test")) {
					assertEquals("Blood test",test);
				}
				else if(test.equalsIgnoreCase("x-ray")) {
					assertEquals("X-ray",test);
				}
			}
		}
}