package com.bgatebco.api.dbengine;

import common.jdbc.JdbcConnectionPool;
import common.jdbc.JdbcParameter;

public class DataEngine {

	private static DataEngine INSTANCE;
	/*--------Database Config------------*/
    private String dbAddr;
    private String dbPort;
    private String dbUser;
    private String dbPass;
    private String dbName;
    private int maxConn = 50;
    private int clearPeriod;
    /*--------DAO------------------------*/
    public DaoClientBuildManager clientBuildDao;
    public DaoEventManager eventManagerDao;
    public DaoUserManager userManagerDao;
    /*---------Object Fields-------------*/
    private JdbcConnectionPool pool;
    private JdbcParameter parameter;
    
    public static DataEngine getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataEngine();
        }
        return INSTANCE;
    }
    
    public void start(int maxConn, int clearPeriod) {
    	try {
    		this.dbAddr = DbConfig.host;
            this.dbPort = DbConfig.port;
            this.dbUser = DbConfig.user;
            this.dbPass = DbConfig.password;
            this.dbName = DbConfig.dbname;
            this.maxConn = maxConn;
            this.clearPeriod = clearPeriod;

            this.parameter = new JdbcParameter();
            this.parameter.setUrl("jdbc:mysql://" + dbAddr + ":" + dbPort + "/" + dbName);
            this.parameter.setUsername(dbUser);
            this.parameter.setPassword(dbPass);
            this.parameter.setMaxConn(maxConn);
            this.parameter.setClearPeriod(clearPeriod);

            this.pool = new JdbcConnectionPool(parameter);
            
            this.clientBuildDao = new DaoClientBuildManager(pool);
            this.eventManagerDao = new DaoEventManager(pool);
            this.userManagerDao = new DaoUserManager(pool);
            //
            System.out.println("Database : " + this.parameter.getUrl());
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    }
	
}
