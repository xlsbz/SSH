package com.dhr.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.IAdminDao;
import com.dhr.domain.Admin;

public class AdminDaoImpl extends HibernateDaoSupport implements IAdminDao {

	@Override
	/**
	 * 用户登录
	 */
	public Admin loginAdmin(Admin admin) {
		String hql = "from Admin where username = ? and password = ?";
		List<Admin> a = 
				(List<Admin>) this.getHibernateTemplate().find(hql, admin.getUsername(),admin.getPassword());
		if(a!=null&&a.size()>0) {
			return a.get(0);
		}
		return null;
	}

}
