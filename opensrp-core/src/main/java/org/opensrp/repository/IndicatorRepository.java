package org.opensrp.repository;

import org.opensrp.domain.Indicator;
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
public class IndicatorRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public Long save(Indicator indicator) throws Exception {

		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(Indicator.tbName).usingGeneratedKeyColumns(Indicator.COL_REFERRAL_INDICATOR_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(Indicator.COL_REFERRAL_INDICATOR_NAME , indicator.getReferralIndicatorName());
		parameters.put(Indicator.COL_REFERRAL_INDICATOR_NAME_SW , indicator.getReferralIndicatorNameSw());
		parameters.put(Indicator.COL_IS_ACTIVE  , indicator.isActive());
		parameters.put(Indicator.COL_CREATED_AT , indicator.getCreatedAt());
		parameters.put(Indicator.COL_UPDATED_AT , indicator.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + Indicator.tbName;
		executeQuery(query);
	}



	public List<Indicator> getReferralIndicators(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new ServiceRowMapper());
	}



	public class ServiceRowMapper implements RowMapper<Indicator> {
		public Indicator mapRow(ResultSet rs, int rowNum) throws SQLException {
			Indicator indicator = new Indicator();

			indicator.setReferralIndicatorId(rs.getLong(rs.findColumn(Indicator.COL_REFERRAL_INDICATOR_ID)));
			indicator.setReferralIndicatorName(rs.getString(rs.findColumn(Indicator.COL_REFERRAL_INDICATOR_NAME)));
			indicator.setReferralIndicatorNameSw(rs.getString(rs.findColumn(Indicator.COL_REFERRAL_INDICATOR_NAME_SW)));
			indicator.setActive(rs.getBoolean(rs.findColumn(Indicator.COL_IS_ACTIVE)));
			indicator.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(Indicator.COL_CREATED_AT)).getTime()));
			indicator.setUpdatedAt(rs.getDate(rs.findColumn(Indicator.COL_UPDATED_AT)));
			return indicator;
		}
		
	}

}
