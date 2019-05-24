package com.dhr.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.IOrderDao;
import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.domain.User;
import com.dhr.util.PageBean;
import com.dhr.util.PageHibernate;
/**
 * 订单管理Dao
 * @author Mr DU
 *
 */
public class OrderDaoImpl extends HibernateDaoSupport implements IOrderDao {

	@Override
	/**
	 * 保存到订单
	 */
	public void saveOrder(Order order) {
		this.getHibernateTemplate().save(order);
	}

	@Override
	/**
	 * 订单个数
	 * @param user
	 * @return
	 */
	public int findOrderRecord(User user) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> c = (List<Long>) this.getHibernateTemplate().find(hql, user.getUid());
		if(c.size()>0&&c!=null) {
			return c.get(0).intValue();
		}
		return 0;
	}

	@Override
	/**
	 * 订单数据
	 * @param bean
	 * @param user
	 * @return
	 */
	public List<Order> findOrderDate(PageBean<Order> bean, User user) {
		String hql = "select o from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> orders = this.getHibernateTemplate().execute(new PageHibernate<>(hql,
				bean.getStartIndex(), bean.getPageSize(), user.getUid()));
		return orders;
	}

	@Override
	public Order payMendOrder(Integer oid) {
		String hql = "from Order o where o.oid = ?";
		Order order = this.getHibernateTemplate().get(Order.class, oid);
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		this.getHibernateTemplate().update(order);
	}

	@Override
	public int findAllOrder() {
		String hql = "select count(*) from Order";
		List<Long> count = (List<Long>) this.getHibernateTemplate().find(hql);
		if(count!=null&&count.size()>0) {
			return count.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Order> findAllOrder(PageBean<Order> bean) {
		String hql = "from Order order by ordertime desc";
		List<Order> orders = this.getHibernateTemplate().execute(new PageHibernate<>(hql, bean.getStartIndex(), bean.getPageSize(), null));
		return orders;
	}

	@Override
	public List<OrderItem> findByOrderId(Integer oid) {
		String hql = "select oi from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> orderitems = (List<OrderItem>) this.getHibernateTemplate().find(hql, oid);
		return orderitems;
	}

	@Override
	public int findStateOrder(Integer state) {
		String hql = "select count(*) from Order where state=?";
		List<Long> count = (List<Long>) this.getHibernateTemplate().find(hql, state);
		if(count.size()>0&&count!=null) {
			return count.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Order> findStateOrder(PageBean<Order> bean, Integer state) {
		String hql = "select o from Order o where o.state = ? order by ordertime desc";
		List<Order> orders = this.getHibernateTemplate().execute(new PageHibernate<>(hql, bean.getStartIndex(), bean.getPageSize(), new Object[] {state}));
		return orders;
	}

}
