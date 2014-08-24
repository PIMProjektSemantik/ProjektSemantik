package de.htwsaarland.scenario.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import static de.htwsaarland.scenario.gui.ScenarioGUIParams.*;

/**
 * Kontrolle aller Darstellungselemente, die im Szenarioablauf dargestellt, geändert
 * und entfernt werden. 
 * 
 * Kontrolliert auch die weiter und zurück Knöpfe usw.
 * 
 * Hier wird die Referenz zur Szenariologik gehalten.
 * 
 * Aktuell noch Dummy. Stell gerade nur Testelemente ohne Szenariokontrolle dar.
 * 
 * @author Stefan
 *
 */
public class ScenarioGUIStepController {


	
	private JPanel leftPanel;
	private JPanel rightPanel;
	
	
	private int currentStepOffset;
	
	
	public ScenarioGUIStepController(JPanel leftPanel, JPanel rightPanel){
		
		this.leftPanel = leftPanel;
		this.rightPanel = rightPanel;
		this.currentStepOffset = 0;
	}
	
	
	
	public void showDemoStep(JPanel left, JPanel right) {
		
		JRadioButton stepActiveButton1 = new JRadioButton("Schritt 1");
		JRadioButton stepActiveButton2 = new JRadioButton("Schritt 2");
		stepActiveButton1.setLocation(15, 50);
		stepActiveButton2.setLocation(15, 100);
		stepActiveButton1.setSize(100, 20);
		stepActiveButton2.setSize(100, 20);
		left.add(stepActiveButton1);
		left.add(stepActiveButton2);
		
		JPanel dropDownPanel = new JPanel();
		dropDownPanel.setLayout(null);
		dropDownPanel.setLocation(120, 40);
		dropDownPanel.setSize(270, 40);
		dropDownPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		JPanel dropDownPanel2 = new JPanel();
		dropDownPanel2.setLayout(null);
		dropDownPanel2.setLocation(120, 90);
		dropDownPanel2.setSize(270, 40);
		dropDownPanel2.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		JComboBox<String> selectionBox = new JComboBox<String>();
		selectionBox.setLocation(10, 10);
		selectionBox.setSize(250, 20);
		
		String[] testArr = {"A","B","C"};
		for(int i = 0; i < testArr.length; ++i){
			selectionBox.addItem(testArr[i]);
		}
		
		dropDownPanel.add(selectionBox);
		
		left.add(dropDownPanel);
		left.add(dropDownPanel2);
		
		right.removeAll();
		
		JLabel helpLabel = new JLabel("Testschritt " + this.currentStepOffset + " links!");
		helpLabel.setSize(100, 20);
		helpLabel.setLocation(30, 30);
		right.add(helpLabel);
		
	}
	

	
	
	public void showDemoStepX() {
		
		// Linkes Auswahlelement
		String[] testArr = {"A","B","C"};
		ScenarioGUIStepDropdownList testStep = new ScenarioGUIStepDropdownList(testArr, "Testschritt " + this.currentStepOffset + " links!");
		
		// Radiobutton
		JRadioButton activeScenarioButton = new JRadioButton();
		activeScenarioButton.setText("Schritt: " + currentStepOffset);
		activeScenarioButton.setBounds(STEP_RADIOBUTTON_X_POS, STEP_RADIOBUTTON_Y_FIRSTPOS + currentStepOffset * STEP_Y_DISTANCE, 
										STEP_RADIOBUTTON_X_SIZE, STEP_RADIOBUTTON_Y_SIZE);

		
		
		
		
		JPanel innerStepPanel = testStep.getLeftComponent();
		innerStepPanel.setBounds(STEP_PANEL_X_POS, STEP_PANEL_Y_FIRSTPOS + currentStepOffset * STEP_Y_DISTANCE, 
									STEP_PANEL_X_SIZE, STEP_PANEL_Y_SIZE);
		
		leftPanel.add(activeScenarioButton);
		leftPanel.add(innerStepPanel);
		leftPanel.repaint();
		leftPanel.revalidate();
		
		// Rechtes Auswahlelement
		JPanel detailPanel = testStep.getRightComponent();
		detailPanel.setBounds(0, 0, SCENARIO_GUI_DETAIL_ELEMENT_WIDTH, SCENARIO_GUI_DETAIL_ELEMENT_HEIGHT);
		
		rightPanel.removeAll();
		rightPanel.add(detailPanel);
		rightPanel.repaint();
		
		this.currentStepOffset++;
	}
	
	
	
	
}
