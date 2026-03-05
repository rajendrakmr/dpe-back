package in.gov.vocport.dto.pos;

import lombok.Data;

@Data
public class PosStatusResponse {
	private boolean success;
    private String messageCode;
    private String status;   // AUTHORIZED / FAILED / null
    private String errorCode;
    private String errorMessage;
}
