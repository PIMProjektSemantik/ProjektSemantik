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

	
	public String generateQueryFromOWL(String category){
		
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
		String typeFilter = "";
		String performance = scenario.getPerformance();
		String performanceFilter = "";
		
		String[] bereich = OntologyRequest.getBudgetForCategory(scenario.getBudgetOntologie(), category);
		category = category.toLowerCase(); //Grossbuchstaben entfernen
		priceLowerFilter = Integer.valueOf(bereich[1].substring(0, bereich[1].lastIndexOf(".")));
		priceUpperFilter = Integer.valueOf(bereich[2].substring(0, bereich[2].lastIndexOf(".")));

		if(!scenario.getIsFastBootSSDRequested()){
			typeFilter = " AND art LIKE 'SSD'";
		}
		
		if(!performance.isEmpty())
			performanceFilter = " AND Kategorie LIKE \"%"+performance+"%\"";
		
		String query = "SELECT * FROM "+ category +" WHERE " 
						+ "preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter + typeFilter+ performanceFilter;
		
		return query;
		
	}
	
	
	public String generateQueryCPU(){
		
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
				
		if(scenario.getBudget() == PriceBudgetGlobal.LOW){
			priceUpperFilter = 200;
		} else if (scenario.getBudget() == PriceBudgetGlobal.MIDDLE){
			priceLowerFilter = 200;
			priceUpperFilter = 400;
		} else {
			priceLowerFilter = 400;
		}
		
		String query = "SELECT * FROM cpu WHERE preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter;
		
		return query;
		
	}
	
	public String generateQueryRAM(){
		
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
				
		if(scenario.getBudget() == PriceBudgetGlobal.LOW){
			priceUpperFilter = 80;
		} else if (scenario.getBudget() == PriceBudgetGlobal.MIDDLE){
			priceLowerFilter = 80;
			priceUpperFilter = 160;
		} else {
			priceLowerFilter = 160;
		}
		
		String query = "SELECT * FROM arbeitsspeicher WHERE preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter;
		
		return query;
		
	}
	
	public String generateQueryGraphics(){
		
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
				
		if(scenario.getBudget() == PriceBudgetGlobal.LOW){
			priceUpperFilter = 200;
		} else if (scenario.getBudget() == PriceBudgetGlobal.MIDDLE){
			priceLowerFilter = 200;
			priceUpperFilter = 400;
		} else {
			priceLowerFilter = 400;
		}
		
		String query = "SELECT * FROM grafikkarte WHERE preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter;
		
		return query;
		
	}
	
	
	public String generateQueryStorage(){
		
		String typeFilter = "%";
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
		
		if(!scenario.getIsFastBootSSDRequested()){
			typeFilter = "SSD";
		}	
		
		
		if(scenario.getBudget() == PriceBudgetGlobal.LOW){
			priceUpperFilter = 50;
		} else if (scenario.getBudget() == PriceBudgetGlobal.MIDDLE){
			priceLowerFilter = 50;
			priceUpperFilter = 100;
		} else {
			priceLowerFilter = 100;
		}
		
		String query = "SELECT * FROM festplatte WHERE art LIKE '" + typeFilter + 
						"' AND preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter;
		
		return query;
		
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
