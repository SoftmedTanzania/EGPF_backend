package org.opensrp.repository;

import org.opensrp.domain.ReferralType;
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
public class ReferralTypeRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public Long save(ReferralType referralType) throws Exception {

		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(ReferralType.tbName).usingGeneratedKeyColumns(ReferralType.COL_REFERRAL_TYPE_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(ReferralType.COL_REFERRAL_TYPE_NAME , referralType.getReferralTypeName());
		parameters.put(ReferralType.COL_IS_ACTIVE  , referralType.isActive());
		parameters.put(ReferralType.COL_CREATED_AT , referralType.getCreatedAt());
		parameters.put(ReferralType.COL_UPDATED_AT , referralType.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + ReferralType.tbName;
		executeQuery(query);
	}



	public List<ReferralType> getReferralType(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new TypeRowMapper());
	}



	public class TypeRowMapper implements RowMapper<ReferralType> {
		public ReferralType mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReferralType referralType = new ReferralType();

			referralType.setReferralTypeId(rs.getLong(rs.findColumn(ReferralType.COL_REFERRAL_TYPE_ID)));
			referralType.setReferralTypeName(rs.getString(rs.findColumn(ReferralType.COL_REFERRAL_TYPE_NAME)));
			referralType.setActive(rs.getBoolean(rs.findColumn(ReferralType.COL_IS_ACTIVE)));
			referralType.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(ReferralType.COL_CREATED_AT)).getTime()));
			referralType.setUpdatedAt(rs.getDate(rs.findColumn(ReferralType.COL_UPDATED_AT)));
			return referralType;
		}
		
	}

}
