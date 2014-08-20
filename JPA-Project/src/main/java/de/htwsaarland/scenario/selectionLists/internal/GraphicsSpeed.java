package de.htwsaarland.scenario.selectionLists.internal;

public enum GraphicsSpeed {

	SLOW("langsam"),
	MIDDLE("mittel"),
	FAST("schnell");
	
	public final String NAME;
	
	private GraphicsSpeed(String name){
		this.NAME = name;
	}
	
}
