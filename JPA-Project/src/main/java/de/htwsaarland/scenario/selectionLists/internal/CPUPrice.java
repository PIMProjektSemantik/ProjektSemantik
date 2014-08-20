package de.htwsaarland.scenario.selectionLists.internal;

public enum CPUPrice {

	LOW("gering"),
	MIDDLE("mittel"),
	HIGH("hoch");
	
	public final String NAME;
	
	private CPUPrice(String name){
		this.NAME = name;
	}
	
}
