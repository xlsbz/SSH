package com.dhr.dao;

import java.util.List;

import com.dhr.domain.BaseDict;

/**
 * @author Mr DU
 *
 */
public interface BaseDictDao {

	/**
	 * @param dict_type_code
	 * @return
	 */
	List<BaseDict> findByType(String dict_type_code);

}
