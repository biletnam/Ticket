package com.finix.utils;

import java.util.Random;

public class UniqueId {

		/**
		* Client Ack Id - 4 digits incremental number
		*/
		public static int ackIdIncrement = 1000;
		/**
		* Client Ack Id - 4 digits decremental number.
		*/
		public static int ackIdDecrement = 999;

		    public static synchronized String getAckId(String id) {
		        
		StringBuffer s_buffer = new StringBuffer();	
		s_buffer.append(id);	
		s_buffer.append(System.currentTimeMillis());	
		if (ackIdIncrement > 9000) ackIdIncrement = 1000;
		if (ackIdDecrement < 150) ackIdDecrement = 999;
		s_buffer.append(++ackIdIncrement);
		s_buffer.append(--ackIdDecrement);
		return s_buffer.toString();
		        
		    }
		    
		    
		    public static int gen() 
		    {
		        Random r = new Random( System.currentTimeMillis() );
		        return (1 + r.nextInt(2)) * 1000 + r.nextInt(1000);
		    }


			public static String getOrderRandNum(String string) 
			{
				
				char[] chars = "ABCOOO22DEFGHKLMNOPQRSTX000123456789".toCharArray();
				Random rnd = new Random();
				StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)));
				for (int i = 0; i < 10; i++)
				{
				    sb.append(chars[rnd.nextInt(chars.length)]);
				}
				sb.append(string);
				return sb.toString(); 
			}


			public static String getTarckingId() 
			{
		    	Random random = new Random();
		        StringBuilder sb = new StringBuilder();

		        // first not 0 digit
		        sb.append(random.nextInt(9) + 1);

		        // rest of 11 digits
		        for (int i = 0; i < 9; i++) {
		            sb.append(random.nextInt(10));
		        }
		        return sb.toString();
				    
			}
		    
		   
		}
