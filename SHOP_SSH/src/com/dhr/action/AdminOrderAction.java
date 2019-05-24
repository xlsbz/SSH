package com.dhr.action;

import java.util.List;

import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.service.IOrderService;
import com.dhr.util.Constant;
import com.dhr.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 管理员管理订单模块
 * @author Mr DU
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	private static final long serialVersionUID = 1L;
	private Order order = new Order();
	@Override
	public Order getModel() {
		return order;
	}

	//注入订单service  接收页码
	private IOrderService orderService;
	private int pageNumber;
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 分页查询所有订单
	 * @return
	 */
	public String findAllOrder() {
		PageBean<Order> pageBean = orderService.findAllOrder(pageNumber);
		//保存到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "pageOrder";
	}
	
	/**
	 * 异步查询订单项
	 * @return
	 */
	public String findOrderItems() {
		List<OrderItem> orderItems = orderService.findByOrderId(order.getOid());
		//保存到值栈
		ActionContext.getContext().getValueStack().set("orderItems", orderItems);
		return "findOrderItems";
	}
	
	/**
	 * 去发货
	 * @return
	 */
	public String goGoods() {
		//查询订单ID，修改订单状态
		order = orderService.payMendOrder(order.getOid());
		order.setState(3);
		orderService.updateOrder(order);
		return "goGoods";
	}
	
	/**
	 * 查看未付款
	 * @return
	 */
	public String noPay() {
		PageBean<Order> pageBean = orderService.findStateOrder(pageNumber,Constant.NO_Pay);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "nopay";
	}
	/**
	 * 查看已付款
	 * @return
	 */
	public String yesPay() {
		PageBean<Order> pageBean = orderService.findStateOrder(pageNumber,Constant.YES_Pay);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "yespay";
	}
	/**
	 * 查看已发货
	 * @return
	 */
	public String sendGoods() {
		PageBean<Order> pageBean = orderService.findStateOrder(pageNumber,Constant.FOR_Goods);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "sendGoods";
	}
	/**
	 * 查看已完成
	 * @return
	 */
	public String orderSuccess() {
		PageBean<Order> pageBean = orderService.findStateOrder(pageNumber,Constant.SUCCESS);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "orderSuccess";
	}
}
