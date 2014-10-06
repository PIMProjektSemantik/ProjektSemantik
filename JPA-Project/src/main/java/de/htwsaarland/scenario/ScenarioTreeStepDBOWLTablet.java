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
public class ScenarioTreeStepDBOWLTablet extends ScenarioTreeStepSimpleDatabaseRequest {

	
	ScenarioHardwareBerater scenario;
	
	/**
	 * Konstruiert den Geräte-Datenbankschritt mit seinem Namen
	 * 
	 * @param name
	 */
	public ScenarioTreeStepDBOWLTablet(String name, String help, ScenarioHardwareBerater scenario) {
		super(name, help);
		
		if(scenario == null){
			throw new IllegalArgumentException("ScenarioTreeStepDBOWLTablet benötigt ein Szenarioobjekt, darf nicht null sein!");
		}
		this.scenario = scenario;
	}

	public String generateQueryFromOWL(){
		String category = "Tablet";
		String mobileInternetFilter = "";
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
		String performance = scenario.getPerformance();
		String performanceFilter = "";
		
		if(!performance.isEmpty())
			performanceFilter = " AND Kategorie LIKE \"%"+performance+"%\"";

		String os = scenario.getOperatingSystemName();
		String osFilter ="";
		String mobileInternet = "";
		
		if(!scenario.getIsMobileInternetRequested()){
			mobileInternet= "Wifi";
		} else {
			mobileInternet= "Wifi +%";
		}
		
		if(!os.isEmpty())
			osFilter = " AND betriebssystem LIKE '%" + os + "%'";
		mobileInternetFilter = " AND connectionType LIKE '" + mobileInternet +"'";
		
		String[] bereich = OntologyRequest.getBudgetForCategory(scenario.getBudgetOntologie(), category);
		category = category.toLowerCase(); //Grossbuchstaben entfernen
		priceLowerFilter = Double.valueOf(bereich[2]);
		priceUpperFilter = Double.valueOf(bereich[4]);

		
		String query = "SELECT * FROM "+ category +" WHERE preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter
						+ mobileInternetFilter + osFilter + performanceFilter;
		return query;
		
	}
	
	public String generateQuery(){

		String osFilter = "";
		String mobileInternetFilter = "";
		double priceLowerFilter = 0;
		double priceUpperFilter = Integer.MAX_VALUE;
		
		osFilter = scenario.getOperatingSystemName();
		
		if(!scenario.getIsMobileInternetRequested()){
			mobileInternetFilter = "Wifi";
		} else {
			mobileInternetFilter = "Wifi +%";
		}
		
		if(scenario.getBudget() == PriceBudgetGlobal.LOW){
			priceUpperFilter = 300;
		} else if (scenario.getBudget() == PriceBudgetGlobal.MIDDLE){
			priceLowerFilter = 300;
			priceUpperFilter = 600;
		} else {
			priceLowerFilter = 600;
		}
		
		String query = "SELECT * FROM tablet WHERE betriebssystem LIKE '%" + osFilter + 
						"%' AND connectionType LIKE '" + mobileInternetFilter +
						"' AND preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter;
				
		return query;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void addFollowUpStep(ScenarioTreeStep stepToAdd) {
		
		if(this.getFollowUpSteps().length >= 1) {
			throw new RuntimeException("ScenarioTreeStepDBOWLTablet: Nur ein Folgeschritt möglich");
		}
		
		super.addFollowUpStep(stepToAdd);
	};
	
	/**
	 * Liefert den einzigen Folgeschritt, den dieser Schritt haben kann.
	 */
	@Override
	public ScenarioTreeStep getNextStep() {
		
		if(this.getFollowUpSteps().length == 0){
			throw new RuntimeException("ScenarioTreeStepDBOWLTablet: Kein Folgeschritt gesetzt!");
		}
		
		return this.getFollowUpStep(0);
	}

}
