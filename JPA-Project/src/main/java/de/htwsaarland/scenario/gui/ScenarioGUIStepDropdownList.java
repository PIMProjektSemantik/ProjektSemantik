package de.htwsaarland.scenario.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
		
	private static final int DEFAULT_HELP_LABEL_MARGIN = 10;
	
	// Auswahlstrings und Hilfetext
	private String[] selectionOptions;
	private String helpText;
	
	// Dropdown-Liste. Referenz zur späteren Abfrage der gewählten Option
	private JComboBox<String> selectionBox;
	
	// Die Panels
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	public ScenarioGUIStepDropdownList(String[] selectionOptions, String helpText) {
		
		if(selectionOptions == null || selectionOptions.length < 1) {
			throw new IllegalArgumentException("ScenarioGUIStepDropdownList: String array may not be null or empty!");
		}
		
		if(helpText == null || helpText.trim().length() < 1) {
			throw new IllegalArgumentException("ScenarioGUIStepDropdownList: Help text may not be null or empty!");
		}
		
		this.selectionOptions = selectionOptions;
		this.helpText = helpText;
		
		// JPanels generieren
		this.createGUIComponents();
	}
	
	/**
	 * Liefert die linke Darstellungskomponente eines Dropdownschritts
	 * mit einer Dropdownliste, die die Auswahloptionen enthält.
	 * 
	 * Diese Komponente ist links neben dem Radiobutton zu sehen.
	 */
	@Override
	public JPanel getLeftComponent() {
		return this.leftPanel;
	}

	/**
	 * Liefert ein JPanel für die Detailseite zum Szenarioschritt mit
	 * Dropdown-Auswahl. Dieser kann dort einen in der Regel einzeiligen
	 * Hilfetext anzeigen.
	 */
	@Override
	public JPanel getRightComponent() {
		return this.rightPanel;
	}

	/**
	 * Liefert die ausgewählte Option der Dropdownliste.
	 * Die int-Werte entsprechen der Anordnung in der String-Liste,
	 * die dem Konstruktor übergeben wurde.
	 */
	@Override
	public int getSelection() {
		return this.selectionBox.getSelectedIndex();
	}

	/**
	 * Hilfsfunktion zur Generierung der beiden JPanels des Schrittes
	 */
	private void createGUIComponents(){
		
		// Panel für die Dropdown-Liste. Wird von der Haupt-GUI platziert
		this.leftPanel = new JPanel();
		this.leftPanel.setLayout(null);
		//dropDownPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		// Combobox mit den Optionen
		JComboBox<String> selectionBox = new JComboBox<String>();
		selectionBox.setBounds(0, 10, 250, 20);
			
		// Optionen hinzufügen (gibt keine "alles"-hinzufügen Option)
		for(int i = 0; i < selectionOptions.length; ++i){
			selectionBox.addItem(selectionOptions[i]);
		}
		
		// Zusammenbau
		this.leftPanel.add(selectionBox);
		
		// Das rechte Panel
		this.rightPanel = new JPanel();
		this.rightPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.rightPanel.setLayout(null);
		
		// Text einbauen
		JLabel help = new JLabel(helpText);
		help.setBounds(DEFAULT_HELP_LABEL_MARGIN, DEFAULT_HELP_LABEL_MARGIN, 
						SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - DEFAULT_HELP_LABEL_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT - DEFAULT_HELP_LABEL_MARGIN);
		help.setVerticalAlignment(SwingConstants.TOP);
		help.setHorizontalAlignment(SwingConstants.LEFT);
		this.rightPanel.add(help);
	
		
	}
	
	
}
