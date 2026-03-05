//package in.gov.vocport.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.web.SecurityFilterChain;
////import in.gov.vocport.security.OracleAuthenticationProvider;
//
//@Configuration
//public class SecurityConfig {
//
////	private final OracleAuthenticationProvider oracleAuthenticationProvider;
////
////	public SecurityConfig(OracleAuthenticationProvider oracleAuthenticationProvider) {
////		this.oracleAuthenticationProvider = oracleAuthenticationProvider;
////	}
//
//	//Dummy Configuration for Testing POS Integration from POSTMAN without login
//	//@Bean
//	//SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		//http.csrf(csrf -> csrf.disable())
//				//.authorizeHttpRequests(
//						//auth -> auth.requestMatchers("/", "/css/**", "/js/**", "/images/**", "/api/payments/**")
//								//.permitAll().anyRequest().authenticated())
//				//.formLogin(form -> form.loginPage("/") // this tells Spring to use your own
//						//.loginProcessingUrl("/perform_login") // form action must post here
//						//.defaultSuccessUrl("/home", true).permitAll())
//				//.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())
//				//.authenticationProvider(oracleAuthenticationProvider);
//		//return http.build();
//	//}
//
//
//	//Actual Configuration
//
////	@Bean
////	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////		http.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
////				.anyRequest().authenticated())
////				.formLogin(form -> form.loginPage("/").loginProcessingUrl("/perform_login")
////						.defaultSuccessUrl("/home", true).permitAll())
////				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())
////				.authenticationProvider(oracleAuthenticationProvider);
////		return http.build();
////	}
////
////	@Bean
////	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
////		return config.getAuthenticationManager();
////	}
//}
