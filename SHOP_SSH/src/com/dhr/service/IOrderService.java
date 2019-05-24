package com.dhr.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.domain.User;
import com.dhr.util.PageBean;

/**
 * 业务接口
 * @author Mr DU
 *
 */
@Transactional
public interface IOrderService {

	/**
	 * 保存到订单
	 * 
	 * @param order
	 */
	void saveOrder(Order order);
	/**
	 * 分页查询我的订单
	 * @param user
	 * @return
	 */
	PageBean<Order> findByPageOrder(User user,int pageNumber);
	/**
	 * ID查看订单
	 * @param oid
	 */
	Order payMendOrder(Integer oid);
	/**
	 * 更新订单
	 * @param order
	 */
	void updateOrder(Order order);
	/**
	 * 查询所有订单
	 * @param pageNumber 
	 * @return
	 */
	PageBean<Order> findAllOrder(int pageNumber);
	/**
	 * 查询订单项
	 * @param oid
	 * @return
	 */
	List<OrderItem> findByOrderId(Integer oid);
	/**
	 * 分页查看订单状态
	 * @param pageNumber
	 * @param noPay
	 * @return
	 */
	PageBean<Order> findStateOrder(int pageNumber, Integer state);

}
