package in.gov.vocport.dto;

import java.util.Date;

public class GateInRowDto {
	private String chit_no;
	private String container_no;
	private String vehicle_no;
	private Date gateInDateTime;
	private String party_cd;
	private String eir;
	private String vessel_no;
	private String loading_status;
	private String container_size;
	private String foreign_coastal_flag;

	public String getChit_no() {
		return chit_no;
	}

	public void setChit_no(String chit_no) {
		this.chit_no = chit_no;
	}

	public String getContainer_no() {
		return container_no;
	}

	public void setContainer_no(String container_no) {
		this.container_no = container_no;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public Date getGateInDateTime() {
		return gateInDateTime;
	}

	public void setGateInDateTime(Date gateInDateTime) {
		this.gateInDateTime = gateInDateTime;
	}

	public String getParty_cd() {
		return party_cd;
	}

	public void setParty_cd(String party_cd) {
		this.party_cd = party_cd;
	}

	public String getEir() {
		return eir;
	}

	public void setEir(String eir) {
		this.eir = eir;
	}

	public String getVessel_no() {
		return vessel_no;
	}

	public void setVessel_no(String vessel_no) {
		this.vessel_no = vessel_no;
	}

	public String getLoading_status() {
		return loading_status;
	}

	public void setLoading_status(String loading_status) {
		this.loading_status = loading_status;
	}

	public String getContainer_size() {
		return container_size;
	}

	public void setContainer_size(String container_size) {
		this.container_size = container_size;
	}

	public String getForeign_coastal_flag() {
		return foreign_coastal_flag;
	}

	public void setForeign_coastal_flag(String foreign_coastal_flag) {
		this.foreign_coastal_flag = foreign_coastal_flag;
	}
	
	
}