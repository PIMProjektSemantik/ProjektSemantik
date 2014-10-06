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


	public static String[] getBudgetTypes() {
		OWLParser parser = new OWLParser();
		ArrayList<String> osList = parser.getValuesOfCategory("Budget");
		
		// Gibt das Array mit dem Listeninhalt gefüllt zurück
		String[] osRet = new String[osList.size()];
		return osList.toArray(osRet);
	}
	
	public static String[] getBudgetForCategory(String budgetType, String category) {
		OWLParser parser = new OWLParser();
		ArrayList<ArrayList<String>> osList = parser.getRangeOfDomain(budgetType);
		ArrayList<String> returnValue = new ArrayList<String>();

		for (ArrayList<String> arrayList : osList) {
			if(arrayList.get(0).contains(category)) {
				returnValue = arrayList;
				break; //Element gefunden... Suche beendet
			}
		}
		// Gibt das Array mit dem Listeninhalt gefüllt zurück
		String[] osRet = new String[returnValue.size()];
		return returnValue.toArray(osRet);
	}
	
	public static String getBudgetType(String category) {
		OWLParser parser = new OWLParser();
		ArrayList<ArrayList<String>> osList = parser.getRestrictionOfCategory(category);
		ArrayList<String> returnValue = new ArrayList<String>();

		for (ArrayList<String> arrayList : osList) {
			if(arrayList.get(0).contains("Budget")) {
				returnValue = arrayList;
				break; //Element gefunden... Suche beendet
			}
		}
		return returnValue.get(1);
	}


	public static String[] getDiskSize(String diskSizeType) {
		OWLParser parser = new OWLParser();
		ArrayList<ArrayList<String>> osList = parser.getRangeOfDomain(diskSizeType);
		ArrayList<String> returnValue = osList.get(0);
		// Gibt das Array mit dem Listeninhalt gefüllt zurück
		String[] osRet = new String[returnValue.size()];
		return returnValue.toArray(osRet);
	}
	
	public static String[] getDiskSizeType() {
		OWLParser parser = new OWLParser();
		ArrayList<String> osList = parser.getValuesOfCategory("FestplattenGroesse");
		
		// Gibt das Array mit dem Listeninhalt gefüllt zurück
		String[] osRet = new String[osList.size()];
		return osList.toArray(osRet);
	}
}
