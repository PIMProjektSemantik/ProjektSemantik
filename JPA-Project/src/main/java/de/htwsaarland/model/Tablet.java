package de.htwsaarland.model;

import javax.persistence.Entity;
/**
 * @author Marc Koster
 *
 */
@Entity
public class Tablet extends Base {

	private String hersteller;
	private String modell;
	private String prozessor;
	private int speicher;
	private double preis;
	private String connectionType;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tablet() {
		// TODO Auto-generated constructor stub
	}

	public String getConnectionType() {
		return connectionType;
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

	public String getProzessor() {
		return prozessor;
	}

	public int getSpeicher() {
		return speicher;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
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

	public void setProzessor(String prozessor) {
		this.prozessor = prozessor;
	}

	public void setSpeicher(int speicher) {
		this.speicher = speicher;
	}

	@Override
	public String toString() {
		return "Tablets [hersteller=" + hersteller + ", modell=" + modell
				+ ", prozessor=" + prozessor + ", speicher=" + speicher
				+ ", preis=" + preis + ", connectionType=" + connectionType
				+ ", getId()=" + getId() + "]";
	}

}
