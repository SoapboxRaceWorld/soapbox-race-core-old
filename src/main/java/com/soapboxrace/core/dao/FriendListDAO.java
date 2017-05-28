package com.soapboxrace.core.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.soapboxrace.core.dao.util.BaseDAO;
import com.soapboxrace.core.jpa.FriendListEntity;

@Stateless
public class FriendListDAO extends BaseDAO<FriendListEntity> {

	@PersistenceContext
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<FriendListEntity> findByOwnerId(Long ownerId) {
		TypedQuery<FriendListEntity> query = entityManager.createNamedQuery("FriendListEntity.findByOwnerId", FriendListEntity.class);
		query.setParameter("userOwnerId", ownerId);
		return query.getResultList();
	}
	
	public FriendListEntity findByOwnerIdAndFriendPersona(Long ownerId, Long friendPersona) {
		TypedQuery<FriendListEntity> query = entityManager.createNamedQuery("FriendListEntity.findByOwnerIdAndFriendPersona", FriendListEntity.class);
		query.setParameter("userOwnerId", ownerId);
		query.setParameter("personaId", friendPersona);
		return ( query.getResultList() != null && !query.getResultList().isEmpty() ) ? query.getResultList().get(0) : null;
	}

}
