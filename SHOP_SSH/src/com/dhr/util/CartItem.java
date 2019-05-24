package com.dhr.util;
/**
 * 购物项：商品，小计，数量
 * @author Mr DU
 *
 */

import com.dhr.domain.Product;

public class CartItem {
	private Product product;
	private Integer count;
	private Double subTotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubTotal() {
		return product.getShop_price()*count;
	}
//	public void setSubTotal(Double subTotal) {
//		this.subTotal = subTotal;
//	}
	
	
}
