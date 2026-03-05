package in.gov.vocport.dto;

public class VesselDto {
 private String vesselNo;
 private String vesselName;
 private String voyageNumber;

 public VesselDto(String vesselNo, String vesselName, String voyageNumber) {
     this.vesselNo = vesselNo;
     this.vesselName = vesselName;
     this.voyageNumber = voyageNumber;
 }

 public String getVesselNo() { return vesselNo; }
 public String getVesselName() { return vesselName; }
 public String getVoyageNumber() { return voyageNumber; }
}


