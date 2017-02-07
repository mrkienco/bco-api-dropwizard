package com.bgatebco.api.views;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.bgatebco.api.bean.TopUser;

import io.dropwizard.views.View;

public class TopUserView extends View {

	private String leaderboardName;
	private ArrayList<TopUser> topUsersCurrentWeek;
	private String timeStartCurrentWeek;
	private String timeEndCurrentWeek;
	
	private ArrayList<TopUser> topUsersLastWeek;
	private String timeStartLastWeek;
	private String timeEndLastWeek;
	
	public TopUserView(String leaderboardName) {
		super("Leaderboard.ftl", StandardCharsets.UTF_8);
		this.leaderboardName = leaderboardName;
	}
	
	public String getLeaderboardName() {
		return leaderboardName;
	}

	public void setLeaderboardName(String leaderboardName) {
		this.leaderboardName = leaderboardName;
	}

	public ArrayList<TopUser> getTopUsersCurrentWeek() {
		return topUsersCurrentWeek;
	}

	public void setTopUsersCurrentWeek(ArrayList<TopUser> topUsersCurrentWeek) {
		this.topUsersCurrentWeek = topUsersCurrentWeek;
	}

	public String getTimeStartCurrentWeek() {
		return timeStartCurrentWeek;
	}

	public void setTimeStartCurrentWeek(String timeStartCurrentWeek) {
		this.timeStartCurrentWeek = timeStartCurrentWeek;
	}

	public String getTimeEndCurrentWeek() {
		return timeEndCurrentWeek;
	}

	public void setTimeEndCurrentWeek(String timeEndCurrentWeek) {
		this.timeEndCurrentWeek = timeEndCurrentWeek;
	}

	public ArrayList<TopUser> getTopUsersLastWeek() {
		return topUsersLastWeek;
	}

	public void setTopUsersLastWeek(ArrayList<TopUser> topUsersLastWeek) {
		this.topUsersLastWeek = topUsersLastWeek;
	}

	public String getTimeStartLastWeek() {
		return timeStartLastWeek;
	}

	public void setTimeStartLastWeek(String timeStartLastWeek) {
		this.timeStartLastWeek = timeStartLastWeek;
	}

	public String getTimeEndLastWeek() {
		return timeEndLastWeek;
	}

	public void setTimeEndLastWeek(String timeEndLastWeek) {
		this.timeEndLastWeek = timeEndLastWeek;
	}

}
