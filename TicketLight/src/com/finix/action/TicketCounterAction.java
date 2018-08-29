package com.finix.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.TicketCounterBean;
import com.finix.manager.ITheaterOwnerManager;
import com.finix.manager.ITicketCounterManager;
import com.finix.manager.impl.TheaterOwnerManagerImpl;
import com.finix.manager.impl.TicketCounterManagerImpl;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TicketCounterAction extends ActionSupport implements
ServletResponseAware, ServletRequestAware,SessionAware
{
	
	boolean sessionCheck = false;
	LoginAction loginAction = new LoginAction();
	HttpServletRequest request;
	HttpServletResponse response;
	
	TicketCounterBean ticketCounterBean = new TicketCounterBean();
	ITicketCounterManager ticketCounterManager = new TicketCounterManagerImpl();
	Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
	private byte[] itemImage;
	public InputStream inputStream = null;
	private String content_type;
	ArrayList<TheaterOwnerBean> movieList=new ArrayList<TheaterOwnerBean>();
	ArrayList<TicketCounterBean>moviewiseList=new ArrayList<TicketCounterBean>();
	ArrayList<TicketCounterBean>screenwiseList=new ArrayList<TicketCounterBean>();
	Map<Integer, byte[]> movieImgeMap = new HashMap<Integer,byte[]>();
	TheaterOwnerBean theaterOwnerBean = new TheaterOwnerBean();
	ITheaterOwnerManager theaterOwnerManager = new TheaterOwnerManagerImpl();
	HashMap<Integer, String> movieDateMap=new HashMap<Integer,String>();
	HashMap<Integer, String> movieShowMap=new HashMap<Integer,String>();
	ArrayList<TheaterOwnerBean> movieShowList=new ArrayList<TheaterOwnerBean>();
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
	
	//getTicketCounterPersonDashboardDetail created by ramya -09-08-2018
	public String getTicketCounterPersonDashboardDetail(){
		String str = "getTicketCounterPersonDashboardDetail";
		startMethodLogger(str);
		String status = "ticket-counter-error";
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
	
	//getTicketCounterPersonProfileImage created by ramya -09-08-2018
	public String getTicketCounterPersonProfileImage()
	{

		String status="ticket-counter-error";
		String str = "getTicketCounterPersonProfileImage";
		startMethodLogger(str);
		try
		{
			int modelId = Integer.parseInt(ServletActionContext.getRequest().getParameter("modelId"));
			ticketCounterBean.setTicket_counter_person_id(modelId);
			
			imageMap = ticketCounterManager.getTicketCounterPersonProfileImage(ticketCounterBean);
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
		endMethodLogger(str);
		return status;
	}
	
	
	
	//profile view
	
	public String getProfileView() {
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		String status=ERROR;
		String str = "getProfileView";
		startMethodLogger(str);
		try
		{
			sessionCheck = loginAction.user_SessionCheck();
			if (sessionCheck) 
			{
				Object ticket_counter_person_id=sessionMap.get("ticket_counter_person_id");
				int id = (int) ticket_counter_person_id;
				//theaterOwnerBean.setTheater_owner_id(id);
				ticketCounterBean.setTicket_counter_person_id(id);
				
				ticketCounterBean=ticketCounterManager.getTicketCounterPersonDetails(ticketCounterBean);
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
	
	
	public String getMoviesOnTicketCounter() {
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		String status=ERROR;
		String str = "getProfileView";
		startMethodLogger(str);
		try
		{
			sessionCheck = loginAction.user_SessionCheck();
			if (sessionCheck) 
			{
				Object ticket_counter_person_id=sessionMap.get("ticket_counter_person_id");
				int id = (int) ticket_counter_person_id;
				ticketCounterBean.setTicket_counter_person_id(id);
				
				Object owner_id=sessionMap.get("employee_owner_id");
				int ownerId = (int) owner_id;
				ticketCounterBean.setEmployee_working_theater_owner_id(ownerId);
				theaterOwnerBean.setTheater_owner_id(ownerId);
				
				Object theatre_id=sessionMap.get("employee_working_theater_id");
				int theatreId=(int) theatre_id;
				ticketCounterBean.setEmployee_working_theater_id(theatreId);
				
				movieList=theaterOwnerManager.getMovieDetailList(theaterOwnerBean);
				
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
	
	
	public String getMoviePoster() {
		String status=ERROR;
		String str = "getMoviePoster";
		startMethodLogger(str);
		try {
			
			Map<String, Object> sessionMap =ActionContext.getContext().getSession();
			 
			Object owner_id=sessionMap.get("employee_owner_id");
			int ownerId = (int) owner_id;
			ticketCounterBean.setEmployee_working_theater_owner_id(ownerId);
			theaterOwnerBean.setTheater_owner_id(ownerId);
			
			Object theatre_id=sessionMap.get("employee_working_theater_id");
			int theatreId=(int) theatre_id;
			ticketCounterBean.setEmployee_working_theater_id(theatreId);
			theaterOwnerBean.setTheater_id(theatreId);
			 
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
		catch(Exception e) {
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
		
	}
	
	//counter theatre layout
	public String getCounterMovieDetails() {
		String status=ERROR;
		String str = "getCounterMovieDetails";
		startMethodLogger(str);
		try {
			
			Map<String, Object> sessionMap =ActionContext.getContext().getSession();
			 
			Object owner_id=sessionMap.get("employee_owner_id");
			int ownerId = (int) owner_id;
			ticketCounterBean.setEmployee_working_theater_owner_id(ownerId);
			theaterOwnerBean.setTheater_owner_id(ownerId);
			
			Object theatre_id=sessionMap.get("employee_working_theater_id");
			int theatreId=(int) theatre_id;
			ticketCounterBean.setEmployee_working_theater_id(theatreId);
			theaterOwnerBean.setTheater_id(theatreId);
			 
			int modelId = Integer.parseInt(ServletActionContext.getRequest().getParameter("modelId"));
			theaterOwnerBean.setMovie_details_id(modelId);
			
			/*theaterOwnerBean = theaterOwnerManager.getMovieIDDetails(theaterOwnerBean);*/
			
			theaterOwnerBean=theaterOwnerManager.setMovieEditDetails(theaterOwnerBean);
			movieDateMap=theaterOwnerManager.getMovieDate(theaterOwnerBean);
		    
			movieShowList=theaterOwnerManager.getMovieShowList(theaterOwnerBean);
			
			
			//movieShowMap=theaterOwnerManager.getMovieShows(theaterOwnerBean);
			
			/*movieImgeMap=theaterOwnerManager.getMoviePosterImage(theaterOwnerBean);
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
			}*/
			status=SUCCESS;
		}
		
		catch(Exception e) {
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}
	
	//getTicketOrderSummaryDetail 
	public String getTicketOrderSummaryDetail() {
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		String status=ERROR;
		String str = "setTicketPaymentDetail";
		startMethodLogger(str);
		try
		{
			sessionCheck = loginAction.user_SessionCheck();
			if (sessionCheck) 
			{
				Object ticket_counter_person_id=sessionMap.get("ticket_counter_person_id");
				int id = (int) ticket_counter_person_id;
				ticketCounterBean.setTicket_counter_person_id(id);
				
				
			
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
	
	//getMoviePosterForCounterPerson
	public String getMoviePosterForCounterPerson()
	{
		String status="ticket-counter-error";
		String str = "setTicketPaymentDetail";
		startMethodLogger(str);
		try
		{
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
		catch(Exception e) {
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		endMethodLogger(str);
		return status;
	}
	
	
	public String getCounterTheaterLayout() {
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		String status=ERROR;
		try {
			sessionCheck = loginAction.user_SessionCheck();
			if (sessionCheck) 
			{
				Object ticket_counter_person_id=sessionMap.get("ticket_counter_person_id");
				int id = (int) ticket_counter_person_id;
				ticketCounterBean.setTicket_counter_person_id(id);
				
				Object owner_id=sessionMap.get("employee_owner_id");
				int ownerId = (int) owner_id;
				ticketCounterBean.setEmployee_working_theater_owner_id(ownerId);
				theaterOwnerBean.setTheater_owner_id(ownerId);
				
				Object theatre_id=sessionMap.get("employee_working_theater_id");
				int theatreId=(int) theatre_id;
				ticketCounterBean.setEmployee_working_theater_id(theatreId);
				theaterOwnerBean.setTheater_id(theatreId);
				
				
			
			status=SUCCESS;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		return status;
	}
	
	
	/*getCounterScreenTicketBookingLayout*/
	
	public String getCounterScreenTicketBookingLayout() {
		Map<String, Object> sessionMap =ActionContext.getContext().getSession();
		String status = ERROR;
		String str = "getCounterScreenTicketBookingLayout";
		startMethodLogger(str);
		try {
			
			sessionCheck = loginAction.user_SessionCheck();
			  request = ServletActionContext.getRequest();
			  response = ServletActionContext.getResponse();
			  
				
				sessionCheck = loginAction.user_SessionCheck();
				if(sessionCheck)
				{
					Object owner_id=sessionMap.get("employee_owner_id");
					int ownerId = (int) owner_id;
					 theaterOwnerBean.setTheater_owner_id(ownerId);
					 
					int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
					int scrId = Integer.parseInt(request.getParameter("scrId"));
					int showTimeId=Integer.parseInt(request.getParameter("showTimeId"));
					int movieDetailsId=Integer.parseInt(request.getParameter("movieDetailsId"));
					int showDetailId=Integer.parseInt(request.getParameter("showDetailId"));
					
					theaterOwnerBean.setTheater_id(theaterIdVal);
					theaterOwnerBean.setScreen_id(scrId);
					theaterOwnerBean.setShow_timing_id(showTimeId);
					theaterOwnerBean.setMovie_details_id(movieDetailsId);
					theaterOwnerBean.setShow_detail_id(showDetailId);
					
					theaterOwnerBean = theaterOwnerManager.getScreenwiseSeatingDetails(theaterOwnerBean);
					
					convertResultToJSON(theaterOwnerBean, response);
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
		
		
		private void convertResultToJSON(TheaterOwnerBean theaterOwnerBean,
				HttpServletResponse response) throws IOException {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
			String temp = new Gson().toJson(theaterOwnerBean);
			out.print(temp);
			out.close();
		}
	
	
		public String getTicketDetails() {
			Map<String, Object> sessionMap =ActionContext.getContext().getSession();
			String status = ERROR;
			String str = "getTicketDetails";
			startMethodLogger(str);
			try {
				sessionCheck = loginAction.user_SessionCheck();
				  request = ServletActionContext.getRequest();
				  response = ServletActionContext.getResponse();
				  
					
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck)
					{
						Object owner_id=sessionMap.get("employee_owner_id");
						int ownerId = (int) owner_id;
						 theaterOwnerBean.setTheater_owner_id(ownerId);
						 
						int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
						int scrId = Integer.parseInt(request.getParameter("scrId"));
						int showTimeId=Integer.parseInt(request.getParameter("showTimeId"));
						int movieDetailsId=Integer.parseInt(request.getParameter("movieDetailsId"));
						int showDetailId=Integer.parseInt(request.getParameter("showDetailId"));
						
						theaterOwnerBean.setTheater_id(theaterIdVal);
						theaterOwnerBean.setScreen_id(scrId);
						theaterOwnerBean.setShow_timing_id(showTimeId);
						theaterOwnerBean.setMovie_details_id(movieDetailsId);
						theaterOwnerBean.setShow_detail_id(showDetailId);
						
						theaterOwnerBean = theaterOwnerManager.getTicketDetail(theaterOwnerBean);
						
						convertResultToJSON(theaterOwnerBean, response);
				
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
		
	/*	getTicketSoldDetail*/
		
		public String getTicketSoldDetail() {
			Map<String, Object> sessionMap =ActionContext.getContext().getSession();
			String status = ERROR;
			String str = "getTicketSoldDetail";
			startMethodLogger(str);
			try {
				sessionCheck = loginAction.user_SessionCheck();
				  request = ServletActionContext.getRequest();
				  response = ServletActionContext.getResponse();
				  
					
					sessionCheck = loginAction.user_SessionCheck();
					if(sessionCheck)
					{
						Object owner_id=sessionMap.get("employee_owner_id");
						int ownerId = (int) owner_id;
						theaterOwnerBean.setTheater_owner_id(ownerId);
						 
					    Object ticket_counter_person_id=sessionMap.get("ticket_counter_person_id");
					    int id = (int) ticket_counter_person_id;
						ticketCounterBean.setTicket_counter_person_id(id);
						theaterOwnerBean.setTicket_counter_person_id(id);
							
						Object role=sessionMap.get("role");
						String roleId = (String) role;
						theaterOwnerBean.setUser_role(roleId);
							
						 
						int theaterIdVal = Integer.parseInt(request.getParameter("theaterIdVal"));
						int scrId = Integer.parseInt(request.getParameter("scrId"));
						int showTimeId=Integer.parseInt(request.getParameter("showTimeId"));
						int movieDetailsId=Integer.parseInt(request.getParameter("movieDetailsId"));
						int showDetailId=Integer.parseInt(request.getParameter("showDetailId"));
						String seatStr=request.getParameter("seatStr");
						String dateId=request.getParameter("dateId");
						String TicketPrice=request.getParameter("totalPrice");
						
						theaterOwnerBean.setTheater_id(theaterIdVal);
						theaterOwnerBean.setScreen_id(scrId);
						theaterOwnerBean.setShow_timing_id(showTimeId);
						theaterOwnerBean.setMovie_details_id(movieDetailsId);
						theaterOwnerBean.setShow_detail_id(showDetailId);
						theaterOwnerBean.setBooking_seat(seatStr);
						theaterOwnerBean.setBooking_date(dateId);
						theaterOwnerBean.setTotal_price(TicketPrice);
						
						theaterOwnerBean = theaterOwnerManager.getTicketSoldDetail(theaterOwnerBean);
						
						convertResultToJSON(theaterOwnerBean, response);
				
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



	//getMovieDetails 
	public String getMovieDetails()
	{
		Map<String, Object> sessionMap = ActionContext.getContext().getSession();
		String str = "getMovieDetails";
		startMethodLogger(str);
		String status = "ticket-counter-error";
		
		  request = ServletActionContext.getRequest();
		  response = ServletActionContext.getResponse();
		  
		try{
			sessionCheck = loginAction.user_SessionCheck();
			if (sessionCheck) 
			{
				Object ticket_counter_person_id=sessionMap.get("ticket_counter_person_id");
				Object employee_working_theater_id=sessionMap.get("employee_working_theater_id");
				int id = (int) ticket_counter_person_id;
				int employee_theater_id = (int) employee_working_theater_id;
				ticketCounterBean.setTicket_counter_person_id(id);
				ticketCounterBean.setEmployee_working_theater_id(employee_theater_id);
				
				
				String dateIdVal = request.getParameter("date");				
				ticketCounterBean.setDate(dateIdVal);
				
				moviewiseList =ticketCounterManager.getMovieDetails(ticketCounterBean);
				convertResultToJSON1(moviewiseList, response);
				status = SUCCESS;
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

		private void convertResultToJSON1(ArrayList<TicketCounterBean>moviewiseList,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.flush();
		String temp = new Gson().toJson(moviewiseList);
		out.print(temp);
		out.close();
		
	}
		

	//getMovieWiseScreenDetails
		
	public String getMovieWiseScreenDetails()
		{
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			String str = "getMovieWiseScreenDetails";
			startMethodLogger(str);
			String status = "ticket-counter-error";
			
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			try
			{
				sessionCheck = loginAction.user_SessionCheck();
				if (sessionCheck) 
				{	
				
				
				
				Object ticket_counter_person_id=sessionMap.get("ticket_counter_person_id");
				Object employee_working_theater_id=sessionMap.get("employee_working_theater_id");
				int id = (int) ticket_counter_person_id;
				int employee_theater_id = (int) employee_working_theater_id;
				ticketCounterBean.setTicket_counter_person_id(id);
				ticketCounterBean.setEmployee_working_theater_id(employee_theater_id);
				
				String dateIdVal = request.getParameter("date");				
				ticketCounterBean.setDate(dateIdVal);
				
				System.out.println(dateIdVal);
				
				screenwiseList =ticketCounterManager.getMovieWiseScreenDetails(ticketCounterBean);
				convertResultToJSON1(screenwiseList, response);
				status = SUCCESS;
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

	public TicketCounterBean getTicketCounterBean() {
		return ticketCounterBean;
	}

	public void setTicketCounterBean(TicketCounterBean ticketCounterBean) {
		this.ticketCounterBean = ticketCounterBean;
	}

	public Map<Integer, byte[]> getImageMap() {
		return imageMap;
	}

	public void setImageMap(Map<Integer, byte[]> imageMap) {
		this.imageMap = imageMap;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
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

	public TheaterOwnerBean getTheaterOwnerBean() {
		return theaterOwnerBean;
	}

	public void setTheaterOwnerBean(TheaterOwnerBean theaterOwnerBean) {
		this.theaterOwnerBean = theaterOwnerBean;
	}

	public HashMap<Integer, String> getMovieDateMap() {
		return movieDateMap;
	}

	public void setMovieDateMap(HashMap<Integer, String> movieDateMap) {
		this.movieDateMap = movieDateMap;
	}

	public HashMap<Integer, String> getMovieShowMap() {
		return movieShowMap;
	}

	public void setMovieShowMap(HashMap<Integer, String> movieShowMap) {
		this.movieShowMap = movieShowMap;
	}

	public ArrayList<TheaterOwnerBean> getMovieShowList() {
		return movieShowList;
	}

	public void setMovieShowList(ArrayList<TheaterOwnerBean> movieShowList) {
		this.movieShowList = movieShowList;
	}
   
	

}
