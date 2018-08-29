package com.finix.action;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.finix.bean.GovernmentUserBean;
import com.finix.dao.impl.GovernmentUserDaoImpl;
import com.finix.manager.IGovernmentUserManager;
import com.finix.manager.impl.GovernmentUserManagerImpl;
import com.opensymphony.xwork2.ActionSupport;

public class GovenmentUserAction extends ActionSupport implements
ServletResponseAware, ServletRequestAware,SessionAware
{
	
	boolean sessionCheck = false;
	LoginAction loginAction = new LoginAction();
	HttpServletRequest request;
	HttpServletResponse response;
	
	GovernmentUserBean governmentUserBean = new GovernmentUserBean();
	IGovernmentUserManager governmentUserManager = new GovernmentUserManagerImpl();

	final Logger debugLog = Logger.getLogger("debugLogger");
	final Logger logger = Logger.getLogger("reportsLogger");
	long startTime;
	long endTime;
	long diffTime;
	
	private void catchMethodLogger(String str, Exception e) 
	{
		long startTime1;
		startTime1 = System.currentTimeMillis(); 
	 	
		Date date = new Date(startTime1);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        
        
        debugLog.error("***************" );
        debugLog.error("Theater Owner Action :Method Name "+str );

        debugLog.error("Exception Occured Time " +dateFormatted);
        
        debugLog.error("Type of Exception"+e);
        debugLog.error("***************" );
		
	}

	private void endMethodLogger(String str) 
	{
			  
		  	
		  	endTime = System.currentTimeMillis();
		  
		  	Date date1 = new Date(endTime);
	        DateFormat formatter1 = new SimpleDateFormat("HH:mm:ss:SSS");
	        String dateFormatted2 = formatter1.format(date1);
	        
	        diffTime = endTime - startTime;
	        
	        int milliseconds = (int) ((diffTime%1000)/100);
            int seconds = (int) ((diffTime/1000)%60);
            int minutes = (int) ((diffTime/(1000*60))%60);
            int hours = (int) ((diffTime/(1000*60*60))%24);
             
            String diffStr = hours+":"+minutes+":"+seconds+":"+milliseconds;
     
	        logger.info("Method End Time "+dateFormatted2);
	        
	        logger.info("Method Difference Time "+diffStr);
	        
	        logger.info("******************");
			
	}
	
	private void startMethodLogger(String str)
	{
		startTime = System.currentTimeMillis(); 
	 	
		Date date = new Date(startTime);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        
        logger.info("******************");
        logger.info("Theater Owner Action : Method Start Time " +dateFormatted);
        
        logger.info("Method Name "+str );
	}

	
	//getGovernmentUserDashboardDetail created by ramya - 12-08-18
	public String getGovernmentUserDashboardDetail(){
		String str = "getGovernmentUserDashboardDetail";
		startMethodLogger(str);
		String status = "government-user-error";
		try{
			
			status = SUCCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}
	
	
	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
