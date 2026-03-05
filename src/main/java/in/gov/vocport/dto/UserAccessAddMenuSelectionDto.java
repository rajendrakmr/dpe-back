package in.gov.vocport.dto;

public class UserAccessAddMenuSelectionDto {

	private String moduleId;
	private String menuId;

	public UserAccessAddMenuSelectionDto() {
	}

	public UserAccessAddMenuSelectionDto(String moduleId, String menuId) {
		this.moduleId = moduleId;
		this.menuId = menuId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}