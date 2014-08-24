package de.htwsaarland.scenario.gui;

import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_WIDTH;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * GUI Komponente für Szenarioschritte mit zwei Optionen.
 * 
 * In der Regel handelt es sich hier um Ja/Nein-Fragen
 * 
 * Besteht aus einem Dropdown-Feld in der Schrittliste
 * und einem Beschreibungstext für den Detailbereich
 * 
 * @author Stefan
 *
 */
public class ScenarioGUIStepTwoOptionsRadiobuttons extends ScenarioGUIStep {

private static final int DEFAULT_HELP_LABEL_MARGIN = 10;
	
	private String[] selectionOptions;
	private String helpText;
	
	public ScenarioGUIStepTwoOptionsRadiobuttons(String[] selectionOptions, String helpText) {
		
		if(selectionOptions == null || selectionOptions.length != 2) {
			throw new IllegalArgumentException("ScenarioGUIStepDropdownList: String array may not be null and must have 2 selections!");
		}
		
		if(helpText == null || helpText.trim().length() < 1) {
			throw new IllegalArgumentException("ScenarioGUIStepDropdownList: Help text may not be null or empty!");
		}
		
		this.selectionOptions = selectionOptions;
		this.helpText = helpText;
	}
	
	
	@Override
	public JPanel getLeftComponent() {

		// Panel für die Dropdown-Liste. Wird von der Haupt-GUI platziert
				JPanel radioButtonPanel = new JPanel();
				radioButtonPanel.setLayout(null);
				//dropDownPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
				
				// Combobox mit den Optionen
				JRadioButton firstOptionButton = new JRadioButton(this.selectionOptions[0]);
				firstOptionButton.setBounds(0, 10, 120, 20);
				
				JRadioButton secondOptionButton = new JRadioButton(this.selectionOptions[1]);
				secondOptionButton.setBounds(130, 10, 120, 20);
				
				// Gruppieren der beiden Radiobuttons
				ButtonGroup radioGroup = new ButtonGroup();
				radioGroup.add(firstOptionButton);
				radioGroup.add(secondOptionButton);
	
				// Zusammenbau
				radioButtonPanel.add(firstOptionButton);
				radioButtonPanel.add(secondOptionButton);
				return radioButtonPanel;
				
					
	}


	/**
	 * Liefert ein JPanel für die Detailseite zum Szenarioschritt mit
	 * Dropdown-Auswahl. Dieser kann dort einen in der Regel einzeiligen
	 * Hilfetext anzeigen.
	 */
	@Override
	public JPanel getRightComponent() {
		
		// Das Panel selbst
		JPanel helpPanel = new JPanel();
		helpPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		helpPanel.setLayout(null);
		
		// Text einbauen
		JLabel help = new JLabel(helpText);
		help.setBounds(DEFAULT_HELP_LABEL_MARGIN, DEFAULT_HELP_LABEL_MARGIN, 
						SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - DEFAULT_HELP_LABEL_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT - DEFAULT_HELP_LABEL_MARGIN);
		help.setVerticalAlignment(SwingConstants.TOP);
		help.setHorizontalAlignment(SwingConstants.LEFT);
		helpPanel.add(help);
		
		return helpPanel;
	}

}
