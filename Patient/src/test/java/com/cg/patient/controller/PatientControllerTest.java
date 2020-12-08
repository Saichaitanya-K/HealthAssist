package com.cg.patient.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.PatientException;
import com.cg.patient.service.MapValidationErrorService;
import com.cg.patient.service.PatientService;
import com.cg.patient.web.PatientController;
/**
 * 
 * @author Sai Chaitanya Krishna
 * Description : Test cases for patient controller class
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientController.class)
class PatientControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PatientService patientService;
	
	@MockBean
	MapValidationErrorService mapValidationErrorService;

	@InjectMocks
	PatientController patientController;
	/**
	 * Test case that check patient with identifier present in database  
	 * @throws Exception
	 */
	@Test
	void test1_getPatientById() throws Exception
	{
		BDDMockito.given(patientService.findPatientByPatientIdentifier("PA03")).willReturn(new Patient("PA03","sai",22,11232L,"as"));
		mockMvc.perform(get("/api/patients/PA03"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("patientName").value("sai"))
		.andExpect(jsonPath("patientIdentifier").value("PA03"))
		.andExpect(jsonPath("patientAge").value(22))
		.andExpect(jsonPath("phoneNumber").value(11232L));
		
	}

	
	@Test
	void test2_getPatientById() throws Exception
	{
		BDDMockito.given(patientService.findPatientByPatientIdentifier("PA03")).willThrow(new PatientException());
		mockMvc.perform(get("/api/patients/PA03"))
		.andExpect(status().isBadRequest());
	}
	/**
	 * Test case for searching the laboratory by medical test method
	 * @throws Exception
	 */
	@Test
	void test3_searchLaboratoryByMedicalTest() throws Exception
	{
		when(patientService.searchLaboratoryByMedicalTest("abc","Blood test")).thenReturn(true);
		mockMvc.perform(get("/api/patients/searchLaboratoryByMedicalTest"))
		.andExpect(status().isOk());
	}

	@Test
	void test4_searchLaboratoryByMedicalTest() throws Exception
	{
		when(patientService.searchLaboratoryByMedicalTest("ab","abc")).thenReturn(false);
		mockMvc.perform(post("/api/patients/searchLaboratoryByMedicalTest"))
		.andExpect(status().isBadRequest());
	}
	/**
	 * Test case for booking appointment for medical test
	 * @throws Exception
	 */
	@Test
	void test5_bookAppointmentForMedicalTest() throws Exception
	{
		when(patientService.bookAppointmentForMedicalTest("abc","Blood test")).thenReturn(true);
		mockMvc.perform(get("/api/patients/searchLaboratoryByMedicalTest"))
		.andExpect(status().isOk());
	}
	@Test
	void test6_bookAppointmentForMedicalTest() throws Exception
	{
		when(patientService.bookAppointmentForMedicalTest("ab","Blood")).thenReturn(false);
		mockMvc.perform(post("/api/patients/searchLaboratoryByMedicalTest"))
		.andExpect(status().isBadRequest());
	}
}