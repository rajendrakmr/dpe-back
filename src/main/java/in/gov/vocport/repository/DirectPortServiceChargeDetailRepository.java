package in.gov.vocport.repository;

import in.gov.vocport.entities.CtTdDirectServiceChg;
import in.gov.vocport.entities.CtTdDirectServiceChgId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DirectPortServiceChargeDetailRepository extends JpaRepository<CtTdDirectServiceChg, CtTdDirectServiceChgId> {
    Optional<CtTdDirectServiceChg> findById_ChitNo(String chitNo);

    @Query(value = "SELECT CT_DPE_PKG.FN_PAY_STATUS_CHECK(:p_cfs_no) FROM dual", nativeQuery = true)
    String findGetOutPaymentStatus(@Param("p_cfs_no") String cfsNo);

    @Query(value = "SELECT CT_DPE_PKG.FN_GET_DELIVERY_DATE(:p_admission_chit_no) FROM dual", nativeQuery = true)
    String findDeliveryDate(@Param("p_admission_chit_no") String admissionChitNo);
}
