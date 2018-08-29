package com.finix.rabbitmq;

import com.finix.bean.UserBean;

public class UserTicket_QueueMain {

	public static String ticketQueue(UserBean userBean) {
	
		 final String QUEUE_NAME = "UserTicket_QUEUE";
		  
		 UserTicket_consumer userticket_consumer = new UserTicket_consumer();
	      Thread consumerThread = new Thread(userticket_consumer);
	      consumerThread.start();
	     
	      System.out.println(userBean.getBooking_seat());
	         //Publishes msg in the queue
	      UserTicket_Producer producer = new UserTicket_Producer(QUEUE_NAME);
	        producer.publishMessage(userBean);
	        
		  
		  
		return  "success";
	}

}
