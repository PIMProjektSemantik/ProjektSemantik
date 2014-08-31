package de.htwsaarland.scenario.gui;

import javax.swing.JPanel;

/**
 * Darstellungskomponente für Szenarioschritte
 * 
 * Ist sehr allgemein dient nur als gemeinsame Oberklasse der eigentlichen
 * Darstellungselemente.  Ableitungen dieser Klasse sind erfoderlich, um
 * konkrete Schritttypen darzustellen.
 *
 * Jede Komponente besteht aus zwei Teilen. Das erste Element ist allgemein das Element, 
 * das in der Schrittliste erscheint und das zweite Element ermöglicht detaillierte Anzeigen, 
 * ist aber nur zu sehen wenn der Schritt aktiv ist.
 * 
 * Bei einfachen Schritten erscheint in der Schrittliste ein 
 * Dropdownfeld oder Radiobuttons (Ja/Nein-Prinzip) und in der
 * Detailanzeige Hilfetexte oder garnichts.
 * 
 * Datenbankabfrage-Schritte können das Detailfeld zur Anzeige der
 * Auswahltabelle verwenden, während sie in der Schrittliste
 * nur ein Textlabel mit einem Hinweis auf die Tabelle anzeigen werden.
 * 
 * @author Stefan
 *
 */
public abstract class ScenarioGUIStep {

	
	/**
	 * Aktiviert oder deaktiviert die Inhalte des Schrittlistenelementes 
	 */
	public void setLeftComponentActivated(boolean enabled){ 
		// Es gibt Schritte , die hier keine Auswahlen haben, dh. kann die Methode leer sein
	}
	
	/**
	 * Liefert die erste GUI Komponente (Schrittlistenelement) des Szenarioschritts
	 * 
	 * @return
	 */
	public abstract JPanel getLeftComponent();
	
	/**
	 * Liefert die zweite GUI Komponente (Detailelement) des Szenarioschritts
	 * 
	 * @return
	 */
	public abstract JPanel getRightComponent();
	
	/**
	 * Transferiert die Auswahl der GUI-Komponenten in den darunterliegenden Szenarioschritt,
	 * damit die Szenariologik diese Auswahl weiterverwenden kann.
	 */
	public abstract void setSelectionIntoScenarioStep();
	
	/**
	 * Liefert die in den GUI-Komponenten des Schritts ausgewählte Option
	 * 
	 * @return
	 */
	public abstract int getSelection();
	
	
}
