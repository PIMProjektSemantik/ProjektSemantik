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

import de.htwsaarland.dao.ArbeitsspeicherDao;
import de.htwsaarland.dao.FestplatteDao;
import de.htwsaarland.dao.GrafikkarteDao;
import de.htwsaarland.model.ArbeitsspeicherJTableModel;
import de.htwsaarland.model.FestplatteJTableModel;
import de.htwsaarland.model.GrafikkarteJTableModel;
import de.htwsaarland.scenario.ScenarioTreeStepDBOWLComputerComponents;

public class ScenarioGUIStepDatabaseRequestComputerComponents extends ScenarioGUIStep {

	private static final int DEFAULT_LABEL_MARGIN = 5;
	private ScenarioTreeStepDBOWLComputerComponents scenarioStep;
	
	private JPanel leftNoticePanel;
	private JPanel rightTablePanel;
	
	public ScenarioGUIStepDatabaseRequestComputerComponents(ScenarioTreeStepDBOWLComputerComponents scenarioStep){
		
		if(scenarioStep == null) {
			throw new IllegalArgumentException("ScenarioGUIStepDatabaseRequestComputerComponents: scenarioStep may not be null!");
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
		JLabel tableTitleCPU = new JLabel("Prozessoren");
		tableTitleCPU.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 20);
		JLabel tableTitleRAM = new JLabel("Arbeitsspeicher");
		tableTitleRAM.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN + 130, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 20);
		JLabel tableTitleGraphics = new JLabel("Grafikkarten");
		tableTitleGraphics.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN + 260, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 20);
		JLabel tableTitleStorage = new JLabel("Festplatten");
		tableTitleStorage.setBounds(DEFAULT_LABEL_MARGIN, DEFAULT_LABEL_MARGIN + 390, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 20);
		
		// Prozessortabelle
		// Tabelle fehlt noch
		
		
		// Arbeitsspeichertabelle
		ArbeitsspeicherJTableModel tableModelRAM = new ArbeitsspeicherJTableModel(new ArbeitsspeicherDao(), this.scenarioStep.generateQueryRAM());
		JTable tableRAM = new JTable(tableModelRAM);
		tableRAM.setBounds(0, 0, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 100);
		tableRAM.getColumnModel().getColumn(0).setMaxWidth(60);
	
		JScrollPane paneRAM = new JScrollPane(tableRAM);
		paneRAM.setBounds(DEFAULT_LABEL_MARGIN, 160, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 100);
		
		// Grafikkartentabelle
		GrafikkarteJTableModel tableModelGraphics = new GrafikkarteJTableModel(new GrafikkarteDao(), this.scenarioStep.generateQueryGraphics());
		JTable tableGraphics = new JTable(tableModelGraphics);
		tableGraphics.setBounds(0, 0, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 100);
		tableGraphics.getColumnModel().getColumn(0).setMaxWidth(60);
		
		JScrollPane paneGraphics = new JScrollPane(tableGraphics);
		paneGraphics.setBounds(DEFAULT_LABEL_MARGIN, 290, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 100);
		
		// Festplattentabelle
		FestplatteJTableModel tableModelStorage = new FestplatteJTableModel(new FestplatteDao(), this.scenarioStep.generateQueryStorage());
		JTable tableStorage = new JTable(tableModelStorage);
		tableStorage.setBounds(0, 0, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 100);
		tableStorage.getColumnModel().getColumn(0).setMaxWidth(60);
		
		JScrollPane paneStorage = new JScrollPane(tableStorage);
		paneStorage.setBounds(DEFAULT_LABEL_MARGIN, 420, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH - 2 * DEFAULT_LABEL_MARGIN, 100);
		
		// Tabelle einbauen
		this.rightTablePanel.add(tableTitleCPU);
		this.rightTablePanel.add(tableTitleRAM);
		this.rightTablePanel.add(tableTitleGraphics);
		this.rightTablePanel.add(tableTitleStorage);
		this.rightTablePanel.add(paneRAM);
		this.rightTablePanel.add(paneGraphics);
		this.rightTablePanel.add(paneStorage);
		
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
