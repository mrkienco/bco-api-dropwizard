package com.bgatebco.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.simple.JSONObject;

import com.bgatebco.api.dbengine.BeanUserManager;
import com.bgatebco.api.dbengine.DataEngine;
import com.bgatebco.api.utils.ParamsKey;

@Path("user")
public class UserResource {

	public UserResource() {
		
	}
	
	@GET
	@Path("/info")
	@Produces("text/html; charset=utf-8")
	public String getTopUser(@QueryParam(ParamsKey.USER_NAME) String user_name) {
		JSONObject jsonObj = new JSONObject();
		try {
			BeanUserManager beanUserManager = DataEngine.getInstance().userManagerDao.getUserManager(user_name);
			if (beanUserManager != null) {
				jsonObj.put("user_name", beanUserManager.getName());
				jsonObj.put("title", beanUserManager.getTitle());
				jsonObj.put("money", beanUserManager.getMoney());
				jsonObj.put("gold", beanUserManager.getGold());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jsonObj.toJSONString();
	}
	
}
