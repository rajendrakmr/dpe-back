package in.gov.vocport.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TC_DPE_MENU_USER_ACCESS")
@IdClass(MenuUserAccessId.class)
public class MenuUserAccess {
    @Id
    private String moduleId;
    @Id
    private String menuId;
    @Id
    private String userId;
    private String includeExclude;
}