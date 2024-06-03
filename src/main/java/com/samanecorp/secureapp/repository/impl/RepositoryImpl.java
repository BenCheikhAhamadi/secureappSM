
package com.samanecorp.secureapp.repository.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.samanecorp.secureapp.config.HibernateUtil;
import com.samanecorp.secureapp.repository.imterface.Repository;

public class RepositoryImpl<T> implements Repository<T>{
	
	private Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = null;

	@Override
	public boolean add(T t) {
		try {
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
		
		
	}

	@Override
	public boolean delete(long id, T t) {
		try {
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public boolean update(T t) {
		try {
			transaction = session.beginTransaction();
			session.save(t);
			transaction.commit();
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(T t) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<T> cq = (CriteriaQuery<T>)cb.createQuery(t.getClass());
		Root<T> root = (Root<T>) cq.from(t.getClass());
		cq.select(root);
		return session.createQuery(cq).getResultList();
	}

	@Override
	public T get(long id, T t) {
		
		return (T) session.get(t.getClass(), id);
	}

	

}

