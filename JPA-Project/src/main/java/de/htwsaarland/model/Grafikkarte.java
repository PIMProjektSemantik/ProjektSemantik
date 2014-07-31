package de.htwsaarland.model;

import javax.persistence.Entity;
/**
 * @author Marc Koster
 *
 */
@Entity
public class Grafikkarte extends Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	private String hersteller;

	private double rechenleistung;
	private double preis;
	private int speichergroesse;
	private String kategorie;
	public Grafikkarte() {
		// TODO Auto-generated constructor stub
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
	public double getRechenleistung() {
		return rechenleistung;
	}
	public int getSpeichergroesse() {
		return speichergroesse;
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
	public void setRechenleistung(double rechenleistung) {
		this.rechenleistung = rechenleistung;
	}
	public void setSpeichergroesse(int speichergroesse) {
		this.speichergroesse = speichergroesse;
	}
	@Override
	public String toString() {
		return "Grafikkarte [name=" + name + ", hersteller=" + hersteller
				+ ", rechenleistung=" + rechenleistung + ", preis=" + preis
				+ ", speichergroesse=" + speichergroesse + "]";
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
