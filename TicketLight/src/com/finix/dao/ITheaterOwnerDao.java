package com.finix.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.finix.bean.TemplateBean;
import com.finix.bean.TheaterDetailBean;
import com.finix.bean.TheaterOwnerBean;

public interface ITheaterOwnerDao {

	TheaterOwnerBean setRegistrationDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TemplateBean getRegistrationMailTemplate(TemplateBean templateBean) throws Exception;

	String checkTheaterOwnerEmail(String email) throws Exception;

	TheaterOwnerBean setAccountActivationDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean setMailDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean setSMSDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getTheaterOwnerDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getProfileViewPage(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, String> getStateDetail() throws Exception;

	Map<Integer, String> getCityDetails() throws Exception;

	TheaterOwnerBean getUpdatePageDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, byte[]> profile_images(TheaterOwnerBean theaterOwnerBean) throws Exception;

	String checkOldPasswordAvailable(String oldPassword, TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean updatePasswordDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getStateWiseCityDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getTheaterScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, byte[]> getScreenImage(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getTheaterAgainstScreen(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean setTheaterWiseScreenDetailSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean setTheaterSeatCountDetailSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getSeatingArrangementDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

Map<Integer, String> getDistrictDetails() throws Exception;

	Map<Integer, String> getDistrictWiseCityDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getEditTheaterDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean setAddTheaterDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getAddTheaterAvailableStatusDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, String> getTheaterOwnerTheater(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterDetailBean getTheaterAllDetails(TheaterDetailBean theaterDetailBean) throws Exception;

	TheaterDetailBean getTheaterBasicScreendetail(TheaterDetailBean theaterDetailBean) throws Exception;

	TheaterOwnerBean setDeleteTheaterDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean setUpdateTheaterDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;


	Map<Integer, String> getUserRoleMap();

	TheaterOwnerBean settTheaterAddUserSubmit(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TemplateBean getEmployeeInviteTemplate(TemplateBean templateBean)throws Exception;

	ArrayList<TheaterOwnerBean> getTheaterEmployeeList(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean getEmployeeEditDetails(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean setTheaterEmployeeDetails(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean deleteTheaterEmployeeDetails(TheaterOwnerBean theaterOwnerBean)throws Exception;

	Map<Integer, String> getMovieLanguages()throws Exception;

	Map<Integer, String> getMovieGenre()throws Exception;

	Map<Integer, String> getMovieFormat()throws Exception;

	TheaterOwnerBean setMovieDetailsSubit(TheaterOwnerBean theaterOwnerBean)throws Exception;

	ArrayList<TheaterOwnerBean> getMovieDetailList(TheaterOwnerBean theaterOwnerBean)throws Exception;

	Map<Integer, byte[]> getMoviePosterImage(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterDetailBean setDeleteScreenLayoutDetail(TheaterDetailBean theaterDetailBean) throws Exception;

	Map<Integer, byte[]> getEmployeProfileImage(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, String> getTheaterMapDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

    TheaterOwnerBean setMovieEditDetails(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean setMovieEditDetailSubmit(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean deleteMovieDetailsSubmit(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean getTheaterScreenDetailsForView(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getTheaterAgainstScreenForview(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getEditScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getDistrictWiseCityDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getNavBarScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception; 

	Map<Integer, String> getMovieIdDetails(TheaterOwnerBean theaterOwnerBean) throws Exception; 

   TheaterOwnerBean setMovieScreenShowTimeDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getMovieScreenShowDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, String> getScreenMapDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean setDeleteShowTime(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getMovieIDDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getNavBarMovieDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getNavBarUserDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

    HashMap<Integer, String> getMovieDate(TheaterOwnerBean theaterOwnerBean)throws Exception;

	HashMap<Integer, String> getMovieShows(TheaterOwnerBean theaterOwnerBean)throws Exception;

	ArrayList<TheaterOwnerBean> getMovieShowList(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean getScreenwiseSeatingDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, String> getMasterMovieDetails() throws Exception;

	Map<Integer, String> getMovieCertificationDetails() throws Exception;

	TheaterOwnerBean getTicketDetail(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean getTicketSoldDetail(TheaterOwnerBean theaterOwnerBean)throws Exception;

	TheaterOwnerBean getTheatreOwnersTaxDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, String> getTaxMap(TheaterOwnerBean theaterOwnerBean)throws Exception;

	ArrayList<TheaterOwnerBean> getTheaterOwnerMoviewiseDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;

	ArrayList<TheaterOwnerBean> getTheaterOwnerMovieScreenwiseDetails(TheaterOwnerBean theaterOwnerBean) throws Exception;


}
