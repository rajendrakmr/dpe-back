package in.gov.vocport.repository;

import in.gov.vocport.entities.CtTdDirectServiceChg;
import in.gov.vocport.entities.CtTdDirectServiceChgId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectPortServiceChargeDetailRepository extends JpaRepository<CtTdDirectServiceChg, CtTdDirectServiceChgId> {
    Optional<CtTdDirectServiceChg> findById_ChitNo(String chitNo);
}
