package in.gov.vocport.dto;

public class PortDto {
    private String code;
    private String name;

    public PortDto(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
}

