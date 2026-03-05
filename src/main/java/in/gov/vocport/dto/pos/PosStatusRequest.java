package in.gov.vocport.dto.pos;

import lombok.Data;

@Data
public class PosStatusRequest {
	private String username;
    private String appKey;
    private String origP2pRequestId;
}
