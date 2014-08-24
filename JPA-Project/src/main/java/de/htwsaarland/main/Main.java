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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main {

	public static ScenarioGUIStepController testController;
	
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
		frame.setBounds(100, 100, 901, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 400, 450);
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
		radioButton_1.setBounds(10, 394, 109, 23);
		panel.add(radioButton_1);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(122, 395, 245, 20);
		panel.add(comboBox_3);
		
		JRadioButton radioButton_2 = new JRadioButton("RAM");
		radioButton_2.setBounds(10, 420, 109, 23);
		panel.add(radioButton_2);
		
		JLabel lblAntwortRechtsWhlen = new JLabel("Antwort rechts w\u00E4hlen");
		lblAntwortRechtsWhlen.setBounds(122, 424, 245, 14);
		panel.add(lblAntwortRechtsWhlen);
		
		JLabel lblHierErscheintNun = new JLabel("So werden die Schritte aussehen");
		lblHierErscheintNun.setBounds(10, 367, 250, 14);
		panel.add(lblHierErscheintNun);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(421, 11, 450, 450);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHierIrgendwasAnzeigen = new JLabel("Zusatzinfos zum aktiven Schritt");
		lblHierIrgendwasAnzeigen.setBounds(32, 186, 198, 14);
		panel_1.add(lblHierIrgendwasAnzeigen);
		
		JLabel lblNichtMitDropdown = new JLabel("Bei DB/OWL auch Tabellen usw.");
		lblNichtMitDropdown.setBounds(32, 208, 198, 14);
		panel_1.add(lblNichtMitDropdown);
		
		
		
		//Test
		testController = new ScenarioGUIStepController(panel, panel_1);
		
		JButton btnSchrittRunter = new JButton("Schritt runter");
		btnSchrittRunter.setBounds(150, 472, 131, 23);
		frame.getContentPane().add(btnSchrittRunter);
		
		JButton btnSchrittHoch = new JButton("Schritt hoch");
		btnSchrittHoch.setBounds(10, 472, 131, 23);
		frame.getContentPane().add(btnSchrittHoch);
		btnSchrittRunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testController.showDemoStepX();
			}
		});
		//testcontroller.showDemoStep(panel, panel_1);
	

		
		
	}
	
}
