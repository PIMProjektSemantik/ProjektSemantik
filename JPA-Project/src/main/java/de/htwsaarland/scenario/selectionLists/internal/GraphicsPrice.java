package de.htwsaarland.scenario.selectionLists.internal;

public enum GraphicsPrice {

	LOW("gering"),
	MIDDLE("mittel"),
	HIGH("hoch");
	
	public final String NAME;
	
	private GraphicsPrice(String name){
		this.NAME = name;
	}
	
}
