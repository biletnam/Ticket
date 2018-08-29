package com.finix.manager;

import com.finix.bean.GovernmentUserBean;

public interface IGovernmentUserManager {

	GovernmentUserBean getGovernmentUserDetails(GovernmentUserBean governmentUserBean) throws Exception;

}
