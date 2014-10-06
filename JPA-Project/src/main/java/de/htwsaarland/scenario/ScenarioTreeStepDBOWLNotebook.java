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
		String typeFilter = "";
		String performance = scenario.getPerformance();
		String performanceFilter = "";
		int diskSize = 0;
		String diskSizeFilter = "";
		
		if(!performance.isEmpty())
			performanceFilter = " AND Kategorie LIKE \"%"+performance+"%\"";
		
		
		if(!os.isEmpty())
			osFilter = " AND betriebssystem LIKE '%" + os + "%'";
		

		//Ermittlung der Festplattenspezifikationen
		System.out.println(category);
		String[] diskSizeDefinition = OntologyRequest.getDiskSize(scenario.getDiskSizeType());
		diskSize = Integer.valueOf(diskSizeDefinition[2]);
		if(diskSizeDefinition[1].equals("minInclusive")){
			diskSizeFilter = " AND festplattengroesse >= ";
		} else if(diskSizeDefinition[1].equals("maxInclusive")){
			diskSizeFilter = " AND festplattengroesse <= ";
		} else if(diskSizeDefinition[1].equals("minExclusive")){
			diskSizeFilter = " AND festplattengroesse >= ";
		} else if(diskSizeDefinition[1].equals("maxExclusive")){
			diskSizeFilter = " AND festplattengroesse <= ";
		}
		diskSizeFilter += diskSize;
		if(scenario.getIsFastBootSSDRequested())
			typeFilter = " AND (festplattentyp LIKE 'SSD' OR festplattentyp LIKE 'SSHD')";

		
		String[] bereich = OntologyRequest.getBudgetForCategory(scenario.getBudgetOntologie(), category);
		category = category.toLowerCase(); //Grossbuchstaben entfernen
		priceLowerFilter = Double.valueOf(bereich[2]);
		priceUpperFilter = Double.valueOf(bereich[4]);
		
		String query = "SELECT * FROM "+ category + " WHERE preis >= " + priceLowerFilter + 
						" AND preis <= " + priceUpperFilter
						+ osFilter + typeFilter +performanceFilter+diskSizeFilter;
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
