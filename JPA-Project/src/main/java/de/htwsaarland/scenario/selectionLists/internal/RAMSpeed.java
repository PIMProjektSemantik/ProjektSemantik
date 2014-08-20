package de.htwsaarland.scenario.selectionLists.internal;

public enum RAMSpeed {

	SLOW("langsam"),
	MIDDLE("mittel"),
	FAST("schnell");
	
	public final String NAME;
	
	private RAMSpeed(String name){
		this.NAME = name;
	}
	
}
