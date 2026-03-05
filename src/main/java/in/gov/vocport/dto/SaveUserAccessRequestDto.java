package in.gov.vocport.dto;

import java.util.List;

public class SaveUserAccessRequestDto {

	private String userId;
	private List<UserAccessAddMenuSelectionDto> selections;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserAccessAddMenuSelectionDto> getSelections() {
		return selections;
	}

	public void setSelections(List<UserAccessAddMenuSelectionDto> selections) {
		this.selections = selections;
	}

}