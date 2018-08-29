package com.finix.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class UserTicket_MessageQueueEndPoint {

	Connection connection;
    Channel channel;
    String endPointName;
	
	public UserTicket_MessageQueueEndPoint(String qUEUE_NAME) {
		this.endPointName = qUEUE_NAME;
		
		 try {
        	 ConnectionFactory factory = new ConnectionFactory();
        	 factory.setUsername("test");
        	 factory.setPassword("test");
        	 factory.setVirtualHost("/");
        	 factory.setHost("192.168.0.112");
        	 factory.setPort(5672);
        
        	  String exchangeName = "checking_userticket_myExchange";
        	    String routingKey = "checking_userticket_testRoute";
        	    
            //getting a connection
            connection = factory.newConnection();
     
            //creating a channel
            channel = connection.createChannel();
      
            boolean durable = true;
            channel.exchangeDeclare(exchangeName, "direct", durable);
            channel.queueDeclare(qUEUE_NAME, durable,false,false,null);
            channel.queueBind(qUEUE_NAME, exchangeName, routingKey);
           
            
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
 
 
   
     public void close() throws IOException{
         this.connection.close(); //closing connection, closes all the open channels
     }
		
		
	

}
