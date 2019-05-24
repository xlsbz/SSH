package com.dhr.service.impl;

import java.util.List;

import com.dhr.dao.IProductDao;
import com.dhr.domain.Product;
import com.dhr.service.IProductService;
import com.dhr.util.Constant;
import com.dhr.util.PageBean;

public class ProductServiceImpl implements IProductService {

	//注入dao
	private IProductDao productDao;
	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}


	@Override
	public List<Product> finByHotProduct() {
		return productDao.findByHotProduct();
	}


	@Override
	public List<Product> findByNewProduct() {
		return productDao.findByNewProduct();
	}


	@Override
	public List<Product> findByLikeProduct() {
		return productDao.findByLikeProduct();
	}


	@Override
	public Product findById(Integer pid) {
		return productDao.findById(pid);
	}


	@Override
	public PageBean<Product> findPageByCid(Integer cid, Integer pageNumber) {
		//封装pageBean的数据
		PageBean<Product> bean = new PageBean<>(pageNumber, Constant.Page_Size);
		//查询获取总记录数--->封装到pagebean
		int count = productDao.findByTotal(cid);
		bean.setTotalRecord(count);
		//查询每页的数据---->封装到pagebean
		List<Product> pList = productDao.findByPage(bean,cid);
		bean.setList(pList);
		return bean;
	}


	@Override
	public PageBean<Product> findBySecondCid(Integer csid, Integer pageNumber) {
		//封装pageBean数据
		PageBean<Product> bean = new PageBean<>(pageNumber, Constant.Page_Size);
		//查询总记录封装
		int count = productDao.findBySecondRecord(csid);
		bean.setTotalRecord(count);
		//查询商品封装
		List<Product> products = productDao.findBySecondProduct(bean,csid);
		bean.setList(products);
		return bean;
	}


	@Override
	public PageBean<Product> findAllProduct(int pageNumber) {
		//封装参数
		PageBean<Product> bean = new PageBean<>(pageNumber, 8);
		//查询总记录
		int count = productDao.findAllRecords();
		bean.setTotalRecord(count);
		//封装集合
		List<Product> products = productDao.findAllProduct(bean);
		bean.setList(products);
		return bean;
	}


	@Override
	public void uploadProduct(Product product) {
		productDao.uploadProduct(product);
	}


	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}


	@Override
	public void delProduct(Product product) {
		productDao.delProduct(product);
	}



}
