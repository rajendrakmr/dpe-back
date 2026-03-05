package in.gov.vocport.dto;

public class LocationDto {
	private String code;
	private String name;

	public LocationDto(String code, String name) {
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
