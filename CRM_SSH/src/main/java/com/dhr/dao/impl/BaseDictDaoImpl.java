package com.dhr.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.BaseDictDao;
import com.dhr.domain.BaseDict;

/**
 * @author Mr DU
 *
 */
public class BaseDictDaoImpl extends HibernateDaoSupport implements BaseDictDao {

	/**
	 * 查询指定的数据字典
	 * 
	 * @param dict_type_code
	 * @return
	 */
	@Override
	public List<BaseDict> findByType(String dict_type_code) {
		List<BaseDict> list = (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code=?",
				dict_type_code);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;

	}

}
