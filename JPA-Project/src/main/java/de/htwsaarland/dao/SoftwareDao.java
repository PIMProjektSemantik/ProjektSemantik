package de.htwsaarland.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.htwsaarland.model.Software;
import de.htwsaarland.model.Base;
import de.htwsaarland.model.Software;

/**
 * @author Marc Koster
 * 
 */
public class SoftwareDao extends BaseDao implements IDao {

	public SoftwareDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Software find(Long id) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Software fSoftware = getEntityManager()
				.getReference(Software.class, id);
		tx.commit();
		return fSoftware;
	}

	@SuppressWarnings("unchecked")
	public List<Software> findAll() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createQuery("SELECT N FROM Software N");
		tx.commit();
		return query.getResultList();
	}

	public void remove(Base o) {
		Software Software = (Software) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().remove(Software);
		tx.commit();
	}

	public void save(Base o) {
		Software Software = (Software) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(Software);
		tx.commit();
	}
	@SuppressWarnings("unchecked")
	public List<Software> fireQuery(String querie) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createNativeQuery(querie, Software.class);
		tx.commit();
		return query.getResultList();
	}

}
