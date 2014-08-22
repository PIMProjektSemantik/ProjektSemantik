package de.htwsaarland.main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

import de.htwsaarland.dao.NotebookDao;
import de.htwsaarland.model.Notebook;
import de.htwsaarland.scenario.gui.ScenarioGUIStepController;


public class Main {

	// Database creation/check trigger
	NotebookDao ndao = new NotebookDao();
	Notebook notebook = new Notebook("Lenovo yoga5");

	// GUI Part
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 716, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 403, 433);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSchritte = new JLabel("Schritte");
		lblSchritte.setBounds(10, 11, 46, 14);
		panel.add(lblSchritte);
		
		JLabel lblAntworten = new JLabel("Antworten");
		lblAntworten.setBounds(122, 11, 74, 14);
		panel.add(lblAntworten);
		
		JRadioButton radioButton_1 = new JRadioButton("CPU");
		radioButton_1.setBounds(6, 199, 109, 23);
		panel.add(radioButton_1);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(121, 200, 245, 20);
		panel.add(comboBox_3);
		
		JRadioButton radioButton_2 = new JRadioButton("RAM");
		radioButton_2.setBounds(6, 229, 109, 23);
		panel.add(radioButton_2);
		
		JButton btnSchrittHoch = new JButton("Schritt hoch");
		btnSchrittHoch.setBounds(10, 369, 109, 23);
		panel.add(btnSchrittHoch);
		
		JButton btnSchrittRunter = new JButton("Schritt runter");
		btnSchrittRunter.setBounds(10, 397, 109, 23);
		panel.add(btnSchrittRunter);
		
		JLabel lblAntwortRechtsWhlen = new JLabel("Antwort rechts w\u00E4hlen");
		lblAntwortRechtsWhlen.setBounds(121, 233, 245, 14);
		panel.add(lblAntwortRechtsWhlen);
		
		JLabel lblHierErscheintNun = new JLabel("So werden die Schritte aussehen");
		lblHierErscheintNun.setBounds(10, 176, 250, 14);
		panel.add(lblHierErscheintNun);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(424, 11, 266, 433);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHierIrgendwasAnzeigen = new JLabel("Zusatzinfos zum aktiven Schritt");
		lblHierIrgendwasAnzeigen.setBounds(32, 186, 198, 14);
		panel_1.add(lblHierIrgendwasAnzeigen);
		
		JLabel lblNichtMitDropdown = new JLabel("Bei DB/OWL auch Tabellen usw.");
		lblNichtMitDropdown.setBounds(32, 208, 198, 14);
		panel_1.add(lblNichtMitDropdown);
		
		
		
		//Test
		ScenarioGUIStepController testcontroller = new ScenarioGUIStepController(panel, panel_1);
		testcontroller.showDemoStep(panel, panel_1);
		

		
		
	}
	
}
