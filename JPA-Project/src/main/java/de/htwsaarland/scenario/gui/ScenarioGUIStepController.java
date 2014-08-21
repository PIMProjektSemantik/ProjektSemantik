package de.htwsaarland.scenario.gui;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

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

	
	public static final int SCENARIO_GUI_LEFT_AREA_X_MARGIN = 10;
	public static final int SCENARIO_GUI_LEFT_AREA_Y_MARGIN = 30;
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	
	private int currentStepOffset;
	
	
	public ScenarioGUIStepController(JPanel leftPanel, JPanel rightPanel){
		
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
		
	}
	
	
	
	public void showDemoStep() {
		
		// Radiobutton
		JRadioButton activeScenarioButton = new JRadioButton();
		activeScenarioButton.setText("Demoschritt: ");
		activeScenarioButton.setLocation(SCENARIO_GUI_LEFT_AREA_X_MARGIN, SCENARIO_GUI_LEFT_AREA_Y_MARGIN);
		
		// Linkes Auswahlelement
		String[] testArr = {"A","B","C"};
		ScenarioGUIStepDropdownList testStep = new ScenarioGUIStepDropdownList(testArr, "Ein Testschritt");
		
		JPanel innerStepPanel = testStep.getLeftComponent();
		innerStepPanel.setLocation(SCENARIO_GUI_LEFT_AREA_X_MARGIN + 500, SCENARIO_GUI_LEFT_AREA_Y_MARGIN + 100);
		
		leftPanel.add(testStep.getLeftComponent());
		//leftPanel.invalidate();
		
		// Rechtes Auswahlelement
		//rightPanel.removeAll();
		//rightPanel.add(testStep.getRightComponent());
		//rightPanel.invalidate();
		
	}
	
	
	
	
}
