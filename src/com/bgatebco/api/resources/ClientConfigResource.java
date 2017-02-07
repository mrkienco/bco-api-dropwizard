package com.bgatebco.api.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bgatebco.api.ClientDefaultConfiguration;
import com.bgatebco.api.dbengine.BeanClientBuildManager;
import com.bgatebco.api.dbengine.DataEngine;
import com.bgatebco.api.utils.ErrorCode;
import com.bgatebco.api.utils.ParamsKey;

@Path("client_config")
public class ClientConfigResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientConfigResource.class);
	
	private ClientDefaultConfiguration clientDefaultCfg;
	
	public ClientConfigResource(ClientDefaultConfiguration cfg) {
		this.clientDefaultCfg = cfg;
	}
	
	@GET
	@Path("/get")
	public String getClientConfig(@QueryParam(ParamsKey.BUILD_ID) String buildId,
			@QueryParam(ParamsKey.PARTNER) String partner,
			@QueryParam(ParamsKey.PLATFORM) String platform,
			@QueryParam(ParamsKey.PACKAGE_NAME) String package_name) {
		JSONObject json = new JSONObject();
		try {
			DataEngine DB = DataEngine.getInstance();
			ArrayList<BeanClientBuildManager> listClientBuild = (ArrayList<BeanClientBuildManager>) DB.clientBuildDao.getClientBuildManager(buildId);
			boolean payment_permission = false;
			boolean verify_appstore = true;
			String server_primary_host = clientDefaultCfg.getServer_primary_host();
			String server_primary_port = clientDefaultCfg.getServer_primary_port();
			String server_backup_host = clientDefaultCfg.getServer_backup_host();
			String server_backup_port = clientDefaultCfg.getServer_backup_port();
			String upload_avatar = clientDefaultCfg.getUpload_avatar();
			String url_card_api = clientDefaultCfg.getUrl_card_api();
			boolean card_payment_webview = false;
			String orbit_version = "1";
			String orbit_url = clientDefaultCfg.getOrbit_url();
			String fan_page = clientDefaultCfg.getFan_page();
			if (listClientBuild.size() > 0) {
				BeanClientBuildManager clientBuild = listClientBuild.get(0);
				payment_permission = clientBuild.getPayment_permission() == 1;
				verify_appstore = clientBuild.getVerify_appstore() == 1;
				server_primary_host = clientBuild.getServer_primary_host();
				server_primary_port = clientBuild.getServer_primary_port() + "";
				server_backup_host = clientBuild.getServer_backup_host();
				server_backup_port = clientBuild.getServer_backup_port() + "";
				upload_avatar = clientBuild.getUpload_avatar();
				url_card_api = clientBuild.getUrl_card_api();
				card_payment_webview = clientBuild.getCard_payment_webview() == 1;
				orbit_version = clientBuild.getOrbit_version() + "";
				orbit_url = clientBuild.getOrbit_url();
				fan_page = clientBuild.getFan_page();
			} else {
				int totalClientBuild = DB.clientBuildDao.getAllClientBuildConfig().size();
				DB.clientBuildDao.insertClientBuildManager(buildId, server_primary_host, Integer.parseInt(server_primary_port), server_backup_host, 
						Integer.parseInt(server_backup_port), upload_avatar, url_card_api, 0, orbit_version, 
						orbit_url, fan_page, package_name, partner, platform, totalClientBuild + 1);
			}
			json.put(ParamsKey.RESULT, ErrorCode.SUCCESS.getCode());
			json.put(ParamsKey.MESSAGE, ErrorCode.SUCCESS.getMessage());
			json.put(ParamsKey.PAYMENT_PERMISSION, payment_permission);
			json.put(ParamsKey.VERIFY_APPSTORE, verify_appstore);
			json.put(ParamsKey.SERVER_PRIMARY_HOST, server_primary_host);
			json.put(ParamsKey.SERVER_PRIMARY_PORT, server_primary_port);
			json.put(ParamsKey.SERVER_BACKUP_HOST, server_backup_host);
			json.put(ParamsKey.SERVER_BACKUP_PORT, server_backup_port);
			json.put(ParamsKey.UPLOAD_AVATAR, upload_avatar);
			json.put(ParamsKey.URL_CARD_API, url_card_api);
			json.put(ParamsKey.ORBIT_VERSION, orbit_version);
			json.put(ParamsKey.CARD_PAYMENT_WEBVIEW, card_payment_webview);
			json.put(ParamsKey.ORBIT_URL, orbit_url);
			json.put(ParamsKey.FAN_PAGE, fan_page);			
		} catch (Exception ex) {
			ex.printStackTrace();
			json = buildJsonFromErrorCode(ErrorCode.FAIL);
			
		}
		return json.toJSONString();
	}
	
	public JSONObject buildJsonFromErrorCode(ErrorCode errorCode) {
		JSONObject json = new JSONObject();
		json.put(ParamsKey.RESULT, errorCode.getCode());
		json.put(ParamsKey.MESSAGE, errorCode.getMessage());
		return json;
	}
}
