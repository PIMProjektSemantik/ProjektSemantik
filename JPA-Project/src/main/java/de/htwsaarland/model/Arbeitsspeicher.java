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

	private String takt;

	private String groesse;

	private double preis;
	
	private String kategorie;

	public Arbeitsspeicher() {
	}

	public String getGroesse() {
		return groesse;
	}

	public String getName() {
		return name;
	}

	public double getPreis() {
		return preis;
	}

	public String getTakt() {
		return takt;
	}

	public String getTyp() {
		return typ;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}
	public void setTakt(String takt) {
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

	/**
	 * @return the kategorie
	 */
	public String getKategorie() {
		return kategorie;
	}

	/**
	 * @param kategorie the kategorie to set
	 */
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
}
