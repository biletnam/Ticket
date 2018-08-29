package com.finix.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class ScreenDetailBean {

	private int screen_id;
	private String screen_name;
	private String screen_layout_status;
	private Map<Integer, byte[]> theater_screen_layout;
	private int seat_count;
	private File screen_layout;
	private int order_id;
	private int show_id;
	private String show_timing;
	private int show_detail_id;
    private ArrayList<TicketCounterBean> showTimingList;
    private ArrayList<TicketCounterBean> seatList;
    private int movie_detail_id;
	
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
	public String getScreen_layout_status() {
		return screen_layout_status;
	}
	public void setScreen_layout_status(String screen_layout_status) {
		this.screen_layout_status = screen_layout_status;
	}
	public int getSeat_count() {
		return seat_count;
	}
	public void setSeat_count(int seat_count) {
		this.seat_count = seat_count;
	}
	public Map<Integer, byte[]> getTheater_screen_layout() {
		return theater_screen_layout;
	}
	public void setTheater_screen_layout(Map<Integer, byte[]> theater_screen_layout) {
		this.theater_screen_layout = theater_screen_layout;
	}
	public File getScreen_layout() {
		return screen_layout;
	}
	public void setScreen_layout(File screen_layout) {
		this.screen_layout = screen_layout;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getShow_id() {
		return show_id;
	}
	public void setShow_id(int show_id) {
		this.show_id = show_id;
	}
	public String getShow_timing() {
		return show_timing;
	}
	public void setShow_timing(String show_timing) {
		this.show_timing = show_timing;
	}
	public int getShow_detail_id() {
		return show_detail_id;
	}
	public void setShow_detail_id(int show_detail_id) {
		this.show_detail_id = show_detail_id;
	}
	public ArrayList<TicketCounterBean> getShowTimingList() {
		return showTimingList;
	}
	public void setShowTimingList(ArrayList<TicketCounterBean> showTimingList) {
		this.showTimingList = showTimingList;
	}
	public ArrayList<TicketCounterBean> getSeatList() {
		return seatList;
	}
	public void setSeatList(ArrayList<TicketCounterBean> seatList) {
		this.seatList = seatList;
	}
	public int getMovie_detail_id() {
		return movie_detail_id;
	}
	public void setMovie_detail_id(int movie_detail_id) {
		this.movie_detail_id = movie_detail_id;
	}
    
	
   
}
