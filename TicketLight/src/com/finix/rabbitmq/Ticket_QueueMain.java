package com.finix.rabbitmq;

import com.finix.bean.TheaterOwnerBean;

public class Ticket_QueueMain {

	public static String ticketQueue(TheaterOwnerBean theaterOwnerBean) {
		  final String QUEUE_NAME = "Ticket_QUEUE";
		  
		  Ticket_consumer ticket_consumer = new Ticket_consumer();
	      Thread consumerThread = new Thread(ticket_consumer);
	      consumerThread.start();
	     
	      System.out.println(theaterOwnerBean.getBooking_seat());
	         //Publishes msg in the queue
	        Ticket_Producer producer = new Ticket_Producer(QUEUE_NAME);
	        producer.publishMessage(theaterOwnerBean);
	        
		  
		  
		return  "success";
	}

}
