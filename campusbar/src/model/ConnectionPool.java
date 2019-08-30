package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class ConnectionPool {


	public static Connection getConnection() throws SQLException {
		if (datasource == null) {
			PoolProperties p = new PoolProperties();
			p.setUrl("jdbc:mysql://remotemysql.com:3306/fCDBBQwrx0?serverTimezone=" + TimeZone.getDefault().getID());
			p.setDriverClassName("com.mysql.cj.jdbc.Driver");
			//p.setUrl("jdbc:mysql://localhost:3306/campusbar?serverTimezone=" + TimeZone.getDefault().getID());
			//p.setUsername("root");
			//p.setPassword("sasa");
			p.setUsername("fCDBBQwrx0");
			p.setPassword("VVAoJuTmci");
			p.setMaxActive(150);
			p.setInitialSize(10);
			p.setMinIdle(10);
			p.setRemoveAbandonedTimeout(1200);
			p.setRemoveAbandoned(true);
			datasource = new DataSource();
			datasource.setPoolProperties(p);
		}
		return datasource.getConnection();
	}
	
	private static DataSource datasource;

}
