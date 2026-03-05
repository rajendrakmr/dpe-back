package in.gov.vocport.dto;

import lombok.Data;

@Data
public class ContainerInPortDto {
	private String containerNo;
    private String chitNo;
    private String gateInDateTime;
    private String agentCode;
    private String agentName;
    private String boeNo;
    private Integer containerSize;
    private String loadingStatus;
    private String foreignCoastalFlag;
}
