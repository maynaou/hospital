package com.example.hospital;

import com.example.hospital.entities.Patient;
import com.example.hospital.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	// @Bean
	public CommandLineRunner start(PatientRepository patientRepository) {
		// return new CommandLineRunner() {
		// @Override
		// public void run(String... args) throws Exception {

		// }
		// };
		return args -> {
			Patient p1 = new Patient();
			p1.setNom("Youssfi");
			p1.setPrenom("Mohamed");
			p1.setScore(101);
			p1.setMalade(false);
			p1.setDateNaissance(new Date());
			Patient p2 = new Patient(null, "Aynaou", "mohssin", new Date(), 1200, false);
			Patient p3 = Patient.builder()
					.nom("yaya")
					.dateNaissance(new Date())
					.build();
			patientRepository.save(p1);
			patientRepository.save(p2);
			patientRepository.save(p3);
			List<Patient> patients = patientRepository.findAll();
			patients.forEach(p -> {
				System.out.println(p.toString());
			});
		};
	}
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
