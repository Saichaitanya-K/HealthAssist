package com.cg.patient.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.patient.domain.Laboratory;
import com.cg.patient.domain.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
	
	Patient findByPatientIdentifier(String patientIdentifier);
	
}
