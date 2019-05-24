package com.dhr.dao;

import java.util.List;

import com.dhr.domain.Product;
import com.dhr.util.PageBean;

public interface IProductDao {

	/**
	 * 查询热门
	 * @return
	 */
	List<Product> findByHotProduct();

	/**
	 * 最新商品
	 * @return
	 */
	List<Product> findByNewProduct();

	/**
	 * 猜你喜欢
	 * @return
	 */
	List<Product> findByLikeProduct();

	/**
	 * ID查询
	 * @param pid
	 * @return
	 */
	Product findById(Integer pid);

	/**
	 * 查询获取总记录
	 * @return
	 */
	int findByTotal(Integer cid);

	/**
	 * 一级分类每页数据
	 * @param bean
	 * @param cid
	 * @return
	 */
	List<Product> findByPage(PageBean<Product> bean, Integer cid);

	/**
	 * 二级分类每页数据
	 * @param bean
	 * @param csid
	 * @return
	 */
	int findBySecondRecord(Integer csid);

	/**
	 * 每页具体数据
	 * @param bean
	 * @param csid
	 * @return
	 */
	List<Product> findBySecondProduct(PageBean<Product> bean, Integer csid);

	/**
	 * 查询总记录
	 * @return
	 */
	int findAllRecords();

	/**
	 * 封装所有商品
	 * @param bean
	 * @return
	 */
	List<Product> findAllProduct(PageBean<Product> bean);

	/**
	 * 文件上传
	 * @param product
	 */
	void uploadProduct(Product product);

	/**
	 * 文件修改
	 * @param product
	 */
	void updateProduct(Product product);

	/**
	 * 删除商品
	 * @param pid
	 */
	void delProduct(Product product);

}
