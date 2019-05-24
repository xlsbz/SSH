package com.dhr.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.ICategoryDao;
import com.dhr.domain.Category;
/**
 * 分类管理Dao
 * @author Mr DU
 *
 */
public class CategoryDaoImpl extends HibernateDaoSupport implements ICategoryDao {

	@Override
	public List<Category> findByCategory() {
		String hql = "from Category order by cid asc";
		List<Category> cates = (List<Category>) this.getHibernateTemplate().find(hql);
		return cates;
	}

	@Override
	public void saveCategory(Category category) {
		this.getHibernateTemplate().save(category);
	}

	@Override
	public void delCategory(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	@Override
	public Category toEdit(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	@Override
	public void updateCategory(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
