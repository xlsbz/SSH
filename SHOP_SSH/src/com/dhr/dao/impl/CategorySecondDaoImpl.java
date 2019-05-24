package com.dhr.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.ICategorySecondDao;
import com.dhr.domain.CategorySecond;
import com.dhr.util.PageBean;
import com.dhr.util.PageHibernate;
/**
 * 二级分类管理
 * @author Mr DU
 *
 */
public class CategorySecondDaoImpl extends HibernateDaoSupport implements ICategorySecondDao{

	@Override
	public int findAllRecords() {
		String hql = "select count(*) from CategorySecond";
		List<Long> count = (List<Long>) this.getHibernateTemplate().find(hql);
		if(count!=null&&count.size()>0) {
			return count.get(0).intValue();
		}
		return 0;
	}

	
	@Override
	public List<CategorySecond> findAllCategorySecond(PageBean<CategorySecond> bean) {
		String hql = "from CategorySecond";
		List<CategorySecond> categorySeconds = this.getHibernateTemplate().execute(new PageHibernate<>(hql, bean.getStartIndex(), bean.getPageSize(), null));
		if(categorySeconds!=null&&categorySeconds.size()>0) {
			return categorySeconds;
		}
		return null;
	}


	@Override
	public void saveCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}


	@Override
	public void delCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}


	@Override
	public CategorySecond toEdit(CategorySecond categorySecond) {
		CategorySecond second = this.getHibernateTemplate().get(CategorySecond.class,categorySecond.getCsid());
		return second;
	}


	@Override
	public void updateCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}


	@Override
	public List<CategorySecond> findAllSecond() {
		String hql = "from CategorySecond";
		List<CategorySecond> cate = (List<CategorySecond>) this.getHibernateTemplate().find(hql);
		return cate;
	}


}
