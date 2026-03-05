package in.gov.vocport.service;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;
import in.gov.vocport.dto.UserAccessAddMenuDto;
import in.gov.vocport.dto.UserAccessAddMenuSelectionDto;
import jakarta.transaction.Transactional;

import java.sql.*;

@Service
public class UserAccessAddMenuService {

	private final DataSource dataSource;

	public UserAccessAddMenuService(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<UserAccessAddMenuDto> getMenusForUser(String userId) throws SQLException {
		List<UserAccessAddMenuDto> menus = new ArrayList<>();

		String call = "{ call CT_DPE_PKG.GET_MENU_FOR_ADD_USER_PR(?, ?) }";

		try (Connection conn = dataSource.getConnection(); CallableStatement cs = conn.prepareCall(call)) {

			// set IN parameter
			if (userId == null || userId.isBlank()) {
				cs.setNull(1, Types.VARCHAR);
			} else {
				cs.setString(1, userId);
			}

			// register OUT cursor
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);

			cs.execute();

			try (ResultSet rs = (ResultSet) cs.getObject(2)) {
				while (rs.next()) {
					String moduleId = rs.getString("MODULE_ID");
					if (rs.wasNull())
						moduleId = null;
					String moduleName = rs.getString("MODULE_NAME");
					String menuId = rs.getString("MENU_ID");
					if (rs.wasNull())
						menuId = null;
					String menuName = rs.getString("MENU_NAME");
					menus.add(new UserAccessAddMenuDto(moduleId, moduleName, menuId, menuName));
				}
			}
		}

		return menus;
	}
	
	@Transactional
	public int saveUserAccess(String userId, List<UserAccessAddMenuSelectionDto> selections) throws SQLException {
	    if (selections == null || selections.isEmpty()) return 0;

	    final String insertSql = "INSERT INTO TC_DPE_MENU_USER_ACCESS (MODULE_ID, MENU_ID, USER_ID, INCLUDE_EXCLUDE) VALUES (?, ?, ?, ?)";

	    int[] counts;
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement ps = conn.prepareStatement(insertSql)) {
	        // Optional: set autoCommit false if you prefer explicit transaction:
	        boolean oldAuto = conn.getAutoCommit();
	        conn.setAutoCommit(false);
	        try {
	            for (UserAccessAddMenuSelectionDto s : selections) {
	                // set module id, menu id, user id, status 'I'
	                if (s.getModuleId() != null) {
	                    ps.setString(1, s.getModuleId());
	                } else {
	                    ps.setNull(1, java.sql.Types.BIGINT);
	                }
	                if (s.getMenuId() != null) {
	                    ps.setString(2, s.getMenuId());
	                } else {
	                    ps.setNull(2, java.sql.Types.BIGINT);
	                }
	                ps.setString(3, userId);
	                ps.setString(4, "I");
	                ps.addBatch();
	            }
	            counts = ps.executeBatch();
	            conn.commit();
	        } catch (SQLException ex) {
	            conn.rollback();
	            throw ex;
	        } finally {
	            conn.setAutoCommit(oldAuto);
	        }
	    }

	    // sum counts (some drivers return SUCCESS_NO_INFO (-2))
	    int inserted = 0;
	    for (int c : counts) {
	        if (c >= 0) inserted += c;
	        else inserted++; // best-effort count
	    }
	    return inserted;
	}

}