package de.htwsaarland.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.htwsaarland.dao.FestplatteDao;

@SuppressWarnings("serial")
public class FestplatteJTableModel extends AbstractTableModel {

	// Datenquelle
	private FestplatteDao dbSource;
	private String query;
	
	// Spalten. Können statisch definiert werden, da dies im DAO auch so ist, das als Datenbankinterface dient.
	private String[] columnNames = {"Prod.Nr.", "Hersteller", "Modell", "Art", "Größe", "Preis"};
	
	@SuppressWarnings("rawtypes")
	private Class[] classList = {Integer.class, String.class, String.class, String.class, String.class, String.class};
	private Object[][] data;
	
	/**
	 * 
	 * 
	 * @param dbSource
	 * @param query
	 */
	public FestplatteJTableModel(FestplatteDao dbSource, String query){
		
		if(dbSource == null){
			throw new IllegalArgumentException("Festplatte table model benötigt eine Datenbankquelle != null");
		}
		this.dbSource = dbSource;
		
		if(query == null || query.trim().isEmpty()){
			throw new IllegalArgumentException("Festplatte table model query String darf nicht null oder leer sein!");
		}
		
		this.query = query;
		this.getDBContents();
	}

	/**
	 * Hilfsfunktion, um die DB Daten zu holen und in Datenarray zu speichern.
	 */
	public void getDBContents(){
		
		List<Festplatte> entries = dbSource.fireQuery(query);
		this.data = new Object[entries.size()][columnNames.length];
		
		for(int i = 0; i < entries.size(); ++i) {
			
			Festplatte currentFestplatte = entries.get(i);
			
			data[i][0] = currentFestplatte.getId();
			data[i][1] = currentFestplatte.getHersteller();
			data[i][2] = currentFestplatte.getName();
			data[i][3] = currentFestplatte.getArt();
			data[i][4] = currentFestplatte.getGroesse();
			data[i][5] = currentFestplatte.getPreis() + " €";
			
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
