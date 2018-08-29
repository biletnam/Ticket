package com.finix.bean;

public class LoginBean 
{
	private int role_xid;
	private String email;
	private String password;
	private String status;
	private String activation;
	
	private int theater_owner_id ;
	private int ticket_counter_person_id ;
    private int government_user_id;
	private String name;
	private int end_user_id;

	
	//Getter and setter
	public int getRole_xid() {
		return role_xid;
	}
	public void setRole_xid(int role_xid) {
		this.role_xid = role_xid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getActivation() {
		return activation;
	}
	public void setActivation(String activation) {
		this.activation = activation;
	}
	public int getTheater_owner_id() {
		return theater_owner_id;
	}
	public void setTheater_owner_id(int theater_owner_id) {
		this.theater_owner_id = theater_owner_id;
	}
	public int getTicket_counter_person_id() {
		return ticket_counter_person_id;
	}
	public void setTicket_counter_person_id(int ticket_counter_person_id) {
		this.ticket_counter_person_id = ticket_counter_person_id;
	}
	public int getGovernment_user_id() {
		return government_user_id;
	}
	public void setGovernment_user_id(int government_user_id) {
		this.government_user_id = government_user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnd_user_id() {
		return end_user_id;
	}
	public void setEnd_user_id(int end_user_id) {
		this.end_user_id = end_user_id;
	}
	
	
	

}
