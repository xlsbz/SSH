package com.dhr.domain;

import java.util.Set;

/**
 * 商品二级分类
 * @author Mr DU
 *
 */
public class CategorySecond {
	private Integer csid;//二级分类ID
	private String csname;//二级分类名称
	
	//二级分类：一级分类  n:1
	//在这里存放个一级分类的对象
	private Category category;

	//存放商品的集合
	private Set<Product> products;
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
