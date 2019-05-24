package com.dhr.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dhr.dao.SaleVisitDao;
import com.dhr.domain.SaleVisit;
import com.dhr.service.SaleVisitService;
import com.dhr.util.PageBean;

/**
 * @author Mr DU
 *
 */
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	// 注入dao
	@Autowired
	private SaleVisitDao saleVisitDao;

	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	/**
	 * @param criteria
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageBean<SaleVisit> findAllSaleVisit(DetachedCriteria criteria, int pageNumber, int pageSize) {
		// 封装补全pageBean
		PageBean<SaleVisit> bean = new PageBean<>(pageNumber, pageSize);
		int count = saleVisitDao.findRecords(criteria);
		bean.setTotalRecords(count);
		List<SaleVisit> findListPage = saleVisitDao.findListPage(criteria, bean);
		bean.setList(findListPage);

		return bean;
	}

	/**
	 * @param saleVisit
	 */
	@Override
	public void saveSale(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	/**
	 * @param visit_id
	 * @return
	 */
	@Override
	public SaleVisit findById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	/**
	 * @param s
	 */
	@Override
	public void deleteSale(SaleVisit s) {
		saleVisitDao.delete(s);
	}

}
