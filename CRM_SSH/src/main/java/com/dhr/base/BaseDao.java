package com.dhr.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.dhr.util.PageBean;

/**
 * @author Mr DU 通用Dao
 */
public interface BaseDao<T> {
	// 增
	void save(T t);

	// 删
	void delete(T t);

	// 改
	void update(T t);

	// 查询单个
	T findById(Serializable id);

	// 查询所有
	List<T> findAll();

	// 统计个数
	int findRecords(DetachedCriteria criteria);

	// 分页查询
	List<T> findListPage(DetachedCriteria criteria, PageBean<T> bean);
}
