package com.finix.manager;

import java.util.ArrayList;
import java.util.Map;

import com.finix.bean.TicketCounterBean;

public interface ITicketCounterManager {

	TicketCounterBean getTicketCounterPersonDetails(TicketCounterBean ticketCounterBean) throws Exception;

	Map<Integer, byte[]> getTicketCounterPersonProfileImage(TicketCounterBean ticketCounterBean) throws Exception;

	ArrayList<TicketCounterBean> getMovieDetails(TicketCounterBean ticketCounterBean) throws Exception;

	ArrayList<TicketCounterBean> getMovieWiseScreenDetails(TicketCounterBean ticketCounterBean) throws Exception;

	
}
