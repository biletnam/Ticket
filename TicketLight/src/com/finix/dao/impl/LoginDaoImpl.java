package com.finix.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.finix.bean.LoginBean;
import com.finix.bean.TemplateBean;
import com.finix.dao.ILoginDao;
import com.finix.dao.utils.ConnectionManager;
import com.finix.utils.MyStringRandomGen;

public class LoginDaoImpl implements ILoginDao
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
        debugLog.error("Theater Owner Dao Impl :Method Name "+str );

        debugLog.error("Exception Occured Time " +dateFormatted);
        
        debugLog.error("Type of Exception"+e);
        debugLog.error("***************" );
		
	}
	
	//login
	public Map<String, Object> login(LoginBean loginBean) throws Exception 
	{
		String str = "login";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object>  profileMap=new HashMap<String, Object> ();
		boolean flag = false;
		try
		{
			conn = connectionManager.getConnection();
			
			int role_id = loginBean.getRole_xid();
		
				String sql = "SELECT user_id,government_user_id,theater_employee_id,theater_owner_id,email,PASSWORD,activation FROM login WHERE email = ? AND PASSWORD = ? AND role_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, loginBean.getEmail());
				ps.setString(2, loginBean.getPassword());
				ps.setInt(3, role_id);
				rs = ps.executeQuery();
				if(rs.next())
				{
					if(rs.getString("activation").equals("Y"))
					{
						flag = true;
						loginBean.setEmail(rs.getString("email"));
						loginBean.setPassword(rs.getString("PASSWORD"));
						loginBean.setTheater_owner_id(rs.getInt("theater_owner_id"));
						loginBean.setTicket_counter_person_id(rs.getInt("theater_employee_id"));
						loginBean.setGovernment_user_id(rs.getInt("government_user_id"));
						loginBean.setEnd_user_id(rs.getInt("user_id"));
						loginBean.setStatus("success");
						
					}
					else
					{
						flag = true;
						loginBean.setTheater_owner_id(rs.getInt("theater_owner_id"));
						loginBean.setTicket_counter_person_id(rs.getInt("theater_employee_id"));
						loginBean.setGovernment_user_id(rs.getInt("government_user_id"));
						loginBean.setEnd_user_id(rs.getInt("user_id"));
						loginBean.setStatus("activation_failed");
					}
				}
				
				if(flag==true)
				{
					profileMap.put("status", "success");
					profileMap.put("loginBean", loginBean);
				}
				else 
				{
					profileMap.put("status", "failure");
					profileMap.put("loginBean", loginBean);
				}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return profileMap;
	}
	
	
	//getPasswordDetails
	public LoginBean getPasswordDetails(LoginBean loginBean) throws Exception 
	{
		Connection conn =null;
		PreparedStatement ps =null;
		PreparedStatement ps1 =null;
		ResultSet rs=null;		
		ResultSet rs1=null;

		try
		{
			conn = connectionManager.getConnection();
			String randomnumber=MyStringRandomGen.generateRandomString();
			String sql ="SELECT theater_owner_id,theater_employee_id,government_user_id FROM login WHERE email=? AND role_id = ?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, loginBean.getEmail());
			ps.setInt(2, loginBean.getRole_xid());
			rs = ps.executeQuery();
			if(rs.next())
			{
				int role_id = loginBean.getRole_xid();
				 switch (role_id) {
				case 1:
					
					 loginBean.setTheater_owner_id(rs.getInt("theater_owner_id"));
					 String sql2 ="UPDATE login SET PASSWORD=? WHERE theater_owner_id=?";
					 ps1=conn.prepareStatement(sql2);
					 ps1.setString(1, randomnumber);
					 ps1.setInt(2, loginBean.getTheater_owner_id());
					 ps1.executeUpdate();
					 
					 String sql5 ="SELECT theater_owner_first_name FROM theater_owner_detail WHERE theater_owner_id = ?";
					 ps1=conn.prepareStatement(sql5);
					 ps1.setInt(1, loginBean.getTheater_owner_id());
					 rs1 = ps1.executeQuery();
					 while(rs1.next()){
						 loginBean.setName(rs1.getString("theater_owner_first_name"));
					 }
					
					break;
					
                case 2:
					
                	 loginBean.setTicket_counter_person_id(rs.getInt("theater_employee_id"));
					 String sql3 ="UPDATE login SET PASSWORD=? WHERE theater_employee_id=?";
					 ps1=conn.prepareStatement(sql3);
					 ps1.setString(1, randomnumber);
					 ps1.setInt(2, loginBean.getTicket_counter_person_id());
					 ps1.executeUpdate();
					 
					 String sql6 ="SELECT theater_employee_name FROM theater_employee_details WHERE theater_employee_id = ?";
					 ps1=conn.prepareStatement(sql6);
					 ps1.setInt(1, loginBean.getTicket_counter_person_id());
					 rs1 = ps1.executeQuery();
					 while(rs1.next()){
						 loginBean.setName(rs1.getString("theater_employee_name"));
					 }
					 
					break;
					
                case 3:
	
                	 loginBean.setGovernment_user_id(rs.getInt("government_user_id"));
					 String sql4 ="UPDATE login SET PASSWORD=? WHERE government_user_id=?";
					 ps1=conn.prepareStatement(sql4);
					 ps1.setString(1, randomnumber);
					 ps1.setInt(2, loginBean.getGovernment_user_id());
					 ps1.executeUpdate();
					 
					 String sql7 ="SELECT government_user_name FROM government_user_detail WHERE government_user_id = ?";
					 ps1=conn.prepareStatement(sql7);
					 ps1.setInt(1, loginBean.getGovernment_user_id());
					 rs1 = ps1.executeQuery();
					 while(rs1.next()){
						 loginBean.setName(rs1.getString("government_user_name"));
					 }
	            break;

				}
				
				 loginBean.setPassword(randomnumber);
				 loginBean.setStatus("success");
			}
			else 
			{
				loginBean.setStatus("email_invalid");
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			String str = "getPasswordDetails";
			catchMethodLogger(str, e);
		}
		finally
		{
			if(conn!=null)
			{
				connectionManager.releaseConnection(conn);
			}
		}
		return loginBean;	
	}

		//getforgotPassword
		public TemplateBean getforgotPassword(TemplateBean templateBean)throws Exception 
		{
			Connection conn =null;
			PreparedStatement ps =null;
			ResultSet rs = null;
			try
			{
				conn = connectionManager.getConnection();
				String sql ="SELECT template_value FROM template WHERE description=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, "Forgot_Password");
				rs=ps.executeQuery();
				while(rs.next())
				{
					templateBean.setTemplate(rs.getString("template_value"));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				String str = "getforgotPassword";
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
			
}

