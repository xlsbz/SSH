package com.dhr.action;

import org.apache.struts2.ServletActionContext;

import com.dhr.domain.Product;
import com.dhr.service.IProductService;
import com.dhr.util.Cart;
import com.dhr.util.CartItem;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车Action
 * @author Mr DU
 *
 */
public class CartAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	//获取页面数据
	private int count;
	private int pid;
	public void setCount(int count) {
		this.count = count;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

	//注入productService
	private IProductService productService;
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * 添加到购物车
	 * @return
	 */
	public String cartInfo() {
		//1.从session中获取购物车
		Cart cart = getCart();
		//2.封装购物项数据
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		//根据商品ID查询商品封装到购物项
		Product product = productService.findById(pid);
		cartItem.setProduct(product);
		//3.把购物项封装到购物车里
		cart.addCart(cartItem);
		return "cartInfo";
	}
	
	/**
	 * 移除商品
	 * @return
	 */
	public String removeCart() {
		Cart cart = getCart();
		cart.removeCart(pid);
		return "cartInfo";
	}
	
	/**
	 * 清空购物车
	 * @return
	 */
	public String clearCart() {
		Cart cart = this.getCart();
		cart.clearCart();
		return "cartInfo";
	}
	
	/**
	 * 获取购物车
	 * @return
	 */
	public Cart getCart() {
		//如果session中有就从session里获取
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart!=null) {
			return cart;
		}else {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	/**
	 *我的购物车 
	 * @return
	 */
	public String myCart() {
		return "cartInfo";
	}
}
