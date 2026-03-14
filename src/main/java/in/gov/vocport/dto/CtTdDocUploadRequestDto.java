package in.gov.vocport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CtTdDocUploadRequestDto {
    private Long srlNo;

    private String documentType;

    private String documentRemarks;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate docUploadDate;

    private String cancelFlag;

    private String dccDownLink;

    private MultipartFile file;
}
