package de.htwsaarland.model;

import javax.persistence.Entity;
/**
 * @author Marc Koster
 *
 */
@Entity
public class Notebook extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hersteller;



	private String modell;


	private String cpu;


	// in MB
	private int ram;


	// in GB
	private int festplatte;

	private String betriebssystem;

	// in St
	private int akku;

	private String displaygroesse;

	private String grafikkarte;

	private double preis;

	public Notebook() {
		// TODO Auto-generated constructor stub
	}

	public Notebook(String string) {
		this.hersteller = string;
	}

	public int getAkku() {
		return akku;
	}

	public String getBetriebssystem() {
		return betriebssystem;
	}

	public String getCpu() {
		return cpu;
	}

	public String getDisplaygroesse() {
		return displaygroesse;
	}

	public int getFestplatte() {
		return festplatte;
	}

	public String getGrafikkarte() {
		return grafikkarte;
	}

	public String getHersteller() {
		return hersteller;
	}

	public String getModell() {
		return modell;
	}

	public double getPreis() {
		return preis;
	}

	public int getRam() {
		return ram;
	}

	public void setAkku(int akku) {
		this.akku = akku;
	}

	public void setBetriebssystem(String betriebssystem) {
		this.betriebssystem = betriebssystem;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public void setDisplaygroesse(String displaygroesse) {
		this.displaygroesse = displaygroesse;
	}
	public void setFestplatte(int festplatte) {
		this.festplatte = festplatte;
	}
	public void setGrafikkarte(String grafikkarte) {
		this.grafikkarte = grafikkarte;
	}
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}
	public void setModell(String modell) {
		this.modell = modell;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	@Override
	public String toString() {
		return "Notebook [hersteller=" + hersteller + ", modell=" + modell
				+ ", cpu=" + cpu + ", ram=" + ram + ", festplatte="
				+ festplatte + ", betriebssystem=" + betriebssystem + ", akku="
				+ akku + ", displaygroesse=" + displaygroesse
				+ ", grafikkarte=" + grafikkarte + ", preis=" + preis
				+ ", getId()=" + getId() + "]";
	}

}
