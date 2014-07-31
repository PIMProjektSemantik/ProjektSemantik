package de.htwsaarland.model;

import javax.persistence.Entity;
/**
 * @author Marc Koster
 *
 */
@Entity
public class Betriebssystem extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String hersteller;

	private String edition;

	private double preis;

	public Betriebssystem() {
		// TODO Auto-generated constructor stub
	}

	public String getEdition() {
		return edition;
	}

	public String getHersteller() {
		return hersteller;
	}

	public String getName() {
		return name;
	}

	public double getPreis() {
		return preis;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	@Override
	public String toString() {
		return "Betriebssystem [name=" + name + ", hersteller=" + hersteller
				+ ", edition=" + edition + ", preis=" + preis + ", getId()="
				+ getId() + "]";
	}
}
