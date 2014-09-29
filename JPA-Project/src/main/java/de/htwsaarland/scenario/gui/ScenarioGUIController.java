package de.htwsaarland.scenario.gui;

import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_WIDTH;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_X_POS;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_X_SIZE;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_Y_FIRSTPOS;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_Y_SIZE;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_RADIOBUTTON_X_POS;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_RADIOBUTTON_X_SIZE;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_RADIOBUTTON_Y_FIRSTPOS;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_RADIOBUTTON_Y_SIZE;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_Y_DISTANCE;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.htwsaarland.scenario.ScenarioHardwareBerater;
import de.htwsaarland.scenario.ScenarioTreeStep;
import de.htwsaarland.scenario.ScenarioTreeStepBeginning;
import de.htwsaarland.scenario.ScenarioTreeStepDBOWLComputerComponents;
import de.htwsaarland.scenario.ScenarioTreeStepDBOWLNotebook;
import de.htwsaarland.scenario.ScenarioTreeStepDBOWLSoftwareAndOS;
import de.htwsaarland.scenario.ScenarioTreeStepDBOWLTablet;
import de.htwsaarland.scenario.ScenarioTreeStepFinish;
import de.htwsaarland.scenario.ScenarioTreeStepSimpleList;
import de.htwsaarland.scenario.ScenarioTreeStepTwoOptions;

/**
 * Kontrolle aller Darstellungselemente, die im Szenarioablauf dargestellt, geändert
 * und entfernt werden. 
 * 
 * Kontrolliert auch die weiter und zurück Knöpfe usw.
 * 
 * Hier wird die Referenz zur Szenariologik gehalten.
 * 
 * Aktuell noch Dummy. Stell gerade nur Testelemente ohne Szenariokontrolle dar.
 * 
 * @author Stefan
 *
 */
public class ScenarioGUIController {

	// Die beiden Panels der Haupt-GUI (links die Schritte und Rechts der Detailbereich)
	// Weitere sind Listen für die Panels und Radiobuttons, die auf der linken Seite entstehen
	// Diese dienen zur einfachen Entfernung bei "Zurück"-Knopf
	private JPanel 						leftPanel;
	private JPanel 						rightPanel;
	private ArrayList<ScenarioGUIStep> 	guiStepList;
	private ArrayList<JPanel>			leftSideStepPanelList;
	private ArrayList<JRadioButton>		leftSideRadioButtonList;
	private ButtonGroup 				leftPanelRadioButtonGroup;


	// Referenz zur Szenariologik
	ScenarioHardwareBerater scenarioHardwareBerater;
	
	public ScenarioGUIController(JPanel leftPanel, JPanel rightPanel) throws IOException{
		
		this.leftPanel 					= leftPanel;
		this.rightPanel 				= rightPanel;
		this.scenarioHardwareBerater 	= new ScenarioHardwareBerater();
		this.guiStepList 				= new ArrayList<ScenarioGUIStep>();
		this.leftSideStepPanelList		= new ArrayList<JPanel>();
		this.leftSideRadioButtonList	= new ArrayList<JRadioButton>();
		this.leftPanelRadioButtonGroup 	= new ButtonGroup();
		
		// Ersten Schritt in die GUI holen

		ScenarioTreeStep currentStep = this.scenarioHardwareBerater.getCurrentStep();
		ScenarioGUIStep currentGUIStep = this.getScenarioGUIStepFromTreeStep(currentStep);
		this.guiStepList.add(currentGUIStep);
		this.showScenarioStepGUIComponents(currentStep.NAME, currentGUIStep);
	}
	
	

