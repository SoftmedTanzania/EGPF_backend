package org.opensrp.repository;

import org.opensrp.domain.HealthFacilitiesPatients;
import org.opensrp.domain.TBPatient;
import org.opensrp.domain.TBPatient;
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
public class TBPatientsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;

	
	public int save(TBPatient tBPatient) throws Exception {
		String insertQuery = "insert into " + TBPatient.tbName + " (" +
				TBPatient.COL_HEALTH_FACILITY_PATIENT_ID + "," +
				TBPatient.COL_PATIENT_TYPE + "," +
				TBPatient.COL_TRANSFER_TYPE + "," +
				TBPatient.COL_REFERRAL_TYPE + "," +
				TBPatient.COL_VEO + "," +
				TBPatient.COL_WEIGHT + "," +
				TBPatient.COL_XRAY + "," +
				TBPatient.COL_MAKOHOZI + "," +
				TBPatient.COL_OTHER_TESTS_DETAILS + "," +
				TBPatient.COL_TEST_TYPE + "," +
				TBPatient.COL_TREATMENT_TYPE + "," +
				TBPatient.COL_OUTCOME + "," +
				TBPatient.COL_OUTCOME_DATE + "," +
				TBPatient.COL_OUTCOME_DETAILS + "," +
				TBPatient.COL_IS_PREGNANT + "," +
				TBPatient.COL_UPDATED_AT + "," +
				TBPatient.COL_CREATED_AT + ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		Object[] params = new Object[] {
				tBPatient.getHealthFacilitiesPatients().getHealthFacilityPatientId(),
				tBPatient.getPatientType(),
				tBPatient.getTransferType(),
				tBPatient.getReferralType(),
		        tBPatient.getVeo(),
		        tBPatient.getWeight(),
		        tBPatient.getXray(),
		        tBPatient.getMakohozi(),
		        tBPatient.getOtherTestsDetails(),
		        tBPatient.getTestType(),
		        tBPatient.getTreatment_type(),
		        tBPatient.getOutcome(),
		        tBPatient.getOutcomeDate(),
		        tBPatient.getOutcomeDetails(),
		        tBPatient.isPregnant(),
		        tBPatient.getUpdatedAt(),
				tBPatient.getCreatedAt() };
		int[] types = new int[] {
				Types.BIGINT,
				Types.INTEGER,
				Types.INTEGER,
				Types.INTEGER,
				Types.VARCHAR,
				Types.DOUBLE,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.INTEGER,
				Types.VARCHAR,
				Types.VARCHAR,
				Types.DATE,
				Types.VARCHAR,
				Types.BOOLEAN,
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
		String query = "DELETE FROM " + TBPatient.tbName;
		executeQuery(query);
	}


	public List<TBPatient> getTBPatients(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new TBPatientRowMapper());
	}

	
	public class TBPatientRowMapper implements RowMapper<TBPatient> {
		public TBPatient mapRow(ResultSet rs, int rowNum) throws SQLException {
			TBPatient tbPatient = new TBPatient();

			tbPatient.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(TBPatient.COL_CREATED_AT)).getTime()));

			HealthFacilitiesPatients healthFacilitiesPatients = new HealthFacilitiesPatients();
			healthFacilitiesPatients.setHealthFacilityPatientId(rs.getLong(rs.findColumn(TBPatient.COL_HEALTH_FACILITY_PATIENT_ID)));

			tbPatient.setHealthFacilitiesPatients(healthFacilitiesPatients);
			tbPatient.setPatientType(rs.getInt(rs.findColumn(TBPatient.COL_PATIENT_TYPE)));
			tbPatient.setTransferType(rs.getInt(rs.findColumn(TBPatient.COL_TRANSFER_TYPE)));
			tbPatient.setReferralType(rs.getInt(rs.findColumn(TBPatient.COL_REFERRAL_TYPE)));
			tbPatient.setTreatment_type(rs.getString(rs.findColumn(TBPatient.COL_TREATMENT_TYPE)));
			tbPatient.setVeo(rs.getString(rs.findColumn(TBPatient.COL_VEO)));
			tbPatient.setXray(rs.getString(rs.findColumn(TBPatient.COL_XRAY)));
			tbPatient.setWeight(rs.getDouble(rs.findColumn(TBPatient.COL_WEIGHT)));
			tbPatient.setMakohozi(rs.getString(rs.findColumn(TBPatient.COL_MAKOHOZI)));
			tbPatient.setOtherTestsDetails(rs.getString(rs.findColumn(TBPatient.COL_OTHER_TESTS_DETAILS)));
			tbPatient.setTestType(rs.getInt(rs.findColumn(TBPatient.COL_TEST_TYPE)));
			tbPatient.setOutcome(rs.getString(rs.findColumn(TBPatient.COL_OUTCOME)));
			tbPatient.setOutcomeDate(rs.getDate(rs.findColumn(TBPatient.COL_OUTCOME_DATE)));
			tbPatient.setOutcomeDetails(rs.getString(rs.findColumn(TBPatient.COL_OUTCOME_DETAILS)));
			tbPatient.setUpdatedAt(rs.getDate(rs.findColumn(TBPatient.COL_UPDATED_AT)));
			tbPatient.setTbPatientId(rs.getLong(rs.findColumn(TBPatient.COL_TB_PATIENT_ID)));
			tbPatient.setPregnant(rs.getBoolean(rs.findColumn(TBPatient.COL_IS_PREGNANT)));
			return tbPatient;
		}
		
	}

}
