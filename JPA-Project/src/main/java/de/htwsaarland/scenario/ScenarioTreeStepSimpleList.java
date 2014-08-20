package de.htwsaarland.scenario;

import java.util.ArrayList;

/**
 * Form des Szenarioschrittes, die eine einfache Auswahl von Optionen bereitstellt und 
 * dadurch stets Drop-Down-Kompatibel ist.
 * 
 * Es gibt zu allen Auswahloptionen je einen Nachfolgeschritt. Diese werden mitsamt einem
 * benutzerdefinierten Optionstext über addFollowUpStep eingetragen. Dies ist ansonsten
 * die gleiche Schritthinzufügeoption wie beim Standard-Schritt.
 * 
 * In der Regel können die konkreten Schritte auf dieser Basis ohne Ableitungsklassen
 * definiert werden, in dem man Instanzen erzeugt und mit "addFollowUpStep" die
 * Nachfolger hinzufügt.
 * 
 * Die Reihenfolge der Nachfolgeschritte muss zur 
 * 
 * @author Stefan
 *
 */
public class ScenarioTreeStepSimpleList extends ScenarioTreeStep {

	// Parameter für die gesetzte Auswahl
	private int selectedOption;
	
	/**
	 * Erstellt einen Schritt mit Schrittnamen. Name darf nicht null
	 * oder nur aus Leerzeichen sein.
	 * 
	 * @param name Name des Schrittes
	 */
	public ScenarioTreeStepSimpleList(String name) {
		super(name);
		selectedOption = -1;
	}


	ArrayList<String> selectionNames;
	
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

	/**
	 * Fügt einen neuen Folgeschritt zur Liste der Folgeschritte hinzu. Wird
	 * dieser hier ohne Auswahloptionsname übergeben, dann wird der 
	 * Schrittname als Auswahloptionsname verwendet.
	 * 
	 */
	
	@Override
	public void addFollowUpStep(ScenarioTreeStep stepToAdd){
		
		// Fängt null-Fehler bei stepToAdd
		super.addFollowUpStep(stepToAdd);
		
		this.selectionNames.add(stepToAdd.name);
		
	}
	
	
	
	/**
	 * Fügt einen Folgeschritt mit benutzerdefiniertem Optionstext hinzu.
	 * 
	 * @param stepToAdd
	 * @param optionName
	 */
	public void addFollowUpStep(ScenarioTreeStep stepToAdd, String optionName){
		
		// Fängt null-Fehler bei stepToAdd
		super.addFollowUpStep(stepToAdd);
		
		if(optionName == null || optionName.trim().isEmpty()) {
			throw new IllegalArgumentException("ScenarioTreeStepSimpleList: Optionsname darf nicht null oder nur Whitespaces sein!");
		}
		this.selectionNames.add(optionName);
		
	}
	
	/**
	 * Setzt die gewählte 
	 * 
	 * @param id
	 */
	public void setSelection(int id){
		
		if(id < 0 || id >= selectionNames.size()) {
			throw new IllegalArgumentException("ScenarioTreeStepSimpleList: Auswahl-Id muss > 0 und < Listengröße " + selectionNames.size() + " sein. Wert: " + id);
		}
		this.selectedOption = id;
		
	}
	
	/**
	 * Liefert den Schritt zurück, der durch die gesetzte Auswahl-Id
	 * gekennzeichnet ist.
	 */
	@Override
	public ScenarioTreeStep getNextStep() {
		
		if(this.selectedOption == -1){
			throw new RuntimeException("ScenarioTreeStepSimpleList: Auswahl-Id muss gesetzt worden sein, um nächsten Schritt abzurufen.");
		}
		
		return this.getFollowUpStep(selectedOption);
	}
	
	
	
}
