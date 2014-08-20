package de.htwsaarland.scenario.selectionLists;

public enum MobileDeviceCategory {

	LAPTOP("Laptop"),
	TABLET("Tablet");
	
	public final String NAME;
	
	private MobileDeviceCategory(String name){
		this.NAME = name;
	}
	
}
