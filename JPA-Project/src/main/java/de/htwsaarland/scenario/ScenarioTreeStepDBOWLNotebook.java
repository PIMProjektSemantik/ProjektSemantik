package de.htwsaarland.scenario;

/**
 * Schritt für Geräte-Abfrage aus Datenbank und Ontologie.
 * 
 * Besitzt nur einen Folgeschritt.
 * 
 * @author Stefan
 *
 */
public class ScenarioTreeStepDBOWLNotebook extends ScenarioTreeStepSimpleDatabaseRequest {

	
	ScenarioHardwareBerater scenario;
	
	/**
	 * Konstruiert den Geräte-Datenbankschritt mit seinem Namen
	 * 
	 * @param name
	 */
	public ScenarioTreeStepDBOWLNotebook(String name, String help, ScenarioHardwareBerater scenario) {
		super(name, help);
		
		if(scenario == null){
			throw new IllegalArgumentException("ScenarioTreeStepDBOWLNotebook benötigt ein Szenarioobjekt, darf nicht null sein!");
		}
		this.scenario = scenario;
		
	}

		
	
	public String generateQuery(){
		
		return "SELECT * FROM notebook where preis > 100";

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void addFollowUpStep(ScenarioTreeStep stepToAdd) {
		
		if(this.getFollowUpSteps().length >= 1) {
			throw new RuntimeException("ScenarioTreeStepDBOWLNotebook: Nur ein Folgeschritt möglich");
		}
		
		super.addFollowUpStep(stepToAdd);
	};
	
	/**
	 * Liefert den einzigen Folgeschritt, den dieser Schritt haben kann.
	 */
	@Override
	public ScenarioTreeStep getNextStep() {
		
		if(this.getFollowUpSteps().length == 0){
			throw new RuntimeException("ScenarioTreeStepDBOWLNotebook: Kein Folgeschritt gesetzt!");
		}
		
		return this.getFollowUpStep(0);
	}

}
