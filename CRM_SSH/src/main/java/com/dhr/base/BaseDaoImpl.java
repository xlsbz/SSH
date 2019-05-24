package com.dhr.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dhr.util.PageBean;

/**
 * @author Mr DU
 * @param <T>
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;

	// // 构造方法
	// public BaseDaoImpl(Class clazz) {
	// this.clazz = clazz;
	// }

	// 方案二：反射实现获得调用该通用接口的类型
	public BaseDaoImpl() {
		Class<? extends BaseDaoImpl> claz = this.getClass();// 谁调用它，this就是谁的对象
		Type type = claz.getGenericSuperclass();// 获得它父类的类型,参数化类型 BaseDaoImpl<LinkMan>
		System.out.println(type);
		ParameterizedType parameterizedType = (ParameterizedType) type;// 将type强转为参数化的类型
		Type[] types = parameterizedType.getActualTypeArguments();// 获得实际参数的数组map<string,object>
		this.clazz = (Class) types[0];// 取出LinkMan，customer等实际对象
	}

	/**
	 * @param t
	 */
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	/**
	 * @param t
	 */
	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * @param t
	 */
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	/**
	 * @param id
	 * @return
	 */
	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	/**
	 * @param criteria
	 * @return
	 */
	@Override
	public int findRecords(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> findByCriteria = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if (findByCriteria != null) {
			return findByCriteria.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param criteria
	 * @param bean
	 * @return
	 */
	@Override
	public List<T> findListPage(DetachedCriteria criteria, PageBean<T> bean) {
		criteria.setProjection(null);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, bean.getStartIndex(),
				bean.getPageSize());
		if (list != null) {
			return list;
		}
		return null;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

}
