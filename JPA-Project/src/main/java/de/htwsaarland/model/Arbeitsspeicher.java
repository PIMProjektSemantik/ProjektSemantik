package de.htwsaarland.model;

import javax.persistence.Entity;
/**
 * @author Marc Koster
 *
 */
@Entity
public class Arbeitsspeicher extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String typ;

	private int takt;

	private int groesse;

	private double preis;

	public Arbeitsspeicher() {
	}

	public int getGroesse() {
		return groesse;
	}

	public String getName() {
		return name;
	}

	public double getPreis() {
		return preis;
	}

	public int getTakt() {
		return takt;
	}

	public String getTyp() {
		return typ;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}
	public void setTakt(int takt) {
		this.takt = takt;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	@Override
	public String toString() {
		return "Arbeitsspeicher [name=" + name + ", typ=" + typ + ", takt="
				+ takt + ", groesse=" + groesse + ", preis=" + preis + "]";
	}
}
