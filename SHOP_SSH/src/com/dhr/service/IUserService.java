package com.dhr.service;
/**
 * 用户业务接口
 * @author Mr DU
 *
 */

import com.dhr.domain.User;

public interface IUserService {
	/**
	 * 检查用户名是否存在
	 */
	User checkName(String name);

	/**
	 * 注册用户
	 * @param user
	 */
	void register(User user);

	/**
	 * 查询用户
	 * @param code
	 * @return
	 */
	User findByUser(String code);

	/**
	 * 用户修改
	 * @param exitsUser
	 */
	void updateUser(User exitsUser);

	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @return 
	 */
	User login(String account, String password);
}
