package in.gov.vocport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CtThDocUploadRequestDto {
    private String vesselNo;

    private String vcn;

    private String vesselName;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime berthedTime;

    private String agentCustomerId;

    private String agentCustomerName;

    private String zoneId;

    private List<CtTdDocUploadRequestDto> documents;
}
