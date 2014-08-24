package de.htwsaarland.scenario;

/**
 * Szenarioschritt, der Auswahldaten auf Datenbank- und Ontologieabfragen basierend
 * generiert. Für die konkreten Abfragen verschiedener Schritte wird
 * man je eine Ableitung mit der Logik für die konkreten Datenbanktabellen
 * und Ontologieabfragen benötigen.
 * 
 * Dieser "Dummy"-Typ ist im wesentlichen zur Identifizierung dieses Schritt-Typs
 * im GUI-Bereich mit einer einheitlichen Darstellungskomponente
 * vorgesehen sowie zur allgemeinen Kategorisierung der Klassenstruktur der Schritte
 * 
 * In diesen Ableitungen können dann setMethoden für Filterparameter
 * eingebracht werden. (zur Beeinflussung der DB-Resultate neben
 * Daten aus der Ontologie).
 * 
 * Datenbankschritte dieser Art können ebenfalls mehrere Nachfolgeschritte 
 * besitzen.
 * 
 * 
 * 
 * 
 * @author Stefan
 *
 */
public abstract class ScenarioTreeStepSimpleDatabaseRequest extends ScenarioTreeStep {

	public ScenarioTreeStepSimpleDatabaseRequest(String name, String help) {
		super(name, help);

	}

	
}
