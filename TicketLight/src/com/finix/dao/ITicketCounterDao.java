package com.finix.dao;

import java.util.ArrayList;
import java.util.Map;

import com.finix.bean.TicketCounterBean;

public interface ITicketCounterDao {

	TicketCounterBean getTicketCounterPersonDetails(TicketCounterBean ticketCounterBean) throws Exception;

	Map<Integer, byte[]> getTicketCounterPersonProfileImage(TicketCounterBean ticketCounterBean) throws Exception;

	ArrayList<TicketCounterBean> getMovieDetails(TicketCounterBean ticketCounterBean) throws Exception;

	ArrayList<TicketCounterBean> getMovieWiseScreenDetails(TicketCounterBean ticketCounterBean) throws Exception;

}
