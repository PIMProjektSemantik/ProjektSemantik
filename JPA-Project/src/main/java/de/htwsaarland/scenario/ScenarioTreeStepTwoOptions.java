package de.htwsaarland.scenario;

/**
 * Szenarioschritt mit genau zwei Optionen.
 * 
 * Kann z.B. in der GUI auch ohne Drop-Down-Liste
 * oder Tabelle mit zwei direkten Elementen dargestellt werden.
 * 
 * @author Stefan
 *
 */
public class ScenarioTreeStepTwoOptions extends ScenarioTreeStepSimpleList {

	public static final String DEFAULT_FIRST_TEXT = "Ja";
	public static final String DEFAULT_SECOND_TEXT = "Nein";
	

	/**
	 * Konstruktor des Zwei-Optionen Schritts
	 * 
	 * @param name		Schritt-Name
	 * @param first		Auswahlschritt der ersten Option unter "Ja"
	 * @param second	Auswahlschritt der zweiten Option unter "Nein"
	 */
	public ScenarioTreeStepTwoOptions(String name, ScenarioTreeStep first, ScenarioTreeStep second) {
		
		this(name, first, DEFAULT_FIRST_TEXT, second, DEFAULT_SECOND_TEXT);
		
	}
	
	/**
	 * Konstruktor des Zwei-Optionen Schritts mit benutzerdefinierten Optionstexten
	 * 
	 * @param name			Schritt-Name
	 * @param first			Auswahlschritt der ersten Option unter wählbarem Text
	 * @param firstText		Text der ersten Option
	 * @param second		Auswahlschritt der zweiten Option unter wählbarem Text
	 * @param secondText	Text der zweiten Option
	 */
	public ScenarioTreeStepTwoOptions(String name, ScenarioTreeStep first, String firstText, ScenarioTreeStep second, String secondText) {
		
		super(name);
		if (first == null || second == null) { 
			throw new IllegalArgumentException("ScenarioTreeStepTwoOptions: Beide Schritte müssen gesetzt sein!");			
		}
		
		if (firstText == null || firstText.trim().isEmpty() || secondText == null || secondText == null) {
			throw new IllegalArgumentException("ScenarioTreeStepTwoOptions: Beide Texte müssen gesetzt und nicht leer sein!");
		}
		
		// Schritte registrieren
		this.addFollowUpStep(first, firstText);
		this.addFollowUpStep(second, secondText);
		
	}
	
	
	@Override
	public void addFollowUpStep(ScenarioTreeStep stepToAdd) {
		
		if(this.getFollowUpSteps().length >= 2) {
			throw new RuntimeException("ScenarioTreeStepTwoOptions: Es dürfen nur zwei Folgeschritte existieren!");
		}
		
		super.addFollowUpStep(stepToAdd);
	};
	
}
