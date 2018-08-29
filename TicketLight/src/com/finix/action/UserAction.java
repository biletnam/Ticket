package com.finix.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

import com.finix.bean.TemplateBean;
import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.UserBean;
import com.finix.manager.ILoginManager;
import com.finix.manager.IUserManager;
import com.finix.manager.impl.LoginManagerImpl;
import com.finix.manager.impl.UserManagerImpl;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sendgrid.SendGrid;
import com.sun.mail.smtp.SMTPMessage;

public class UserAction extends ActionSupport implements
ServletResponseAware, ServletRequestAware,SessionAware
{
	
	boolean sessionCheck = false;
	LoginAction loginAction = new LoginAction();
	HttpServletRequest request;
	HttpServletResponse response;
	
	UserBean userBean = new UserBean();
	IUserManager userManager = new UserManagerImpl();
	TemplateBean templateBean = new TemplateBean();
	ILoginManager loginManager = new LoginManagerImpl();
	Map<Integer, String> citymap = new HashMap<Integer, String>();
    TheaterOwnerBean theaterOwnerBean = new TheaterOwnerBean();
	Map<Integer, byte[]> moviePosterMap = new HashMap<Integer,byte[]>();
	Map<Integer, byte[]> movieBgPosterMap = new HashMap<Integer,byte[]>();
	private byte[] itemImage;
	public InputStream inputStream = null;
	private String content_type;
	
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
	
	
	//setUserRegisterSubmit - created  by hemalatha - 14-08-2018
	public String setUserRegisterSubmit()
	{
		String status = "user-error";
		String str = "setUserRegisterSubmit";
		startMethodLogger(str);
		try
		{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			
			userBean.setName(request.getParameter("nameVal"));
			userBean.setEmail(request.getParameter("emailVal"));
			userBean.setMobile(request.getParameter("mobileVal"));
			userBean.setPassword(request.getParameter("pwdVal"));
			
			userBean = userManager.setUserRegisterDetails(userBean);
			
			if(userBean.getStatus().equals("success"))
			{
				confirmSMS();
			}
			
			convertResultToJSON(userBean, response);
			status = SUCCESS;
			
		}
		catch (Exception e)
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
	
	private String confirmSMS() 
	{
		String status = "user-error";
		try
		{
			String contact = userBean.getMobile();
    		String msg="Dear Candidates, Your Mobile verfication PIN No is "+userBean.getSms_pin();
    		String user="finixdnd";String pass="finixdnd";
    		String encodemsg=URLEncoder.encode(msg, "UTF-8").replace("+", "%20");
            String requestUrl  = "http://hp.dial4sms.com/SendSMS/sendmsg.php?uname="+user+"&pass="+pass+"&send=FXALUM&dest="+contact+"&msg="+encodemsg;

            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setDoOutput(false);
            connection.setDoInput(true);
           
            
            String res=connection.getResponseMessage();
                  

            int code = connection.getResponseCode () ;
            if ( code == HttpURLConnection.HTTP_OK )
            {

            connection.disconnect() ;
		    }
            
            status=SUCCESS;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return status;
	}
	
	
//	/resendConfirmSMS - Created by hemalatha - 20-07-2018
	public String resendConfirmSMS()
	{
		String str = "resendConfirmSMS";
		String status = "theater-owner-error";
		startMethodLogger(str);
		try
		{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			
			userBean.setUser_id(user_id);
			
			userBean = userManager.setSMSDetails(userBean);
			
			
			String contact = userBean.getMobile();
    		String msg="Dear Candidates, Your Mobile verfication PIN No is "+userBean.getSms_pin();
    		String user="finixdnd";String pass="finixdnd";
    		String encodemsg=URLEncoder.encode(msg, "UTF-8").replace("+", "%20");
            String requestUrl  = "http://hp.dial4sms.com/SendSMS/sendmsg.php?uname="+user+"&pass="+pass+"&send=FXALUM&dest="+contact+"&msg="+encodemsg;

            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setDoOutput(false);
            connection.setDoInput(true);
           
            
            String res=connection.getResponseMessage();
            

            int code = connection.getResponseCode () ;
            if ( code == HttpURLConnection.HTTP_OK )
            {

            connection.disconnect() ;
            }
            
            userBean.setStatus("success");
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
	
	
	//setAccountActivation
	public String setAccountActivation()
	{
		String status = "user-error";
		String str = "setAccountActivation";
		startMethodLogger(str);
		try
		{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			
			userBean.setSms_pin(request.getParameter("smspinId"));
			userBean.setUser_id(Integer.parseInt(request.getParameter("userID")));
			
			userBean  = userManager.setAccountActivationDetails(userBean);
			
			convertResultToJSON(userBean, response);
			status = SUCCESS;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}

	
	//forgetPasswordForUser - Created by hemalatha - 16-08-2018
	public String forgetPasswordForUser()
	{
		String status = "user-error";
		String str = "setAccountActivation";
		startMethodLogger(str);
		try
		{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			String msgContent = "";
			
			userBean.setEmail(request.getParameter("email"));
			
			userBean  = userManager.forgetPasswordForUser(userBean);
			
			if(userBean.getStatus().equals("success"))
			{
				String toAddress = userBean.getEmail();
				templateBean =loginManager.getforgotPassword(templateBean);
				
				
				msgContent = templateBean.getTemplate();
				msgContent = msgContent.replace("name", userBean.getName());
				msgContent = msgContent.replace("userEmail", userBean.getEmail());
				msgContent = msgContent.replace("userPassword", userBean.getPassword());
				
				String subject = "Ticket_lite - Forget Password";
				
				sendEmail(msgContent, toAddress, subject);
			}
			
			convertResultToJSON(userBean, response);
			status = SUCCESS;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}
	
	private void sendEmail(String message, String toAddress, String subject) 
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
	}


	//getSearchCategoryDetail created by ramya -16-08-18
	public String getSearchCategoryDetail()
	{
		String status = "user-error";
		String str = "getSearchCategoryDetail";
		startMethodLogger(str);
		try
		{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			
			/*citymap  = userManager.getCityDetail();*/
			int cityId = Integer.parseInt(request.getParameter("cityId"));
			userBean.setCity_id(cityId);
			userBean  = userManager.getSearchCategoryDetail(userBean);
			convertResultToJSON(userBean, response);

			status = SUCCESS;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}

	
   //getCitywiseMovieDetail created by ramya - 18-08-18
	/*public String getCitywiseMovieDetail()
	{
		String status = "user-error";
		String str = "getCitywiseMovieDetail";
		startMethodLogger(str);
		try
		{
		
			userBean = userManager.getCitywiseMovieDetail(userBean);
			
			status = SUCCESS;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}*/
	
	   //getMoviewiseTheaterDetail created by ramya - 18-08-18
	   public String getMoviewiseTheaterDetail()
	   {
			String status = "user-error";
			String str = "getMoviewiseTheaterDetail";
			startMethodLogger(str);
			try
			{
			
				theaterOwnerBean = userManager.getMoviewiseTheaterDetail(theaterOwnerBean);
				
				status = SUCCESS;
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
		}
	   
	 //getCityCinemasDetail created by ramya - 18-08-18
	   public String getCityCinemasDetail()
	   {
			String status = "user-error";
			String str = "getCityCinemasDetail";
			startMethodLogger(str);
			try
			{
			    theaterOwnerBean.setCity_id(theaterOwnerBean.getLocation_id());
				theaterOwnerBean = userManager.getSearchTheaterDetail(theaterOwnerBean);
				theaterOwnerBean = userManager.getMovieBgPosterCountDetail(theaterOwnerBean);

				status = SUCCESS;
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
		}
	
	 //getCityMovieDetail created by ramya - 20-08-18
	   public String getCitywiseAllMoviesDetail()
	   {
			String status = "user-error";
			String str = "getCitywiseAllMoviesDetail";
			startMethodLogger(str);
			try
			{
				theaterOwnerBean = userManager.getCityMovieDetail(theaterOwnerBean);
				theaterOwnerBean = userManager.getMovieBgPosterCountDetail(theaterOwnerBean);

				status = SUCCESS;
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
		}
	   
	   //getTheaterwiseMovieDetail created by ramya - 20-08-18
	   public String getTheaterwiseMovieDetail()
	   {
			String status = "user-error";
			String str = "getTheaterwiseMovieDetail";
			startMethodLogger(str);
			try
			{
			
				theaterOwnerBean = userManager.getTheaterMovieDetail(theaterOwnerBean);
				
				status = SUCCESS;
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
		}
	 
	 //getMasterMoviePoster created by ramya - 20-08-18
		public String getMasterMoviePoster() {
			String status=ERROR;
			try {
			
				int modelId = Integer.parseInt(ServletActionContext.getRequest().getParameter("modelId"));
				theaterOwnerBean.setMaster_movie_id(modelId);
				moviePosterMap = userManager.getMasterMoviePosterImage(theaterOwnerBean);
				if(moviePosterMap.size()>0)
				{
					for (Map.Entry<Integer, byte[]> entry : moviePosterMap.entrySet()) 
					{
			    	 int modelid1=(int)entry.getKey();
						if(modelid1==modelId)
						{
							itemImage = (byte[]) entry.getValue();
							inputStream = new ByteArrayInputStream(itemImage);
							setContent_type("jpg");
							status=SUCCESS;
						}
					}
				}
				status=SUCCESS;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return status;
		}
		
		//getMasterMovieBgPoster created by ramya - 20-08-18
		public String getMasterMovieBgPoster() {
			String status=ERROR;
			try {
			
				int modelId = Integer.parseInt(ServletActionContext.getRequest().getParameter("modelId"));
				theaterOwnerBean.setMaster_movie_id(modelId);
				movieBgPosterMap = userManager.getMasterMovieBgPosterImage(theaterOwnerBean);
				if(movieBgPosterMap.size()>0)
				{
					for (Map.Entry<Integer, byte[]> entry : movieBgPosterMap.entrySet()) 
					{
			    	 int modelid1=(int)entry.getKey();
						if(modelid1==modelId)
						{
							itemImage = (byte[]) entry.getValue();
							inputStream = new ByteArrayInputStream(itemImage);
							setContent_type("jpg");
							status=SUCCESS;
						}
					}
				}
				status=SUCCESS;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return status;
		}
		
	    //dateFilterInTheaterwiseMovie created by ramya - 21-08-18
		public String dateFilterInTheaterwiseMovie()
		   {
				String status = "user-error";
				String str = "dateFilterInTheaterwiseMovie";
				startMethodLogger(str);
				try
				{
				
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
					
					int theater_id = Integer.parseInt(request.getParameter("theaterId"));
					int date_id = Integer.parseInt(request.getParameter("dateId"));
                    theaterOwnerBean.setTheater_id(theater_id);
                    theaterOwnerBean.setDate_id(date_id);
					
					theaterOwnerBean = userManager.getTheaterMovieDetail(theaterOwnerBean);
					
					convertResultToJSON(theaterOwnerBean, response);  
					
					status = SUCCESS;
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
	
		
		  //dateFilterInMoviewiseTheater created by ramya - 21-08-18
				public String dateFilterInMoviewiseTheater()
				   {
						String status = "user-error";
						String str = "dateFilterInMoviewiseTheater";
						startMethodLogger(str);
						try
						{
						
							HttpServletRequest request = ServletActionContext.getRequest();
							HttpServletResponse response = ServletActionContext.getResponse();
							
							int master_movie_id = Integer.parseInt(request.getParameter("movieId"));
							int date_id = Integer.parseInt(request.getParameter("dateId"));
		                    theaterOwnerBean.setMaster_movie_id(master_movie_id);
		                    theaterOwnerBean.setDate_id(date_id);
							
							theaterOwnerBean = userManager.getMoviewiseTheaterDetail(theaterOwnerBean);
							
							convertResultToJSON(theaterOwnerBean, response);  
							
							status = SUCCESS;
							
						}
						catch (Exception e)
						{
							e.printStackTrace();
							catchMethodLogger(str, e);
						}
						endMethodLogger(str);
						return status;
					}
		
		
		private void convertResultToJSON(TheaterOwnerBean theaterOwnerBean,
				HttpServletResponse response) throws IOException {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
			String temp = new Gson().toJson(theaterOwnerBean);
			out.print(temp);
			out.close();
		}
		
		
	//getLoginStatusDetail - Created by hemalatha - 25-08-2018
		public String getLoginStatusDetail()
		{
			String status = "user-error";
			String str = "getLoginStatusDetail";
			startMethodLogger(str);
			try
			{
				response = ServletActionContext.getResponse();
				sessionCheck = loginAction.end_user_SessionCheck();
				if (sessionCheck) 
				{
					userBean.setStatus("Logged In");
				}
				else
				{
					userBean.setStatus("Logged Out");
				}
				
				convertResultToJSON(userBean, response);
				
				status = SUCCESS;
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			
		}
		
		public String getTheatreLayout() {
			String status=ERROR;
			String str = "getTheatreLayout";
			startMethodLogger(str);
			try {
				
				
				System.out.println(userBean.getTheater_id());
				System.out.println(userBean.getScreen_id());
				System.out.println(userBean.getMovie_details_id());
				System.out.println(userBean.getShow_timing_id());
				System.out.println(userBean.getShow_detail_id());
				
				//userBean=userManager.getTheatreLayoutUserView(userBean);
				
				userBean = userManager.getOrderSummaryDetails(userBean);
								
				status=SUCCESS;
				
			}
			catch(Exception e) {
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			
		}
		
		
		public String getUserTheatreLayoutView() {
			String status=ERROR;
			String str = "getUserTheatreLayoutView";
			startMethodLogger(str);
			try {
				
				 request = ServletActionContext.getRequest();
				  response = ServletActionContext.getResponse();
				  
				  int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
					int scrId = Integer.parseInt(request.getParameter("scrId"));
					int showTimeId=Integer.parseInt(request.getParameter("showTimeId"));
					int movieDetailsId=Integer.parseInt(request.getParameter("movieDetailsId"));
					int showDetailId=Integer.parseInt(request.getParameter("showDetailId"));
					
					userBean.setTheater_id(theaterIdVal);
					userBean.setScreen_id(scrId);
					userBean.setShow_timing_id(showTimeId);
					userBean.setMovie_details_id(movieDetailsId);
					userBean.setShow_detail_id(showDetailId);
					
					userBean = userManager.getScreenwiseSeatingDetails(userBean);
					
					convertResultToJSON1(userBean, response);
				
				status=SUCCESS;
			}
			catch(Exception e) {
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
		}
		
		
		private void convertResultToJSON1(UserBean userBean,
				HttpServletResponse response) throws IOException {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
			String temp = new Gson().toJson(userBean);
			out.print(temp);
			out.close();
		}
		
		public String userTicketPaymentSubmit() {
			String status=ERROR;
			try {
				
				Map<String, Object> sessionMap = ActionContext.getContext().getSession();

				Object cityId=sessionMap.get("city_id");
     			int id = Integer.valueOf((String) cityId);
				theaterOwnerBean.setCity_id(id);
			
				status=SUCCESS;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return status;
		}
		
		
		public String getUserTicketSoldDetail() {
			Map<String, Object> sessionMap =ActionContext.getContext().getSession();
			String status = ERROR;
			String str = "getUserTicketSoldDetail";
			startMethodLogger(str);
			try {
				  request = ServletActionContext.getRequest();
				  response = ServletActionContext.getResponse();
				  
					
					sessionCheck = loginAction.end_user_SessionCheck();
					if(sessionCheck)
					{
						Object user_id = sessionMap.get("user_id");
						int user_id_val = (int) user_id;
						userBean.setUser_id(user_id_val);
						
						 
					  /*  Object ticket_counter_person_id=sessionMap.get("ticket_counter_person_id");
					    int id = (int) ticket_counter_person_id;
						ticketCounterBean.setTicket_counter_person_id(id);
						theaterOwnerBean.setTicket_counter_person_id(id);
							
						Object role=sessionMap.get("role");
						String roleId = (String) role;
						theaterOwnerBean.setUser_role(roleId);*/
							
						 
						int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
						int scrId = Integer.parseInt(request.getParameter("scrId"));
						int showTimeId=Integer.parseInt(request.getParameter("showTimeId"));
						int movieDetailsId=Integer.parseInt(request.getParameter("movieDetailsId"));
						int showDetailId=Integer.parseInt(request.getParameter("showDetailId"));
						String seatStr=request.getParameter("seatStr");
						String dateId=request.getParameter("dateId");
						String TicketPrice=request.getParameter("totalPrice");
						
						userBean.setTheater_id(theaterIdVal);
						userBean.setScreen_id(scrId);
						userBean.setShow_timing_id(showTimeId);
						userBean.setMovie_details_id(movieDetailsId);
						userBean.setShow_detail_id(showDetailId);
						userBean.setBooking_seat(seatStr);
						userBean.setBooking_date(dateId);
						userBean.setTotal_price(TicketPrice);
						
						userBean = userManager.getTicketSoldDetail(userBean);
						
						convertResultToJSON(userBean, response);
				
						status=SUCCESS;
					}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
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

	public Map<Integer, String> getCitymap() {
		return citymap;
	}

	public void setCitymap(Map<Integer, String> citymap) {
		this.citymap = citymap;
	}

	public TheaterOwnerBean getTheaterOwnerBean() {
		return theaterOwnerBean;
	}

	public void setTheaterOwnerBean(TheaterOwnerBean theaterOwnerBean) {
		this.theaterOwnerBean = theaterOwnerBean;
	}

	public Map<Integer, byte[]> getMoviePosterMap() {
		return moviePosterMap;
	}

	public void setMoviePosterMap(Map<Integer, byte[]> moviePosterMap) {
		this.moviePosterMap = moviePosterMap;
	}

	public byte[] getItemImage() {
		return itemImage;
	}

	public void setItemImage(byte[] itemImage) {
		this.itemImage = itemImage;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public Map<Integer, byte[]> getMovieBgPosterMap() {
		return movieBgPosterMap;
	}

	public void setMovieBgPosterMap(Map<Integer, byte[]> movieBgPosterMap) {
		this.movieBgPosterMap = movieBgPosterMap;
	}
	
	

}
