package de.htwsaarland.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.htwsaarland.model.Arbeitsspeicher;
import de.htwsaarland.model.Base;

/**
 * @author Marc Koster
 * 
 */
public class ArbeitsspeicherDao extends BaseDao implements IDao {

	public ArbeitsspeicherDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Arbeitsspeicher find(Long id) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Arbeitsspeicher fArbeitsspeicher = getEntityManager()
				.getReference(Arbeitsspeicher.class, id);
		tx.commit();
		return fArbeitsspeicher;
	}

	@SuppressWarnings("unchecked")
	public List<Arbeitsspeicher> findAll() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createQuery("SELECT N FROM Arbeitsspeicher N");
		tx.commit();
		return query.getResultList();
	}

	public void remove(Base o) {
		Arbeitsspeicher Arbeitsspeicher = (Arbeitsspeicher) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().remove(Arbeitsspeicher);
		tx.commit();
	}

	public void save(Base o) {
		Arbeitsspeicher Arbeitsspeicher = (Arbeitsspeicher) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(Arbeitsspeicher);
		tx.commit();
	}

}
