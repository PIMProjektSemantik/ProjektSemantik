package de.htwsaarland.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import de.htwsaarland.dao.NotebookDao;
import de.htwsaarland.model.Notebook;
import de.htwsaarland.scenario.gui.ScenarioGUIController;


public class Main {

	public static ScenarioGUIController testController;
	
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
		frame.setResizable(false);
		frame.setBounds(100, 100, 1167, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 482, 529);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSchritte = new JLabel("Schritte");
		lblSchritte.setBounds(10, 11, 46, 14);
		panel.add(lblSchritte);
		
		JLabel lblAntworten = new JLabel("Antworten");
		lblAntworten.setBounds(200, 11, 74, 14);
		panel.add(lblAntworten);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(502, 11, 650, 529);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblHierIrgendwasAnzeigen = new JLabel("Bitte \"Schritt runter\" drücken");
		lblHierIrgendwasAnzeigen.setBounds(32, 186, 198, 14);
		panel_1.add(lblHierIrgendwasAnzeigen);
		
		JLabel lblNichtMitDropdown = new JLabel("Hier stehen Hilfen etc.");
		lblNichtMitDropdown.setBounds(32, 208, 198, 14);
		panel_1.add(lblNichtMitDropdown);
		
		
		
		//Test
		testController = new ScenarioGUIController(panel, panel_1);
		
		JButton btnSchrittRunter = new JButton("Schritt runter");
		btnSchrittRunter.setBounds(151, 551, 131, 23);
		frame.getContentPane().add(btnSchrittRunter);
		
		JButton btnSchrittHoch = new JButton("Schritt hoch");
		btnSchrittHoch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testController.goBackwardsInScenario();
			}
		});
		btnSchrittHoch.setBounds(10, 551, 131, 23);
		frame.getContentPane().add(btnSchrittHoch);
		btnSchrittRunter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testController.goForwardInScenario();
			}
		});
		//testcontroller.showDemoStep(panel, panel_1);
	

		
		
	}
	
}
