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

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import de.htwsaarland.scenario.ScenarioHardwareBerater;
import de.htwsaarland.scenario.ScenarioTreeStep;
import de.htwsaarland.scenario.ScenarioTreeStepBeginning;
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
public class ScenarioGUIStepController {

	// Die beiden Panels der Haupt-GUI (links die Schritte und Rechts der Detailbereich)
	private JPanel leftPanel;
	private ArrayList<ScenarioGUIStep> guiStepList;
	private ButtonGroup leftPanelRadioButtonGroup;
	private JPanel rightPanel;

	// Referenz zur Szenariologik
	ScenarioHardwareBerater scenarioHardwareBerater;
	
	public ScenarioGUIStepController(JPanel leftPanel, JPanel rightPanel){
		
		this.leftPanel 					= leftPanel;
		this.rightPanel 				= rightPanel;
		this.scenarioHardwareBerater 	= new ScenarioHardwareBerater();
		this.guiStepList 				= new ArrayList<ScenarioGUIStep>();
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
		
		// GUI Parameter auslesen und in Szenarioschritt übertragen. Auswirkungen werden beim Aufruf
		// von goToNextStep() im Szenario automatisch aktiv, sofern für den nächsten Schritt relevant#
		ScenarioGUIStep currentGUIStep = this.guiStepList.get(this.guiStepList.size() - 1);
		currentGUIStep.setSelectionIntoScenarioStep();
				
		// Aufruf zum nächsten Szenarioschritt, siehe auch obigen Hinweis
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
	 * Fügt der Haupt-GUI die Szenarioschritt-Komponente des aktuellen Schrittes hinzu und aktualisiert
	 * 
	 */
	private void showScenarioStepGUIComponents(String name, ScenarioGUIStep currentGUIStep){
		
		// 1 abziehen, da das aktuelle Element schon in der Liste ist und Index immer 1 unter size
		int currentStepIndex = this.guiStepList.size() - 1;
		
		// Radiobutton erzeugen und einbauen
		JRadioButton activeScenarioButton = new JRadioButton();
		activeScenarioButton.setText(name);
		activeScenarioButton.setBounds(STEP_RADIOBUTTON_X_POS, STEP_RADIOBUTTON_Y_FIRSTPOS + currentStepIndex * STEP_Y_DISTANCE, 
										STEP_RADIOBUTTON_X_SIZE, STEP_RADIOBUTTON_Y_SIZE);
		
		leftPanelRadioButtonGroup.add(activeScenarioButton);
		leftPanelRadioButtonGroup.setSelected(activeScenarioButton.getModel(), true);
		
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
		}  else if (currentStep instanceof ScenarioTreeStepFinish){
			return new ScenarioGUIStepFinish((ScenarioTreeStepFinish)currentStep);
		}
			
		// Derzeit gibt es die obigen Typen
		return null;
	}
	
	public void showDemoStepX() {
			
	}
	
	
	
	
}
