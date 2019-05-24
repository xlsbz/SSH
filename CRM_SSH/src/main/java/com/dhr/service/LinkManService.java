package com.dhr.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.dhr.domain.Customer;
import com.dhr.domain.LinkMan;
import com.dhr.util.PageBean;

/**
 * @author Mr DU
 *
 */
public interface LinkManService {

	/**
	 * 分页条件查询
	 * 
	 * @param criteria
	 * @param pageNumber
	 * @param pageSzie
	 * @return
	 */
	PageBean<LinkMan> findAllLinkMan(DetachedCriteria criteria, int pageNumber, int pageSize);

	/**
	 * 查询所有客户
	 * 
	 * @return
	 */
	List<Customer> findAllCustomer();

	/**
	 * 保存联系人
	 * 
	 * @param linkman
	 */
	void saveLinkMan(LinkMan linkman);

	/**
	 * 去修改页
	 * 
	 * @param lkm_id
	 * @return
	 */
	LinkMan findLinkManById(Integer lkm_id);

	/**
	 * 修改
	 * 
	 * @param linkman
	 */
	void updateLinkMan(LinkMan linkman);

	/**
	 * 删除
	 * 
	 * @param man
	 */
	void deleteLinkMan(LinkMan man);

}
