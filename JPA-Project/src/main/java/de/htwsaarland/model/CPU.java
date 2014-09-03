package de.htwsaarland.model;

import javax.persistence.Entity;

@Entity
public class CPU extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CPU() {
	}

	private String name;

	private String hersteller;

	private double takt;

	private int kerne;

	private double preis;
	
	private int leistung;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public double getTakt() {
		return takt;
	}

	public void setTakt(double takt) {
		this.takt = takt;
	}

	public int getKerne() {
		return kerne;
	}

	public void setKerne(int kerne) {
		this.kerne = kerne;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	@Override
	public String toString() {
		return "CPU [name=" + name + ", hersteller=" + hersteller + ", takt=" + takt + ", kerne=" + kerne + ", preis=" + preis + ", leistung=" + leistung + "]";
	}

	public int getLeistung() {
		return leistung;
	}

	public void setLeistung(int leistung) {
		this.leistung = leistung;
	}
	
}
