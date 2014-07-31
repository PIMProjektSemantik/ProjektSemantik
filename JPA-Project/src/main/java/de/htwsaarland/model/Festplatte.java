package de.htwsaarland.model;

import javax.persistence.Entity;
/**
 * @author Marc Koster
 *
 */
@Entity
public class Festplatte extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	private String art;

	private String groesse;
	private double preis;
	private String hersteller;
	private String kategorie;
	
	public Festplatte() {
		// TODO Auto-generated constructor stub
	}
	public String getArt() {
		return art;
	}
	public String getGroesse() {
		return groesse;
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
	public void setArt(String art) {
		this.art = art;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
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
		return "Festplatte [name=" + name + ", art=" + art + ", groesse="
				+ groesse + ", preis=" + preis + ", hersteller=" + hersteller
				+ "]";
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
