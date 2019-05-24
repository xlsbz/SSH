package com.dhr.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.IUserDao;
import com.dhr.domain.User;
import com.dhr.util.Constant;
import com.dhr.util.UUIDUtils;

/**
 * 用户实现类
 * @author Mr DU
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements IUserDao{

	@Override
	/**
	 * 检查用户名是否存在
	 */
	public User checkName(String name) {
		String sql = "from User where username=?";
		List<User> users = (List<User>) this.getHibernateTemplate().find(sql, name);
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	/**
	 * 注册用户
	 */
	public void register(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	/**
	 * 查询用户
	 */
	public User findByUser(String code) {
		String hql = "from User where code = ?";
		List<User> users = (List<User>) this.getHibernateTemplate().find(hql, code);
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	/**
	 * 修改用户
	 */
	public void updateUser(User exitsUser) {
		this.getHibernateTemplate().update(exitsUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 用户登录
	 */
	public User loginUser(String account, String password) {
		//account肯是用户名也可能是邮箱
		User user = null;
		//用户名登录
		String hql = "from User where username = ? and password = ?";
		List<User> users = (List<User>) this.getHibernateTemplate().find(hql, account,password);
		if(users.size()>0) {
			user = users.get(0);
		}else {
			//邮箱登录
			hql = "from User where email = ? and password = ?";
			users = (List<User>) this.getHibernateTemplate().find(hql, account,password);
			if(users.size()>0) {
				user = users.get(0);
			}
		}
		return user;
	}
	
	
}
