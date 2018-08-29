package com.finix.manager.impl;

import com.finix.bean.GovernmentUserBean;
import com.finix.dao.IGovernmentUserDao;
import com.finix.dao.impl.GovernmentUserDaoImpl;
import com.finix.manager.IGovernmentUserManager;

public class GovernmentUserManagerImpl implements IGovernmentUserManager{

	IGovernmentUserDao governmentUserDao = new GovernmentUserDaoImpl();
	
	//getGovernmentUserDetails created by ramya -12-08-18
	public GovernmentUserBean getGovernmentUserDetails(GovernmentUserBean governmentUserBean) throws Exception {
		governmentUserBean = governmentUserDao.getGovernmentUserDetails(governmentUserBean);
		return governmentUserBean;
	}

}
