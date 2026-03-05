//package in.gov.vocport.security;
//
//import in.gov.vocport.dto.AuthUserDto;
//import in.gov.vocport.service.OracleAuthService;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import java.util.List;
//
//@Component
//public class OracleAuthenticationProvider implements AuthenticationProvider {
//
//	private final OracleAuthService oracleAuthService;
//
//	public OracleAuthenticationProvider(OracleAuthService oracleAuthService) {
//		this.oracleAuthService = oracleAuthService;
//	}
//
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String username = authentication.getName();
//		String password = authentication.getCredentials().toString();
//		String result = oracleAuthService.authenticate(username, password);
//
//		if (result == null || !result.startsWith("Y")) {
//			throw new BadCredentialsException("Invalid username or password");
//		}
//		String[] parts = result.split("~");
//		String flag = parts[0];
//		String role = parts[1];
//		String userName = parts[2];
//		String userId = parts[3];
//		String userDisplayName = parts.length > 4 ? parts[4] : userName;
//
//		if (!"Y".equalsIgnoreCase(flag)) {
//			throw new BadCredentialsException("Authentication failed");
//		}
//		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
//		AuthUserDto principal = new AuthUserDto(userId, userName, userDisplayName);
//		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, password,
//				authorities);
//		SecurityContextHolder.getContext().setAuthentication(auth);
//		try {
//			RequestContextHolder.currentRequestAttributes().setAttribute("displayName", userDisplayName,
//					RequestAttributes.SCOPE_SESSION);
//		} catch (Exception ex) {
//		}
//		return auth;
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//	}
//}
