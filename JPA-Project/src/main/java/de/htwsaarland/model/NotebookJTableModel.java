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
	private String[] columnNames = {"Nr.", "Firma", "Modell", "Betriebs.", "Display", "Akku", "Prozessor", "RAM", "Grafikkarte", "Festplatte", "Preis"};
	
	@SuppressWarnings("rawtypes")
	private Class[] classList = {Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class};
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
			
			data[i][0] = currentNotebook.getId();
			data[i][1] = currentNotebook.getHersteller();
			data[i][2] = currentNotebook.getModell();
			data[i][3] = currentNotebook.getBetriebssystem();
			data[i][4] = currentNotebook.getDisplaygroesse() + " Zoll";
			data[i][5] = currentNotebook.getAkku() + " h";
			data[i][6] = currentNotebook.getCpu();
			data[i][7] = currentNotebook.getRam();
			data[i][8] = currentNotebook.getGrafikkarte();
			data[i][9] = currentNotebook.getFestplatte();
			data[i][10] = currentNotebook.getPreis() + " €";
			
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c){
		return classList[c];
	}
}
