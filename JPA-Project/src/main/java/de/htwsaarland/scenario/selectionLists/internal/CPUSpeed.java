package de.htwsaarland.scenario.selectionLists.internal;

public enum CPUSpeed {

	SLOW("langsam"),
	MIDDLE("mittel"),
	FAST("schnell");
	
	public final String NAME;
	
	private CPUSpeed(String name){
		this.NAME = name;
	}
	
}
