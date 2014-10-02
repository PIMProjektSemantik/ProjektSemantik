package de.htwsaarland.ontology;

import java.util.ArrayList;

import de.htwsaarland.owlHelper.OWLParser;

/**
 * Implementiert Abfragen für Inhalte der Ontologie zum HardwareBerater
 * 
 * @author Stefan
 *
 */
public class OntologyRequest {

	public static String[] getMainUsageCategories() {
		OWLParser parser = new OWLParser();
		ArrayList<String> catList = parser.getValuesOfCategory("Anwendung");
		
		// Gibt das Array mit dem Listeninhalt gefüllt zurück
		String[] categories = new String[catList.size()];
		return catList.toArray(categories);
	}
	
	
	public static String[] getOperatingSystems() {
		OWLParser parser = new OWLParser();
		ArrayList<String> osList = parser.getValuesOfCategory("Betriebssystem");
		
		// Gibt das Array mit dem Listeninhalt gefüllt zurück
		String[] osRet = new String[osList.size()];
		return osList.toArray(osRet);
	}
	
	public static String getPerformance(String category) {
		String performance = "";
		OWLParser parser = new OWLParser();
		ArrayList<ArrayList<String>> restrictions = parser.getRestrictionOfCategory(category);
		for (ArrayList<String> arrayList : restrictions) {
			if (arrayList.get(0).equals("hatLeistung")) {
				performance = arrayList.get(1);
			}
		}
		return performance;
		
	}
	
}
