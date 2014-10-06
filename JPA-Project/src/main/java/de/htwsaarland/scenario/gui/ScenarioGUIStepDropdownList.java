package de.htwsaarland.scenario.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import de.htwsaarland.scenario.ScenarioTreeStepSimpleList;
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
		
	private static final int DEFAULT_MARGIN = 5;
	private static final int DROPDOWN_BOX_HEIGHT = 20;
	private static final int DROPDOWN_BOX_WIDTH = 270;
	
	// Zugeordneter logischer Schritt
	private ScenarioTreeStepSimpleList scenarioStep;
	
	// Auswahlstrings und Hilfetext
	private String[] selectionOptions;
		
	// Dropdown-Liste. Referenz zur späteren Abfrage der gewählten Option
	private JComboBox<String> selectionBox;
	
	// Die Panels
	private JPanel dropDownPanel;
	private JPanel helpPanel;
	
	public ScenarioGUIStepDropdownList(ScenarioTreeStepSimpleList scenarioStep) {
		
		if(scenarioStep == null) {
			throw new IllegalArgumentException("ScenarioGUIStepDropdownList: scenarioStep may not be empty!");
		}
		
		this.selectionOptions = scenarioStep.getSelectionOptions();
		this.scenarioStep = scenarioStep;
		
		// JPanels generieren
		this.createGUIComponents();
	}

	/**
	 * Hilfsfunktion zur Generierung der beiden JPanels des Schrittes
	 */
	private void createGUIComponents(){
		
		// Panel für die Dropdown-Liste. Wird von der Haupt-GUI platziert
		this.dropDownPanel = new JPanel();
		this.dropDownPanel.setLayout(null);
		//dropDownPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		// Combobox mit den Optionen
		this.selectionBox = new JComboBox<String>();
		selectionBox.setBounds(0, DEFAULT_MARGIN, DROPDOWN_BOX_WIDTH, DROPDOWN_BOX_HEIGHT);
			
		// Optionen hinzufügen (gibt keine "alles"-hinzufügen Option)
		for(int i = 0; i < selectionOptions.length; ++i){
			selectionBox.addItem(selectionOptions[i]);
		}
		
		// Zusammenbau
		this.dropDownPanel.add(selectionBox);
		
		// Das rechte Panel
		this.helpPanel = new JPanel();
		this.helpPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.helpPanel.setLayout(null);
		
		// Text einbauen
		JLabel help = new JLabel(scenarioStep.HELP);
		help.setBounds(DEFAULT_MARGIN, DEFAULT_MARGIN, 
						SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - DEFAULT_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT - DEFAULT_MARGIN);
		help.setVerticalAlignment(SwingConstants.TOP);
		help.setHorizontalAlignment(SwingConstants.LEFT);
		this.helpPanel.add(help);
	
		
	}
	
	
	/**
	 * Liefert die linke Darstellungskomponente eines Dropdownschritts
	 * mit einer Dropdownliste, die die Auswahloptionen enthält.
	 * 
	 * Diese Komponente ist links neben dem Radiobutton zu sehen.
	 */
	@Override
	public JPanel getLeftComponent() {
		return this.dropDownPanel;
	}

	/**
	 * Liefert ein JPanel für die Detailseite zum Szenarioschritt mit
	 * Dropdown-Auswahl. Dieser kann dort einen in der Regel einzeiligen
	 * Hilfetext anzeigen.
	 */
	@Override
	public JPanel getRightComponent() {
		return this.helpPanel;
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
	 * Liefert den Namen der ausgewählten Option der Dropdownliste.
	 * Die String-Werte entsprechen der String-Werte in der String-Liste,
	 * die dem Konstruktor übergeben wurde.
	 */
	public String getSelectionName() {
		return this.selectionBox.getSelectedItem().toString();
	}

	/**
	 * Transferiert die Auswahl der GUI-Komponenten in den darunterliegenden Szenarioschritt,
	 * damit die Szenariologik diese Auswahl weiterverwenden kann.
	 */
	@Override
	public void setSelectionIntoScenarioStep() {
		this.scenarioStep.setSelection(this.getSelection());
		this.scenarioStep.setSelectionName(this.getSelectionName());
	}
	
	@Override
	public void setLeftComponentActivated(boolean enabled){
		this.selectionBox.setEnabled(enabled);
	}
}
