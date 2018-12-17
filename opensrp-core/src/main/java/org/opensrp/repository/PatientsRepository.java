package org.opensrp.repository;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import org.opensrp.domain.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.opensrp.domain.Patients;

@Repository
public class PatientsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;


	public Long save(Patients patients) throws Exception {
		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(Patients.tbName).usingGeneratedKeyColumns(Patients.COL_PATIENT_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Patients.COL_PATIENT_FIRST_NAME , patients.getFirstName());
		parameters.put(Patients.COL_PATIENT_MIDDLE_NAME , patients.getMiddleName());
		parameters.put(Patients.COL_PATIENT_SURNAME , patients.getSurname());
		parameters.put(Patients.COL_PHONE_NUMBER , patients.getPhoneNumber());
		parameters.put(Patients.COL_DATE_OF_BIRTH , patients.getDateOfBirth());
		parameters.put(Patients.COL_GENDER , patients.getGender());
		parameters.put(Patients.COL_DATE_OF_DEATH , patients.getDateOfDeath());
		parameters.put(Patients.COL_HIV_STATUS , patients.isHivStatus());
		parameters.put(Patients.COL_VILLAGE , patients.getVillage());
		parameters.put(Patients.COL_WARD , patients.getWard());
		parameters.put(Patients.COL_HAMLET , patients.getHamlet());
		parameters.put(Patients.COL_COMMUNITY_BASED_HIV_SERVICE , patients.getCommunityBasedHivService());
		parameters.put(Patients.COL_CARE_TAKER_NAME , patients.getCareTakerName());
		parameters.put(Patients.COL_CARE_TAKER_PHONE_NUMBER, patients.getPhoneNumber());
		parameters.put(Patients.COL_CARE_TAKER_RELATIONSHIP, patients.getCareTakerRelationship());
		parameters.put(Patients.COL_CREATED_AT , patients.getCreatedAt());
		parameters.put(Patients.COL_UPDATED_AT , patients.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
	}

	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}

	public int checkIfExists(String query, Object[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);

	}

	public void clearTable() throws Exception {
		String query = "DELETE FROM " + Patients.tbName;
		executeQuery(query);
	}


	public List<Patients> getPatients(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new referralPatientsRowMapper());
	}


	public class referralPatientsRowMapper implements RowMapper<Patients> {
		public Patients mapRow(ResultSet rs, int rowNum) throws SQLException {
			Patients patients = new Patients();
			patients.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(Patients.COL_CREATED_AT)).getTime()));
			patients.setUpdatedAt(new Date(rs.getTimestamp(rs.findColumn(Patients.COL_UPDATED_AT)).getTime()));
			patients.setPatientId(rs.getLong(rs.findColumn(Patients.COL_PATIENT_ID)));
			patients.setFirstName(rs.getString(rs.findColumn(Patients.COL_PATIENT_FIRST_NAME)));
			patients.setSurname(rs.getString(rs.findColumn(Patients.COL_PATIENT_SURNAME)));
			patients.setMiddleName(rs.getString(rs.findColumn(Patients.COL_PATIENT_MIDDLE_NAME)));
			patients.setWard(rs.getString(rs.findColumn(Patients.COL_WARD)));
			patients.setVillage(rs.getString(rs.findColumn(Patients.COL_VILLAGE)));
			patients.setHamlet(rs.getString(rs.findColumn(Patients.COL_HAMLET)));
			patients.setPhoneNumber(rs.getString(rs.findColumn(Patients.COL_PHONE_NUMBER)));
			patients.setDateOfBirth(rs.getDate(rs.findColumn(Patients.COL_DATE_OF_BIRTH)));
			patients.setCommunityBasedHivService(rs.getString(rs.findColumn(Patients.COL_COMMUNITY_BASED_HIV_SERVICE)));
			patients.setGender(rs.getString(rs.findColumn(Patients.COL_GENDER)));
			patients.setCareTakerName(rs.getString(rs.findColumn(Patients.COL_CARE_TAKER_NAME)));
			patients.setCareTakerPhoneNumber(rs.getString(rs.findColumn(Patients.COL_CARE_TAKER_PHONE_NUMBER)));
			patients.setCareTakerRelationship(rs.getString(rs.findColumn(Patients.COL_CARE_TAKER_RELATIONSHIP)));
			patients.setHivStatus(rs.getBoolean(rs.findColumn(Patients.COL_HIV_STATUS)));
			patients.setDateOfDeath(rs.getDate(rs.findColumn(Patients.COL_DATE_OF_DEATH)));
			return patients;
		}

	}

}
