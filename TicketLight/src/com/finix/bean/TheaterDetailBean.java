package com.finix.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;


public class TheaterDetailBean {

	private int theater_id;
	private String theater_name;
	private int city_id;
	private String city_name;
	private int district_id;
	private String district_name;
	private String address;
	private String theater_license_number;
	private String theater_tin_number;
	private String theater_gst_number;
	private int number_of_seats;
	private int screen_id;
	private String screen_name;
	private int screen_count;
	private File screen_layout;
	private ArrayList<ScreenDetailBean> screenList;

	private String category_name;
	private int col_count;
	
	private int category_id;
	private int seat_category_id;
	private int seat_count;
	private int seat_order_id;
	private int col_id;
	
	private int total_seat_count;
	
	private Map<Integer, String> screenMap;
	private Map<Integer, byte[]> theater_screen_layout;
	private String screen_layout_status;
    private String status;
    private String screen_status;
    private int theater_owner_id;
    private String movie_id;
    private int movie_detail_id;
    private String movie_name;
    private ArrayList<ScreenDetailBean> show_list;
    private ArrayList<String> language_list;
    private int master_movie_id;
    private String movie_certification_name;
    private String movie_language;
    private String movie_format;
    private String movie_genre;
    ArrayList<TheaterDetailBean>showwiseList;
    private int ticket_amount;
    private String show_timing;
    private int scrn_id;
	
	//getter and setters

	public int getTheater_id() {
		return theater_id;
	}
	public void setTheater_id(int theater_id) {
		this.theater_id = theater_id;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTheater_license_number() {
		return theater_license_number;
	}
	public void setTheater_license_number(String theater_license_number) {
		this.theater_license_number = theater_license_number;
	}
	public String getTheater_tin_number() {
		return theater_tin_number;
	}
	public void setTheater_tin_number(String theater_tin_number) {
		this.theater_tin_number = theater_tin_number;
	}
	public String getTheater_gst_number() {
		return theater_gst_number;
	}
	public void setTheater_gst_number(String theater_gst_number) {
		this.theater_gst_number = theater_gst_number;
	}
	public int getNumber_of_seats() {
		return number_of_seats;
	}
	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}
	public int getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public int getScreen_count() {
		return screen_count;
	}
	public void setScreen_count(int screen_count) {
		this.screen_count = screen_count;
	}
	public File getScreen_layout() {
		return screen_layout;
	}
	public void setScreen_layout(File screen_layout) {
		this.screen_layout = screen_layout;
	}
	public ArrayList<ScreenDetailBean> getScreenList() {
		return screenList;
	}
	public void setScreenList(ArrayList<ScreenDetailBean> screenList) {
		this.screenList = screenList;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getCol_count() {
		return col_count;
	}
	public void setCol_count(int col_count) {
		this.col_count = col_count;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getSeat_category_id() {
		return seat_category_id;
	}
	public void setSeat_category_id(int seat_category_id) {
		this.seat_category_id = seat_category_id;
	}
	public int getSeat_order_id() {
		return seat_order_id;
	}
	public void setSeat_order_id(int seat_order_id) {
		this.seat_order_id = seat_order_id;
	}
	public int getCol_id() {
		return col_id;
	}
	public void setCol_id(int col_id) {
		this.col_id = col_id;
	}
	public int getTotal_seat_count() {
		return total_seat_count;
	}
	public void setTotal_seat_count(int total_seat_count) {
		this.total_seat_count = total_seat_count;
	}
public Map<Integer, String> getScreenMap() {
		return screenMap;
	}
	public void setScreenMap(Map<Integer, String> screenMap) {
		this.screenMap = screenMap;
	}
	public Map<Integer, byte[]> getTheater_screen_layout() {
		return theater_screen_layout;
	}
	public void setTheater_screen_layout(Map<Integer, byte[]> theater_screen_layout) {
		this.theater_screen_layout = theater_screen_layout;
	}
	public int getSeat_count() {
		return seat_count;
	}
	public void setSeat_count(int seat_count) {
		this.seat_count = seat_count;
	}
	public String getScreen_layout_status() {
		return screen_layout_status;
	}
	public void setScreen_layout_status(String screen_layout_status) {
		this.screen_layout_status = screen_layout_status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScreen_status() {
		return screen_status;
	}
	public void setScreen_status(String screen_status) {
		this.screen_status = screen_status;
	}
	public int getTheater_owner_id() {
		return theater_owner_id;
	}
	public void setTheater_owner_id(int theater_owner_id) {
		this.theater_owner_id = theater_owner_id;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public int getMovie_detail_id() {
		return movie_detail_id;
	}
	public void setMovie_detail_id(int movie_detail_id) {
		this.movie_detail_id = movie_detail_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public ArrayList<ScreenDetailBean> getShow_list() {
		return show_list;
	}
	public void setShow_list(ArrayList<ScreenDetailBean> show_list) {
		this.show_list = show_list;
	}
	public ArrayList<String> getLanguage_list() {
		return language_list;
	}
	public void setLanguage_list(ArrayList<String> language_list) {
		this.language_list = language_list;
	}
	public int getMaster_movie_id() {
		return master_movie_id;
	}
	public void setMaster_movie_id(int master_movie_id) {
		this.master_movie_id = master_movie_id;
	}
	public String getMovie_certification_name() {
		return movie_certification_name;
	}
	public void setMovie_certification_name(String movie_certification_name) {
		this.movie_certification_name = movie_certification_name;
	}
	public String getMovie_language() {
		return movie_language;
	}
	public void setMovie_language(String movie_language) {
		this.movie_language = movie_language;
	}
	public String getMovie_format() {
		return movie_format;
	}
	public void setMovie_format(String movie_format) {
		this.movie_format = movie_format;
	}
	public String getMovie_genre() {
		return movie_genre;
	}
	public void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
	}
	public int getScrn_id() {
		return scrn_id;
	}
	public void setScrn_id(int scrn_id) {
		this.scrn_id = scrn_id;
	}
	
	public String getShow_timing() {
		return show_timing;
	}
	public void setShow_timing(String show_timing) {
		this.show_timing = show_timing;
	}
	public int getTicket_amount() {
		return ticket_amount;
	}
	public void setTicket_amount(int ticket_amount) {
		this.ticket_amount = ticket_amount;
	}

	public ArrayList<TheaterDetailBean> getShowwiseList() {
		return showwiseList;
	}
	public void setShowwiseList(ArrayList<TheaterDetailBean> showwiseList) {
		this.showwiseList = showwiseList;
	}
	
	
	

}
