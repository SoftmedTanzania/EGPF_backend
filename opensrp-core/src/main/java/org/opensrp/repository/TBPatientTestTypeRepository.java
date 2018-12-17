package org.opensrp.repository;

import org.opensrp.domain.TBPatientTestType;
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
public class TBPatientTestTypeRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public Long save(TBPatientTestType patientType) throws Exception {

		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(TBPatientTestType.tbName).usingGeneratedKeyColumns("_id");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(TBPatientTestType.COL_TEST_TYPE_NAME, patientType.getTestTypeName());
		parameters.put(TBPatientTestType.COL_IS_ACTIVE  , patientType.getIsActive());
		parameters.put(TBPatientTestType.COL_CREATED_AT , patientType.getCreatedAt());
		parameters.put(TBPatientTestType.COL_UPDATED_AT , patientType.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + TBPatientTestType.tbName;
		executeQuery(query);
	}



	public List<TBPatientTestType> getTBPatientTypes(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new ServiceRowMapper());
	}



	public class ServiceRowMapper implements RowMapper<TBPatientTestType> {
		public TBPatientTestType mapRow(ResultSet rs, int rowNum) throws SQLException {
			TBPatientTestType patientType = new TBPatientTestType();

			patientType.setId(rs.getLong(rs.findColumn("_id")));
			patientType.setTestTypeName(rs.getString(rs.findColumn(TBPatientTestType.COL_TEST_TYPE_NAME)));
			patientType.setIsActive(rs.getBoolean(rs.findColumn(TBPatientTestType.COL_IS_ACTIVE)));
			patientType.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(TBPatientTestType.COL_CREATED_AT)).getTime()));
			patientType.setUpdatedAt(rs.getDate(rs.findColumn(TBPatientTestType.COL_UPDATED_AT)));
			return patientType;
		}
		
	}

}
