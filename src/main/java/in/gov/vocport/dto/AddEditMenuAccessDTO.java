package in.gov.vocport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddEditMenuAccessDTO {
    private String menuId;
    private String parentMenuId;
    private String menuIdTree;
    private String menuNameTree;
    private String menuLinkName;
    private Integer level;
    private String rootId;
    private String path;
    private String moduleName;
    private String moduleId;
    private Integer leaf;
    private Integer checked = 0; //0->false, 1-> true
}
