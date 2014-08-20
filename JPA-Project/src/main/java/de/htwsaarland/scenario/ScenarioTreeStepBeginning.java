package de.htwsaarland.scenario;/**
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
public class ScenarioTreeStepBeginning extends ScenarioTreeStep {

	public final static String DEFAULT_START_TEXT = "Start";
	
	/**
	 * Erstellt einen Start-Schritt mit Standardname "Ende"
	 * 
	 */
	public ScenarioTreeStepBeginning(){
		super(DEFAULT_START_TEXT);
	}
	
	/**
	 * Erstellt einen End-Schritt mit benutzerdefiniertem Namen
	 * 
	 * @param name Name des Endschrittes
	 */
	public ScenarioTreeStepBeginning(String name) {
		super(name);

	}
	
	@Override
	public void addFollowUpStep(ScenarioTreeStep stepToAdd) {
		
		if(this.getFollowUpSteps().length >= 1) {
			throw new RuntimeException("ScenarioTreeStepBeginning: Nur ein Folgeschritt möglich");
		}
		
		super.addFollowUpStep(stepToAdd);
	};
	

	/**
	 * getNextStep liefert bei dem Endschritt immer "null" 
	 */
	@Override
	public ScenarioTreeStep getNextStep() {
		
		if(this.getFollowUpSteps().length == 0){
			throw new RuntimeException("ScenarioTreeStepBDOWLAccessory: Kein Folgeschritt gesetzt!");
		}
		
		return this.getFollowUpStep(0);
	}

}
