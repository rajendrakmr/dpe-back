package in.gov.vocport.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import in.gov.vocport.entities.MenuUserAccess;
import in.gov.vocport.repository.MenuUserAccessRepository;
import org.springframework.stereotype.Service;
import in.gov.vocport.dto.UserAccessAddMenuSelectionDto;
import in.gov.vocport.dto.UserAccessEditMenuDto;
import jakarta.transaction.Transactional;

@Service
public class UserAccessEditMenuService {

	private final DataSource dataSource;
	private final MenuUserAccessRepository repository;

	public UserAccessEditMenuService(DataSource dataSource, MenuUserAccessRepository repository) {
		this.dataSource = dataSource;
        this.repository = repository;
    }

	public List<UserAccessEditMenuDto> getMenusForUser(String userId) throws SQLException {
		List<UserAccessEditMenuDto> menus = new ArrayList<>();

		String call = "{ call CT_DPE_PKG.GET_MENU_FOR_EDIT_USER_PR(?, ?) }";

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
					String chkField = rs.getString("CHK_FIELD");
					if (rs.wasNull())
						chkField = null;
					menus.add(new UserAccessEditMenuDto(moduleId, moduleName, menuId, menuName,chkField));
				}
			}
		}

		return menus;
	}

	@Transactional
	public int editUserAccess(String userId, List<UserAccessAddMenuSelectionDto> selections) throws SQLException {

		final String deleteProc = "{ call CT_DPE_PKG.DELETE_MENU_FOR_EDIT_USER_PR(?) }";
		final String insertSql = "INSERT INTO TC_DPE_MENU_USER_ACCESS (MODULE_ID, MENU_ID, USER_ID, INCLUDE_EXCLUDE) "
				+ "VALUES (?, ?, ?, 'I')";
		if (userId == null || userId.isBlank()) {
			throw new IllegalArgumentException("User ID cannot be null or empty.");
		}
		int inserted = 0;
		try (Connection conn = dataSource.getConnection()) {
			boolean oldAutoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false); // Begin transaction

			try {
				try (CallableStatement cs = conn.prepareCall(deleteProc)) {
					cs.setString(1, userId.trim());
					cs.execute();
					System.out.println("Existing menu access removed for user: " + userId);
				}
				if (selections != null && !selections.isEmpty()) {
//					try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
//						for (UserAccessAddMenuSelectionDto s : selections) {
//							if (s.getModuleId() != null)
//								ps.setString(1, s.getModuleId());
//							else
//								ps.setNull(1, Types.VARCHAR);
//							if (s.getMenuId() != null)
//								ps.setString(2, s.getMenuId());
//							else
//								ps.setNull(2, Types.VARCHAR);
//							ps.setString(3, userId);
//							ps.addBatch();
//						}
//						int[] batchResults = ps.executeBatch();
//						for (int result : batchResults) {
//							inserted += (result >= 0) ? result : 1;
//						}
//					}

					for (UserAccessAddMenuSelectionDto s : selections) {
						MenuUserAccess userAccess = repository.save(new MenuUserAccess(s.getModuleId(), s.getMenuId(), userId, "I"));
					}
				}
				conn.commit();
			} catch (SQLException ex) {
				conn.rollback();
				System.err.println("Failed to save user access: " + ex.getMessage());
				throw ex;
			} finally {
				conn.setAutoCommit(oldAutoCommit);
			}
		}
		return inserted;
	}
}