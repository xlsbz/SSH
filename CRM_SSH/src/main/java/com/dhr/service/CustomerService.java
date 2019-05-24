package com.dhr.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.dhr.domain.Customer;
import com.dhr.util.PageBean;

/**
 * @author Mr DU
 *
 */
public interface CustomerService {

	/**
	 * 保存
	 * 
	 * @param customer
	 */
	void save(Customer customer);

	/**
	 * 分页查询
	 * 
	 * @param pageSize
	 * 
	 * @param criteria
	 * 
	 * @return
	 */
	PageBean<Customer> findByPageCustomer(int pageNumber, int pageSize, DetachedCriteria criteria);

	/**
	 * 查询客户
	 * 
	 * @param id
	 * @return
	 */
	Customer findCustomerById(Long id);

	/**
	 * 修改用户
	 * 
	 * @param customer
	 */
	void updateCustomer(Customer customer);

	/**
	 * 删除客户
	 * 
	 * @param customer
	 */
	void deleteCustomer(Customer customer);

	/**
	 * 查询所有客户
	 * 
	 * @return
	 */
	List<Customer> findCustomer();

}
