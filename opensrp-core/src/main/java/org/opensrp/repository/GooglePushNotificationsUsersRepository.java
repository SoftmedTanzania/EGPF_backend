package org.opensrp.repository;

import org.opensrp.domain.GooglePushNotificationsUsers;
import org.opensrp.domain.GooglePushNotificationsUsers;
import org.opensrp.domain.GooglePushNotificationsUsers;
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
public class GooglePushNotificationsUsersRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public Long save(GooglePushNotificationsUsers googlePushNotificationsUsers) throws Exception {

		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(GooglePushNotificationsUsers.tbName).usingGeneratedKeyColumns("_id");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(GooglePushNotificationsUsers.COL_USER_UIID , googlePushNotificationsUsers.getUserUiid());
		parameters.put(GooglePushNotificationsUsers.COL_FACILITY_UIID  , googlePushNotificationsUsers.getFacilityUiid());
		parameters.put(GooglePushNotificationsUsers.COL_USER_TYPE  , googlePushNotificationsUsers.getUserType());
		parameters.put(GooglePushNotificationsUsers.COL_GOOGLE_PUSH_NOTIFICATION_TOKEN , googlePushNotificationsUsers.getGooglePushNotificationToken());
		parameters.put(GooglePushNotificationsUsers.COL_CREATED_AT , googlePushNotificationsUsers.getCreatedAt());
		parameters.put(GooglePushNotificationsUsers.COL_UPDATED_AT , googlePushNotificationsUsers.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + GooglePushNotificationsUsers.tbName;
		executeQuery(query);
	}



	public List<GooglePushNotificationsUsers> getGooglePushNotificationsUsers(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new GooglePushNotificationsUsersRowMapper());
	}



	public class GooglePushNotificationsUsersRowMapper implements RowMapper<GooglePushNotificationsUsers> {
		public GooglePushNotificationsUsers mapRow(ResultSet rs, int rowNum) throws SQLException {
			GooglePushNotificationsUsers googlePushNotificationsUsers = new GooglePushNotificationsUsers();

			googlePushNotificationsUsers.setId(rs.getLong(rs.findColumn("_id")));
			googlePushNotificationsUsers.setUserUiid(rs.getString(rs.findColumn(GooglePushNotificationsUsers.COL_USER_UIID)));
			googlePushNotificationsUsers.setUserType(rs.getInt(rs.findColumn(GooglePushNotificationsUsers.COL_USER_TYPE)));
			googlePushNotificationsUsers.setFacilityUiid(rs.getString(rs.findColumn(GooglePushNotificationsUsers.COL_FACILITY_UIID)));
			googlePushNotificationsUsers.setGooglePushNotificationToken(rs.getString(rs.findColumn(GooglePushNotificationsUsers.COL_GOOGLE_PUSH_NOTIFICATION_TOKEN)));
			googlePushNotificationsUsers.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(GooglePushNotificationsUsers.COL_CREATED_AT)).getTime()));
			googlePushNotificationsUsers.setUpdatedAt(rs.getDate(rs.findColumn(GooglePushNotificationsUsers.COL_UPDATED_AT)));
			return googlePushNotificationsUsers;
		}
		
	}

}
