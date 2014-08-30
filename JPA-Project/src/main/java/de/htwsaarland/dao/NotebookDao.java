package de.htwsaarland.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import de.htwsaarland.model.Base;
import de.htwsaarland.model.Notebook;

/**
 * @author Marc Koster
 * 
 */
public class NotebookDao extends BaseDao implements IDao {

	public NotebookDao() {
		super();
	}

	public Notebook find(Long id) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Notebook fNotebook = getEntityManager()
				.getReference(Notebook.class, id);
		tx.commit();
		return fNotebook;
	}

	@SuppressWarnings("unchecked")
	public List<Notebook> findAll() {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createQuery("SELECT N FROM Notebook N");
		tx.commit();
		return query.getResultList();
	}

	public void remove(Base o) {
		Notebook notebook = (Notebook) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().remove(notebook);
		tx.commit();
	}

	public void save(Base o) {
		Notebook notebook = (Notebook) o;
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		getEntityManager().persist(notebook);
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Notebook> fireQuery(String querie) {
		EntityTransaction tx = getEntityManager().getTransaction();
		tx.begin();
		Query query = getEntityManager()
				.createNativeQuery(querie, Notebook.class);
		tx.commit();
		return query.getResultList();
	}

}
