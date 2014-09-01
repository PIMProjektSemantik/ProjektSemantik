package de.htwsaarland.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.htwsaarland.dao.TabletDao;

@SuppressWarnings("serial")
public class TabletJTableModel extends AbstractTableModel {

	// Datenquelle
	private TabletDao dbSource;
	private String query;
	
	// Spalten. Können statisch definiert werden, da dies im DAO auch so ist, das als Datenbankinterface dient.
	private String[] columnNames = {"Nr.", "Hersteller", "Modell", "Betriebs.", "Display", "Prozessor", "Speicher", "Funk", "Preis"};
	
	@SuppressWarnings("rawtypes")
	private Class[] classList = {Integer.class, String.class, String.class, String.class, Integer.class, String.class, String.class, String.class, String.class};
	private Object[][] data;
	
	/**
	 * 
	 * 
	 * @param dbSource
	 * @param query
	 */
	public TabletJTableModel(TabletDao dbSource, String query){
		
		if(dbSource == null){
			throw new IllegalArgumentException("Tablet table model benötigt eine Datenbankquelle != null");
		}
		this.dbSource = dbSource;
		
		if(query == null || query.trim().isEmpty()){
			throw new IllegalArgumentException("Tablet table model query String darf nicht null oder leer sein!");
		}
		
		this.query = query;
		this.getDBContents();
	}

	/**
	 * Hilfsfunktion, um die DB Daten zu holen und in Datenarray zu speichern.
	 */
	public void getDBContents(){
		
		List<Tablet> entries = dbSource.fireQuery(query);
		this.data = new Object[entries.size()][columnNames.length];
		
		for(int i = 0; i < entries.size(); ++i) {
			
			Tablet currentTablet = entries.get(i);
			
			data[i][0] = currentTablet.getId();
			data[i][1] = currentTablet.getHersteller();
			data[i][2] = currentTablet.getModell();
			data[i][3] = currentTablet.getBetriebssystem();
			data[i][4] = currentTablet.getDisplaygroesse();
			data[i][5] = currentTablet.getProzessor();
			data[i][6] = currentTablet.getSpeicher() + " GB";
			data[i][7] = currentTablet.getConnectionType();
			data[i][8] = currentTablet.getPreis() + " €";
			
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
