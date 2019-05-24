package com.dhr.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.dhr.dao.CustomerDao;
import com.dhr.domain.Customer;
import com.dhr.service.CustomerService;
import com.dhr.util.PageBean;

/**
 * @author Mr DU
 *
 */
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * @param customer
	 */
	@Override
	public void save(Customer customer) {
		customerDao.saveCustomer(customer);
	}

	/**
	 * @return
	 */
	@Override
	public PageBean<Customer> findByPageCustomer(int pageNumber, int pageSize, DetachedCriteria criteria) {
		// 创建pageBean完成属性的补全
		PageBean<Customer> bean = new PageBean<>(pageNumber, pageSize);
		int count = customerDao.findRecord(criteria);
		bean.setTotalRecords(count);
		List<Customer> customers = customerDao.findCustomerList(bean, criteria);
		bean.setList(customers);
		return bean;
	}

	/**
	 * @param cust_id
	 * @return
	 */
	@Override
	public Customer findCustomerById(Long cust_id) {

		return customerDao.findCustomerById(cust_id);
	}

	/**
	 * @param customer
	 */
	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	/**
	 * @param customer
	 */
	@Override
	public void deleteCustomer(Customer customer) {
		customerDao.deleteCustomer(customer);
	}

	/**
	 * @return
	 */
	@Override
	public List<Customer> findCustomer() {
		return customerDao.findAll();
	}

}
