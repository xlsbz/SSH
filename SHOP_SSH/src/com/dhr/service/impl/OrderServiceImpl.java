package com.dhr.service.impl;

import java.util.List;

import com.dhr.dao.IOrderDao;
import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.domain.User;
import com.dhr.service.IOrderService;
import com.dhr.util.PageBean;

public class OrderServiceImpl implements IOrderService {
	// 注入Dao
	private IOrderDao orderDao;

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	@Override
	public PageBean<Order> findByPageOrder(User user, int pageNumber) {
		// 封装分页数据
		PageBean<Order> bean = new PageBean<>(pageNumber, 5);
		// 查询封装订单总个数
		int count = orderDao.findOrderRecord(user);
		bean.setTotalRecord(count);
		// 查询封装订单数据
		List<Order> orders = orderDao.findOrderDate(bean, user);
		bean.setList(orders);
		return bean;

	}

	@Override
	public Order payMendOrder(Integer oid) {
		return orderDao.payMendOrder(oid);
	}

	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}

	@Override
	public PageBean<Order> findAllOrder(int pageNumber) {
		// 封装bean
		PageBean<Order> bean = new PageBean<>(pageNumber, 8);
		// 查询总个数
		int count = orderDao.findAllOrder();
		bean.setTotalRecord(count);
		// 总集合
		List<Order> orders = orderDao.findAllOrder(bean);
		bean.setList(orders);
		return bean;
	}

	@Override
	public List<OrderItem> findByOrderId(Integer oid) {
		return orderDao.findByOrderId(oid);
	}

	@Override
	public PageBean<Order> findStateOrder(int pageNumber, Integer state) {
		// 封装bean
		PageBean<Order> bean = new PageBean<>(pageNumber, 4);
		// 查询总个数
		int count = orderDao.findStateOrder(state);
		bean.setTotalRecord(count);
		// 总集合
		List<Order> orders = orderDao.findStateOrder(bean,state);
		bean.setList(orders);
		return bean;
	}
}
