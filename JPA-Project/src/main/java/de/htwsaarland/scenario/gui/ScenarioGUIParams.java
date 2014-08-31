package de.htwsaarland.scenario.gui;

/**
 * Sammelstelle für Pixelparameter etc. im Bezug auf die GUI-Darstellung
 * der Szenarioschritte
 * 
 * Für das GUI Konzept der Szenarioschritte siehe ScenarioGUIStep Klasse.
 * 
 * @author Stefan
 *
 */
public class ScenarioGUIParams {

	// Größe des linken Gesamtpanels 
	public static final int SCENARIO_GUI_STEP_LIST_HEIGHT = 500;
	public static final int SCENARIO_GUI_STEP_LIST_WIDTH = 500;
	
	// Bereich der Szenarioschritte links, oberer und linker Abstand
	public static final int SCENARIO_GUI_LEFT_AREA_X_MARGIN = 10;
	public static final int SCENARIO_GUI_LEFT_AREA_Y_MARGIN = 30;
	
	// Szenarioschrittgröße. X pos von Radiobutton und Panel,
	// y Pos für RButton und Panel des ersten Schrittes, y Abstand zwischen Schritten
	public static final int STEP_RADIOBUTTON_X_POS		= 5;
	public static final int STEP_RADIOBUTTON_X_SIZE		= 170;
	public static final int STEP_RADIOBUTTON_Y_FIRSTPOS	= 45; 
	public static final int STEP_RADIOBUTTON_Y_SIZE		= 20;
	public static final int STEP_PANEL_X_POS			= 200;
	public static final int STEP_PANEL_X_SIZE			= 250;
	public static final int STEP_PANEL_Y_FIRSTPOS		= 40;
	public static final int STEP_PANEL_Y_SIZE			= 25;
	public static final int STEP_Y_DISTANCE				= 25;
	
	// Größe der rechten Komponente (Detailfeld, nur zu sehen, wenn Schritt aktiv)
	public static final int SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT = 529;
	public static final int SCENARIO_GUI_DETAIL_ELEMENT_WIDTH = 650;
	
}
