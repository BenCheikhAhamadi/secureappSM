
package com.samanecorp.secureapp.repository.impl;

import java.util.List;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Transaction;

import org.hibernate.Session;

import com.samanecorp.secureapp.config.HibernateUtil;
import com.samanecorp.secureapp.entities.AccountUserEntity;
import com.samanecorp.secureapp.repository.imterface.IAccountUserRepository;

public class AccountUserRepositoryImpl  implements IAccountUserRepository {
	
	Transaction transaction = null;
	

	@Override
	public List<AccountUserEntity> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<AccountUserEntity> cr = cb.createQuery(AccountUserEntity.class);
		Root<AccountUserEntity> root = cr.from(AccountUserEntity.class);
		cr.select(root);
		
		List<AccountUserEntity> results = session.createQuery(cr).getResultList();
		session.close();
		return results;
	}
	
	@Override
	public boolean add(AccountUserEntity u) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(u);
			transaction.commit();
			return true;
			
		} catch (Exception e1) {
			return false;
		}
	}
	

	@Override
	public boolean delete(long mat) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(session.get(AccountUserEntity.class,mat));
			transaction.commit();
			return true;
		} catch (Exception e1) {
			return false;
		}
		
	}

	@Override
	public boolean update(AccountUserEntity e) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.merge(e);
			transaction.commit();
			return true;
		} catch (Exception e2) {
			return false;
		}
		
		
	}

	@Override
	public AccountUserEntity get(AccountUserEntity id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		return (AccountUserEntity) session.get(AccountUserEntity.class,id);
	}

	
  
} 
