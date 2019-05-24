package com.dhr.service;

import org.hibernate.criterion.DetachedCriteria;

import com.dhr.domain.SaleVisit;
import com.dhr.util.PageBean;

/**
 * @author Mr DU
 *
 */
public interface SaleVisitService {

	/**
	 * 分页查询
	 * 
	 * @param criteria
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	PageBean<SaleVisit> findAllSaleVisit(DetachedCriteria criteria, int pageNumber, int pageSize);

	/**
	 * 保存
	 * 
	 * @param saleVisit
	 */
	void saveSale(SaleVisit saleVisit);

	/**
	 * 查询
	 * 
	 * @param visit_id
	 * @return
	 */
	SaleVisit findById(String visit_id);

	/**
	 * 删除
	 * 
	 * @param s
	 */
	void deleteSale(SaleVisit s);

}
