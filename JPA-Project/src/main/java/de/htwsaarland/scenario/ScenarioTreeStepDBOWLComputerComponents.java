package de.htwsaarland.scenario;

/**
 * Schritt für Geräte-Abfrage aus Datenbank und Ontologie.
 * 
 * Besitzt nur einen Folgeschritt.
 * 
 * @author Stefan
 *
 */
public class ScenarioTreeStepDBOWLComputerComponents extends ScenarioTreeStepSimpleDatabaseRequest {

	
	ScenarioHardwareBerater scenario;
	
	/**
	 * Konstruiert den Geräte-Datenbankschritt mit seinem Namen
	 * 
	 * @param name
	 */
	public ScenarioTreeStepDBOWLComputerComponents(String name, String help, ScenarioHardwareBerater scenario) {
		super(name, help);
		
		if(scenario == null){
			throw new IllegalArgumentException("ScenarioTreeStepDBOWLComputerComponents benötigt ein Szenarioobjekt, darf nicht null sein!");
		}
		this.scenario = scenario;
	}

	
	
	
	
	public String generateQueryCPU(){
		
		return "SELECT * FROM prozessor";
		
	}
	
	public String generateQueryRAM(){
		
		return "SELECT * FROM arbeitsspeicher";
		
	}
	
	public String generateQueryGraphics(){
		
		return "SELECT * FROM grafikkarte";
		
	}
	
	
	public String generateQueryStorage(){
		
		return "SELECT * FROM festplatte";
		
	}
	
	
	
	
	
	
	
	@Override
	public void addFollowUpStep(ScenarioTreeStep stepToAdd) {
		
		if(this.getFollowUpSteps().length >= 1) {
			throw new RuntimeException("ScenarioTreeStepDBOWLComputerComponents: Nur ein Folgeschritt möglich");
		}
		
		super.addFollowUpStep(stepToAdd);
	};
	
	/**
	 * Liefert den einzigen Folgeschritt, den dieser Schritt haben kann.
	 */
	@Override
	public ScenarioTreeStep getNextStep() {
		
		if(this.getFollowUpSteps().length == 0){
			throw new RuntimeException("ScenarioTreeStepDBOWLComputerComponents: Kein Folgeschritt gesetzt!");
		}
		
		return this.getFollowUpStep(0);
	}

}
