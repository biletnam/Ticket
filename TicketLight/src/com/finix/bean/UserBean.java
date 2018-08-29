package com.finix.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class UserBean  implements Serializable
{
	private String name;
	private String email;
	private String mobile;
	private String password;
	private int user_id;
	private String sms_pin;
	private String sms_pin_status;
	private String status;
	private Map<Integer, String> cityMap;
	private Map<Integer, String> theaterMap;
	private Map<Integer, String> movieMap;
	private int city_id;
    private ArrayList<TheaterOwnerBean> movieList;
    private String show_values;
    
    private int theater_id;
    private int screen_id;
    
    private int movie_details_id;
    private int show_detail_id;
    
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
    
    private String booking_seat;
    private String booking_date;
    private String total_price;
    private ArrayList<TheaterOwnerBean> theaterList;
    private ArrayList<TheaterOwnerBean> cityList;
    private String screen_name;
    private String show_time_str;
	private ArrayList<TheaterDetailBean> theaterRowList;
	private ArrayList<TheaterDetailBean> seatCountList;
	private ArrayList<ScreenDetailBean> seatCategoryList;
	private String theatre_name;
	private int selected_seat_count;

	
	//Getter and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSms_pin() {
		return sms_pin;
	}
	public void setSms_pin(String sms_pin) {
		this.sms_pin = sms_pin;
	}
	public String getSms_pin_status() {
		return sms_pin_status;
	}
	public void setSms_pin_status(String sms_pin_status) {
		this.sms_pin_status = sms_pin_status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<Integer, String> getCityMap() {
		return cityMap;
	}
	public void setCityMap(Map<Integer, String> cityMap) {
		this.cityMap = cityMap;
	}
	public Map<Integer, String> getTheaterMap() {
		return theaterMap;
	}
	public void setTheaterMap(Map<Integer, String> theaterMap) {
		this.theaterMap = theaterMap;
	}
	public Map<Integer, String> getMovieMap() {
		return movieMap;
	}
	public void setMovieMap(Map<Integer, String> movieMap) {
		this.movieMap = movieMap;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public ArrayList<TheaterOwnerBean> getMovieList() {
		return movieList;
	}
	public void setMovieList(ArrayList<TheaterOwnerBean> movieList) {
		this.movieList = movieList;
	}
	public String getShow_values() {
		return show_values;
	}
	public void setShow_values(String show_values) {
		this.show_values = show_values;
	}
	public int getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(int theater_id) {
		this.theater_id = theater_id;
	}
	public int getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}
	public int getShow_timing_id() {
		return show_timing_id;
	}
	public void setShow_timing_id(int show_timing_id) {
		this.show_timing_id = show_timing_id;
	}
	public int getMovie_details_id() {
		return movie_details_id;
	}
	public void setMovie_details_id(int movie_details_id) {
		this.movie_details_id = movie_details_id;
	}
	public int getShow_detail_id() {
		return show_detail_id;
	}
	public void setShow_detail_id(int show_detail_id) {
		this.show_detail_id = show_detail_id;
	}
	public ArrayList<TheaterDetailBean> getTheaterRowList() {
		return theaterRowList;
	}
	public void setTheaterRowList(ArrayList<TheaterDetailBean> theaterRowList) {
		this.theaterRowList = theaterRowList;
	}
	public ArrayList<TheaterDetailBean> getSeatCountList() {
		return seatCountList;
	}
	public void setSeatCountList(ArrayList<TheaterDetailBean> seatCountList) {
		this.seatCountList = seatCountList;
	}
	public ArrayList<ScreenDetailBean> getSeatCategoryList() {
		return seatCategoryList;
	}
	public void setSeatCategoryList(ArrayList<ScreenDetailBean> seatCategoryList) {
		this.seatCategoryList = seatCategoryList;
	}
	public String getShow_timing() {
		return show_timing;
	}
	public void setShow_timing(String show_timing) {
		this.show_timing = show_timing;
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
	public String getTheatre_name() {
		return theatre_name;
	}
	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}
	public ArrayList<TheaterOwnerBean> getTheaterList() {
		return theaterList;
	}
	public void setTheaterList(ArrayList<TheaterOwnerBean> theaterList) {
		this.theaterList = theaterList;
	}
	public ArrayList<TheaterOwnerBean> getCityList() {
		return cityList;
	}
	public void setCityList(ArrayList<TheaterOwnerBean> cityList) {
		this.cityList = cityList;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getShow_time_str() {
		return show_time_str;
	}
	public void setShow_time_str(String show_time_str) {
		this.show_time_str = show_time_str;
	}
	
	public String getBooking_seat() {
		return booking_seat;
	}
	public void setBooking_seat(String booking_seat) {
		this.booking_seat = booking_seat;
	}
	public String getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(String booking_date) {
		this.booking_date = booking_date;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public int getSelected_seat_count() {
		return selected_seat_count;
	}
	public void setSelected_seat_count(int selected_seat_count) {
		this.selected_seat_count = selected_seat_count;
	}
	
	

}
