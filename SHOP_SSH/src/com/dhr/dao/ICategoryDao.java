package com.dhr.dao;

import java.util.List;

import com.dhr.domain.Category;

public interface ICategoryDao {

	/**
	 * 分类
	 * @return
	 */
	List<Category> findByCategory();

	/**
	 * 添加分类
	 * @param category
	 */
	void saveCategory(Category category);

	/**
	 * 删除分类
	 * @param cid
	 */
	void delCategory(Category category);

	/**
	 * 去编辑
	 * @param cid
	 * @return
	 */
	Category toEdit(Integer cid);

	/**
	 * 修改分类
	 * @param category
	 */
	void updateCategory(Category category);

}
