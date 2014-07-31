package de.htwsaarland.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.htwsaarland.model.Base;
import de.htwsaarland.model.Tablet;

/**
 * @author Marc Koster
 * 
 */
public class TabletDao extends BaseDao implements IDao {

	public TabletDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Tablet find(Long id) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Tablet fTablet = getEntityManager()
				.getReference(Tablet.class, id);
		tx.commit();
		return fTablet;
	}

	@SuppressWarnings("unchecked")
	public List<Tablet> findAll() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createQuery("SELECT N FROM Tablet N");
		tx.commit();
		return query.getResultList();
	}

	public void remove(Base o) {
		Tablet Tablet = (Tablet) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().remove(Tablet);
		tx.commit();
	}

	public void save(Base o) {
		Tablet Tablet = (Tablet) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(Tablet);
		tx.commit();
	}

}
