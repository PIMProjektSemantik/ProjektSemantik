package de.htwsaarland.scenario.gui;

import static de.htwsaarland.scenario.gui.ScenarioGUIParams.SCENARIO_GUI_DETAIL_ELEMENT_WIDTH;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_X_SIZE;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.STEP_PANEL_Y_SIZE;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import de.htwsaarland.dao.BetriebssystemDao;
import de.htwsaarland.dao.SoftwareDao;
import de.htwsaarland.model.BetriebssystemJTableModel;
import de.htwsaarland.model.SoftwareJTableModel;
import de.htwsaarland.scenario.ScenarioTreeStepDBOWLSoftwareAndOS;

public class ScenarioGUIStepDatabaseRequestSoftwareAndOS extends ScenarioGUIStep {

	private static final int DEFAULT_LABEL_MARGIN = 5;
	private ScenarioTreeStepDBOWLSoftwareAndOS scenarioStep;
	
	private JPanel leftNoticePanel;
	private JPanel rightTablePanel;
	
	public ScenarioGUIStepDatabaseRequestSoftwareAndOS(ScenarioTreeStepDBOWLSoftwareAndOS scenarioStep){
		
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
		
		// Tabellentitel
		JLabel tableTitleOS = new JLabel("Betriebssysteme");
		tableTitleOS.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 20);
		JLabel tableTitleSoftware = new JLabel("Software");
		tableTitleSoftware.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN + 260, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 20);
		
		// Betriebssystemtabelle
		BetriebssystemJTableModel tableModelOS = new BetriebssystemJTableModel(new BetriebssystemDao(), this.scenarioStep.generateQueryOS());
		JTable tableOS = new JTable(tableModelOS);
		tableOS.setBounds(0, 0, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 230);
		tableOS.getColumnModel().getColumn(0).setMaxWidth(60);
		
		JScrollPane paneOS = new JScrollPane(tableOS);
		paneOS.setBounds(DEFAULT_LABEL_MARGIN, 30, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 230);
		
		// Softwaretabelle
		SoftwareJTableModel tableModelSoftware = new SoftwareJTableModel(new SoftwareDao(), this.scenarioStep.generateQuerySoftware());
		JTable tableGraphics = new JTable(tableModelSoftware);
		tableGraphics.setBounds(0, 0, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 230);
		tableGraphics.getColumnModel().getColumn(0).setMaxWidth(60);
		
		JScrollPane paneSoftware = new JScrollPane(tableGraphics);
		paneSoftware.setBounds(DEFAULT_LABEL_MARGIN, 290, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 230);
		
		
		// Tabelle einbauen
		this.rightTablePanel.add(tableTitleOS);
		this.rightTablePanel.add(tableTitleSoftware);
		this.rightTablePanel.add(paneOS);
		this.rightTablePanel.add(paneSoftware);

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
