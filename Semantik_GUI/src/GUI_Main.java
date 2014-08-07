import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;


public class GUI_Main {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Main window = new GUI_Main();
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
	public GUI_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 716, 514);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(122, 49, 245, 20);
		panel.add(comboBox);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Nutzertyp");
		rdbtnNewRadioButton.setBounds(6, 48, 109, 23);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Ger\u00E4t");
		rdbtnNewRadioButton_1.setBounds(6, 74, 109, 23);
		panel.add(rdbtnNewRadioButton_1);
		
		JLabel lblSchritte = new JLabel("Schritte");
		lblSchritte.setBounds(10, 11, 46, 14);
		panel.add(lblSchritte);
		
		JLabel lblAntworten = new JLabel("Antworten");
		lblAntworten.setBounds(122, 11, 74, 14);
		panel.add(lblAntworten);
		
		JRadioButton radioButton = new JRadioButton("Zubeh\u00F6r");
		radioButton.setBounds(6, 100, 109, 23);
		panel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("CPU");
		radioButton_1.setBounds(6, 126, 109, 23);
		panel.add(radioButton_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(122, 101, 245, 20);
		panel.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(122, 127, 245, 20);
		panel.add(comboBox_3);
		
		JRadioButton radioButton_2 = new JRadioButton("RAM");
		radioButton_2.setBounds(6, 152, 109, 23);
		panel.add(radioButton_2);
		
		JButton btnSchrittHoch = new JButton("Schritt hoch");
		btnSchrittHoch.setBounds(6, 288, 109, 23);
		panel.add(btnSchrittHoch);
		
		JButton btnSchrittRunter = new JButton("Schritt runter");
		btnSchrittRunter.setBounds(6, 315, 109, 23);
		panel.add(btnSchrittRunter);
		
		JLabel lblAntwortRechtsWhlen = new JLabel("Antwort rechts w\u00E4hlen");
		lblAntwortRechtsWhlen.setBounds(122, 156, 245, 14);
		panel.add(lblAntwortRechtsWhlen);
		
		JLabel lblAppleIpadMini = new JLabel("Apple Ipad Mini");
		lblAppleIpadMini.setBounds(122, 78, 245, 14);
		panel.add(lblAppleIpadMini);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(424, 11, 266, 433);
		frame.getContentPane().add(panel_1);
		
		JLabel lblHierIrgendwasAnzeigen = new JLabel("Hier irgendwas anzeigen, wenn die Frage");
		panel_1.add(lblHierIrgendwasAnzeigen);
		
		JLabel lblNichtMitDropdown = new JLabel("nicht mit Dropdown geht, zb. Tabelle");
		panel_1.add(lblNichtMitDropdown);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
