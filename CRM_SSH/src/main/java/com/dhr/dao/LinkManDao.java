package com.dhr.dao;

import java.util.List;

import com.dhr.base.BaseDao;
import com.dhr.domain.Customer;
import com.dhr.domain.LinkMan;

/**
 * @author Mr DU 联系人接口
 */
public interface LinkManDao extends BaseDao<LinkMan> {

	/**
	 * 自定义查询所有客户的接口
	 * 
	 * @return
	 */
	List<Customer> findAllCustomer();

	// 使用通用dao的通用接口方法
	// /**
	// * @param criteria
	// * @return
	// */
	// int findAllRecords(DetachedCriteria criteria);
	//
	// /**
	// * @param criteria
	// * @param bean
	// * @return
	// */
	// List<LinkMan> findAllLinkMan(DetachedCriteria criteria, PageBean<LinkMan>
	// bean);
	//
	// /**
	// * @return
	// */
	// List<Customer> findAllCustomer();
	//
	// /**
	// * @param linkman
	// */
	// void saveLinkMan(LinkMan linkman);
	//
	// /**
	// * @param lkm_id
	// * @return
	// */
	// LinkMan findLinkManById(Integer lkm_id);
	//
	// /**
	// * @param linkman
	// */
	// void updateLinkMan(LinkMan linkman);
	//
	// /**
	// * @param man
	// */
	// void deleteLinkMan(LinkMan man);

}
