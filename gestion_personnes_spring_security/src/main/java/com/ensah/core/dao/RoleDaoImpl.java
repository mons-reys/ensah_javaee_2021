package com.ensah.core.dao;

import org.springframework.stereotype.Repository;

import com.ensah.core.bo.Role;
import com.ensah.genericdao.HibernateSpringGenericDaoImpl;

@Repository
public class RoleDaoImpl extends HibernateSpringGenericDaoImpl<Role, Long> implements IRoleDao{

	public RoleDaoImpl() {
		super(Role.class);
	}

	
	
}
