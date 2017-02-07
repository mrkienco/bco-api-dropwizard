package com.bgatebco.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TopUser {
	
	@JsonProperty
    private int index;

	@JsonProperty
    private String username;
	
	@JsonProperty
    private String displayName;
	
	@JsonProperty
    private int score;
	
	public TopUser() {
		
	}
	
	public TopUser(int index, String username, String displayName, int score) {
		this.index = index;
		this.username = username;
		this.displayName = displayName;
		this.score = score;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
