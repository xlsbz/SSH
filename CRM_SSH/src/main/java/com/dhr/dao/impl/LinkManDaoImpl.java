package com.dhr.dao.impl;

import java.util.List;

import com.dhr.base.BaseDaoImpl;
import com.dhr.dao.LinkManDao;
import com.dhr.domain.Customer;
import com.dhr.domain.LinkMan;

/**
 * @author Mr DU
 *
 */

public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {

	// /**
	// * 第一种实现方案：继承通用的dao
	// *
	// * @param clazz
	// */
	// public LinkManDaoImpl() {
	// super(LinkMan.class);
	// }

	// 第二种方案：使用反射无限调用任何构造

	/**
	 * 自己实现查询其他类的数据
	 * 
	 * @return
	 */
	@Override
	public List<Customer> findAllCustomer() {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
		return list;
	}

	// /**
	// * @param criteria
	// * @return
	// */
	// @Override
	// public int findAllRecords(DetachedCriteria criteria) {
	// criteria.setProjection(Projections.rowCount());
	// List<Long> list = (List<Long>)
	// this.getHibernateTemplate().findByCriteria(criteria);
	// if (list != null) {
	// return list.get(0).intValue();
	// }
	// return 0;
	// }
	//
	// /**
	// * @param criteria
	// * @param bean
	// * @return
	// */
	// @Override
	// public List<LinkMan> findAllLinkMan(DetachedCriteria criteria,
	// PageBean<LinkMan> bean) {
	// // 清空查询条件
	// criteria.setProjection(null);
	// List<LinkMan> list = (List<LinkMan>)
	// this.getHibernateTemplate().findByCriteria(criteria, bean.getStartIndex(),
	// bean.getPageSize());
	// if (list != null) {
	// return list;
	// }
	// return null;
	// }
	//
	// /**
	// * @return
	// */
	// @Override
	// public List<Customer> findAllCustomer() {
	// List<Customer> find = (List<Customer>) this.getHibernateTemplate().find("from
	// Customer");
	// if (find != null) {
	// return find;
	// }
	// return null;
	// }
	//
	// /**
	// * @param linkman
	// */
	// @Override
	// public void saveLinkMan(LinkMan linkman) {
	// this.getHibernateTemplate().save(linkman);
	// }
	//
	// /**
	// * @param lkm_id
	// * @return
	// */
	// @Override
	// public LinkMan findLinkManById(Integer lkm_id) {
	// LinkMan linkMan = this.getHibernateTemplate().get(LinkMan.class, lkm_id);
	// return linkMan;
	// }
	//
	// /**
	// * @param linkman
	// */
	// @Override
	// public void updateLinkMan(LinkMan linkman) {
	// this.getHibernateTemplate().update(linkman);
	// }
	//
	// /**
	// * @param man
	// */
	// @Override
	// public void deleteLinkMan(LinkMan man) {
	// this.getHibernateTemplate().delete(man);
	// }

}
