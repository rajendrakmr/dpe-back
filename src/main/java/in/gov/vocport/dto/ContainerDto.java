package in.gov.vocport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SqlResultSetMapping(
        name = "containerDto",
        classes = @ConstructorResult(
                targetClass = ContainerDto.class,
                columns = {
                        @ColumnResult(name = "container_no", type = String.class),
                        @ColumnResult(name = "admission_time", type = LocalDateTime.class),
                        @ColumnResult(name = "admission_chit_no", type = String.class),
                        @ColumnResult(name = "party_cd", type = String.class),
                        @ColumnResult(name = "CH_Agent_name", type = String.class),
                        @ColumnResult(name = "shipping_bill_no", type = String.class),
                        @ColumnResult(name = "shipper", type = String.class),
                        @ColumnResult(name = "container_size", type = String.class)
                }
        )
)
public class ContainerDto {
    @Id
    private String containerNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime admissionTime;
    private String admissionChitNo;
    private String partyCd;
    private String cHAgentName;
    private String shippingBillNo;
    private String shipper;
    private String containerSize;
}
