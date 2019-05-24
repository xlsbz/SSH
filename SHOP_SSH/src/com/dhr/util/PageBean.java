package com.dhr.util;
/**
 * 分页bean
 * @author Mr DU
 *
 */

import java.util.List;

public class PageBean<T> {
	private int totalPage;//总共多少页
	private int pageNumber;//第几页
	private int pageSize;//每页多少数据
	private int totalRecord;//一共多少数据
	private List<T> list;//每页的数据
	
	//从第几条开始
	public int getStartIndex() {
		return (pageNumber-1)*pageSize;
	}
	
	//一共多少页
	public int getTotalPage() {
		return (int) Math.ceil(totalRecord*1.0/pageSize);
	}
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageBean(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public PageBean() {
	}
	
	
}
