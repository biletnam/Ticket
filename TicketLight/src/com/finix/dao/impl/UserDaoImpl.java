package com.finix.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import com.finix.bean.ScreenDetailBean;
import com.finix.bean.TheaterDetailBean;
import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.TicketCounterBean;
import com.finix.bean.UserBean;
import com.finix.dao.IUserDao;
import com.finix.dao.utils.ConnectionManager;
import com.finix.rabbitmq.Ticket_QueueMain;
import com.finix.rabbitmq.UserTicket_QueueMain;
import com.finix.utils.MobileRandomNum;
import com.finix.utils.MyStringRandomGen;
import com.finix.utils.UniqueId;
import com.sun.mail.imap.protocol.UIDSet;

public class UserDaoImpl implements IUserDao 
{

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
        debugLog.error("User Dao Impl :Method Name "+str );

        debugLog.error("Exception Occured Time " +dateFormatted);
        
        debugLog.error("Type of Exception"+e);
        debugLog.error("***************" );
		
	}
	
	//setUserRegisterDetails
	public UserBean setUserRegisterDetails(UserBean userBean) throws Exception 
	{
		Connection conn = null;
		CallableStatement cl = null;
		try
		{
			conn = connectionManager.getConnection();
		
			String sms_pin = "";
			
			sms_pin = MobileRandomNum.mobileRandomNum();
			
			String id="";
			String uniqueId=UniqueId.getAckId(id);
			uniqueId = "USR"+uniqueId;
			
			String sql = "{CALL Ticket_User_setRegistrationDetails(?,?,?,?,?,?,?)}";
			cl = conn.prepareCall(sql);
			cl.setString(1, userBean.getName());
			cl.setString(2, userBean.getEmail());
			cl.setString(3, userBean.getMobile());
			cl.setString(4, userBean.getPassword());
			cl.setString(5, uniqueId);
			cl.setString(6, sms_pin);
			cl.registerOutParameter(7, Types.INTEGER);
			cl.execute();
			
			userBean.setUser_id(cl.getInt(7));
			userBean.setStatus("success");
			userBean.setSms_pin_status("not_success");
			userBean.setSms_pin(sms_pin);
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "setUserRegisterDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	}

	//setSMSDetails
	public UserBean setSMSDetails(UserBean userBean) throws Exception 
	{

		Connection conn = null;
		CallableStatement cl = null;
		try
		{
			conn = connectionManager.getConnection();
		
			String sms_pin = "";
			sms_pin = MobileRandomNum.mobileRandomNum();
			
			String sql = "{CALL Ticket_USER_setSMSDetails(?,?,?)}";
			cl = conn.prepareCall(sql);
			cl.setInt(1, userBean.getUser_id());
			cl.setString(2, sms_pin);
			cl.registerOutParameter(3, Types.VARCHAR);
			cl.execute();
			
			userBean.setSms_pin(sms_pin);
			userBean.setMobile(cl.getString(3));
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "setSMSDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	
	
	}

	//setAccountActivationDetails
	public UserBean setAccountActivationDetails(UserBean userBean) throws Exception 
	{
		Connection conn = null;
		CallableStatement cl = null;
		try
		{
			conn = connectionManager.getConnection();
			
			String sql = "{CALL Ticket_USER_setAccountActivationDetails(?,?,?)}";
			cl = conn.prepareCall(sql);
			cl.setInt(1, userBean.getUser_id());
			cl.setString(2, userBean.getSms_pin());
			cl.registerOutParameter(3, Types.VARCHAR);
			cl.execute();
			
			userBean.setStatus(cl.getString(3));
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "setAccountActivationDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	}

	//getUserDetails
	public UserBean getUserDetails(UserBean userBean) throws Exception 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = connectionManager.getConnection();
			
			String sql = "SELECT user_name,user_email,user_mobile FROM user_detail WHERE user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userBean.getUser_id());
			rs = ps.executeQuery();
			while(rs.next())
			{
				userBean.setName(rs.getString("user_name"));
				userBean.setEmail(rs.getString("user_email"));
				userBean.setMobile(rs.getString("user_mobile"));
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "getUserDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	}

	//forgetPasswordForUser
	public UserBean forgetPasswordForUser(UserBean userBean) throws Exception 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		PreparedStatement ps1 = null;
		try
		{
			conn = connectionManager.getConnection();
			String randomnumber=MyStringRandomGen.generateRandomString();
			
			String sql = "SELECT activation,user_id FROM login WHERE email = ? AND role_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userBean.getEmail());
			ps.setInt(2, 4);
			rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getString("activation").equals("Y"))
				{
					String sql1 = "UPDATE login SET password = ? where user_id = ?";
					ps1 = conn.prepareStatement(sql1);
					ps1.setString(1, randomnumber);
					ps1.setInt(2, rs.getInt("user_id"));
					ps1.executeUpdate();
					
					String sql2 = "SELECT user_email,user_name FROM user_detail WHERE user_id = ?";
					ps2 = conn.prepareStatement(sql2);
					ps2.setInt(1, rs.getInt("user_id"));
					rs2 = ps2.executeQuery();
					if(rs2.next())
					{
						userBean.setName(rs2.getString("user_name"));
						userBean.setEmail(rs2.getString("user_email"));
					}
					
					userBean.setPassword(randomnumber);
					userBean.setStatus("success");	
				}
				else if(rs.getString("activation").equals("N"))
				{
					userBean.setUser_id(rs.getInt("user_id"));
					userBean.setStatus("failure");
				}
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "forgetPasswordForUser";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	}


    //getSearchCategoryDetail created by ramya - 16-08-18
	public UserBean getSearchCategoryDetail(UserBean userBean) throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		ArrayList<TheaterOwnerBean> theaterList = new ArrayList<TheaterOwnerBean>();
		ArrayList<TheaterOwnerBean> movieList = new ArrayList<TheaterOwnerBean>();
		ArrayList<TheaterOwnerBean> cityList = new ArrayList<TheaterOwnerBean>();
		TheaterOwnerBean theaterOwnerBean = null;
		int i =1;
		try
		{
			conn = connectionManager.getConnection();
			
				String sql = "{CALL Ticket_TO_getSearchCategoryDetail(?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, userBean.getCity_id());
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
				
					 if(userBean.getCity_id() != 0){
						 
						    if(i==1)
							{
								while(rs.next())
								{
									theaterOwnerBean = new TheaterOwnerBean();
									theaterOwnerBean.setTheater_id(rs.getInt("theater_id"));
									theaterOwnerBean.setTheater_name(rs.getString("theater_name")+": "+rs.getString("theater_reference"));
									theaterList.add(theaterOwnerBean);
								}
							}
							if(i==2)
							{
								while(rs.next())
								{
									theaterOwnerBean = new TheaterOwnerBean();
									theaterOwnerBean.setMaster_movie_id(rs.getInt("master_movie_id"));
									theaterOwnerBean.setMovie_name(rs.getString("movie_name"));
									movieList.add(theaterOwnerBean);
								}
							}
							
					 }
					 else{
						    if(i==1)
							{
								while(rs.next())
								{
									theaterOwnerBean = new TheaterOwnerBean();
									theaterOwnerBean.setCity_id(rs.getInt("city_id"));
									theaterOwnerBean.setCity_name(rs.getString("city_name"));
									cityList.add(theaterOwnerBean);
								}
							}
					 }
					i++;
					res = cl.getMoreResults();
				}
				userBean.setTheaterList(theaterList);
				userBean.setMovieList(movieList);
				userBean.setCityList(cityList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String str = "getSearchCategoryDetail";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	}

	//getCityDetail
	public Map<Integer, String> getCityDetail() throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		Map<Integer, String> citymap = new HashMap<Integer, String>();
		int i =1;
		try
		{
			conn = connectionManager.getConnection();
			
				String sql = "{CALL Ticket_TO_getSearchCityDetail()}";
				cl = conn.prepareCall(sql);
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
					
				if(i==1)
				{
					while(rs.next())
					{
						citymap.put(rs.getInt("city_id"), rs.getString("city_name"));

					}
				}
					i++;
					res = cl.getMoreResults();
				}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String str = "getCityDetail";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return citymap;
	}


	//getCitywiseMovieDetail created by ramya - 18-08-18
	public UserBean getCitywiseMovieDetail(UserBean userBean) throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		ArrayList<TheaterOwnerBean> mvList = new ArrayList<TheaterOwnerBean>();
		int i =1;
		TheaterOwnerBean theaterOwnerBean = null;
		try
		{
			conn = connectionManager.getConnection();
			
				String sql = "{CALL Ticket_TO_getCitywiseMovieDetail(?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, userBean.getCity_id());
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
				
				if(i==1)
				{
					while(rs.next())
					{
						theaterOwnerBean = new TheaterOwnerBean();
						theaterOwnerBean.setMaster_movie_id(rs.getInt("master_movie_id"));
						theaterOwnerBean.setMovie_name(rs.getString("movie_name"));
						theaterOwnerBean.setMovie_genre_name(rs.getString("genre_name"));
						theaterOwnerBean.setMovie_languge_name(rs.getString("language_name"));
						theaterOwnerBean.setMovie_certification_name(rs.getString("movie_certification_name"));
						mvList.add(theaterOwnerBean);
					}
				}
					i++;
					res = cl.getMoreResults();
				}
				
				userBean.setMovieList(mvList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String str = "getCitywiseMovieDetail";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	}

	//getMoviewiseTheaterDetail created by ramya - 18-08-18
	public TheaterOwnerBean getMoviewiseTheaterDetail(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		ArrayList<String> genreList = new ArrayList<String>();
		ArrayList<String> languageList = new ArrayList<String>();
		ArrayList<TicketCounterBean> mvDateList = new ArrayList<TicketCounterBean>();
		ArrayList<TheaterDetailBean> theaterList = new ArrayList<TheaterDetailBean>();
        TicketCounterBean ticketCounterBean = null;
        TheaterDetailBean theaterDetailBean = null;
        ScreenDetailBean screenDetailBean = null;
		try
		{
			conn = connectionManager.getConnection();
			
			int firstDateId = 0;
			
				
				String genre = "";
				String language = "";
				String sql = "SELECT m.master_movie_id,m.movie_name,m.duration,m.release_date,s.movie_certification_name,m.genre_id_str,m.language_id_str,l.format_name FROM master_movie_detail m INNER JOIN movie_certification s ON m.certification_id = s.movie_certification_id INNER JOIN movie_format l ON l.movie_format_id = m.format_id WHERE m.master_movie_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getMaster_movie_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterOwnerBean.setMaster_movie_id(rs.getInt("master_movie_id"));
					theaterOwnerBean.setMovie_name(rs.getString("movie_name"));
					theaterOwnerBean.setMovie_certification_name(rs.getString("movie_certification_name"));
					theaterOwnerBean.setMovie_format_name(rs.getString("format_name"));
				    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				    Date date1 = date.parse(rs.getString("release_date"));
				    SimpleDateFormat relDate = new SimpleDateFormat("dd MMM, yyyy");
				    String  releaseDate = relDate.format(date1);
				    theaterOwnerBean.setMovie_release_date(releaseDate);
				    
				    String[] time = rs.getString("duration").split(":");
				    theaterOwnerBean.setMovie_duration(time[0]+" hrs "+time[1]+" mins");
				    genre = rs.getString("genre_id_str");
				    language = rs.getString("language_id_str");
				}
				
				if(genre != "" && language != ""){
					
					String sql1 = "SELECT genre_name FROM movie_genre WHERE genre_id IN ("+genre+")";
					ps = conn.prepareStatement(sql1);
					rs = ps.executeQuery();
					while(rs.next())
					{
						genreList.add(rs.getString("genre_name"));
					}
					
					String sql2 = "SELECT language_name FROM movie_languge WHERE language_id IN ("+language+")";
					ps = conn.prepareStatement(sql2);
					rs = ps.executeQuery();
					while(rs.next())
					{
						languageList.add(rs.getString("language_name"));
					}
					
					theaterOwnerBean.setGenre_list(genreList);
					theaterOwnerBean.setLanguage_list(languageList);
				}
				
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String d = dateFormat.format(date);
				
				String sql3 = "SELECT DISTINCT(show_date_id),show_date FROM theater_show_details  WHERE movie_detail_id IN (SELECT movie_details_id FROM movie_details WHERE master_movie_id = ? AND STATUS = 'Active' AND is_deleted = 'N') AND show_date >= ? ORDER BY show_date ASC LIMIT 8";
				ps = conn.prepareStatement(sql3);
				ps.setInt(1, theaterOwnerBean.getMaster_movie_id());
				ps.setString(2, d);
				rs = ps.executeQuery();
				while(rs.next())
				{
					ticketCounterBean = new TicketCounterBean();
					
					if(theaterOwnerBean.getDate_id() ==0){
							if(firstDateId == 0) {
							firstDateId = rs.getInt("show_date_id");
	                        ticketCounterBean.setStatus("Success");
							}
					}
					else if(theaterOwnerBean.getDate_id() == rs.getInt("show_date_id")){
						firstDateId = theaterOwnerBean.getDate_id();
						ticketCounterBean.setStatus("Success");
					}
					
					ticketCounterBean.setDate_id(rs.getInt("show_date_id"));
					
					String returnDate = dateFormatChangeFunction(rs.getString("show_date"));
					
					String[] spDate = returnDate.split(",");
					ticketCounterBean.setDate(spDate[0]);
					ticketCounterBean.setDayName(spDate[1]);
					/*if(d.equals(rs.getString("show_date"))){
						String[] da = rs.getString("show_date").split("-");
						ticketCounterBean.setDate(da[2]+" TODAY");

					}else{
						Date date2 = dateFormat.parse(rs.getString("show_date"));
						SimpleDateFormat mvDate = new SimpleDateFormat("dd MMM");
						String movieDate = mvDate.format(date2);
						ticketCounterBean.setDate(movieDate);

					}*/
					mvDateList.add(ticketCounterBean);
				}
				theaterOwnerBean.setDate_list(mvDateList);
				

			if(firstDateId != 0){
				String sql4 = "SELECT DISTINCT(m.theater_id),m.theater_name,m.theater_reference FROM theater_detail m INNER JOIN theater_show_details s ON s.theater_id = m.theater_id WHERE s.show_date_id =  ? AND s.movie_detail_id IN (SELECT movie_details_id FROM movie_details WHERE master_movie_id = ? AND STATUS = 'Active' AND is_deleted = 'N')";
				ps = conn.prepareStatement(sql4);				
				ps.setInt(1, firstDateId);
				ps.setInt(2, theaterOwnerBean.getMaster_movie_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterDetailBean = new TheaterDetailBean();
					theaterDetailBean.setTheater_id(rs.getInt("theater_id"));
					theaterDetailBean.setTheater_name(rs.getString("theater_name")+": "+rs.getString("theater_reference"));
					
					String sql5 = "SELECT show_details_id,movie_detail_id,screen_id FROM theater_show_details WHERE theater_id = ? AND show_date_id =  ? AND movie_detail_id IN (SELECT movie_details_id FROM movie_details WHERE master_movie_id = ? AND STATUS = 'Active' AND is_deleted = 'N')";
					ps2 = conn.prepareStatement(sql5);
					ps2.setInt(1, rs.getInt("theater_id"));
					ps2.setInt(2, firstDateId);
					ps2.setInt(3, theaterOwnerBean.getMaster_movie_id());
					rs2 = ps2.executeQuery();
					while(rs2.next())
					{
						ArrayList<ScreenDetailBean> showTimingList = new ArrayList<ScreenDetailBean>();

						String sql6 = "SELECT show_timing_id,show_timing FROM show_timing WHERE show_details_id = ? AND STATUS = 'Active' AND is_deleted = 'N'";
						ps1 = conn.prepareStatement(sql6);				
						ps1.setInt(1, rs2.getInt("show_details_id"));
						rs1 = ps1.executeQuery();
						while(rs1.next())
						{
							screenDetailBean = new ScreenDetailBean();
							screenDetailBean.setMovie_detail_id(rs2.getInt("movie_detail_id"));
							screenDetailBean.setScreen_id(rs2.getInt("screen_id"));
							
							screenDetailBean.setShow_detail_id(rs2.getInt("show_details_id"));
							screenDetailBean.setShow_id(rs1.getInt("show_timing_id"));
							
					    	SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
							SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
							String showTime = date12Format.format(date24Format.parse(rs1.getString("show_timing")));
							screenDetailBean.setShow_timing(showTime);	
					    	
							showTimingList.add(screenDetailBean);
						}
						theaterDetailBean.setShow_list(showTimingList);
					}
					
					theaterList.add(theaterDetailBean);
				}
				theaterOwnerBean.setTheaterDetailList(theaterList);
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "getMoviewiseTheaterDetail";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return theaterOwnerBean;
	}

	private String dateFormatChangeFunction(String passDate) throws ParseException {
		String date = "";

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat Format = new SimpleDateFormat("dd");
	    SimpleDateFormat myFormat = new SimpleDateFormat("dd,EEE");

	    Calendar calendar = Calendar.getInstance();
	    
	    Date today = calendar.getTime();
	 
	    calendar.add(Calendar.DAY_OF_YEAR, 1);
	    
	    Date tomorrow = calendar.getTime();
	    
	    String td_date = dateFormat.format(today);
	    String tm_date = dateFormat.format(tomorrow);
	    Date mdate = dateFormat.parse(passDate);
	    String mydate = dateFormat.format(mdate);
	    if(td_date.equals(mydate)){
	    	
	    	date = Format.format(mdate)+ ",TODAY";
	    }
	    else if(tm_date.equals(mydate)){
	    	
	    	date = Format.format(mdate)+ ",TOM";

	    }
	    else{
	    	
	    	date = myFormat.format(mdate);

	    }
		
		return date;
	}

	//getTheaterDetail created by ramya - 18-08-18
	public TheaterOwnerBean getSearchTheaterDetail(TheaterOwnerBean theaterOwnerBean)
			throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		ArrayList<TheaterDetailBean> theaterList = new ArrayList<TheaterDetailBean>();
		TheaterDetailBean theaterDetailBean = null;
		int i =1;
		try
		{
			conn = connectionManager.getConnection();
			
				String sql = "{CALL Ticket_TO_getSearchTheaterDetail(?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getCity_id());
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
				
				if(i==1)
				{
					while(rs.next())
					{
						theaterDetailBean = new TheaterDetailBean();
						theaterDetailBean.setTheater_id(rs.getInt("theater_id"));
						theaterDetailBean.setTheater_name(rs.getString("theater_name")+": "+rs.getString("theater_reference"));
						theaterList.add(theaterDetailBean);
					}
				}
					i++;
					res = cl.getMoreResults();
				}
				theaterOwnerBean.setTheaterDetailList(theaterList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String str = "getSearchTheaterDetail";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return theaterOwnerBean;
	}

   //getCityMovieDetail created by ramya - 20-08-18
	public TheaterOwnerBean getCityMovieDetail(TheaterOwnerBean theaterOwnerBean)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		ArrayList<TheaterDetailBean> movieList = new ArrayList<TheaterDetailBean>();
		TheaterDetailBean theaterDetailBean = null;
		try
		{
			conn = connectionManager.getConnection();
			
			String movieIdStr = "";
			String sql = "SELECT l.master_movie_id FROM master_movie_detail l INNER JOIN movie_details s ON s.master_movie_id = l.master_movie_id INNER JOIN theater_show_details m ON m.movie_detail_id = s.movie_details_id WHERE m.theater_id IN (SELECT k.theater_id FROM theater_detail k WHERE k.city_id = ?) GROUP BY s.movie_details_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, theaterOwnerBean.getCity_id());
			rs = ps.executeQuery();
			while(rs.next())
			{
				movieIdStr += rs.getInt("master_movie_id")+",";
			}
			
			if(movieIdStr != ""){
				
				movieIdStr = movieIdStr.substring(0,movieIdStr.length()-1);
				
				String sql1 = "SELECT m.master_movie_id,m.movie_name,m.duration,m.release_date,s.movie_certification_name,m.genre_id_str,m.language_id_str,l.format_name FROM master_movie_detail m INNER JOIN movie_certification s ON m.certification_id = s.movie_certification_id INNER JOIN movie_format l ON l.movie_format_id = m.format_id WHERE m.master_movie_id IN ("+movieIdStr+")";
				ps = conn.prepareStatement(sql1);
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterDetailBean = new TheaterDetailBean(); 
					theaterDetailBean.setMaster_movie_id(rs.getInt("master_movie_id"));
					theaterDetailBean.setMovie_name(rs.getString("movie_name"));
					theaterDetailBean.setMovie_certification_name(rs.getString("movie_certification_name"));
				    String language_id = rs.getString("language_id_str");
				    String genre_id = rs.getString("genre_id_str");
				    String language = ""; 
				    String genre = ""; 

					String sql2 = "SELECT language_name FROM movie_languge WHERE language_id IN ("+language_id+")";
					ps1 = conn.prepareStatement(sql2);
					rs1 = ps1.executeQuery();
					while(rs1.next())
					{
						language = rs1.getString("language_name")+", ";
					}
					
					String sql3 = "SELECT genre_name FROM movie_genre WHERE genre_id IN ("+genre_id+")";
					ps1 = conn.prepareStatement(sql3);
					rs1 = ps1.executeQuery();
					while(rs1.next())
					{
						genre = rs1.getString("genre_name")+", ";
					}
					if(language != "" && genre != ""){
						
						language = language.substring(0,language.length()-2);
						genre    = genre.substring(0,genre.length()-2);
                        theaterDetailBean.setMovie_language(language);
                        theaterDetailBean.setMovie_genre(genre);
					}
					
					movieList.add(theaterDetailBean);

				}
				theaterOwnerBean.setMovieList(movieList);
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "getCityMovieDetail";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return theaterOwnerBean;
	}

	//getTheaterMovieDetail created by ramya -20-08-18
	public TheaterOwnerBean getTheaterMovieDetail(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		ArrayList<TicketCounterBean> mvDateList = new ArrayList<TicketCounterBean>();
		ArrayList<TheaterDetailBean> movieList = new ArrayList<TheaterDetailBean>();
        TicketCounterBean ticketCounterBean = null;
        TheaterDetailBean theaterDetailBean = null;
        ScreenDetailBean screenDetailBean = null;
		try
		{
			conn = connectionManager.getConnection();
			
			int firstDateId = 0;

				String sql = "SELECT theater_id,theater_name,theater_reference,address FROM theater_detail WHERE theater_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getTheater_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterOwnerBean.setTheater_id(rs.getInt("theater_id"));
					theaterOwnerBean.setTheater_name(rs.getString("theater_name")+": "+rs.getString("theater_reference"));
					theaterOwnerBean.setTheater_address(rs.getString("address"));
			
				}
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String d = dateFormat.format(date);
				
				String sql3 = "SELECT DISTINCT(show_date_id),show_date FROM theater_show_details  WHERE theater_id = ? AND show_date >= ? ORDER BY show_date ASC LIMIT 8";
				ps = conn.prepareStatement(sql3);
				ps.setInt(1, theaterOwnerBean.getTheater_id());
				ps.setString(2, d);
				rs = ps.executeQuery();
				while(rs.next())
				{
					ticketCounterBean = new TicketCounterBean();
					
					
					if(theaterOwnerBean.getDate_id() == 0){
						if(firstDateId == 0) {
						firstDateId = rs.getInt("show_date_id");
                        ticketCounterBean.setStatus("Success");
						}
					}
					else if(theaterOwnerBean.getDate_id() == rs.getInt("show_date_id")){
						firstDateId = theaterOwnerBean.getDate_id();
						ticketCounterBean.setStatus("Success");
					}
				
					ticketCounterBean.setDate_id(rs.getInt("show_date_id"));
	                String returnDate = dateFormatChangeFunction(rs.getString("show_date"));
					
					String[] spDate = returnDate.split(",");
					ticketCounterBean.setDate(spDate[0]);
					ticketCounterBean.setDayName(spDate[1]);
					
				/*	if(d.equals(rs.getString("show_date"))){
						String[] da = rs.getString("show_date").split("-");
						ticketCounterBean.setDate(da[2]+" TODAY");

					}else{
						Date date2 = dateFormat.parse(rs.getString("show_date"));
						SimpleDateFormat mvDate = new SimpleDateFormat("dd MMM");
						String movieDate = mvDate.format(date2);
						ticketCounterBean.setDate(movieDate);

					}*/
					mvDateList.add(ticketCounterBean);
				}
				theaterOwnerBean.setDate_list(mvDateList);
			
			
			if(firstDateId != 0){
				String sql4 = "SELECT DISTINCT(s.movie_details_id),l.movie_name,h.language_name,f.format_name,l.master_movie_id,m.theater_id FROM master_movie_detail l INNER JOIN movie_details s ON s.master_movie_id = l.master_movie_id INNER JOIN theater_show_details m ON m.movie_detail_id = s.movie_details_id INNER JOIN movie_format f ON f.movie_format_id = m.format_id INNER JOIN movie_languge h ON h.language_id = m.movie_language_id WHERE m.theater_id = ? AND m.show_date_id = ?";
				ps = conn.prepareStatement(sql4);	
				ps.setInt(1, theaterOwnerBean.getTheater_id());
				ps.setInt(2, firstDateId);
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterDetailBean = new TheaterDetailBean();
					theaterDetailBean.setMovie_detail_id(rs.getInt("movie_details_id"));
					theaterDetailBean.setMaster_movie_id(rs.getInt("master_movie_id"));
					theaterDetailBean.setMovie_name(rs.getString("movie_name"));
					theaterDetailBean.setMovie_language(rs.getString("language_name"));
					theaterDetailBean.setMovie_format(rs.getString("format_name"));
					theaterDetailBean.setTheater_id(rs.getInt("theater_id"));
					
					ArrayList<ScreenDetailBean> showTimingList = new ArrayList<ScreenDetailBean>();

					String sql6 = "SELECT DISTINCT(screen_id) FROM theater_show_details WHERE theater_id = ? AND show_date_id = ?";
					ps2 = conn.prepareStatement(sql6);	
					ps2.setInt(1, theaterOwnerBean.getTheater_id());
					ps2.setInt(2, firstDateId);
					rs2 = ps2.executeQuery();
					while(rs2.next())
					{
						String sql5 = "SELECT s.show_details_id,s.show_timing_id,s.show_timing FROM show_timing s INNER JOIN theater_show_details m ON m.show_details_id = s.show_details_id WHERE m.theater_id = ? AND m.screen_id = ? AND m.show_date_id = ? AND m.movie_detail_id = ? AND s.status = 'Active' AND s.is_deleted  = 'N'";
						ps1 = conn.prepareStatement(sql5);				
						ps1.setInt(1, theaterOwnerBean.getTheater_id());
						ps1.setInt(2, rs2.getInt("screen_id"));
						ps1.setInt(3, firstDateId);
						ps1.setInt(4, theaterDetailBean.getMovie_detail_id());
						rs1 = ps1.executeQuery();
						while(rs1.next())
						{
							screenDetailBean = new ScreenDetailBean();
							screenDetailBean.setScreen_id(rs2.getInt("screen_id"));
							screenDetailBean.setShow_detail_id(rs1.getInt("show_details_id"));
							screenDetailBean.setShow_id(rs1.getInt("show_timing_id"));
							SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
							SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
							String showTime = date12Format.format(date24Format.parse(rs1.getString("show_timing")));
							screenDetailBean.setShow_timing(showTime);
							showTimingList.add(screenDetailBean);
						}
						theaterDetailBean.setShow_list(showTimingList);
					}
					
					
					movieList.add(theaterDetailBean);
				}
				theaterOwnerBean.setTheaterDetailList(movieList);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "getMoviewiseTheaterDetail";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return theaterOwnerBean;
	}

	//getMasterMoviePosterImage created by ramya - 20-08-18
	public Map<Integer, byte[]> getMasterMoviePosterImage(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		ResultSet rs = null;
		Map<Integer, byte[]> moviePosterMap = new HashMap<Integer,byte[]>();
		try
		{
			conn = connectionManager.getConnection();
			String sql = "{CALL Ticket_TO_getMasterMoviePosterImage(?)}";
			cl = conn.prepareCall(sql);
			cl.setInt(1, theaterOwnerBean.getMaster_movie_id());
			cl.execute();
			rs = cl.executeQuery();
			if(rs.next())
			{
				moviePosterMap.put(rs.getInt("master_movie_id"), rs.getBytes("movie_poster"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String str = "getMasterMoviePosterImage";
			catchMethodLogger(str, e);
		}
		finally {
			if (conn != null) {
				connectionManager.releaseConnection(conn);
			}
		}
		return moviePosterMap;
	}

	//getMasterMovieBgPosterImage created by ramya - 20-08-18
	public Map<Integer, byte[]> getMasterMovieBgPosterImage(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		ResultSet rs = null;
		Map<Integer, byte[]> movieBgPosterMap = new HashMap<Integer,byte[]>();
		try
		{
			conn = connectionManager.getConnection();
			String sql = "{CALL Ticket_TO_getMasterMovieBgPosterImage(?)}";
			cl = conn.prepareCall(sql);
			cl.setInt(1, theaterOwnerBean.getMaster_movie_id());
			cl.execute();
			rs = cl.executeQuery();
			if(rs.next())
			{
				movieBgPosterMap.put(rs.getInt("master_movie_id"), rs.getBytes("movie_bg_poster"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String str = "getMasterMovieBgPosterImage";
			catchMethodLogger(str, e);
		}
		finally {
			if (conn != null) {
				connectionManager.releaseConnection(conn);
			}
		}
		return movieBgPosterMap;
	}

	//getMovieBgPosterCountDetail created by ramya - 21-08-18
	public TheaterOwnerBean getMovieBgPosterCountDetail(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		Connection conn = null;
		CallableStatement cl = null;
		ArrayList<Integer> movieCountList = new ArrayList<Integer>();
		int i =1;
		try
		{
			conn = connectionManager.getConnection();
			String sql = "{CALL Ticket_TO_getMovieBgPosterCountDetail()}";
			cl = conn.prepareCall(sql);
			boolean res = cl.execute();
			while(res)
			{
				ResultSet rs = cl.getResultSet();
			
			if(i==1)
			{
				while(rs.next())
				{
					movieCountList.add(rs.getInt("master_movie_id"));
				}
			}
				i++;
				res = cl.getMoreResults();
			}
			theaterOwnerBean.setMovie_detail_id_list(movieCountList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String str = "getMovieBgPosterCountDetail";
			catchMethodLogger(str, e);
		}
		finally {
			if (conn != null) {
				connectionManager.releaseConnection(conn);
			}
		}
		return theaterOwnerBean;
	}

	//get screen wise details
	public UserBean getScreenwiseSeatingDetails(UserBean userBean) throws Exception {
		PreparedStatement ps=null;
		ResultSet rs=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;
		PreparedStatement ps2=null;
		ResultSet rs2=null;
		Connection conn=null;
		ArrayList<TheaterDetailBean> finalList = new ArrayList<TheaterDetailBean>();
		ArrayList<ScreenDetailBean> subList = new ArrayList<ScreenDetailBean>();
		ArrayList<TicketCounterBean> seatList = new ArrayList<TicketCounterBean>();
		ArrayList<String> soldedSeatsList = new ArrayList<String>();

		try
		{
			conn = connectionManager.getConnection();
			
			int seat_category_start_id = 0;
			int seat_category_end_id = 0;
			int order_id = 0;
			int passage_count = 0;
			int seat_count = 0;
			int theatre_owner_id=0;

			String solded_seats = "";
			
			String owner="select theater_owner_id from theater_detail where theater_id=?";
			ps2 = conn.prepareStatement(owner);
			ps2.setInt(1, userBean.getTheater_id());
			rs2 = ps2.executeQuery();
			while(rs2.next()) {
				
				theatre_owner_id = rs2.getInt("theater_owner_id");
			}
			
			
			String sql6 = "SELECT seat_id FROM movie_ticket_sold_details WHERE theater_id =? AND screen_id =? AND show_timing_id = ? AND movie_detail_id = ? AND show_detail_id = ? AND STATUS = 'Active' AND is_deleted = 'N'";
			ps = conn.prepareStatement(sql6);
			ps.setInt(1, userBean.getTheater_id());
			ps.setInt(2, userBean.getScreen_id());
			ps.setInt(3, userBean.getShow_timing_id());
			ps.setInt(4, userBean.getMovie_details_id());
			ps.setInt(5, userBean.getShow_detail_id());
			rs = ps.executeQuery();
			while(rs.next()) {
				
				solded_seats = rs.getString("seat_id");
			}
			
			String soldedSeatsArray[] = solded_seats.split(",");
			
			 for(int j=0;j < soldedSeatsArray.length;j++) {
					
				 soldedSeatsList.add(soldedSeatsArray[j]);
			}
			 
			String sql = "SELECT seat_category_start_id,seat_category_end_id,order_id FROM screen_seat_category_detail WHERE screen_id = ? AND theater_id = ?  AND theater_owner_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userBean.getScreen_id());
			ps.setInt(2, userBean.getTheater_id());
			ps.setInt(3, theatre_owner_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				seat_category_start_id = rs.getInt("seat_category_start_id");
				seat_category_end_id = rs.getInt("seat_category_end_id");
				order_id = rs.getInt("order_id");
			}
			
			String sql2 = "SELECT passage_count FROM screen_detail WHERE screen_id = ? AND theater_id = ?";
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, userBean.getScreen_id());
			ps.setInt(2, userBean.getTheater_id());
			rs = ps.executeQuery();
			while(rs.next()) {
				
				passage_count = rs.getInt("passage_count");
			}
			
			String sql4 = "SELECT seat_count FROM `floor_plan_detail` WHERE theater_id = ? AND screen_id = ? ORDER BY seat_count DESC LIMIT 1";
			ps = conn.prepareStatement(sql4);
			ps.setInt(1, userBean.getTheater_id());
			ps.setInt(2, userBean.getScreen_id());
			rs = ps.executeQuery();
			while(rs.next()) {
				
				seat_count = rs.getInt("seat_count");
			}
			
			if(order_id == 1) {
				
				String sql1 = "SELECT seat_category,seat_category_id FROM seat_category WHERE seat_category_id BETWEEN ? AND ? ORDER BY seat_category_id ASC";
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, seat_category_start_id);
				ps.setInt(2, seat_category_end_id);
				rs = ps.executeQuery();
				while(rs.next()) {
					
					TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
					subList = new ArrayList<ScreenDetailBean>();
					theaterDetailBean.setCategory_name(rs.getString("seat_category"));
					theaterDetailBean.setCol_count(passage_count);
					theaterDetailBean.setTotal_seat_count(seat_count);
					
					/*String sql5 = "SELECT seat_count,seating_order_id FROM floor_plan_detail WHERE screen_id = ? AND theater_id = ? AND seat_category_id = ? ORDER BY column_id ASC";*/
					String sql5 = "SELECT seat_id,seat_count,seating_order_id FROM floor_plan_detail WHERE screen_id = ? AND theater_id = ? AND seat_category_id = ? ORDER BY column_id ASC";
					ps1 = conn.prepareStatement(sql5);
					ps1.setInt(1, userBean.getScreen_id());
					ps1.setInt(2, userBean.getTheater_id());
					ps1.setInt(3, rs.getInt("seat_category_id"));
					rs1 = ps1.executeQuery();
					while(rs1.next()) {
						
						seatList = new ArrayList<TicketCounterBean>();
						
						ScreenDetailBean screenDetailBean = new ScreenDetailBean();
						screenDetailBean.setSeat_count(rs1.getInt("seat_count"));
						screenDetailBean.setOrder_id(rs1.getInt("seating_order_id"));
						
						String seat_id_str = rs1.getString("seat_id");
						String array[] = seat_id_str.split(",");
						
						for(int i=0;i < seat_id_str.length();i++) {
							
							TicketCounterBean ticketCounterBean = new TicketCounterBean();
								
							if(soldedSeatsList.contains(array[i])){
									
							    ticketCounterBean.setSeat_status_id(3);
							}
							ticketCounterBean.setSeat_name_str(array[i]);
							seatList.add(ticketCounterBean);
						}
						screenDetailBean.setSeatList(seatList);
						subList.add(screenDetailBean);
					}
					theaterDetailBean.setScreenList(subList);
					finalList.add(theaterDetailBean);
				}
			}
			else {
				
				String sql1 = "SELECT seat_category,seat_category_id FROM seat_category WHERE seat_category_id BETWEEN ? AND ? ORDER BY seat_category_id DESC;";
				ps = conn.prepareStatement(sql1);
				ps.setInt(1, seat_category_start_id);
				ps.setInt(2, seat_category_end_id);
				rs = ps.executeQuery();
				while(rs.next()) {
					
					TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
					subList = new ArrayList<ScreenDetailBean>();
					theaterDetailBean.setCategory_name(rs.getString("seat_category"));
					theaterDetailBean.setCol_count(passage_count);
					theaterDetailBean.setTotal_seat_count(seat_count);
					
					/*String sql5 = "SELECT seat_count,seating_order_id FROM floor_plan_detail WHERE screen_id = ? AND theater_id = ? AND seat_category_id = ? ORDER BY column_id ASC";*/
					String sql5 = "SELECT seat_id,seat_count,seating_order_id FROM floor_plan_detail WHERE screen_id = ? AND theater_id = ? AND seat_category_id = ? ORDER BY column_id ASC";
					ps1 = conn.prepareStatement(sql5);
					ps1.setInt(1, userBean.getScreen_id());
					ps1.setInt(2, userBean.getTheater_id());
					ps1.setInt(3, rs.getInt("seat_category_id"));
					rs1 = ps1.executeQuery();
					while(rs1.next()) {
						
						seatList = new ArrayList<TicketCounterBean>();
						
						ScreenDetailBean screenDetailBean = new ScreenDetailBean();
						screenDetailBean.setSeat_count(rs1.getInt("seat_count"));
						screenDetailBean.setOrder_id(rs1.getInt("seating_order_id"));
						
						String seat_id_str = rs1.getString("seat_id");
						String array[] = seat_id_str.split(",");
						
						for(int i=0;i < array.length;i++) {
							
							TicketCounterBean ticketCounterBean = new TicketCounterBean();
					
							if(soldedSeatsList.contains(array[i])){
								
							    ticketCounterBean.setSeat_status_id(3);
							}
							ticketCounterBean.setSeat_name_str(array[i].trim());
							seatList.add(ticketCounterBean);
						}
						screenDetailBean.setSeatList(seatList);
						subList.add(screenDetailBean);
					}
					theaterDetailBean.setScreenList(subList);
					finalList.add(theaterDetailBean);
				}
			}
			
			userBean.setTheaterRowList(finalList);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String str = "getSeatingArrangementDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	}

	
	
	@SuppressWarnings("unused")
	public UserBean getTicketSoldDetail(UserBean userBean) throws Exception 
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;
		PreparedStatement ps2=null;
		ResultSet rs2=null;
		PreparedStatement ps3=null;
		ResultSet rs3=null;
		String seatStr = "";
		String bookingSeatStr = "";
		try {
			
			 
			String ticket_status=UserTicket_QueueMain.ticketQueue(userBean);
				
				return userBean;
				
			
			/*conn = connectionManager.getConnection();
			
			
			String insert_ticket="insert into individual_movie_ticket_sold_details(theatre_id,screen_id,show_timing_id,movie_detail_id,show_date_id,user_id,user_role_id,seat_id,ticket_amount,is_deleted,status,create_date) values(?,?,?,?,?,?,?,?,?,?,?,now())";
			ps3= conn.prepareStatement(insert_ticket);
			ps3.setInt(1, userBean.getTheater_id());
			ps3.setInt(2, userBean.getScreen_id());
			ps3.setInt(3, userBean.getShow_timing_id());
			ps3.setInt(4, userBean.getMovie_details_id());
			ps3.setString(5, userBean.getBooking_date());
			ps3.setInt(6, userBean.getUser_id());
			ps3.setString(7, "End user");
			ps3.setString(8, userBean.getBooking_seat());
			ps3.setString(9, userBean.getTotal_price());
			ps3.setString(10,"N");
			ps3.setString(11,"Active");
			ps3.executeUpdate();
			
			
			
			String sql="SELECT seat_id FROM  movie_ticket_sold_details WHERE theater_id=? AND screen_id=? AND show_timing_id=? AND movie_detail_id=? ";
			ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, userBean.getTheater_id());
			ps1.setInt(2, userBean.getScreen_id());
			ps1.setInt(3, userBean.getShow_timing_id());
			ps1.setInt(4, userBean.getMovie_details_id());
			rs1 = ps1.executeQuery();
			
			if(rs1.next()) {
				
				seatStr=rs1.getString("seat_id");
				bookingSeatStr=seatStr+",";
				bookingSeatStr=bookingSeatStr.concat(userBean.getBooking_seat());
						
				
			String update_ticket="UPDATE movie_ticket_sold_details SET seat_id=?,show_date_id=? WHERE theater_id=? AND screen_id=? AND show_timing_id=? AND movie_detail_id=?";
			ps2 = conn.prepareStatement(update_ticket);
			ps2.setString(1,bookingSeatStr);
			ps2.setString(2, userBean.getBooking_date() );
			ps2.setInt(3, userBean.getTheater_id());
			ps2.setInt(4, userBean.getScreen_id());
			ps2.setInt(5, userBean.getShow_timing_id());
			ps2.setInt(6, userBean.getMovie_details_id());
			ps2.executeUpdate();
			}
			
			else {
			String ticket_details="insert into movie_ticket_sold_details(theater_id,screen_id,show_timing_id,show_date_id,movie_detail_id,show_detail_id,seat_id,is_deleted,status,created_on) values(?,?,?,?,?,?,?,?,?,now())";
			ps = conn.prepareStatement(ticket_details);
			ps.setInt(1, userBean.getTheater_id());
			ps.setInt(2, userBean.getScreen_id());
			ps.setInt(3, userBean.getShow_timing_id());
			ps.setString(4, userBean.getBooking_date());
			ps.setInt(5, userBean.getMovie_details_id());
			ps.setInt(6, userBean.getShow_detail_id());
			ps.setString(7, userBean.getBooking_seat());
			ps.setString(8,"N");
			ps.setString(9,"Active");
			ps.executeUpdate();
			}*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return userBean;
	}

	//getOrderSummaryDetails
	public UserBean getOrderSummaryDetails(UserBean userBean) throws Exception 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			conn = connectionManager.getConnection();
			
			String sql = "SELECT screen_name FROM screen_detail WHERE screen_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userBean.getScreen_id());
			rs = ps.executeQuery();
			if(rs.next())
			{
				userBean.setScreen_name(rs.getString("screen_name"));
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String str = "getOrderSummaryDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return userBean;
	}

}
