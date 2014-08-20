package de.htwsaarland.scenario.selectionLists;

public enum AdditionalScreenCategory {
	
	NONE("Kein Bildschirm"),
	STANDARD("Standard Flachbildschirm"),
	TRUE_COLOR("Farbechter Flachbildschirm"),
	TOUCH_SCREEN("Ber�hrungsempfindlicher Flachbildschirm");
	
	public final String NAME;
	
	private AdditionalScreenCategory(String name) {
		this.NAME = name;
	}
	
}
