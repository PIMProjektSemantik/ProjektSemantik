package de.htwsaarland.scenario.selectionLists.internal;

public enum RAMPrice {

	LOW("gering"),
	MIDDLE("mittel"),
	HIGH("hoch");
	
	public final String NAME;
	
	private RAMPrice(String name){
		this.NAME = name;
	}
	
}
