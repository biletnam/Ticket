package com.finix.dao.impl;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
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

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;

import com.finix.bean.ScreenDetailBean;
import com.finix.bean.TemplateBean;
import com.finix.bean.TheaterDetailBean;
import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.TicketCounterBean;
import com.finix.dao.ITheaterOwnerDao;
import com.finix.dao.utils.ConnectionManager;
import com.finix.rabbitmq.Ticket_QueueMain;
import com.finix.utils.MobileRandomNum;
import com.finix.utils.MyStringRandomGen;
import com.finix.utils.UniqueId;

public class TheaterOwnerDaoImpl implements ITheaterOwnerDao
{

	private static ConnectionManager connectionManager = ConnectionManager.getConnectionManager();
	final Logger debugLog = Logger.getLogger("debugLogger");
	private SessionMap<String, Object> sessionMap;
	
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
	
	//setRegistrationDetails
	public TheaterOwnerBean setRegistrationDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		Connection conn = null;
		CallableStatement cl = null;
		try
		{
			conn = connectionManager.getConnection();
			
			String mail_pin = "";
			String sms_pin = "";
			mail_pin = MyStringRandomGen.generateRandomString();
			
			
			sms_pin = MobileRandomNum.mobileRandomNum();
			
			String id="";
			String uniqueId=UniqueId.getAckId(id);
			uniqueId = "TO"+uniqueId;
			
			String sql = "{CALL Ticket_TO_setRegistrationDetails(?,?,?,?,?,?,?,?,?)}";
			cl = conn.prepareCall(sql);
			cl.setString(1, theaterOwnerBean.getTheater_owner_first_name());
			cl.setString(2, theaterOwnerBean.getTheater_owner_last_name());
			cl.setString(3, theaterOwnerBean.getTheater_owner_email());
			cl.setString(4, theaterOwnerBean.getTheater_owner_mobile());
			cl.setString(5, theaterOwnerBean.getPassowrd());
			cl.setString(6, mail_pin);
			cl.setString(7, sms_pin);
			cl.registerOutParameter(8, Types.INTEGER);
			cl.setString(9, uniqueId);
			cl.execute();
			
			theaterOwnerBean.setTheater_owner_id(cl.getInt(8));
			theaterOwnerBean.setMail_pin(mail_pin);
			theaterOwnerBean.setSms_pin(sms_pin);
			theaterOwnerBean.setStatus("success");
			theaterOwnerBean.setMail_pin_status("not_success");
			theaterOwnerBean.setSms_pin_status("not_success");
			theaterOwnerBean.setUnique_id(uniqueId);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "setRegistrationDetails";
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

	//getRegistrationMailTemplate
	public TemplateBean getRegistrationMailTemplate(TemplateBean templateBean) throws Exception 
	{
		Connection conn = null;
		CallableStatement cl = null;
		int i = 1;
		try
		{
			conn = connectionManager.getConnection();
			
			String sql = "{CALL Ticket_TO_getRegistrationMailTemplate(?)}";
			cl = conn.prepareCall(sql);
			cl.setString(1, "mail_activation");
			boolean result = cl.execute();
			
			while(result)
			{
				ResultSet rs = cl.getResultSet();
				if(i==1)
				{
					while(rs.next())
					{
						templateBean.setTemplate(rs.getString("template_value"));
					}
				}
				
				i++;
				result = cl.getMoreResults();
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "getRegistrationMailTemplate";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return templateBean;
	}

	//checkTheaterOwnerEmail
	public String checkTheaterOwnerEmail(String email) throws Exception 
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String status = "";
		try
		{
			conn = connectionManager.getConnection();
			
			String sql = "SELECT email FROM login WHERE email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next())
			{
				status = "Not_Available";
			}
			else
			{
				status = "Available";
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "getRegistrationMailTemplate";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return status;
	}

	//setAccountActivationDetails  - Created by hemalatha - 19-07-2018
	public TheaterOwnerBean setAccountActivationDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		Connection conn = null;
		CallableStatement cl = null;
		try
		{
			conn = connectionManager.getConnection();
			
			String sql = "{CALL Ticket_TO_setAccountActivationDetails(?,?,?,?,?,?)}";
			cl = conn.prepareCall(sql);
			cl.setString(1, theaterOwnerBean.getMail_pin());
			cl.setString(2, theaterOwnerBean.getSms_pin());
			cl.setInt(3, theaterOwnerBean.getTheater_owner_id());
			cl.registerOutParameter(4, Types.VARCHAR);
			cl.registerOutParameter(5, Types.VARCHAR);
			cl.registerOutParameter(6, Types.VARCHAR);
			cl.execute();
			
			theaterOwnerBean.setMail_pin_status(cl.getString(4));
			theaterOwnerBean.setSms_pin_status(cl.getString(5));
			theaterOwnerBean.setStatus(cl.getString(6));
			
			
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
		return theaterOwnerBean;
	}

	//setMailDetails
	public TheaterOwnerBean setMailDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		Connection conn = null;
		CallableStatement cl = null;
		try
		{
			conn = connectionManager.getConnection();
			
			String mail_pin = "";
			mail_pin = MyStringRandomGen.generateRandomString();
			
			
			
			String sql = "{CALL Ticket_TO_setMailDetails(?,?,?,?,?,?)}";
			cl = conn.prepareCall(sql);
			cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
			cl.setString(2, mail_pin);
			cl.registerOutParameter(3, Types.VARCHAR);
			cl.registerOutParameter(4, Types.VARCHAR);
			cl.registerOutParameter(5, Types.VARCHAR);
			cl.registerOutParameter(6, Types.VARCHAR);
			cl.execute();
			
			theaterOwnerBean.setTheater_owner_first_name(cl.getString(3));
			theaterOwnerBean.setTheater_owner_last_name(cl.getString(4));
			theaterOwnerBean.setTheater_owner_email(cl.getString(5));
			theaterOwnerBean.setMail_pin(cl.getString(6));
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "setMailDetails";
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

	//setSMSDetails
	public TheaterOwnerBean setSMSDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{

		Connection conn = null;
		CallableStatement cl = null;
		try
		{
			conn = connectionManager.getConnection();
		
			String sms_pin = "";
			sms_pin = MobileRandomNum.mobileRandomNum();
			
			String sql = "{CALL Ticket_TO_setSMSDetails(?,?,?,?,?,?)}";
			cl = conn.prepareCall(sql);
			cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
			cl.setString(2, sms_pin);
			cl.registerOutParameter(3, Types.VARCHAR);
			cl.registerOutParameter(4, Types.VARCHAR);
			cl.registerOutParameter(5, Types.VARCHAR);
			cl.registerOutParameter(6, Types.VARCHAR);
			cl.execute();
			
			theaterOwnerBean.setTheater_owner_first_name(cl.getString(3));
			theaterOwnerBean.setTheater_owner_last_name(cl.getString(4));
			theaterOwnerBean.setTheater_owner_mobile(cl.getString(5));
			theaterOwnerBean.setSms_pin(cl.getString(6));
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "setMailDetails";
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

	//getTheaterOwnerDetails
	public TheaterOwnerBean getTheaterOwnerDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		Connection conn = null;
		CallableStatement cl = null;
		int i =1;
		try
		{
			conn = connectionManager.getConnection();
			
			String sql = "{CALL Ticket_TO_getTheaterOwnerDetails(?,?,?)}";
			cl = conn.prepareCall(sql);
			cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
			cl.registerOutParameter(2, Types.VARCHAR);
			cl.registerOutParameter(3, Types.VARCHAR);
			boolean res = cl.execute();
			
			while(res)
			{
				ResultSet rs = cl.getResultSet();
				
				if(i==1)
				{
					while(rs.next())
					{
						theaterOwnerBean.setTheater_owner_first_name(rs.getString("theater_owner_first_name"));
						theaterOwnerBean.setTheater_owner_last_name(rs.getString("theater_owner_last_name"));
						theaterOwnerBean.setTheater_owner_email(rs.getString("theater_owner_email"));
						theaterOwnerBean.setTheater_owner_mobile(rs.getString("theater_owner_mobile"));
						theaterOwnerBean.setTheater_owner_address(rs.getString("theater_owner_address"));
						theaterOwnerBean.setState_name(cl.getString(2));
						theaterOwnerBean.setCity_name(cl.getString(3));
						
						if(rs.getString("mail_pin_status").equals("Y"))
						{
							theaterOwnerBean.setMail_pin_status("success");
						}
						else
						{
							theaterOwnerBean.setMail_pin_status("not_success");
						}
						
						if(rs.getString("sms_pin_status").equals("Y"))
						{
							theaterOwnerBean.setSms_pin_status("success");
						}
						else
						{
							theaterOwnerBean.setSms_pin_status("not_success");
						}
						
					}
				}
				
				i++;
				res = cl.getMoreResults();
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String str = "getTheaterOwnerDetails";
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
	
	//getProfileViewPage
	public TheaterOwnerBean getProfileViewPage(TheaterOwnerBean theaterOwnerBean)
			throws Exception 
	{
			Connection conn = null;
			CallableStatement cl = null;
			ResultSet rs = null;
			
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getProfileViewPage(?,?,?,?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
				cl.registerOutParameter(2, Types.VARCHAR);
				cl.registerOutParameter(3, Types.VARCHAR);
				cl.registerOutParameter(4, Types.VARCHAR);
				cl.execute();
				
				rs = cl.executeQuery();
				while(rs.next())
				{
					theaterOwnerBean.setTheater_owner_id(rs.getInt("theater_owner_id"));
					theaterOwnerBean.setTheater_owner_first_name(rs.getString("theater_owner_first_name"));
					theaterOwnerBean.setTheater_owner_last_name(rs.getString("theater_owner_last_name"));
					theaterOwnerBean.setTheater_owner_email(rs.getString("theater_owner_email"));
					theaterOwnerBean.setTheater_owner_mobile(rs.getString("theater_owner_mobile"));
					theaterOwnerBean.setTheater_owner_address(rs.getString("theater_owner_address"));
					theaterOwnerBean.setState_id(rs.getInt("theater_owner_state_id"));
					theaterOwnerBean.setState_name(cl.getString(2));
					theaterOwnerBean.setCity_id(rs.getInt("theater_owner_city_id"));
					theaterOwnerBean.setDistrict_name(cl.getString(4));
					theaterOwnerBean.setDistrict_id(rs.getInt("theater_owner_district_id"));
					theaterOwnerBean.setCity_name(cl.getString(3));
				}
				
				
				
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					String str = "getProfileViewPage";
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
	
	
	//getStateDetail  - Created by Nachimuthu 25-07-2018
		public Map<Integer, String> getStateDetail() throws Exception 
		{
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			Map<Integer,String> stateMap=new HashMap<Integer, String>();
			try{
				conn=connectionManager.getConnection();
			
				String sql = "SELECT state_id,state_name FROM state";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next())
				{
					stateMap.put(rs.getInt("state_id"), rs.getString("state_name"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				String str = "getStateDetail";
				catchMethodLogger(str, e);
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return stateMap;	
		}
		
		//getCityDetails  - Created by Nachimuthu 25-07-2018
		public Map<Integer, String> getCityDetails() throws Exception 
		{
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			Map<Integer,String> cityMap=new HashMap<Integer, String>();
			try
			{
				conn=connectionManager.getConnection();
				
				String city="SELECT city_id,city_name FROM city";
				ps=conn.prepareStatement(city);
				rs=ps.executeQuery();
				while(rs.next())
				{
					cityMap.put(rs.getInt("city_id"), rs.getString("city_name"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				String str = "getCityDetails";
				catchMethodLogger(str, e);
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return cityMap;
		}
		
		//getUpdatePageDetails - Created by Nachimuthu 25-07-2018
		public TheaterOwnerBean getUpdatePageDetails(TheaterOwnerBean theaterOwnerBean)
				throws Exception 
		{
			Connection conn = null;
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			PreparedStatement ps2 = null;
			
			try 
			{
				conn = connectionManager.getConnection();
				
				String sql = "UPDATE theater_owner_detail SET theater_owner_first_name=?,theater_owner_last_name=?,theater_owner_mobile=?,theater_owner_address=?,theater_owner_state_id=?,theater_owner_city_id=?,theater_owner_district_id = ? WHERE theater_owner_id=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, theaterOwnerBean.getTheater_owner_first_name());
				ps.setString(2, theaterOwnerBean.getTheater_owner_last_name());
				ps.setString(3, theaterOwnerBean.getTheater_owner_mobile());
				ps.setString(4, theaterOwnerBean.getTheater_owner_address());
				ps.setInt(5, theaterOwnerBean.getState_id());
				ps.setInt(6, theaterOwnerBean.getCity_id());
				ps.setInt(7, theaterOwnerBean.getDistrict_id());
				ps.setInt(8, theaterOwnerBean.getTheater_owner_id());
				ps.executeUpdate();
				
				InputStream profilrpictureInputStream = new FileInputStream(theaterOwnerBean.getProfile_photo());
				String sql1 = "SELECT PROFILE FROM theater_owner_profile_detail WHERE theater_owner_id = ?";
				ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, theaterOwnerBean.getTheater_owner_id());
				rs1 = ps1.executeQuery();
				if(rs1.next())
				{
					String sql2 = "UPDATE theater_owner_profile_detail SET PROFILE = ? WHERE theater_owner_id = ?";
					ps2 = conn.prepareStatement(sql2);
					ps2.setBinaryStream(1, profilrpictureInputStream,(int)theaterOwnerBean.getProfile_photo().length());
					ps2.setInt(2, theaterOwnerBean.getTheater_owner_id());
					ps2.executeUpdate();
				}
				else
				{
					String sql2 = "INSERT INTO theater_owner_profile_detail(PROFILE,theater_owner_id,created_on) VALUES(?,?,now())";
					ps2 = conn.prepareStatement(sql2);
					ps2.setBinaryStream(1, profilrpictureInputStream,(int)theaterOwnerBean.getProfile_photo().length());
					ps2.setInt(1, theaterOwnerBean.getTheater_owner_id());
					ps2.executeUpdate();
				}
				
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getUpdatePageDetails";
				catchMethodLogger(str, e);
			}
			finally {
				if (conn != null) {
					connectionManager.releaseConnection(conn);
				}
			}
			return theaterOwnerBean;
		}
		
		//profile_images
		public Map<Integer, byte[]> profile_images(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			CallableStatement cl = null;
			ResultSet rs = null;
			Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
			try
			{
				conn = connectionManager.getConnection();
				String sql = "{CALL Ticket_TO_profile_images(?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
				cl.execute();
				rs = cl.executeQuery();
				if(rs.next())
				{
					imageMap.put(rs.getInt("theater_owner_id"), rs.getBytes("profile"));
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				String str = "Thetare owner Dao Impl : profile_images";
				catchMethodLogger(str, e);
			}
			finally {
				if (conn != null) {
					connectionManager.releaseConnection(conn);
				}
			}
			return imageMap;
		}
		
		//checkOldPassword -created by Babu25-7-2018
		public String checkOldPasswordAvailable(String oldPassword,
				TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String status = null;
			try 
			{
				conn = connectionManager.getConnection();
				String sql="SELECT email FROM login WHERE theater_owner_id=? AND PASSWORD=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getTheater_owner_id());
				ps.setString(2,oldPassword);
				rs=ps.executeQuery();
				if(rs.next())
				{
					status = "match";
				}
				else
				{
					status = "misMatch";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				String str = "Thetare owner Dao Impl :checkOldPasswordAvailable";
				catchMethodLogger(str, e);			
			}
			finally
			 {
				 if(conn!=null)
				 {
					 connectionManager.releaseConnection(conn);
				 }
			 }
			return status;
		}	
		
		//updatePassWordDetails -created by Babu25-7-2018
		public TheaterOwnerBean updatePasswordDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try
			{
			    conn=connectionManager.getConnection();
	            String select="SELECT password FROM login WHERE theater_owner_id=? AND password=?";
	           
				ps=conn.prepareStatement(select);
	             ps.setInt(1,theaterOwnerBean.getTheater_owner_id());
	             ps.setString(2, theaterOwnerBean.getOld_password());
	             rs=ps.executeQuery();
	             if(rs.next())
	             {
	            	 String update="update login set password=? where theater_owner_id=?";
	            	 ps=conn.prepareStatement(update);
	            	 ps.setString(1, theaterOwnerBean.getPassword());
	            	 ps.setInt(2, theaterOwnerBean.getTheater_owner_id());
	            	 int s=ps.executeUpdate();
	            	 if(s==1)
	            	 {
	            		 theaterOwnerBean.setStatus("success");
	            	 }
	             }
	             else
	             {
	            	 theaterOwnerBean.setStatus("error");
	             }
	    	}
			catch(Exception e)
			{
				e.printStackTrace();
				String str = "updatePasswordDetails";
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
		
		//getStateWiseCityDetails  - Created by Nachimuthu 25-07-2018
		public TheaterOwnerBean getStateWiseCityDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Map<Integer, String> cityMap = new HashMap<Integer,String>();
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "SELECT district_id,district_name FROM district WHERE state_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getState_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
					cityMap.put(rs.getInt("district_id"), rs.getString("district_name"));
				} 
				theaterOwnerBean.setCityMap(cityMap);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getStateWiseCityDetails";
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

		//getTheaterScreenDetails
		public TheaterOwnerBean getTheaterScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> theaterMap = new HashMap<Integer,String>();
			Map<Integer, String> screenMap = new HashMap<Integer,String>();
			Map<Integer, String> categoryMap = new HashMap<Integer,String>();
			int i = 1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getTheaterScreenDetails(?,?,?,?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
				cl.registerOutParameter(2, Types.INTEGER);
				cl.registerOutParameter(3, Types.INTEGER);
				cl.registerOutParameter(4, Types.VARCHAR);
				boolean result = cl.execute();
				
				if(result)
				{
					theaterOwnerBean.setTheater_id(cl.getInt(2));
					theaterOwnerBean.setScreen_id(cl.getInt(3));
					theaterOwnerBean.setTheater_name(cl.getString(4));
					theaterOwnerBean.setStatus("success");
					
					while(result)
					{
						ResultSet rs = cl.getResultSet();
						
						if(i==1)
						{
							while(rs.next())
							{
								theaterMap.put(rs.getInt("theater_id"), rs.getString("theater_name"));
							}
						}
						theaterOwnerBean.setTheaterMap(theaterMap);
						
						if(i==2)
						{
							while(rs.next())
							{
								screenMap.put(rs.getInt("screen_id"), rs.getString("screen_name"));
							}
						}
						theaterOwnerBean.setScreenMap(screenMap);
						
						if(i==3)
						{
							while(rs.next())
							{
								categoryMap.put(rs.getInt("seat_category_id"), rs.getString("seat_category"));
							}
						}
						theaterOwnerBean.setTicketCategoryMap(categoryMap);
						
						i++;
						result = cl.getMoreResults();
					}
				}
				else
				{
					theaterOwnerBean.setStatus("failure");
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getTheaterScreenDetails";
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

		//getScreenImage
		public Map<Integer, byte[]> getScreenImage(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "SELECT screen_id,screen_layout FROM screen_detail WHERE screen_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getScreen_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
					imageMap.put(rs.getInt("screen_id"), rs.getBytes("screen_layout"));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getScreenImage";
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

		//getTheaterAgainstScreen
		public TheaterOwnerBean getTheaterAgainstScreen(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			CallableStatement cl = null;
			ResultSet rs = null;
			Map<Integer, String> screenMap = new HashMap<Integer,String>();
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getTheaterAgainstScreen(?,?,?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_id());
				cl.registerOutParameter(2, Types.INTEGER);
				cl.registerOutParameter(3, Types.VARCHAR);
				cl.execute();
				rs = cl.executeQuery();
				
				theaterOwnerBean.setScreen_id(cl.getInt(2));
				theaterOwnerBean.setTheater_name(cl.getString(3));
				
				while(rs.next())
				{
					screenMap.put(rs.getInt("screen_id"), rs.getString("screen_name"));
				}
				
				theaterOwnerBean.setScreenMap(screenMap);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getTheaterAgainstScreen";
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

		//setTheaterWiseScreenDetailSubmit
		public TheaterOwnerBean setTheaterWiseScreenDetailSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			CallableStatement cl = null;
			TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
			ArrayList<TheaterDetailBean> theaterDetailList = new ArrayList<TheaterDetailBean>();
			Map<Integer, String> seatCategoryMap = new HashMap<Integer,String>();
			Map<Integer, String> ticketCategoryMap = new HashMap<Integer,String>();
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_setTheaterWiseScreenDetailSubmit(?,?,?,?,?,?,?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
				cl.setInt(2, theaterOwnerBean.getTheater_id());
				cl.setInt(3, theaterOwnerBean.getScreen_id());
				cl.setInt(4, theaterOwnerBean.getPassage_count());
				cl.setInt(5, theaterOwnerBean.getCategory_from_id());
				cl.setInt(6, theaterOwnerBean.getCategory_to_id());
				cl.setInt(7, theaterOwnerBean.getOrder_id());
				boolean result = cl.execute();
				int i = 1;
				while(result)
				{
					ResultSet rs = cl.getResultSet();
					
					if(i==1)
					{
						while(rs.next())
						{
							theaterDetailBean = new TheaterDetailBean();
							theaterDetailBean.setCategory_name(rs.getString("catryName"));
							theaterDetailBean.setCategory_id(rs.getInt("catId"));
							theaterDetailBean.setCol_count(rs.getInt("cnt"));
							theaterDetailList.add(theaterDetailBean);
						}
					}
					theaterOwnerBean.setTheaterRowList(theaterDetailList);	

					if(i==2)
					{
						while(rs.next())
						{
							ticketCategoryMap.put(rs.getInt("seat_category_id"), rs.getString("seat_category"));
						}
					}
					theaterOwnerBean.setTicketCategoryMap(ticketCategoryMap);
					
					if(i==3)
					{
						while(rs.next())
						{
							seatCategoryMap.put(rs.getInt("seating_order_id"), rs.getString("seating_order"));
						}
					}
					theaterOwnerBean.setSeatCategoryMap(seatCategoryMap);
					
					i++;
					result = cl.getMoreResults();
						
				}
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "setTheateriseScreenDetailSubmit";
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

		//setTheaterSeatCountDetailSubmit
		public TheaterOwnerBean setTheaterSeatCountDetailSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			CallableStatement cl = null;
			try
			{
				conn = connectionManager.getConnection();
			
				String sql = "{CALL Ticket_TO_setTheaterSeatCountDetailSubmit(?,?,?,?,?,?,?)}";
				cl = conn.prepareCall(sql);
				cl.setString(1, theaterOwnerBean.getSeatCountDetail());
				cl.setString(2, theaterOwnerBean.getSeatCategoryDetail());
				cl.setString(3, "-");
				cl.setString(4, "_");
				cl.setInt(5, theaterOwnerBean.getTheater_id());
				cl.setInt(6, theaterOwnerBean.getScreen_id());
				cl.setInt(7, theaterOwnerBean.getTheater_owner_id());
				boolean res = cl.execute();
				
				if(res)
				{
					theaterOwnerBean.setStatus("success");
				}
				else
				{
					theaterOwnerBean.setStatus("failure");
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "setTheaterSeatCountDetailSubmit";
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

		//getSeatingArrangementDetails
		public TheaterOwnerBean getSeatingArrangementDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			CallableStatement cl = null;
			ResultSet rs = null;
			ArrayList<TheaterDetailBean> finalList = new ArrayList<TheaterDetailBean>();
			ArrayList<ScreenDetailBean> subList = new ArrayList<ScreenDetailBean>();
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getSeatingArrangementDetails(?,?,?,?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_id());
				cl.setInt(2, theaterOwnerBean.getScreen_id());
				cl.setInt(3, theaterOwnerBean.getTheater_owner_id());
				cl.registerOutParameter(4, Types.INTEGER);
				cl.execute();
				rs = cl.executeQuery();
				
				while(rs.next())
				{
					TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
					subList = new ArrayList<ScreenDetailBean>();
					theaterDetailBean.setCategory_name(rs.getString("seatCatName"));
					theaterDetailBean.setCol_count(rs.getInt("pasCount"));
					theaterDetailBean.setTotal_seat_count(cl.getInt(4));
					
					String strArr[] = rs.getString("seatingValue").split(",");
					for(String str : strArr)
					{
						ScreenDetailBean screenDetailBean = new ScreenDetailBean();
						String strArr1[] = str.split("-");
						screenDetailBean.setSeat_count(Integer.parseInt(strArr1[0].trim()));
						screenDetailBean.setOrder_id(Integer.parseInt(strArr1[1].trim()));
						subList.add(screenDetailBean);
					}
					theaterDetailBean.setScreenList(subList);
					finalList.add(theaterDetailBean);
				}
				
				theaterOwnerBean.setTheaterRowList(finalList);
				
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
			return theaterOwnerBean;
		}



		//getDistrictDetails created by ramya - 30-07-08
		public Map<Integer, String> getDistrictDetails() throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> districtMap = new HashMap<Integer,String>();
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "CALL Ticket_TO_getDistrictDetails()";
				cl = conn.prepareCall(sql);
				boolean result = cl.execute();
				while(result)
				{
					ResultSet rs = cl.getResultSet();
					if(i==1)
					{
						while(rs.next())
						{
							districtMap.put(rs.getInt("district_id"), rs.getString("district_name"));
						} 
					}
					
					result = cl.getMoreResults();
					i++;
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return districtMap;
		}

		//getDistrictWiseCityDetail
		public Map<Integer, String> getDistrictWiseCityDetail(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> cityMap = new HashMap<Integer,String>();
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql1 = "{CALL Ticket_TO_getDistrictWiseCityDetail(?)}";
				cl = conn.prepareCall(sql1);
				cl.setInt(1, theaterOwnerBean.getDistrict_id());
				boolean result = cl.execute();
				while(result)
				{
					ResultSet rs = cl.getResultSet();
					if(i==1)
					{
						while(rs.next())
						{
							cityMap.put(rs.getInt("city_id"), rs.getString("city_name"));
						} 
					}
					
					result = cl.getMoreResults();
					i++;
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return cityMap;
		}

		//getTheaterDetails
		public TheaterOwnerBean getEditTheaterDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			ArrayList<TheaterDetailBean> theaterDetailList = new ArrayList<TheaterDetailBean>();
			TheaterDetailBean theaterDetailBean = null;
			ScreenDetailBean screenDetailBean = null;
			BufferedImage bufImg = null;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "SELECT theater_id,theater_name,address,city_id,district_id,theater_license_number,theater_license_number,theater_tin_number,gst_number,total_number_of_seats FROM theater_detail WHERE theater_owner_id = ? AND STATUS= 'Active' AND is_deleted = 'N'";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getTheater_owner_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterDetailBean = new TheaterDetailBean();
					
					theaterDetailBean.setTheater_id(rs.getInt("theater_id"));
					theaterDetailBean.setTheater_name(rs.getString("theater_name"));
					theaterDetailBean.setAddress(rs.getString("address"));
					theaterDetailBean.setCity_id(rs.getInt("city_id"));
					theaterDetailBean.setDistrict_id(rs.getInt("district_id"));
					theaterDetailBean.setTheater_license_number(rs.getString("theater_license_number"));
					theaterDetailBean.setTheater_gst_number(rs.getString("gst_number"));
					theaterDetailBean.setTheater_tin_number(rs.getString("theater_tin_number"));
					theaterDetailBean.setNumber_of_seats(rs.getInt("total_number_of_seats"));
					
					ArrayList<ScreenDetailBean> screenDetailList = new ArrayList<ScreenDetailBean>();

					String sql1 = "SELECT screen_id,screen_name,seat_count,screen_layout FROM screen_detail WHERE theater_id = ? AND theater_owner_id = ? AND STATUS = 'Active' AND is_deleted = 'N'";
					ps1 = conn.prepareStatement(sql1);
					ps1.setInt(1, theaterDetailBean.getTheater_id());
					ps1.setInt(2, theaterOwnerBean.getTheater_owner_id());
					rs1 = ps1.executeQuery();
					while(rs1.next())
					{
						screenDetailBean = new ScreenDetailBean();
						
						screenDetailBean.setScreen_id(rs1.getInt("screen_id"));
						screenDetailBean.setScreen_name(rs1.getString("screen_name"));
						screenDetailBean.setSeat_count(rs1.getInt("seat_count"));
						byte[] screenLayout = rs1.getBytes("screen_layout");
						
						if(screenLayout == null){
							
							screenDetailBean.setScreen_layout_status("No");
						}
						else{
							screenDetailBean.setScreen_layout_status("Yes");
						}
						screenDetailList.add(screenDetailBean);
					}
					
					theaterDetailBean.setScreen_count(screenDetailList.size());
					theaterDetailBean.setScreenList(screenDetailList);
					theaterDetailList.add(theaterDetailBean);
				} 
				
				if(theaterDetailList.size()>1)
				{
					theaterOwnerBean.setStatus("Yes");
				}
				else
				{
					theaterOwnerBean.setStatus("No");
				}
				theaterOwnerBean.setTheater_count(theaterDetailList.size());
				theaterOwnerBean.setTheaterDetailList(theaterDetailList);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
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

		//setAddTheaterDetails 
		public TheaterOwnerBean setAddTheaterDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			try
			{
				conn = connectionManager.getConnection();
				
				for(TheaterDetailBean theaterDetailBean : theaterOwnerBean.getTheaterDetailList()){
					
					String sql = "INSERT INTO theater_detail (theater_name,theater_owner_id,address,city_id,district_id,theater_license_number,theater_tin_number,gst_number,STATUS,is_deleted,created_on) VALUES (?,?,?,?,?,?,?,?,'Active','N',NOW())";
					ps = conn.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
					ps.setString(1, theaterDetailBean.getTheater_name());
					ps.setInt(2, theaterOwnerBean.getTheater_owner_id());
					ps.setString(3, theaterDetailBean.getAddress());
					ps.setInt(4, theaterDetailBean.getCity_id());
					ps.setInt(5, theaterDetailBean.getDistrict_id());
					ps.setString(6, theaterDetailBean.getTheater_license_number());
					ps.setString(7, theaterDetailBean.getTheater_tin_number());
					ps.setString(8, theaterDetailBean.getTheater_gst_number());
					ps.executeUpdate();
					rs = ps.getGeneratedKeys();
					if(rs.next()){
						
						theaterDetailBean.setTheater_id(rs.getInt(1));
					}
					
					int total_seats_count = 0;
					
					for(ScreenDetailBean screenDetailBean : theaterDetailBean.getScreenList()){
						
						total_seats_count = total_seats_count+screenDetailBean.getSeat_count();
						
						String sql1 = "INSERT INTO screen_detail (screen_name,seat_count,screen_layout,theater_id,theater_owner_id,STATUS,is_deleted,created_on) VALUES (?,?,?,?,?,'Active','N',NOW())";
						ps1 = conn.prepareStatement(sql1);
						ps1.setString(1, screenDetailBean.getScreen_name());
						ps1.setInt(2, screenDetailBean.getSeat_count());
                        if(screenDetailBean.getScreen_layout() == null){
							ps1.setBinaryStream(3, null);

						}
						else{
							InputStream profilrpictureInputStream = new FileInputStream(screenDetailBean.getScreen_layout());
							ps1.setBinaryStream(3, profilrpictureInputStream,(int)screenDetailBean.getScreen_layout().length());

						}
                        ps1.setInt(4, theaterDetailBean.getTheater_id());
                        ps1.setInt(5, theaterOwnerBean.getTheater_owner_id());
                        ps1.executeUpdate();
						
					}
					
					String sql2 = "UPDATE theater_detail SET total_number_of_seats = ? WHERE theater_id = ?";
					ps1 = conn.prepareStatement(sql2);
					ps1.setInt(1, total_seats_count); 
					ps1.setInt(2, theaterDetailBean.getTheater_id());
					ps1.executeUpdate();
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
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

		//getAddTheaterAvailableStatusDetails - created by ramya 31-07-18
		public TheaterOwnerBean getAddTheaterAvailableStatusDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
					String sql = "{CALL Ticket_TO_getAddTheaterAvailableStatusDetails(?)}";
					cl = conn.prepareCall(sql);
					cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
					boolean result = cl.execute();
					while(result)
					{
						ResultSet rs = cl.getResultSet();
						if(i==1)
						{
							if(rs.next())
							{
								theaterOwnerBean.setStatus("Success");
							}
							else{
								theaterOwnerBean.setStatus("Faliure");

							}
						}
						
						result = cl.getMoreResults();
						i++;
					}
			}
			catch (Exception e)
			{
				e.printStackTrace();
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

		//getTheaterOwnerTheaterDetails
		public Map<Integer, String> getTheaterOwnerTheater(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> theaterMap = new HashMap<Integer,String>();
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "CALL Ticket_TO_getTheaterOwnerTheater(?)";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
				boolean result = cl.execute();
				while(result)
				{
					ResultSet rs = cl.getResultSet();
					if(i==1)
					{
						while(rs.next())
						{
							theaterMap.put(rs.getInt("theater_id"), rs.getString("theater_name"));
						} 
					}
					result = cl.getMoreResults();
					i++;
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return theaterMap;
		}
		
		//getTheaterAllDetails created by ramya 01-08-18
		public TheaterDetailBean getTheaterAllDetails(
				TheaterDetailBean theaterDetailBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> screenMap = new HashMap<Integer, String>();
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
					String sql = "{CALL Ticket_TO_getTheaterDetails(?)}";
					cl = conn.prepareCall(sql);
					cl.setInt(1, theaterDetailBean.getTheater_id());
					boolean res = cl.execute();
					while(res)
					{
						ResultSet rs = cl.getResultSet();
						
						switch (i) {
						case 1:
							
							if(rs.next())
							{
							theaterDetailBean.setTheater_id(rs.getInt("theater_id"));
							theaterDetailBean.setTheater_name(rs.getString("theater_name"));
							theaterDetailBean.setAddress(rs.getString("address"));
							theaterDetailBean.setTheater_license_number(rs.getString("theater_license_number"));
							theaterDetailBean.setTheater_tin_number(rs.getString("theater_tin_number"));
							theaterDetailBean.setTheater_gst_number(rs.getString("gst_number"));
							theaterDetailBean.setNumber_of_seats(rs.getInt("total_number_of_seats"));
							theaterDetailBean.setDistrict_name(rs.getString("district_name"));
							theaterDetailBean.setCity_name(rs.getString("city_name"));
							}
							break;
                        case 2:
							
                        	int scrCount = 0;
                        	while(rs.next())
							{
                        		scrCount = scrCount+1;
                        		screenMap.put(rs.getInt("screen_id"), rs.getString("screen_name"));
							}
                        	theaterDetailBean.setScreenMap(screenMap);
                        	theaterDetailBean.setScreen_count(scrCount);
                        	
							break;
                        case 3:
							
                        	if(rs.next())
							{
                        	theaterDetailBean.setScreen_id(rs.getInt("screen_id"));
                        	theaterDetailBean.setScreen_name(rs.getString("screen_name"));
                        	theaterDetailBean.setSeat_count(rs.getInt("seat_count"));
                        	byte[] screenLayout = rs.getBytes("screen_layout");
   						 
    						if(screenLayout == null){
    							
    							theaterDetailBean.setScreen_layout_status("No");
    						}
    						else{
    							theaterDetailBean.setScreen_layout_status("Yes");
    						}
							}
							break;
						}
						
						i++;
						res = cl.getMoreResults();
					}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return theaterDetailBean;
		}

		//getTheaterBasicScreendetail
		public TheaterDetailBean getTheaterBasicScreendetail(
				TheaterDetailBean theaterDetailBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
					String sql = "{CALL Ticket_TO_getTheaterBasicScreendetail(?,?)}";
					cl = conn.prepareCall(sql);
					cl.setInt(1, theaterDetailBean.getTheater_id());
					cl.setInt(2, theaterDetailBean.getScreen_id());
					boolean res = cl.execute();
					while(res)
					{
						ResultSet rs = cl.getResultSet();
						
					if(i==1)
					{
						if(rs.next())
						{
                    	theaterDetailBean.setScreen_id(rs.getInt("screen_id"));
                    	theaterDetailBean.setScreen_name(rs.getString("screen_name"));
                    	theaterDetailBean.setSeat_count(rs.getInt("seat_count"));
                    	byte[] screenLayout = rs.getBytes("screen_layout");
						 
						if(screenLayout == null){
							
							theaterDetailBean.setScreen_layout_status("No");
						}
						else{
							theaterDetailBean.setScreen_layout_status("Yes");
						}
						}
					}
						
						i++;
						res = cl.getMoreResults();
					}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return theaterDetailBean;
		}

		//setDeleteTheaterDetail
		public TheaterOwnerBean setDeleteTheaterDetail(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			try
			{
				    conn = connectionManager.getConnection();
				
					String sql = "{CALL Ticket_TO_setDeleteTheaterDetail(?,?,?)}";
					cl = conn.prepareCall(sql);
					cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
					cl.setInt(2, theaterOwnerBean.getTheater_id());
					cl.registerOutParameter(3, Types.VARCHAR);
					cl.execute();
					
					theaterOwnerBean.setTheater_status(cl.getString(3));
			}
			catch (Exception e)
			{
				e.printStackTrace();
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

		//setUpdateTheaterDetails created by ramya 03-08-184
		public TheaterOwnerBean setUpdateTheaterDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			PreparedStatement ps1 = null;
			
			try
			{
				    conn = connectionManager.getConnection();
				
					for(TheaterDetailBean theaterDetailBean : theaterOwnerBean.getTheaterDetailList()){
						
						int total_seats_count = 0;
						if(theaterDetailBean.getTheater_id() != 0){
							
							String sql = "UPDATE theater_detail SET theater_name=?,theater_owner_id=?,address=?,city_id=?,district_id=?,theater_license_number=?,theater_tin_number=?,gst_number=? WHERE theater_id = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, theaterDetailBean.getTheater_name());
							ps.setInt(2, theaterOwnerBean.getTheater_owner_id());
							ps.setString(3, theaterDetailBean.getAddress());
							ps.setInt(4, theaterDetailBean.getCity_id());
							ps.setInt(5, theaterDetailBean.getDistrict_id());
							ps.setString(6, theaterDetailBean.getTheater_license_number());
							ps.setString(7, theaterDetailBean.getTheater_tin_number());
							ps.setString(8, theaterDetailBean.getTheater_gst_number());
							ps.setInt(9, theaterDetailBean.getTheater_id());
							ps.executeUpdate();
						 
							
                            	
                            	for(ScreenDetailBean screenDetailBean : theaterDetailBean.getScreenList()){
    								
    								total_seats_count = total_seats_count+screenDetailBean.getSeat_count();
    								
    								if(screenDetailBean.getScreen_id() != 0){
    									
    									 if(screenDetailBean.getScreen_layout() == null){
    										 String sql1 = "UPDATE screen_detail SET screen_name =?,seat_count=? WHERE screen_id =? AND theater_id =? AND theater_owner_id =?";
    	    								 ps1 = conn.prepareStatement(sql1);
    	    								 ps1.setString(1, screenDetailBean.getScreen_name());
    	        							 ps1.setInt(2, screenDetailBean.getSeat_count());
    	    								 ps1.setInt(3, screenDetailBean.getScreen_id());
    	    								 ps1.setInt(4, theaterDetailBean.getTheater_id());
    	    								 ps1.setInt(5, theaterOwnerBean.getTheater_owner_id());
    	    		                         ps1.executeUpdate();
         								}
         								else{
         									InputStream profilrpictureInputStream = new FileInputStream(screenDetailBean.getScreen_layout());
         									
         									 String sql1 = "UPDATE screen_detail SET screen_name =?,seat_count=?,screen_layout=? WHERE screen_id =? AND theater_id =? AND theater_owner_id =?";
    	    								 ps1 = conn.prepareStatement(sql1);
    	    								 ps1.setString(1, screenDetailBean.getScreen_name());
    	        							 ps1.setInt(2, screenDetailBean.getSeat_count());
          									 ps1.setBinaryStream(3, profilrpictureInputStream,(int)screenDetailBean.getScreen_layout().length());
    	    								 ps1.setInt(4, screenDetailBean.getScreen_id());
    	    								 ps1.setInt(5, theaterDetailBean.getTheater_id());
    	    								 ps1.setInt(6, theaterOwnerBean.getTheater_owner_id());
    	    		                         ps1.executeUpdate();
         								}
    									
    								}
    								else{
    									
    									String sql2 = "INSERT INTO screen_detail (screen_name,seat_count,screen_layout,theater_id,theater_owner_id,STATUS,is_deleted,created_on) VALUES (?,?,?,?,?,'Active','N',NOW())";
        								ps1 = conn.prepareStatement(sql2);
        								ps1.setString(1, screenDetailBean.getScreen_name());
        								ps1.setInt(2, screenDetailBean.getSeat_count());
        		                        if(screenDetailBean.getScreen_layout() == null){
        									ps1.setBinaryStream(3, null);
        								}
        								else{
        									InputStream profilrpictureInputStream = new FileInputStream(screenDetailBean.getScreen_layout());
        									ps1.setBinaryStream(3, profilrpictureInputStream,(int)screenDetailBean.getScreen_layout().length());

        								}
        		                        ps1.setInt(4, theaterDetailBean.getTheater_id());
        		                        ps1.setInt(5, theaterOwnerBean.getTheater_owner_id());
        		                        ps1.executeUpdate();
    								}
                            }
						}
						else{
							
							String sql = "INSERT INTO theater_detail (theater_name,theater_owner_id,address,city_id,district_id,theater_license_number,theater_tin_number,gst_number,STATUS,is_deleted,created_on) VALUES (?,?,?,?,?,?,?,?,'Active','N',NOW())";
							ps = conn.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
							ps.setString(1, theaterDetailBean.getTheater_name());
							ps.setInt(2, theaterOwnerBean.getTheater_owner_id());
							ps.setString(3, theaterDetailBean.getAddress());
							ps.setInt(4, theaterDetailBean.getCity_id());
							ps.setInt(5, theaterDetailBean.getDistrict_id());
							ps.setString(6, theaterDetailBean.getTheater_license_number());
							ps.setString(7, theaterDetailBean.getTheater_tin_number());
							ps.setString(8, theaterDetailBean.getTheater_gst_number());
							ps.executeUpdate();
							rs = ps.getGeneratedKeys();
							if(rs.next()){
								
								theaterDetailBean.setTheater_id(rs.getInt(1));
							}
							
							
							for(ScreenDetailBean screenDetailBean : theaterDetailBean.getScreenList()){
								
								total_seats_count = total_seats_count+screenDetailBean.getSeat_count();
								
								String sql1 = "INSERT INTO screen_detail (screen_name,seat_count,screen_layout,theater_id,theater_owner_id,STATUS,is_deleted,created_on) VALUES (?,?,?,?,?,'Active','N',NOW())";
								ps1 = conn.prepareStatement(sql1);
								ps1.setString(1, screenDetailBean.getScreen_name());
								ps1.setInt(2, screenDetailBean.getSeat_count());
		                        if(screenDetailBean.getScreen_layout() == null){
									ps1.setBinaryStream(3, null);

								}
								else{
									InputStream profilrpictureInputStream = new FileInputStream(screenDetailBean.getScreen_layout());
									ps1.setBinaryStream(3, profilrpictureInputStream,(int)screenDetailBean.getScreen_layout().length());

								}
		                        ps1.setInt(4, theaterDetailBean.getTheater_id());
		                        ps1.setInt(5, theaterOwnerBean.getTheater_owner_id());
		                        ps1.executeUpdate();
								
							}
						}
						
						String sql2 = "UPDATE theater_detail SET total_number_of_seats = ? WHERE theater_id = ?";
						ps1 = conn.prepareStatement(sql2);
						ps1.setInt(1, total_seats_count); 
						ps1.setInt(2, theaterDetailBean.getTheater_id());
						ps1.executeUpdate();
					}
			}
			catch (Exception e)
			{
				e.printStackTrace();
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


		@Override
		public Map<Integer, String> getUserRoleMap() {
			Map<Integer, String> userRoleMap=new HashMap<Integer,String>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String employee_role="select theatre_employee_role_id,theatre_employee_role_name from theatre_employee_role";
				ps = conn.prepareStatement(employee_role);
				rs = ps.executeQuery();
				while(rs.next())
				{
					//districtMap.put(rs.getInt("district_id"), rs.getString("district_name"));
					userRoleMap.put(rs.getInt("theatre_employee_role_id"), rs.getString("theatre_employee_role_name"));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
			return userRoleMap;
		}

		
		//set theater employee details
		public TheaterOwnerBean settTheaterAddUserSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				
				String employee_details="insert into theater_employee_details(theater_employee_name,theater_employee_mail,theater_employee_mobile,gender_id,employee_dob,theater_employee_role_id,employee_profile,is_deleted,status,created_user_id,employee_id,theater_id,created_date) values(?,?,?,?,?,?,?,?,?,?,?,?,now())";
				ps = conn.prepareStatement(employee_details, ps.RETURN_GENERATED_KEYS);
				ps.setString(1, theaterOwnerBean.getFirst_name());
				ps.setString(2, theaterOwnerBean.getEmail_id());
				ps.setString(3, theaterOwnerBean.getPhone_number());
				ps.setInt(4, theaterOwnerBean.getGender_id());
				ps.setString(5, theaterOwnerBean.getDate_of_birth());
				ps.setInt(6, theaterOwnerBean.getTheatre_employee_role_id());
				if(theaterOwnerBean.getProfile_image()!=null)
				{
					InputStream profilrpictureInputStream = new FileInputStream(theaterOwnerBean.getProfile_image());
					ps.setBinaryStream(7,profilrpictureInputStream,(int)theaterOwnerBean.getProfile_image().length());
				}
				else{
					ps.setBinaryStream(7,null);
				}
				ps.setString(8, "N");
				ps.setString(9, "Active");
				ps.setInt(10, theaterOwnerBean.getTheater_owner_id());
				ps.setString(11, theaterOwnerBean.getEmployee_id());
				ps.setInt(12, theaterOwnerBean.getTheater_id());
				ps.executeUpdate();		
				rs = ps.getGeneratedKeys();
				if(rs.next())
				{
					//theatre_employee_id
					theaterOwnerBean.setTheatre_employee_id(rs.getInt(1));
					theaterOwnerBean.setNav_bar_user_status("Yes");
					
					String id = ""+theaterOwnerBean.getTheatre_employee_id();
					String uniqueid = UniqueId.getAckId(id);
					theaterOwnerBean.setUniqueId(uniqueid);
					
					String employee_login="insert into login(email,password,theater_employee_id,activation,status,unique_id,is_deleted,role_id,created_date) values(?,?,?,?,?,?,?,?,now())";
					ps1 = conn.prepareStatement(employee_login);
					ps1.setString(1, theaterOwnerBean.getEmail_id());
					ps1.setString(2, "User123$");
					ps1.setInt(3, theaterOwnerBean.getTheatre_employee_id());
					ps1.setString(4, "Y");
					ps1.setString(5, "Active");
					ps1.setString(6, theaterOwnerBean.getUniqueId());
					ps1.setString(7, "N");
					ps1.setInt(8, 2);
					ps1.executeUpdate();	
					
				}
				
				String sql="SELECT theater_name FROM theater_detail WHERE theater_id = ?";
				ps1 = conn.prepareStatement(sql);
				ps1.setInt(1, theaterOwnerBean.getTheater_id());
				rs1 = ps1.executeQuery();
				while(rs1.next()){
					theaterOwnerBean.setTheater_name(rs1.getString("theater_name"));
				}
				
				
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
			
			
			return theaterOwnerBean;
		}

		//get invite template
		public TemplateBean getEmployeeInviteTemplate(TemplateBean templateBean) throws Exception {
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		try {
			conn = connectionManager.getConnection();
			String sql="select template_value from template where description='invite_employee'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
			templateBean.setTemplate(rs.getString("template_value"));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
				
			return templateBean;
		}

		
	
		public ArrayList<TheaterOwnerBean> getTheaterEmployeeList(TheaterOwnerBean theaterOwnerBean) throws Exception {
			ArrayList<TheaterOwnerBean> theaterEmployeeList=new ArrayList<TheaterOwnerBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String employeeList="SELECT t.employee_id,t.theater_employee_id,t.theater_employee_name,t.employee_dob,t.theater_employee_mail,t.theater_employee_mobile,t.employee_profile,t.theater_employee_role_id ,r.theatre_employee_role_name,t.employee_profile,s.theater_name FROM theater_employee_details t INNER JOIN theatre_employee_role r ON t.theater_employee_role_id=r.theatre_employee_role_id INNER JOIN theater_detail s ON t.theater_id = s.theater_id WHERE t.status ='Active' AND t.is_deleted='N'";
				ps = conn.prepareStatement(employeeList);
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterOwnerBean = new TheaterOwnerBean();
					theaterOwnerBean.setEmployee_id(rs.getString("employee_id"));
					theaterOwnerBean.setTheatre_employee_id(rs.getInt("theater_employee_id"));
					theaterOwnerBean.setFirst_name(rs.getString("theater_employee_name"));
					theaterOwnerBean.setEmail_id(rs.getString("theater_employee_mail"));
					theaterOwnerBean.setDate_of_birth(rs.getString("employee_dob"));
					theaterOwnerBean.setPhone_number(rs.getString("theater_employee_mobile"));
					theaterOwnerBean.setEmployee_role(rs.getString("theatre_employee_role_name"));
					theaterOwnerBean.setTheater_name(rs.getString("theater_name"));
					if(rs.getBytes("employee_profile") != null) {
						theaterOwnerBean.setStatus("Success");
					} 
					else{
						theaterOwnerBean.setStatus("notSuccess");

					}
					theaterEmployeeList.add(theaterOwnerBean);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return theaterEmployeeList;
		}

		//get employee details
		public TheaterOwnerBean getEmployeeEditDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String employeeList="SELECT t.theater_employee_id,t.theater_employee_name,t.employee_dob,t.theater_employee_mail,t.theater_employee_mobile,t.employee_profile,t.theater_employee_role_id ,r.theatre_employee_role_name,t.gender_id,t.employee_profile,t.theater_id,t.employee_id FROM theater_employee_details t INNER JOIN theatre_employee_role r ON t.theater_employee_role_id=r.theatre_employee_role_id WHERE  t.theater_employee_id=? AND status='Active' AND is_deleted='N'";
				ps = conn.prepareStatement(employeeList);
				ps.setInt(1, theaterOwnerBean.getTheatre_employee_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
					
					//theaterOwnerBean.setEmployee_id(rs.getInt("theater_employee_id"));
					theaterOwnerBean.setTheatre_employee_id(rs.getInt("theater_employee_id"));
					theaterOwnerBean.setFirst_name(rs.getString("theater_employee_name"));
					theaterOwnerBean.setEmail_id(rs.getString("theater_employee_mail"));
					theaterOwnerBean.setDate_of_birth(rs.getString("employee_dob"));
					theaterOwnerBean.setPhone_number(rs.getString("theater_employee_mobile"));
					theaterOwnerBean.setEmployee_role(rs.getString("theatre_employee_role_name"));
					theaterOwnerBean.setTheatre_employee_role_id(rs.getInt("theater_employee_role_id"));
                    theaterOwnerBean.setGender_id(rs.getInt("gender_id"));
                    theaterOwnerBean.setTheater_id(rs.getInt("theater_id"));
                    theaterOwnerBean.setEmployee_id(rs.getString("employee_id"));
                    if(rs.getBytes("employee_profile") != null) {
						theaterOwnerBean.setStatus("Success");
					} 
					else{
						theaterOwnerBean.setStatus("notSuccess");

					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
			
			return theaterOwnerBean;
		}

		//set employee edit details
		public TheaterOwnerBean setTheaterEmployeeDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				
				if(theaterOwnerBean.getProfile_image() == null)
				{
					String updateEmployee="UPDATE theater_employee_details SET theater_employee_name=?,theater_employee_mail=?,theater_employee_mobile=?,gender_id=?,employee_dob=?,theater_employee_role_id=?,theater_id=?,employee_id=? WHERE theater_employee_id=?";
					ps = conn.prepareStatement(updateEmployee);
					ps.setString(1, theaterOwnerBean.getFirst_name());
					ps.setString(2, theaterOwnerBean.getEmail_id());
					ps.setString(3, theaterOwnerBean.getPhone_number());
					ps.setInt(4, theaterOwnerBean.getGender_id());
					ps.setString(5, theaterOwnerBean.getDate_of_birth());
					ps.setInt(6, theaterOwnerBean.getTheatre_employee_role_id());
					ps.setInt(7, theaterOwnerBean.getTheater_id());
					ps.setString(8, theaterOwnerBean.getEmployee_id());
					ps.setInt(9, theaterOwnerBean.getTheatre_employee_id());
					ps.executeUpdate();
				}
				else{
					
					InputStream profilrpictureInputStream = new FileInputStream(theaterOwnerBean.getProfile_image());

					String updateEmployee="UPDATE theater_employee_details SET theater_employee_name=?,theater_employee_mail=?,theater_employee_mobile=?,gender_id=?,employee_dob=?,theater_employee_role_id=?,employee_profile=?,theater_id=?,employee_id=? WHERE theater_employee_id=?";
					ps = conn.prepareStatement(updateEmployee);
					ps.setString(1, theaterOwnerBean.getFirst_name());
					ps.setString(2, theaterOwnerBean.getEmail_id());
					ps.setString(3, theaterOwnerBean.getPhone_number());
					//ps.setString(4, theaterOwnerBean);
					ps.setInt(4, theaterOwnerBean.getGender_id());
					ps.setString(5, theaterOwnerBean.getDate_of_birth());
					ps.setInt(6, theaterOwnerBean.getTheatre_employee_role_id());
					ps.setBinaryStream(7,profilrpictureInputStream,(int)theaterOwnerBean.getProfile_image().length());
					ps.setInt(8, theaterOwnerBean.getTheater_id());
					ps.setString(9, theaterOwnerBean.getEmployee_id());
					ps.setInt(10, theaterOwnerBean.getTheatre_employee_id());
					ps.executeUpdate();
					
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
				
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

		@Override
		public TheaterOwnerBean deleteTheaterEmployeeDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String deleteEmployee="UPDATE theater_employee_details SET status=?,is_deleted=? WHERE  theater_employee_id=?";
				ps = conn.prepareStatement(deleteEmployee);
				ps.setString(1, "InActive");
				ps.setString(2, "Y");
				ps.setInt(3, theaterOwnerBean.getTheatre_employee_id());
				ps.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
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

		
		//get movie languge
		public Map<Integer, String> getMovieLanguages() throws Exception {
			Map<Integer, String> movieLangMap=new HashMap<Integer,String>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String movie_lang="select language_id,language_name from  movie_languge";
				ps = conn.prepareStatement(movie_lang);
				rs = ps.executeQuery();
				while(rs.next())
				{
					movieLangMap.put(rs.getInt("language_id"), rs.getString("language_name"));
				}
			
			
		}	
			catch(Exception e) {
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return movieLangMap;
}

	//get movie genre
		public Map<Integer, String> getMovieGenre() throws Exception {
			Map<Integer, String> movieGenreMap=new HashMap<Integer,String>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String movie_genre="select genre_id,genre_name from  movie_genre";
				ps = conn.prepareStatement(movie_genre);
				rs = ps.executeQuery();
				while(rs.next())
				{
					movieGenreMap.put(rs.getInt("genre_id"), rs.getString("genre_name"));
				}
			
			
		}	
			catch(Exception e) {
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return movieGenreMap;
		}

		
		//get movie format
		public Map<Integer, String> getMovieFormat() throws Exception {
			Map<Integer, String> movieFormatMap=new HashMap<Integer,String>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String movie_format="select movie_format_id,format_name from  movie_format";
				ps = conn.prepareStatement(movie_format);
				rs = ps.executeQuery();
				while(rs.next())
				{
					movieFormatMap.put(rs.getInt("movie_format_id"), rs.getString("format_name"));
				}
			
			
		}	
			catch(Exception e) {
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return movieFormatMap;
		}

		//set movie details submit
		public TheaterOwnerBean setMovieDetailsSubit(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				
				int movie_generated_id = 0;
				String sql="SELECT movie_details_id FROM movie_details ORDER BY movie_details_id DESC LIMIT 1";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if(rs.next()){
					
					movie_generated_id = rs.getInt("movie_details_id");
				}
				else{
					movie_generated_id = 1;
				}
				
				String movie_id = "MV"+movie_generated_id;
				
				
				String movie_details="INSERT INTO movie_details(master_movie_id,movie_language_id,genre_id,Format_id,movie_duration,movie_poster,movie_release_date,theater_owner_id,is_deleted,STATUS,movie_id,certification_id,created_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,NOW())";
				ps = conn.prepareStatement(movie_details, ps.RETURN_GENERATED_KEYS);
				ps.setInt(1, theaterOwnerBean.getMaster_movie_id());
				ps.setInt(2, theaterOwnerBean.getMovie_language_id());
				ps.setInt(3, theaterOwnerBean.getMovie_genre_id());
				ps.setInt(4, theaterOwnerBean.getMovie_format_id());
				ps.setString(5, theaterOwnerBean.getMovie_duration());
				
				if(theaterOwnerBean.getMovie_poster()!=null)
				{
					InputStream profilrpictureInputStream = new FileInputStream(theaterOwnerBean.getMovie_poster());
					ps.setBinaryStream(6,profilrpictureInputStream,(int)theaterOwnerBean.getMovie_poster().length());
				}
				else{
					ps.setBinaryStream(6,null);
				}
				ps.setString(7, theaterOwnerBean.getMovie_release_date());
				ps.setInt(8,theaterOwnerBean.getTheater_owner_id());
				ps.setString(9, "N");
				ps.setString(10, "Active");
				ps.setString(11, movie_id);
				ps.setInt(12, theaterOwnerBean.getMovie_certification_id());
				ps.executeUpdate();		
				rs = ps.getGeneratedKeys();
				if(rs.next())
				{
					//movie_details_id
					theaterOwnerBean.setMovie_details_id(rs.getInt(1));
					theaterOwnerBean.setNav_bar_movie_status("Yes");
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
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

		
		public ArrayList<TheaterOwnerBean> getMovieDetailList(TheaterOwnerBean theaterOwnerBean) throws Exception {
			ArrayList<TheaterOwnerBean> movieList=new ArrayList<TheaterOwnerBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			
			try {
				conn = connectionManager.getConnection();
				String movie_list="SELECT k.movie_certification_name,n.movie_name,m.movie_id,m.movie_details_id,m.movie_language_id,m.genre_id,m.Format_id,m.movie_duration,m.movie_poster,l.language_name,f.format_name,g.genre_name,m.movie_release_date FROM movie_details m"
						+ " INNER JOIN movie_languge l ON m.movie_language_id=l.language_id INNER JOIN  movie_genre g ON g.genre_id=m.genre_id INNER JOIN movie_format f ON f.movie_format_id=m.Format_id INNER JOIN master_movie_detail n ON n.master_movie_id = m.master_movie_id INNER JOIN movie_certification k ON k.movie_certification_id = m.certification_id WHERE m.theater_owner_id = ? AND m.is_deleted='N'";
				ps = conn.prepareStatement(movie_list);
				ps.setInt(1, theaterOwnerBean.getTheater_owner_id());
				rs = ps.executeQuery();
				while(rs.next()){
					theaterOwnerBean=new TheaterOwnerBean();
					theaterOwnerBean.setMovie_details_id(rs.getInt("movie_details_id"));
					theaterOwnerBean.setMovie_name(rs.getString("movie_name"));
					theaterOwnerBean.setMovie_languge_name(rs.getString("language_name"));
					theaterOwnerBean.setMovie_genre_name(rs.getString("genre_name"));
					theaterOwnerBean.setMovie_format_name(rs.getString("format_name"));
					theaterOwnerBean.setMovie_certification_name(rs.getString("movie_certification_name"));
					theaterOwnerBean.setMovie_duration(rs.getString("movie_duration"));
					theaterOwnerBean.setMovie_image(rs.getBlob("movie_poster"));
					theaterOwnerBean.setMovie_id(rs.getString("movie_id"));
					theaterOwnerBean.setMovie_release_date(rs.getString("movie_release_date"));
					movieList.add(theaterOwnerBean);
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return movieList;
		}

		
		public Map<Integer, byte[]> getMoviePosterImage(TheaterOwnerBean theaterOwnerBean) throws Exception {
			Map<Integer, byte[]> movieImgeMap = new HashMap<Integer,byte[]>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String movie_poster="select movie_details_id,movie_poster from  movie_details where movie_details_id =?";
				ps = conn.prepareStatement(movie_poster);
				ps.setInt(1, theaterOwnerBean.getMovie_details_id());
				rs = ps.executeQuery();
				while(rs.next()) {
					if(rs.getBytes("movie_poster") != null) {
					movieImgeMap.put(rs.getInt("movie_details_id"), rs.getBytes("movie_poster"));
					
				  }
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return movieImgeMap;
		}

		//setDeleteScreenLayoutDetail created by ramya -03-08-18
		public TheaterDetailBean setDeleteScreenLayoutDetail(
				TheaterDetailBean theaterDetailBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			try
			{
				    conn = connectionManager.getConnection();
				
					String sql = "{CALL Ticket_TO_setDeleteScreenLayoutDetail(?,?,?,?)}";
					cl = conn.prepareCall(sql);
					cl.setInt(1, theaterDetailBean.getTheater_owner_id());
					cl.setInt(2, theaterDetailBean.getTheater_id());
					cl.setInt(3, theaterDetailBean.getScreen_id());
					cl.registerOutParameter(4, Types.VARCHAR);
					cl.execute();
					
					theaterDetailBean.setScreen_status(cl.getString(4));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return theaterDetailBean;
		}

		//getEmployeProfileImage created by ramya -04-08-18
		public Map<Integer, byte[]> getEmployeProfileImage(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
			try {
				conn = connectionManager.getConnection();
				String sql = "SELECT theater_employee_id,employee_profile FROM theater_employee_details WHERE created_user_id =? AND theater_employee_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1,theaterOwnerBean.getTheater_owner_id());
				ps.setInt(2, theaterOwnerBean.getTheatre_employee_id());
				rs = ps.executeQuery();
				while(rs.next()) {
					if(rs.getBytes("employee_profile") != null) {
						imageMap.put(rs.getInt("theater_employee_id"), rs.getBytes("employee_profile"));
					
				  }
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return imageMap;
		}

	//edit movie details
		public TheaterOwnerBean setMovieEditDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				
				String movie_details="SELECT k.movie_name,m.certification_id,m.format_id,m.movie_language_id,m.movie_details_id,m.master_movie_id,m.movie_language_id,m.genre_id,m.Format_id,m.movie_duration,m.movie_poster,l.language_name,f.format_name,g.genre_name,m.movie_release_date FROM movie_details m "
						+ "INNER JOIN  movie_languge l ON m.movie_language_id=l.language_id INNER JOIN  movie_genre g ON g.genre_id=m.genre_id INNER JOIN movie_format f ON f.movie_format_id=m.Format_id INNER JOIN master_movie_detail k ON k.master_movie_id = m.master_movie_id WHERE is_deleted='N' AND theater_owner_id=? AND movie_details_id =?" ; 
				ps = conn.prepareStatement(movie_details);
				ps.setInt(1,theaterOwnerBean.getTheater_owner_id());
				ps.setInt(2, theaterOwnerBean.getMovie_details_id());
				rs = ps.executeQuery();
				while(rs.next()) {
					theaterOwnerBean.setMovie_name(rs.getString("movie_name"));
					theaterOwnerBean.setMovie_certification_id(rs.getInt("certification_id"));
					theaterOwnerBean.setMovie_format_id(rs.getInt("format_id"));
					theaterOwnerBean.setMovie_language_id(rs.getInt("movie_language_id"));
					theaterOwnerBean.setMovie_details_id(rs.getInt("movie_details_id"));
					theaterOwnerBean.setMaster_movie_id(rs.getInt("master_movie_id"));
					theaterOwnerBean.setMovie_languge_name(rs.getString("language_name"));
					theaterOwnerBean.setMovie_genre_name(rs.getString("genre_name"));
					theaterOwnerBean.setMovie_genre_id(rs.getInt("genre_id"));
					theaterOwnerBean.setMovie_format_name(rs.getString("format_name"));
					theaterOwnerBean.setMovie_duration(rs.getString("movie_duration"));
					theaterOwnerBean.setMovie_release_date(rs.getString("movie_release_date"));
				  }
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return theaterOwnerBean;
		}

		//edit movie submit
		public TheaterOwnerBean setMovieEditDetailSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				
				conn = connectionManager.getConnection();
				
				if(theaterOwnerBean.getMovie_poster()!=null)
				{
					InputStream profilrpictureInputStream = new FileInputStream(theaterOwnerBean.getMovie_poster());

					String update_movie="UPDATE movie_details SET master_movie_id=?,movie_language_id=?,genre_id=?,Format_id=?,movie_duration=?,movie_poster=?,movie_release_date=?,certification_id=? WHERE movie_details_id=? and theater_owner_id=?";
					ps = conn.prepareStatement(update_movie);
					ps.setInt(1, theaterOwnerBean.getMaster_movie_id());
					ps.setInt(2, theaterOwnerBean.getMovie_language_id());
					ps.setInt(3, theaterOwnerBean.getMovie_genre_id());
					ps.setInt(4, theaterOwnerBean.getMovie_format_id());
					ps.setString(5, theaterOwnerBean.getMovie_duration());
					ps.setBinaryStream(6,profilrpictureInputStream,(int)theaterOwnerBean.getMovie_poster().length());
					ps.setString(7, theaterOwnerBean.getMovie_release_date());
					ps.setInt(8, theaterOwnerBean.getMovie_certification_id());
					ps.setInt(9, theaterOwnerBean.getMovie_details_id());
					ps.setInt(10, theaterOwnerBean.getTheater_owner_id());
					ps.executeUpdate();	
				}
				else{
					String update_movie="UPDATE movie_details SET master_movie_id=?,movie_language_id=?,genre_id=?,Format_id=?,movie_duration=?,movie_release_date=?,certification_id=? WHERE movie_details_id=? and theater_owner_id=?";
					ps = conn.prepareStatement(update_movie);
					ps.setInt(1, theaterOwnerBean.getMaster_movie_id());
					ps.setInt(2, theaterOwnerBean.getMovie_language_id());
					ps.setInt(3, theaterOwnerBean.getMovie_genre_id());
					ps.setInt(4, theaterOwnerBean.getMovie_format_id());
					ps.setString(5, theaterOwnerBean.getMovie_duration());
					ps.setString(6, theaterOwnerBean.getMovie_release_date());
					ps.setInt(7, theaterOwnerBean.getMovie_certification_id());
					ps.setInt(8, theaterOwnerBean.getMovie_details_id());
					ps.setInt(9, theaterOwnerBean.getTheater_owner_id());
					ps.executeUpdate();	
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return theaterOwnerBean;
		}

		//delete movie details
		public TheaterOwnerBean deleteMovieDetailsSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String deleteEmployee="UPDATE movie_details SET status=?,is_deleted=? WHERE movie_details_id=?";
				ps = conn.prepareStatement(deleteEmployee);
				ps.setString(1, "InActive");
				ps.setString(2, "Y");
				ps.setInt(3, theaterOwnerBean.getMovie_details_id());
				ps.executeUpdate();
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return theaterOwnerBean;
		}

		//getTheaterMapDetail created by ramya - 06-08-18
		public Map<Integer, String> getTheaterMapDetail(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			Map<Integer, String> theaterMap = new HashMap<Integer, String>();
			try {
				conn = connectionManager.getConnection();
				String sql = "SELECT theater_id,theater_name FROM theater_detail WHERE theater_owner_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1,theaterOwnerBean.getTheater_owner_id());
				rs = ps.executeQuery();
				while(rs.next()) {
						theaterMap.put(rs.getInt("theater_id"), rs.getString("theater_name"));
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return theaterMap;
		}

		//getTheaterScreenDetailsForView
		public TheaterOwnerBean getTheaterScreenDetailsForView(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{

			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> theaterMap = new HashMap<Integer,String>();
			Map<Integer, String> screenMap = new HashMap<Integer,String>();
			Map<Integer, String> categoryMap = new HashMap<Integer,String>();
			int i = 1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getTheaterScreenDetails_View(?,?,?,?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
				cl.registerOutParameter(2, Types.INTEGER);
				cl.registerOutParameter(3, Types.INTEGER);
				cl.registerOutParameter(4, Types.VARCHAR);
				boolean result = cl.execute();
				
				if(result)
				{
					theaterOwnerBean.setTheater_id(cl.getInt(2));
					theaterOwnerBean.setScreen_id(cl.getInt(3));
					theaterOwnerBean.setTheater_name(cl.getString(4));
					theaterOwnerBean.setStatus("success");
					
					while(result)
					{
						ResultSet rs = cl.getResultSet();
						
						if(i==1)
						{
							while(rs.next())
							{
								theaterMap.put(rs.getInt("theater_id"), rs.getString("theater_name"));
							}
						}
						theaterOwnerBean.setTheaterMap(theaterMap);
						
						if(i==2)
						{
							while(rs.next())
							{
								screenMap.put(rs.getInt("screen_id"), rs.getString("screen_name"));
							}
						}
						theaterOwnerBean.setScreenMap(screenMap);
						
						if(i==3)
						{
							while(rs.next())
							{
								categoryMap.put(rs.getInt("seat_category_id"), rs.getString("seat_category"));
							}
						}
						theaterOwnerBean.setTicketCategoryMap(categoryMap);
						
						i++;
						result = cl.getMoreResults();
					}
				}
				else
				{
					theaterOwnerBean.setStatus("failure");
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getTheaterScreenDetailsForView";
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

		//getTheaterAgainstScreenForview
		public TheaterOwnerBean getTheaterAgainstScreenForview(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{

			Connection conn = null;
			CallableStatement cl = null;
			ResultSet rs = null;
			Map<Integer, String> screenMap = new HashMap<Integer,String>();
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getTheaterAgainstScreen_View(?,?,?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_id());
				cl.registerOutParameter(2, Types.INTEGER);
				cl.registerOutParameter(3, Types.VARCHAR);
				cl.execute();
				rs = cl.executeQuery();
				
				theaterOwnerBean.setScreen_id(cl.getInt(2));
				theaterOwnerBean.setTheater_name(cl.getString(3));
				
				while(rs.next())
				{
					screenMap.put(rs.getInt("screen_id"), rs.getString("screen_name"));
				}
				
				theaterOwnerBean.setScreenMap(screenMap);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getTheaterAgainstScreenForview";
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

		//getEditScreenDetails
		public TheaterOwnerBean getEditScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			CallableStatement cl = null;
			int i = 1;
			Map<Integer, String> categoryMap = new HashMap<Integer,String>();
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getEditScreenDetails(?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getScreen_id());
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
					
					if(i==1)
					{
						if(rs.next())
						{
							theaterOwnerBean.setCategory_from_id(rs.getInt("seat_category_start_id"));
							theaterOwnerBean.setCategory_to_id(rs.getInt("seat_category_end_id"));
							theaterOwnerBean.setOrder_id(rs.getInt("order_id"));
							theaterOwnerBean.setPassage_count(rs.getInt("passage_count"));
						}
					}
					
					if(i==2)
					{
						while(rs.next())
						{
							categoryMap.put(rs.getInt("seat_category_id"), rs.getString("seat_category"));	
						}
					}
					
					theaterOwnerBean.setSeatCategoryMap(categoryMap);
					
					i++;
					res = cl.getMoreResults();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getEditScreenDetails";
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

		//getDistrictWiseCityDetails
		public TheaterOwnerBean getDistrictWiseCityDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			Map<Integer, String> cityMap = new HashMap<Integer,String>();
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "SELECT city_id,city_name FROM city WHERE district_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getDistrict_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
					cityMap.put(rs.getInt("city_id"), rs.getString("city_name"));
				} 
				theaterOwnerBean.setCityMap(cityMap);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getDistrictWiseCityDetails";
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

		//getNavBarScreenDetails
		public TheaterOwnerBean getNavBarScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			CallableStatement cl = null;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getNavBarScreenDetails(?,?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
				cl.registerOutParameter(2, Types.VARCHAR);
				cl.execute();
				
				theaterOwnerBean.setNavBarScreenStatus(cl.getString(2));
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getNavBarScreenDetails";
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

	//getMovieIdDetails created by ramya 10-08-18
		public Map<Integer, String> getMovieIdDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> movieIdMap = new HashMap<Integer,String>();
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getMovieIdDetails(?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
					
				if(i==1)
				{
					while(rs.next())
					{
						movieIdMap.put(rs.getInt("movie_details_id"), rs.getString("movie_name"));
					}
				}
					
					i++;
					res = cl.getMoreResults();
				}
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getMovieIdDetails";
				catchMethodLogger(str, e);
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return movieIdMap;
		}

		//setMovieScreenShowTimeDetail created by ramya 10-08-18
		public TheaterOwnerBean setMovieScreenShowTimeDetail(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
			try {
				int showdateId = Integer.parseInt(theaterOwnerBean.getShow_date().replace("-",""));
				 
				conn = connectionManager.getConnection();
				String sql="INSERT INTO theater_show_details (theater_id,theater_owner_id,screen_id,movie_detail_id,show_date,show_date_id,movie_language_id,genre_id,format_id,creation_date) VALUES(?,?,?,?,?,?,?,?,?,NOW())";
				ps = conn.prepareStatement(sql,ps.RETURN_GENERATED_KEYS);
				ps.setInt(1, theaterOwnerBean.getTheater_id());
				ps.setInt(2, theaterOwnerBean.getTheater_owner_id());
				ps.setInt(3, theaterOwnerBean.getScreen_id());
				ps.setInt(4, theaterOwnerBean.getMovie_details_id());
				ps.setString(5, theaterOwnerBean.getShow_date());
				ps.setInt(6, showdateId);
				ps.setInt(7, theaterOwnerBean.getMovie_language_id());
				ps.setInt(8, theaterOwnerBean.getMovie_genre_id());
				ps.setInt(9, theaterOwnerBean.getMovie_format_id());
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				int show_detail_id = 0;
				if(rs.next()){
					show_detail_id = rs.getInt(1);
					theaterOwnerBean.setShow_detail_id(show_detail_id);
				}
				
				for (String showTime : theaterOwnerBean.getShowTimingList()) {
				
					String sql1="INSERT INTO show_timing (show_details_id,show_timing,STATUS,is_deleted) VALUES(?,?,'Active','N')";
					ps = conn.prepareStatement(sql1);
					ps.setInt(1, theaterOwnerBean.getShow_detail_id());
					ps.setString(2, showTime);
					ps.executeUpdate();
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return theaterOwnerBean;
		}

     //getMovieScreenShowDetail created by ramya-11-08-18
		public TheaterOwnerBean getMovieScreenShowDetail(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
			PreparedStatement ps2=null;
			ResultSet rs2=null;
			Connection conn=null;
			TheaterDetailBean theaterDetailBean = null;
			ScreenDetailBean screenDetailBean = null;
			TicketCounterBean ticketCounterBean = null;
			ArrayList<TheaterDetailBean> movieList = new ArrayList<TheaterDetailBean>();
			try {
				
				conn = connectionManager.getConnection();
				String date = "";
				if(theaterOwnerBean.getStatus_id() == 1){
					date = theaterOwnerBean.getDate();
				}
				else{
					Date today = new Date();
					SimpleDateFormat date_Format = new SimpleDateFormat("yyyy-MM-dd");
					date = date_Format.format(today);
				}
				
				theaterOwnerBean.setDate(date);
				
				int dateId = Integer.parseInt(date.replace("-", ""));
				
				String sql="SELECT DISTINCT(s.movie_details_id),n.movie_name,s.movie_id,l.theater_name,l.theater_id FROM movie_details s INNER JOIN theater_show_details m ON s.movie_details_id = m.movie_detail_id INNER JOIN theater_detail l ON l.theater_id = m.theater_id INNER JOIN master_movie_detail n ON n.master_movie_id = s.master_movie_id WHERE m.theater_owner_id = ? AND m.show_date_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getTheater_owner_id());
				ps.setInt(2, dateId);
				rs = ps.executeQuery();
				while(rs.next()){
					theaterDetailBean = new TheaterDetailBean();
					
					theaterDetailBean.setMovie_detail_id(rs.getInt("movie_details_id"));
					theaterDetailBean.setMovie_name(rs.getString("movie_name"));
					theaterDetailBean.setMovie_id(rs.getString("movie_id"));
					theaterDetailBean.setTheater_name(rs.getString("theater_name"));
					theaterDetailBean.setTheater_id(rs.getInt("theater_id"));
					
					ArrayList<ScreenDetailBean> screenList = new ArrayList<ScreenDetailBean>();

					String sql1="SELECT m.show_details_id,s.screen_id,s.screen_name FROM screen_detail s INNER JOIN theater_show_details m ON s.screen_id = m.screen_id WHERE m.movie_detail_id = ? AND m.theater_id = ?";
					ps1 = conn.prepareStatement(sql1);
					ps1.setInt(1, theaterDetailBean.getMovie_detail_id());
					ps1.setInt(2, theaterDetailBean.getTheater_id());
					rs1 = ps1.executeQuery();
					while(rs1.next()){
						
					    screenDetailBean =  new ScreenDetailBean();
						screenDetailBean.setScreen_id(rs1.getInt("screen_id"));
						screenDetailBean.setScreen_name(rs1.getString("screen_name"));
						screenDetailBean.setShow_detail_id(rs1.getInt("show_details_id"));
						
						ArrayList<TicketCounterBean> showTimingList = new ArrayList<TicketCounterBean>();

						String sql2="SELECT show_timing_id,show_timing FROM show_timing WHERE show_details_id = ? AND STATUS = 'Active' AND is_deleted = 'N'";
						ps2 = conn.prepareStatement(sql2);
						ps2.setInt(1, screenDetailBean.getShow_detail_id());
						rs2 = ps2.executeQuery();
						while(rs2.next()){
							
							ticketCounterBean = new TicketCounterBean();
							ticketCounterBean.setShow_timing_id(rs2.getInt("show_timing_id"));
							
							SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
							SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
							String showTime = date12Format.format(date24Format.parse(rs2.getString("show_timing")));
							ticketCounterBean.setShow_timing(showTime);
                            
							showTimingList.add(ticketCounterBean);
						}
						screenDetailBean.setShowTimingList(showTimingList);
						screenList.add(screenDetailBean);
					}
					theaterDetailBean.setScreenList(screenList);
					movieList.add(theaterDetailBean);
				}
				
				theaterOwnerBean.setTheaterDetailList(movieList);
			}
			catch(Exception e) {
				e.printStackTrace();
				String str = "getMovieScreenShowDetail";
				catchMethodLogger(str, e);
			}
			
			return theaterOwnerBean;
		}

	    //getScreenMapDetail created by ramya-11-08-18
		public Map<Integer, String> getScreenMapDetail(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> screenMap = new HashMap<Integer,String>();
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getScreenMapDetail(?)}";
				cl = conn.prepareCall(sql);
				cl.setInt(1, theaterOwnerBean.getTheater_id());
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
					
				if(i==1)
				{
					while(rs.next())
					{
						screenMap.put(rs.getInt("screen_id"), rs.getString("screen_name"));
					}
				}
					
					i++;
					res = cl.getMoreResults();
				}
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getScreenMapDetail";
				catchMethodLogger(str, e);
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return screenMap;
		}

		//setDeleteShowTime created by ramya-11-08-18
		public TheaterOwnerBean setDeleteShowTime(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			try
			{
				    conn = connectionManager.getConnection();
				
					String sql = "{CALL Ticket_TO_setDeleteShowTime(?,?)}";
					cl = conn.prepareCall(sql);
					cl.setInt(1, theaterOwnerBean.getShow_timing_id());
					cl.registerOutParameter(2, Types.VARCHAR);
					cl.execute();
					
					theaterOwnerBean.setShow_time_status(cl.getString(2));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "setDeleteShowTime";
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

		//getMovieIDDetails
		public TheaterOwnerBean getMovieIDDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "SELECT movie_name,movie_id FROM movie_details WHERE movie_details_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getMovie_details_id());
				rs = ps.executeQuery();
				if(rs.next())
				{
					theaterOwnerBean.setMovie_name(rs.getString("movie_name"));
					theaterOwnerBean.setMovie_id(rs.getString("movie_id"));
					
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getMovieIDDetails";
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

		//getNavBarMovieDetails created by ramya -13-08-18
		public TheaterOwnerBean getNavBarMovieDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			try
			{
				    conn = connectionManager.getConnection();
				
					String sql = "{CALL Ticket_TO_getNavBarMovieDetails(?,?)}";
					cl = conn.prepareCall(sql);
					cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
					cl.registerOutParameter(2, Types.VARCHAR);
					cl.execute();
					
					theaterOwnerBean.setNav_bar_movie_status(cl.getString(2));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getNavBarMovieDetails";
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

		//getNavBarUserDetails created by ramya -13-08-18
		public TheaterOwnerBean getNavBarUserDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			try
			{
				    conn = connectionManager.getConnection();
				
					String sql = "{CALL Ticket_TO_getNavBarUserDetails(?,?)}";
					cl = conn.prepareCall(sql);
					cl.setInt(1, theaterOwnerBean.getTheater_owner_id());
					cl.registerOutParameter(2, Types.VARCHAR);
					cl.execute();
					
					theaterOwnerBean.setNav_bar_user_status(cl.getString(2));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getNavBarUserDetails";
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
		
		//get movie date
		public HashMap<Integer, String> getMovieDate(TheaterOwnerBean theaterOwnerBean) throws Exception {
			HashMap<Integer, String> movieDateMap=new HashMap<Integer,String>();
			PreparedStatement ps=null;
			Connection conn=null;
			ResultSet rs=null;
			
			try {
				
				conn = connectionManager.getConnection();
				String datemap="select show_details_id,show_date from theater_show_details where movie_detail_id=?";
				ps = conn.prepareStatement(datemap);
				ps.setInt(1, theaterOwnerBean.getMovie_details_id());
				rs = ps.executeQuery();
				if(rs.next())
				{
					//screenMap.put(rs.getInt("screen_id"), rs.getString("screen_name"));
					movieDateMap.put(rs.getInt("show_details_id"), rs.getString("show_date"));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			return movieDateMap;
		}

		//get show details
		public HashMap<Integer, String> getMovieShows(TheaterOwnerBean theaterOwnerBean) throws Exception {
			HashMap<Integer, String> movieShowMap=new HashMap<Integer,String>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
		
			try {
				conn = connectionManager.getConnection();
				String movieshow="SELECT s.screen_id,t.show_timing  FROM  theater_show_details s INNER JOIN  show_timing t ON t.show_details_id=s.show_details_id  WHERE s.movie_detail_id=?";
				ps = conn.prepareStatement(movieshow);
				ps.setInt(1, theaterOwnerBean.getMovie_details_id());
				rs = ps.executeQuery();
				if(rs.next())
				{
					//screenMap.put(rs.getInt("screen_id"), rs.getString("screen_name"));
					movieShowMap.put(rs.getInt("screen_id"),rs.getString("show_timing"));
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return movieShowMap;
		}

		@Override
		public ArrayList<TheaterOwnerBean> getMovieShowList(TheaterOwnerBean theaterOwnerBean) throws Exception {
			ArrayList<TheaterOwnerBean> movieShowList=new ArrayList<TheaterOwnerBean>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
		
			try {
				conn = connectionManager.getConnection();
				String movieshow="SELECT s.show_details_id,s.screen_id,s.movie_detail_id,s.theater_id,t.show_timing_id,t.show_timing  FROM  theater_show_details s INNER JOIN  show_timing t ON t.show_details_id=s.show_details_id  WHERE s.movie_detail_id=?";
				ps = conn.prepareStatement(movieshow);
				ps.setInt(1, theaterOwnerBean.getMovie_details_id());
				rs = ps.executeQuery();
			    while(rs.next())
				{
			    	//theaterOwnerBean.setMovie_name(rs.getString("movie_name"));
			    	theaterOwnerBean=new TheaterOwnerBean();
			    	theaterOwnerBean.setShow_detail_id(rs.getInt("show_details_id"));
			    	theaterOwnerBean.setScreen_id(rs.getInt("screen_id"));
			    	theaterOwnerBean.setMovie_details_id(rs.getInt("movie_detail_id"));
			    	theaterOwnerBean.setTheater_id(rs.getInt("theater_id"));
			    	theaterOwnerBean.setShow_timing_id(rs.getInt("show_timing_id"));
			    	
			    	SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
					SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
					String showTime = date12Format.format(date24Format.parse(rs.getString("show_timing")));
			    	theaterOwnerBean.setShow_time(showTime);	
			    	movieShowList.add(theaterOwnerBean);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return movieShowList;
		}

		//getScreenwiseSeatingDetails
		public TheaterOwnerBean getScreenwiseSeatingDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
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

				String solded_seats = "";
				
				String sql6 = "SELECT seat_id FROM movie_ticket_sold_details WHERE theater_id =? AND screen_id =? AND show_timing_id = ? AND movie_detail_id = ? AND show_detail_id = ? AND STATUS = 'Active' AND is_deleted = 'N'";
				ps = conn.prepareStatement(sql6);
				ps.setInt(1, theaterOwnerBean.getTheater_id());
				ps.setInt(2, theaterOwnerBean.getScreen_id());
				ps.setInt(3, theaterOwnerBean.getShow_timing_id());
				ps.setInt(4, theaterOwnerBean.getMovie_details_id());
				ps.setInt(5, theaterOwnerBean.getShow_detail_id());
				rs = ps.executeQuery();
				while(rs.next()) {
					
					solded_seats = rs.getString("seat_id");
				}
				
				String soldedSeatsArray[] = solded_seats.split(",");
				
				 for(int j=0;j < soldedSeatsArray.length;j++) {
						
					 soldedSeatsList.add(soldedSeatsArray[j]);
				}
				 
				String sql = "SELECT seat_category_start_id,seat_category_end_id,order_id FROM screen_seat_category_detail WHERE screen_id = ? AND theater_id = ? AND theater_owner_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getScreen_id());
				ps.setInt(2, theaterOwnerBean.getTheater_id());
				ps.setInt(3, theaterOwnerBean.getTheater_owner_id());
				rs = ps.executeQuery();
				while(rs.next()) {
					
					seat_category_start_id = rs.getInt("seat_category_start_id");
					seat_category_end_id = rs.getInt("seat_category_end_id");
					order_id = rs.getInt("order_id");
				}
				
				String sql2 = "SELECT passage_count FROM screen_detail WHERE screen_id = ? AND theater_id = ?";
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, theaterOwnerBean.getScreen_id());
				ps.setInt(2, theaterOwnerBean.getTheater_id());
				rs = ps.executeQuery();
				while(rs.next()) {
					
					passage_count = rs.getInt("passage_count");
				}
				
				String sql4 = "SELECT seat_count FROM `floor_plan_detail` WHERE theater_id = ? AND screen_id = ? ORDER BY seat_count DESC LIMIT 1";
				ps = conn.prepareStatement(sql4);
				ps.setInt(1, theaterOwnerBean.getTheater_id());
				ps.setInt(2, theaterOwnerBean.getScreen_id());
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
						ps1.setInt(1, theaterOwnerBean.getScreen_id());
						ps1.setInt(2, theaterOwnerBean.getTheater_id());
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
						ps1.setInt(1, theaterOwnerBean.getScreen_id());
						ps1.setInt(2, theaterOwnerBean.getTheater_id());
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
				
				theaterOwnerBean.setTheaterRowList(finalList);
				
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
			return theaterOwnerBean;
		}

		//getMasterMovieDetails created by ramya 15-08-18
		public Map<Integer, String> getMasterMovieDetails()
				throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> movieIdMap = new HashMap<Integer,String>();
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getMasterMovieDetails()}";
				cl = conn.prepareCall(sql);
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
					
				if(i==1)
				{
					while(rs.next())
					{
						movieIdMap.put(rs.getInt("master_movie_id"), rs.getString("movie_name"));
					}
				}
					
					i++;
					res = cl.getMoreResults();
				}
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getMasterMovieDetails";
				catchMethodLogger(str, e);
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return movieIdMap;
		}

		//getMovieCertificationDetails created by ramya 15-08-18
		public Map<Integer, String> getMovieCertificationDetails()
				throws Exception {
			Connection conn = null;
			CallableStatement cl = null;
			Map<Integer, String> movieCertificationMap = new HashMap<Integer,String>();
			int i =1;
			try
			{
				conn = connectionManager.getConnection();
				
				String sql = "{CALL Ticket_TO_getMovieCertificationDetails()}";
				cl = conn.prepareCall(sql);
				boolean res = cl.execute();
				while(res)
				{
					ResultSet rs = cl.getResultSet();
					
				if(i==1)
				{
					while(rs.next())
					{
						movieCertificationMap.put(rs.getInt("movie_certification_id"), rs.getString("movie_certification_name"));
					}
				}
					
					i++;
					res = cl.getMoreResults();
				}
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				String str = "getMovieCertificationDetails";
				catchMethodLogger(str, e);
			}
			finally
			{
				if(conn!=null)
				{
					connectionManager.releaseConnection(conn);
				}
			}
			return movieCertificationMap;
		}


//ticket details
		public TheaterOwnerBean getTicketDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
			PreparedStatement ps2=null;
			ResultSet rs2=null;
			Connection conn=null;
			try {
				conn = connectionManager.getConnection();
				String sql="SELECT t.theater_name,s.screen_name FROM theater_detail t INNER JOIN  screen_detail s ON s.theater_id=t.theater_id WHERE t.theater_id=? AND s.screen_id=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getTheater_id());
				ps.setInt(2, theaterOwnerBean.getScreen_id());
				/*ps.setInt(3, theaterOwnerBean.getShow_timing_id());
				ps.setInt(4, theaterOwnerBean.getMovie_details_id());
				ps.setInt(5, theaterOwnerBean.getShow_detail_id());*/
				rs = ps.executeQuery();
				while(rs.next()) {
					theaterOwnerBean.setTheater_name(rs.getString("theater_name"));
					theaterOwnerBean.setScreen_name(rs.getString("screen_name"));
				}
				String movie_name="SELECT m.movie_name FROM master_movie_detail m INNER JOIN movie_details d ON d.master_movie_id = m.master_movie_id WHERE d.movie_details_id = ?";
				ps1 = conn.prepareStatement(movie_name);
				ps1.setInt(1, theaterOwnerBean.getMovie_details_id());
				rs1 = ps1.executeQuery();
				while(rs1.next()) {
					theaterOwnerBean.setMovie_name(rs1.getString("movie_name"));
				}
				String show_name="SELECT s.show_date,s.show_date_id,t.show_timing  FROM  theater_show_details s INNER JOIN show_timing t ON t.show_details_id=s.show_details_id WHERE s.theater_id=? AND s.screen_id=? AND t.show_timing_id=?";
				ps2 = conn.prepareStatement(show_name);
				ps2.setInt(1, theaterOwnerBean.getTheater_id());
				ps2.setInt(2, theaterOwnerBean.getScreen_id());
				ps2.setInt(3, theaterOwnerBean.getShow_timing_id());
				rs2 = ps2.executeQuery();
				while(rs2.next()) {
					theaterOwnerBean.setShow_date(rs2.getString("show_date"));
					theaterOwnerBean.setShow_date_id(rs2.getString("show_date_id"));
					theaterOwnerBean.setShow_time(rs2.getString("show_timing"));
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return theaterOwnerBean;
		}

		//tickt booking details
		@SuppressWarnings("unused")
		public TheaterOwnerBean getTicketSoldDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
			PreparedStatement ps2=null;
			ResultSet rs2=null;
			String seatStr = "";
			String bookingSeatStr = "";
			Connection conn=null;
			try {
				
              String ticket_status=Ticket_QueueMain.ticketQueue(theaterOwnerBean);
				
				return theaterOwnerBean;
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return theaterOwnerBean;
		}

		//getTaxMap
		public Map<Integer, String> getTaxMap(TheaterOwnerBean theaterOwnerBean) throws Exception {
			HashMap<Integer, String> taxMap=new HashMap<Integer,String>();
			PreparedStatement ps=null;
			ResultSet rs=null;
			Connection conn=null;
		
			try {
				conn = connectionManager.getConnection();
				String sql="SELECT theatre_id,theater_name FROM theater_detail WHERE theater_owner_id=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1,theaterOwnerBean.getTheater_owner_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
				
					taxMap.put(rs.getInt("theatre_id"), rs.getString("theater_name"));
					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return taxMap;
		}

		//getTheatreOwnersTaxDetail

		public TheaterOwnerBean getTheatreOwnersTaxDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			PreparedStatement ps=null;
			ResultSet rs=null;
			PreparedStatement ps1=null;
			ResultSet rs1=null;
			Connection conn=null;
		
			try {
				conn = connectionManager.getConnection();
				String[] date = theaterOwnerBean.getDate().split("-");
				int startDate = Integer.parseInt(date[0].replace("/", "").trim());
				int endDate = Integer.parseInt(date[1].replace("/", "").trim());

				String sql= "SELECT t.theater_name,COUNT(s.screen_name) AS screen_count FROM theater_detail t INNER JOIN screen_detail s ON t.theatre_id = s.theatre_id WHERE t.theatre_id=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1,theaterOwnerBean.getTheatre_id());
				rs = ps.executeQuery();
				while(rs.next())
				{
			    	theaterOwnerBean.setTheater_name(rs.getString("theater_name"));
			    	theaterOwnerBean.setScreen_count(rs.getInt("screen_count"));
				}
				               
				String sql1="SELECT SUM(total_amount) AS totalAmount,cgst_tax,sgst_tax,et_tax,total_payable_amount FROM report_theatre_wise_tax_detail WHERE date_key  BETWEEN  ? AND ? AND theatre_id=?";
			    ps1 = conn.prepareStatement(sql1);
			    ps1.setInt(1, startDate);
			    ps1.setInt(2, endDate);
			    ps1.setInt(3,theaterOwnerBean.getTheatre_id());
			    rs1 = ps1.executeQuery();
			    while(rs1.next())
			    {
			    	theaterOwnerBean.setTotal_amount(rs1.getInt("totalAmount"));
			    	theaterOwnerBean.setCgst_tax(rs1.getInt("cgst_tax"));
			    	theaterOwnerBean.setSgst_tax(rs1.getInt("sgst_tax"));
			    	theaterOwnerBean.setEt_tax(rs1.getInt("et_tax"));
			    	theaterOwnerBean.setTotal_payable_amount(rs1.getInt("total_payable_amount"));
			    }
			
			}
				catch(Exception e) {
				e.printStackTrace();
			}
			return theaterOwnerBean;
		}

		//getTheaterOwnerMoviewiseDetails
		public ArrayList<TheaterOwnerBean> getTheaterOwnerMoviewiseDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<TheaterOwnerBean>theatreOwnerMovieList=new ArrayList<TheaterOwnerBean>();
			try 
			{
				conn = connectionManager.getConnection();

				String[] date = theaterOwnerBean.getDate().split("-");
				int startDate = Integer.parseInt(date[0].replace("/", "").trim());
				int endDate = Integer.parseInt(date[1].replace("/", "").trim());
				
				if(startDate == endDate){
					
					String sql ="SELECT m.movie_name,i.ticket_amount,i.seat_id FROM movie_details m INNER JOIN individual_movie_ticket_sold_details i ON m.movie_details_id=i.movie_detail_id WHERE theater_owner_id=? AND show_date_id=?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, theaterOwnerBean.getTheater_owner_id());
					ps.setInt(2, startDate);
				}
				else{
					String sql =" SELECT m.movie_name,i.ticket_amount,i.seat_id FROM movie_details m INNER JOIN individual_movie_ticket_sold_details i ON m.movie_details_id=i.movie_detail_id WHERE theater_owner_id=? AND show_date_id BETWEEN  ? AND ?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, theaterOwnerBean.getTheater_owner_id());
					ps.setInt(2, startDate);
					ps.setInt(3, endDate);

				}
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterOwnerBean = new TheaterOwnerBean();
					theaterOwnerBean.setMovie_name(rs.getString("movie_name"));
					theaterOwnerBean.setTicket_amount(rs.getInt("ticket_amount"));
					String seatStr =  rs.getString("seat_id");
					if(seatStr !="")
					{
						String[] seat = seatStr.split(",");
						theaterOwnerBean.setSeat_count(seat.length);
					}
					
					theatreOwnerMovieList.add(theaterOwnerBean);
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return theatreOwnerMovieList;
		}

		//getTheaterOwnerMovieScreenwiseDetails
		public ArrayList<TheaterOwnerBean> getTheaterOwnerMovieScreenwiseDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			Connection conn = null;
			PreparedStatement ps = null;
			PreparedStatement ps1 = null; 
			ResultSet rs = null;
			ResultSet rs1 = null;
			ArrayList<TheaterOwnerBean>screenwiseList=new ArrayList<TheaterOwnerBean>();
			TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
			try 
			{
				conn = connectionManager.getConnection();
				
				int showdateId = Integer.parseInt(theaterOwnerBean.getDate().replace("-",""));
				
				String sql = "SELECT s.screen_id,s.screen_name,t.seat_id,SUM(t.ticket_amount) AS ticket_amount FROM screen_detail s INNER JOIN individual_movie_ticket_sold_details t ON s.screen_id=t.screen_id WHERE s.theater_owner_id=? AND show_date_id=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, theaterOwnerBean.getTheater_owner_id());
				ps.setInt(2, showdateId);
				rs = ps.executeQuery();
				while(rs.next())
				{
					theaterOwnerBean = new TheaterOwnerBean();
					theaterOwnerBean.setScreen_id(rs.getInt("screen_id"));
					theaterOwnerBean.setScreen_name(rs.getString("screen_name"));
					theaterOwnerBean.setTicket_amount(rs.getInt("ticket_amount"));
					String seatStr =  rs.getString("seat_id");
					if(seatStr !="")
					{
						String[] seat = seatStr.split(",");
						theaterOwnerBean.setSeat_count(seat.length);
					}
					
					ArrayList<TheaterDetailBean>showwiseList=new ArrayList<TheaterDetailBean>();

				
				String sql1 = "SELECT s.screen_name,sh.show_timing,i.ticket_amount FROM screen_detail s INNER JOIN individual_movie_ticket_sold_details i ON s.screen_id=i.screen_id INNER JOIN show_timing sh ON sh.show_timing_id=i.show_timing_id WHERE s.screen_id=?";
				ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, theaterOwnerBean.getScreen_id());
				rs1 = ps1.executeQuery();
				while(rs1.next())
				{
					//TheaterDetailBean theaterDetailBean = new TheaterDetailBean();
					theaterDetailBean = new TheaterDetailBean();
					theaterDetailBean.setScreen_name(rs1.getString("screen_name"));
					theaterDetailBean.setTicket_amount(rs1.getInt("ticket_amount"));
					
					SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm");
					SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm a");
					String showTime = date12Format.format(date24Format.parse(rs1.getString("show_timing")));
					theaterDetailBean.setShow_timing(showTime);
					showwiseList.add(theaterDetailBean);
				}
			    theaterOwnerBean.setShowwiseList(showwiseList);
				screenwiseList.add(theaterOwnerBean);
			}
				
		} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
					return screenwiseList;
				}

		}

