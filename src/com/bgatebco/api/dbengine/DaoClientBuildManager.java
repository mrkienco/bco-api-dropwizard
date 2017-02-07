package com.bgatebco.api.dbengine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcConnectionPool;
import common.jdbc.core.RowMapper;
import common.jdbc.core.simple.SimpleJdbcDaoSupport;

public class DaoClientBuildManager extends SimpleJdbcDaoSupport {

	public DaoClientBuildManager(JdbcConnectionPool pool) {
		super(pool);
	}
	
	public class ClientBuildMapper implements RowMapper {

		public Object mapRow(ResultSet res, int index) throws SQLException {
			BeanClientBuildManager obj = new BeanClientBuildManager();
			obj.setBuild_id(res.getString("build_id"));
			obj.setCard_payment_webview(res.getInt("card_payment_webview"));
			obj.setFan_page(res.getString("fan_page"));
			obj.setId(res.getInt("id"));
			obj.setOrbit_url(res.getString("orbit_url"));
			obj.setOrbit_version(res.getInt("orbit_version"));
			obj.setOrdering(res.getInt("ordering"));
			obj.setPackage_name(res.getString("package_name"));
			obj.setPartner(res.getString("partner"));
			obj.setPayment_permission(res.getInt("payment_permission"));
			obj.setPlatform(res.getString("platform"));
			obj.setServer_backup_host(res.getString("server_backup_host"));
			obj.setServer_backup_port(res.getInt("server_backup_port"));
			obj.setServer_primary_host(res.getString("server_primary_host"));
			obj.setServer_primary_port(res.getInt("server_primary_port"));
			obj.setUpload_avatar(res.getString("upload_avatar"));
			obj.setUrl_card_api(res.getString("url_card_api"));
			obj.setVerify_appstore(res.getInt("verify_appstore"));
			return obj;
		}
		
	}
	
	public List<BeanClientBuildManager> getClientBuildManager(String buildId) {
		String sql = "SELECT * FROM client_build_manager WHERE build_id = '" + buildId + "'";
		return getSimpleJdbcTemplate().query(sql, new ClientBuildMapper());
	}
	
	public void insertClientBuildManager(String build_id, String server_primary_host, int server_primary_port, 
			String server_backup_host, int server_backup_port, String upload_avatar, String url_card_api, 
			int card_payment_webview, String orbit_version, String orbit_url, String fan_page, String package_name, 
			String partner, String platform, int ordering) {
		String sql = "INSERT INTO client_build_manager(build_id, server_primary_host, server_primary_port, server_backup_host, "
				+ "server_backup_port, upload_avatar, url_card_api, card_payment_webview, orbit_version, orbit_url, fan_page, "
				+ "package_name, partner, platform, ordering, create_time) "
				+ "VALUES('" + build_id + "', '" + server_primary_host + "', " + server_primary_port + ", '" + server_backup_host 
				+ "', " + server_backup_port + ", '" + upload_avatar + "', '" + url_card_api + "', " + card_payment_webview + ", '" 
				+ orbit_version + "', '" + orbit_url + "', '" + fan_page + "', '" + package_name + "', '" + partner + "', '" + platform 
				+ "', " + ordering + ", now())";
		getSimpleJdbcTemplate().update(sql);
	}
	
	public List<BeanClientBuildManager> getAllClientBuildConfig() {
		String sql = "SELECT * FROM client_build_manager";
		return getSimpleJdbcTemplate().query(sql, new ClientBuildMapper());
	}
	
}
