package de.htwsaarland.owlHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class OWLParser {


	public ArrayList<String> getValuesOfCategory(String category) {
		String categoryValues;
		String newLine = "";
		int classCount = 0;
		boolean next=true;
		ArrayList<String> values = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/ontologieFinalRDF.owl"));
			while (next) {
				newLine = br.readLine();
				// Letzte Zeile
				if (newLine == null) {
					break;
				}
				// Passendes XML-Element gefunden
				if (newLine.contains("Class rdf:about=\"&pc2;" + category + "\"")) {
					//Classe gefunden, Suche endet mit Classen-Ende
					if(newLine.contains("/class"))
						next = false;
					while (!newLine.contains("/unionOf")) {
						if(newLine.contains("</Class"))
							classCount--;
						else if(newLine.contains("<Class"))
							classCount++;
						
						if(classCount == 0)
							break;
						// Kategorie extrahieren
						if (newLine.contains("Description")) {
							categoryValues = newLine
									.substring(newLine.indexOf(";") + 1, newLine.lastIndexOf("\""));
							values.add(categoryValues);
						}


						newLine = br.readLine();
					}
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return values;
		
	}


	public ArrayList<ArrayList<String>> getRestrictionOfCategory(String category) {
		String categoryValues;
		String newLine = "";
		int classCount = 0;
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/ontologieFinalRDF.owl"));
			while (true) {
				newLine = br.readLine();
				// Letzte Zeile
				if (newLine == null) {
					break;
				}
				// Passendes XML-Element gefunden
				if (newLine.contains("Class rdf:about=\"&pc2;" + category + "\"")) {
					while (true) {
						if(newLine.contains("</Class"))
							classCount--;
						else if(newLine.contains("<Class"))
							classCount++;
						
						if(classCount == 0)
							break;
						// Kategorie extrahieren
						if (newLine.contains("onProperty")) {
							categoryValues = newLine
									.substring(newLine.indexOf(";") + 1, newLine.lastIndexOf("\""));
							ArrayList<String> value = new ArrayList<String>();
							value.add(categoryValues);
							values.add(value);
						}if (newLine.contains("hasValue")) {
							categoryValues = newLine
									.substring(newLine.indexOf(">") + 1, newLine.lastIndexOf("<"));
							values.get(values.size()-1).add(categoryValues);
						}


						newLine = br.readLine();
					}
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return values;
		
	}
}
