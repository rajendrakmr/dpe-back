package in.gov.vocport.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CT_T_CONTAINER_IN_PORT_AREA")
public class ContainerGateInEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String chit_no;

	private String vehicle_no, from_loc_id, to_loc_id, vessel_no, party_cd, origin_port, trn_shp_port, shipper,
			weighment_flag, port_in_through, port_out_through, imp_exp_trns, eir, ia_ea_no, boe_no, authorised_by,
			local_origin, container_no, container_size, loading_status, cargo_code, bags, foreign_coastal_flag,
			quantity, icd_cfs_fsc_none, hazardous, customs_examinations, reffer_cont_flg, sical_vessel_no,
			sical_voyage_no, sical_vessel_name, sical_line_code, sical_line_name, sical_agent_name, sical_agent_code,
			security_wall, in_port_status, rent_id, gate_in_through, gate_out_through, del_flag,
			terminal_operator, shut_out;

	private Date sical_in_date_time, sical_out_date_time, sical_landing_date_time, vessel_in_date_time,
			vessel_out_date_time, modified_on;

	@Column(name = "GATE_IN_DATE_TIME")
	private Date gateInDateTime;

	@Column(name = "GATE_OUT_DATE_TIME")
	private Date gateOutDateTime;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_ON")
	private Date createdOn;
	
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	public String getChit_no() {
		return chit_no;
	}

	public void setChit_no(String chit_no) {
		this.chit_no = chit_no;
	}

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public String getFrom_loc_id() {
		return from_loc_id;
	}

	public void setFrom_loc_id(String from_loc_id) {
		this.from_loc_id = from_loc_id;
	}

	public String getTo_loc_id() {
		return to_loc_id;
	}

	public void setTo_loc_id(String to_loc_id) {
		this.to_loc_id = to_loc_id;
	}

	public String getVessel_no() {
		return vessel_no;
	}

	public void setVessel_no(String vessel_no) {
		this.vessel_no = vessel_no;
	}

	public String getParty_cd() {
		return party_cd;
	}

	public void setParty_cd(String party_cd) {
		this.party_cd = party_cd;
	}

	public String getOrigin_port() {
		return origin_port;
	}

	public void setOrigin_port(String origin_port) {
		this.origin_port = origin_port;
	}

	public String getTrn_shp_port() {
		return trn_shp_port;
	}

	public void setTrn_shp_port(String trn_shp_port) {
		this.trn_shp_port = trn_shp_port;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getWeighment_flag() {
		return weighment_flag;
	}

	public void setWeighment_flag(String weighment_flag) {
		this.weighment_flag = weighment_flag;
	}

	public String getPort_in_through() {
		return port_in_through;
	}

	public void setPort_in_through(String port_in_through) {
		this.port_in_through = port_in_through;
	}

	public String getPort_out_through() {
		return port_out_through;
	}

	public void setPort_out_through(String port_out_through) {
		this.port_out_through = port_out_through;
	}

	public String getImp_exp_trns() {
		return imp_exp_trns;
	}

	public void setImp_exp_trns(String imp_exp_trns) {
		this.imp_exp_trns = imp_exp_trns;
	}

	public String getEir() {
		return eir;
	}

	public void setEir(String eir) {
		this.eir = eir;
	}

	public String getIa_ea_no() {
		return ia_ea_no;
	}

	public void setIa_ea_no(String ia_ea_no) {
		this.ia_ea_no = ia_ea_no;
	}

	public String getBoe_no() {
		return boe_no;
	}

	public void setBoe_no(String boe_no) {
		this.boe_no = boe_no;
	}

	public String getAuthorised_by() {
		return authorised_by;
	}

	public void setAuthorised_by(String authorised_by) {
		this.authorised_by = authorised_by;
	}

	public String getLocal_origin() {
		return local_origin;
	}

	public void setLocal_origin(String local_origin) {
		this.local_origin = local_origin;
	}

	public String getContainer_no() {
		return container_no;
	}

	public void setContainer_no(String container_no) {
		this.container_no = container_no;
	}

	public String getContainer_size() {
		return container_size;
	}

	public void setContainer_size(String container_size) {
		this.container_size = container_size;
	}

	public String getLoading_status() {
		return loading_status;
	}

	public void setLoading_status(String loading_status) {
		this.loading_status = loading_status;
	}

	public String getCargo_code() {
		return cargo_code;
	}

	public void setCargo_code(String cargo_code) {
		this.cargo_code = cargo_code;
	}

	public String getBags() {
		return bags;
	}

	public void setBags(String bags) {
		this.bags = bags;
	}

	public String getForeign_coastal_flag() {
		return foreign_coastal_flag;
	}

	public void setForeign_coastal_flag(String foreign_coastal_flag) {
		this.foreign_coastal_flag = foreign_coastal_flag;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getIcd_cfs_fsc_none() {
		return icd_cfs_fsc_none;
	}

	public void setIcd_cfs_fsc_none(String icd_cfs_fsc_none) {
		this.icd_cfs_fsc_none = icd_cfs_fsc_none;
	}

	public String getHazardous() {
		return hazardous;
	}

	public void setHazardous(String hazardous) {
		this.hazardous = hazardous;
	}

	public String getCustoms_examinations() {
		return customs_examinations;
	}

	public void setCustoms_examinations(String customs_examinations) {
		this.customs_examinations = customs_examinations;
	}

	public String getReffer_cont_flg() {
		return reffer_cont_flg;
	}

	public void setReffer_cont_flg(String reffer_cont_flg) {
		this.reffer_cont_flg = reffer_cont_flg;
	}

	public String getSical_vessel_no() {
		return sical_vessel_no;
	}

	public void setSical_vessel_no(String sical_vessel_no) {
		this.sical_vessel_no = sical_vessel_no;
	}

	public String getSical_voyage_no() {
		return sical_voyage_no;
	}

	public void setSical_voyage_no(String sical_voyage_no) {
		this.sical_voyage_no = sical_voyage_no;
	}

	public String getSical_vessel_name() {
		return sical_vessel_name;
	}

	public void setSical_vessel_name(String sical_vessel_name) {
		this.sical_vessel_name = sical_vessel_name;
	}

	public String getSical_line_code() {
		return sical_line_code;
	}

	public void setSical_line_code(String sical_line_code) {
		this.sical_line_code = sical_line_code;
	}

	public String getSical_line_name() {
		return sical_line_name;
	}

	public void setSical_line_name(String sical_line_name) {
		this.sical_line_name = sical_line_name;
	}

	public String getSical_agent_name() {
		return sical_agent_name;
	}

	public void setSical_agent_name(String sical_agent_name) {
		this.sical_agent_name = sical_agent_name;
	}

	public String getSical_agent_code() {
		return sical_agent_code;
	}

	public void setSical_agent_code(String sical_agent_code) {
		this.sical_agent_code = sical_agent_code;
	}

	public String getSecurity_wall() {
		return security_wall;
	}

	public void setSecurity_wall(String security_wall) {
		this.security_wall = security_wall;
	}

	public String getIn_port_status() {
		return in_port_status;
	}

	public void setIn_port_status(String in_port_status) {
		this.in_port_status = in_port_status;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getRent_id() {
		return rent_id;
	}

	public void setRent_id(String rent_id) {
		this.rent_id = rent_id;
	}

	public String getGate_in_through() {
		return gate_in_through;
	}

	public void setGate_in_through(String gate_in_through) {
		this.gate_in_through = gate_in_through;
	}

	public String getGate_out_through() {
		return gate_out_through;
	}

	public void setGate_out_through(String gate_out_through) {
		this.gate_out_through = gate_out_through;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getTerminal_operator() {
		return terminal_operator;
	}

	public void setTerminal_operator(String terminal_operator) {
		this.terminal_operator = terminal_operator;
	}

	public String getShut_out() {
		return shut_out;
	}

	public void setShut_out(String shut_out) {
		this.shut_out = shut_out;
	}

	public Date getGateInDateTime() {
		return gateInDateTime;
	}

	public void setGateInDateTime(Date gateInDateTime) {
		this.gateInDateTime = gateInDateTime;
	}

	public Date getGateOutDateTime() {
		return gateOutDateTime;
	}

	public void setGateOutDateTime(Date gateOutDateTime) {
		this.gateOutDateTime = gateOutDateTime;
	}

	public Date getSical_in_date_time() {
		return sical_in_date_time;
	}

	public void setSical_in_date_time(Date sical_in_date_time) {
		this.sical_in_date_time = sical_in_date_time;
	}

	public Date getSical_out_date_time() {
		return sical_out_date_time;
	}

	public void setSical_out_date_time(Date sical_out_date_time) {
		this.sical_out_date_time = sical_out_date_time;
	}

	public Date getSical_landing_date_time() {
		return sical_landing_date_time;
	}

	public void setSical_landing_date_time(Date sical_landing_date_time) {
		this.sical_landing_date_time = sical_landing_date_time;
	}

	public Date getVessel_in_date_time() {
		return vessel_in_date_time;
	}

	public void setVessel_in_date_time(Date vessel_in_date_time) {
		this.vessel_in_date_time = vessel_in_date_time;
	}

	public Date getVessel_out_date_time() {
		return vessel_out_date_time;
	}

	public void setVessel_out_date_time(Date vessel_out_date_time) {
		this.vessel_out_date_time = vessel_out_date_time;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

}
