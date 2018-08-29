package com.finix.manager;

import java.util.Map;

import com.finix.bean.TheaterOwnerBean;
import com.finix.bean.UserBean;

public interface IUserManager {

	UserBean setUserRegisterDetails(UserBean userBean) throws Exception;

	UserBean setSMSDetails(UserBean userBean) throws Exception;

	UserBean setAccountActivationDetails(UserBean userBean) throws Exception;

	UserBean getUserDetails(UserBean userBean) throws Exception;

	UserBean forgetPasswordForUser(UserBean userBean) throws Exception;

	UserBean getSearchCategoryDetail(UserBean userBean) throws Exception;

	Map<Integer, String> getCityDetail() throws Exception;

	UserBean getCitywiseMovieDetail(UserBean userBean) throws Exception;

	TheaterOwnerBean getMoviewiseTheaterDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getSearchTheaterDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getCityMovieDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getTheaterMovieDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, byte[]> getMasterMoviePosterImage(TheaterOwnerBean theaterOwnerBean) throws Exception;

	Map<Integer, byte[]> getMasterMovieBgPosterImage(TheaterOwnerBean theaterOwnerBean) throws Exception;

	TheaterOwnerBean getMovieBgPosterCountDetail(TheaterOwnerBean theaterOwnerBean) throws Exception;

	UserBean getScreenwiseSeatingDetails(UserBean userBean) throws Exception;

	UserBean getTicketSoldDetail(UserBean userBean)throws Exception;

	UserBean getOrderSummaryDetails(UserBean userBean) throws Exception;

	

}
