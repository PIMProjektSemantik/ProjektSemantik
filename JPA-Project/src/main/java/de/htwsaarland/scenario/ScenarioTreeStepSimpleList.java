package de.htwsaarland.scenario;


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
	private String selectedOptionName;

	
	/**
	 * Erstellt einen Schritt mit Schrittnamen. Name darf nicht null
	 * oder nur aus Leerzeichen sein.
	 * 
	 * @param name Name des Schrittes
	 */
	public ScenarioTreeStepSimpleList(String name, String help) {
		super(name, help);
		selectedOption = -1;
	}
	
	public int getSelection(){
		return this.selectedOption;
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

	public void setSelectionName(String selectionName) {
		selectedOptionName = selectionName;
	}
	
	public String getSelectionName() {
		return selectedOptionName;
	}
}
