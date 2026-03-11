package in.gov.vocport.repository;

import in.gov.vocport.dto.GateInRowDto;
import in.gov.vocport.dto.GateInSearchCriteria;
import in.gov.vocport.dto.GateOutSearchCriteria;
import in.gov.vocport.dto.PagedResponse;
import in.gov.vocport.dto.gateOutRowDto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContainerGateInSearchRepository {

	private final JdbcTemplate jdbcTemplate;

	public ContainerGateInSearchRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PagedResponse<GateInRowDto> search(GateInSearchCriteria c, int page, int size) {

		StringBuilder base = new StringBuilder("""
				FROM CT_T_CONTAINER_IN_PORT_AREA t
				WHERE (t.DEL_FLAG IS NULL OR t.DEL_FLAG <> 'Y' ) AND GATE_IN_DATE_TIME IS NOT NULL
				""");

		List<Object> params = new ArrayList<>();

		if (hasText(c.getChitNo())) {
			base.append(" AND t.CHIT_NO LIKE ?");
			params.add("%" + c.getChitNo().trim() + "%");
		}
		if (hasText(c.getContainerNo())) {
			base.append(" AND t.CONTAINER_NO LIKE ?");
			params.add("%" + c.getContainerNo().trim() + "%");
		}
		if (hasText(c.getVehicleNo())) {
			base.append(" AND t.VEHICLE_NO LIKE ?");
			params.add("%" + c.getVehicleNo().trim() + "%");
		}

		// *** NEW: single date filter instead of FROM/TO ***
		// *** Single-date filter using TRUNC and java.sql.Date ***
		// *** Single date filter using BETWEEN and java.sql.Timestamp ***
		if (hasText(c.getGateInDate())) {

		    // Normalize input: support 27-11-2025 or 27/11/2025
		    String raw = c.getGateInDate().trim()
		            .replace("/", "-")
		            .split(" ")[0]; // in case user somehow sends "27-11-2025 10:00"

		    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		    LocalDate d = LocalDate.parse(raw, fmt);

		    LocalDateTime startOfDay = d.atStartOfDay();               // 2025-11-27 00:00:00
		    LocalDateTime nextDayStart = d.plusDays(1).atStartOfDay(); // 2025-11-28 00:00:00

		    base.append(" AND t.GATE_IN_DATE_TIME >= ? ");
		    base.append(" AND t.GATE_IN_DATE_TIME <  ? ");

		    params.add(java.sql.Timestamp.valueOf(startOfDay));
		    params.add(java.sql.Timestamp.valueOf(nextDayStart));
		}


		if (hasText(c.getAgent())) {
			base.append(" AND t.PARTY_CD LIKE ?");
			params.add("%" + c.getAgent().trim() + "%");
		}
		if (hasText(c.getEir())) {
			base.append(" AND t.EIR LIKE ?");
			params.add("%" + c.getEir().trim() + "%");
		}
		if (hasText(c.getVesselNo())) {
			base.append(" AND t.VESSEL_NO LIKE ?");
			params.add("%" + c.getVesselNo().trim() + "%");
		}
		if (hasText(c.getLoadingStatus())) {
			base.append(" AND UPPER(t.LOADING_STATUS) = UPPER(?)");
			params.add(c.getLoadingStatus().trim());
		}
		if (hasText(c.getContainerSize())) {
			base.append(" AND t.CONTAINER_SIZE = ?");
			params.add(c.getContainerSize().trim());
		}
		if (hasText(c.getVoyage())) {
			base.append(" AND t.FOREIGN_COASTAL_FLAG LIKE ?");
			params.add("%" + c.getVoyage().trim() + "%");
		}

		// ---- count ----
		String countSql = "SELECT COUNT(*) " + base;
		@SuppressWarnings("deprecation")
		long total = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);

		int offset = page * size;

//		// ---- data page ----
//		String dataSql = """
//				SELECT t.CHIT_NO,
//				       t.CONTAINER_NO,
//				       t.VEHICLE_NO,
//				       t.GATE_IN_DATE_TIME,
//				       t.PARTY_CD,
//				       t.EIR,
//				       t.VESSEL_NO,
//				       t.LOADING_STATUS,
//				       t.CONTAINER_SIZE,
//				       t.FOREIGN_COASTAL_FLAG
//				""" + base + """
//				ORDER BY t.GATE_IN_DATE_TIME DESC, t.CHIT_NO
//				OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
//				""";
//
//		System.out.println(dataSql);
//
//		params.add(offset);
//		params.add(size);

		String dataSql = """
        SELECT CHIT_NO,
               CONTAINER_NO,
               VEHICLE_NO,
               GATE_IN_DATE_TIME,
               PARTY_CD,
               EIR,
               VESSEL_NO,
               LOADING_STATUS,
               CONTAINER_SIZE,
               FOREIGN_COASTAL_FLAG
        FROM (
            SELECT t.CHIT_NO,
                   t.CONTAINER_NO,
                   t.VEHICLE_NO,
                   t.GATE_IN_DATE_TIME,
                   t.PARTY_CD,
                   t.EIR,
                   t.VESSEL_NO,
                   t.LOADING_STATUS,
                   t.CONTAINER_SIZE,
                   t.FOREIGN_COASTAL_FLAG,
                   ROWNUM rnum
            FROM (
                SELECT t.CHIT_NO,
                       t.CONTAINER_NO,
                       t.VEHICLE_NO,
                       t.GATE_IN_DATE_TIME,
                       t.PARTY_CD,
                       t.EIR,
                       t.VESSEL_NO,
                       t.LOADING_STATUS,
                       t.CONTAINER_SIZE,
                       t.FOREIGN_COASTAL_FLAG
                """ + base + """
                ORDER BY t.GATE_IN_DATE_TIME DESC, t.CHIT_NO
            ) t
            WHERE ROWNUM <= ?
        )
        WHERE rnum > ?
        """;

		System.out.println(dataSql);

		params.add(offset + size); // upper limit
		params.add(offset);        // lower limit

		@SuppressWarnings("deprecation")
		List<GateInRowDto> content = jdbcTemplate.query(dataSql, params.toArray(), (rs, rowNum) -> {
			GateInRowDto dto = new GateInRowDto();
			dto.setChit_no(rs.getString("CHIT_NO"));
			dto.setContainer_no(rs.getString("CONTAINER_NO"));
			dto.setVehicle_no(rs.getString("VEHICLE_NO"));
			dto.setGateInDateTime(rs.getTimestamp("GATE_IN_DATE_TIME"));
			dto.setParty_cd(rs.getString("PARTY_CD"));
			dto.setEir(rs.getString("EIR"));
			dto.setVessel_no(rs.getString("VESSEL_NO"));
			dto.setLoading_status(rs.getString("LOADING_STATUS"));
			dto.setContainer_size(rs.getString("CONTAINER_SIZE"));
			dto.setForeign_coastal_flag(rs.getString("FOREIGN_COASTAL_FLAG"));
			return dto;
		});

		int totalPages = (int) Math.ceil((double) total / size);
		return new PagedResponse<>(content, page, totalPages, total);
	}
	
	public PagedResponse<gateOutRowDto> searchGateOut(GateOutSearchCriteria c, int page, int size) {

		StringBuilder base = new StringBuilder("""
				FROM CT_T_CONTAINER_IN_PORT_AREA t
				WHERE (t.DEL_FLAG IS NULL OR t.DEL_FLAG <> 'Y')
				""");

		List<Object> params = new ArrayList<>();

		if (hasText(c.getChitNo())) {
			base.append(" AND t.CHIT_NO LIKE ?");
			params.add("%" + c.getChitNo().trim() + "%");
		}
		if (hasText(c.getContainerNo())) {
			base.append(" AND t.CONTAINER_NO LIKE ?");
			params.add("%" + c.getContainerNo().trim() + "%");
		}
		if (hasText(c.getVehicleNo())) {
			base.append(" AND t.VEHICLE_NO LIKE ?");
			params.add("%" + c.getVehicleNo().trim() + "%");
		}

		// *** NEW: single date filter instead of FROM/TO ***
		// *** Single-date filter using TRUNC and java.sql.Date ***
		// *** Single date filter using BETWEEN and java.sql.Timestamp ***
		if (hasText(c.getGateOutDate())) {

		    // Normalize input: support 27-11-2025 or 27/11/2025
		    String raw = c.getGateOutDate().trim()
		            .replace("/", "-")
		            .split(" ")[0]; // in case user somehow sends "27-11-2025 10:00"

		    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		    LocalDate d = LocalDate.parse(raw, fmt);

		    LocalDateTime startOfDay = d.atStartOfDay();               // 2025-11-27 00:00:00
		    LocalDateTime nextDayStart = d.plusDays(1).atStartOfDay(); // 2025-11-28 00:00:00

		    base.append(" AND t.GATE_OUT_DATE_TIME >= ? ");
		    base.append(" AND t.GATE_OUT_DATE_TIME <  ? ");

		    params.add(java.sql.Timestamp.valueOf(startOfDay));
		    params.add(java.sql.Timestamp.valueOf(nextDayStart));
		}


		if (hasText(c.getAgent())) {
			base.append(" AND t.PARTY_CD LIKE ?");
			params.add("%" + c.getAgent().trim() + "%");
		}
		if (hasText(c.getEir())) {
			base.append(" AND t.EIR LIKE ?");
			params.add("%" + c.getEir().trim() + "%");
		}
		if (hasText(c.getVesselNo())) {
			base.append(" AND t.VESSEL_NO LIKE ?");
			params.add("%" + c.getVesselNo().trim() + "%");
		}
		if (hasText(c.getLoadingStatus())) {
			base.append(" AND UPPER(t.LOADING_STATUS) = UPPER(?)");
			params.add(c.getLoadingStatus().trim());
		}
		if (hasText(c.getContainerSize())) {
			base.append(" AND t.CONTAINER_SIZE = ?");
			params.add(c.getContainerSize().trim());
		}
		if (hasText(c.getVoyage())) {
			base.append(" AND t.FOREIGN_COASTAL_FLAG LIKE ?");
			params.add("%" + c.getVoyage().trim() + "%");
		}

		// ---- count ----
		String countSql = "SELECT COUNT(*) " + base;
		@SuppressWarnings("deprecation")
		long total = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);

		int offset = page * size;

