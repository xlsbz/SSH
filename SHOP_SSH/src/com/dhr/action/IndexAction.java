package com.dhr.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.dhr.domain.Category;
import com.dhr.domain.Product;
import com.dhr.service.ICategoryService;
import com.dhr.service.IProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 跳往主页的Action
 * @author Mr DU
 *
 */

public class IndexAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	//注入分类业务层接口
	private ICategoryService categoryService;
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
	//注入商品业务层接口
	private IProductService productService;
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	/**
	 * 默认执行方法
	 */
	@Override
	public String execute() throws Exception {
		//同步加载首页导航,热门商品,最新商品,猜你喜欢
		List<Category> cate = categoryService.findByCategory();
		List<Product> productHots = productService.finByHotProduct();
		List<Product> productNews = productService.findByNewProduct();
		List<Product> productLikes = productService.findByLikeProduct();
		//设置到session
		ServletActionContext.getRequest().getSession().setAttribute("category", cate);
		//设置到值栈
		ActionContext.getContext().getValueStack().set("productHots", productHots);
		ActionContext.getContext().getValueStack().set("productNews", productNews);
		ActionContext.getContext().getValueStack().set("productLikes", productLikes);
		return "index";
	}

}
