package com.bgatebco.api;

import javax.validation.Valid;

import io.dropwizard.Configuration;

public class BcoApiConfiguration extends Configuration{
	
	@Valid
	private DbConfigConfiguration db_config;
	
	@Valid
	private ClientDefaultConfiguration default_client_config;

	public DbConfigConfiguration getDb_config() {
		return db_config;
	}

	public void setDb_config(DbConfigConfiguration db_config) {
		this.db_config = db_config;
	}

	public ClientDefaultConfiguration getDefault_client_config() {
		return default_client_config;
	}

	public void setDefault_client_config(
			ClientDefaultConfiguration default_client_config) {
		this.default_client_config = default_client_config;
	}
	
}
