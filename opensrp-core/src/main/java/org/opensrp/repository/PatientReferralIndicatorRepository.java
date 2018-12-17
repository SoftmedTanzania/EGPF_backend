package org.opensrp.repository;

import org.opensrp.domain.PatientReferralIndicators;
import org.opensrp.domain.PatientReferralIndicators;
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
public class PatientReferralIndicatorRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public Long save(PatientReferralIndicators patientReferralIndicator) throws Exception {

		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(PatientReferralIndicators.tbName).usingGeneratedKeyColumns(PatientReferralIndicators.COL_PATIENT_REFERRAL_INDICATOR_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(PatientReferralIndicators.COL_REFERRAL_ID , patientReferralIndicator.getReferralId());
		parameters.put(PatientReferralIndicators.COL_REFERRAL_SERVICE_INDICATOR_ID , patientReferralIndicator.getReferralServiceIndicatorId());
		parameters.put(PatientReferralIndicators.COL_IS_ACTIVE  , patientReferralIndicator.isActive());
		parameters.put(PatientReferralIndicators.COL_CREATED_AT , patientReferralIndicator.getCreatedAt());
		parameters.put(PatientReferralIndicators.COL_UPDATED_AT , patientReferralIndicator.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + PatientReferralIndicators.tbName;
		executeQuery(query);
	}



	public List<PatientReferralIndicators> getPatientReferralIndicators(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new ServiceRowMapper());
	}



	public class ServiceRowMapper implements RowMapper<PatientReferralIndicators> {
		public PatientReferralIndicators mapRow(ResultSet rs, int rowNum) throws SQLException {
			PatientReferralIndicators patientReferralIndicator = new PatientReferralIndicators();

			patientReferralIndicator.setPatientReferralIndicatorId(rs.getLong(rs.findColumn(PatientReferralIndicators.COL_PATIENT_REFERRAL_INDICATOR_ID)));
			patientReferralIndicator.setReferralId(rs.getLong(rs.findColumn(PatientReferralIndicators.COL_REFERRAL_ID)));
			patientReferralIndicator.setReferralServiceIndicatorId(rs.getLong(rs.findColumn(PatientReferralIndicators.COL_REFERRAL_SERVICE_INDICATOR_ID)));
			patientReferralIndicator.setActive(rs.getBoolean(rs.findColumn(PatientReferralIndicators.COL_IS_ACTIVE)));
			patientReferralIndicator.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(PatientReferralIndicators.COL_CREATED_AT)).getTime()));
			patientReferralIndicator.setUpdatedAt(rs.getDate(rs.findColumn(PatientReferralIndicators.COL_UPDATED_AT)));
			return patientReferralIndicator;
		}
		
	}

}
