package de.htwsaarland.scenario.selectionLists;

public enum AdditionalScreenCategory {
	
	TRUE_COLOR("Farbechter Flachbildschirm"),
	TOUCH_SCREEN("Berührungsempfindlicher Flachbildschirm");
	
	public final String NAME;
	
	private AdditionalScreenCategory(String name) {
		this.NAME = name;
	}
	
}
