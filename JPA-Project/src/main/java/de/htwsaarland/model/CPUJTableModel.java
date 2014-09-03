package de.htwsaarland.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.htwsaarland.dao.CPUDao;

@SuppressWarnings("serial")
public class CPUJTableModel extends AbstractTableModel {

	// Datenquelle
	private CPUDao dbSource;
	private String query;
	
	// Spalten. Können statisch definiert werden, da dies im DAO auch so ist, das als Datenbankinterface dient.
	private String[] columnNames = {"Prod.Nr.", "Hersteller", "Name", "Takt", "Kerne", "Leistung", "Preis"};
	
	@SuppressWarnings("rawtypes")
	private Class[] classList = {Integer.class, String.class, String.class, String.class, Integer.class, String.class, String.class};
	private Object[][] data;
	
	/**
	 * 
	 * 
	 * @param dbSource
	 * @param query
	 */
	public CPUJTableModel(CPUDao dbSource, String query){
		
		if(dbSource == null){
			throw new IllegalArgumentException("Betriebssystem table model benötigt eine Datenbankquelle != null");
		}
		this.dbSource = dbSource;
		
		if(query == null || query.trim().isEmpty()){
			throw new IllegalArgumentException("Betriebssystem table model query String darf nicht null oder leer sein!");
		}
		
		this.query = query;
		this.getDBContents();
	}

	/**
	 * Hilfsfunktion, um die DB Daten zu holen und in Datenarray zu speichern.
	 */
	public void getDBContents(){
		
		List<CPU> entries = dbSource.fireQuery(query);
		this.data = new Object[entries.size()][columnNames.length];
		
		for(int i = 0; i < entries.size(); ++i) {
			
			CPU currentCPU = entries.get(i);
			
			data[i][0] = currentCPU.getId();
			data[i][1] = currentCPU.getHersteller();
			data[i][2] = currentCPU.getName();
			data[i][3] = currentCPU.getTakt() + " GHz";
			data[i][4] = currentCPU.getKerne();
			data[i][5] = currentCPU.getLeistung() + " Watt";
			data[i][6] = currentCPU.getPreis() + " €";
			
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
