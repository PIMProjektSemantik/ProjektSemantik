package de.htwsaarland.scenario;

import de.htwsaarland.ontology.OntologyRequest;
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

	public String generateQueryFromOWL(){
		
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
		String os = scenario.getOperatingSystemName();
		String osFilter ="";		
		String category = "Notebook";
		
		
		if(!os.isEmpty())
			osFilter = " AND betriebssystem LIKE '%" + os + "%'";
		
		String[] bereich = OntologyRequest.getBudgetForCategory(scenario.getBudgetOntologie(), category);
		category = category.toLowerCase(); //Grossbuchstaben entfernen
		priceLowerFilter = Integer.valueOf(bereich[1].substring(0, bereich[1].lastIndexOf(".")));
		priceUpperFilter = Integer.valueOf(bereich[2].substring(0, bereich[2].lastIndexOf(".")));
		
		String query = "SELECT * FROM "+ category + " WHERE preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter + osFilter;
		return query;
		
	}		
	
	public String generateQuery(){
		

		String osFilter = "";
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
		
		osFilter = scenario.getOperatingSystemName();
		
		
		
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
