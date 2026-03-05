package in.gov.vocport.dto.pos;

import java.math.BigDecimal;
import java.util.Map;
import lombok.Data;

@Data
public class PosPayRequest {
    private String username;
    private String appKey;
    private BigDecimal amount;
    private String externalRefNumber;
    private Map<String, String> pushTo; // deviceId
    private String mode; // e.g. "CARD" or "ALL"
}