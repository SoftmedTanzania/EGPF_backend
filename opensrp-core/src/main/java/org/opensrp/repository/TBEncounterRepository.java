package org.opensrp.repository;

import org.opensrp.domain.TBEncounter;
import org.opensrp.domain.TBEncounter;
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
public class TBEncounterRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;

	
	public int save(TBEncounter tBEncounter) throws Exception {
		String insertQuery = "insert into " + TBEncounter.tbName + " (" +
				TBEncounter.COL_TB_PATIENT_ID + "," +
				TBEncounter.COL_APPOINTMENT_ID + "," +
				TBEncounter.COL_LOCAL_ID + "," +
				TBEncounter.COL_ENCOUNTER_MONTH + "," +
				TBEncounter.COL_ENCOUNTER_YEAR + "," +
				TBEncounter.COL_HAS_FINISHED_PREVIOUS_MONTH_MEDICATION + "," +
				TBEncounter.COL_MAKOHOZI + "," +
				TBEncounter.COL_WEIGHT + "," +
				TBEncounter.COL_SCHEDULED_DATE + "," +
				TBEncounter.COL_MEDICATION_DATE + "," +
				TBEncounter.COL_MEDICATION_STATUS + "," +
				TBEncounter.COL_UPDATED_AT + "," +
				TBEncounter.COL_CREATED_AT + ") values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		Object[] params = new Object[] {
				tBEncounter.getTbPatientId(),
				tBEncounter.getAppointmentId(),
				tBEncounter.getLocalID(),
				tBEncounter.getEncounterMonth(),
				tBEncounter.getEncounterYear(),
				tBEncounter.isHasFinishedPreviousMonthMedication(),
		        tBEncounter.getMakohozi(),
		        tBEncounter.getWeight(),
				tBEncounter.getScheduledDate(),
				tBEncounter.getMedicationDate(),
				tBEncounter.isMedicationStatus(),
		        tBEncounter.getUpdatedAt(),
				tBEncounter.getCreatedAt() };
		int[] types = new int[] {
				Types.BIGINT,
				Types.BIGINT,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.BOOLEAN,
				Types.VARCHAR,
				Types.DOUBLE,
				Types.DATE,
				Types.DATE,
				Types.BOOLEAN,
				Types.DATE,
				Types.TIMESTAMP };
		
		return jdbcTemplate.update(insertQuery, params, types);
		
	}


	public int update(TBEncounter tBEncounter) throws Exception {
		String insertQuery = "UPDATE " + TBEncounter.tbName + " SET " +
				TBEncounter.COL_ENCOUNTER_MONTH + " = ? ," +
				TBEncounter.COL_ENCOUNTER_YEAR + " = ? ," +
				TBEncounter.COL_HAS_FINISHED_PREVIOUS_MONTH_MEDICATION + " = ? ," +
				TBEncounter.COL_MAKOHOZI + " = ? ," +
				TBEncounter.COL_WEIGHT + " = ? ," +
				TBEncounter.COL_MEDICATION_DATE + " = ? ," +
				TBEncounter.COL_MEDICATION_STATUS + " = ?," +
				TBEncounter.COL_UPDATED_AT + " = ? " +
				"WHERE _id = ? ";

		Object[] params = new Object[] {
				tBEncounter.getEncounterMonth(),
				tBEncounter.getEncounterYear(),
				tBEncounter.isHasFinishedPreviousMonthMedication(),
				tBEncounter.getMakohozi(),
				tBEncounter.getWeight(),
				tBEncounter.getMedicationDate(),
				tBEncounter.isMedicationStatus(),
				tBEncounter.getUpdatedAt(),
				tBEncounter.getId()};
		int[] types = new int[] {
				Types.INTEGER,
				Types.INTEGER,
				Types.BOOLEAN,
				Types.VARCHAR,
				Types.DOUBLE,
				Types.DATE,
				Types.BOOLEAN,
				Types.DATE,
				Types.BIGINT};

		return jdbcTemplate.update(insertQuery, params, types);

	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + TBEncounter.tbName;
		executeQuery(query);
	}


	public List<TBEncounter> getTBEncounters(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new TBEncounterRowMapper());
	}

	
	public class TBEncounterRowMapper implements RowMapper<TBEncounter> {
		public TBEncounter mapRow(ResultSet rs, int rowNum) throws SQLException {
			TBEncounter tbEncounter = new TBEncounter();

			tbEncounter.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(TBEncounter.COL_CREATED_AT)).getTime()));
			tbEncounter.setAppointmentId(rs.getLong(rs.findColumn(TBEncounter.COL_APPOINTMENT_ID)));
			tbEncounter.setEncounterMonth(rs.getInt(rs.findColumn(TBEncounter.COL_ENCOUNTER_MONTH)));
			tbEncounter.setEncounterYear(rs.getInt(rs.findColumn(TBEncounter.COL_ENCOUNTER_YEAR)));
			tbEncounter.setEncounterYear(rs.getInt(rs.findColumn(TBEncounter.COL_ENCOUNTER_YEAR)));
			tbEncounter.setTbPatientId(rs.getLong(rs.findColumn(TBEncounter.COL_TB_PATIENT_ID)));
			tbEncounter.setLocalID(rs.getString(rs.findColumn(TBEncounter.COL_LOCAL_ID)));
			tbEncounter.setMakohozi(rs.getString(rs.findColumn(TBEncounter.COL_MAKOHOZI)));
			tbEncounter.setWeight(rs.getDouble(rs.findColumn(TBEncounter.COL_WEIGHT)));
			tbEncounter.setScheduledDate(rs.getDate(rs.findColumn(TBEncounter.COL_SCHEDULED_DATE)));
			tbEncounter.setMedicationDate(rs.getDate(rs.findColumn(TBEncounter.COL_MEDICATION_DATE)));
			tbEncounter.setMedicationStatus(rs.getBoolean(rs.findColumn(TBEncounter.COL_MEDICATION_STATUS)));
			tbEncounter.setUpdatedAt(rs.getDate(rs.findColumn(TBEncounter.COL_UPDATED_AT)));
			tbEncounter.setId(rs.getLong(rs.findColumn("_id")));
			return tbEncounter;
		}
		
	}

}
