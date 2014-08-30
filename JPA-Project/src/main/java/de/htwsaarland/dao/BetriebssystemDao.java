package de.htwsaarland.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.htwsaarland.model.Base;
import de.htwsaarland.model.Betriebssystem;

/**
 * @author Marc Koster
 * 
 */
public class BetriebssystemDao extends BaseDao implements IDao {

	public BetriebssystemDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Betriebssystem find(Long id) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Betriebssystem fBetriebssystem = getEntityManager()
				.getReference(Betriebssystem.class, id);
		tx.commit();
		return fBetriebssystem;
	}

	@SuppressWarnings("unchecked")
	public List<Betriebssystem> findAll() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createQuery("SELECT N FROM Betriebssystem N");
		tx.commit();
		return query.getResultList();
	}

	public void remove(Base o) {
		Betriebssystem betriebssystem = (Betriebssystem) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().remove(betriebssystem);
		tx.commit();
	}

	public void save(Base o) {
		Betriebssystem betriebssystem = (Betriebssystem) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(betriebssystem);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Betriebssystem> fireQuery(String querie) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createNativeQuery(querie, Betriebssystem.class);
		tx.commit();
		return query.getResultList();
	}
}
