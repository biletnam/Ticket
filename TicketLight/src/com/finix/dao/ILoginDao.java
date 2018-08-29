package com.finix.dao;

import java.util.Map;

import com.finix.bean.LoginBean;
import com.finix.bean.TemplateBean;

public interface ILoginDao {

	Map<String, Object> login(LoginBean loginBean) throws Exception;

	LoginBean getPasswordDetails(LoginBean loginBean)throws Exception;

	TemplateBean getforgotPassword(TemplateBean templateBean)throws Exception;

}
