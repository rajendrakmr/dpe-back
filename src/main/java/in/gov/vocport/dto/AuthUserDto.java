package in.gov.vocport.dto;

import java.io.Serializable;

public class AuthUserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String userId;
	private final String username;
	private final String displayName;

	public AuthUserDto(String userId, String username, String displayName) {
		this.userId = userId;
		this.username = username;
		this.displayName = displayName;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return username == null ? userId : username;
	}
}