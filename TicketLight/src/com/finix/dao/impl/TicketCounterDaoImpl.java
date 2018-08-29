package com.finix.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.finix.bean.TheaterDetailBean;
import com.finix.bean.TicketCounterBean;
import com.finix.dao.ITicketCounterDao;
import com.finix.dao.utils.ConnectionManager;

public class TicketCounterDaoImpl implements ITicketCounterDao{

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
	
	//getTicketCounterPersonDetails created by ramya - 09-08-18
	public TicketCounterBean getTicketCounterPersonDetails(
			TicketCounterBean ticketCounterBean) throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		int i =1;
		try {
			
			  conn = connectionManager.getConnection();
			  
			  String sql = "{CALL Ticket_TO_getTicketCounterPersonDetails(?)}";
			  cl = conn.prepareCall(sql);
			  cl.setInt(1, ticketCounterBean.getTicket_counter_person_id());
			  boolean res = cl.execute();
			  while(res)
			  {
					ResultSet rs = cl.getResultSet();
					
					if(i==1)
					{
						while(rs.next())
						{
							 ticketCounterBean.setTicket_counter_person_id(rs.getInt("theater_employee_id"));
							 ticketCounterBean.setTicket_counter_person_name(rs.getString("theater_employee_name"));
							 ticketCounterBean.setTicket_counter_person_mobile(rs.getString("theater_employee_mobile"));
							 ticketCounterBean.setTicket_counter_person_email(rs.getString("theater_employee_mail"));
							 ticketCounterBean.setEmployee_id(rs.getString("employee_id"));
							 ticketCounterBean.setEmployee_role_id(rs.getInt("theater_employee_role_id"));
							 ticketCounterBean.setEmployee_working_theater_owner_id(rs.getInt("created_user_id"));
							 ticketCounterBean.setEmployee_working_theater_id(rs.getInt("theater_id"));
							
						}
					}
					
					i++;
					res = cl.getMoreResults();
				}
			  
			
		} catch (Exception e) {
			e.printStackTrace();
			String str = "getTicketCounterPersonDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return ticketCounterBean;
	}

