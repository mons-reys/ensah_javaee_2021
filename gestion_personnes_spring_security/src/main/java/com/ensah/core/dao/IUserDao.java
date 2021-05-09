package com.ensah.core.dao;

import com.ensah.core.bo.UserAccount;
import com.ensah.genericdao.GenericDao;

public interface IUserDao extends GenericDao<UserAccount, Long> {
    UserAccount findByUsername(String username);

}
