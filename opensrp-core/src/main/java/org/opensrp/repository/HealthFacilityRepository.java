package org.opensrp.repository;

import org.opensrp.domain.HealthFacilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;


@Repository
public class HealthFacilityRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;

	
	public int save(HealthFacilities healthFacilities) throws Exception {
		String insertQuery = "insert into " + HealthFacilities.tbName + " (" +
				HealthFacilities.COL_OPENMRS_UIID + "," +
				HealthFacilities.COL_FACILITY_NAME + "," +
				HealthFacilities.COL_FACILITY_CTC_CODE + "," +
				HealthFacilities.COL_HFR_CODE + "," +
				HealthFacilities.COL_PARENT_OPENMRS_UIID + "," +
				HealthFacilities.COL_PARENT_HFR_CODE + "," +
				HealthFacilities.COL_UPDATED_AT + "," +
				HealthFacilities.COL_CREATED_AT + ") values (?,?,?,?,?,?,?,?) ";

		Object[] params = new Object[] {
				healthFacilities.getOpenMRSUIID(),
				healthFacilities.getFacilityName(),
				healthFacilities.getFacilityCtcCode(),
		        healthFacilities.getHfrCode(),
		        healthFacilities.getParentOpenmrsUIID(),
				healthFacilities.getParentHFRCode(),
		        healthFacilities.getUpdatedAt(),
				healthFacilities.getCreatedAt() };
		int[] types = new int[] {
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.DATE,
				Types.TIMESTAMP };
		
		return jdbcTemplate.update(insertQuery, params, types);
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + HealthFacilities.tbName;
		executeQuery(query);
	}


	public List<HealthFacilities> getHealthFacility(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new HealthFacilitiesRowMapper());
	}

	
	public class HealthFacilitiesRowMapper implements RowMapper<HealthFacilities> {
		public HealthFacilities mapRow(ResultSet rs, int rowNum) throws SQLException {
			HealthFacilities healthFacilitie = new HealthFacilities();
			healthFacilitie.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(HealthFacilities.COL_CREATED_AT)).getTime()));
			healthFacilitie.setOpenMRSUIID(rs.getString(rs.findColumn(HealthFacilities.COL_OPENMRS_UIID)));
			healthFacilitie.setFacilityName(rs.getString(rs.findColumn(HealthFacilities.COL_FACILITY_NAME)));
			healthFacilitie.setFacilityCtcCode(rs.getString(rs.findColumn(HealthFacilities.COL_FACILITY_CTC_CODE)));
			healthFacilitie.setHfrCode(rs.getString(rs.findColumn(HealthFacilities.COL_HFR_CODE)));
			healthFacilitie.setParentHFRCode(rs.getString(rs.findColumn(HealthFacilities.COL_PARENT_HFR_CODE)));
			healthFacilitie.setParentOpenmrsUIID(rs.getString(rs.findColumn(HealthFacilities.COL_PARENT_OPENMRS_UIID)));
			healthFacilitie.setUpdatedAt(rs.getDate(rs.findColumn(HealthFacilities.COL_UPDATED_AT)));
			healthFacilitie.setId(rs.getLong(rs.findColumn("_id")));
			return healthFacilitie;
		}
		
	}

}
