package in.gov.vocport.dto;

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
        name = "containerDetailsDto",
        classes = @ConstructorResult(
                targetClass = ContainerDetailsDto.class,
                columns = {
                        @ColumnResult(name = "container_no", type = String.class),
                        @ColumnResult(name = "admission_chit_no", type = String.class),
                        @ColumnResult(name = "vehicle_no", type = String.class),
                        @ColumnResult(name = "imp_exp_trns", type = String.class),
                        @ColumnResult(name = "form_location", type = String.class),
                        @ColumnResult(name = "be_sb_no", type = String.class),
                        @ColumnResult(name = "vessel_no", type = String.class),
                        @ColumnResult(name = "vessel_name", type = String.class),
                        @ColumnResult(name = "calinv_vcn", type = String.class),
                        @ColumnResult(name = "local_origin", type = String.class),
                        @ColumnResult(name = "sical_vessel_name", type = String.class),
                        @ColumnResult(name = "sical_voyage_no", type = String.class),
                        @ColumnResult(name = "trn_shp_port", type = String.class),
                        @ColumnResult(name = "weighment_flag", type = String.class),
                        @ColumnResult(name = "security_wall", type = String.class),
                        @ColumnResult(name = "origin_port", type = String.class),
                        @ColumnResult(name = "reffer_cont_flg", type = String.class),
                        @ColumnResult(name = "in_port_status", type = String.class),
                        @ColumnResult(name = "loading_status", type = String.class),
                        @ColumnResult(name = "cargo_code", type = String.class),
                        @ColumnResult(name = "foreign_coastal_flag", type = String.class),
                        @ColumnResult(name = "eir", type = String.class),
                        @ColumnResult(name = "gate_in_date_time", type = LocalDateTime.class),
                        @ColumnResult(name = "shut_out", type = String.class),
                        @ColumnResult(name = "customs_examinations", type = String.class),
                        @ColumnResult(name = "hazardous", type = String.class),
                        @ColumnResult(name = "icd_cfs_fsc_none", type = String.class),
                        @ColumnResult(name = "sical_line_code", type = String.class),
                        @ColumnResult(name = "sical_line_name", type = String.class),
                        @ColumnResult(name = "bags", type = String.class),
                        @ColumnResult(name = "quantity", type = Double.class)
                }
        )
)
public class ContainerDetailsDto {
    @Id
    private String containerNo;
    private String admissionChitNo;
    private String vehicleNo;
    private String impExpTrns;
    private String formLocation;
    private String beSbNo;
    private String vesselNo;
    private String vesselName;
    private String calinvVcn;
    private String localOrigin;
    private String sicalVesselName;
    private String sicalVoyageNo;
    private String trnShpPort;
    private String weighmentFlag;
    private String securityWall;
    private String originPort;
    private String refferContFlg;
    private String inPortStatus;
    private String loadingStatus;
    private String cargoCode;
    private String foreignCoastalFlag;
    private String eir;
    private LocalDateTime gateInDateTime;
    private String shutOut;
    private String customsExaminations;
    private String hazardous;
    private String icdCfsFscNone;
    private String sicalLineCode;
    private String sicalLineName;
    private String bags;
    private Double quantity;
}
