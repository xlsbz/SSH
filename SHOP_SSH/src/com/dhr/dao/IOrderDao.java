package com.dhr.dao;

import java.util.List;

import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.domain.User;
import com.dhr.util.PageBean;

/**
 * 订单接口
 * @author Mr DU
 *
 */
public interface IOrderDao {

	/**
	 * 保存到订单
	 * @param order
	 */
	void saveOrder(Order order);
	/**
	 * 订单个数
	 * @param user
	 * @return
	 */
	int findOrderRecord(User user);

	/**
	 * 订单数据
	 * @param bean
	 * @param user
	 * @return
	 */
	List<Order> findOrderDate(PageBean<Order> bean, User user);
	/**
	 * ID查询订单
	 * @param oid
	 */
	Order payMendOrder(Integer oid);
	/**
	 * 更新订单
	 * @param order
	 */
	void updateOrder(Order order);
	/**
	 * 总记录
	 * @return
	 */
	int findAllOrder();
	/**
	 * 总集合
	 * @param bean
	 * @return
	 */
	List<Order> findAllOrder(PageBean<Order> bean);
	/**
	 * 查询订单项
	 * @param oid
	 * @return
	 */
	List<OrderItem> findByOrderId(Integer oid);
	/**
	 * 查询各个状态的订单个数
	 * @param state
	 * @return
	 */
	int findStateOrder(Integer state);
	/**
	 * 查询各个订单的集合
	 * @param bean
	 * @param state
	 * @return
	 */
	List<Order> findStateOrder(PageBean<Order> bean, Integer state);

}
