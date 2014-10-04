package de.htwsaarland.owlHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
						}else if (newLine.contains("hasValue")) {
							categoryValues = newLine
									.substring(newLine.indexOf(">") + 1, newLine.lastIndexOf("<"));
							values.get(values.size()-1).add(categoryValues);

						} else if (newLine.contains("onClass")) {
							categoryValues = newLine
									.substring(newLine.indexOf(";") + 1, newLine.lastIndexOf("\""));
							values.get(values.size() - 1).add(categoryValues);
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
	

	/**
	 * @throws IOException
	 * 
	 */
	public ArrayList<ArrayList<String>> getRangeOfCategory(String category,
			String subcategory) {
		String categoryValues;
		String oldLine = "";
		String newLine = "";
		int classCount = 0;
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		try{
			BufferedReader br = new BufferedReader(new FileReader("src/ontologieFinalRDF.owl"));
			while (true) {
				newLine = br.readLine();
				// Letzte Zeile
				if (newLine == null) {
					break;
				}
				// Passendes XML-Element gefunden
				if (newLine.contains("ObjectProperty rdf:about=\"&pc2;" + category
						+ "\"")) {
					while (true) {
						if (newLine.contains("</Class")
								|| newLine.contains("</ObjectProperty"))
							classCount--;
						else if (newLine.contains("<Class")
								|| newLine.contains("<ObjectProperty"))
							classCount++;
	
						if (classCount == 0)
							break;
						// Kategorie extrahieren
						if (newLine.contains("<rdfs:range"))
							while (!newLine.contains("</rdfs:range")) {
								ArrayList<String> value = new ArrayList<String>();
								if (newLine.contains("onProperty")
										&& (values.size() > 0 ? values.get(
												values.size() - 1).size() > 1
												: true)) {
									categoryValues = newLine.substring(
											newLine.indexOf(";") + 1,
											newLine.lastIndexOf("\""));
									value.add(categoryValues);
									values.add(value);
								} else if (newLine.contains("xsd:minInclusive")) {
									categoryValues = newLine.substring(
											newLine.indexOf(">") + 1,
											newLine.lastIndexOf("<"));
									values.get(values.size() - 1).add(
											categoryValues);
								} else if (newLine.contains("xsd:maxInclusive")) {
									categoryValues = newLine.substring(
											newLine.indexOf(">") + 1,
											newLine.lastIndexOf("<"));
									values.get(values.size() - 1).add(
											categoryValues);
								}
	
								newLine = br.readLine();
							}
						newLine = br.readLine();
					}
					// ERGEBNIS: Category-Variable in die Dropdown-Box (statt
					// println())
				}
				oldLine = newLine;
			}
			br.close();
		} catch(IOException e) {
			// TODO: handle exception
		}
		//Rueckgabe wird aufbereitet: Packt die Ranges sortiert zusammen
		ArrayList<ArrayList<String>> rValues = new ArrayList<ArrayList<String>>();
		for (ArrayList<String> arrayList : values) {
			ArrayList<String> nArray = new ArrayList<String>();
			nArray.add(arrayList.get(0));
			nArray.add(arrayList.get(1));
			boolean adden = true;
			//fuegt bereits vorhandener Range den neuen Wert sortiert ein
			if (rValues.size() >= 0) {//gibt es schon angelegte Ranges?
				for (int i = 0; i < rValues.size(); i++) {
					ArrayList<String> current = rValues.get(i);
					if (current.get(0).equals(arrayList.get(0))) {
						if (current.size() == 2) {
							if (Double.valueOf(current.get(1)) < Double
									.valueOf(arrayList.get(1)))
								current.add(arrayList.get(1));
							else
								current.add(1, arrayList.get(1));
						} else {
							current.add(arrayList.get(1));
						}
						adden = false; //Range wurde gefunden
					}
				}
			}
			if (adden) //Range war nicht dabei, daher adden
				rValues.add(nArray);
		}
		return rValues;
	}
	
	/**
	 * @throws IOException
	 * 
	 */
	public ArrayList<ArrayList<String>> getRangeOfDomain(String category) {
		String categoryValues;
		String oldLine = "";
		String newLine = "";
		int classCount = 0;
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		try{
			BufferedReader br = new BufferedReader(new FileReader("src/ontologieFinalRDF.owl"));
			while (true) {
				newLine = br.readLine();
				// Letzte Zeile
				if (newLine == null) {
					break;
				}
				// Passendes XML-Element gefunden
				if (newLine.contains("<rdfs:domain rdf:resource=\"&pc2;" + category + "\"")) {
					while (true) {
						if (newLine.contains("</Class")
								|| newLine.contains("</ObjectProperty"))
							classCount--;
						else if (newLine.contains("<Class")
								|| newLine.contains("<ObjectProperty"))
							classCount++;
	
						if (classCount == 0)
							break;
						// Kategorie extrahieren
						if (newLine.contains("<rdfs:range"))
							while (!newLine.contains("</rdfs:range")) {
								ArrayList<String> value = new ArrayList<String>();
								if (newLine.contains("onProperty")
										&& (values.size() > 0 ? values.get(
												values.size() - 1).size() > 1
												: true)) {
									categoryValues = newLine.substring(
											newLine.indexOf(";") + 1,
											newLine.lastIndexOf("\""));
									value.add(categoryValues);
									values.add(value);
								} else if (newLine.contains("xsd:minInclusive")) {
									categoryValues = newLine.substring(
											newLine.indexOf(">") + 1,
											newLine.lastIndexOf("<"));
									values.get(values.size() - 1).add(
											categoryValues);
								} else if (newLine.contains("xsd:maxInclusive")) {
									categoryValues = newLine.substring(
											newLine.indexOf(">") + 1,
											newLine.lastIndexOf("<"));
									values.get(values.size() - 1).add(
											categoryValues);
								}
	
								newLine = br.readLine();
							}
						newLine = br.readLine();
					}
					// ERGEBNIS: Category-Variable in die Dropdown-Box (statt
					// println())
				}
				oldLine = newLine;
			}
			br.close();
		} catch(IOException e) {
			// TODO: handle exception
		}
		//Rueckgabe wird aufbereitet: Packt die Ranges sortiert zusammen
		ArrayList<ArrayList<String>> rValues = new ArrayList<ArrayList<String>>();
		for (ArrayList<String> arrayList : values) {
			ArrayList<String> nArray = new ArrayList<String>();
			nArray.add(arrayList.get(0));
			nArray.add(arrayList.get(1));
			boolean adden = true;
			//fuegt bereits vorhandener Range den neuen Wert sortiert ein
			if (rValues.size() >= 0) {//gibt es schon angelegte Ranges?
				for (int i = 0; i < rValues.size(); i++) {
					ArrayList<String> current = rValues.get(i);
					if (current.get(0).equals(arrayList.get(0))) {
						if (current.size() == 2) {
							if (Double.valueOf(current.get(1)) < Double
									.valueOf(arrayList.get(1)))
								current.add(arrayList.get(1));
							else
								current.add(1, arrayList.get(1));
						} else {
							current.add(arrayList.get(1));
						}
						adden = false; //Range wurde gefunden
					}
				}
			}
			if (adden) //Range war nicht dabei, daher adden
				rValues.add(nArray);
		}
		return rValues;
	}

}
