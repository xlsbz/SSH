package com.dhr.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.dhr.domain.Customer;
import com.dhr.domain.LinkMan;
import com.dhr.service.LinkManService;
import com.dhr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Mr DU
 *
 */
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkman = new LinkMan();

	/**
	 * @return
	 */
	@Override
	public LinkMan getModel() {
		return linkman;
	}

	// 注入service
	private LinkManService linkManService;

	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}

	// 获取分页参数
	private int pageNumber = 1;
	private int pageSize = 3;

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页查询所有联系人
	 * 
	 * @return
	 */
	public String findAllLinkMan() {
		// 创建离线查询条件
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		// 查询条件
		if (linkman.getLkm_name() != null && !"".equals(linkman.getLkm_name())) {
			criteria.add(Restrictions.like("lkm_name", "%" + linkman.getLkm_name() + "%"));
		}
		if (linkman.getLkm_gender() != null && !"".equals(linkman.getLkm_gender())) {
			criteria.add(Restrictions.eq("lkm_gender", linkman.getLkm_gender()));
		}
		// service查询
		PageBean<LinkMan> pageBean = linkManService.findAllLinkMan(criteria, pageNumber, pageSize);
		// 放到值栈
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAllLinkMan";
	}

	/**
	 * 去保存页面
	 * 
	 * @return
	 */
	public String saveUI() {
		// 查询出所有客户
		List<Customer> customers = linkManService.findAllCustomer();
		// 存到值栈
		ActionContext.getContext().getValueStack().set("list", customers);
		return "toAddLinkMan";
	}

	/**
	 * 保存联系人
	 * 
	 * @return
	 */
	public String save() {
		linkManService.saveLinkMan(linkman);
		return "addSuccess";
	}

	/**
	 * 去修改页
	 * 
	 * @return
	 */
	public String toEdit() {
		// 查询联系人和所有客户回显
		List<Customer> customers = linkManService.findAllCustomer();
		LinkMan linkMan = linkManService.findLinkManById(linkman.getLkm_id());
		// 放到值栈
		ActionContext.getContext().getValueStack().set("list", customers);
		ActionContext.getContext().getValueStack().push(linkMan);
		// 返回视图
		return "toEdit";
	}

	/**
	 * 修改联系人
	 * 
	 * @return
	 */
	public String update() {
		linkManService.updateLinkMan(linkman);
		return "updateSuccess";
	}

	/**
	 * 删除联系人
	 * 
	 * @return
	 */
	public String delete() {
		// 级联删除,先查联系人再删除
		LinkMan man = linkManService.findLinkManById(linkman.getLkm_id());
		linkManService.deleteLinkMan(man);
		return "deleteSuccess";
	}
}
