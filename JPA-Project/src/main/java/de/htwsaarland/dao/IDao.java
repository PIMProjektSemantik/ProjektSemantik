package de.htwsaarland.dao;

import java.util.List;

import de.htwsaarland.model.Base;
/**
 * @author Marc Koster
 *
 */
public interface IDao {

	Base find(Long id);

	List<? extends Base> findAll();

	void remove(Base b);
	
	public void save(Base b);
}
