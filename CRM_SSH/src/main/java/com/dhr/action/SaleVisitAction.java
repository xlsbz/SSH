package com.dhr.action;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dhr.domain.SaleVisit;
import com.dhr.service.SaleVisitService;
import com.dhr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Mr DU 客户拜访action
 */
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit = new SaleVisit();

	/**
	 * @return
	 */
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	// 注入service
	private SaleVisitService saleVisitService;

	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}

	// 分页参数
	private int pageNumber = 1;
	private int pageSize = 3;

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 查询条件
	private Date visit_end_time;

	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	public Date getVisit_end_time() {
		return visit_end_time;
	}

	/**
	 * 查询所有客户拜访记录
	 * 
	 * @return
	 */
	public String findAllSale() {
		// 创建离线查询条件
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		// 判断条件
		if (saleVisit.getVisit_time() != null && !"".equals(saleVisit.getVisit_time())) {
			criteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if (visit_end_time != null && !"".equals(visit_end_time)) {
			criteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findAllSaleVisit(criteria, pageNumber, pageSize);
		// 设置到值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAllSale";
	}

	/**
	 * 去保存来访记录页面
	 * 
	 * @return
	 */
	public String saveUI() {
		// 异步加载客户和用户
		return "toSave";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		saleVisitService.saveSale(saleVisit);
		return "saveSuccess";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		SaleVisit s = saleVisitService.findById(saleVisit.getVisit_id());
		saleVisitService.deleteSale(s);
		return "deleteSuccess";
	}
}
