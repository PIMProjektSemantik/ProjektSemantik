package de.htwsaarland.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.htwsaarland.model.Base;
import de.htwsaarland.model.Festplatte;

/**
 * @author Marc Koster
 * 
 */
public class FestplatteDao extends BaseDao implements IDao {

	public FestplatteDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Festplatte find(Long id) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Festplatte fFestplatte = getEntityManager()
				.getReference(Festplatte.class, id);
		tx.commit();
		return fFestplatte;
	}

	@SuppressWarnings("unchecked")
	public List<Festplatte> findAll() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createQuery("SELECT N FROM Festplatte N");
		tx.commit();
		return query.getResultList();
	}

	public void remove(Base o) {
		Festplatte fFestplatte = (Festplatte) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().remove(fFestplatte);
		tx.commit();
	}

	public void save(Base o) {
		Festplatte fFestplatte = (Festplatte) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(fFestplatte);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Festplatte> fireQuery(String querie) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createNativeQuery(querie, Festplatte.class);
		tx.commit();
		return query.getResultList();
	}
}
