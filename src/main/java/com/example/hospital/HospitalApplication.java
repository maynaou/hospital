package com.example.hospital;

import com.example.hospital.entities.Patient;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.security.service.AccountService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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
	//@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
		PasswordEncoder passwordEncoder = passwordEncoder();
		return args -> {
			UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user11");
			if (u1==null)
			jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build());
		    UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user22");
			if (u2==null)
			jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build());
		    UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin2");
			if (u3==null)
			jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build());
		};
	}

	//@Bean

	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
		return args -> {
             accountService.addNewRole("USER");
			 accountService.addNewRole("ADMIN");
			 accountService.addNewUser("user1", "1234", "user1@gmail.com", "1234"); 
			 accountService.addNewUser("user2", "1234", "user2@gmail.com", "1234");  
			 accountService.addNewUser("admin", "1234", "admin@gmail.com", "1234");
			 accountService.addRoleToUser("user1", "USER");
			 accountService.addRoleToUser("user2", "USER");
			 accountService.addRoleToUser("admin", "USER");
			 accountService.addRoleToUser("admin", "ADMIN");
		};
	}
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
