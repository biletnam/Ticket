package com.finix.ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.UserBean;
import com.finix.dao.utils.ConnectionManager;

public class TicketBooking  {
public static	 ConnectionManager connectionManager = ConnectionManager
			.getConnectionManager();
	

	public static String insertTicketDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
		PreparedStatement ps=null;
		ResultSet rs=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;
		PreparedStatement ps2=null;
		ResultSet rs2=null;
		PreparedStatement ps3=null;
		ResultSet rs3=null;
		String seatStr = "";
		String bookingSeatStr = "";
		Connection conn=null;
		
		try {
			conn = connectionManager.getConnection();
		String insert_ticket="insert into individual_movie_ticket_sold_details(theatre_id,screen_id,show_timing_id,movie_detail_id,show_date_id,user_id,user_role_id,seat_id,ticket_amount,is_deleted,status,create_date) values(?,?,?,?,?,?,?,?,?,?,?,now())";
		ps3= conn.prepareStatement(insert_ticket);
		ps3.setInt(1, theaterOwnerBean.getTheater_id());
		ps3.setInt(2, theaterOwnerBean.getScreen_id());
		ps3.setInt(3, theaterOwnerBean.getShow_timing_id());
		ps3.setInt(4, theaterOwnerBean.getMovie_details_id());
		ps3.setString(5, theaterOwnerBean.getBooking_date());
		ps3.setInt(6, theaterOwnerBean.getTicket_counter_person_id());
		ps3.setString(7, theaterOwnerBean.getUser_role());
		ps3.setString(8, theaterOwnerBean.getBooking_seat());
		ps3.setString(9, theaterOwnerBean.getTotal_price());
		ps3.setString(10,"N");
		ps3.setString(11,"Active");
		ps3.executeUpdate();
		
		
		String sql="SELECT seat_id FROM  movie_ticket_sold_details WHERE theater_id=? AND screen_id=? AND show_timing_id=? AND movie_detail_id=? ";
		ps1 = conn.prepareStatement(sql);
		ps1.setInt(1, theaterOwnerBean.getTheater_id());
		ps1.setInt(2, theaterOwnerBean.getScreen_id());
		ps1.setInt(3, theaterOwnerBean.getShow_timing_id());
		ps1.setInt(4, theaterOwnerBean.getMovie_details_id());
		rs1 = ps1.executeQuery();
		
		if(rs1.next()) {
			
			seatStr=rs1.getString("seat_id");
			bookingSeatStr=seatStr+",";
			bookingSeatStr=bookingSeatStr.concat(theaterOwnerBean.getBooking_seat());
					
			
		String update_ticket="UPDATE movie_ticket_sold_details SET seat_id=?,show_date_id=? WHERE theater_id=? AND screen_id=? AND show_timing_id=? AND movie_detail_id=?";
		ps2 = conn.prepareStatement(update_ticket);
		ps2.setString(1,bookingSeatStr);
		ps2.setString(2, theaterOwnerBean.getBooking_date() );
		ps2.setInt(3, theaterOwnerBean.getTheater_id());
		ps2.setInt(4, theaterOwnerBean.getScreen_id());
		ps2.setInt(5, theaterOwnerBean.getShow_timing_id());
		ps2.setInt(6, theaterOwnerBean.getMovie_details_id());
		ps2.executeUpdate();
		}
		
		else {
		String ticket_details="insert into movie_ticket_sold_details(theater_id,screen_id,show_timing_id,show_date_id,movie_detail_id,show_detail_id,seat_id,is_deleted,status,created_on) values(?,?,?,?,?,?,?,?,?,now())";
		ps = conn.prepareStatement(ticket_details);
		ps.setInt(1, theaterOwnerBean.getTheater_id());
		ps.setInt(2, theaterOwnerBean.getScreen_id());
		ps.setInt(3, theaterOwnerBean.getShow_timing_id());
		ps.setString(4, theaterOwnerBean.getBooking_date());
		ps.setInt(5, theaterOwnerBean.getMovie_details_id());
		ps.setInt(6, theaterOwnerBean.getShow_detail_id());
		ps.setString(7, theaterOwnerBean.getBooking_seat());
		ps.setString(8,"N");
		ps.setString(9,"Active");
		ps.executeUpdate();
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return "success";
		
	
		
	}


	public static String insertUserTicketDetails(UserBean userBean)throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		PreparedStatement ps1=null;
		ResultSet rs1=null;
		PreparedStatement ps2=null;
		ResultSet rs2=null;
		PreparedStatement ps3=null;
		ResultSet rs3=null;
		String seatStr = "";
		String bookingSeatStr = "";
		
		try {
			
conn = connectionManager.getConnection();
			
			
			String insert_ticket="insert into individual_movie_ticket_sold_details(theatre_id,screen_id,show_timing_id,movie_detail_id,show_date_id,user_id,user_role_id,seat_id,ticket_amount,is_deleted,status,create_date) values(?,?,?,?,?,?,?,?,?,?,?,now())";
			ps3= conn.prepareStatement(insert_ticket);
			ps3.setInt(1, userBean.getTheater_id());
			ps3.setInt(2, userBean.getScreen_id());
			ps3.setInt(3, userBean.getShow_timing_id());
			ps3.setInt(4, userBean.getMovie_details_id());
			ps3.setString(5, userBean.getBooking_date());
			ps3.setInt(6, userBean.getUser_id());
			ps3.setString(7, "End user");
			ps3.setString(8, userBean.getBooking_seat());
			ps3.setString(9, userBean.getTotal_price());
			ps3.setString(10,"N");
			ps3.setString(11,"Active");
			ps3.executeUpdate();
			
			
			
			String sql="SELECT seat_id FROM  movie_ticket_sold_details WHERE theater_id=? AND screen_id=? AND show_timing_id=? AND movie_detail_id=? ";
			ps1 = conn.prepareStatement(sql);
			ps1.setInt(1, userBean.getTheater_id());
			ps1.setInt(2, userBean.getScreen_id());
			ps1.setInt(3, userBean.getShow_timing_id());
			ps1.setInt(4, userBean.getMovie_details_id());
			rs1 = ps1.executeQuery();
			
			if(rs1.next()) {
				
				seatStr=rs1.getString("seat_id");
				bookingSeatStr=seatStr+",";
				bookingSeatStr=bookingSeatStr.concat(userBean.getBooking_seat());
						
				
			String update_ticket="UPDATE movie_ticket_sold_details SET seat_id=?,show_date_id=? WHERE theater_id=? AND screen_id=? AND show_timing_id=? AND movie_detail_id=?";
			ps2 = conn.prepareStatement(update_ticket);
			ps2.setString(1,bookingSeatStr);
			ps2.setString(2, userBean.getBooking_date() );
			ps2.setInt(3, userBean.getTheater_id());
			ps2.setInt(4, userBean.getScreen_id());
			ps2.setInt(5, userBean.getShow_timing_id());
			ps2.setInt(6, userBean.getMovie_details_id());
			ps2.executeUpdate();
			}
			
			else {
			String ticket_details="insert into movie_ticket_sold_details(theater_id,screen_id,show_timing_id,show_date_id,movie_detail_id,show_detail_id,seat_id,is_deleted,status,created_on) values(?,?,?,?,?,?,?,?,?,now())";
			ps = conn.prepareStatement(ticket_details);
			ps.setInt(1, userBean.getTheater_id());
			ps.setInt(2, userBean.getScreen_id());
			ps.setInt(3, userBean.getShow_timing_id());
			ps.setString(4, userBean.getBooking_date());
			ps.setInt(5, userBean.getMovie_details_id());
			ps.setInt(6, userBean.getShow_detail_id());
			ps.setString(7, userBean.getBooking_seat());
			ps.setString(8,"N");
			ps.setString(9,"Active");
			ps.executeUpdate();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

}
