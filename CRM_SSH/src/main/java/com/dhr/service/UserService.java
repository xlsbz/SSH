package com.dhr.service;

import java.util.List;

import com.dhr.domain.User;

/**
 * @author Mr DU
 *
 */
public interface UserService {

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	void addUser(User user);

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	User loginUser(User user);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> findAll();

}
