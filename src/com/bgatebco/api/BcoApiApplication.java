package com.bgatebco.api;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.bgatebco.api.dbengine.DataEngine;
import com.bgatebco.api.dbengine.DbConfig;
import com.bgatebco.api.filter.DateRequiredFeature;
import com.bgatebco.api.filter.SimpleHealthCheck;
import com.bgatebco.api.resources.ClientConfigResource;
import com.bgatebco.api.resources.EventResource;
import com.bgatebco.api.resources.UserResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Hello world!
 *
 */
public class BcoApiApplication extends Application<BcoApiConfiguration> {
	
	public static void main(String[] args) {
		try {
			new BcoApiApplication().run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
    public String getName() {
        return "bco-api";
    }
	
	@Override
    public void initialize(Bootstrap<BcoApiConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
    }
	
	@Override
	public void run(BcoApiConfiguration configuration, Environment environment) throws Exception {
		// TODO Auto-generated method stub
		environment.healthChecks().register("bco-api", new SimpleHealthCheck());
		environment.jersey().register(DateRequiredFeature.class);
		environment.jersey().register(RolesAllowedDynamicFeature.class);
		
		environment.jersey().register(new ClientConfigResource(configuration.getDefault_client_config()));
		environment.jersey().register(new EventResource());
		environment.jersey().register(new UserResource());
		DbConfig.dbname = configuration.getDb_config().getDbname();
		DbConfig.host = configuration.getDb_config().getHost();
		DbConfig.password = configuration.getDb_config().getPassword();
		DbConfig.port = configuration.getDb_config().getPort();
		DbConfig.user = configuration.getDb_config().getUser();
		DataEngine.getInstance().start(50, 50);
	}
}
