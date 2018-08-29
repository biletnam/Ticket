package com.finix.dao;

import com.finix.bean.GovernmentUserBean;

public interface IGovernmentUserDao {

	GovernmentUserBean getGovernmentUserDetails(GovernmentUserBean governmentUserBean) throws Exception;

}
