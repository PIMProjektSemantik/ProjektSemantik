package de.htwsaarland.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.htwsaarland.dao.NotebookDao;

@SuppressWarnings("serial")
public class NotebookJTableModel extends AbstractTableModel {

	// Datenquelle
	private NotebookDao dbSource;
	private String query;
	
	// Spalten. Können statisch definiert werden, da dies im DAO auch so ist, das als Datenbankinterface dient.
	private String[] columnNames = {"Hersteller", "Modell", "Betriebssystem", "Display", "Akku", "Prozessor", "RAM", "Grafikkarte", "Festplatte", "Preis"};
	private Class[] classList = {String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class};
	private Object[][] data;
	
	/**
	 * 
	 * 
	 * @param dbSource
	 * @param query
	 */
	public NotebookJTableModel(NotebookDao dbSource, String query){
		
		if(dbSource == null){
			throw new IllegalArgumentException("Notebook table model benötigt eine Datenbankquelle != null");
		}
		this.dbSource = dbSource;
		
		if(query == null || query.trim().isEmpty()){
			throw new IllegalArgumentException("Notebook table model query String darf nicht null oder leer sein!");
		}
		
		this.query = query;
		this.getDBContents();
	}

	/**
	 * Hilfsfunktion, um die DB Daten zu holen und in Datenarray zu speichern.
	 */
	public void getDBContents(){
		
		List<Notebook> entries = dbSource.fireQuery(query);
		this.data = new Object[entries.size()][columnNames.length];
		
		for(int i = 0; i < entries.size(); ++i) {
			
			Notebook currentNotebook = entries.get(i);
			
			data[i][0] = currentNotebook.getHersteller();
			data[i][1] = currentNotebook.getModell();
			data[i][2] = currentNotebook.getBetriebssystem();
			data[i][3] = currentNotebook.getDisplaygroesse() + " Zoll";
			data[i][4] = currentNotebook.getAkku() + " h";
			data[i][5] = currentNotebook.getCpu();
			data[i][6] = currentNotebook.getRam();
			data[i][7] = currentNotebook.getGrafikkarte();
			data[i][8] = currentNotebook.getFestplatte();
			data[i][9] = currentNotebook.getPreis() + " €";
			
		}
		
	}
	
	public String[] getColumnNames(){
		return this.columnNames;
	}

	@Override
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
	
	@Override
	public Class getColumnClass(int c){
		return classList[c];
	}
}
