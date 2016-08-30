package com.jianglibo.vaadin.dashboard.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.jianglibo.vaadin.dashboard.domain.InstallStepDefine;;

public class InstallStepRepositoryImpl implements InstallStepRepositoryCustom<InstallStepDefine> {
	
	private final EntityManager em;
	
	@Autowired
	public InstallStepRepositoryImpl(EntityManager em) {
		this.em = em;
	}


	@Override
	public List<InstallStepDefine> getFilteredPage(Pageable page, String filterString, boolean trashed) {
		String jpql;
		if (Strings.isNullOrEmpty(filterString)) {
			jpql = "SELECT s FROM InstallStep AS s";
		} else {
			jpql = "SELECT s FROM InstallStep AS s WHERE s.name LIKE :name";
		}
		 
		TypedQuery<InstallStepDefine> q =  em.createQuery(jpql, InstallStepDefine.class);
		q.setFirstResult(page.getOffset());
		q.setMaxResults(page.getPageSize());
		
		if (!Strings.isNullOrEmpty(filterString)) {
			q.setParameter("name", RepositoryUtil.roundLike(filterString));
		} 
		List<InstallStepDefine> results = q.getResultList();
		
		return results;
	}


	@Override
	public long getFilteredNumber(String filterString, boolean trashed) {
		String jpql;
		if (Strings.isNullOrEmpty(filterString)) {
			jpql = "SELECT COUNT(DISTINCT s) FROM InstallStep AS s";
		} else {
			jpql = "SELECT COUNT(DISTINCT s) FROM InstallStep AS s WHERE s.name LIKE :name";
		}
		 
		TypedQuery<Long> q =  em.createQuery(jpql, Long.class);
		
		if (!Strings.isNullOrEmpty(filterString)) {
			q.setParameter("name", RepositoryUtil.roundLike(filterString));
		} 
		Long result = q.getSingleResult();
		return result;
	}

}