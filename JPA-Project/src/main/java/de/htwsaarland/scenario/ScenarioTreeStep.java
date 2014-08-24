package de.htwsaarland.scenario;

import java.util.ArrayList;

/**
 * Beschreibt einen Szenarioschritt eines Beratungs/Abfrageszenarios, entsprechend einer 
 * Frage im Szenarioablauf mitsamt der Antwortmöglichkeiten und Referenzen zu den Folgeschritten.
 * 
 * Diese Klasse definiert direkt nur den Rumpf, dies beinhaltet den Namen des Schritts
 * und eine Liste von Nachfolgeschritten.
 * 
 * Ableitungsklassen definieren verschiedene Typen von Verknüpfungen zu 
 * den Nachfolgeschritten, im Semantik-Projekt einen für eine Enum-Liste mit festen Optionen sowie
 * einen für eine DB-Abfrage, die nur einen festen Nachfolgeschritt hat (die Auswahl beeinflusst
 * nicht den Szenarioablauf). Weitere Varianten sind nach Bedarf möglich.
 *  * 
 * Ein Szenarioschritt kann über verschiedene Szenariopfade erreichbar sein, wird aber stets
 * nur einmal definiert, womit auch die gesamte nachfolgende Definition ab einem solchen Punkt
 * nur einmal notwendig ist.
 * 
 * 
 * Zur konkreten Verwendung:
 * 
 * Ein Schritt muss instanziiert und mit Referenzen zu den Nachfolgeschritten versehen werden.
 * Bei einer kompletten Ablaufstruktur geht man hierbei am besten von hinten nach vorne vor (um
 * immer alle Referenzen schon zur Verfügung zu haben).
 * 
 * 
 * Weiterhin:
 * 
 * Um ein Szenario zu durchlaufen und Antworten zu speichern ist eine Kontrollstruktur notwendig,
 * die nicht durch die Schritte verfügbar ist. Sie liegt darüber und besitzt Referenzen 
 * zu den Schritten bzw. zum aktuellen Schritt und zu Datenhaltungsobjekten.
 * 
 * 
 * @author Stefan
 *
 */
public abstract class ScenarioTreeStep {

	public final String NAME;
	ArrayList<String> selectionNames;
	
	// Alle möglichen Folgeschritte
	private ArrayList<ScenarioTreeStep> followUpStepsList;

	
	/**
	 * Konstuktor des absrakten Szenario Schritts.
	 */
	public ScenarioTreeStep(String name) {
		
		if (name == null || name.trim().isEmpty()){
			throw new IllegalArgumentException("ScenarioTreeStep: Namen darf nicht null sein bzw. nur Whitespaces enthalten!");
		}
		this.NAME = name;
		
		followUpStepsList = new ArrayList<ScenarioTreeStep>();
	}
	
	
	/**
	 * Fügt einen Schritt zur Liste von Nachfolgeschritten hinzu.
	 * Als Optionsname gilt der interne Schrittname
	 * 
	 * @param stepToAdd
	 */
	public void addFollowUpStep(ScenarioTreeStep stepToAdd){
		
		this.addFollowUpStep(stepToAdd, null);
	}
	
	
	/**
	 * Fügt einen Folgeschritt mit benutzerdefiniertem Optionstext hinzu.
	 * Je nach konkretem Schrittyp kann dieser Name unsichtbar sein. Wird
	 * er weggelassen (null), dann gilt automatisch der Schrittname.
	 * 
	 * @param stepToAdd
	 * @param optionName
	 */
	public void addFollowUpStep(ScenarioTreeStep stepToAdd, String optionName){
		
		// Argument null-Check
		if (stepToAdd == null){
			throw new IllegalArgumentException("ScenarioTreeStep: Szenario zum Hinzufügen darf nicht null sein!");
		}
				
		this.followUpStepsList.add(stepToAdd);

		// Name Option check
		if(optionName == null || optionName.trim().isEmpty()) {
			this.selectionNames.add(stepToAdd.NAME);
		} else {
			this.selectionNames.add(optionName);
		}
		
		
	}
	
	/**
	 * Ruft einen Folgeschritt unter Angabe seines Index auf.
	 * 
	 * @param id Index
	 * @return Der Schritt, sofern im Indexbereich oder Exception
	 */
	public ScenarioTreeStep getFollowUpStep(int id){
		
		// Nur die Argumentprüfung. Mehr braucht man hier nicht.
		if(id < 0 || id >= followUpStepsList.size()) {
			throw new IllegalArgumentException("ScenarioTreeStep: Auswahl-Id muss > 0 und < Listengröße " + followUpStepsList.size() + " sein. Wert: " + id);
		}
		return this.followUpStepsList.get(id);
		
	}
	
	/**
	 * Liefert eine Liste aller Folgeschritte
	 * 
	 * @return
	 */
	public ScenarioTreeStep[] getFollowUpSteps(){
		
		return (ScenarioTreeStep[])followUpStepsList.toArray();
		
	}
	
	/**
	 * Gibt den gewählten Folgeschritt auf Basis des Auswahlzustandes zurück.
	 * 
	 * @return
	 */
	public abstract ScenarioTreeStep getNextStep();
	
	
	/**
	 * Liefert die Auswahloptionen dieses Szenarioschritts. Geliefert werden
	 * die zur jeweligen Auswahl gehörenden lesbaren Texte. Diese
	 * werden in der gleichen Reihenfolge geordnet wie die dazugehörigen Schritte.
	 *  
	 * @return String-Array
	 */
	public String[] getSelectionOptions(){
		
		return (String[])selectionNames.toArray();
	}
	
}
