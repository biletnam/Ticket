package com.finix.manager.impl;

import java.util.HashMap;
import java.util.Map;

import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.UserBean;
import com.finix.dao.IUserDao;
import com.finix.dao.impl.UserDaoImpl;
import com.finix.manager.IUserManager;

public class UserManagerImpl implements IUserManager 
{

	IUserDao userDao = new UserDaoImpl();
	
	//setUserRegisterDetails
	public UserBean setUserRegisterDetails(UserBean userBean) throws Exception 
	{
		userBean = userDao.setUserRegisterDetails(userBean);
		return userBean;
	}

	//setSMSDetails
	public UserBean setSMSDetails(UserBean userBean) throws Exception 
	{
		userBean = userDao.setSMSDetails(userBean);
		return userBean;
	}

	//setAccountActivationDetails
	public UserBean setAccountActivationDetails(UserBean userBean) throws Exception 
	{
		userBean = userDao.setAccountActivationDetails(userBean);
		return userBean;
	}

	//getUserDetails
	public UserBean getUserDetails(UserBean userBean) throws Exception 
	{
		userBean = userDao.getUserDetails(userBean);
		return userBean;
	}

	//forgetPasswordForUser
	public UserBean forgetPasswordForUser(UserBean userBean) throws Exception 
	{
		userBean = userDao.forgetPasswordForUser(userBean);
		return userBean;
	}
	
//getSearchLocationDetail
	public UserBean getSearchCategoryDetail(UserBean userBean) throws Exception {
		userBean = userDao.getSearchCategoryDetail(userBean);
		return userBean;
	}

	//getCityDetail
	public Map<Integer, String> getCityDetail() throws Exception {
		Map<Integer, String> citymap = new HashMap<Integer, String>();
		citymap =  userDao.getCityDetail();
		return citymap;
	}

	//getCitywiseMovieDetail created by ramya 18-08-18
	public UserBean getCitywiseMovieDetail(UserBean userBean) throws Exception {
		userBean = userDao.getCitywiseMovieDetail(userBean);
		return userBean;
	}

	//getMoviewiseTheaterDetail created by ramya 18-08-18
	public TheaterOwnerBean getMoviewiseTheaterDetail(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		theaterOwnerBean = userDao.getMoviewiseTheaterDetail(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//getSearchTheaterDetail created by ramya 18-08-18
	public TheaterOwnerBean getSearchTheaterDetail(TheaterOwnerBean theaterOwnerBean)
			throws Exception {
		theaterOwnerBean = userDao.getSearchTheaterDetail(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//getCityMovieDetail created by ramya 20-08-18
	public TheaterOwnerBean getCityMovieDetail(TheaterOwnerBean theaterOwnerBean)
			throws Exception {
		theaterOwnerBean = userDao.getCityMovieDetail(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//getTheaterMovieDetail created by ramya 20-08-18
	public TheaterOwnerBean getTheaterMovieDetail(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		theaterOwnerBean = userDao.getTheaterMovieDetail(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//getMasterMoviePosterImage created by ramya 20-08-18
	public Map<Integer, byte[]> getMasterMoviePosterImage(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		Map<Integer, byte[]> moviePosterMap = new HashMap<Integer,byte[]>();
		moviePosterMap = userDao.getMasterMoviePosterImage(theaterOwnerBean);
		return moviePosterMap;
	}

	//getMasterMovieBgPosterImage created by ramya 20-08-18
	public Map<Integer, byte[]> getMasterMovieBgPosterImage(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		Map<Integer, byte[]> movieBgPosterMap = new HashMap<Integer,byte[]>();
		movieBgPosterMap = userDao.getMasterMovieBgPosterImage(theaterOwnerBean);
		return movieBgPosterMap;
	}

	//getMovieBgPosterCountDetail created by ramya 21-08-18
	public TheaterOwnerBean getMovieBgPosterCountDetail(
			TheaterOwnerBean theaterOwnerBean) throws Exception {
		theaterOwnerBean = userDao.getMovieBgPosterCountDetail(theaterOwnerBean);
		return theaterOwnerBean;
	}

	//get theatre layout
	public UserBean getScreenwiseSeatingDetails(UserBean userBean) throws Exception {
		userBean = userDao.getScreenwiseSeatingDetails(userBean);
		return userBean;
	}

	//set ticket details
	public UserBean getTicketSoldDetail(UserBean userBean) throws Exception {
		userBean=userDao.getTicketSoldDetail(userBean);
		return userBean;
	}

	//getOrderSummaryDetails
	public UserBean getOrderSummaryDetails(UserBean userBean) throws Exception 
	{
		userBean = userDao.getOrderSummaryDetails(userBean);
		return userBean;
	}

	

}
