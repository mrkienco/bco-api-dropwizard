package com.bgatebco.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bgatebco.api.bean.TopUser;
import com.bgatebco.api.dbengine.DBEngine;
import com.bgatebco.api.dbengine.DataEngine;
import com.bgatebco.api.utils.ParamsKey;
import com.bgatebco.api.utils.TimeUtils;
import com.bgatebco.api.views.TopUserView;

@Path("event")
public class EventResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventResource.class);
	
	private static int ONTARGET = 0;
    private static int MISS = 1;
    private static int MAMMON_FISH = 2;
	
	public EventResource() {
		
	}
	
	@GET
	@Path("/topUser")
	@Produces("text/html; charset=utf-8")
	public TopUserView getTopUser() {
		List<TopUser> topUsers = DataEngine.getInstance().eventManagerDao.getTopRich();
		TopUserView topUserView = new TopUserView("Top đại gia");
		String timeStartCurrentWeek = TimeUtils.getTimeString(TimeUtils.getFirstDayOfWeek(), true);
		String timeEndCurrentWeek = TimeUtils.getTimeString(TimeUtils.getLastDayOfWeek(), true);
		String timeStartLastWeek = TimeUtils.getTimeString(TimeUtils.getFirstDayOfWeek() - 7 * TimeUtils.MILLIS_ONE_DAY, true);
		String timeEndLastWeek = TimeUtils.getTimeString(TimeUtils.getLastDayOfWeek() - 7 * TimeUtils.MILLIS_ONE_DAY, true);
		topUserView.setTimeEndCurrentWeek(timeEndCurrentWeek);
		topUserView.setTimeEndLastWeek(timeEndLastWeek);
		topUserView.setTimeStartCurrentWeek(timeStartCurrentWeek);
		topUserView.setTimeStartLastWeek(timeStartLastWeek);
		topUserView.setTopUsersCurrentWeek((ArrayList<TopUser>) topUsers);
		topUserView.setTopUsersLastWeek((ArrayList<TopUser>) topUsers);
		return topUserView;
	}
	
	@GET
	@Path("/topEventUser")
	@Produces("text/html; charset=utf-8")
	public TopUserView getTopEventUser(@QueryParam(ParamsKey.TYPE) int type) {
		String topUserTitle = "Siêu ngư dân";
		if (type == ONTARGET) {
			topUserTitle = "Siêu ngư dân";
		} else if (type == MISS) {
			topUserTitle = "Yêu thương cá";
		} else if (type == MAMMON_FISH) {
			topUserTitle = "Top săn cá thần tài";
		}
		TopUserView topUserView = new TopUserView(topUserTitle);
		String timeStartCurrentWeek = TimeUtils.getTimeString(TimeUtils.getFirstDayOfWeek(), true);
		String timeEndCurrentWeek = TimeUtils.getTimeString(TimeUtils.getLastDayOfWeek(), true);
		String timeStartLastWeek = TimeUtils.getTimeString(TimeUtils.getFirstDayOfWeek() - 7 * TimeUtils.MILLIS_ONE_DAY, true);
		String timeEndLastWeek = TimeUtils.getTimeString(TimeUtils.getLastDayOfWeek() - 7 * TimeUtils.MILLIS_ONE_DAY, true);
		if (type == MAMMON_FISH) {
			timeStartCurrentWeek = TimeUtils.getTimeString(System.currentTimeMillis(), true);
			timeEndCurrentWeek = TimeUtils.getTimeString(System.currentTimeMillis(), true);
			timeStartLastWeek = TimeUtils.getTimeString(System.currentTimeMillis() - 1 * TimeUtils.MILLIS_ONE_DAY, true);
			timeEndLastWeek = TimeUtils.getTimeString(System.currentTimeMillis() - 1 * TimeUtils.MILLIS_ONE_DAY, true);
		}
		topUserView.setTimeEndCurrentWeek(timeEndCurrentWeek);
		topUserView.setTimeEndLastWeek(timeEndLastWeek);
		topUserView.setTimeStartCurrentWeek(timeStartCurrentWeek);
		topUserView.setTimeStartLastWeek(timeStartLastWeek);
		List<TopUser> topUsersCurrentWeek = DataEngine.getInstance().eventManagerDao.getTopUserEvent(timeStartCurrentWeek, timeEndCurrentWeek, type);
		List<TopUser> topUsersLastWeek = DataEngine.getInstance().eventManagerDao.getTopUserEvent(timeStartLastWeek, timeEndLastWeek, type);
		topUserView.setTopUsersCurrentWeek((ArrayList<TopUser>) topUsersCurrentWeek);
		topUserView.setTopUsersLastWeek((ArrayList<TopUser>) topUsersLastWeek);
		return topUserView;
	}
	
}
