package de.htwsaarland.ontology;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implementiert Abfragen für Inhalte der Ontologie zum HardwareBerater
 * 
 * @author Stefan
 *
 */
public class OntologyRequest {

	public static String[] getMainUsageCategories() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("src/ontologieFinalRDF_Fixed.owl"));		
		String oldLine = "";
		String newLine = "";
		ArrayList<String> categories = new ArrayList<String>();
		
		while (true) {
			newLine = br.readLine();
			
			// Letzte Zeile
			if (newLine == null) {
				break;
			}
			
			// Passendes XML-Element gefunden
			if (newLine.contains("rdfs:subClassOf rdf:resource=\"&pc2;Anwendung\"")) {
				
				// Kategorie extrahieren
				String category = oldLine.substring(oldLine.indexOf(";")+1);
				category = category.substring(0, category.length()-2);
				
				// In die Liste hängen
				categories.add(category);
		
			}
			
			oldLine = newLine;
		}
		br.close();
		
		// Gibt das Array mit dem Listeninhalt gefüllt zurück
		String[] categoriesRet = new String[categories.size()];
		return categories.toArray(categoriesRet);
	}
	
	
	public static String[] getOperatingSystems() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("src/ontologieFinalRDF_Fixed.owl"));		
		String oldLine = "";
		String newLine = "";
		ArrayList<String> osList = new ArrayList<String>();
		
		while (true) {
			newLine = br.readLine();
			
			// Letzte Zeile
			if (newLine == null) {
				break;
			}
			
			// Passendes XML-Element gefunden
			if (newLine.contains("rdfs:subClassOf rdf:resource=\"&pc2;Betriebssystem\"")) {
				
				// Fehlerhaftes "Fire-OS"-Element ignorieren
				if (oldLine.contains("rdfs:label")) {
					continue;
				}
				
				// Kategorie extrahieren
				String os = oldLine.substring(oldLine.indexOf(";")+1);
				os = os.substring(0, os.length()-2);
				
				// In die Liste hängen
				osList.add(os);
		
			}
			
			oldLine = newLine;
		}
		br.close();
		
		// Gibt das Array mit dem Listeninhalt gefüllt zurück
		String[] osRet = new String[osList.size()];
		return osList.toArray(osRet);
	}
	
}
