package in.gov.vocport.dto;

public class UserAccessAddMenuDto {
	private String moduleId;
	private String moduleName;
	private String menuId;
	private String menuName;

	public UserAccessAddMenuDto() {
	}

	public UserAccessAddMenuDto(String moduleId, String moduleName, String menuId, String menuName) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.menuId = menuId;
		this.menuName = menuName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}