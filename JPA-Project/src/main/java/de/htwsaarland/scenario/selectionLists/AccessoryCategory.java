package de.htwsaarland.scenario.selectionLists;

public enum AccessoryCategory {
	
	NONE("Kein Zubehör"),
	BEAMER("Beamer"),
	PRINTER("Drucker"),
	BAG("Tasche"),
	DOCKING_STATION("Dockingstation"),
	INPUT_DEVICE("Eingabegerät"),
	EXTERNAL_STORAGE("Externer Speicher"),
	OTHER("Sonstige");
	
	
	public final String NAME;
	
	private AccessoryCategory(String name) {
		this.NAME = name;
	}
	
}
