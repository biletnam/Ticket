package com.finix.bean;

import java.util.ArrayList;
import java.io.Serializable;

public class TicketCounterBean implements Serializable {

	private int ticket_counter_person_id;
	private String ticket_counter_person_name;
	private String ticket_counter_person_mobile;
	private String ticket_counter_person_email;
	private String employee_id;
	private int employee_role_id;
	private int employee_working_theater_id;
	private int employee_working_theater_owner_id;
	private int show_timing_id;
	private String show_timing;
	private int seat_count;
	private String movie_date;
	private String seat_name_str;

	private String movie_name;
	private int total_amount;
	private int seat_status_id;
    private int date_id;
    private String date;
    private String dayName;
    private String status;
private int ticket_amount;
	private int theater_id;
	private String screen_name;
	ArrayList<TicketCounterBean>moviewiseList;
	ArrayList<TicketCounterBean>screenwiseList;
	ArrayList<TheaterDetailBean>showwiseList;
	private String show_date;
	private String show_date_id;
	private int screen_id;
	
	
	public int getTicket_counter_person_id() {
		return ticket_counter_person_id;
	}

	public void setTicket_counter_person_id(int ticket_counter_person_id) {
		this.ticket_counter_person_id = ticket_counter_person_id;
	}

	public String getTicket_counter_person_name() {
		return ticket_counter_person_name;
	}

	public void setTicket_counter_person_name(String ticket_counter_person_name) {
		this.ticket_counter_person_name = ticket_counter_person_name;
	}
	public int getEmployee_role_id() {
		return employee_role_id;
	}

	public void setEmployee_role_id(int employee_role_id) {
		this.employee_role_id = employee_role_id;
	}

	public int getEmployee_working_theater_id() {
		return employee_working_theater_id;
	}

	public void setEmployee_working_theater_id(int employee_working_theater_id) {
		this.employee_working_theater_id = employee_working_theater_id;
	}

	public int getEmployee_working_theater_owner_id() {
		return employee_working_theater_owner_id;
	}

	public void setEmployee_working_theater_owner_id(
			int employee_working_theater_owner_id) {
		this.employee_working_theater_owner_id = employee_working_theater_owner_id;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getTicket_counter_person_mobile() {
		return ticket_counter_person_mobile;
	}

	public void setTicket_counter_person_mobile(String ticket_counter_person_mobile) {
		this.ticket_counter_person_mobile = ticket_counter_person_mobile;
	}

	public String getTicket_counter_person_email() {
		return ticket_counter_person_email;
	}

	public void setTicket_counter_person_email(String ticket_counter_person_email) {
		this.ticket_counter_person_email = ticket_counter_person_email;
	}

	public int getSeat_count() {
		return seat_count;
	}

	public void setSeat_count(int seat_count) {
		this.seat_count = seat_count;
	}

	public String getMovie_date() {
		return movie_date;
	}

	public void setMovie_date(String movie_date) {
		this.movie_date = movie_date;
	}

	public String getSeat_name_str() {
		return seat_name_str;
	}

	public void setSeat_name_str(String seat_name_str) {
		this.seat_name_str = seat_name_str;
	}

	public String getShow_timing() {
		return show_timing;
	}

	public void setShow_timing(String show_timing) {
		this.show_timing = show_timing;
	}

	public int getShow_timing_id() {
		return show_timing_id;
	}

	public void setShow_timing_id(int show_timing_id) {
		this.show_timing_id = show_timing_id;
	}
	public String getMovie_name() {
		return movie_name;
	}

	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}

	public int getSeat_status_id() {
		return seat_status_id;
	}

	public void setSeat_status_id(int seat_status_id) {
		this.seat_status_id = seat_status_id;
	}

	public int getDate_id() {
		return date_id;
	}

	public void setDate_id(int date_id) {
		this.date_id = date_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<TicketCounterBean> getMoviewiseList() {
		return moviewiseList;
	}

	public void setMoviewiseList(ArrayList<TicketCounterBean> moviewiseList) {
		this.moviewiseList = moviewiseList;
	}

	public ArrayList<TicketCounterBean> getScreenwiseList() {
		return screenwiseList;
	}

	public void setScreenwiseList(ArrayList<TicketCounterBean> screenwiseList) {
		this.screenwiseList = screenwiseList;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public ArrayList<TheaterDetailBean> getShowwiseList() {
		return showwiseList;
	}

	public void setShowwiseList(ArrayList<TheaterDetailBean> showwiseList) {
		this.showwiseList = showwiseList;
	}

	public String getShow_date() {
		return show_date;
	}

	public void setShow_date(String show_date) {
		this.show_date = show_date;
	}

	public String getShow_date_id() {
		return show_date_id;
	}

	public void setShow_date_id(String show_date_id) {
		this.show_date_id = show_date_id;
	}

	public int getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}

	public int getTicket_amount() {
		return ticket_amount;
	}

	public void setTicket_amount(int ticket_amount) {
		this.ticket_amount = ticket_amount;
	}

	public int getTheater_id() {
		return theater_id;
	}

	public void setTheater_id(int theater_id) {
		this.theater_id = theater_id;
	}
	
	
	

}