	/**
	 * Geht im Szenario einen Schritt vorwärts. F
	 * 
	 */
	public void goForwardInScenario(){
				
		// GUI Parameter auslesen und in Szenarioschritt übertragen.
		ScenarioGUIStep currentGUIStep = this.guiStepList.get(this.guiStepList.size() - 1);
				
		// Blockieren, wenn es der letzte Schritt ist (der setSelection Aufruf ist unkritisch, da er dort leer ist)
		if(currentGUIStep instanceof ScenarioGUIStepFinish){
			return;
		}
			
		// Linke Auswahlelemente des aktuellen Schrittes deaktivieren
		currentGUIStep.setLeftComponentActivated(false);
		
		// Auswahl in logischen Scenarioschritt durchreichen und Aufruf zum nächsten Szenarioschritt, 
		// dies verwendet die zuvor gesetzte Auswahl automatisch weiter 
		currentGUIStep.setSelectionIntoScenarioStep();
		this.scenarioHardwareBerater.goToNextStep();
				
		// GUI-Schritt Objekt holen (definiert das Aussehen der GUI-Elemente des Schritts)
		ScenarioTreeStep currentStep = scenarioHardwareBerater.getCurrentStep();
		System.out.println("Next identified Step: " + currentStep.NAME);
		currentGUIStep = getScenarioGUIStepFromTreeStep(currentStep);
		this.guiStepList.add(currentGUIStep);
		
		// GUI um Auswahlelemente des neuen Schrittes ergänzen.
		this.showScenarioStepGUIComponents(currentStep.NAME, currentGUIStep);
		
	}
	
	/**
	 * Wandert einen Schritt im Szenario zurück
	 * 
	 */
	public void goBackwardsInScenario(){
		
		// Wenn Startschritt, Aufruf ignorieren
		if(this.guiStepList.size() <= 1){
			return;
		}
		
		// Gui Elemente löschen
		this.removeLastScenarioStepGUIComponents();
		this.guiStepList.remove(this.guiStepList.size() - 1);
		
		// Szenario aktualisieren
		this.scenarioHardwareBerater.goToPreviousStep();
	
		// Linke Auswahlelemente des nun untersten Schrittes reaktivieren
		this.guiStepList.get(this.guiStepList.size() - 1).setLeftComponentActivated(true);
		
	}
	
	/**
	 * Fügt der Haupt-GUI die Szenarioschritt-Komponente des aktuellen Schrittes hinzu und aktualisiert
	 * 
	 */
	private void showScenarioStepGUIComponents(String name, ScenarioGUIStep currentGUIStep){
		
		// 1 abziehen, da das aktuelle Element schon in der Liste ist und Index immer 1 unter size
		int currentStepIndex = this.guiStepList.size() - 1;
		
		// Radiobutton erzeugen und einbauen
		JRadioButton activeScenarioButton = new JRadioButton();
		activeScenarioButton.setText(name);
		activeScenarioButton.setEnabled(false);
		activeScenarioButton.setBounds(STEP_RADIOBUTTON_X_POS, STEP_RADIOBUTTON_Y_FIRSTPOS + currentStepIndex * STEP_Y_DISTANCE, 
										STEP_RADIOBUTTON_X_SIZE, STEP_RADIOBUTTON_Y_SIZE);
		
		// Radiobutton in Gruppe setzen und registrieren
		leftPanelRadioButtonGroup.add(activeScenarioButton);
		leftPanelRadioButtonGroup.setSelected(activeScenarioButton.getModel(), true);
		leftSideRadioButtonList.add(activeScenarioButton);
		
		// Linkes Schrittelement Panel
		
		// Panel aus GUI Step Objekt holen und in Schrittpanel-Liste Setzen
		System.out.println(currentGUIStep.getClass().getCanonicalName());
		System.out.println("currentStep: " + (guiStepList.size() - 1));
		JPanel innerStepPanel = currentGUIStep.getLeftComponent();
		innerStepPanel.setBounds(STEP_PANEL_X_POS, STEP_PANEL_Y_FIRSTPOS + currentStepIndex * STEP_Y_DISTANCE, 
									STEP_PANEL_X_SIZE, STEP_PANEL_Y_SIZE);
		
		// Schrittpanel in GUI hinzufügen
		leftPanel.add(activeScenarioButton);
		leftPanel.add(innerStepPanel);
		leftPanel.repaint();
		leftPanel.revalidate();
		
		// Panel registrieren
		leftSideStepPanelList.add(innerStepPanel);
		
		
		// Rechtes Detailpanel
		
		// Rechtes Detailpanel leeren
		rightPanel.removeAll();
		
		// Neues Panel setzen
		JPanel detailPanel = currentGUIStep.getRightComponent();
		detailPanel.setBounds(0, 0, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT);
	
		rightPanel.add(detailPanel);
		rightPanel.repaint();
		rightPanel.revalidate();
	
		
	}
	
