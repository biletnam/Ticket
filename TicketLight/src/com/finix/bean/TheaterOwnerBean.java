package com.finix.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Blob;

public class TheaterOwnerBean implements Serializable
{
	
	private String theater_owner_first_name;
	private String theater_owner_last_name;
	private String theater_owner_email;
	private String theater_owner_mobile;
	private String theater_owner_address;
	private String passowrd;
	private int theater_owner_id;
	private String mail_pin;
	private String sms_pin;
	private String mail_pin_status;
	private String sms_pin_status;
	private String status;
	private String unique_id;
	
	private int state_id;
	private int city_id;
	private String state_name;
	private String city_name;
	private String old_password;
	private String password;
	private File profile_photo;
	
	private Map<Integer, String> cityMap;
	private int screen_id;
	
	private Map<Integer, String> theaterMap;
	private Map<Integer, String> screenMap;
	private Map<Integer, String> ticketCategoryMap;
	private Map<Integer, String> seatCategoryMap;
	private String theater_name;
	private int category_from_id;
	private int category_to_id;
	private int order_id;
	private int passage_count;
	
	private ArrayList<TheaterDetailBean> theaterRowList;
	private ArrayList<TheaterDetailBean> seatCountList;
	private ArrayList<ScreenDetailBean> seatCategoryList;
	
	private String seatCountDetail;
	private String seatCategoryDetail;
	
	private int district_id;
	private int theater_count;
	private int theater_id;
	private String theater_status;

	private ArrayList<TheaterDetailBean> theaterDetailList;
	
	private int theatre_employee_id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String employee_id;
	private int gender_id;
	private String gender;
	private String date_of_birth;
	private String phone_number;
	private String email_id;
	private String employee_role;
	private File profile_image;
	private String uniqueId;
	private int theatre_employee_role_id;
	private java.sql.Blob profile;
	
	private int movie_details_id;
	private String movie_name;
	private int movie_language_id;
	private String movie_languge_name;
	private int movie_genre_id;
	private String movie_genre_name;
	private int movie_format_id;
	private String movie_format_name;
	private String movie_duration;
	private File movie_poster;
	private Blob movie_image;
	private String movie_release_date;
	private String district_name;
	private String navBarScreenStatus;
    private String show_date;
	private String movie_id;
	private ArrayList<String> showTimingList;
	private int show_detail_id;
    private String date;
	private int show_timing_id;
    private String show_time_status;
    private int status_id;
    private String nav_bar_movie_status;
    private String nav_bar_user_status;
	private String show_time;
    private int master_movie_id;
    private int movie_certification_id;
    private String movie_certification_name;
	private ArrayList<String> genre_list;
	private ArrayList<String> language_list;
	private ArrayList<TicketCounterBean> date_list;
	private ArrayList<TheaterDetailBean> movieList;

		private String screen_name;
	private String show_date_id;
	private String booking_seat;
	private String booking_date;
	private String theater_address;
    private int ticket_counter_person_id;
	private String user_role;
	private String total_price;
	private ArrayList<Integer> movie_detail_id_list;
    private int date_id;
    private int tab_id;
    private int location_id;
    private int selected_seat_count;
    
    private int report_theatre_wise_tax_id;
	private String date_tax;
	private int date_key;
	private int total_amount;
	private int cgst_tax;
	private int sgst_tax;
	private int et_tax;
	private int total_payable_amount;
	private int theatre_id;
	private String theatre_name;
	private int subId;
	private int screen_count;
	private int ticket_amount;
	private int seat_count;
	private ArrayList<TheaterDetailBean>showwiseList;

	//Getters and setters
	
