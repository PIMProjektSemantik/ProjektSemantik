package de.htwsaarland.scenario.gui;

import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_WIDTH;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import de.htwsaarland.scenario.ScenarioTreeStepSimpleList;
import de.htwsaarland.scenario.ScenarioTreeStepTwoOptions;

/**
 * GUI Komponente für Szenarioschritte mit zwei Optionen.
 * 
 * In der Regel handelt es sich hier um Ja/Nein-Fragen
 * 
 * Besteht aus zwei Radiobuttons. 
 * 
 * 
 * 
 * @author Stefan
 *
 */
public class ScenarioGUIStepTwoOptionsRadiobuttons extends ScenarioGUIStep {

	private static final int DEFAULT_HELP_LABEL_MARGIN = 10;
	
	// Zugeordneter logischer Schritt
	private ScenarioTreeStepSimpleList scenarioStep;

	// Auswahloptionen und Hilfetext
	private String[] selectionOptions;
	
	// Referenzen zu den Radiobuttons, um ausgewählten Button auszulesen
	private JRadioButton firstRadioButton;
	private JRadioButton secondRadioButton;
	
	// Die Panels
	private JPanel radioButtonPanel;
	private JPanel helpPanel;
	
	/**
	 * Erzeugt eine neues GUI-Objekt für einen Zwei-Options-Szenarioschritt.
	 * Die Indizes der beiden Strings entsprechen dem Rückgabewert bei Abfrage der
	 * ausgewählten Option.
	 * 
	 * @param selectionOptions
	 * @param helpText
	 */
	public ScenarioGUIStepTwoOptionsRadiobuttons(ScenarioTreeStepTwoOptions scenarioStep) {
		
		if(scenarioStep == null) {
			throw new IllegalArgumentException("ScenarioGUIStepTwoOptionsRadiobuttons: scenarioStep may not be empty!");
		}
	
		this.selectionOptions = scenarioStep.getSelectionOptions();
		this.scenarioStep = scenarioStep;
		
		// Gui Komponenten bauen
		this.createGUIComponents();
	}
	
	
	private void createGUIComponents(){
		
		// Panel für die Dropdown-Liste. Wird von der Haupt-GUI platziert
		this.radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(null);
		//dropDownPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		// Combobox mit den Optionen
		this.firstRadioButton = new JRadioButton(this.selectionOptions[0]);
		firstRadioButton.setBounds(0, 10, 120, 20);
		
		this.secondRadioButton = new JRadioButton(this.selectionOptions[1]);
		secondRadioButton.setBounds(130, 10, 120, 20);
		
		// Gruppieren der beiden Radiobuttons
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(firstRadioButton);
		radioGroup.add(secondRadioButton);

		// Zusammenbau
		radioButtonPanel.add(firstRadioButton);
		radioButtonPanel.add(secondRadioButton);
		

		// Das Panel selbst
		this.helpPanel = new JPanel();
		helpPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		helpPanel.setLayout(null);
		
		// Text einbauen
		JLabel help = new JLabel(scenarioStep.HELP);
		help.setBounds(DEFAULT_HELP_LABEL_MARGIN, DEFAULT_HELP_LABEL_MARGIN, 
						SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - DEFAULT_HELP_LABEL_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT - DEFAULT_HELP_LABEL_MARGIN);
		help.setVerticalAlignment(SwingConstants.TOP);
		help.setHorizontalAlignment(SwingConstants.LEFT);
		helpPanel.add(help);
	}
	
	
	@Override
	public JPanel getLeftComponent() {
		return this.radioButtonPanel;
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
	 * Liefert die ausgewählte Option. Der Int-Wert entspricht der Zuordnung der Optionstexte
	 * im String Array, der dem Konstruktor übergeben wurde (0 oder 1).
	 */
	@Override
	public int getSelection() {

		// Man muss nur den einen Knopf abfragen, wenn er nicht gewählt ist, dann der andere
		if(firstRadioButton.isSelected()){
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * Transferiert die Auswahl der GUI-Komponenten in den darunterliegenden Szenarioschritt,
	 * damit die Szenariologik diese Auswahl weiterverwenden kann.
	 */
	@Override
	public void setSelectionIntoScenarioStep() {

		this.scenarioStep.setSelection(this.getSelection());
		
	}

}
