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

	// Standardabstand für Elemente in den Boxen für Schritte und Detailbereich
	public static final int DEFAULT_X_MARGIN = 5;
	public static final int DEFAULT_Y_MARGIN = 5;
	
	// Größe der Schrittliste (die "linke" Seite)
	public static final int SCENARIO_GUI_STEP_LIST_HEIGHT = 500;
	public static final int SCENARIO_GUI_STEP_LIST_WIDTH = 500;
	
	// Größe der linken Komponente (in der Schrittliste)
	public static final int SCENARIO_GUI_STEP_BASE_ELEMENT_HEIGHT = 40;
	public static final int SCENARIO_GUI_STEP_BASE_ELEMENT_WIDTH  = 200;
	
	// Größe der rechten Komponente (Detailfeld, nur zu sehen, wenn Schritt aktiv)
	public static final int SCENARIO_GUI_STEP_DETAIL_ELEMENT_HEIGHT = 500;
	public static final int SCENARIO_GUI_STEP_DETAIL_ELEMENT_WIDTH = 500;
	
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
	
	
}
