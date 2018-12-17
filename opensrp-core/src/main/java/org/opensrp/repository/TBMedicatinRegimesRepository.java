package org.opensrp.repository;

import org.opensrp.domain.TBMedicationRegime;
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
public class TBMedicatinRegimesRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	public Long save(TBMedicationRegime tbMedicationRegime) throws Exception {

		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(TBMedicationRegime.tbName).usingGeneratedKeyColumns("_id");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(TBMedicationRegime.COL_MEDICATION_REGIME_NAME, tbMedicationRegime.getMedicationRegimeName());
		parameters.put(TBMedicationRegime.COL_IS_ACTIVE  , tbMedicationRegime.getIsActive());
		parameters.put(TBMedicationRegime.COL_CREATED_AT , tbMedicationRegime.getCreatedAt());
		parameters.put(TBMedicationRegime.COL_UPDATED_AT , tbMedicationRegime.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + TBMedicationRegime.tbName;
		executeQuery(query);
	}



	public List<TBMedicationRegime> getTBSputumMedicationRegime(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new ServiceRowMapper());
	}



	public class ServiceRowMapper implements RowMapper<TBMedicationRegime> {
		public TBMedicationRegime mapRow(ResultSet rs, int rowNum) throws SQLException {
			TBMedicationRegime tbMedicationRegime = new TBMedicationRegime();

			tbMedicationRegime.setId(rs.getLong(rs.findColumn("_id")));
			tbMedicationRegime.setMedicationRegimeName(rs.getString(rs.findColumn(TBMedicationRegime.COL_MEDICATION_REGIME_NAME)));
			tbMedicationRegime.setIsActive(rs.getBoolean(rs.findColumn(TBMedicationRegime.COL_IS_ACTIVE)));
			tbMedicationRegime.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(TBMedicationRegime.COL_CREATED_AT)).getTime()));
			tbMedicationRegime.setUpdatedAt(rs.getDate(rs.findColumn(TBMedicationRegime.COL_UPDATED_AT)));
			return tbMedicationRegime;
		}
		
	}

}
