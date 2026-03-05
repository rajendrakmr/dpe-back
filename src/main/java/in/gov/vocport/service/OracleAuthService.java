package in.gov.vocport.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OracleAuthService {

    private final JdbcTemplate jdbcTemplate;

    public OracleAuthService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String authenticate(String username, String password) {
        String sql = "SELECT CT_DPE_PKG.FN_GET_USER_ACCESS(?, ?) FROM DUAL";
        return jdbcTemplate.queryForObject(sql, String.class, username, password);
    }
}

