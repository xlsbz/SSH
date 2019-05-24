package com.dhr.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dhr.domain.Category;
import com.dhr.domain.Product;
@Transactional
public interface ICategoryService {

	/**
	 * 分类查询
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
	Category findByCategoryID(Integer cid);

	/**
	 * 修改分类
	 * @param category
	 */
	void updateCategory(Category category);


}
