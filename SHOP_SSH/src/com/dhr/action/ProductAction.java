package com.dhr.action;

import com.dhr.domain.Product;
import com.dhr.service.IProductService;
import com.dhr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品处理
 * @author Mr DU
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private static final long serialVersionUID = 1L;
	//模型驱动
	private Product product = new Product();
	@Override
	public Product getModel() {
		return product;
	}
	
	
	//接收回显一级分类cid
	private Integer cid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//接收回显二级分类csid
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}


	//接收第几页
	private Integer pageNumber;
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}


	//注入业务层
	private IProductService productService;
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	/**
	 * 商品详情
	 * @return
	 */
	public String info() {
		//获取到pid
		Integer pid = product.getPid();
		product = productService.findById(pid);
		return "info";
	}
	
	/**
	 * 一级分类下的分页显示商品
	 * @return
	 */
	public String findByCid() {
		//获取页面的第几页
		PageBean<Product> pageBean = productService.findPageByCid(cid,pageNumber);
		//把分页bean存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "pageInfo";
	}
	
	/**
	 * 二级分类下分页显示商品
	 * @return
	 */
	public String findBySecondCid() {
		//获取基本数据
		PageBean<Product> products = productService.findBySecondCid(csid,pageNumber);
		ActionContext.getContext().getValueStack().set("pageBean", products);
		return "pageInfo";
	}
}
