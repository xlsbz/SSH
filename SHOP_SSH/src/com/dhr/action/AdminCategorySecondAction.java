package com.dhr.action;

import java.util.List;

import com.dhr.domain.Category;
import com.dhr.domain.CategorySecond;
import com.dhr.service.ICategorySecondService;
import com.dhr.service.ICategoryService;
import com.dhr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 管理二级分类
 * @author Mr DU
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private static final long serialVersionUID = 1L;
	private CategorySecond categorySecond = new CategorySecond();
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	//注入一级分类
	private ICategoryService categoryService;
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//注入二级分类
	private ICategorySecondService categorySecondService;
	public void setCategorySecondService(ICategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	//获取pageNumber
	private int pageNumber;
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 分页查询所有二级分类
	 * @return
	 */
	public String findAllPage() {
		int pageSize = 8;
		PageBean<CategorySecond> pageBean = categorySecondService.findAllPage(pageNumber,pageSize);
		//存到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllPage";
	}
	
	/**
	 * 去往添加二级分类并且查出一级分类的名称
	 * @return
	 */
	public String toaddCategorySecond() {
		//查询所有一级分类
		List<Category> category = categoryService.findByCategory();
		ActionContext.getContext().getValueStack().set("category", category);
		return "toCategorySecond";
	}
	
	/**
	 * 添加二级分类
	 * @return
	 */
	public String saveCategorySecond() {
		categorySecondService.saveCategorySecond(categorySecond);
		return "addSuccess";
	}
	/**
	 * 删除分类
	 * @return
	 */
	public String delCategorySecond() {
		categorySecondService.delCategorySecond(categorySecond);
		return "delSuccess";
	}
	/**
	 * 去修改页面
	 * @return
	 */
	public String toEdit() {
		//查询所有一级分类
		List<Category> category = categoryService.findByCategory();
		ActionContext.getContext().getValueStack().set("category", category);
		categorySecond = categorySecondService.findById(categorySecond);
		return "toEdit";
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String updateCategorySecond() {
		categorySecondService.updateCategorySecond(categorySecond);
		return "updateSuccess";
	}
}
