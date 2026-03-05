package in.gov.vocport.service;

import in.gov.vocport.security.jwtconfig.AuthenticationService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import in.gov.vocport.dto.UserListDto;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

	private final DataSource dataSource;
	private final OracleAuthService oracleAuthService;
	private final AuthenticationService authenticationService;

	public UserService(DataSource dataSource, OracleAuthService oracleAuthService, AuthenticationService authenticationService) {
		this.dataSource = dataSource;
        this.oracleAuthService = oracleAuthService;
        this.authenticationService = authenticationService;
    }
	
	public List<UserListDto> getAllUsersforAddUserAccess() throws SQLException {
	    List<UserListDto> users = new ArrayList<>();

	    String call = "{ call CT_DPE_PKG.GET_USER_PR(?, ?) }";

	    try (Connection conn = dataSource.getConnection();
	         CallableStatement cs = conn.prepareCall(call)) {
	        cs.setNull(1, Types.VARCHAR);
	        cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
	        cs.execute();
	        try (ResultSet rs = (ResultSet) cs.getObject(2)) {
	            while (rs.next()) {
	                String id = rs.getString("USER_ID");
	                String name = rs.getString("USER_NAME");
	                users.add(new UserListDto(id, name));
	            }
	        }
	    }
	    return users;
	}
	
	public List<UserListDto> getAllUsersforEditUserAccess() throws SQLException {
	    List<UserListDto> users = new ArrayList<>();

	    String call = "{ call CT_DPE_PKG.GET_USER_EDIT_PR(?, ?) }";

	    try (Connection conn = dataSource.getConnection();
	         CallableStatement cs = conn.prepareCall(call)) {
	        cs.setNull(1, Types.VARCHAR);
	        cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
	        cs.execute();
	        try (ResultSet rs = (ResultSet) cs.getObject(2)) {
	            while (rs.next()) {
	                String id = rs.getString("USER_ID");
	                String name = rs.getString("USER_NAME");
	                users.add(new UserListDto(id, name));
	            }
	        }
	    }
	    return users;
	}

	public void loginValidate(HashMap<String, String> userDetails, HashMap<String, Object> result) {
		String username = userDetails.get("userName");
		String password = userDetails.get("password");
		String res = oracleAuthService.authenticate(username, password);

		if (res == null || !res.startsWith("Y")) {
			throw new BadCredentialsException("Invalid username or password");
		}
		String[] parts = res.split("~");
		String flag = parts[0];
		String role = parts[1];
		String userName = parts[2];
		String userId = parts[3];
		String userDisplayName = parts.length > 4 ? parts[4] : userName;

		if (!"Y".equalsIgnoreCase(flag)) {
			throw new BadCredentialsException("Authentication failed");
		}

		String token = authenticationService.generateToken(userName);
		result.put("success", token);
		result.put("loginId", userName);
		result.put("userId", userId);
		result.put("EmployeeType" , role);
		result.put("userDisplayName", userDisplayName);
	}
}
