package com.finix.manager.impl;

import java.util.HashMap;
import java.util.Map;

import com.finix.bean.GovernmentUserBean;
import com.finix.bean.LoginBean;
import com.finix.bean.TemplateBean;
import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.TicketCounterBean;
import com.finix.bean.UserBean;
import com.finix.dao.ILoginDao;
import com.finix.dao.impl.LoginDaoImpl;
import com.finix.manager.IGovernmentUserManager;
import com.finix.manager.ILoginManager;
import com.finix.manager.ITheaterOwnerManager;
import com.finix.manager.ITicketCounterManager;
import com.finix.manager.IUserManager;

public class LoginManagerImpl implements ILoginManager
{
	ILoginDao loginDao = new LoginDaoImpl();
	ITheaterOwnerManager theaterOwnerManager = new TheaterOwnerManagerImpl();
	ITicketCounterManager ticketCounterManager = new TicketCounterManagerImpl();
	IGovernmentUserManager governmentUserManager = new GovernmentUserManagerImpl();
	IUserManager userManager = new UserManagerImpl();
	
	//login
	public Map<String, Object> login(LoginBean loginBean) throws Exception 
	{
		Map<String, Object> profileMap = new HashMap<String, Object>();
		try
		{
			profileMap = loginDao.login(loginBean);
			LoginBean lBean=(LoginBean)profileMap.get("loginBean");

			if(profileMap.get("status").equals("success"))
			{
				if(lBean.getRole_xid()== 1)
				{
					TheaterOwnerBean theaterOwnerBean = new TheaterOwnerBean();
					theaterOwnerBean.setTheater_owner_id(lBean.getTheater_owner_id());
					theaterOwnerBean = theaterOwnerManager.getTheaterOwnerDetails(theaterOwnerBean);

					if(lBean.getStatus().equals("success"))
					{
						theaterOwnerBean = theaterOwnerManager.getNavBarScreenDetails(theaterOwnerBean);
						theaterOwnerBean = theaterOwnerManager.getNavBarUserDetails(theaterOwnerBean);
						theaterOwnerBean = theaterOwnerManager.getNavBarMovieDetails(theaterOwnerBean);


						if(theaterOwnerBean  != null){
							profileMap.put("user", "theater-owner");
							profileMap.put("theaterOwnerBean", theaterOwnerBean);
						}
					}
					else if (lBean.getStatus().equals("activation_failed"))
					{
						
						if(theaterOwnerBean  != null){
							profileMap.put("user", "theater-owner-activation-failed");
							profileMap.put("theaterOwnerBean", theaterOwnerBean);
						}
					}
					
				}
				else if (lBean.getRole_xid()== 2) {
					
					TicketCounterBean ticketCounterBean = new TicketCounterBean();
					ticketCounterBean.setTicket_counter_person_id(lBean.getTicket_counter_person_id());
					ticketCounterBean = ticketCounterManager.getTicketCounterPersonDetails(ticketCounterBean);

					if(lBean.getStatus().equals("success"))
					{
						if(ticketCounterBean  != null){
							profileMap.put("user", "ticket-counter-person");
							profileMap.put("ticketCounterBean", ticketCounterBean);
						}
					}
					else if (lBean.getStatus().equals("activation_failed"))
					{
						
						if(ticketCounterBean  != null){
							profileMap.put("user", "ticket-counter-person-activation-failed");
							profileMap.put("ticketCounterBean", ticketCounterBean);
						}
					}
				}
                else if (lBean.getRole_xid()== 3) {
					
					GovernmentUserBean governmentUserBean = new GovernmentUserBean();
					governmentUserBean.setGovernment_user_id(lBean.getGovernment_user_id());
					governmentUserBean = governmentUserManager.getGovernmentUserDetails(governmentUserBean);

					if(lBean.getStatus().equals("success"))
					{
						if(governmentUserBean  != null){
							profileMap.put("user", "government-user");
							profileMap.put("governmentUserBean", governmentUserBean);
						}
					}
					else if (lBean.getStatus().equals("activation_failed"))
					{
						
						if(governmentUserBean  != null){
							profileMap.put("user", "government-user-activation-failed");
							profileMap.put("governmentUserBean", governmentUserBean);
						}
					}
				}
                else if (lBean.getRole_xid()== 4) 
                {
                	UserBean userBean = new UserBean();
                	userBean.setUser_id(lBean.getEnd_user_id());
                	
                	userBean = userManager.getUserDetails(userBean);
                	
                	if(lBean.getStatus().equals("success"))
					{
                		if(userBean!=null)
                		{
                			profileMap.put("user", "end-user");
							profileMap.put("userBean", userBean);
                		}
					}
                	else if (lBean.getStatus().equals("activation_failed"))
					{
                		if(userBean!=null)
                		{
                			profileMap.put("user", "end-user-activation-failed");
							profileMap.put("userBean", userBean);
                		}
					}
                	
                }
             
			}
			else{
				if(lBean.getRole_xid()== 1)
				{
					profileMap.put("user", "theater-owner");
				}
				else if (lBean.getRole_xid()== 2){
					
					profileMap.put("user", "ticket-counter-person");
				}
                else if (lBean.getRole_xid()== 3){
					
					profileMap.put("user", "government-user");
				}
                else if (lBean.getRole_xid()== 4)
                {
					profileMap.put("user", "end-user");
				}
             
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return profileMap;
	}
	
	
	//getPasswordDetails
	public LoginBean getPasswordDetails(LoginBean loginBean) throws Exception 
	{
		loginBean = loginDao.getPasswordDetails(loginBean);
		return loginBean;
	}


	//getforgotPassword
	public TemplateBean getforgotPassword(TemplateBean templateBean)
			throws Exception 
	{
		templateBean=loginDao.getforgotPassword(templateBean);
		return templateBean;
		
	}
}
