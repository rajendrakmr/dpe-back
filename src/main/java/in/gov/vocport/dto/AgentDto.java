package in.gov.vocport.dto;

public class AgentDto {
	private String code;
	private String name;

	public AgentDto(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
