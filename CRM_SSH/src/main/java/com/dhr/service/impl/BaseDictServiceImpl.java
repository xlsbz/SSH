package com.dhr.service.impl;

import java.util.List;

import com.dhr.dao.BaseDictDao;
import com.dhr.domain.BaseDict;
import com.dhr.service.BaseDictService;

/**
 * @author Mr DU
 *
 */
public class BaseDictServiceImpl implements BaseDictService {

	// 注入dao
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	/**
	 * @param dict_type_code
	 * @return
	 */
	@Override
	public List<BaseDict> findByType(String dict_type_code) {
		return baseDictDao.findByType(dict_type_code);
	}

}
