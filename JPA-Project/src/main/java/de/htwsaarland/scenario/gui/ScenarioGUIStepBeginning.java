package de.htwsaarland.scenario.gui;

import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_WIDTH;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_X_SIZE;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_Y_SIZE;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import de.htwsaarland.scenario.ScenarioTreeStepBeginning;

/**
 * GUI-Darstellung des Beginnschrittes
 * 
 * @author Stefan
 *
 */
public class ScenarioGUIStepBeginning extends ScenarioGUIStep {

	private static final int DEFAULT_LABEL_MARGIN = 5;
	
	// Zugeordneter logischer Schritt
	private ScenarioTreeStepBeginning scenarioStep; 
	
	// Die Panels
	private JPanel startTextPanel;
	private JPanel helpPanel;
	
	public ScenarioGUIStepBeginning(ScenarioTreeStepBeginning scenarioStep){
		
		if(scenarioStep == null) {
			throw new IllegalArgumentException("ScenarioGUIStepBeginning: scenarioStep may not be empty!");
		}
		this.scenarioStep = scenarioStep;
		
		// Gui Komponenten bauen
		this.createGUIComponents();
}


private void createGUIComponents(){
	
	// Panel für die Dropdown-Liste. Wird von der Haupt-GUI platziert
	this.startTextPanel = new JPanel();
	startTextPanel.setLayout(null);
	
	// Text einbauen
	JLabel startText = new JLabel(scenarioStep.HELP);
	startText.setBounds(0, DEFAULT_LABEL_MARGIN, 
					STEP_PANEL_X_SIZE - DEFAULT_LABEL_MARGIN, STEP_PANEL_Y_SIZE - DEFAULT_LABEL_MARGIN);
	startText.setVerticalAlignment(SwingConstants.TOP);
	startText.setHorizontalAlignment(SwingConstants.LEFT);
	startTextPanel.add(startText);
	
	// Das Panel selbst
	this.helpPanel = new JPanel();
	helpPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	helpPanel.setLayout(null);
	
	// Text einbauen
	JLabel help = new JLabel(scenarioStep.HELP);
	help.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN, 
					SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - DEFAULT_LABEL_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT - DEFAULT_LABEL_MARGIN);
	help.setVerticalAlignment(SwingConstants.TOP);
	help.setHorizontalAlignment(SwingConstants.LEFT);
	helpPanel.add(help);
}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public JPanel getLeftComponent() {
		return this.startTextPanel;
	}

	@Override
	public JPanel getRightComponent() {
		return this.helpPanel;
	}
	
	/**
	 *
	 * Setzt normalerweise die GUI-Auswahl in den Szenarioschritt ein, aber
	 * im Spezialfall des vorgelagerten Beginnschrittes ohne Funktion.
	 *
	 */
	@Override
	public void setSelectionIntoScenarioStep() {
				
	}

	/**
	 * Liefert normalerweise die Auswahl, aber im Spezialfall des vorgelagerten
	 * Beginnschrittes ohne Funktion.
	 */
	@Override
	public int getSelection() {
		return 0;
	}

}
