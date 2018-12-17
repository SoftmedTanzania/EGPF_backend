package org.opensrp.repository;

import org.opensrp.domain.PKReferralServiceIndicator;
import org.opensrp.domain.ServiceIndicator;
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
public class ReferralServiceIndicatorRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public void save(ServiceIndicator serviceIndicator) throws Exception {

		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(ServiceIndicator.tbName);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(ServiceIndicator.COL_SERVICE_INDICATOR_ID, serviceIndicator.getServiceIndicatorId());
		parameters.put(ServiceIndicator.COL_INDICATOR_ID, serviceIndicator.getPkReferralServiceIndicator().getIndicatorId());
		parameters.put(ServiceIndicator.COL_SERVICE_ID, serviceIndicator.getPkReferralServiceIndicator().getServiceId());
		parameters.put(ServiceIndicator.COL_IS_ACTIVE  , serviceIndicator.isActive());
		parameters.put(ServiceIndicator.COL_CREATED_AT , serviceIndicator.getCreatedAt());
		parameters.put(ServiceIndicator.COL_UPDATED_AT , serviceIndicator.getCreatedAt());

		insert.execute(parameters);
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + ServiceIndicator.tbName;
		executeQuery(query);
	}



	public List<ServiceIndicator> getReferralServicesIndicators(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new ServiceRowMapper());
	}



	public class ServiceRowMapper implements RowMapper<ServiceIndicator> {
		public ServiceIndicator mapRow(ResultSet rs, int rowNum) throws SQLException {
			ServiceIndicator serviceIndicator = new ServiceIndicator();

			serviceIndicator.setServiceIndicatorId(rs.getLong(rs.findColumn(ServiceIndicator.COL_SERVICE_INDICATOR_ID)));

			PKReferralServiceIndicator pkReferralServiceIndicator = new PKReferralServiceIndicator(rs.getLong(rs.findColumn(ServiceIndicator.COL_INDICATOR_ID)),rs.getLong(rs.findColumn(ServiceIndicator.COL_SERVICE_ID)));
			serviceIndicator.setPkReferralServiceIndicator(pkReferralServiceIndicator);


			serviceIndicator.setActive(rs.getBoolean(rs.findColumn(ServiceIndicator.COL_IS_ACTIVE)));
			serviceIndicator.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(ServiceIndicator.COL_CREATED_AT)).getTime()));
			serviceIndicator.setUpdatedAt(rs.getDate(rs.findColumn(ServiceIndicator.COL_UPDATED_AT)));
			return serviceIndicator;
		}
		
	}

}
