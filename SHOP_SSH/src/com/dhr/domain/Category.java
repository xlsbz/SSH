package com.dhr.domain;

import java.util.Set;

/**
 * 商品一级分类表
 * @author Mr DU
 *
 */
public class Category {
	private Integer cid;//一级分类ID
	private String cname;//一级分类名
	
	//一级分类:二级分类 1:n
	//存放一个二级分类的集合
	private Set<CategorySecond> categorySecond;
	public Set<CategorySecond> getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(Set<CategorySecond> categorySecond) {
		this.categorySecond = categorySecond;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
