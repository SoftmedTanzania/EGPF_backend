package org.opensrp.repository;

import org.opensrp.domain.HealthFacilitiesPatients;
import org.opensrp.domain.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class HealthFacilitiesPatientsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public Long save(HealthFacilitiesPatients healthFacilitiesPatients) throws Exception {
		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(HealthFacilitiesPatients.tbName).usingGeneratedKeyColumns(HealthFacilitiesPatients.COL_HEALTH_FACILITY_PATIENT_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(HealthFacilitiesPatients.COL_PATIENT_ID , healthFacilitiesPatients.getPatient().getPatientId());
		parameters.put(HealthFacilitiesPatients.COL_FACILITY_ID  , healthFacilitiesPatients.getFacilityId());
		parameters.put(HealthFacilitiesPatients.COL_CTC_NUMBER , healthFacilitiesPatients.getCtcNumber());
		parameters.put(HealthFacilitiesPatients.COL_CREATED_AT , healthFacilitiesPatients.getCreatedAt());
		parameters.put(HealthFacilitiesPatients.COL_UPDATED_AT , healthFacilitiesPatients.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + HealthFacilitiesPatients.tbName;
		executeQuery(query);
	}



	public List<HealthFacilitiesPatients> getHealthFacilityPatients(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new HealthFacilityPatientsRowMapper());
	}



	public class HealthFacilityPatientsRowMapper implements RowMapper<HealthFacilitiesPatients> {
		public HealthFacilitiesPatients mapRow(ResultSet rs, int rowNum) throws SQLException {
			HealthFacilitiesPatients facilitiesPatients = new HealthFacilitiesPatients();

			facilitiesPatients.setHealthFacilityPatientId(rs.getLong(rs.findColumn(HealthFacilitiesPatients.COL_HEALTH_FACILITY_PATIENT_ID)));

			Patients patients = new Patients();
			patients.setPatientId(rs.getLong(rs.findColumn(HealthFacilitiesPatients.COL_PATIENT_ID)));

			facilitiesPatients.setPatient(patients);
			facilitiesPatients.setCtcNumber(rs.getString(rs.findColumn(HealthFacilitiesPatients.COL_CTC_NUMBER)));
			facilitiesPatients.setFacilityId(rs.getLong(rs.findColumn(HealthFacilitiesPatients.COL_FACILITY_ID)));
			facilitiesPatients.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(HealthFacilitiesPatients.COL_CREATED_AT)).getTime()));
			facilitiesPatients.setUpdatedAt(rs.getDate(rs.findColumn(HealthFacilitiesPatients.COL_UPDATED_AT)));
			return facilitiesPatients;
		}
		
	}

}
