package de.htwsaarland.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.htwsaarland.dao.ArbeitsspeicherDao;

@SuppressWarnings("serial")
public class ArbeitsspeicherJTableModel extends AbstractTableModel {

	// Datenquelle
	private ArbeitsspeicherDao dbSource;
	private String query;
	
	// Spalten. Können statisch definiert werden, da dies im DAO auch so ist, das als Datenbankinterface dient.
	private String[] columnNames = {"Prod.Nr.", "Name", "Typ", "Größe", "Takt", "Preis"};
	
	@SuppressWarnings("rawtypes")
	private Class[] classList = {Integer.class, String.class, String.class, String.class, String.class, String.class};
	private Object[][] data;
	
	/**
	 * 
	 * 
	 * @param dbSource
	 * @param query
	 */
	public ArbeitsspeicherJTableModel(ArbeitsspeicherDao dbSource, String query){
		
		if(dbSource == null){
			throw new IllegalArgumentException("Arbeitsspeicher table model benötigt eine Datenbankquelle != null");
		}
		this.dbSource = dbSource;
		
		if(query == null || query.trim().isEmpty()){
			throw new IllegalArgumentException("Arbeitsspeicher table model query String darf nicht null oder leer sein!");
		}
		
		this.query = query;
		this.getDBContents();
	}

	/**
	 * Hilfsfunktion, um die DB Daten zu holen und in Datenarray zu speichern.
	 */
	public void getDBContents(){
		
		List<Arbeitsspeicher> entries = dbSource.fireQuery(query);
		this.data = new Object[entries.size()][columnNames.length];
		
		for(int i = 0; i < entries.size(); ++i) {
			
			Arbeitsspeicher currentSpeicher = entries.get(i);
			
			data[i][0] = currentSpeicher.getId();
			data[i][1] = currentSpeicher.getName();
			data[i][2] = currentSpeicher.getTyp();
			data[i][3] = currentSpeicher.getGroesse();
			data[i][4] = currentSpeicher.getTakt();
			data[i][5] = currentSpeicher.getPreis() + " €";
			
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
