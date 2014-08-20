package de.htwsaarland.scenario.selectionLists.internal;

public enum RAMSize {

	SMALL("klein"),
	MIDDLE("mittel"),
	BIG("gross");
	
	public final String NAME;
	
	private RAMSize(String name){
		this.NAME = name;
	}
	
}
