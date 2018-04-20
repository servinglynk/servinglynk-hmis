package com.servinglynk.hive.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.servinglynk.hmis.warehouse.Properties;

public class ImpalaConnection {
    private static String driverName = "com.cloudera.impala.jdbc41.Driver";
    private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
	     try {
	            // Register driver and create driver instance
	            Class.forName(driverName);
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }

	        // get connection
	        System.out.println("connecting to db");
		if (connection == null) {
			connection = DriverManager.getConnection(Properties.IMPALA_DRIVER_URL, Properties.IMPALA_USERNAME, Properties.IMPALA_PASSWORD);
	        // create statement

		}
		if (connection.isClosed()) {
			throw new SQLException("connection could not initiated");
		}
		return connection;
	}
}