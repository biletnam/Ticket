package com.finix.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.finix.bean.GovernmentUserBean;
import com.finix.dao.IGovernmentUserDao;
import com.finix.dao.utils.ConnectionManager;

public class GovernmentUserDaoImpl implements IGovernmentUserDao{

private static ConnectionManager connectionManager = ConnectionManager.getConnectionManager();
	
	final Logger debugLog = Logger.getLogger("debugLogger");

	
	private void catchMethodLogger(String str, Exception e) 
	{
		long startTime1;
		startTime1 = System.currentTimeMillis(); 
	 	
		Date date = new Date(startTime1);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        
        
        debugLog.error("***************" );
        debugLog.error("Theater Owner Dao Impl :Method Name "+str );

        debugLog.error("Exception Occured Time " +dateFormatted);
        
        debugLog.error("Type of Exception"+e);
        debugLog.error("***************" );
		
	}
	
	
	//getGovernmentUserDetails created by ramya -12-08-18
	public GovernmentUserBean getGovernmentUserDetails(GovernmentUserBean governmentUserBean) throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		int i =1;
		try {
			
			  conn = connectionManager.getConnection();
			  
			  String sql = "{CALL Ticket_TO_getGovernmentUserDetails(?)}";
			  cl = conn.prepareCall(sql);
			  cl.setInt(1, governmentUserBean.getGovernment_user_id());
			  boolean res = cl.execute();
			  while(res)
			  {
					ResultSet rs = cl.getResultSet();
					
					if(i==1)
					{
						while(rs.next())
						{
							 governmentUserBean.setGovernment_user_id(rs.getInt("government_user_id"));
							 governmentUserBean.setGovernment_user_name(rs.getString("government_user_name"));
							 governmentUserBean.setGovernment_user_email(rs.getString("government_user_email"));
							 governmentUserBean.setGovernment_user_mobile(rs.getString("government_user_mobile"));
						}
					}
					
					i++;
					res = cl.getMoreResults();
				}
			  
			
		} catch (Exception e) {
			e.printStackTrace();
			String str = "getGovernmentUserDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return governmentUserBean;
	}

}