	/**
	 * Entfernt die GUI-Komponenten des letzten Szenarioschrittes
	 * und stellt die Detailseite des vorherigen Schrittes wieder her
	 */
	private void removeLastScenarioStepGUIComponents(){

		// Schrittindex des letzten Elements
		int lastStepIndex = this.guiStepList.size() - 1;
		
		// Letzten Radiobutton (vom linken Rand) und JPanel abrufen
		JPanel lastLeftPanel = this.leftSideStepPanelList.get(lastStepIndex);
		JRadioButton lastRadioButton = this.leftSideRadioButtonList.get(lastStepIndex);
		
		// Radiobutton aus Gruppe, Liste und GUI löschen
		this.leftPanelRadioButtonGroup.remove(lastRadioButton);
		this.leftSideRadioButtonList.remove(lastStepIndex);
		this.leftPanel.remove(lastRadioButton);
		
		// Panel aus Liste und GUI löschen
		this.leftSideStepPanelList.remove(lastStepIndex);
		this.leftPanel.remove(lastLeftPanel);
		
		// Neu zeichen
		this.leftPanel.repaint();
		this.leftPanel.revalidate();
		
		// Rechtes Panel leeren
		this.rightPanel.removeAll();

		// Vorheriges rechtes Panel abrufen und wieder darstellen (da der letzte Schritt noch
		// in Schrittliste, also 1 Pos vor lastStepIndex)
		JPanel detailPanel = this.guiStepList.get(lastStepIndex-1).getRightComponent();
		detailPanel.setBounds(0, 0, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT);
			
		rightPanel.add(detailPanel);
		rightPanel.repaint();
		rightPanel.revalidate();
	}
	
	
	
	/**
	 * Erstellt aus einem logischen Szenarioschritt Objekt das GUI-Schritt Objekt
	 * 
	 * @param currentStep
	 * @return
	 */
	private ScenarioGUIStep getScenarioGUIStepFromTreeStep(ScenarioTreeStep currentStep) {
				
		// Gibt das passende GUI Schritt Objekt zurück zum Szenarioschrittobjekt
		// und fügt das Szenarioschrittobjekt dem GUI Schritt Objekt per Konstruktor hinzu
		if(currentStep instanceof ScenarioTreeStepBeginning){
			return new ScenarioGUIStepBeginning((ScenarioTreeStepBeginning)currentStep);
		} else if(currentStep instanceof ScenarioTreeStepTwoOptions) {
			return new ScenarioGUIStepTwoOptionsRadiobuttons((ScenarioTreeStepTwoOptions)currentStep);
		} else if(currentStep instanceof ScenarioTreeStepSimpleList) {
			return new ScenarioGUIStepDropdownList((ScenarioTreeStepSimpleList)currentStep);
		} else if (currentStep instanceof ScenarioTreeStepFinish){
			return new ScenarioGUIStepFinish((ScenarioTreeStepFinish)currentStep);
		} else if (currentStep instanceof ScenarioTreeStepDBOWLTablet){
			return new ScenarioGUIStepDatabaseRequestTablet((ScenarioTreeStepDBOWLTablet)currentStep);
		} else if (currentStep instanceof ScenarioTreeStepDBOWLNotebook){
			return new ScenarioGUIStepDatabaseRequestNotebook((ScenarioTreeStepDBOWLNotebook)currentStep);
		} else if (currentStep instanceof ScenarioTreeStepDBOWLComputerComponents){
			return new ScenarioGUIStepDatabaseRequestComputerComponents((ScenarioTreeStepDBOWLComputerComponents)currentStep);
		} else if (currentStep instanceof ScenarioTreeStepDBOWLSoftwareAndOS){
			return new ScenarioGUIStepDatabaseRequestSoftwareAndOS((ScenarioTreeStepDBOWLSoftwareAndOS)currentStep);
		}
			
		// Derzeit gibt es die obigen Typen
		return null;
	}
	
	
	
}
