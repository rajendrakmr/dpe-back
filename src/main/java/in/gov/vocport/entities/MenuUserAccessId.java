package in.gov.vocport.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuUserAccessId implements Serializable {
    private String moduleId;
    private String menuId;
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuUserAccessId that = (MenuUserAccessId) o;
        return Objects.equals(moduleId, that.moduleId) && Objects.equals(menuId, that.menuId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, menuId, userId);
    }
}
