package in.gov.vocport.repository;

import in.gov.vocport.entities.CtThDirectServiceChg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectPortServiceChargeHeaderRepository extends JpaRepository<CtThDirectServiceChg, String> {
    CtThDirectServiceChg findByChitNoAndContainerNo(String chitNo, String containerNo);
}
