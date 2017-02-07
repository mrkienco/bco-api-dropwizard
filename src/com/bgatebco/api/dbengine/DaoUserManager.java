package com.bgatebco.api.dbengine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import common.jdbc.JdbcConnectionPool;
import common.jdbc.core.RowMapper;
import common.jdbc.core.simple.SimpleJdbcDaoSupport;

public class DaoUserManager extends SimpleJdbcDaoSupport {

	public DaoUserManager(JdbcConnectionPool pool) {
		super(pool);
	}
	
	public class BeanUserManagerMapper implements RowMapper {

		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			BeanUserManager beanUserManager = new BeanUserManager();
			beanUserManager.setName(rs.getString("name"));
			beanUserManager.setTitle(rs.getString("title"));
			beanUserManager.setMoney(rs.getInt("money"));
			beanUserManager.setGold(rs.getInt("gold"));
			return beanUserManager;
		}
		
	}
	
	public BeanUserManager getUserManager(String username) {
		BeanUserManager userManager = null;
		try {
			String sql = "SELECT * FROM user_manager WHERE name = '" + username + "'";
			List<BeanUserManager> list = getSimpleJdbcTemplate().query(sql, new BeanUserManagerMapper());
			if (list.size() > 0) {
				userManager = list.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userManager;
	}
	
}
