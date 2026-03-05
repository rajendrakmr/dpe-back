package in.gov.vocport.dto;

public class LinerDto {
	private String partyCode;
	private String agentName;

	public LinerDto(String partyCode, String agentName) {
		this.partyCode = partyCode;
		this.agentName = agentName;
	}

	public String getPartyCode() {
		return partyCode;
	}

	public String getAgentName() {
		return agentName;
	}
}
