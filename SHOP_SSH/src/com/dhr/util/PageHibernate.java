package com.dhr.util;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

/**
 * 实现分页数据
 * @author Mr DU
 * @param <T>
 *
 */
public class PageHibernate<T> implements HibernateCallback<List<T>>{

	private String hql;
	private int startIndex;
	private int pageSize;
	private Object[] paramers;
	
	public PageHibernate(String hql, int startIndex, int pageSize, Object ...paramers) {
		super();
		this.hql = hql;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
		this.paramers = paramers;
	}


	@Override
	public List<T> doInHibernate(Session session) throws HibernateException {
		Query query = session.createQuery(hql);
		//设置参数
		if(this.paramers != null) {
			for(int i=0;i<paramers.length;i++) {
				query.setParameter(i, paramers[i]);
			}
		}
		//设置起始值
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}

}
