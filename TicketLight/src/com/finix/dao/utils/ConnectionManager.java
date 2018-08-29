package com.finix.dao.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager 
{

	 private static ConnectionManager connectionManager = new ConnectionManager();
	  private DataSource dataSource = null;
	  private Context context = null;
	  
	  private int numberOfOpenConnections = 0;

	  public static ConnectionManager getConnectionManager()
	  {
	    return connectionManager;
	  }

	  private ConnectionManager() {
	   
	    try {
	    	
	      this.context = new InitialContext();
	      Context initContext  = (Context) this.context.lookup("java:/comp/env");
	     this.dataSource = ((DataSource)initContext.lookup("jdbc/assessmentx"));
	    } catch (NamingException ex) {
	      ex.printStackTrace();
	    }
	  }

	  public Connection getConnection()
	    throws Exception
	  {
	    Connection connection = null;
	    try {
	  
			
			String url = "jdbc:mysql://192.168.0.113:3306/ticket_lite";
			String user = "root";
			String password = "root";
			
		/*	
	    	String url = "jdbc:mysql://132.148.140.60:3306/ticket_lite";
			String user = "root";
			String password = "655N_9b+nQ";*/
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			this.numberOfOpenConnections += 1;
		      
	      return connection;
	      } 
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    } 
	   
	    throw new Exception("couldn't Open the connection");
	  }

	  public void releaseConnection(Connection connection)
	    throws Exception
	  {
	    try
	    {
	      connection.close();
	      this.numberOfOpenConnections -= 1;
	      
	    }
	    catch (SQLException ex) {
	      throw new Exception("couldn't close the connection");
	    }
	  }



}
