package in.gov.vocport.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.gov.vocport.dto.AgentDto;
import in.gov.vocport.dto.CargoDto;
import in.gov.vocport.dto.ContainerGateInDto;
import in.gov.vocport.dto.ContainerNoDto;
import in.gov.vocport.dto.GateOutSearchCriteria;
import in.gov.vocport.dto.LinerDto;
import in.gov.vocport.dto.LocationDto;
import in.gov.vocport.dto.PagedResponse;
import in.gov.vocport.dto.PortDto;
import in.gov.vocport.dto.ShipperDto;
import in.gov.vocport.dto.VesselDto;
import in.gov.vocport.dto.gateOutRowDto;
import in.gov.vocport.entities.ContainerGateInEntity;
import in.gov.vocport.repository.CommonSearchOptionRepository;
import in.gov.vocport.repository.ContainerGateInRepository;
import in.gov.vocport.repository.ContainerGateInSearchRepository;

@Service
public class ContainerGateOutService {
	
	private final CommonSearchOptionRepository commonSearchOptionRepository;

	@Autowired
	private ContainerGateInRepository repository;

	@Autowired
	private ContainerGateInSearchRepository searchRepo;

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public ContainerGateOutService(CommonSearchOptionRepository commonSearchOptionRepository) {
		this.commonSearchOptionRepository = commonSearchOptionRepository;
	}

	public String getNewChitNo() {
		return commonSearchOptionRepository.generateChitNoGateOut();
	}

	public String getCurrentInTime() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(DATE_TIME_FORMATTER);
	}

	public PagedResponse<PortDto> searchPortOfDestinations(String term, int page, int size) {
		return commonSearchOptionRepository.searchPorts(term, page, size);
	}

	public PagedResponse<ShipperDto> searchShippers(String term, int page, int size) {
		return commonSearchOptionRepository.searchShippers(term, page, size);
	}

	public PagedResponse<VesselDto> searchVessels(String term, int page, int size) {
		return commonSearchOptionRepository.searchVessels(term, page, size);
	}

	public PagedResponse<LocationDto> searchGateOutFromlocations(String term,int page, int size) {
		return commonSearchOptionRepository.searchGateOutFromlocations(term, page, size);
	}
	
	public PagedResponse<LocationDto> searchGateOutTolocations(String term,int page, int size) {
		return commonSearchOptionRepository.searchGateOutTolocations(term, page, size);
	}

	public PagedResponse<AgentDto> searchAgents(String term, int page, int size) {
		return commonSearchOptionRepository.searchAgents(term, page, size);
	}

	public PagedResponse<CargoDto> searchCargo(String term, int page, int size) {
		return commonSearchOptionRepository.searchCargo(term, page, size);
	}

	public PagedResponse<LinerDto> searchLiners(String term, int page, int size) {
		return commonSearchOptionRepository.searchLiners(term, page, size);
	}
	
	public PagedResponse<ContainerNoDto> gateOutContainerNo(String term,int page, int size) {
		return commonSearchOptionRepository.searchGateOutContainerNo(term, page, size);
	}

	public ContainerGateInEntity saveOrUpdate(ContainerGateInDto dto, String userCode) {
		Date now = new Date();
		ContainerGateInEntity entity = repository.findById(dto.getChitNo()).orElse(new ContainerGateInEntity());
		boolean isNew = (entity.getChit_no() == null);
		entity.setChit_no(dto.getChitNo());
		entity.setVehicle_no(dto.getVehicleNo());
		entity.setFrom_loc_id(dto.getFromLocId());
		entity.setTo_loc_id(dto.getToLocId());
		entity.setImp_exp_trns(dto.getImpExpTrns());
		entity.setBoe_no(dto.getBeSbNo());
		entity.setParty_cd(dto.getChAgentCode());
		entity.setVessel_no(dto.getVesselNo());
		entity.setSical_vessel_name(dto.getVesselName());
		entity.setSical_voyage_no(dto.getVoyageNo());
		entity.setShipper(dto.getShipperName());
		entity.setLocal_origin(dto.getLocalOrigin());
		// choose one: origin_port or trn_shp_port – I'll use trn_shp_port
		entity.setTrn_shp_port(dto.getPortOfDestination());
		entity.setWeighment_flag(dto.getWeightmentFlag());
		entity.setSecurity_wall(dto.getSecurityWall());
		entity.setGate_out_through(dto.getGateInThrough());
		entity.setContainer_no(dto.getContainerNo());
		entity.setReffer_cont_flg("N");// Static value may need to be changed
		entity.setIn_port_status("N");
		// Static value may need to be changed
		// containerStatus = "20,Load" or "40,Empty" etc.
		String containerStatus = dto.getContainerStatus();
		if (containerStatus != null && containerStatus.contains(",")) {
			String[] parts = containerStatus.split(",");
			entity.setContainer_size(parts[0]); // 20 / 40 / Above 40
			entity.setLoading_status(parts[1]); // Load / Empty
		} else {
			entity.setContainer_size(null);
			entity.setLoading_status(null);
		}
		entity.setCargo_code(dto.getCargoName());
		entity.setBags(dto.getPackages());
		entity.setQuantity(dto.getQuantity());
		entity.setSical_line_code(dto.getLinerCode());
		entity.setSical_line_name(dto.getLinerName());
		entity.setEir(dto.getEir());
		entity.setIcd_cfs_fsc_none(dto.getIcdCfsFcs());
		entity.setHazardous(dto.getHazardous());
		entity.setCustoms_examinations(dto.getCustomsExamination());
		entity.setShut_out(dto.getShutOut());
		entity.setForeign_coastal_flag(dto.getForeignCoastalFlag());
		if (isNew) {
			entity.setCreatedBy(userCode);
			entity.setCreatedOn(now);
			entity.setGateOutDateTime(now);
			entity.setDel_flag("N");
		} else {
			entity.setModifiedBy(userCode);
			entity.setModified_on(now);
		}
		return repository.save(entity);
	}

	public PagedResponse<gateOutRowDto> searchGateOut(GateOutSearchCriteria criteria, int page, int size) {
		return searchRepo.searchGateOut(criteria, page, size);
	}

}
