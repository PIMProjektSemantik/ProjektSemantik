package de.htwsaarland.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.htwsaarland.dao.GrafikkarteDao;

@SuppressWarnings("serial")
public class GrafikkarteJTableModel extends AbstractTableModel {

	// Datenquelle
	private GrafikkarteDao dbSource;
	private String query;
	
	// Spalten. Können statisch definiert werden, da dies im DAO auch so ist, das als Datenbankinterface dient.
	private String[] columnNames = {"Prod.Nr.", "Hersteller", "Modell", "Leistung", "Speicher", "Preis"};
	
	@SuppressWarnings("rawtypes")
	private Class[] classList = {Integer.class, String.class, String.class, String.class, String.class, String.class};
	private Object[][] data;
	
	/**
	 * 
	 * 
	 * @param dbSource
	 * @param query
	 */
	public GrafikkarteJTableModel(GrafikkarteDao dbSource, String query){
		
		if(dbSource == null){
			throw new IllegalArgumentException("Grafikkarte table model benötigt eine Datenbankquelle != null");
		}
		this.dbSource = dbSource;
		
		if(query == null || query.trim().isEmpty()){
			throw new IllegalArgumentException("Grafikkarte table model query String darf nicht null oder leer sein!");
		}
		
		this.query = query;
		this.getDBContents();
	}

	/**
	 * Hilfsfunktion, um die DB Daten zu holen und in Datenarray zu speichern.
	 */
	public void getDBContents(){
		
		List<Grafikkarte> entries = dbSource.fireQuery(query);
		this.data = new Object[entries.size()][columnNames.length];
		
		for(int i = 0; i < entries.size(); ++i) {
			
			Grafikkarte currentGrafikkarte = entries.get(i);
			
			data[i][0] = currentGrafikkarte.getId();
			data[i][1] = currentGrafikkarte.getHersteller();
			data[i][2] = currentGrafikkarte.getName();
			data[i][3] = currentGrafikkarte.getRechenleistung() + " GFlops";
			data[i][4] = currentGrafikkarte.getSpeichergroesse() + " GB";
			data[i][5] = currentGrafikkarte.getPreis() + " €";
			
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
