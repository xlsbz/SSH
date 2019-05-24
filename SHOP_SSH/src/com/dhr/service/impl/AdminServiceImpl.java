package com.dhr.service.impl;

import com.dhr.dao.IAdminDao;
import com.dhr.domain.Admin;
import com.dhr.service.IAdminService;

public class AdminServiceImpl implements IAdminService{
	//注入dao
	private IAdminDao adminDao;
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}
	@Override
	public Admin loginAdmin(Admin admin) {
		return adminDao.loginAdmin(admin);
	}
	
	
}
