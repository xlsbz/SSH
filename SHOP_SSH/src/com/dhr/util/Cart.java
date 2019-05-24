package com.dhr.util;
/**
 * 购物车
 * @author Mr DU
 * 存放的是：购物项+总计 
 */

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<Integer, CartItem> map = new LinkedHashMap<>();
	private Double Total = 0.0;
	
	//只需要获取map的value这一单列
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	//总计
	public Double getTotal() {
		return Total;
	}
	/**
	 * 添加到购物车
	 * @param cartItem
	 */
	public void addCart(CartItem cartItem) {
		//1.如果这个商品存在--->数量改变  价格改变 样式不变
		Integer pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)) {
			//获取原有的对象
			CartItem old_cart = map.get(pid);
			//设置新的数量  现在数量=老的数量+新的数量
			old_cart.setCount(old_cart.getCount()+cartItem.getCount());
		}else {
			//2.商品不存在，直接put
			map.put(pid, cartItem);
		}
		//金额
		Total += cartItem.getSubTotal();
	}
	
	/**
	 * 从购物车移除一个商品
	 * @param pid
	 */
	public void removeCart(Integer pid) {
		//获取被商品
		CartItem cartItem = map.remove(pid);
		Total -= cartItem.getSubTotal();
	}
	
	//清空购物车
	public void clearCart() {
		map.clear();
		Total = 0.0;
	}
	
}
