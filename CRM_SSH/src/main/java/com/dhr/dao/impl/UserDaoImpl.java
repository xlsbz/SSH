package com.dhr.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.UserDao;
import com.dhr.domain.User;

/**
 * @author Mr DU
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	@Override
	public void addUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * @param user
	 * @return
	 */
	@Override
	public User loginUser(User user) {
		String hql = "from User where user_code=? and user_password=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, user.getUser_code(),
				user.getUser_password());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @return
	 */
	@Override
	public List<User> findAll() {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User");
		return list;
	}

}
