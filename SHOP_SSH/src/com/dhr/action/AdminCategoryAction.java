package com.dhr.action;

import java.util.List;

import com.dhr.domain.Category;
import com.dhr.service.ICategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 管理员管理一级分类
 * @author Mr DU
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private static final long serialVersionUID = 1L;
	private Category category = new Category();
	@Override
	public Category getModel() {
		return category;
	}

	//注入service
	private ICategoryService categoryService;
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	/**
	 * 查询一级分类
	 * @return
	 */
	public String findAllCategory() {
		List<Category> categorys = categoryService.findByCategory();
		ActionContext.getContext().getValueStack().set("categorys", categorys);
		return "findAllCategory";
	}
	/**
	 * 添加一级分类
	 * @return
	 */
	public String saveCategory() {
		categoryService.saveCategory(category);
		return "saveSuccess";
	}
	/**
	 * 删除一级分类
	 * @return
	 */
	public String delCategory() {
		//级联删除，先查再删
		category = categoryService.findByCategoryID(category.getCid());
		categoryService.delCategory(category);
		return "delSuccess";
	}
	/**
	 * 去修改页面
	 * @return
	 */
	public String toEdit() {
		category = categoryService.findByCategoryID(category.getCid());
		return "toedit";
	}
	/**
	 * 修改分类
	 * @return
	 */
	public String updateCategory() {
		categoryService.updateCategory(category);
		return "updateCategory";
	}
}
