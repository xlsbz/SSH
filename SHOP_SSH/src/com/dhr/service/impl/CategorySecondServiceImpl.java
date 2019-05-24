package com.dhr.service.impl;

import java.util.List;

import com.dhr.dao.ICategorySecondDao;
import com.dhr.domain.CategorySecond;
import com.dhr.service.ICategorySecondService;
import com.dhr.util.PageBean;

public class CategorySecondServiceImpl implements ICategorySecondService{
	
	//注入service
	private ICategorySecondDao categorySecondDao;
	public void setCategorySecondDao(ICategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	@Override
	public PageBean<CategorySecond> findAllPage(int pageNumber, int pageSize) {
		//封装pageBean
		PageBean<CategorySecond> bean = new PageBean<>(pageNumber, pageSize);
		//查询封装总数居
		int count = categorySecondDao.findAllRecords();
		bean.setTotalRecord(count);
		//查询封装数据记录
		List<CategorySecond> seconds = categorySecondDao.findAllCategorySecond(bean);
		bean.setList(seconds);
		return bean;
	}
	@Override
	public void saveCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.saveCategorySecond(categorySecond);
	}
	@Override
	public void delCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.delCategorySecond(categorySecond);
	}
	@Override
	public CategorySecond findById(CategorySecond categorySecond) {
		return categorySecondDao.toEdit(categorySecond);
	}
	@Override
	public void updateCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.updateCategorySecond(categorySecond);
	}
	@Override
	public List<CategorySecond> finAllSecond() {
		return categorySecondDao.findAllSecond();
	}
	
	
}
