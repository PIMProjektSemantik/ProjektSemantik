package de.htwsaarland.scenario.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import static de.htwsaarland.scenario.gui.ScenarioGUIParams.*;

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
		this.helpText = helpText;
	}
	
	@Override
	public JPanel getLeftComponent() {

		// Panel für die Dropdown-Liste. Position wird vom Verwender gesetzt
		JPanel dropDownPanel = new JPanel();
		dropDownPanel.setLayout(null);
		dropDownPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		JComboBox<String> selectionBox = new JComboBox<String>();
		selectionBox.setBounds(0, 10, 250, 20);
				
		for(int i = 0; i < selectionOptions.length; ++i){
			selectionBox.addItem(selectionOptions[i]);
		}
		
		dropDownPanel.add(selectionBox);
		return dropDownPanel;
		
	}

	@Override
	public JPanel getRightComponent() {
		
		JPanel helpPanel = new JPanel();
		helpPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		helpPanel.setLayout(null);
		
		
		JLabel help = new JLabel(helpText);
		help.setBounds(40, 40, 200, 200);
	
		helpPanel.add(help);
		
		return helpPanel;
	}

	
	
	
	
}
