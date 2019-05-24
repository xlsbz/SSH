package com.dhr.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.dhr.domain.Customer;
import com.dhr.util.PageBean;

/**
 * @author Mr DU
 *
 */
public interface CustomerDao {

	/**
	 * @param customer
	 */
	void saveCustomer(Customer customer);

	/**
	 * @param criteria
	 * @return
	 */
	int findRecord(DetachedCriteria criteria);

	/**
	 * @param bean
	 * @param criteria
	 * @return
	 */
	List<Customer> findCustomerList(PageBean<Customer> bean, DetachedCriteria criteria);

	/**
	 * @param cust_id
	 * @return
	 */
	Customer findCustomerById(Long cust_id);

	/**
	 * @param customer
	 */
	void updateCustomer(Customer customer);

	/**
	 * @param customer
	 */
	void deleteCustomer(Customer customer);

	/**
	 * @return
	 */
	List<Customer> findAll();

}
