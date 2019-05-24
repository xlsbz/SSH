package com.dhr.dao;
/**
 * 用户接口
 * @author Mr DU
 *
 */

import com.dhr.domain.User;

public interface IUserDao {
	/**
	 * 检查用户名
	 * @return
	 */
	User checkName(String name);

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	void register(User user);

	/**
	 * 查询用户
	 * @param code
	 * @return
	 */
	User findByUser(String code);

	/**
	 * 修改用户
	 * @param exitsUser
	 */
	void updateUser(User exitsUser);

	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @return
	 */
	User loginUser(String account, String password);
}