//		// ---- data page ----
//		String dataSql = """
//				SELECT t.CHIT_NO,
//				       t.CONTAINER_NO,
//				       t.VEHICLE_NO,
//				       t.GATE_OUT_DATE_TIME,
//				       t.PARTY_CD,
//				       t.EIR,
//				       t.VESSEL_NO,
//				       t.LOADING_STATUS,
//				       t.CONTAINER_SIZE,
//				       t.FOREIGN_COASTAL_FLAG
//				""" + base + """
//				ORDER BY t.GATE_OUT_DATE_TIME DESC, t.CHIT_NO
//				OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
//				""";
//
//		params.add(offset);
//		params.add(size);

		// ---- data page ----
		String dataSql = """
        SELECT CHIT_NO,
               CONTAINER_NO,
               VEHICLE_NO,
               GATE_OUT_DATE_TIME,
               PARTY_CD,
               EIR,
               VESSEL_NO,
               LOADING_STATUS,
               CONTAINER_SIZE,
               FOREIGN_COASTAL_FLAG
        FROM (
            SELECT t.CHIT_NO,
                   t.CONTAINER_NO,
                   t.VEHICLE_NO,
                   t.GATE_OUT_DATE_TIME,
                   t.PARTY_CD,
                   t.EIR,
                   t.VESSEL_NO,
                   t.LOADING_STATUS,
                   t.CONTAINER_SIZE,
                   t.FOREIGN_COASTAL_FLAG,
                   ROWNUM rnum
            FROM (
                SELECT t.CHIT_NO,
                       t.CONTAINER_NO,
                       t.VEHICLE_NO,
                       t.GATE_OUT_DATE_TIME,
                       t.PARTY_CD,
                       t.EIR,
                       t.VESSEL_NO,
                       t.LOADING_STATUS,
                       t.CONTAINER_SIZE,
                       t.FOREIGN_COASTAL_FLAG
                """ + base + """
                ORDER BY t.GATE_OUT_DATE_TIME DESC, t.CHIT_NO
            ) t
            WHERE ROWNUM <= ?
        )
        WHERE rnum > ?
        """;

		params.add(offset + size); // upper limit
		params.add(offset);        // lower limit

		@SuppressWarnings("deprecation")
		List<gateOutRowDto> content = jdbcTemplate.query(dataSql, params.toArray(), (rs, rowNum) -> {
			gateOutRowDto dto = new gateOutRowDto();
			dto.setChit_no(rs.getString("CHIT_NO"));
			dto.setContainer_no(rs.getString("CONTAINER_NO"));
			dto.setVehicle_no(rs.getString("VEHICLE_NO"));
			dto.setGateOutDateTime(rs.getTimestamp("GATE_OUT_DATE_TIME"));
			dto.setParty_cd(rs.getString("PARTY_CD"));
			dto.setEir(rs.getString("EIR"));
			dto.setVessel_no(rs.getString("VESSEL_NO"));
			dto.setLoading_status(rs.getString("LOADING_STATUS"));
			dto.setContainer_size(rs.getString("CONTAINER_SIZE"));
			dto.setForeign_coastal_flag((rs.getString("FOREIGN_COASTAL_FLAG")));
			return dto;
		});

		int totalPages = (int) Math.ceil((double) total / size);
		return new PagedResponse<>(content, page, totalPages, total);
	}


	private boolean hasText(String s) {
		return s != null && !s.isBlank();
	}
}