package com.bgatebco.api;

import javax.validation.constraints.NotNull;

public class DbConfigConfiguration {

	@NotNull
    private String host;
	
	@NotNull
    private String port;
	
	@NotNull
	private String user;
	
	@NotNull
	private String password;
	
	@NotNull
	private String dbname;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	
}
