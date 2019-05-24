package com.dhr.dao.impl;

import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.dao.IProductDao;
import com.dhr.domain.Product;
import com.dhr.util.Constant;
import com.dhr.util.PageBean;
import com.dhr.util.PageHibernate;
/**
 * 商品管理Dao
 * @author Mr DU
 *
 */
public class ProductDaoImpl extends HibernateDaoSupport implements IProductDao {

	@Override
	/**
	 * 热门商品
	 */
	public List<Product> findByHotProduct() {
		// 使用离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", Constant.IS_HOT));
		// 排序
		criteria.addOrder(Order.desc("pdate"));
		List<Product> products = (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return products;
	}

	@Override
	/**
	 * 最新商品
	 */
	public List<Product> findByNewProduct() {
		// 创建离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 时间排序
		criteria.addOrder(Order.desc("pdate"));
		List<Product> products = (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return products;
	}

	@Override
	/**
	 * 猜你喜欢
	 */
	public List<Product> findByLikeProduct() {

		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		List<Product> products = (List<Product>) this.getHibernateTemplate().findByCriteria(criteria,
				new Random().nextInt(10), new Random().nextInt(40));
		return products;
	}

	@Override
	/**
	 * 商品详情
	 */
	public Product findById(Integer pid) {
		Product product = this.getHibernateTemplate().get(Product.class, pid);
		return product;
	}

	@Override
	/**
	 * 某个一级分类下的商品个数
	 */
	public int findByTotal(Integer cid) {
		int count = 0;
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> find = (List<Long>) this.getHibernateTemplate().find(hql, cid);
		if (find.size() > 0 && find != null) {
			count = find.get(0).intValue();
		}
		return count;
	}

	@Override
	/**
	 * 二级分类的数据
	 */
	public List<Product> findByPage(PageBean<Product> bean, Integer cid) {
		// SELECT p.* FROM product p,categorysecond cs,category c WHERE p.csid=cs.csid
		// AND cs.cid = c.cid AND c.cid = 1
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> products = this.getHibernateTemplate().execute(new HibernateCallback<List<Product>>() {

			@Override
			public List<Product> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				// 设置条件
				query.setParameter(0, cid);
				// 设置起使结束数据
				query.setFirstResult(bean.getStartIndex());
				query.setMaxResults(bean.getPageSize());
				return query.list();
			}
		});
		return products;
	}

	@Override
	/**
	 * 二级分类的商品
	 */
	public int findBySecondRecord(Integer csid) {
		//SELECT COUNT(*) FROM product p,categorysecond cs WHERE p.`csid` = cs.`csid` AND cs.csid=1
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> c = (List<Long>) this.getHibernateTemplate().find(hql, csid);
		if(c.get(0)!=null && c.size()>0) {
			return c.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Product> findBySecondProduct(PageBean<Product> bean, Integer csid) {
		// SELECT p.* FROM product p,categorysecond cs WHERE p.`csid` = cs.`csid` AND
		// cs.csid = 2;
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		Object[] parameters = { csid };
		List<Product> products = this.getHibernateTemplate()
				.execute(new PageHibernate<>(hql, bean.getStartIndex(), bean.getPageSize(), parameters));
		return products;
	}

	@Override
	public int findAllRecords() {
		String hql = "select count(*) from Product";
		List<Long> count = (List<Long>) this.getHibernateTemplate().find(hql);
		if(count!=null&&count.size()>0) {
			return count.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Product> findAllProduct(PageBean<Product> bean) {
		String hql = "from Product order by pid desc";
		List<Product> products = 
				this.getHibernateTemplate().execute(new PageHibernate<>(hql, bean.getStartIndex(), bean.getPageSize(), null));
		return products;
	}

	@Override
	public void uploadProduct(Product product) {
		this.getHibernateTemplate().save(product);
	}

	@Override
	public void updateProduct(Product product) {
		this.getHibernateTemplate().update(product);
	}

	@Override
	public void delProduct(Product product) {
		this.getHibernateTemplate().delete(product);
	}

}
