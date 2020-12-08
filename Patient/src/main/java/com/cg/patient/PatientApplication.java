package com.cg.patient;

import org.springframework.boot.SpringApplication;
import java.util.regex.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan({"main.controllers", "main.repositories"})
//@EnableJpaRepositories("main.repositories")
public class PatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

}
