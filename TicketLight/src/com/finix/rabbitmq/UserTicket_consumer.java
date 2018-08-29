package com.finix.rabbitmq;

import org.apache.commons.lang.SerializationUtils;

import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.UserBean;
import com.finix.ticket.TicketBooking;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class UserTicket_consumer implements Runnable {

	String qu_name = "UserTicket_QUEUE";
	
	UserBean userBean = new UserBean();
	
	public void run() {
		try
		{
		ConnectionFactory factory = new ConnectionFactory();
		 factory.setUsername("test");
		 factory.setPassword("test");
		 factory.setVirtualHost("/");
		 factory.setHost("192.168.0.112");
		 factory.setPort(5672);
		 Connection conn = factory.newConnection();
		      Channel channel = conn.createChannel();
		      String exchangeName = "checking_ticket_myExchange";
		      String routingKey = "checking_ticket_testRoute";
		      boolean durable = true;
		      channel.exchangeDeclare(exchangeName, "direct", durable);
		      channel.queueDeclare(qu_name, durable,false,false,null);
		      channel.queueBind(qu_name, exchangeName, routingKey);
		      boolean noAck = false;
		      QueueingConsumer consumer = new QueueingConsumer(channel);
		      channel.basicConsume(qu_name, noAck, consumer);
		      boolean runInfinite = true;
		      while (runInfinite) {
		            QueueingConsumer.Delivery delivery;
		            try {
		               delivery = consumer.nextDelivery();
		               
		            } catch (InterruptedException ie) {
		               continue;
		            }
		         
		            
		          
		            userBean = (UserBean) SerializationUtils.deserialize(delivery.getBody());
		      
			        System.out.println(userBean.getBooking_seat());
			        insertUserTicket(userBean);
			        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		      }
		   
		      channel.close();
		      conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private String insertUserTicket(UserBean userBean) throws Exception{
		  System.out.println(userBean.getBooking_seat());
		TicketBooking.insertUserTicketDetails(userBean);
		
		
		return "success";
	}
	
	

}
