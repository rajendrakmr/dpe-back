package in.gov.vocport.repository;

import in.gov.vocport.entities.MenuUserAccess;
import in.gov.vocport.entities.MenuUserAccessId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuUserAccessRepository extends JpaRepository<MenuUserAccess, MenuUserAccessId> {
}
