package in.gov.vocport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.gov.vocport.dto.ContainerInPortDto;
import in.gov.vocport.dto.ContainerNoDto;
import in.gov.vocport.dto.PagedResponse;
import in.gov.vocport.repository.CommonSearchOptionRepository;

@Service
public class AddEditServiceCharge {
	
	@Autowired
	private CommonSearchOptionRepository commonSearchOptionRepository;
	
	public PagedResponse<ContainerNoDto> searchContainerNoForServiceAdd(String term, int page, int size) {
		return commonSearchOptionRepository.searchContainerNoForServiceAdd(term, page, size);
	}
	
	public ContainerInPortDto getContainerInPortDetails(String containerNo) {
	    return commonSearchOptionRepository.findContainerInPortDetails(containerNo);
	}

}
