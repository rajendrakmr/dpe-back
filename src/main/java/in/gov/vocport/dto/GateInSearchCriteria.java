package in.gov.vocport.dto;

public class GateInSearchCriteria {

	private String chitNo;
	private String containerNo;
	private String vehicleNo;
	/** dd-MM-yyyy or similar, represents gate_in_date_time date part */
	private String gateInDate;
	private String agent;
	private String eir;
	private String vesselNo;
	private String loadingStatus;
	private String containerSize;
	private String voyage; // sical_voyage_no

	// getters & setters
	public String getChitNo() {
		return chitNo;
	}

	public void setChitNo(String chitNo) {
		this.chitNo = chitNo;
	}

	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getGateInDate() {
		return gateInDate;
	}

	public void setGateInDate(String gateInDate) {
		this.gateInDate = gateInDate;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getEir() {
		return eir;
	}

	public void setEir(String eir) {
		this.eir = eir;
	}

	public String getVesselNo() {
		return vesselNo;
	}

	public void setVesselNo(String vesselNo) {
		this.vesselNo = vesselNo;
	}

	public String getLoadingStatus() {
		return loadingStatus;
	}

	public void setLoadingStatus(String loadingStatus) {
		this.loadingStatus = loadingStatus;
	}

	public String getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}
}
