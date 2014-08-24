package de.htwsaarland.scenario;

/**
 * Schritt für Zubehör-Abfrage aus Datenbank und Ontologie.
 * 
 * Besitzt nur einen Folgeschritt.
 * 
 * @author Stefan
 *
 */
public class ScenarioTreeStepDBOWLAccessory extends ScenarioTreeStepSimpleDatabaseRequest {

	/**
	 * Konstruiert den ZUbehör-Datenbankschritt mit seinem Namen
	 * 
	 * @param name
	 */
	public ScenarioTreeStepDBOWLAccessory(String name, String help) {
		super(name, help);
		
	}

	
	@Override
	public void addFollowUpStep(ScenarioTreeStep stepToAdd) {
		
		if(this.getFollowUpSteps().length >= 1) {
			throw new RuntimeException("ScenarioTreeStepBDOWLAccessory: Nur ein Folgeschritt möglich");
		}
		
		super.addFollowUpStep(stepToAdd);
	};
	
	/**
	 * Liefert den einzigen Folgeschritt, den dieser Schritt haben kann.
	 */
	@Override
	public ScenarioTreeStep getNextStep() {
		
		if(this.getFollowUpSteps().length == 0){
			throw new RuntimeException("ScenarioTreeStepBDOWLAccessory: Kein Folgeschritt gesetzt!");
		}
		
		return this.getFollowUpStep(0);
	}

}
