package com.finix.rabbitmq;

import java.io.IOException;

import org.apache.commons.lang.SerializationUtils;

import com.finix.bean.TheaterOwnerBean;
import com.rabbitmq.client.MessageProperties;

public class Ticket_Producer extends Ticket_MessageQueueEndPoint {
	

	public Ticket_Producer(String qUEUE_NAME) {
		super(qUEUE_NAME);
	}
	
	  String exchangeName = "checking_ticket_myExchange";
	    String routingKey = "checking_ticket_testRoute";
	    
	public void publishMessage(TheaterOwnerBean theaterOwnerBean) {
		 try {
			  System.out.println(theaterOwnerBean.getBooking_seat());
	            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, SerializationUtils.serialize(theaterOwnerBean));
	            
	        } catch (IOException e) {
	           e.printStackTrace();
	        }
		
	}

}
