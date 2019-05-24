package com.dhr.service;

import java.util.List;

import com.dhr.domain.CategorySecond;
import com.dhr.util.PageBean;

public interface ICategorySecondService {

	/**
	 * 分页查询二级分类
	 * @return
	 */
	PageBean<CategorySecond> findAllPage(int pageNumber, int pageSize);

	/**
	 * 添加分类
	 * @param categorySecond
	 */
	void saveCategorySecond(CategorySecond categorySecond);

	/**
	 * 删除二级分类
	 * @param categorySecond
	 */
	void delCategorySecond(CategorySecond categorySecond);

	/**
	 * 去修改页面
	 * @param categorySecond
	 * @return
	 */
	CategorySecond findById(CategorySecond categorySecond);

	/**
	 * 修改
	 * @param categorySecond
	 */
	void updateCategorySecond(CategorySecond categorySecond);

	/**
	 * 查询所有二级分类
	 * @return
	 */
	List<CategorySecond> finAllSecond();

}
