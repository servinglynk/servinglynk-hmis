package com.servinglynk.hive.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import com.servinglynk.hmis.warehouse.Logging;
import com.servinglynk.hmis.warehouse.Properties;

public class SyncPostgresProcessor extends Logging{

    public static int batchSize = 1000;
    private static Connection connection = null;
    static final Logger logger = Logger.getLogger(SyncPostgresProcessor.class);
    static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + Properties.POSTGRESQL_DB_HOST + ":" + Properties.POSTGRESQL_DB_PORT + "/" + Properties.POSTGRESQL_DB_DATABASE,
                    Properties.POSTGRESQL_DB_USERNAME,
                    Properties.POSTGRESQL_DB_PASSWORD);
        }
        if (connection.isClosed()) {
            throw new SQLException("connection could not initiated");
        }
        return connection;
    }
    
   
    public static Map<String,List<String>> getProjects() throws Exception{
        Map<String,List<String>> maps = new HashedMap();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try{
        	List<String> projects = new ArrayList<>();
            connection = getConnection();
            statement = connection.prepareStatement("select value as projectid, report_config_id as reportconfig from base.report_config_param where key='PROJECT_ID' and report_config_id in ( select id from base.report_config where status='INITIAL' limit 1 )");
            resultSet = statement.executeQuery();
            String reportConfig = "";
            while (resultSet.next()){
            	String projectId = resultSet.getString("projectid");
            	reportConfig = resultSet.getString("reportconfig"); 
            	projects.add(projectId);
            }
            maps.put(reportConfig, projects);
        }catch (Exception ex){
            throw ex;
        }
        return maps;
    }

   
}