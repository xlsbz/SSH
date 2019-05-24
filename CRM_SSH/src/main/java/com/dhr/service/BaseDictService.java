package com.dhr.service;

import java.util.List;

import com.dhr.domain.BaseDict;

/**
 * @author Mr DU
 *
 */
public interface BaseDictService {

	/**
	 * 查询指定的数据字典
	 * 
	 * @param dict_type_code
	 * @return
	 */
	List<BaseDict> findByType(String dict_type_code);

}
