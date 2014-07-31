package de.htwsaarland.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * @author Marc Koster
 *
 */
public class BaseDao {

	private EntityManager entityManager;

	public BaseDao() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("persistenceUnit");
		setEntityManager(factory.createEntityManager());
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
