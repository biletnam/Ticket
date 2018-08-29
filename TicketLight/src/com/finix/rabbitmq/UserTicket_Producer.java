package com.finix.rabbitmq;

import java.io.IOException;

import org.apache.commons.lang.SerializationUtils;

import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.UserBean;
import com.rabbitmq.client.MessageProperties;

public class UserTicket_Producer extends  UserTicket_MessageQueueEndPoint{

	public UserTicket_Producer(String qUEUE_NAME) {
		super(qUEUE_NAME);
	}

	 String exchangeName = "checking_userticket_myExchange";
	    String routingKey = "checking_userticket_testRoute";
	    
	public void publishMessage(UserBean userBean) {
		 try {
			  System.out.println(userBean.getBooking_seat());
	            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, SerializationUtils.serialize(userBean));
	            
	        } catch (IOException e) {
	           e.printStackTrace();
	        }
		
	}
}
