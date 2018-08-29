package com.finix.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.finix.bean.TicketCounterBean;
import com.finix.dao.ITicketCounterDao;
import com.finix.dao.impl.TicketCounterDaoImpl;
import com.finix.manager.ITicketCounterManager;

public class TicketCounterManagerImpl implements ITicketCounterManager{
    ITicketCounterDao ticketCounterDao = new TicketCounterDaoImpl();
	
	//getTicketCounterPersonDetails created by ramya-09-08-18
	public TicketCounterBean getTicketCounterPersonDetails(
			TicketCounterBean ticketCounterBean) throws Exception {
		ticketCounterBean = ticketCounterDao.getTicketCounterPersonDetails(ticketCounterBean);
		return ticketCounterBean;
	}

	//getTicketCounterPersonProfileImage created by ramya-09-08-18
	public Map<Integer, byte[]> getTicketCounterPersonProfileImage(
			TicketCounterBean ticketCounterBean) throws Exception {
		Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
		imageMap = ticketCounterDao.getTicketCounterPersonProfileImage(ticketCounterBean);
		return imageMap;
	}

	//getMovieDetails
	
	public ArrayList<TicketCounterBean> getMovieDetails(TicketCounterBean ticketCounterBean) throws Exception {
	ArrayList<TicketCounterBean>moviewiseList=new ArrayList<TicketCounterBean>();
	moviewiseList =ticketCounterDao.getMovieDetails(ticketCounterBean);
	return moviewiseList;
	}

	//getMovieWiseScreenDetails
	public ArrayList<TicketCounterBean> getMovieWiseScreenDetails(TicketCounterBean ticketCounterBean) throws Exception {
	ArrayList<TicketCounterBean>screenwiseList=new ArrayList<TicketCounterBean>();
	screenwiseList =ticketCounterDao.getMovieWiseScreenDetails(ticketCounterBean);
	return screenwiseList;
	}
     
	 

}
