package in.gov.vocport.dto;

public class UserAccessEditMenuDto {
	
	private String moduleId;
	private String moduleName;
	private String menuId;
	private String menuName;
	private String chkField;

	public UserAccessEditMenuDto() {
	}

	public UserAccessEditMenuDto(String moduleId, String moduleName, String menuId, String menuName, String chkField) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.menuId = menuId;
		this.menuName = menuName;
		this.chkField = chkField;
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

	public String getChkField() {
		return chkField;
	}

	public void setChkField(String chkField) {
		this.chkField = chkField;
	}
	
}
