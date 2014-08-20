package de.htwsaarland.scenario;

/**
 * Symbolischer Szenarioschritt , der nach dem letzten regulären Schritt
 * betreten wird. Hiermit kann programmatisch ohne hässliche Null-Referenzen
 * das Szenarioende in die verschiedenen Pfade eingesetzt werden.
 * 
 * Hier kann man je nach GUI-Implementierung dann den "Absenden" bzw.
 * "Speichern" - Vorgang über einen Button freigeben.
 * 
 * @author Stefan
 *
 */
public class ScenarioTreeStepFinish extends ScenarioTreeStep {

	public final static String DEFAULT_END_TEXT = "Ende";
	
	/**
	 * Erstellt einen End-Schritt mit Standardname "Ende"
	 * 
	 */
	public ScenarioTreeStepFinish(){
		super(DEFAULT_END_TEXT);
	}
	
	/**
	 * Erstellt einen End-Schritt mit benutzerdefiniertem Namen
	 * 
	 * @param name Name des Endschrittes
	 */
	public ScenarioTreeStepFinish(String name) {
		super(name);

	}

	/**
	 * getNextStep liefert bei dem Endschritt immer "null" 
	 */
	@Override
	public ScenarioTreeStep getNextStep() {
		return null;
	}

}
