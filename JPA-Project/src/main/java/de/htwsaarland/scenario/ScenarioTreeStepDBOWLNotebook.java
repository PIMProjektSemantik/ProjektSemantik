package de.htwsaarland.scenario;

import de.htwsaarland.scenario.selectionLists.OperatingSystemComputer;
import de.htwsaarland.scenario.selectionLists.PriceBudgetGlobal;

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
		

		String osFilter = "";
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
		
		if(scenario.getOperatingSystemComputer() == OperatingSystemComputer.WINDOWS){
			osFilter = "Windows";
		} else if (scenario.getOperatingSystemComputer() == OperatingSystemComputer.MAC_OS_X){
			osFilter = "IOS";
		} else if (scenario.getOperatingSystemComputer() == OperatingSystemComputer.LINUX){
			osFilter = "Linux";
		}
		
		
		
		if(scenario.getBudget() == PriceBudgetGlobal.LOW){
			priceUpperFilter = 400;
		} else if (scenario.getBudget() == PriceBudgetGlobal.MIDDLE){
			priceLowerFilter = 400;
			priceUpperFilter = 800;
		} else {
			priceLowerFilter = 800;
		}
		
		String query = "SELECT * FROM notebook WHERE betriebssystem LIKE '%" + osFilter + 
						"%' AND preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter;
		
		return query;

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
