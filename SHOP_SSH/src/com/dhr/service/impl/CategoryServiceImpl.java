package com.dhr.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dhr.dao.ICategoryDao;
import com.dhr.domain.Category;
import com.dhr.service.ICategoryService;
/**
 * 商品
 * @author Mr DU
 *
 */
public class CategoryServiceImpl implements ICategoryService{

	//注入service
	private ICategoryDao categoryDao;
	public void setCategoryDao(ICategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}


	/**
	 * 分类查询
	 */
	@Override
	public List<Category> findByCategory() {
		return categoryDao.findByCategory();
	}


	@Override
	public void saveCategory(Category category) {
		categoryDao.saveCategory(category);
	}


	@Override
	public void delCategory(Category category) {
		categoryDao.delCategory(category);
	}


	@Override
	public Category findByCategoryID(Integer cid) {
		return categoryDao.toEdit(cid);
	}


	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}


}
