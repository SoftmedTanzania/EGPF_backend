package org.opensrp.repository;

import org.opensrp.domain.PatientReferral;
import org.opensrp.domain.Patients;
import org.opensrp.dto.CHWReferralsSummaryDTO;
import org.opensrp.dto.FacilityDepartmentReferralSummaryDTO;
import org.opensrp.dto.FacilityProvidersReferralSummaryDTO;
import org.opensrp.dto.InterFacilityReferralSummaryDTO;
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
public class PatientReferralRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;


	public long save(PatientReferral patientReferral) throws Exception {


		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(PatientReferral.tbName).usingGeneratedKeyColumns(PatientReferral.COL_REFERRAL_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(PatientReferral.COL_PATIENT_ID, patientReferral.getPatient().getPatientId());
		parameters.put(PatientReferral.COL_COMMUNITY_BASED_HIV_SERVICE, patientReferral.getCommunityBasedHivService());
		parameters.put(PatientReferral.COL_REFERRAL_REASON, patientReferral.getReferralReason());
		parameters.put(PatientReferral.COL_SERVICE_ID, patientReferral.getServiceId());
		parameters.put(PatientReferral.COL_REFERRAL_UUID, patientReferral.getReferralUUID());
		parameters.put(PatientReferral.COL_FACILITY_ID, patientReferral.getFacilityId());
		parameters.put(PatientReferral.COL_CTC_NUMBER, patientReferral.getCtcNumber());
		parameters.put(PatientReferral.COL_SERVICE_PROVIDER_UIID, patientReferral.getServiceProviderUIID());
		parameters.put(PatientReferral.COL_SERVICE_PROVIDER_GROUP, patientReferral.getServiceProviderGroup());
		parameters.put(PatientReferral.COL_VILLAGE_LEADER, patientReferral.getVillageLeader());
		parameters.put(PatientReferral.COL_FROM_FACILITY_ID, patientReferral.getFromFacilityId());
		parameters.put(PatientReferral.COL_OTHER_CLINICAL_INFORMATION, patientReferral.getOtherClinicalInformation());
		parameters.put(PatientReferral.COL_SERVICES_GIVEN_TO_PATIENT, patientReferral.getServiceGivenToPatient());
		parameters.put(PatientReferral.COL_OTHER_NOTES, patientReferral.getOtherNotes());
		parameters.put(PatientReferral.COL_REFERRAL_SOURCE, patientReferral.getReferralSource());
		parameters.put(PatientReferral.COL_REFERRAL_DATE, patientReferral.getReferralDate());
		parameters.put(PatientReferral.COL_APPOINTMENT_DATE, patientReferral.getAppointmentDate());
		parameters.put(PatientReferral.COL_REFERRAL_STATUS, patientReferral.getReferralStatus());
		parameters.put(PatientReferral.COL_INSTANCE_ID, patientReferral.getInstanceId());
		parameters.put(PatientReferral.COL_REFERRAL_TYPE, patientReferral.getReferralType());
		parameters.put(PatientReferral.COL_LAB_TEST, patientReferral.getLabTest());
		parameters.put(PatientReferral.COL_TEST_RESULTS, patientReferral.isTestResults());
		parameters.put(PatientReferral.COL_IS_EMERGENCY, patientReferral.isEmergency());
		parameters.put(PatientReferral.COL_CREATED_AT, patientReferral.getCreatedAt());
		parameters.put(PatientReferral.COL_UPDATED_AT, patientReferral.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();

	}

	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}

	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);

	}

	public void clearTable() throws Exception {
		String query = "DELETE FROM " + PatientReferral.tbName;
		executeQuery(query);
	}


	public List<PatientReferral> getReferrals(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new HealthFacilityRefferalRowMapper());
	}


	public List<CHWReferralsSummaryDTO> getCHWReferralsSummary(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new CHWReferralsSummaryRowMapper());
	}


	public class HealthFacilityRefferalRowMapper implements RowMapper<PatientReferral> {
		public PatientReferral mapRow(ResultSet rs, int rowNum) throws SQLException {
			PatientReferral patientReferral = new PatientReferral();
			patientReferral.setId(rs.getLong(rs.findColumn(PatientReferral.COL_REFERRAL_ID)));

			Patients patient = new Patients();
			patient.setPatientId(rs.getLong(rs.findColumn(PatientReferral.COL_PATIENT_ID)));
			patientReferral.setPatient(patient);

			patientReferral.setCommunityBasedHivService(rs.getString(rs.findColumn(PatientReferral.COL_COMMUNITY_BASED_HIV_SERVICE)));
			patientReferral.setReferralReason(rs.getString(rs.findColumn(PatientReferral.COL_REFERRAL_REASON)));
			patientReferral.setFromFacilityId(rs.getString(rs.findColumn(PatientReferral.COL_FROM_FACILITY_ID)));
			patientReferral.setServiceId(rs.getInt(rs.findColumn(PatientReferral.COL_SERVICE_ID)));
			patientReferral.setReferralUUID(rs.getString(rs.findColumn(PatientReferral.COL_REFERRAL_UUID)));
			patientReferral.setCtcNumber(rs.getString(rs.findColumn(PatientReferral.COL_CTC_NUMBER)));
			patientReferral.setServiceProviderUIID(rs.getString(rs.findColumn(PatientReferral.COL_SERVICE_PROVIDER_UIID)));
			patientReferral.setServiceProviderGroup(rs.getString(rs.findColumn(PatientReferral.COL_SERVICE_PROVIDER_GROUP)));
			patientReferral.setVillageLeader(rs.getString(rs.findColumn(PatientReferral.COL_VILLAGE_LEADER)));
			patientReferral.setReferralDate(rs.getDate(rs.findColumn(PatientReferral.COL_REFERRAL_DATE)));
			patientReferral.setAppointmentDate(rs.getDate(rs.findColumn(PatientReferral.COL_APPOINTMENT_DATE)));
			patientReferral.setFacilityId(rs.getString(rs.findColumn(PatientReferral.COL_FACILITY_ID)));
			patientReferral.setReferralStatus(rs.getInt(rs.findColumn(PatientReferral.COL_REFERRAL_STATUS)));
			patientReferral.setInstanceId(rs.getString(rs.findColumn(PatientReferral.COL_INSTANCE_ID)));
			patientReferral.setReferralType(rs.getLong(rs.findColumn(PatientReferral.COL_REFERRAL_TYPE)));
			patientReferral.setReferralSource(rs.getInt(rs.findColumn(PatientReferral.COL_REFERRAL_SOURCE)));
			patientReferral.setServiceGivenToPatient(rs.getString(rs.findColumn(PatientReferral.COL_SERVICES_GIVEN_TO_PATIENT)));
			patientReferral.setTestResults(rs.getInt(rs.findColumn(PatientReferral.COL_TEST_RESULTS)));
			patientReferral.setEmergency(rs.getBoolean(rs.findColumn(PatientReferral.COL_IS_EMERGENCY)));
			patientReferral.setLabTest(rs.getInt(rs.findColumn(PatientReferral.COL_LAB_TEST)));
			patientReferral.setOtherClinicalInformation(rs.getString(rs.findColumn(PatientReferral.COL_OTHER_CLINICAL_INFORMATION)));
			patientReferral.setOtherNotes(rs.getString(rs.findColumn(PatientReferral.COL_OTHER_NOTES)));
			patientReferral.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(PatientReferral.COL_CREATED_AT)).getTime()));
			patientReferral.setUpdatedAt(rs.getDate(rs.findColumn(PatientReferral.COL_UPDATED_AT)));
			return patientReferral;
		}

	}

	public class CHWReferralsSummaryRowMapper implements RowMapper<CHWReferralsSummaryDTO> {
		public CHWReferralsSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CHWReferralsSummaryDTO chwReferralsSummaryDTO =new CHWReferralsSummaryDTO();

			chwReferralsSummaryDTO.setCount(rs.getInt(rs.findColumn("count")));
			chwReferralsSummaryDTO.setServiceName(rs.getString(rs.findColumn("service_name")));
			return  chwReferralsSummaryDTO;
		}

	}



	public List<FacilityDepartmentReferralSummaryDTO> getFacilityDepartmentReferralsSummary(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new HealthFacilityDepartmentReferralsSummaryRowMapper());
	}



	public class HealthFacilityDepartmentReferralsSummaryRowMapper implements RowMapper<FacilityDepartmentReferralSummaryDTO> {
		public FacilityDepartmentReferralSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			FacilityDepartmentReferralSummaryDTO facilityReferralsSummaryDTO =new FacilityDepartmentReferralSummaryDTO();


			facilityReferralsSummaryDTO.setCount(rs.getInt(rs.findColumn("count")));


			if(rs.getInt(rs.findColumn("referral_source")) == 0) {
				facilityReferralsSummaryDTO.setDepartmentName("OPD");
			}else if(rs.getInt(rs.findColumn("referral_source")) == 1){
				facilityReferralsSummaryDTO.setDepartmentName("HIV Clinic");
			}else if(rs.getInt(rs.findColumn("referral_source")) == 2){
				facilityReferralsSummaryDTO.setDepartmentName("TB Clinic");
			}


			if(rs.getInt(rs.findColumn("referral_status")) == 1 )
				facilityReferralsSummaryDTO.setStatus("Success");
			else if(rs.getInt(rs.findColumn("referral_status")) == 0 ){
				facilityReferralsSummaryDTO.setStatus("Pending");
			}else{
				facilityReferralsSummaryDTO.setStatus("Failed");
			}


			return  facilityReferralsSummaryDTO;
		}

	}

	public List<FacilityProvidersReferralSummaryDTO> getFacilityProvidersReferralsSummary(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new HealthFacilityDepartmentProvidersReferralsSummaryRowMapper());
	}



	public class HealthFacilityDepartmentProvidersReferralsSummaryRowMapper implements RowMapper<FacilityProvidersReferralSummaryDTO> {
		public FacilityProvidersReferralSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			FacilityProvidersReferralSummaryDTO facilityReferralsSummaryDTO =new FacilityProvidersReferralSummaryDTO();


			facilityReferralsSummaryDTO.setCount(rs.getInt(rs.findColumn("count")));


			if(rs.getInt(rs.findColumn("referral_source")) == 0) {
				facilityReferralsSummaryDTO.setDepartmentName("OPD");
			}else if(rs.getInt(rs.findColumn("referral_source")) == 1){
				facilityReferralsSummaryDTO.setDepartmentName("HIV Clinic");
			}else if(rs.getInt(rs.findColumn("referral_source")) == 2){
				facilityReferralsSummaryDTO.setDepartmentName("TB Clinic");
			}


			facilityReferralsSummaryDTO.setProviderUuid(rs.getString(rs.findColumn("provider_uuid")));

			if(rs.getInt(rs.findColumn("referral_status")) == 1 )
				facilityReferralsSummaryDTO.setStatus("Success");
			else if(rs.getInt(rs.findColumn("referral_status")) == 0 ){
				facilityReferralsSummaryDTO.setStatus("Pending");
			}else{
				facilityReferralsSummaryDTO.setStatus("Failed");
			}


			return  facilityReferralsSummaryDTO;
		}

	}


	public List<InterFacilityReferralSummaryDTO> getInterFacilityReferralsSummary(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new IntraHealthFacilityReferralsSummaryRowMapper());
	}


	public class IntraHealthFacilityReferralsSummaryRowMapper implements RowMapper<InterFacilityReferralSummaryDTO> {
		public InterFacilityReferralSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			InterFacilityReferralSummaryDTO interFacilityReferralSummaryDTO =new InterFacilityReferralSummaryDTO();

			interFacilityReferralSummaryDTO.setCount(rs.getInt(rs.findColumn("count")));
			interFacilityReferralSummaryDTO.setToFacilityName(rs.getString(rs.findColumn("to_facility_name")));

			if(rs.getInt(rs.findColumn("referral_status")) == 1 )
				interFacilityReferralSummaryDTO.setStatus("Success");
			else if(rs.getInt(rs.findColumn("referral_status")) == 0 ){
				interFacilityReferralSummaryDTO.setStatus("Pending");
			}else{
				interFacilityReferralSummaryDTO.setStatus("Failed");
			}

			return  interFacilityReferralSummaryDTO;
		}

	}


}
