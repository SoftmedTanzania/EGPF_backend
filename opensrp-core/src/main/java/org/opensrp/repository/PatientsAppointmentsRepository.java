package org.opensrp.repository;

import org.opensrp.domain.HealthFacilitiesPatients;
import org.opensrp.domain.PatientAppointments;
import org.opensrp.domain.PatientAppointments;
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
public class PatientsAppointmentsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int save(PatientAppointments patientAppointments) throws Exception {
		String insertQuery = "insert into " + PatientAppointments.tbName + " (" +
				PatientAppointments.COL_HEALTH_FACILITY_PATIENT_ID + "," +
				PatientAppointments.COL_APPOINTMENT_DATE + "," +
				PatientAppointments.COL_IS_CANCELLED + "," +
				PatientAppointments.COL_STATUS + "," +
				PatientAppointments.COL_APPOINTMENT_TYPE + "," +
				PatientAppointments.COL_UPDATED_AT + "," +
				PatientAppointments.COL_CREATED_AT + ") values (?,?,?,?,?,?,?) ";

		Object[] params = new Object[] {
				patientAppointments.getHealthFacilitiesPatients().getHealthFacilityPatientId(),
				patientAppointments.getAppointmentDate(),
				patientAppointments.getIsCancelled(),
				patientAppointments.getStatus(),
				patientAppointments.getAppointmentType(),
				patientAppointments.getUpdatedAt(),
				patientAppointments.getCreatedAt() };

		int[] types = new int[] {
				Types.BIGINT,
				Types.DATE,
				Types.BOOLEAN,
				Types.VARCHAR,
				Types.INTEGER,
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
		String query = "DELETE FROM " + PatientAppointments.tbName;
		executeQuery(query);
	}



	public List<PatientAppointments> getAppointments(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new PatientsAppointmentsRowMapper());
	}



	public class PatientsAppointmentsRowMapper implements RowMapper<PatientAppointments> {
		public PatientAppointments mapRow(ResultSet rs, int rowNum) throws SQLException {
			PatientAppointments patientAppointments = new PatientAppointments();

			patientAppointments.setAppointment_id(rs.getLong(rs.findColumn(PatientAppointments.COL_APPOINTMENT_ID)));

			HealthFacilitiesPatients healthFacilitiesPatients = new HealthFacilitiesPatients();
			healthFacilitiesPatients.setHealthFacilityPatientId(rs.getLong(rs.findColumn(PatientAppointments.COL_HEALTH_FACILITY_PATIENT_ID)));

			patientAppointments.setHealthFacilitiesPatients(healthFacilitiesPatients);
			patientAppointments.setAppointmentDate(rs.getDate(rs.findColumn(PatientAppointments.COL_APPOINTMENT_DATE)));
			patientAppointments.setIsCancelled(rs.getBoolean(rs.findColumn(PatientAppointments.COL_IS_CANCELLED)));
			patientAppointments.setStatus(rs.getString(rs.findColumn(PatientAppointments.COL_STATUS)));
			patientAppointments.setAppointmentType(rs.getInt(rs.findColumn(PatientAppointments.COL_APPOINTMENT_TYPE)));
			patientAppointments.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(PatientAppointments.COL_CREATED_AT)).getTime()));
			patientAppointments.setUpdatedAt(rs.getDate(rs.findColumn(PatientAppointments.COL_UPDATED_AT)));
			return patientAppointments;
		}
		
	}

}
