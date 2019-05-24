package com.dhr.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.CustomerDao;
import com.dhr.domain.Customer;
import com.dhr.util.PageBean;

/**
 * @author Mr DU
 *
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	/**
	 * @param customer
	 */
	@Override
	public void saveCustomer(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	/**
	 * @return
	 */
	@Override
	public int findRecord(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param bean
	 * @return
	 */
	@Override
	public List<Customer> findCustomerList(PageBean<Customer> bean, DetachedCriteria detachedCriteria) {
		// 清除设置的离线查询条件
		detachedCriteria.setProjection(null);
		// 查询
		List<Customer> findByCriteria = (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria,
				bean.getStartIndex(), bean.getPageSize());
		if (findByCriteria != null && findByCriteria.size() > 0) {
			return findByCriteria;
		}
		return null;
	}

	/**
	 * @param cust_id
	 * @return
	 */
	@Override
	public Customer findCustomerById(Long cust_id) {
		Customer customer = this.getHibernateTemplate().get(Customer.class, cust_id);
		return customer;
	}

	/**
	 * @param customer
	 */
	@Override
	public void updateCustomer(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	/**
	 * @param customer
	 */
	@Override
	public void deleteCustomer(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	/**
	 * @return
	 */
	@Override
	public List<Customer> findAll() {
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().find("from Customer");
		return list;
	}

}
