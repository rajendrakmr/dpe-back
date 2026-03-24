package in.gov.vocport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AgentProjection {
    private String partyCd;
    private String agentNm;
}
