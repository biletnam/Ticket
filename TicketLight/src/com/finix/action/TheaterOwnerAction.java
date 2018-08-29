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
import java.util.ArrayList;
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

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

import com.finix.bean.ScreenDetailBean;
import com.finix.bean.TemplateBean;
import com.finix.bean.TheaterDetailBean;
import com.finix.bean.TheaterOwnerBean;
import com.finix.manager.ITheaterOwnerManager;
import com.finix.manager.impl.TheaterOwnerManagerImpl;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sendgrid.SendGrid;
import com.sun.mail.smtp.SMTPMessage;

import org.apache.log4j.Logger;

public class TheaterOwnerAction  extends ActionSupport implements
ServletResponseAware, ServletRequestAware,SessionAware
{
	
	boolean sessionCheck = false;
	LoginAction loginAction = new LoginAction();
	HttpServletRequest request;
	HttpServletResponse response;
	
	TheaterOwnerBean theaterOwnerBean = new TheaterOwnerBean();
	ITheaterOwnerManager theaterOwnerManager = new TheaterOwnerManagerImpl();
	TemplateBean templateBean = new TemplateBean();
	TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
	Map<Integer, String> stateMap = new HashMap<Integer, String>();
	Map<Integer, String> cityMap = new HashMap<Integer, String>();
	Map<Integer, String> districtMap = new HashMap<Integer, String>();
	Map<Integer, String> theaterMap = new HashMap<Integer, String>();
	Map<Integer, String> movieIdMap = new HashMap<Integer, String>();
	Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
	Map<Integer, String> userRoleMap=new HashMap<Integer,String>();
	Map<Integer, String> movieLangMap=new HashMap<Integer,String>();
	Map<Integer, String> movieGenreMap=new HashMap<Integer,String>();
	Map<Integer, String> movieFormatMap=new HashMap<Integer,String>();
	Map<Integer, String> screenMap=new HashMap<Integer,String>();
	Map<Integer, String> movieCertificationMap=new HashMap<Integer,String>();
	Map<Integer, String> taxMap=new HashMap<Integer,String>();
	


	ArrayList<TheaterOwnerBean>screenwiseList=new ArrayList<TheaterOwnerBean>();
	ArrayList<TheaterOwnerBean> theatreOwnerMovieList=new ArrayList<TheaterOwnerBean>();
	ArrayList<TheaterOwnerBean> theaterEmployeeList=new ArrayList<TheaterOwnerBean>();
	ArrayList<TheaterOwnerBean> movieList=new ArrayList<TheaterOwnerBean>();
	Map<Integer, byte[]> movieImgeMap = new HashMap<Integer,byte[]>();
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
	
	
	//getHomePageDetails - Created by hemalatha - 18-07-2018
	public String getHomePageDetails()
	{
		String str = "getHomePageDetails";
		startMethodLogger(str);
		String status = "theater-owner-error";
		try
		{
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
	
	//getRegistrationDetails - Created by hemalatha - 18-07-2018
	public String getRegistrationDetails()
	{
		String str = "getRegistrationDetails";
		startMethodLogger(str);
		String status = "theater-owner-error";
		try
		{
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
	
	//setRegistrationDetails - Created by hemalatha - 18-07-2018
	public String setRegistrationDetails()
	{
		String str = "setRegistrationDetails";
		startMethodLogger(str);
		String status = ERROR;
		try
		{
			theaterOwnerBean = theaterOwnerManager.setRegistrationDetails(theaterOwnerBean);
			
			if(theaterOwnerBean.getStatus().equals("success"))
			{
				confirmMail();
				confirmSMS();
				addActionMessage("Mail and sms pin send to your registered mail ID and mobile");
				status = SUCCESS;
			}
			else
			{
				status = ERROR;
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
	

	private String confirmSMS() 
	{

		String status = "theater-owner-error";
		try
		{
			String contact = theaterOwnerBean.getTheater_owner_mobile();
    		String msg="Dear Candidates, Your Mobile verfication PIN No is "+theaterOwnerBean.getSms_pin();
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

	private void confirmMail() throws Exception 
	{

			String toAddress = theaterOwnerBean.getTheater_owner_email();
			String name = theaterOwnerBean.getTheater_owner_first_name();
			
			templateBean= theaterOwnerManager.getRegistrationMailTemplate(templateBean);
			String message=templateBean.getTemplate();
			message = message.replace("userPin", theaterOwnerBean.getMail_pin());
			message = message.replace("name", name);
			
			String subject = "ticket.com - Account Verification Pin";
			
			sendEmail(message,toAddress,subject);
		
	}

	private String sendEmail(String message, String toAddress, String subject) 
	{
		String status="theater-owner-error";
		try
		{
			boolean sendStatus=false;
		 	String from_email= "way2jobz@finix.co.in";
			String server="finix.co.in";
		  
			
			if(server != null) {
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
	
	//checkTheaterOwnerEmail - created by hemalatha - 18-07-2018
	public String checkTheaterOwnerEmail()
	{
		String str = "checkTheaterOwnerEmail";
		String status = "theater-owner-error";
		startMethodLogger(str);
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			String email = request.getParameter("email");
			String emailStatus = "";
			emailStatus = theaterOwnerManager.checkTheaterOwnerEmail(email);
			if(emailStatus.equals("Available"))
			{
				status = SUCCESS;
			}
			else
			{
				status = "activation-error";
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
	
	//setAccountActivationDetails - Created by hemalatha - 19-07-2018
	public String setAccountActivationDetails()
	{
		String str = "checkTheaterOwnerEmail";
		String status = "theater-owner-error";
		startMethodLogger(str);
		try
		{
			System.out.println(theaterOwnerBean.getTheater_owner_id());
			
			theaterOwnerBean  = theaterOwnerManager.setAccountActivationDetails(theaterOwnerBean);
			
			if(theaterOwnerBean.getStatus().equals("success"))
			{
				addActionMessage("Successfully updated..!");
				status = SUCCESS;
			}
			else
			{
				addActionError("Invalid pin");
				status = "notMatched";
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
	
	
	//resendConfirmMail
	public String resendConfirmMail()
	{
		String str = "resendConfirmMail";
		String status = "theater-owner-error";
		startMethodLogger(str);
		try
		{
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			
			int owner_id = Integer.parseInt(request.getParameter("owner_id"));

			theaterOwnerBean.setTheater_owner_id(owner_id);
			theaterOwnerBean = theaterOwnerManager.setMailDetails(theaterOwnerBean);
			String toAddress=theaterOwnerBean.getTheater_owner_email();
			String name = theaterOwnerBean.getTheater_owner_first_name()+" "+theaterOwnerBean.getTheater_owner_last_name();
			
			templateBean= theaterOwnerManager.getRegistrationMailTemplate(templateBean);
			String message=templateBean.getTemplate();
			message = message.replace("userPin", theaterOwnerBean.getMail_pin());
			message = message.replace("name", name);
			
			String subject = "ticket - Resend Mail Pin";
			
			sendEmail(message, toAddress, subject);
			addActionMessage("Mail pin send to your Mail ID");
			      
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
			
			int owner_id = Integer.parseInt(request.getParameter("owner_id"));

			theaterOwnerBean.setTheater_owner_id(owner_id);
			
			theaterOwnerBean = theaterOwnerManager.setSMSDetails(theaterOwnerBean);
			
			
			String contact = theaterOwnerBean.getTheater_owner_mobile();
    		String msg="Dear Candidates, Your Mobile verfication PIN No is "+theaterOwnerBean.getSms_pin();
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
            
            addActionMessage("SMS pin send to your mobile");
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
	
	
	//getProfileViewPage
		public String getProfileViewPage()
		{
			String str = "getProfileViewPage";
			startMethodLogger(str);
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			String status = "theater-owner-error";
			try
			{
				sessionCheck = loginAction.user_SessionCheck();
				if (sessionCheck) 
				{
					Object theater_owner_id=sessionMap.get("theater_owner_id");
					int id = (int) theater_owner_id;
					theaterOwnerBean.setTheater_owner_id(id);

					theaterOwnerBean = theaterOwnerManager.getProfileViewPage(theaterOwnerBean);
					status = SUCCESS;
				}
				else
				{
					status = "theater-owner-error";
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}			
			return status;
		}
		
		//getEditPage - Created by Nachimuthu 25-07-2018
		public String getEditPage()
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			String str = "getEditPage";
			String status = ERROR;
			startMethodLogger(str);
			try 
			{
				sessionCheck = loginAction.user_SessionCheck();
				if (sessionCheck) 
				{
					Object theater_owner_id=sessionMap.get("theater_owner_id");
					int id = (int) theater_owner_id;
					theaterOwnerBean.setTheater_owner_id(id);
					
					stateMap = theaterOwnerManager.getStateDetail();
					setStateMap(stateMap);
					cityMap=theaterOwnerManager.getCityDetails();
					setCityMap(cityMap);
					districtMap = theaterOwnerManager.getDistrictDetails();
					setDistrictMap(districtMap);
					
					theaterOwnerBean = theaterOwnerManager.getProfileViewPage(theaterOwnerBean);
					status = SUCCESS;
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
		
		//getUpdatePageDetails  - Created by Nachimuthu 25-07-2018
		public String getUpdatePageDetails()
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			String str = "getUpdatePageDetails";
			String status = ERROR;
			startMethodLogger(str);
			try 
			{
				sessionCheck = loginAction.user_SessionCheck();
				if (sessionCheck) 
				{
					Object theater_owner_id=sessionMap.get("theater_owner_id");
					int id = (int) theater_owner_id;
					theaterOwnerBean.setTheater_owner_id(id);
					
					theaterOwnerBean = theaterOwnerManager.getUpdatePageDetails(theaterOwnerBean);
					theaterOwnerBean = theaterOwnerManager.getProfileViewPage(theaterOwnerBean);
					status = SUCCESS;	
				} 
				else
				{
					status = "theater-owner-error";
				}
			}
			catch (Exception e)
			{
				catchMethodLogger(str, e);
				e.printStackTrace();
			}
			endMethodLogger(str);
			return status;
		}
		
	//profile_images
		public String profile_images() throws Exception
		{
			String status="theater-owner-error";
			String str = "profile_images";
			startMethodLogger(str);
			try
			{
				int modelId = Integer.parseInt(ServletActionContext.getRequest().getParameter("modelId"));
				theaterOwnerBean.setTheater_owner_id(modelId);
				imageMap = theaterOwnerManager.profile_images(theaterOwnerBean);
				if(imageMap.size()>0)
				{
			    for (Map.Entry<Integer, byte[]> entry : imageMap.entrySet()) 
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
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return status;
		}	
		
		//checkOldPasswordAvailable
		public String checkOldPasswordAvailable()
		{
			String str = "checkOldPasswordAvailable";
			startMethodLogger(str);
			String status="theater-owner-error";
			Map<String,Object> sessionMap = ActionContext.getContext().getSession();
			try
			{
				Object theater_owner_id=sessionMap.get("theater_owner_id");
				int id = (int)theater_owner_id;
				theaterOwnerBean.setTheater_owner_id(id);
				
				sessionCheck = loginAction.user_SessionCheck();
				if(sessionCheck==true)
				{
				HttpServletResponse response = ServletActionContext.getResponse();
				request = ServletActionContext.getRequest();
				
				String oldPassword = request.getParameter("oldPassword");
				status = theaterOwnerManager.checkOldPasswordAvailable(oldPassword,theaterOwnerBean);
				if (status.equals("match"))
					status = "match";
				else
					status = "misMatch";
				
				convertResultToJSON(status,response);

				}
				else
				{
					status = "theater-owner-error";
				}
			}
			catch(Exception e)
			{
				catchMethodLogger(str,e);
				e.printStackTrace();
			}
			endMethodLogger(str);
			return status;
		}
		
		private void convertResultToJSON(String status,
				HttpServletResponse response) throws IOException 
		{
				response = ServletActionContext.getResponse();
			    response.setContentType("text/html;charset=UTF-8");
			    PrintWriter out = response.getWriter();
			    out.flush();
			   // String temp = new Gson().toJson(str);			    
			    out.print(status);
			    out.close();
			  }
		
		
		//updatePasswordDetails
			public String updatePasswordDetails()
			{
				String str = "updatePasswordDetails";
				startMethodLogger(str);
				String status=ERROR;
				try
				{
					sessionCheck = loginAction.user_SessionCheck();
					request = ServletActionContext.getRequest();
					  
						Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							
							theaterOwnerBean = theaterOwnerManager.updatePasswordDetails(theaterOwnerBean);
							if(theaterOwnerBean.getStatus().equals("success"))
							{
						    	addActionMessage("Your password Updated Successfully");
						    }
						    else
						    {
						    	addActionError("Old password Doesn't Matched");
						    }
					    	status=SUCCESS;
						}
					   else
					   {
						   status = "theater-owner-error";
					   }
				}
				catch(Exception e)
				{
					catchMethodLogger(str,e);
					e.printStackTrace();
				}
				endMethodLogger(str);
				return status;
			}
			
			//getStateWiseCityDetails
			public String getStateWiseCityDetails()
			{
				String str = "getStateWiseCityDetails";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				try
				{
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
					
					int state_id = Integer.parseInt(request.getParameter("stateId"));
					
					theaterOwnerBean.setState_id(state_id);
					
					theaterOwnerBean = theaterOwnerManager.getStateWiseCityDetails(theaterOwnerBean);
					
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
			
			
			//getDistrictWiseCityDetails
			public String getDistrictWiseCityDetails()
			{
				String str = "getStateWiseCityDetails";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				try
				{
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
					
					int state_id = Integer.parseInt(request.getParameter("stateId"));
					
					theaterOwnerBean.setDistrict_id(state_id);
					
					theaterOwnerBean = theaterOwnerManager.getDistrictWiseCityDetails(theaterOwnerBean);
					
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
			
	//setScreenDetails - Created by hemalatha - 30-07-2018
	public String setScreenDetails()
	{
		String str = "setScreenDetails";
		startMethodLogger(str);
		String status = "theater-owner-error";
		try
		{
			sessionCheck = loginAction.user_SessionCheck();
			request = ServletActionContext.getRequest();
			  
				Map<String, Object> sessionMap =ActionContext.getContext().getSession();
				sessionCheck = loginAction.user_SessionCheck();
				if(sessionCheck == true)
				{
					 Object theater_owner_id=sessionMap.get("theater_owner_id");
					 int id = (int) theater_owner_id;
					 theaterOwnerBean.setTheater_owner_id(id);
					 
					theaterOwnerBean = theaterOwnerManager.getTheaterScreenDetails(theaterOwnerBean);
					status = SUCCESS;
				}
				else
				{
					status = "theater-owner-error";
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
	
	//getScreenImage - Created by hemalatha - 3007-2018
	public String getScreenImage()
	{

		String status="theater-owner-error";
		String str = "getScreenImage";
		startMethodLogger(str);
		try
		{
			int modelId = Integer.parseInt(ServletActionContext.getRequest().getParameter("modelId"));
			theaterOwnerBean.setScreen_id(modelId);
			
			imageMap = theaterOwnerManager.getScreenImage(theaterOwnerBean);
			if(imageMap.size()>0)
			{
		    for (Map.Entry<Integer, byte[]> entry : imageMap.entrySet()) 
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
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return status;
	}
	
	//getTheaterAgainstScreen - Created by hemalatha - 31-07-2018
	public String getTheaterAgainstScreen()
	{
		String str = "getTheaterAgainstScreen";
		startMethodLogger(str);		
		String status = "theater-owner-error";
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
			
			theaterOwnerBean.setTheater_id(theaterIdVal);
			
			theaterOwnerBean = theaterOwnerManager.getTheaterAgainstScreen(theaterOwnerBean);
			
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
	
	//setTheaterWiseScreenDetailSubmit - Created by hemalatha - 31-07-2018
	public String setTheaterWiseScreenDetailSubmit()
	{
		String str = "setScreenDetails";
		startMethodLogger(str);
		String status = "theater-owner-error";
		try
		{
			sessionCheck = loginAction.user_SessionCheck();
			request = ServletActionContext.getRequest();
			  
				Map<String, Object> sessionMap =ActionContext.getContext().getSession();
				sessionCheck = loginAction.user_SessionCheck();
				if(sessionCheck == true)
				{
					 Object theater_owner_id=sessionMap.get("theater_owner_id");
					 int id = (int) theater_owner_id;
					 theaterOwnerBean.setTheater_owner_id(id);
					 
					 HttpServletRequest request = ServletActionContext.getRequest();
					 HttpServletResponse response = ServletActionContext.getResponse();
						
					int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
					int scrId = Integer.parseInt(request.getParameter("scrId"));
					int pasCountId = Integer.parseInt(request.getParameter("pasCountId"));
					int ctgryFrmId = Integer.parseInt(request.getParameter("ctgryFrmId"));
					int ctgryToId = Integer.parseInt(request.getParameter("ctgryToId"));
					int orderId = Integer.parseInt(request.getParameter("orderId"));
					
					theaterOwnerBean.setTheater_id(theaterIdVal);
					theaterOwnerBean.setScreen_id(scrId);
					theaterOwnerBean.setPassage_count(pasCountId);
					theaterOwnerBean.setCategory_from_id(ctgryFrmId);
					theaterOwnerBean.setCategory_to_id(ctgryToId);
					theaterOwnerBean.setOrder_id(orderId);
					
					theaterOwnerBean = theaterOwnerManager.setTheaterWiseScreenDetailSubmit(theaterOwnerBean);
					
					convertResultToJSON(theaterOwnerBean, response);
					
					status = SUCCESS;
				}
				else
				{
					status = "theater-owner-error";
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
	
	
	//setTheaterSeatCountDetailSubmit
	public String setTheaterSeatCountDetailSubmit()
	{
		String str = "setTheaterSeatCountDetailSubmit";
		startMethodLogger(str);
		String status = "theater-owner-error";
		try
		{
			sessionCheck = loginAction.user_SessionCheck();
			  request = ServletActionContext.getRequest();
			  response = ServletActionContext.getResponse();
			  
				Map<String, Object> sessionMap =ActionContext.getContext().getSession();
				sessionCheck = loginAction.user_SessionCheck();
				if(sessionCheck == true)
				{
					 Object theater_owner_id=sessionMap.get("theater_owner_id");
					 int id = (int) theater_owner_id;
					 theaterOwnerBean.setTheater_owner_id(id);
					 
				
						
					String seatCountDetail = request.getParameter("seatCountValue");
					String seatCategoryDetail = request.getParameter("searCategoryValue");
					int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
					int scrId = Integer.parseInt(request.getParameter("scrId"));
					
					theaterOwnerBean.setTheater_id(theaterIdVal);
					theaterOwnerBean.setScreen_id(scrId);
					theaterOwnerBean.setSeatCountDetail(seatCountDetail);
					theaterOwnerBean.setSeatCategoryDetail(seatCategoryDetail);
					
					theaterOwnerBean = theaterOwnerManager.setTheaterSeatCountDetailSubmit(theaterOwnerBean);
					
					if(theaterOwnerBean.getStatus().equals("success"))
					{
						theaterOwnerBean = theaterOwnerManager.getSeatingArrangementDetails(theaterOwnerBean);
						theaterOwnerBean = theaterOwnerManager.getNavBarScreenDetails(theaterOwnerBean);
						sessionMap.put("nav_bar_screen_status", theaterOwnerBean.getNavBarScreenStatus());
					}
					convertResultToJSON(theaterOwnerBean, response);

					status = SUCCESS;
				}
				else
				{
					status = "theater-owner-error";
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
			

//getAddTheaterPageDetails created by ramya - 30-07-18
			public String getAddTheaterPageDetails(){
				String status = ERROR;
				String str = "getAddTheaterPageDetails";
				startMethodLogger(str);						
				try{
					sessionCheck = loginAction.user_SessionCheck();
					request = ServletActionContext.getRequest();
					  
						Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							 theaterOwnerBean = theaterOwnerManager.getAddTheaterAvailableStatusDetails(theaterOwnerBean);
							 
							 if(theaterOwnerBean.getStatus().equals("Success")){
								 
								 theaterMap = theaterOwnerManager.getTheaterOwnerTheater(theaterOwnerBean);
								 Map.Entry<Integer, String> entry = theaterMap.entrySet().iterator().next();
								 int theater_id = entry.getKey();
								 theaterDetailBean.setTheater_id(theater_id);
							     theaterDetailBean = theaterOwnerManager.getTheaterAllDetails(theaterDetailBean);
								 
								 status = "view-theater-page";

							 }
							 else{
								 
								 districtMap = theaterOwnerManager.getDistrictDetails();
								 
								 status = "add-theater-page";
							 }
                             
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
			//districtWiseCityDetail created by ramya - 30-07-18
			
			public String districtWiseCityDetail(){
				String status = ERROR;
				String str = "districtWiseCityDetail";
				startMethodLogger(str);
				try{
					  
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 request = ServletActionContext.getRequest();
							 response = ServletActionContext.getResponse();
						     int district_id = Integer.parseInt(request.getParameter("districtId"));
						     theaterOwnerBean.setDistrict_id(district_id);
						     
							 cityMap = theaterOwnerManager.getDistrictWiseCityDetail(theaterOwnerBean);
							 
							 convertResultToJSON1(cityMap, response);  

							 status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
			
			//getJSONDistrictDetails created by ramya - 31-07-18
			public String getJSONDistrictDetails(){
				String status = ERROR;
				String str = "getJSONDistrictDetails";
				startMethodLogger(str);
				try{
					  
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 request = ServletActionContext.getRequest();
							 response = ServletActionContext.getResponse();
						     
							 districtMap = theaterOwnerManager.getDistrictDetails();
							 
							 convertResultToJSON1(districtMap, response);  

							 status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
			//setAddTheaterDetails created by ramya - 31-07-18
			public String setAddTheaterDetails(){
				String status = ERROR;
				String str = "setAddTheaterDetails";
				startMethodLogger(str);						
				try{
					sessionCheck = loginAction.user_SessionCheck();
					request = ServletActionContext.getRequest();
					  
						Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							theaterOwnerBean = theaterOwnerManager.setAddTheaterDetails(theaterOwnerBean);
                             
							 status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
			//getJqueryTheaterdetail
			public String getJqueryTheaterdetail(){
				String status = ERROR;
				String str = "getJqueryTheaterdetail";
				startMethodLogger(str);
				try{
					  
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 request = ServletActionContext.getRequest();
							 response = ServletActionContext.getResponse();
						     
							 int theater_id =  Integer.parseInt(request.getParameter("theaterId"));
						     theaterDetailBean.setTheater_id(theater_id);
							 
							 theaterDetailBean = theaterOwnerManager.getTheaterAllDetails(theaterDetailBean);

							 convertResultToJSON2(theaterDetailBean, response);  

							 status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
			//getJqueryTheaterBasicScreendetail
			public String getJqueryTheaterBasicScreendetail(){
				String status = ERROR;
				String str = "getJqueryTheaterBasicScreendetail";
				startMethodLogger(str);
				try{
					  
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 request = ServletActionContext.getRequest();
							 response = ServletActionContext.getResponse();
						     
							 int theater_id =  Integer.parseInt(request.getParameter("theaterId"));
							 int screen_id =  Integer.parseInt(request.getParameter("screenId"));
						     theaterDetailBean.setTheater_id(theater_id);
						     theaterDetailBean.setScreen_id(screen_id);
							 
							 theaterDetailBean = theaterOwnerManager.getTheaterBasicScreendetail(theaterDetailBean);

							 convertResultToJSON2(theaterDetailBean, response);  

							 status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
			//editTheaterDetail
			public String editTheaterDetail(){
				String status = ERROR;
				String str = "editTheaterDetail";
				startMethodLogger(str);
				try{
					    Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							 districtMap = theaterOwnerManager.getDistrictDetails();

							 theaterOwnerBean.setDistrict_id(0);
							 cityMap = theaterOwnerManager.getDistrictWiseCityDetail(theaterOwnerBean);
							 
							 theaterOwnerBean = theaterOwnerManager.getEditTheaterDetails(theaterOwnerBean);
							 status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
			//deleteTheaterDetail created by ramya - 02-08-18
			public String deleteTheaterDetail(){
				String status = ERROR;
				String str = "deleteTheaterDetail";
				startMethodLogger(str);
				try{
					    Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							 theaterOwnerBean = theaterOwnerManager.setDeleteTheaterDetail(theaterOwnerBean);
							
							 status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
			//updateTheaterDetail created by ramya - 03-08-18
			public String updateTheaterDetail(){
				String status = ERROR;
				String str = "updateTheaterDetail";
				startMethodLogger(str);
				try{
					    Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							 theaterOwnerBean = theaterOwnerManager.setUpdateTheaterDetails(theaterOwnerBean);
							 sessionMap.put("nav_bar_screen_status", "No");
							 status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
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
			
			private void convertResultToJSON1(Map<Integer, String> cityMap,
					HttpServletResponse response) throws IOException {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.flush();
				String temp = new Gson().toJson(cityMap);
				out.print(temp);
				out.close();
			}
			
			private void convertResultToJSON2(TheaterDetailBean theaterDetailBean,
					HttpServletResponse response) throws IOException {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.flush();
				String temp = new Gson().toJson(theaterDetailBean);
				out.print(temp);
				out.close();
			}

//add Counter User
			public String addCounterUser() {
				String status = ERROR;
				String str = "addCounterUser";
				startMethodLogger(str);
			    Map<String, Object> sessionMap =ActionContext.getContext().getSession();
				try {
					 Object theater_owner_id=sessionMap.get("theater_owner_id");
					 int id = (int) theater_owner_id;
					 theaterOwnerBean.setTheater_owner_id(id);
					 
					 userRoleMap=theaterOwnerManager.getUserRoleMap();
					 theaterMap = theaterOwnerManager.getTheaterMapDetail(theaterOwnerBean);
					
					status=SUCCESS;
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
			}
			
		//set theater employee details
			public String  theaterAddUserSubmit() {
				String status = ERROR;
				String str = "theaterAddUserSubmit";
				startMethodLogger(str);
				try {
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						 
						 theaterOwnerBean=theaterOwnerManager.settTheaterAddUserSubmit(theaterOwnerBean);
						 sessionMap.put("nav_bar_user_status", theaterOwnerBean.getNav_bar_user_status());

						 theaterEmployeeList=theaterOwnerManager.getTheaterEmployeeList(theaterOwnerBean);
						 
						 if(theaterOwnerBean!=null) {
							 templateBean=theaterOwnerManager.getEmployeeInviteTemplate(templateBean);
							 String message=templateBean.getTemplate();
							 message=message.replace("uniqueid", theaterOwnerBean.getUniqueId());
							 message=message.replace("<!emp_name!>", theaterOwnerBean.getFirst_name());
							 message=message.replace("<!emp_mail!>", theaterOwnerBean.getEmail_id());
							 message=message.replace("<!emp_pass!>","User123$");
							 message=message.replace("<!working_theater!>", theaterOwnerBean.getTheater_name());
							 
							 boolean sendStatus=false;
						    	//  smtpServerBean=recruiterManager.getMailToServer(smtpServerBean);
						    	  
							 String from_email="way2jobz@finix.co.in";
							 String server="finix.co.in";
						    	  
						    	 //SMTPServerBean smtpServerBean1=SMTPServerDB.instance().getServerdetail();
									if(server != null) {
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
									Address toAddresses = new InternetAddress(theaterOwnerBean.getEmail_id());
									message1.setRecipient(Message.RecipientType.TO, toAddresses);
									Address replyAddressList[] = { new InternetAddress(from_email) };
									message1.setReplyTo(replyAddressList);
									// set subject
									message1.setSubject("Ticket-System-Employee Invite");
									message1.setContent(message, "text/html");
									message1.addHeader("Return-Path", from_email);
									message1.addHeader("Return-Receipt-To",from_email);
									message1.addHeader("Disposition-Notification-To", from_email);
									message1.setSentDate(new Date());
									Transport.send(message1);
									sendStatus = true;
								}
									else if(sendStatus == false) {
									
									

							    	  SendGrid sendgrid = new SendGrid("toptheexam","admin123");
									    SendGrid.Email email = new SendGrid.Email();
									    
									 
									    
									    email.addTo(theaterOwnerBean.getEmail_id());
									    
									    
									    	email.setFrom("way2jobz@finix.co.in");
										    email.setSubject("Ticket-Booking-System - Employee  Invite");
									   
									    
									    
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
										    jsonParam.put("from", "info@recruit24x7.com");
										    jsonParam.put("fromName", "info@recruit24x7.com");
										    jsonParam.put("to", theaterOwnerBean.getEmail_id());
										    jsonParam.put("subject", "Recruit24x7.com - Confirm Email Id-vivaconnect");
										    jsonParam.put("replyTo", "vignesh@finix.co.in");
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
							 
						 }
					
						 
						 
					status=SUCCESS;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
				
			}
		
		
			//view theater emploee
			public String viewTheaterEmployee() {
				String status=ERROR;
				try {
					
					theaterEmployeeList=theaterOwnerManager.getTheaterEmployeeList(theaterOwnerBean);
					
					status=SUCCESS;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return status;
			}
			
			public String editEmployeeDetails() {
				String status=ERROR;
				try {
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();

					Object theater_owner_id=sessionMap.get("theater_owner_id");
					int id = (int) theater_owner_id;
					theaterOwnerBean.setTheater_owner_id(id);
					 
					userRoleMap=theaterOwnerManager.getUserRoleMap();
					theaterMap = theaterOwnerManager.getTheaterMapDetail(theaterOwnerBean);
					 
					theaterOwnerBean=theaterOwnerManager.getEmployeeEditDetails(theaterOwnerBean);
					
					status=SUCCESS;
				}
				catch(Exception e) {
					e.printStackTrace();
					
				}
				return status;
			}
		
			//editTheaterEmployeeSubmit
			public String editTheaterEmployeeSubmit() {
				String status=ERROR;
				try {
					
					theaterOwnerBean=theaterOwnerManager.setTheaterEmployeeDetails(theaterOwnerBean);
					
					theaterEmployeeList=theaterOwnerManager.getTheaterEmployeeList(theaterOwnerBean);
					
					status=SUCCESS;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return status;
			}
			
			
			public String deleteTheaterEmployee() {
				String status=ERROR;
				try {
					
					theaterOwnerBean=theaterOwnerManager.deleteTheaterEmployeeDetails(theaterOwnerBean);
					
					theaterEmployeeList=theaterOwnerManager.getTheaterEmployeeList(theaterOwnerBean);
					
					status=SUCCESS;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return status;
			}
		
			
			
			
			//add movie page
			public String addMovie() {
				String status=ERROR;
				try {
					
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();

					Object theater_owner_id=sessionMap.get("theater_owner_id");
					int id = (int) theater_owner_id;
					theaterOwnerBean.setTheater_owner_id(id);
					
					movieLangMap=theaterOwnerManager.getMovieLanguages();
					movieGenreMap=theaterOwnerManager.getMovieGenre();
					movieFormatMap=theaterOwnerManager.getMovieFormat();
					movieIdMap = theaterOwnerManager.getMasterMovieDetails();
					movieCertificationMap = theaterOwnerManager.getMovieCertificationDetails();
					
					movieList=theaterOwnerManager.getMovieDetailList(theaterOwnerBean);
					status=SUCCESS;
					}
					else{
						 status="theater-owner-error";
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					
				}
				return status;
			}
		
			
			//set movie details
			public String movieDetailSubmit() {
				String status=ERROR;
				try {
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						 
					     theaterOwnerBean=theaterOwnerManager.setMovieDetailsSubit(theaterOwnerBean);
						 sessionMap.put("nav_bar_movie_status", theaterOwnerBean.getNav_bar_movie_status());

					     status=SUCCESS;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					
				}
				return status;
			}
			
			
			//get movie poster
			public String getMoviePoster() {
				String status=ERROR;
				try {
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					 Object theater_owner_id=sessionMap.get("theater_owner_id");
					 int id = (int) theater_owner_id;
					 theaterOwnerBean.setTheater_owner_id(id);
					 
					int modelId = Integer.parseInt(ServletActionContext.getRequest().getParameter("modelId"));
					theaterOwnerBean.setMovie_details_id(modelId);
					movieImgeMap=theaterOwnerManager.getMoviePosterImage(theaterOwnerBean);
					if(movieImgeMap.size()>0)
					{
						for (Map.Entry<Integer, byte[]> entry : movieImgeMap.entrySet()) 
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
			
			//deleteScreenLayoutDetail - Created by Ramya - 04-08-2018
			public String deleteScreenLayoutDetail() {
				String status=ERROR;
				try {
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterDetailBean.setTheater_owner_id(id);
					
					     theaterDetailBean=theaterOwnerManager.setDeleteScreenLayoutDetail(theaterDetailBean);
					     status=SUCCESS;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					
				}
				return status;
			}
			
			//getEmployeProfileImage - Created by Ramya - 04-08-2018
			public String getEmployeProfileImage()
			{

				String status="theater-owner-error";
				String str = "getEmployeProfileImage";
				startMethodLogger(str);
				Map<String, Object> sessionMap =ActionContext.getContext().getSession();
				try
				{
				    Object theater_owner_id=sessionMap.get("theater_owner_id");
					int id = (int) theater_owner_id;
					theaterOwnerBean.setTheater_owner_id(id);
					 
					int modelId = Integer.parseInt(ServletActionContext.getRequest().getParameter("modelId"));
					theaterOwnerBean.setTheatre_employee_id(modelId);
					
					imageMap = theaterOwnerManager.getEmployeProfileImage(theaterOwnerBean);
					if(imageMap.size()>0)
					{
				    for (Map.Entry<Integer, byte[]> entry : imageMap.entrySet()) 
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
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				return status;
			}
			
			
			
			/*deleteMovieDetailsSubmit*/
			
			public String deleteMovieDetailsSubmit() {
				String status=ERROR;
				try {

					theaterOwnerBean=theaterOwnerManager.deleteMovieDetailsSubmit(theaterOwnerBean);
					
					status=SUCCESS;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return status;
			}
			
         	public String getMovieDetails() {
				String status="theater-owner-error";
				try {
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
					Object theater_owner_id=sessionMap.get("theater_owner_id");
					int id = (int) theater_owner_id;
					theaterOwnerBean.setTheater_owner_id(id);
					
					movieLangMap=theaterOwnerManager.getMovieLanguages();
					movieGenreMap=theaterOwnerManager.getMovieGenre();
					movieFormatMap=theaterOwnerManager.getMovieFormat();
					movieIdMap = theaterOwnerManager.getMasterMovieDetails();
					movieCertificationMap = theaterOwnerManager.getMovieCertificationDetails();

					movieList=theaterOwnerManager.getMovieDetailList(theaterOwnerBean);
					
					theaterOwnerBean=theaterOwnerManager.setMovieEditDetails(theaterOwnerBean);

					status=SUCCESS;
					}
					else
					{
						status = "theater-owner-error";
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return status;
			}
			
			
			
			//edit movie submit
			public String editMovieDetailsSubmit() {
				String status=ERROR;
				try {
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
					     
						 theaterOwnerBean=theaterOwnerManager.setMovieEditDetailSubmit(theaterOwnerBean);
					     movieList=theaterOwnerManager.getMovieDetailList(theaterOwnerBean);
					      
					      status=SUCCESS;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					
				}
				return status;
			}
			
			
	//"getViewScreenDetails" - Created by hemalatha - 07-08-2018
			public String getViewScreenDetails()
			{
				String str = "getViewScreenDetails";
				startMethodLogger(str);
				String status = "theater-owner-error";
				try
				{
					sessionCheck = loginAction.user_SessionCheck();
					request = ServletActionContext.getRequest();
					  
						Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							theaterOwnerBean = theaterOwnerManager.getTheaterScreenDetailsForView(theaterOwnerBean);
							status = SUCCESS;
						}
						else
						{
							status = "theater-owner-error";
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
			
			
	//getBasicFloorPlanDetails
			public String getBasicFloorPlanDetails()
			{

				String str = "getBasicFloorPlanDetails";
				startMethodLogger(str);
				String status = "theater-owner-error";
				try
				{
					sessionCheck = loginAction.user_SessionCheck();
					  request = ServletActionContext.getRequest();
					  response = ServletActionContext.getResponse();
					  
						Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							 int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
							 int scrId = Integer.parseInt(request.getParameter("scrId"));
								
							theaterOwnerBean.setTheater_id(theaterIdVal);
							theaterOwnerBean.setScreen_id(scrId);
							
							theaterOwnerBean = theaterOwnerManager.getSeatingArrangementDetails(theaterOwnerBean);
							convertResultToJSON(theaterOwnerBean, response);

							status = SUCCESS;
						}
						else
						{
							status = "theater-owner-error";
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
			
	//getTheaterAgainstScreenForView
			public String getTheaterAgainstScreenForView()
			{

				String str = "getTheaterAgainstScreen";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				try
				{
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
					
					int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
					
					theaterOwnerBean.setTheater_id(theaterIdVal);
					
					theaterOwnerBean = theaterOwnerManager.getTheaterAgainstScreenForview(theaterOwnerBean);
					
					convertResultToJSON(theaterOwnerBean, response);  
					
					status = SUCCESS;
					
					}
					else{
						status = "theater-owner-error";
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

	     //getMovieArrangementDetails
			public String getMovieArrangementDetails()
			{
				String str = "getMovieArrangementDetails";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				 try{
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						 
						 theaterMap = theaterOwnerManager.getTheaterOwnerTheater(theaterOwnerBean);
						 movieIdMap = theaterOwnerManager.getMovieIdDetails(theaterOwnerBean);
						 movieLangMap=theaterOwnerManager.getMovieLanguages();
						 movieGenreMap=theaterOwnerManager.getMovieGenre();
						 movieFormatMap=theaterOwnerManager.getMovieFormat();
						 
						 theaterOwnerBean = theaterOwnerManager.getMovieScreenShowDetail(theaterOwnerBean);						 
						 status = SUCCESS;
					}
					else{
						status = "theater-owner-error";
					}
			}
			catch(Exception e){
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			}
			
		//setMovieScreenShowTimeDetail created by ramya-10-08-18
			public String setMovieScreenShowTimeDetail()
			{
				String str = "setMovieScreenShowTimeDetail";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				 try{
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						 
						 theaterOwnerBean = theaterOwnerManager.setMovieScreenShowTimeDetail(theaterOwnerBean);
						
						 status = SUCCESS;
					}
					else{
						status = "theater-owner-error";
					}
			}
			catch(Exception e){
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			}	
			
	//getEditScreenDetails - Created by hemalatha - 09-08-2018
			public String getEditScreenDetails()
			{

				String str = "getEditScreenDetails";
				startMethodLogger(str);
				String status = "theater-owner-error";
				try
				{
					sessionCheck = loginAction.user_SessionCheck();
					request = ServletActionContext.getRequest();
					  
						Map<String, Object> sessionMap =ActionContext.getContext().getSession();
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							theaterOwnerBean = theaterOwnerManager.getTheaterScreenDetailsForView(theaterOwnerBean);
							theaterOwnerBean = theaterOwnerManager.getEditScreenDetails(theaterOwnerBean);
							status = SUCCESS;
						}
						else
						{
							status = "theater-owner-error";
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
			
	//getTheaterAgainstScreenForEdit - Created by hemalatha - 09-08-2018
		public String getTheaterAgainstScreenForEdit()
		{
			String str = "getTheaterAgainstScreenForEdit";
			startMethodLogger(str);		
			String status = "theater-owner-error";
			try
			{
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				
				int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
				
				theaterOwnerBean.setTheater_id(theaterIdVal);
				
				theaterOwnerBean = theaterOwnerManager.getTheaterAgainstScreen(theaterOwnerBean);
				theaterOwnerBean = theaterOwnerManager.getEditScreenDetails(theaterOwnerBean);

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
	
	//getScreenDetailsForEdit
		public String getScreenDetailsForEdit()
		{

			String str = "getTheaterAgainstScreenForEdit";
			startMethodLogger(str);		
			String status = "theater-owner-error";
			try
			{
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				
				int scrId = Integer.parseInt(request.getParameter("scrId"));
				
				theaterDetailBean.setScreen_id(scrId);
				
				theaterOwnerBean = theaterOwnerManager.getEditScreenDetails(theaterOwnerBean);

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
			
			//getTheaterwiseScreenDetails
			public String getTheaterwiseScreenDetails()
			{

				String str = "getTheaterwiseScreenDetails";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				try
				{
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpServletResponse response = ServletActionContext.getResponse();
					
					int theaterId = Integer.parseInt(request.getParameter("theaterId"));
					
					theaterOwnerBean.setTheater_id(theaterId);
					
					screenMap = theaterOwnerManager.getScreenMapDetail(theaterOwnerBean);
					
					convertResultToJSON1(screenMap, response);  
					
					status = SUCCESS;
					
					}
					else{
						status = "theater-owner-error";
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
			
		//setDeleteShowTime	created by ramya -10-08-18
			public String setDeleteShowTime()
			{
				String str = "setDeleteShowTime";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				 try{
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						 
						 theaterOwnerBean = theaterOwnerManager.setDeleteShowTime(theaterOwnerBean);
						
						 status = SUCCESS;
					}
					else{
						status = "theater-owner-error";
					}
			}
			catch(Exception e){
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			}	
			
		  //getDatewiseSearchShowDetails
			public String getDatewiseSearchShowDetails()
			{
				String str = "getDatewiseSearchShowDetails";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				 try{
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						 HttpServletRequest request = ServletActionContext.getRequest();
						 HttpServletResponse response = ServletActionContext.getResponse();
						
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						 
						 theaterOwnerBean.setStatus_id(1);
						 String date = request.getParameter("date");
						 theaterOwnerBean.setDate(date);
							
						 theaterOwnerBean = theaterOwnerManager.getMovieScreenShowDetail(theaterOwnerBean);		
						 
						 convertResultToJSON(theaterOwnerBean, response);
						
						 status = SUCCESS;
					}
					else{
						status = "theater-owner-error";
					}
			}
			catch(Exception e){
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			}	
			
		//getAddedMovieDetails created by ramya - 13-08-18	
			public String getAddedMovieDetails()
			{
				String str = "getAddedMovieDetails";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				 try{
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						
						 movieList=theaterOwnerManager.getMovieDetailList(theaterOwnerBean);
						
						 status = SUCCESS;
					}
					else{
						status = "theater-owner-error";
					}
			}
			catch(Exception e){
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			}	
			
			//getTheatreTaxDetail
			public String getTheatreTaxDetail()
			{
				Map<String, Object> sessionMap = ActionContext.getContext().getSession();
				String status=ERROR;
				String str = "getTheatreTaxDetail";
				startMethodLogger(str);
				try
				{
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						
						 taxMap=theaterOwnerManager.getTaxMap(theaterOwnerBean);
						
						 status = SUCCESS;
					}
					else{
						status = "theater-owner-error";
					}
			}
			catch(Exception e){
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			}
			
			//getTheatreOwnersTaxDetail
			public String getTheatreOwnersTaxDetail() {
				Map<String, Object> sessionMap = ActionContext.getContext().getSession();
				String status=ERROR;
				String str = "getTheatreOwnersTaxDetail";
				startMethodLogger(str);
				try
				{
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						request = ServletActionContext.getRequest();
						response = ServletActionContext.getResponse();
						
						int theatre_id = Integer.parseInt(request.getParameter("theatre_id"));
						String date = request.getParameter("dateRange");
						theaterOwnerBean.setTheatre_id(theatre_id);
						theaterOwnerBean.setDate(date);
						System.out.println(date);

						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						
						 theaterOwnerBean=theaterOwnerManager.getTheatreOwnersTaxDetail(theaterOwnerBean);
						 
						 convertResultToJSON(theaterOwnerBean, response);

						 
						 status = SUCCESS;
					}
					else{
						status = "theater-owner-error";
					}
			}
			catch(Exception e){
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			}	
				
			//getTheaterOwnerMoviewiseDetails
			public String getTheaterOwnerMoviewiseDetails() {

				String str = "getTheaterOwnerMoviewiseDetails";
				startMethodLogger(str);		
				String status = "theater-owner-error";
				
					request = ServletActionContext.getRequest();
					response = ServletActionContext.getResponse();
				
				 try{
					Map<String, Object> sessionMap =ActionContext.getContext().getSession();
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck == true)
					{
						
						 Object theater_owner_id=sessionMap.get("theater_owner_id");
						 int id = (int) theater_owner_id;
						 theaterOwnerBean.setTheater_owner_id(id);
						
						 String dateIdVal = request.getParameter("date");				
						 theaterOwnerBean.setDate(dateIdVal);
						 
							
						 theatreOwnerMovieList=theaterOwnerManager.getTheaterOwnerMoviewiseDetails(theaterOwnerBean);
						 convertResultToJSON1(theatreOwnerMovieList, response);

						 status = SUCCESS;
					}
					else{
						status = "theater-owner-error";
					}
			}
			catch(Exception e){
				e.printStackTrace();
				catchMethodLogger(str, e);
			}
			endMethodLogger(str);
			return status;
			}	
			private void convertResultToJSON1(ArrayList<TheaterOwnerBean>theatreOwnerMovieList,HttpServletResponse response) throws IOException {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.flush();
				String temp = new Gson().toJson(theatreOwnerMovieList);
				out.print(temp);
				out.close();
			}
			
			//getTheaterOwnerMovieScreenwiseDetails
			public String getTheaterOwnerMovieScreenwiseDetails(){
					
				{
					Map<String, Object> sessionMap = ActionContext.getContext().getSession();
					String status=ERROR;
					String str = "getTheaterOwnerMovieScreenwiseDetails";
					startMethodLogger(str);
					
					
					request = ServletActionContext.getRequest();
					response = ServletActionContext.getResponse();
				
					try
					{
						sessionCheck = loginAction.user_SessionCheck();
						if(sessionCheck == true)
						{
							
							 Object theater_owner_id=sessionMap.get("theater_owner_id");
							 int id = (int) theater_owner_id;
							 theaterOwnerBean.setTheater_owner_id(id);
							 
							 String dateIdVal = request.getParameter("date");				
							 theaterOwnerBean.setDate(dateIdVal);
							
							screenwiseList =theaterOwnerManager.getTheaterOwnerMovieScreenwiseDetails(theaterOwnerBean);
							convertResultToJSON1(screenwiseList, response);
							status = SUCCESS;
						}
						else{
							status = "theater-owner-error";
						}
				}
				catch(Exception e){
					e.printStackTrace();
					catchMethodLogger(str, e);
				}
				endMethodLogger(str);
				return status;
				}
			}
			
		//getter and setter	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
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

	public TheaterOwnerBean getTheaterOwnerBean() {
		return theaterOwnerBean;
	}

	public void setTheaterOwnerBean(TheaterOwnerBean theaterOwnerBean) {
		this.theaterOwnerBean = theaterOwnerBean;
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

	public Logger getLogger() {
		return logger;
	}

	public TemplateBean getTemplateBean() {
		return templateBean;
	}

	public void setTemplateBean(TemplateBean templateBean) {
		this.templateBean = templateBean;
	}

	public Map<Integer, String> getStateMap() {
		return stateMap;
	}

	public void setStateMap(Map<Integer, String> stateMap) {
		this.stateMap = stateMap;
	}

	public Map<Integer, String> getCityMap() {
		return cityMap;
	}

	public void setCityMap(Map<Integer, String> cityMap) {
		this.cityMap = cityMap;
	}

	public Map<Integer, byte[]> getImageMap() {
		return imageMap;
	}

	public void setImageMap(Map<Integer, byte[]> imageMap) {
		this.imageMap = imageMap;
	}

	public byte[] getItemImage() {
		return itemImage;
	}

	public void setItemImage(byte[] itemImage) {
		this.itemImage = itemImage;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public Map<Integer, String> getDistrictMap() {
		return districtMap;
	}

	public void setDistrictMap(Map<Integer, String> districtMap) {
		this.districtMap = districtMap;
	}

	public TheaterDetailBean getTheaterDetailBean() {
		return theaterDetailBean;
	}

	public void setTheaterDetailBean(TheaterDetailBean theaterDetailBean) {
		this.theaterDetailBean = theaterDetailBean;
	}

	public Map<Integer, String> getTheaterMap() {
		return theaterMap;
	}

	public void setTheaterMap(Map<Integer, String> theaterMap) {
		this.theaterMap = theaterMap;
	}
	
	public Map<Integer, String> getUserRoleMap() {
		return userRoleMap;
	}

	public void setUserRoleMap(Map<Integer, String> userRoleMap) {
		this.userRoleMap = userRoleMap;
	}

	public ArrayList<TheaterOwnerBean> getTheaterEmployeeList() {
		return theaterEmployeeList;
	}

	public void setTheaterEmployeeList(ArrayList<TheaterOwnerBean> theaterEmployeeList) {
		this.theaterEmployeeList = theaterEmployeeList;
	}

	public Map<Integer, String> getMovieLangMap() {
		return movieLangMap;
	}

	public void setMovieLangMap(Map<Integer, String> movieLangMap) {
		this.movieLangMap = movieLangMap;
	}

	public Map<Integer, String> getMovieGenreMap() {
		return movieGenreMap;
	}

	public void setMovieGenreMap(Map<Integer, String> movieGenreMap) {
		this.movieGenreMap = movieGenreMap;
	}

	public Map<Integer, String> getMovieFormatMap() {
		return movieFormatMap;
	}

	public void setMovieFormatMap(Map<Integer, String> movieFormatMap) {
		this.movieFormatMap = movieFormatMap;
	}

	public ArrayList<TheaterOwnerBean> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<TheaterOwnerBean> movieList) {
		this.movieList = movieList;
	}

	public Map<Integer, byte[]> getMovieImgeMap() {
		return movieImgeMap;
	}

	public void setMovieImgeMap(Map<Integer, byte[]> movieImgeMap) {
		this.movieImgeMap = movieImgeMap;
	}

	public Map<Integer, String> getMovieIdMap() {
		return movieIdMap;
	}

	public void setMovieIdMap(Map<Integer, String> movieIdMap) {
		this.movieIdMap = movieIdMap;
	}

	public Map<Integer, String> getScreenMap() {
		return screenMap;
	}

	public void setScreenMap(Map<Integer, String> screenMap) {
		this.screenMap = screenMap;
	}

	public Map<Integer, String> getMovieCertificationMap() {
		return movieCertificationMap;
	}

	public void setMovieCertificationMap(Map<Integer, String> movieCertificationMap) {
		this.movieCertificationMap = movieCertificationMap;
	}

	public Map<Integer, String> getTaxMap() {
		return taxMap;
	}

	public void setTaxMap(Map<Integer, String> taxMap) {
		this.taxMap = taxMap;
	}

	public ArrayList<TheaterOwnerBean> getTheatreOwnerMovieList() {
		return theatreOwnerMovieList;
	}

	public void setTheatreOwnerMovieList(
			ArrayList<TheaterOwnerBean> theatreOwnerMovieList) {
		this.theatreOwnerMovieList = theatreOwnerMovieList;
	}

	public ArrayList<TheaterOwnerBean> getScreenwiseList() {
		return screenwiseList;
	}

	public void setScreenwiseList(ArrayList<TheaterOwnerBean> screenwiseList) {
		this.screenwiseList = screenwiseList;
	}
	
	
}
