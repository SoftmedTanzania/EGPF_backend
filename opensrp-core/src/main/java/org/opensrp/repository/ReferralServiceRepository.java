package org.opensrp.repository;

import org.opensrp.domain.ReferralService;
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
public class ReferralServiceRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public Long save(ReferralService referralService) throws Exception {
		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(ReferralService.tbName).usingGeneratedKeyColumns(ReferralService.COL_REFERRAL_SERVICE_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(ReferralService.COL_REFERRAL_SERVICE_NAME , referralService.getReferralServiceName());
		parameters.put(ReferralService.COL_REFERRAL_SERVICE_NAME_SW , referralService.getReferralServiceNameSw());
		parameters.put(ReferralService.COL_REFERRAL_CATEGORY_NAME , referralService.getReferralCategoryName());
		parameters.put(ReferralService.COL_IS_ACTIVE  , referralService.isActive());
		parameters.put(ReferralService.COL_CREATED_AT , referralService.getCreatedAt());
		parameters.put(ReferralService.COL_UPDATED_AT , referralService.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + ReferralService.tbName;
		executeQuery(query);
	}



	public List<ReferralService> getReferralServices(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new ServiceRowMapper());
	}



	public class ServiceRowMapper implements RowMapper<ReferralService> {
		public ReferralService mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReferralService referralService = new ReferralService();

			referralService.setReferralServiceId(rs.getLong(rs.findColumn(ReferralService.COL_REFERRAL_SERVICE_ID)));
			referralService.setReferralCategoryName(rs.getString(rs.findColumn(ReferralService.COL_REFERRAL_CATEGORY_NAME)));
			referralService.setReferralServiceName(rs.getString(rs.findColumn(ReferralService.COL_REFERRAL_SERVICE_NAME)));
			referralService.setReferralServiceNameSw(rs.getString(rs.findColumn(ReferralService.COL_REFERRAL_SERVICE_NAME_SW)));
			referralService.setActive(rs.getBoolean(rs.findColumn(ReferralService.COL_IS_ACTIVE)));
			referralService.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(ReferralService.COL_CREATED_AT)).getTime()));
			referralService.setUpdatedAt(rs.getDate(rs.findColumn(ReferralService.COL_UPDATED_AT)));
			return referralService;
		}
		
	}

}
