package com.dhr.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dhr.domain.Product;
import com.dhr.util.PageBean;
@Transactional
public interface IProductService {

	/**
	 * 查询热门商品
	 * @return
	 */
	List<Product> finByHotProduct();

	/**
	 * 查询最新商品
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
	 * 分页显示一级分类的商品
	 * @param cid
	 * @return
	 */
	PageBean<Product> findPageByCid(Integer cid, Integer pageNumber);

	/**
	 * 分页二级分类上的商品
	 * @param csid
	 * @param pageNumber
	 * @return
	 */
	PageBean<Product> findBySecondCid(Integer csid, Integer pageNumber);

	/**
	 * 查询所有商品
	 * @param pageNumber 
	 * @return
	 */
	PageBean<Product> findAllProduct(int pageNumber);

	/**
	 * 文件商品上传
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
