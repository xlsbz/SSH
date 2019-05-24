package com.dhr.dao;

import com.dhr.domain.Admin;

public interface IAdminDao {

	/**
	 * 登录
	 * @param admin
	 * @return
	 */
	Admin loginAdmin(Admin admin);

}
