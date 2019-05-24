package com.dhr.dao;

import java.util.List;

import com.dhr.domain.User;

/**
 * @author Mr DU
 *
 */
public interface UserDao {

	/**
	 * @param user
	 */
	void addUser(User user);

	/**
	 * @param user
	 * @return
	 */
	User loginUser(User user);

	/**
	 * @return
	 */
	List<User> findAll();

}
