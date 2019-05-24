package com.dhr.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.dhr.dao.LinkManDao;
import com.dhr.domain.Customer;
import com.dhr.domain.LinkMan;
import com.dhr.service.LinkManService;
import com.dhr.util.PageBean;

/**
 * @author Mr DU
 *
 */
@Transactional
public class LinkManServiceImpl implements LinkManService {
	// 注入dao
	private LinkManDao linkManDao;

	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}

	/**
	 * @param criteria
	 * @param pageNumber
	 * @param pageSzie
	 * @return
	 */
	@Override
	public PageBean<LinkMan> findAllLinkMan(DetachedCriteria criteria, int pageNumber, int pageSize) {
		PageBean<LinkMan> bean = new PageBean<>(pageNumber, pageSize);
		// 补全pageBean属性
		int count = linkManDao.findRecords(criteria);
		bean.setTotalRecords(count);
		List<LinkMan> linkMans = linkManDao.findListPage(criteria, bean);
		bean.setList(linkMans);
		return bean;
	}

	/**
	 * @return
	 */
	@Override
	public List<Customer> findAllCustomer() {
		return linkManDao.findAllCustomer();
	}

	/**
	 * @param linkman
	 */
	@Override
	public void saveLinkMan(LinkMan linkman) {
		linkManDao.save(linkman);
	}

	/**
	 * @param lkm_id
	 * @return
	 */
	@Override
	public LinkMan findLinkManById(Integer lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	/**
	 * @param linkman
	 */
	@Override
	public void updateLinkMan(LinkMan linkman) {

		linkManDao.update(linkman);
	}

	/**
	 * @param man
	 */
	@Override
	public void deleteLinkMan(LinkMan man) {
		linkManDao.delete(man);
	}

}