	//getTicketCounterPersonProfileImage created by ramya -09-08-18
	public Map<Integer, byte[]> getTicketCounterPersonProfileImage(
			TicketCounterBean ticketCounterBean) throws Exception {
		Connection conn = null;
		CallableStatement cl =null;
		Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
		int i =1;
		try
		{
			conn = connectionManager.getConnection();
			
		      String sql = "{CALL Ticket_TO_getTicketCounterPersonProfileImage(?)}";
			  cl = conn.prepareCall(sql);
			  cl.setInt(1, ticketCounterBean.getTicket_counter_person_id());
			  boolean res = cl.execute();
			  while(res)
			  {
					ResultSet rs = cl.getResultSet();
					
					if(i==1)
					{
						while(rs.next())
						{
							imageMap.put(rs.getInt("theater_employee_id"), rs.getBytes("employee_profile"));
						}
					}
					
					i++;
					res = cl.getMoreResults();
			 }
			  
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String str = "getTicketCounterPersonProfileImage";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return imageMap;
	}

	//getMovieDetails
		public ArrayList<TicketCounterBean> getMovieDetails(TicketCounterBean ticketCounterBean) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<TicketCounterBean>moviewiseList=new ArrayList<TicketCounterBean>();
		try 
		{
			conn = connectionManager.getConnection();

			/*int showdateId = Integer.parseInt(ticketCounterBean.getDate().replace("-",""));*/
			
			String[] date = ticketCounterBean.getDate().split("-");
			int startDate = Integer.parseInt(date[0].replace("/", "").trim());
			int endDate = Integer.parseInt(date[1].replace("/", "").trim());
			
			if(startDate == endDate){
				
				String sql ="SELECT m.movie_name,i.ticket_amount,i.seat_id FROM movie_details m INNER JOIN individual_movie_ticket_sold_details i ON m.movie_details_id=i.movie_detail_id WHERE theatre_id=? AND show_date_id=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ticketCounterBean.getEmployee_working_theater_id());
				ps.setInt(2, startDate);
			}
			else{
				String sql =" SELECT m.movie_name,i.ticket_amount,i.seat_id FROM movie_details m INNER JOIN individual_movie_ticket_sold_details i ON m.movie_details_id=i.movie_detail_id WHERE theatre_id=? AND show_date_id BETWEEN  ? AND ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, ticketCounterBean.getEmployee_working_theater_id());
				ps.setInt(2, startDate);
				ps.setInt(3, endDate);

			}
			rs = ps.executeQuery();
			while(rs.next())
			{
				ticketCounterBean = new TicketCounterBean();
				ticketCounterBean.setMovie_name(rs.getString("movie_name"));
				ticketCounterBean.setTicket_amount(rs.getInt("ticket_amount"));
				String seatStr =  rs.getString("seat_id");
				if(seatStr !="")
				{
					String[] seat = seatStr.split(",");
				    ticketCounterBean.setSeat_count(seat.length);
				}
				
				moviewiseList.add(ticketCounterBean);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return moviewiseList;
	}

	//getMovieWiseScreenDetails
		
	public ArrayList<TicketCounterBean> getMovieWiseScreenDetails(TicketCounterBean ticketCounterBean) throws Exception {
	Connection conn = null;
	PreparedStatement ps = null;
	PreparedStatement ps1 = null; 
	ResultSet rs = null;
	ResultSet rs1 = null;
	ArrayList<TicketCounterBean>screenwiseList=new ArrayList<TicketCounterBean>();
	//TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
	try 
	{
		conn = connectionManager.getConnection();
		
		/*int showdateId = Integer.parseInt(ticketCounterBean.getDate().replace("-",""));*/
		
		String[] date = ticketCounterBean.getDate().split("-");
		int startDate = Integer.parseInt(date[0].replace("/", "").trim());
		int endDate = Integer.parseInt(date[1].replace("/", "").trim());
		
		if(startDate == endDate){
		String sql = "SELECT s.screen_id,s.screen_name,t.seat_id,SUM(t.ticket_amount) AS ticket_amount FROM screen_detail s INNER JOIN individual_movie_ticket_sold_details t ON s.screen_id=t.screen_id WHERE s.theatre_id=? AND show_date_id=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, ticketCounterBean.getEmployee_working_theater_id());
		ps.setInt(2, startDate);
		}
		else{
			String sql = "SELECT s.screen_id,s.screen_name,t.seat_id,SUM(t.ticket_amount) AS ticket_amount FROM screen_detail s INNER JOIN individual_movie_ticket_sold_details t ON s.screen_id=t.screen_id WHERE s.theatre_id=? AND show_date_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ticketCounterBean.getEmployee_working_theater_id());
			ps.setInt(2, startDate);
			ps.setInt(3, endDate);
	
		}
		rs = ps.executeQuery();
		while(rs.next())
		{
			ticketCounterBean = new TicketCounterBean();
			ticketCounterBean.setScreen_id(rs.getInt("screen_id"));
			ticketCounterBean.setScreen_name(rs.getString("screen_name"));
			ticketCounterBean.setTicket_amount(rs.getInt("ticket_amount"));
			String seatStr =  rs.getString("seat_id");
			if(seatStr !="")
			{
				String[] seat = seatStr.split(",");
			    ticketCounterBean.setSeat_count(seat.length);
			}
			
			
	ArrayList<TheaterDetailBean>showwiseList=new ArrayList<TheaterDetailBean>();
		
		String sql1 = "SELECT s.screen_name,sh.show_timing,i.ticket_amount FROM screen_detail s INNER JOIN individual_movie_ticket_sold_details i ON s.screen_id=i.screen_id INNER JOIN show_timing sh ON sh.show_timing_id=i.show_timing_id WHERE s.screen_id=?";
		ps1 = conn.prepareStatement(sql1);
		ps1.setInt(1, ticketCounterBean.getScreen_id());
		rs1 = ps1.executeQuery();
		while(rs1.next())
		{
			TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
			theaterDetailBean.setScreen_name(rs1.getString("screen_name"));
			theaterDetailBean.setTicket_amount(rs1.getInt("ticket_amount"));			
			SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
			SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
			String showTime = date12Format.format(date24Format.parse(rs1.getString("show_timing")));
			theaterDetailBean.setShow_timing(showTime);
			showwiseList.add(theaterDetailBean);			
		}
		ticketCounterBean.setShowwiseList(showwiseList);
		screenwiseList.add(ticketCounterBean);
	}
		
} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
			return screenwiseList;
		}

}