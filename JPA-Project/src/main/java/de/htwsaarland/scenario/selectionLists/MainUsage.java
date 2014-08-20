package de.htwsaarland.scenario.selectionLists;

/**
 * Hauptanwendungsbereich
 * 
 * @author Stefan
 *
 */
public enum MainUsage {

	OFFICE("Büroarbeit"),
	MEDIA_EDITING("Medienbearbeitung"),
	CAD("CAD"),
	ENTERTAINMENT_MEDIA("Multimediazentrale"),
	ENTERTAINMENT_GAMES("Spielezentrale"),
	SOFTWARE_DEVELOPMENT("Softwareentwicklung");
	
	public final String NAME;
	
	private MainUsage(String name) {
		this.NAME = name;
	}
	
}
