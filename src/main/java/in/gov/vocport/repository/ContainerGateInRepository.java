package in.gov.vocport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.gov.vocport.entities.ContainerGateInEntity;

@Repository
public interface ContainerGateInRepository extends JpaRepository<ContainerGateInEntity, String> {
	
	List<ContainerGateInEntity> findTop1ByCreatedByOrderByCreatedOnDesc(String createdBy);
	
	List<ContainerGateInEntity> findByCreatedByAndGateOutDateTimeIsNotNullOrderByGateOutDateTimeDesc(String modifiedBy);;
	
}
