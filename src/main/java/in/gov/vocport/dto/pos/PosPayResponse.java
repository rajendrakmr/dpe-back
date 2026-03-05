package in.gov.vocport.dto.pos;

import lombok.Data;

@Data
public class PosPayResponse {
    private boolean success;
    private String errorCode;
    private String errorMessage;
    private String p2pRequestId;
}
