package com.ensah.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ensah.core.bo.UserAccount;
import com.ensah.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class UserDaoImpl extends HibernateSpringGenericDaoImpl<UserAccount, Long> implements IUserDao {

	public UserDaoImpl() {

		super(UserAccount.class);

	}

	public UserAccount findByUsername(String username) {
		List<UserAccount> users = getEntityByColValue("UserAccount", "username", username);
		return users.size() != 0
				? users.get(0)
				: null;
	}
}
