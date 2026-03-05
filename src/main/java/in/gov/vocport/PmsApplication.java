package in.gov.vocport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsApplication.class, args);
		
		/*
		 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); String
		 * rawPassword = "Abcd123#"; String encodedPassword =
		 * encoder.encode(rawPassword); System.out.println("Encoded password: " +
		 * encodedPassword);
		 */
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    //System.out.println(encoder.matches("1234", "$2a$10$S9z8VKMK02bHuEDNPQJijeo8kx58sIRNWdjqjIbcvJWx0K7mYO.9C"));
	}
}
