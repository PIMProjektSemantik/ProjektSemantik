package de.htwsaarland.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.htwsaarland.model.Base;
import de.htwsaarland.model.Grafikkarte;

/**
 * @author Marc Koster
 * 
 */
public class GrafikkarteDao extends BaseDao implements IDao {

	public GrafikkarteDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Grafikkarte find(Long id) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Grafikkarte fGrafikkarte = getEntityManager()
				.getReference(Grafikkarte.class, id);
		tx.commit();
		return fGrafikkarte;
	}

	@SuppressWarnings("unchecked")
	public List<Grafikkarte> findAll() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createQuery("SELECT N FROM Grafikkarte N");
		tx.commit();
		return query.getResultList();
	}

	public void remove(Base o) {
		Grafikkarte Grafikkarte = (Grafikkarte) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().remove(Grafikkarte);
		tx.commit();
	}

	public void save(Base o) {
		Grafikkarte Grafikkarte = (Grafikkarte) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(Grafikkarte);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Grafikkarte> fireQuery(String querie) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createNativeQuery(querie, Grafikkarte.class);
		tx.commit();
		return query.getResultList();
	}
}
