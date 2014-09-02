package de.htwsaarland.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.htwsaarland.model.CPU;
import de.htwsaarland.model.Base;

/**
 * @author Marc Koster
 * 
 */
public class CPUDao extends BaseDao implements IDao {

	public CPUDao() {
	}
	
	public CPU find(Long id) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		CPU fCPU = getEntityManager()
				.getReference(CPU.class, id);
		tx.commit();
		return fCPU;
	}

	@SuppressWarnings("unchecked")
	public List<CPU> findAll() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createQuery("SELECT N FROM CPU N");
		tx.commit();
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<CPU> fireQuery(String querie) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createNativeQuery(querie, CPU.class);
		tx.commit();
		return query.getResultList();
	}

	public void remove(Base o) {
		CPU cpu = (CPU) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().remove(cpu);
		tx.commit();
	}

	public void save(Base o) {
		CPU cpu = (CPU) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(cpu);
		tx.commit();
	}

}
