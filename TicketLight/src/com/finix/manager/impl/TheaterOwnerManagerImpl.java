package com.finix.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.finix.bean.TemplateBean;
import com.finix.bean.TheaterDetailBean;
import com.finix.bean.TheaterOwnerBean;
import com.finix.dao.ITheaterOwnerDao;
import com.finix.dao.impl.TheaterOwnerDaoImpl;
import com.finix.manager.ITheaterOwnerManager;

public class TheaterOwnerManagerImpl implements ITheaterOwnerManager
{

	ITheaterOwnerDao theaterOwnerDao = new TheaterOwnerDaoImpl();
	
	//setRegistrationDetails
	public TheaterOwnerBean setRegistrationDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		theaterOwnerBean = theaterOwnerDao.setRegistrationDetails(theaterOwnerBean);
		return theaterOwnerBean;
	}
	
	//getRegistrationMailTemplate
	public TemplateBean getRegistrationMailTemplate(TemplateBean templateBean) throws Exception 
	{
		templateBean = theaterOwnerDao.getRegistrationMailTemplate(templateBean);
		return templateBean;
	}

	//checkTheaterOwnerEmail
	public String checkTheaterOwnerEmail(String email) throws Exception 
	{
		String emailVal = "";
		emailVal = theaterOwnerDao.checkTheaterOwnerEmail(email);
		return emailVal;
	}

	//setAccountActivationDetails
	public TheaterOwnerBean setAccountActivationDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		theaterOwnerBean = theaterOwnerDao.setAccountActivationDetails(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//setMailDetails
	public TheaterOwnerBean setMailDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		theaterOwnerBean = theaterOwnerDao.setMailDetails(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//setSMSDetails
	public TheaterOwnerBean setSMSDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		theaterOwnerBean = theaterOwnerDao.setSMSDetails(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//getTheaterOwnerDetails
	public TheaterOwnerBean getTheaterOwnerDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
	{
		theaterOwnerBean = theaterOwnerDao.getTheaterOwnerDetails(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//getProfileViewPage
		public TheaterOwnerBean getProfileViewPage(TheaterOwnerBean theaterOwnerBean)
				throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getProfileViewPage(theaterOwnerBean);
			return theaterOwnerBean;
		}
		
		//getStateDetail
		public Map<Integer, String> getStateDetail() throws Exception 
		{
			Map<Integer,String> stateMap = new HashMap<Integer,String>();
			stateMap = theaterOwnerDao.getStateDetail();
			return stateMap;
		}

		//getCityDetails
		public Map<Integer, String> getCityDetails() throws Exception 
		{
			Map<Integer,String> cityMap = new HashMap<Integer,String>();
			cityMap = theaterOwnerDao.getCityDetails();
			return cityMap;
		}
		
		//getUpdatePageDetails
		public TheaterOwnerBean getUpdatePageDetails(TheaterOwnerBean theaterOwnerBean)
				throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getUpdatePageDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//profile_images
		public Map<Integer, byte[]> profile_images(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
			imageMap = theaterOwnerDao.profile_images(theaterOwnerBean);
			return imageMap;
		}
		
		//checkOldPasswordAvailable
		public String checkOldPasswordAvailable(String oldPassword,
				TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			String status = "";
			status = theaterOwnerDao.checkOldPasswordAvailable(oldPassword,theaterOwnerBean);
			return status;
		}

		//updatePasswordDetails
		public TheaterOwnerBean updatePasswordDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean=theaterOwnerDao.updatePasswordDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}
		
		//getStateWiseCityDetails
		public TheaterOwnerBean getStateWiseCityDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getStateWiseCityDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getTheaterScreenDetails
		public TheaterOwnerBean getTheaterScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getTheaterScreenDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getScreenImage
		public Map<Integer, byte[]> getScreenImage(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
			imageMap = theaterOwnerDao.getScreenImage(theaterOwnerBean);
			return imageMap;
		}

		//getTheaterAgainstScreen
		public TheaterOwnerBean getTheaterAgainstScreen(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getTheaterAgainstScreen(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//setTheaterWiseScreenDetailSubmit
		public TheaterOwnerBean setTheaterWiseScreenDetailSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.setTheaterWiseScreenDetailSubmit(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//setTheaterSeatCountDetailSubmit
		public TheaterOwnerBean setTheaterSeatCountDetailSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.setTheaterSeatCountDetailSubmit(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getSeatingArrangementDetails
		public TheaterOwnerBean getSeatingArrangementDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getSeatingArrangementDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getDistrictDetails created by ramya - 30-07-18
  		public Map<Integer, String> getDistrictDetails() throws Exception {
  			Map<Integer,String> districtMap = new HashMap<Integer,String>();
  			districtMap = theaterOwnerDao.getDistrictDetails();
  			return districtMap;
  		}

		//getDistrictWiseCityDetail
		public Map<Integer, String> getDistrictWiseCityDetail(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Map<Integer,String> cityMap = new HashMap<Integer,String>();
			cityMap = theaterOwnerDao.getDistrictWiseCityDetail(theaterOwnerBean);
  			return cityMap;
		}

		//getTheaterDetails
		public TheaterOwnerBean getEditTheaterDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.getEditTheaterDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//setAddTheaterDetails created by ramya - 30-07-18
		public TheaterOwnerBean setAddTheaterDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.setAddTheaterDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getAddTheaterAvailableStatusDetails by ramya - 31-07-18
		public TheaterOwnerBean getAddTheaterAvailableStatusDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.getAddTheaterAvailableStatusDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getTheaterOwnerTheaterDetails by ramya - 01-08-18
		public Map<Integer, String> getTheaterOwnerTheater(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Map<Integer,String> theaterMap = new HashMap<Integer,String>();
			theaterMap = theaterOwnerDao.getTheaterOwnerTheater(theaterOwnerBean);
  			return theaterMap;
		}

		//getAllTheaterDetails by ramya - 01-08-18
		public TheaterDetailBean getTheaterAllDetails(
				TheaterDetailBean theaterDetailBean) throws Exception {
			theaterDetailBean=theaterOwnerDao.getTheaterAllDetails(theaterDetailBean);
			return theaterDetailBean;
		}

		//getAllTheaterDetails by ramya - 01-08-18
		public TheaterDetailBean getTheaterBasicScreendetail(
				TheaterDetailBean theaterDetailBean) throws Exception {
			theaterDetailBean=theaterOwnerDao.getTheaterBasicScreendetail(theaterDetailBean);
			return theaterDetailBean;
		}

		//setDeleteTheaterDetail by ramya - 02-08-18
		public TheaterOwnerBean setDeleteTheaterDetail(
						TheaterOwnerBean theaterOwnerBean) throws Exception {
		    theaterOwnerBean=theaterOwnerDao.setDeleteTheaterDetail(theaterOwnerBean);
		    return theaterOwnerBean;
		}

		//setUpdateTheaterDetails by ramya - 03-08-18
		public TheaterOwnerBean setUpdateTheaterDetails(
						TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.setUpdateTheaterDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}
	@Override
		public Map<Integer, String> getUserRoleMap() throws Exception {
			Map<Integer, String> userRoleMap=new HashMap<Integer,String>();
			userRoleMap=theaterOwnerDao.getUserRoleMap();
			return userRoleMap;
		}

	//set theater add user
		public TheaterOwnerBean settTheaterAddUserSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.settTheaterAddUserSubmit(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//get template invite employee
		public TemplateBean getEmployeeInviteTemplate(TemplateBean templateBean) throws Exception {
			templateBean=theaterOwnerDao.getEmployeeInviteTemplate(templateBean);
			return templateBean;
		}

		//get theater employee list
		public ArrayList<TheaterOwnerBean> getTheaterEmployeeList(TheaterOwnerBean theaterOwnerBean) throws Exception {
			ArrayList<TheaterOwnerBean> theaterEmployeeList=new ArrayList<TheaterOwnerBean>();
			theaterEmployeeList=theaterOwnerDao.getTheaterEmployeeList(theaterOwnerBean);
			return theaterEmployeeList;
		}

		//get employee details
		public TheaterOwnerBean getEmployeeEditDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.getEmployeeEditDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//set employee edit details
		public TheaterOwnerBean setTheaterEmployeeDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.setTheaterEmployeeDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//delete employee details
		public TheaterOwnerBean deleteTheaterEmployeeDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.deleteTheaterEmployeeDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//get movie languge 
		public Map<Integer, String> getMovieLanguages() throws Exception {
			Map<Integer, String> movieLangMap=new HashMap<Integer,String>();
			movieLangMap=theaterOwnerDao.getMovieLanguages();
			return movieLangMap;
		}

		//get movie genre
		public Map<Integer, String> getMovieGenre() throws Exception {
			Map<Integer, String> movieGenreMap=new HashMap<Integer,String>();
			movieGenreMap=theaterOwnerDao.getMovieGenre();
			return movieGenreMap;
		}

		//get movie format
		public Map<Integer, String> getMovieFormat() throws Exception {
			Map<Integer, String> movieFormatMap=new HashMap<Integer,String>();
			movieFormatMap=theaterOwnerDao.getMovieFormat();
			return movieFormatMap;
		}

		//set movie details submit
		public TheaterOwnerBean setMovieDetailsSubit(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.setMovieDetailsSubit(theaterOwnerBean);
			return theaterOwnerBean;
		}

	//get movie details
		public ArrayList<TheaterOwnerBean> getMovieDetailList(TheaterOwnerBean theaterOwnerBean) throws Exception {
			ArrayList<TheaterOwnerBean> movieList=new ArrayList<TheaterOwnerBean>();
			movieList=theaterOwnerDao.getMovieDetailList(theaterOwnerBean);
			return movieList;
		}

		//get movie poster
		public Map<Integer, byte[]> getMoviePosterImage(TheaterOwnerBean theaterOwnerBean) throws Exception {
			Map<Integer, byte[]> movieImgeMap = new HashMap<Integer,byte[]>();
			movieImgeMap=theaterOwnerDao.getMoviePosterImage(theaterOwnerBean);
			return movieImgeMap;
		}

		//setDeleteScreenLayoutDetail created by ramya-03-08-17
		public TheaterDetailBean setDeleteScreenLayoutDetail(TheaterDetailBean theaterDetailBean) throws Exception {
			theaterDetailBean=theaterOwnerDao.setDeleteScreenLayoutDetail(theaterDetailBean);
			return theaterDetailBean;
		}

		//getEmployeProfileImage created by ramya-04-08-17
		public Map<Integer, byte[]> getEmployeProfileImage(
						TheaterOwnerBean theaterOwnerBean) throws Exception {
			Map<Integer, byte[]> imageMap = new HashMap<Integer,byte[]>();
			imageMap=theaterOwnerDao.getEmployeProfileImage(theaterOwnerBean);
			return imageMap;
		}

		//getTheaterMapDetail created by ramya-06-08-17
		public Map<Integer, String> getTheaterMapDetail(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Map<Integer, String> theaterMap=new HashMap<Integer,String>();
			theaterMap=theaterOwnerDao.getTheaterMapDetail(theaterOwnerBean);
			return theaterMap;
		}

        //edit movie details
		public TheaterOwnerBean setMovieEditDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
		theaterOwnerBean=theaterOwnerDao.setMovieEditDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//edit movie details submit
		public TheaterOwnerBean setMovieEditDetailSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.setMovieEditDetailSubmit(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//delete movie details
		public TheaterOwnerBean deleteMovieDetailsSubmit(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.deleteMovieDetailsSubmit(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getTheaterScreenDetailsForView
		public TheaterOwnerBean getTheaterScreenDetailsForView(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getTheaterScreenDetailsForView(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getTheaterAgainstScreenForview
		public TheaterOwnerBean getTheaterAgainstScreenForview(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getTheaterAgainstScreenForview(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getEditScreenDetails
		public TheaterOwnerBean getEditScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getEditScreenDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getDistrictWiseCityDetails
		public TheaterOwnerBean getDistrictWiseCityDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getDistrictWiseCityDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getNavBarScreenDetails
		public TheaterOwnerBean getNavBarScreenDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getNavBarScreenDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

	@Override
		public Map<Integer, String> getMovieIdDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			Map<Integer, String> movieIdMap=new HashMap<Integer,String>();
			movieIdMap = theaterOwnerDao.getMovieIdDetails(theaterOwnerBean);
			return movieIdMap;
		}

      //setMovieScreenShowTimeDetail created by ramya-10-08-18
		public TheaterOwnerBean setMovieScreenShowTimeDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean = theaterOwnerDao.setMovieScreenShowTimeDetail(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getMovieScreenShowDetail created by ramya-11-08-18
		public TheaterOwnerBean getMovieScreenShowDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean = theaterOwnerDao.getMovieScreenShowDetail(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getScreenMapDetail created by ramya-11-08-18
		public Map<Integer, String> getScreenMapDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			Map<Integer, String> screenMap=new HashMap<Integer,String>();
			screenMap = theaterOwnerDao.getScreenMapDetail(theaterOwnerBean);
			return screenMap;
		}

		//setDeleteShowTime created by ramya-11-08-18
		public TheaterOwnerBean setDeleteShowTime(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean = theaterOwnerDao.setDeleteShowTime(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getMovieIDDetails
		public TheaterOwnerBean getMovieIDDetails(TheaterOwnerBean theaterOwnerBean) throws Exception 
		{
			theaterOwnerBean = theaterOwnerDao.getMovieIDDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getNavBarMovieDetails
		public TheaterOwnerBean getNavBarMovieDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean = theaterOwnerDao.getNavBarMovieDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getNavBarUserDetails
		public TheaterOwnerBean getNavBarUserDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean = theaterOwnerDao.getNavBarUserDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

       //get movie details
		public HashMap<Integer, String> getMovieDate(TheaterOwnerBean theaterOwnerBean) throws Exception {
			HashMap<Integer, String> movieDateMap=new HashMap<Integer,String>();
			movieDateMap=theaterOwnerDao.getMovieDate(theaterOwnerBean);
			return movieDateMap;
		}

		//get shows
		public HashMap<Integer, String> getMovieShows(TheaterOwnerBean theaterOwnerBean) throws Exception {
			HashMap<Integer, String> movieShowMap=new HashMap<Integer,String>();
			movieShowMap=theaterOwnerDao.getMovieShows(theaterOwnerBean);
			return movieShowMap;
		}

		//get show list
		public ArrayList<TheaterOwnerBean> getMovieShowList(TheaterOwnerBean theaterOwnerBean) throws Exception {
			ArrayList<TheaterOwnerBean> movieShowList=new ArrayList<TheaterOwnerBean>();
			movieShowList=theaterOwnerDao.getMovieShowList(theaterOwnerBean);
			return movieShowList;
		}

		//getScreenwiseSeatingDetails
		public TheaterOwnerBean getScreenwiseSeatingDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean = theaterOwnerDao.getScreenwiseSeatingDetails(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getMasterMovieDetails created by ramya - 15-08-18
		public Map<Integer, String> getMasterMovieDetails() throws Exception {
			Map<Integer, String> movieIdMap=new HashMap<Integer,String>();
			movieIdMap=theaterOwnerDao.getMasterMovieDetails();
			return movieIdMap;
		}

		//getMovieCertificationDetails created by ramya - 15-08-18
		public Map<Integer, String> getMovieCertificationDetails()
				throws Exception {
			Map<Integer, String> movieCertificationMap=new HashMap<Integer,String>();
			movieCertificationMap = theaterOwnerDao.getMovieCertificationDetails();
			return movieCertificationMap;
		}


		//ticket details
		public TheaterOwnerBean getTicketDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.getTicketDetail(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//ticket booking details
		public TheaterOwnerBean getTicketSoldDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			theaterOwnerBean=theaterOwnerDao.getTicketSoldDetail(theaterOwnerBean);
			return theaterOwnerBean;
		}

		//getTaxMap
		public Map<Integer, String> getTaxMap(TheaterOwnerBean theaterOwnerBean)
				throws Exception {
			Map<Integer, String> getTaxMap=new HashMap<Integer,String>();
			getTaxMap = theaterOwnerDao.getTaxMap(theaterOwnerBean);
			return getTaxMap;
		}

		//getTheatreOwnersTaxDetail
		public TheaterOwnerBean  getTheatreOwnersTaxDetail(TheaterOwnerBean theaterOwnerBean) throws Exception {
			
			theaterOwnerBean=theaterOwnerDao.getTheatreOwnersTaxDetail(theaterOwnerBean);
			
			return theaterOwnerBean;
		}

		@Override
		public ArrayList<TheaterOwnerBean> getTheaterOwnerMoviewiseDetails(TheaterOwnerBean theaterOwnerBean) throws Exception {
			ArrayList<TheaterOwnerBean> theatreOwnerMovieList  =new ArrayList<TheaterOwnerBean>();
			theatreOwnerMovieList=theaterOwnerDao.getTheaterOwnerMoviewiseDetails(theaterOwnerBean);
			return theatreOwnerMovieList;
		}

		//getTheaterOwnerMovieScreenwiseDetails
		public ArrayList<TheaterOwnerBean> getTheaterOwnerMovieScreenwiseDetails(
				TheaterOwnerBean theaterOwnerBean) throws Exception {
			ArrayList<TheaterOwnerBean> screenArrayList  =new ArrayList<TheaterOwnerBean>();
			screenArrayList=theaterOwnerDao.getTheaterOwnerMovieScreenwiseDetails(theaterOwnerBean);
			return screenArrayList;
		}
}
