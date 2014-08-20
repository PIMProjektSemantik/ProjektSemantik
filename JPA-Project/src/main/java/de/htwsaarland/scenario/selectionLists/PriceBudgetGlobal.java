package de.htwsaarland.scenario.selectionLists;

public enum PriceBudgetGlobal {

	LOW("gering"),
	MIDDLE("mittel"),
	HIGH("hoch");
	
	public final String NAME;
	
	private PriceBudgetGlobal(String name){
		this.NAME = name;
	}
	
}
