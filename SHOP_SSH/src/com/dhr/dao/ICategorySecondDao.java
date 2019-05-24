package com.dhr.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dhr.domain.CategorySecond;
import com.dhr.util.PageBean;
@Transactional
public interface ICategorySecondDao {

	/**
	 * 总数据
	 * @return
	 */
	int findAllRecords();

	/**
	 * 每页数据
	 * @param bean 
	 * @return
	 */
	List<CategorySecond> findAllCategorySecond(PageBean<CategorySecond> bean);

	/**
	 * 添加分类
	 * @param categorySecond
	 */
	void saveCategorySecond(CategorySecond categorySecond);

	/**
	 * 删除分类
	 * @param categorySecond
	 */
	void delCategorySecond(CategorySecond categorySecond);

	/**
	 * 去修改
	 * @param categorySecond
	 * @return
	 */
	CategorySecond toEdit(CategorySecond categorySecond);

	/**
	 * 修改
	 * @param categorySecond
	 * @return
	 */
	void updateCategorySecond(CategorySecond categorySecond);

	/**
	 * 查询所有二级分类
	 * @return
	 */
	List<CategorySecond> findAllSecond();

}
