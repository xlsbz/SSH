package com.dhr.util;

import java.util.List;

/**
 * @author Mr DU 分页bean
 */
public class PageBean<T> {

	private int pageNumber;
	private int pageSize;
	private int totalPage;
	private int totalRecords;
	private List<T> list;

	// 每页开始行
	public int getStartIndex() {
		return (pageNumber - 1) * pageSize;
	}

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

	public int getTotalPage() {
		return (int) Math.ceil(totalRecords * 1.0 / pageSize);
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @param pageNumber
	 * @param pageSize
	 */
	public PageBean(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

}
