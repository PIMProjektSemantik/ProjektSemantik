package de.htwsaarland.scenario.gui;

import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_WIDTH;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_X_SIZE;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_Y_SIZE;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import de.htwsaarland.dao.NotebookDao;
import de.htwsaarland.model.NotebookJTableModel;
import de.htwsaarland.scenario.ScenarioTreeStepDBOWLNotebook;

public class ScenarioGUIStepDatabaseRequestNotebook extends ScenarioGUIStep {

	private static final int DEFAULT_LABEL_MARGIN = 5;
	private ScenarioTreeStepDBOWLNotebook scenarioStep;
	
	private JPanel leftNoticePanel;
	private JPanel rightTablePanel;
	
	public ScenarioGUIStepDatabaseRequestNotebook(ScenarioTreeStepDBOWLNotebook scenarioStep){
		
		if(scenarioStep == null) {
			throw new IllegalArgumentException("ScenarioGUIStepDatabaseRequestNotebook: scenarioStep may not be null!");
		}
		this.scenarioStep = scenarioStep;
	
		this.createGUIComponents();
	}
	
	
	private void createGUIComponents(){
		
		// Panel für die Dropdown-Liste. Wird von der Haupt-GUI platziert
		this.leftNoticePanel = new JPanel();
		leftNoticePanel.setLayout(null);
		
		// Text einbauen
		JLabel startText = new JLabel(scenarioStep.HELP);
		startText.setBounds(0, DEFAULT_LABEL_MARGIN, 
						STEP_PANEL_X_SIZE - DEFAULT_LABEL_MARGIN, STEP_PANEL_Y_SIZE - DEFAULT_LABEL_MARGIN);
		startText.setVerticalAlignment(SwingConstants.TOP);
		startText.setHorizontalAlignment(SwingConstants.LEFT);
		leftNoticePanel.add(startText);
		
		// Das Panel selbst
		this.rightTablePanel = new JPanel();
		rightTablePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		rightTablePanel.setLayout(null);
		
		// Text einbauen
		NotebookJTableModel tableModel = new NotebookJTableModel(new NotebookDao(), this.scenarioStep.generateQuery());
		JTable table = new JTable(tableModel);
		table.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN, 
						SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT - 2 * DEFAULT_LABEL_MARGIN);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setMaxWidth(65);
		table.getColumnModel().getColumn(5).setMaxWidth(40);
		table.getColumnModel().getColumn(6).setMinWidth(75);
		table.getColumnModel().getColumn(7).setMaxWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(140);
		table.getColumnModel().getColumn(10).setMaxWidth(70);
		
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN, 
				SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT - 2* DEFAULT_LABEL_MARGIN);
		
		
		// Tabelle einbauen
		this.rightTablePanel.add(pane);
	}
		
	
	
	
	
	@Override
	public JPanel getLeftComponent() {
		return this.leftNoticePanel;
	}

	@Override
	public JPanel getRightComponent() {
		return this.rightTablePanel;
	}

	@Override
	public void setSelectionIntoScenarioStep() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSelection() {
		return 0;
	}

}
