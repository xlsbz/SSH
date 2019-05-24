package com.dhr.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dhr.dao.UserDao;
import com.dhr.domain.User;
import com.dhr.service.UserService;
import com.dhr.util.MD5Utils;

/**
 * @author Mr DU 用户业务
 */
@Transactional
public class UserServiceImpl implements UserService {

	// 注入dao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	@Override
	public void addUser(User user) {
		// 注册加密
		String md5 = MD5Utils.md5(user.getUser_password());
		user.setUser_password(md5);
		userDao.addUser(user);
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public User loginUser(User user) {
		String md5 = MD5Utils.md5(user.getUser_password());
		user.setUser_password(md5);
		return userDao.loginUser(user);
	}

	/**
	 * @return
	 */
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