	public String getTheater_owner_first_name() {
		return theater_owner_first_name;
	}
	public void setTheater_owner_first_name(String theater_owner_first_name) {
		this.theater_owner_first_name = theater_owner_first_name;
	}
	public String getTheater_owner_last_name() {
		return theater_owner_last_name;
	}
	public void setTheater_owner_last_name(String theater_owner_last_name) {
		this.theater_owner_last_name = theater_owner_last_name;
	}
	public String getTheater_owner_email() {
		return theater_owner_email;
	}
	public void setTheater_owner_email(String theater_owner_email) {
		this.theater_owner_email = theater_owner_email;
	}
	public String getTheater_owner_mobile() {
		return theater_owner_mobile;
	}
	public void setTheater_owner_mobile(String theater_owner_mobile) {
		this.theater_owner_mobile = theater_owner_mobile;
	}
	public String getTheater_owner_address() {
		return theater_owner_address;
	}
	public void setTheater_owner_address(String theater_owner_address) {
		this.theater_owner_address = theater_owner_address;
	}
	public String getPassowrd() {
		return passowrd;
	}
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	public int getTheater_owner_id() {
		return theater_owner_id;
	}
	public void setTheater_owner_id(int theater_owner_id) {
		this.theater_owner_id = theater_owner_id;
	}
	public String getMail_pin() {
		return mail_pin;
	}
	public void setMail_pin(String mail_pin) {
		this.mail_pin = mail_pin;
	}
	public String getSms_pin() {
		return sms_pin;
	}
	public void setSms_pin(String sms_pin) {
		this.sms_pin = sms_pin;
	}
	public String getMail_pin_status() {
		return mail_pin_status;
	}
	public void setMail_pin_status(String mail_pin_status) {
		this.mail_pin_status = mail_pin_status;
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
	public String getUnique_id() {
		return unique_id;
	}
	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Map<Integer, String> getCityMap() {
		return cityMap;
	}
	public void setCityMap(Map<Integer, String> cityMap) {
		this.cityMap = cityMap;
	}
	public File getProfile_photo() {
		return profile_photo;
	}
	public void setProfile_photo(File profile_photo) {
		this.profile_photo = profile_photo;
	}
	public int getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}
	public Map<Integer, String> getTheaterMap() {
		return theaterMap;
	}
	public void setTheaterMap(Map<Integer, String> theaterMap) {
		this.theaterMap = theaterMap;
	}
	public Map<Integer, String> getScreenMap() {
		return screenMap;
	}
	public void setScreenMap(Map<Integer, String> screenMap) {
		this.screenMap = screenMap;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public Map<Integer, String> getTicketCategoryMap() {
		return ticketCategoryMap;
	}
	public void setTicketCategoryMap(Map<Integer, String> ticketCategoryMap) {
		this.ticketCategoryMap = ticketCategoryMap;
	}
	public int getCategory_from_id() {
		return category_from_id;
	}
	public void setCategory_from_id(int category_from_id) {
		this.category_from_id = category_from_id;
	}
	public int getCategory_to_id() {
		return category_to_id;
	}
	public void setCategory_to_id(int category_to_id) {
		this.category_to_id = category_to_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getPassage_count() {
		return passage_count;
	}
	public void setPassage_count(int passage_count) {
		this.passage_count = passage_count;
	}
	public ArrayList<TheaterDetailBean> getTheaterRowList() {
		return theaterRowList;
	}
	public void setTheaterRowList(ArrayList<TheaterDetailBean> theaterRowList) {
		this.theaterRowList = theaterRowList;
	}
	public Map<Integer, String> getSeatCategoryMap() {
		return seatCategoryMap;
	}
	public void setSeatCategoryMap(Map<Integer, String> seatCategoryMap) {
		this.seatCategoryMap = seatCategoryMap;
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
	public String getSeatCountDetail() {
		return seatCountDetail;
	}
	public void setSeatCountDetail(String seatCountDetail) {
		this.seatCountDetail = seatCountDetail;
	}
	public String getSeatCategoryDetail() {
		return seatCategoryDetail;
	}
	public void setSeatCategoryDetail(String seatCategoryDetail) {
		this.seatCategoryDetail = seatCategoryDetail;
	}
	
	public ArrayList<TheaterDetailBean> getTheaterDetailList() {
		return theaterDetailList;
	}
	public void setTheaterDetailList(ArrayList<TheaterDetailBean> theaterDetailList) {
		this.theaterDetailList = theaterDetailList;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public int getTheater_count() {
		return theater_count;
	}
	public void setTheater_count(int theater_count) {
		this.theater_count = theater_count;
	}
	public int getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(int theater_id) {
		this.theater_id = theater_id;
	}
	public String getTheater_status() {
		return theater_status;
	}
	public void setTheater_status(String theater_status) {
		this.theater_status = theater_status;
	}

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public int getGender_id() {
		return gender_id;
	}
	public void setGender_id(int gender_id) {
		this.gender_id = gender_id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getEmployee_role() {
		return employee_role;
	}
	public void setEmployee_role(String employee_role) {
		this.employee_role = employee_role;
	}
	public int getTheatre_employee_id() {
		return theatre_employee_id;
	}
	public void setTheatre_employee_id(int theatre_employee_id) {
		this.theatre_employee_id = theatre_employee_id;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public int getTheatre_employee_role_id() {
		return theatre_employee_role_id;
	}
	public void setTheatre_employee_role_id(int theatre_employee_role_id) {
		this.theatre_employee_role_id = theatre_employee_role_id;
	}
	public File getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(File profile_image) {
		this.profile_image = profile_image;
	}
	public java.sql.Blob getProfile() {
		return profile;
	}
	public void setProfile(java.sql.Blob blob) {
		this.profile = blob;
	}
	public int getMovie_details_id() {
		return movie_details_id;
	}
	public void setMovie_details_id(int movie_details_id) {
		this.movie_details_id = movie_details_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public int getMovie_language_id() {
		return movie_language_id;
	}
	public void setMovie_language_id(int movie_language_id) {
		this.movie_language_id = movie_language_id;
	}
	public String getMovie_languge_name() {
		return movie_languge_name;
	}
	public void setMovie_languge_name(String movie_languge_name) {
		this.movie_languge_name = movie_languge_name;
	}
	public int getMovie_genre_id() {
		return movie_genre_id;
	}
	public void setMovie_genre_id(int movie_genre_id) {
		this.movie_genre_id = movie_genre_id;
	}
	public String getMovie_genre_name() {
		return movie_genre_name;
	}
	public void setMovie_genre_name(String movie_genre_name) {
		this.movie_genre_name = movie_genre_name;
	}
	public int getMovie_format_id() {
		return movie_format_id;
	}
	public void setMovie_format_id(int movie_format_id) {
		this.movie_format_id = movie_format_id;
	}
	public String getMovie_format_name() {
		return movie_format_name;
	}
	public void setMovie_format_name(String movie_format_name) {
		this.movie_format_name = movie_format_name;
	}
	public String getMovie_duration() {
		return movie_duration;
	}
	public void setMovie_duration(String movie_duration) {
		this.movie_duration = movie_duration;
	}
	public File getMovie_poster() {
		return movie_poster;
	}
	public void setMovie_poster(File movie_poster) {
		this.movie_poster = movie_poster;
	}
	public Blob getMovie_image() {
		return movie_image;
	}
	public void setMovie_image(Blob movie_image) {
		this.movie_image = movie_image;
	}
	public String getMovie_release_date() {
		return movie_release_date;
	}
	public void setMovie_release_date(String movie_release_date) {
		this.movie_release_date = movie_release_date;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getNavBarScreenStatus() {
		return navBarScreenStatus;
	}
	public void setNavBarScreenStatus(String navBarScreenStatus) {
		this.navBarScreenStatus = navBarScreenStatus;
	}

	public String getShow_date() {
		return show_date;
	}
	public void setShow_date(String show_date) {
		this.show_date = show_date;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public ArrayList<String> getShowTimingList() {
		return showTimingList;
	}
	public void setShowTimingList(ArrayList<String> showTimingList) {
		this.showTimingList = showTimingList;
	}
	public int getShow_detail_id() {
		return show_detail_id;
	}
	public void setShow_detail_id(int show_detail_id) {
		this.show_detail_id = show_detail_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getShow_timing_id() {
		return show_timing_id;
	}
	public void setShow_timing_id(int show_timing_id) {
		this.show_timing_id = show_timing_id;
	}
	public String getShow_time_status() {
		return show_time_status;
	}
	public void setShow_time_status(String show_time_status) {
		this.show_time_status = show_time_status;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getNav_bar_movie_status() {
		return nav_bar_movie_status;
	}
	public void setNav_bar_movie_status(String nav_bar_movie_status) {
		this.nav_bar_movie_status = nav_bar_movie_status;
	}
	public String getNav_bar_user_status() {
		return nav_bar_user_status;
	}
	public void setNav_bar_user_status(String nav_bar_user_status) {
		this.nav_bar_user_status = nav_bar_user_status;
	}
    public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	public int getMaster_movie_id() {
		return master_movie_id;
	}
	public void setMaster_movie_id(int master_movie_id) {
		this.master_movie_id = master_movie_id;
	}
	public int getMovie_certification_id() {
		return movie_certification_id;
	}
	public void setMovie_certification_id(int movie_certification_id) {
		this.movie_certification_id = movie_certification_id;
	}
	public String getMovie_certification_name() {
		return movie_certification_name;
	}
	public void setMovie_certification_name(String movie_certification_name) {
		this.movie_certification_name = movie_certification_name;
	}
	
public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getShow_date_id() {
		return show_date_id;
	}
	public void setShow_date_id(String show_date_id) {
		this.show_date_id = show_date_id;
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
	public ArrayList<String> getGenre_list() {
		return genre_list;
	}
	public void setGenre_list(ArrayList<String> genre_list) {
		this.genre_list = genre_list;
	}
	public ArrayList<String> getLanguage_list() {
		return language_list;
	}
	public void setLanguage_list(ArrayList<String> language_list) {
		this.language_list = language_list;
	}
	public ArrayList<TicketCounterBean> getDate_list() {
		return date_list;
	}
	public void setDate_list(ArrayList<TicketCounterBean> date_list) {
		this.date_list = date_list;
	}
	public ArrayList<TheaterDetailBean> getMovieList() {
		return movieList;
	}
	public void setMovieList(ArrayList<TheaterDetailBean> movieList) {
		this.movieList = movieList;
	}
	public String getTheater_address() {
		return theater_address;
	}
	public void setTheater_address(String theater_address) {
		this.theater_address = theater_address;
	}
	
	public int getTicket_counter_person_id() {
		return ticket_counter_person_id;
	}
	public void setTicket_counter_person_id(int ticket_counter_person_id) {
		this.ticket_counter_person_id = ticket_counter_person_id;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public ArrayList<Integer> getMovie_detail_id_list() {
		return movie_detail_id_list;
	}
	public void setMovie_detail_id_list(ArrayList<Integer> movie_detail_id_list) {
		this.movie_detail_id_list = movie_detail_id_list;
	}
	public int getDate_id() {
		return date_id;
	}
	public void setDate_id(int date_id) {
		this.date_id = date_id;
	}
	public int getTab_id() {
		return tab_id;
	}
	public void setTab_id(int tab_id) {
		this.tab_id = tab_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getSelected_seat_count() {
		return selected_seat_count;
	}
	public void setSelected_seat_count(int selected_seat_count) {
		this.selected_seat_count = selected_seat_count;
	}
	public int getSgst_tax() {
		return sgst_tax;
	}
	public void setSgst_tax(int sgst_tax) {
		this.sgst_tax = sgst_tax;
	}
	public int getEt_tax() {
		return et_tax;
	}
	public void setEt_tax(int et_tax) {
		this.et_tax = et_tax;
	}
	public int getTotal_payable_amount() {
		return total_payable_amount;
	}
	public void setTotal_payable_amount(int total_payable_amount) {
		this.total_payable_amount = total_payable_amount;
	}
	public int getTheatre_id() {
		return theatre_id;
	}
	public void setTheatre_id(int theatre_id) {
		this.theatre_id = theatre_id;
	}
	public String getTheatre_name() {
		return theatre_name;
	}
	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public int getScreen_count() {
		return screen_count;
	}
	public void setScreen_count(int screen_count) {
		this.screen_count = screen_count;
	}
	public int getTicket_amount() {
		return ticket_amount;
	}
	public void setTicket_amount(int ticket_amount) {
		this.ticket_amount = ticket_amount;
	}
	public int getSeat_count() {
		return seat_count;
	}
	public void setSeat_count(int seat_count) {
		this.seat_count = seat_count;
	}
	public ArrayList<TheaterDetailBean> getShowwiseList() {
		return showwiseList;
	}
	public void setShowwiseList(ArrayList<TheaterDetailBean> showwiseList) {
		this.showwiseList = showwiseList;
	}
	public int getReport_theatre_wise_tax_id() {
		return report_theatre_wise_tax_id;
	}
	public void setReport_theatre_wise_tax_id(int report_theatre_wise_tax_id) {
		this.report_theatre_wise_tax_id = report_theatre_wise_tax_id;
	}
	public String getDate_tax() {
		return date_tax;
	}
	public void setDate_tax(String date_tax) {
		this.date_tax = date_tax;
	}
	public int getDate_key() {
		return date_key;
	}
	public void setDate_key(int date_key) {
		this.date_key = date_key;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public int getCgst_tax() {
		return cgst_tax;
	}
	public void setCgst_tax(int cgst_tax) {
		this.cgst_tax = cgst_tax;
	}

	
	

	

}
