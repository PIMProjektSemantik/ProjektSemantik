package de.htwsaarland.scenario;

import de.htwsaarland.scenario.selectionLists.PriceBudgetGlobal;

/**
 * Schritt für Geräte-Abfrage aus Datenbank und Ontologie.
 * 
 * Besitzt nur einen Folgeschritt.
 * 
 * @author Stefan
 *
 */
public class ScenarioTreeStepDBOWLSoftwareAndOS extends ScenarioTreeStepSimpleDatabaseRequest {

	
	ScenarioHardwareBerater scenario;
	
	/**
	 * Konstruiert den Geräte-Datenbankschritt mit seinem Namen
	 * 
	 * @param name
	 */
	public ScenarioTreeStepDBOWLSoftwareAndOS(String name, String help, ScenarioHardwareBerater scenario) {
		super(name, help);
		
		if(scenario == null){
			throw new IllegalArgumentException("ScenarioTreeStepDBOWLSoftwareAndOS benötigt ein Szenarioobjekt, darf nicht null sein!");
		}
		this.scenario = scenario;
	}

	
	
	
	
	
	public String generateQuerySoftware(){
	
		
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
			
		if(scenario.getBudget() == PriceBudgetGlobal.LOW){
			priceUpperFilter = 100;
		} else if (scenario.getBudget() == PriceBudgetGlobal.MIDDLE){
			priceLowerFilter = 0;
			priceUpperFilter = 200;
		} else {
			priceLowerFilter = 0;
		}
		
		String query = "SELECT * FROM software WHERE preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter;
		
		return query;
		
	}
	
	public String generateQueryOS(){
		
		String osFilter = "";
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
		
		osFilter = scenario.getOperatingSystemName();
		
		
		
		if(scenario.getBudget() == PriceBudgetGlobal.LOW){
			priceUpperFilter = 80;
		} else if (scenario.getBudget() == PriceBudgetGlobal.MIDDLE){
			priceLowerFilter = 0;
			priceUpperFilter = 150;
		} else {
			priceLowerFilter = 0;
		}
		
		
		
		String query = "SELECT * FROM betriebssystem WHERE name LIKE '%" + osFilter + 
						"%' AND preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter;
		
		System.out.println(query);
		
		return query;

		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void addFollowUpStep(ScenarioTreeStep stepToAdd) {
		
		if(this.getFollowUpSteps().length >= 1) {
			throw new RuntimeException("ScenarioTreeStepDBOWLSoftwareAndOS: Nur ein Folgeschritt möglich");
		}
		
		super.addFollowUpStep(stepToAdd);
	};
	
	/**
	 * Liefert den einzigen Folgeschritt, den dieser Schritt haben kann.
	 */
	@Override
	public ScenarioTreeStep getNextStep() {
		
		if(this.getFollowUpSteps().length == 0){
			throw new RuntimeException("ScenarioTreeStepDBOWLSoftwareAndOS: Kein Folgeschritt gesetzt!");
		}
		
		return this.getFollowUpStep(0);
	}

}
