package de.htwsaarland.scenario.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI Komponente für Szenarioschritte mit Auswahlliste
 * 
 * Besteht aus einem Dropdown-Feld in der Schrittliste
 * und einem Beschreibungstext für den Detailbereich
 * 
 * @author Stefan
 *
 */
public class ScenarioGUIStepDropdownList extends ScenarioGUIStep {
	
	private String[] selectionOptions;
	private String helpText;
	
	public ScenarioGUIStepDropdownList(String[] selectionOptions, String helpText) {
		
		if(selectionOptions == null || selectionOptions.length < 1) {
			throw new IllegalArgumentException("ScenarioGUIStepDropdownList: String array may not be null or empty!");
		}
		
		if(helpText == null || helpText.trim().length() < 1) {
			throw new IllegalArgumentException("ScenarioGUIStepDropdownList: Help text may not be null or empty!");
		}
		
		this.selectionOptions = selectionOptions;
		
	}
	
	@Override
	public JPanel getLeftComponent() {

		JPanel dropDownPanel = new JPanel();
		dropDownPanel.setLayout(null);
		dropDownPanel.setSize(SCENARIO_GUI_STEP_BASE_ELEMENT_WIDTH, SCENARIO_GUI_STEP_BASE_ELEMENT_HEIGHT);
		
		JComboBox<String> selectionBox = new JComboBox<String>();
		selectionBox.setLocation(DEFAULT_X_MARGIN, DEFAULT_Y_MARGIN);
		selectionBox.setSize(SCENARIO_GUI_STEP_BASE_ELEMENT_WIDTH - 2 * DEFAULT_X_MARGIN, SCENARIO_GUI_STEP_BASE_ELEMENT_HEIGHT - 2 * DEFAULT_Y_MARGIN);
		
		for(int i = 0; i < selectionOptions.length; ++i){
			selectionBox.addItem(selectionOptions[i]);
		}
		
		dropDownPanel.add(selectionBox);
		return dropDownPanel;
		
	}

	@Override
	public JPanel getRightComponent() {
		
		JPanel helpPanel = new JPanel();
		helpPanel.setLayout(null);
		helpPanel.setSize(SCENARIO_GUI_STEP_DETAIL_ELEMENT_WIDTH, SCENARIO_GUI_STEP_DETAIL_ELEMENT_HEIGHT);
		
		JLabel help = new JLabel(helpText);
		help.setLocation(DEFAULT_X_MARGIN, DEFAULT_Y_MARGIN);
		
		return helpPanel;
	}

	
	
	
	
}
