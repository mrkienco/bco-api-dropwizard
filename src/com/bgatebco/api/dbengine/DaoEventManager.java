package com.bgatebco.api.dbengine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bgatebco.api.bean.TopUser;

import common.jdbc.JdbcConnectionPool;
import common.jdbc.core.RowMapper;
import common.jdbc.core.simple.SimpleJdbcDaoSupport;

public class DaoEventManager extends SimpleJdbcDaoSupport {

	public DaoEventManager(JdbcConnectionPool pool) {
		super(pool);
	}
	
	public class TopUserMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			TopUser topUser = new TopUser();
			topUser.setUsername(rs.getString("name"));
			topUser.setDisplayName(rs.getString("title"));
			topUser.setIndex(index);
			topUser.setScore(rs.getInt("money"));
			return topUser;
		}
		
	}
	
	public class TopEventMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			TopUser topUser = new TopUser();
			topUser.setUsername(rs.getString("user_name"));
			topUser.setDisplayName(rs.getString("user_title"));
			topUser.setIndex(index);
			topUser.setScore(rs.getInt("total_score"));
			return topUser;
		}
		
	}
	
	public List<TopUser> getTopRich() {
		String sql = "SELECT * FROM user_manager ORDER BY money DESC LIMIT 10";
		return getSimpleJdbcTemplate().query(sql, new TopUserMapper());
	}
	
	public List<TopUser> getTopUserEvent(String dateStart, String dateEnd, int type) {
		String sql = "SELECT * FROM view_leaderboard_user WHERE DATE(time_start) <= '" + dateStart + "' AND DATE(time_end) >= '" + dateEnd + "' AND type = " + type + " ORDER BY total_score DESC";
		return getSimpleJdbcTemplate().query(sql, new TopEventMapper());
	}
}
