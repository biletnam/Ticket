package com.finix.action;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.finix.bean.GovernmentUserBean;
import com.finix.bean.LoginBean;
import com.finix.bean.TemplateBean;
import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.TicketCounterBean;
import com.finix.bean.UserBean;
import com.finix.manager.ILoginManager;
import com.finix.manager.impl.LoginManagerImpl;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sendgrid.SendGrid;
import com.sun.mail.smtp.SMTPMessage;

import org.json.JSONObject;

public class LoginAction extends ActionSupport implements
ServletResponseAware, ServletRequestAware,SessionAware{
	
	private static final long serialVersionUID = 1L;
	private SessionMap<String, Object> sessionMap;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	ILoginManager loginManager = new LoginManagerImpl();
	TemplateBean templateBean = new TemplateBean();
	
	UserBean userBean = new UserBean();
	TheaterOwnerBean theaterOwnerBean = new TheaterOwnerBean();
	TicketCounterBean ticketCounterBean = new TicketCounterBean();
	GovernmentUserBean governmentUserBean = new GovernmentUserBean();
	LoginBean loginBean = new LoginBean();
	Map<String, Object> profileMap = new HashMap<String, Object>();
	
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
	
	//setLoginDetails
	public String setLoginDetails()
	{
		String str = "setLoginDetails";
		String status = "theater-owner-error";
		startMethodLogger(str);
		try
		{
			profileMap = loginManager.login(loginBean);
			if (profileMap.get("status").equals("success"))
			{
				if(profileMap.get("user")!=null)
				{
					if (profileMap.get("user").equals("theater-owner")) 
					{
						setTheaterOwnerBean((TheaterOwnerBean) profileMap.get("theaterOwnerBean"));
						sessionMap.put("role","theater-owner");
						sessionMap.put("theaterOwnerBean", getTheaterOwnerBean());
						sessionMap.put("theater_owner_id", theaterOwnerBean.getTheater_owner_id());
						sessionMap.put("email", theaterOwnerBean.getTheater_owner_email());
						sessionMap.put("name", theaterOwnerBean.getTheater_owner_first_name());
						sessionMap.put("nav_bar_screen_status", theaterOwnerBean.getNavBarScreenStatus());
						sessionMap.put("nav_bar_user_status", theaterOwnerBean.getNav_bar_user_status());
						sessionMap.put("nav_bar_movie_status", theaterOwnerBean.getNav_bar_movie_status());

						status = "theater-owner";
					}
					else if (profileMap.get("user").equals("ticket-counter-person")) {
						
						setTicketCounterBean((TicketCounterBean) profileMap.get("ticketCounterBean"));
						sessionMap.put("role","ticket-counter-person");
						sessionMap.put("ticketCounterBean", getTicketCounterBean());
						sessionMap.put("ticket_counter_person_id", ticketCounterBean.getTicket_counter_person_id());
						sessionMap.put("name", ticketCounterBean.getTicket_counter_person_name());
						sessionMap.put("email", ticketCounterBean.getTicket_counter_person_email());
						sessionMap.put("employee_owner_id", ticketCounterBean.getEmployee_working_theater_owner_id());
						sessionMap.put("employee_working_theater_id", ticketCounterBean.getEmployee_working_theater_id());
						
						status = "ticket-counter-person";
					}
                    else if (profileMap.get("user").equals("government-user")) {
						
						setGovernmentUserBean((GovernmentUserBean) profileMap.get("governmentUserBean"));
						sessionMap.put("role","government-user");
						sessionMap.put("governmentUserBean", getGovernmentUserBean());
						sessionMap.put("government_user_id", governmentUserBean.getGovernment_user_id());
						sessionMap.put("name", governmentUserBean.getGovernment_user_name());
						sessionMap.put("email", governmentUserBean.getGovernment_user_email());
						
						status = "government-user";
					}
                    else if(profileMap.get("user").equals("theater-owner-activation-failed"))
					{
						setTheaterOwnerBean((TheaterOwnerBean) profileMap.get("theaterOwnerBean"));
						status = "theater-owner-activation-failed";
					}
					else if(profileMap.get("user").equals("ticket-counter-person-activation-failed"))
					{
						addActionError("Invalid Username and Password");
						status = "ticket-counter-person-activation-failed";
					}
					else if(profileMap.get("user").equals("government-user-activation-failed"))
					{
						addActionError("Invalid Username and Password");
						status = "government-user-activation-failed";
					}
			}
			
		}
		else if(profileMap.get("status").equals("failure"))
		{
			if (profileMap.get("user").equals("theater-owner")) 
			{
				addActionError("Invalid Username and Password");
				status = "theater-owner-error";
			}
			else if (profileMap.get("user").equals("ticket-counter-person")) {
				addActionError("Invalid Username and Password");
				status = "ticket-counter-error";
			}
			else if (profileMap.get("user").equals("government-user")) {
				addActionError("Invalid Username and Password");
				status = "government-user-error";
			}
			
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}
	
	public boolean user_SessionCheck()
	{
		boolean sessionResult=false;
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session!=null && session.getAttribute("role")!=null )
        { 
        	sessionResult=true;
        }
		return sessionResult;
	}
	
	public boolean end_user_SessionCheck()
	{
		boolean sessionResult=false;
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session!=null && session.getAttribute("role")=="endUser" )
        { 
        	sessionResult=true;
        }
		return sessionResult;
	}
	
	
	
	//ragavan -24/7/18--getPasswordDetails
		public String getPasswordDetails()
		{
			String str = "setLoginDetails";
			String status = "theater-owner-error";
			startMethodLogger(str);
			try
			{   
				String msgContent = "";
				loginBean =loginManager.getPasswordDetails(loginBean);
				if(loginBean.getStatus().equals("success"))
				{
				String toAddress=loginBean.getEmail();
				templateBean =loginManager.getforgotPassword(templateBean);
				
				
				msgContent = templateBean.getTemplate();
				msgContent = msgContent.replace("name", loginBean.getName());
				msgContent = msgContent.replace("userEmail", loginBean.getEmail());
				msgContent = msgContent.replace("userPassword", loginBean.getPassword());
				
				String subject = "Ticket_lite - Forget Password";
				
				sendEmail(msgContent, toAddress, subject);
				
				addActionMessage("Password has been sent to your email Id");
				
				int roleId = loginBean.getRole_xid();
				
				switch (roleId) {
				case 1:
					
					status = "theater-owner-success";
					
					break;

				case 2:
					
					status = "ticket-counter-success";

					break;
					
				case 3:
					
					status = "government-user-success";

					break;
				}
				
				}
				else if(loginBean.getStatus().equals("email_invalid"))
				{
					addActionError("Given Email Id should not be registered");
					
					int roleId = loginBean.getRole_xid();
					
					switch (roleId) {
					case 1:
						
						status = "theater-owner-activation";
						
						break;

					case 2:
						
						status = "ticket-counter-activation";

						break;
						
					case 3:
						
						status = "government-user-activation";

						break;
					}
					
				}
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
		}
		
		
		private String sendEmail(String message, String toAddress, String subject) 
		{

			String status=ERROR;
			try
			{
				boolean sendStatus=false;
			 
				String from_email= "way2jobz@finix.co.in";
				String server="finix.co.in";
			  
				
				if(server != null) 
				
			{
				Properties props = System.getProperties();
				props.put("mail.smtp.host",server);
				props.put("mail.smtp.from", from_email);
				Session session = Session.getInstance(props);

				SMTPMessage message1 = new SMTPMessage(session);
				message1.setNotifyOptions(-1);
				message1.setReturnOption(SMTPMessage.RETURN_FULL);
				message1.setSendPartial(true);
				Address fromAddressList = new InternetAddress(from_email);
				message1.setFrom(fromAddressList);
				Address toAddresses = new InternetAddress(toAddress);
				message1.setRecipient(Message.RecipientType.TO, toAddresses);
				Address replyAddressList[] = { new InternetAddress(from_email) };
				message1.setReplyTo(replyAddressList);
				message1.setSubject(subject);
				

				 
				message1.setContent(message, "text/html");
				message1.addHeader("Return-Path", from_email);
				message1.addHeader("Return-Receipt-To",from_email);
				message1.addHeader("Disposition-Notification-To", from_email);
				message1.setSentDate(new Date());
				Transport.send(message1);
				sendStatus = true;
				
			}
			
				else if(sendStatus == false) 
						
				{
					 SendGrid sendgrid = new SendGrid("toptheexam","admin123");
					    SendGrid.Email email = new SendGrid.Email();
					    
					    email.addTo(toAddress);
					    email.setSubject(subject);
					
					    
					    
					    email.setHtml(message);
					    
					    SendGrid.Response response = sendgrid.send(email);
					    
				}     
				
				 else {
			    	  
			    	  
			    	  final String s = message;
					     final byte[] authBytes = s.getBytes(StandardCharsets.UTF_8);
					     final String encoded = DatatypeConverter.printBase64Binary(authBytes);
			        	//String http = "http://api.emsender.in/campaign_api/campaign";  
			    	    String http="http://api.emsender.in/campaign_api/campaign/format/json";

				HttpURLConnection urlConnection=null;  
			      
				  URL url = new URL(http);  
				    urlConnection = (HttpURLConnection) url.openConnection();
				    urlConnection.setDoOutput(true);   
				    urlConnection.setRequestMethod("POST");  
				    urlConnection.setUseCaches(false);  
				    urlConnection.setConnectTimeout(10000);  
				    urlConnection.setReadTimeout(10000);  
				  
				    urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

				    urlConnection.setRequestProperty("Host", "https://recruit24x7.com/index");
				    urlConnection.connect();  

				    //Create JSONObject here
				    JSONObject jsonParam = new JSONObject();
				    jsonParam.put("username", "email_Finix");
				    jsonParam.put("password","789456");
						jsonParam.put("from", "info@msmejob.com");
					    jsonParam.put("fromName", "info@msmejob.com");
					    jsonParam.put("subject", "msmejob.com - Friends Invite");
				    jsonParam.put("to", toAddress);
				    jsonParam.put("replyTo", "MSMEjob@msmejob.com");
				    jsonParam.put("campaignName", "Test Campaign");
				    jsonParam.put("htmlBody", encoded);
				    jsonParam.put("textBody", ""); 
				    jsonParam.put("listName", "Test Campaign List");
				    jsonParam.put("attachments","" );
				    
				    OutputStream os=urlConnection.getOutputStream();
				    os.write(jsonParam.toString().getBytes("UTF-8"));
				    os.close();
				    
				    int HttpResult =urlConnection.getResponseCode();  
				    
				 }
		status=SUCCESS;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return status;

			
		}
		
	//setTheaterOwnerLogout
		public String setTheaterOwnerLogout()
		{
			String status = ERROR;
			String str = "setLogout";
			startMethodLogger(str);
			try
			{
				sessionMap.clear();
				status = SUCCESS;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
		}
	
		//setTicketCounterLogout created by ramya - 09-08-18
				public String setTicketCounterLogout()
				{
					String status = ERROR;
					String str = "setTicketCounterLogout";
					startMethodLogger(str);
					try
					{
						sessionMap.clear();
						status = SUCCESS;
					}
					catch(Exception e)
					{
						e.printStackTrace();
						catchMethodLogger(str, e);
					}
					endMethodLogger(str);
					return status;
				}
				
	//setLoginDetailsForUser
	public String setLoginDetailsForUser()
	{
		String status = ERROR;
		String str = "setLoginDetailsForUser";
		startMethodLogger(str);
		try
		{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			
			loginBean.setEmail(request.getParameter("loginEmail"));
			loginBean.setPassword(request.getParameter("loginPassword"));
			loginBean.setRole_xid(4);
			
			profileMap = loginManager.login(loginBean);
			if (profileMap.get("status").equals("success"))
			{
				if(profileMap.get("user")!=null)
				{
					if (profileMap.get("user").equals("end-user")) 
					{
						setUserBean((UserBean) profileMap.get("userBean"));
						sessionMap.put("role","endUser");
						sessionMap.put("userBean", getUserBean());
						sessionMap.put("user_id", userBean.getUser_id());
						sessionMap.put("name", userBean.getName());
						sessionMap.put("city_id", request.getParameter("cityId"));
						sessionMap.put("email", userBean.getEmail());
						sessionMap.put("mobile", userBean.getMobile());
						userBean.setStatus("success");
					}
					else if(profileMap.get("user").equals("end-user-activation-failed"))
					{
						setUserBean((UserBean) profileMap.get("userBean"));
						System.out.println(userBean.getUser_id());
						
						userBean.setStatus("actiavtion failed");
					}
				}
			}
			else if(profileMap.get("status").equals("failure"))
			{
				userBean.setStatus("invalid");
			}
			
			convertResultToJSON(userBean, response);
			status = SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}
		
	private void convertResultToJSON(UserBean userBean,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.flush();
		String temp = new Gson().toJson(userBean);
		out.print(temp);
		out.close();
	}
	
	//setUserLogout
	public String setUserLogout()
	{
		String status = ERROR;
		String str = "setUserLogout";
		startMethodLogger(str);
		try
		{
			sessionMap.clear();
			status = SUCCESS;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}
	
				
	@Override
	public void setSession(Map<String, Object> loginmap) 
	{
		sessionMap=(SessionMap)loginmap; 
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Map<String, Object> getProfileMap() {
		return profileMap;
	}

	public void setProfileMap(Map<String, Object> profileMap) {
		this.profileMap = profileMap;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getDiffTime() {
		return diffTime;
	}

	public void setDiffTime(long diffTime) {
		this.diffTime = diffTime;
	}

	public TheaterOwnerBean getTheaterOwnerBean() {
		return theaterOwnerBean;
	}

	public void setTheaterOwnerBean(TheaterOwnerBean theaterOwnerBean) {
		this.theaterOwnerBean = theaterOwnerBean;
	}

	public TicketCounterBean getTicketCounterBean() {
		return ticketCounterBean;
	}

	public void setTicketCounterBean(TicketCounterBean ticketCounterBean) {
		this.ticketCounterBean = ticketCounterBean;
	}

	public GovernmentUserBean getGovernmentUserBean() {
		return governmentUserBean;
	}

	public void setGovernmentUserBean(GovernmentUserBean governmentUserBean) {
		this.governmentUserBean = governmentUserBean;
	}

	public TemplateBean getTemplateBean() {
		return templateBean;
	}

	public void setTemplateBean(TemplateBean templateBean) {
		this.templateBean = templateBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	

}
