
package com.samanecorp.secureapp.repository.impl;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.secureapp.config.HibernateUtil;
import com.samanecorp.secureapp.entities.AccountUserEntity;

public class LoginRepository {
	Logger logger = LoggerFactory.getLogger(LoginRepository.class);
	
	public Optional <AccountUserEntity> log(String email,String password){
		AccountUserEntity result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<AccountUserEntity> cr = cb.createQuery(AccountUserEntity.class);
		Root<AccountUserEntity> user= cr.from(AccountUserEntity.class);
		
		//Utilisation des predicats pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"),email);
		Predicate predicatePwd = cb.equal(user.get("password"),password);
		Predicate predicateFinal = cb.and(predicateEmail,predicatePwd);
		
		cr.select(user);
		cr.where(predicateFinal);
		try {
			result = session.createQuery(cr).getSingleResult();
			logger.info("Connexion ok");
		} catch (Exception e) {
			logger.error("Error",e);
		} finally {
			session.close();
		}
		
		return Optional.ofNullable(result);
	}
	
	public Optional<AccountUserEntity>logException(String email,String pwd){
		AccountUserEntity result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<AccountUserEntity> cr = cb.createQuery(AccountUserEntity.class);
		Root<AccountUserEntity> user = cr.from(AccountUserEntity.class);
		
		// Predicat pour la clause Where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), pwd);
		Predicate predicateFinal = cb.and(predicateEmail,predicatePwd);
		cr.select(user);
		cr.where(predicateFinal);
		
		return Optional.ofNullable(session.createQuery(cr).getSingleResult());
	}
	
	

	public Optional<AccountUserEntity>login(String email, String password){
		if(email.equals("bensamanecorporation.com")&& password.equals("passer@123")) {
			AccountUserEntity accountUserEntity =new AccountUserEntity();
			accountUserEntity.setEmail(email);
			accountUserEntity.setPassword(password);
			return Optional.of(accountUserEntity);
		}else {
			return Optional.ofNullable(null);
		}
		
		
	}
	
	
}
