package de.htwsaarland.scenario;

import java.util.ArrayList;
import java.util.List;

import de.htwsaarland.scenario.selectionLists.AdditionalScreenCategory;
import de.htwsaarland.scenario.selectionLists.MainUsage;
import de.htwsaarland.scenario.selectionLists.MobileDeviceCategory;
import de.htwsaarland.scenario.selectionLists.OperatingSystemComputer;
import de.htwsaarland.scenario.selectionLists.OperatingSystemMobile;
import de.htwsaarland.scenario.selectionLists.PriceBudgetGlobal;
import de.htwsaarland.scenario.selectionLists.internal.CPUPrice;
import de.htwsaarland.scenario.selectionLists.internal.CPUSpeed;
import de.htwsaarland.scenario.selectionLists.internal.GraphicsPrice;
import de.htwsaarland.scenario.selectionLists.internal.GraphicsSpeed;
import de.htwsaarland.scenario.selectionLists.internal.RAMPrice;
import de.htwsaarland.scenario.selectionLists.internal.RAMSize;
import de.htwsaarland.scenario.selectionLists.internal.RAMSpeed;

/**
 * Repräsentiert den aktuellen Stand der Auswahloptionen.
 * 
 * Speichert Anwendungszweck, Betriebsystem, Budgetkategorie, usw.
 * ebenso die Ja/Nein-Fragen wie WLAN, Mobiles Internet, Mobiles Arbeiten, usw.
 * 
 * Hinzu kommen noch Auswahlantworten, die auf Datenbankinhalten basieren. Diese
 * sind per Id des Eintrags speicherbar.
 * 
 * Auch der aktuelle Schritt wird erfasst. Dazu gibt es für jeden Schritt
 * einen boolean-Wert, um herauszufinden, ob der Schritt im bisherigen Verlauf
 * passiert wurde. Bei abweichenden Wegen zum selben Schritt kann man
 * so sofort herausfinden, ob bestimmte Auswahlwerte hier abzuholen sind oder nicht.
 * Ebenso kann eine derartige Eigenschaft ein Filterkriterium bei einer Datenbankabfrage 
 * darstellen.
 * 
 * stepId definiert den aktuellen Schritt selbst.
 * 
 * @author Stefan
 *
 */
public class ScenarioHardwareBerater {

	// Fortschrittsposition im Szenario
	private ScenarioTreeStep 			currentStep					= null;
	private ArrayList<ScenarioTreeStep> stepPath					= null;
	
	// Referenzen zu den erstellten Schrittobjekten (für Vergleiche im Steppath zb.)
	ScenarioTreeStepFinish 				stepFinish								= null;
	ScenarioTreeStepDBOWLAccessory 		stepAdditionalAccessoryFromDB			= null;
	ScenarioTreeStepTwoOptions 			stepAdditionalAccessoryYesNo			= null;
	ScenarioTreeStepSimpleList 			stepAdditionalScreenRequirementType		= null;
	ScenarioTreeStepTwoOptions 			stepAdditionalScreenRequirementsYesNo	= null;
	ScenarioTreeStepTwoOptions 			stepAdditionalScreenYesNo				= null;
	ScenarioTreeStepTwoOptions 			stepMobileInternetYesNo					= null;
	ScenarioTreeStepTwoOptions 			stepWLANYesNo							= null;
	ScenarioTreeStepTwoOptions 			stepSSDYesNo							= null;
	ScenarioTreeStepTwoOptions 			stepHighAmountOfData					= null;
	ScenarioTreeStepSimpleList 			stepOperatingSystemComputer				= null;
	ScenarioTreeStepSimpleList 			stepOperatingSystemMobile				= null;
	ScenarioTreeStepSimpleList 			stepMobileDeviceType					= null;
	ScenarioTreeStepTwoOptions 			stepMobileUsageYesNo					= null;
	ScenarioTreeStepSimpleList 			stepBudget								= null;
	ScenarioTreeStepSimpleList 			stepMainUsage							= null;
	ScenarioTreeStepBeginning			stepBeginning							= null;
	
	// Über die GUI auswählbare Optionen
	private int 						mainUsageId					= -1;
	private int 						mobileDeviceCategoryId		= -1;
	private int							additionalScreenCategoryId	= -1;
	private int 						operatingSystemComputerId	= -1;
	private int							operatingSystemMobileId		= -1;
	private int							budgetId					= -1;
	
	// Derzeit nicht per GUI gesetzt (folgt automatisch dem Budget-Schritt)
	private CPUPrice					cpuPrice					= null;
	private CPUSpeed					cpuSpeed					= null;
	private GraphicsPrice				graphicsPrice				= null;
	private GraphicsSpeed				graphicsSpeed				= null;
	private RAMPrice					ramPrice					= null;
	private RAMSize						ramSize						= null;
	private RAMSpeed					ramSpeed					= null;
	
	// Zusätzliche Ja/Nein Antworten
	private boolean						additionalAccessoryNeeded 	= false;
	private boolean						additionalScreenNeeded		= false;
	private boolean						additionalScreenRequirements= false;
	private boolean						wlanAvailable				= false;
	private boolean						mobileInternetRequested		= false;
	private boolean						fastBootSSDRequested		= false;
	private boolean						highAmountOfStorageRequested= false;
	private boolean 					mobileUsageRequested		= false;

			
	// Id-Werte für Tabellen-Auswahlen (CPU, RAM, Grafik wie oben automatisch nach Budget??)
	private List<Integer>				accessoryList				= new ArrayList<Integer>();
	private int							tabletId					= -1;
	private int 						notebookId					= -1;
	private int							storageId					= -1;
	private int							cpuId						= -1;
	private int							ramId						= -1;
	private int							graphicsId					= -1;
	
	
	public ScenarioHardwareBerater(){
		
		// Konstruktion des Szenario-Ablaufs
		this.buildScenario();
		
		// Startschritt setzen
		this.currentStep = this.stepBeginning;
		this.stepPath.add(this.stepBeginning);
	}
	
	/**
	 * Erstellt den Szenariobaum als Struktur verknüpfter Szenarioschritt Objekte
	 */
	private void buildScenario(){
		
		// Schritte von hinten nach vorne konstruieren, damit die Nachfolger
		// stets direkt vorhanden sind, wenn der darauf beruhende Schritt 
		// definiert wird.
		
		// Endschritt
		stepFinish = new ScenarioTreeStepFinish("Abschluss");
		
		// Zubehörschritt (Zubehörliste)
		stepAdditionalAccessoryFromDB = new ScenarioTreeStepDBOWLAccessory("Zubehör wählen");
		stepAdditionalAccessoryFromDB.addFollowUpStep(stepFinish);
	
		// Zubehörschritt (Ja/Nein)
		stepAdditionalAccessoryYesNo = new ScenarioTreeStepTwoOptions("Zubehör", stepAdditionalAccessoryFromDB, "Ja", stepFinish, "Nein");
			
		// Monitorschritt (besondere Anforderung wählen)
		stepAdditionalScreenRequirementType = new ScenarioTreeStepSimpleList("Monitoranforderungen");
		stepAdditionalScreenRequirementType.addFollowUpStep(stepAdditionalAccessoryYesNo, AdditionalScreenCategory.TOUCH_SCREEN.NAME);
		stepAdditionalScreenRequirementType.addFollowUpStep(stepAdditionalAccessoryYesNo, AdditionalScreenCategory.TRUE_COLOR.NAME);
		
		// Monitorschritt (Besondere Anforderungen Ja/Nein)
		stepAdditionalScreenRequirementsYesNo = new ScenarioTreeStepTwoOptions("Monitor: Besonders?", stepAdditionalScreenRequirementType, "Ja", stepAdditionalAccessoryYesNo, "Nein");

		// Monitorschritt (Zusätzlicher Monitor Ja/Nein)
		stepAdditionalScreenYesNo = new ScenarioTreeStepTwoOptions("Zusatzmonitor?", stepAdditionalScreenRequirementsYesNo, "Ja", stepAdditionalAccessoryYesNo, "Nein");
		
		// Internetschritt (Mobiles Internet Ja/Nein)
		stepMobileInternetYesNo = new ScenarioTreeStepTwoOptions("Internet unterwegs?", stepAdditionalScreenYesNo, "Ja", stepAdditionalScreenYesNo, "Nein");

		// Internetschritt (WLAN vorhanden Ja/Nein)
		stepWLANYesNo = new ScenarioTreeStepTwoOptions("WLAN Router vorhanden?", stepMobileInternetYesNo, "Ja", stepMobileInternetYesNo, "Nein");
		
		// Datenspeicherschritt (Schnellstart SSD Ja/Nein)
		stepSSDYesNo = new ScenarioTreeStepTwoOptions("Schneller PC-Start?", stepWLANYesNo, "Ja", stepWLANYesNo, "Nein");
		
		// Datenspeicherschritt (Viele Daten Ja/Nein)
		stepHighAmountOfData = new ScenarioTreeStepTwoOptions("Große Datenmenge", stepWLANYesNo, "Ja", stepSSDYesNo, "Nein");
		
		// BetriebssystemSchritt (PC/Laptop)
		stepOperatingSystemComputer = new ScenarioTreeStepSimpleList("Betriebssystem (PC)");
		stepOperatingSystemComputer.addFollowUpStep(stepHighAmountOfData, OperatingSystemComputer.WINDOWS.NAME);
		stepOperatingSystemComputer.addFollowUpStep(stepHighAmountOfData, OperatingSystemComputer.MAC_OS_X.NAME);
		stepOperatingSystemComputer.addFollowUpStep(stepHighAmountOfData, OperatingSystemComputer.LINUX.NAME);
		
		// Betriebssystemschritt (Mobil/Tablet)
		stepOperatingSystemMobile = new ScenarioTreeStepSimpleList("Betriebssystem (PC)");
		stepOperatingSystemMobile.addFollowUpStep(stepHighAmountOfData, OperatingSystemMobile.WINDOWS.NAME);
		stepOperatingSystemMobile.addFollowUpStep(stepHighAmountOfData, OperatingSystemMobile.IOS.NAME);
		stepOperatingSystemMobile.addFollowUpStep(stepHighAmountOfData, OperatingSystemMobile.ANDROID.NAME);
		
		// Mobiler Gerätetyp Schritt
		stepMobileDeviceType = new ScenarioTreeStepSimpleList("Gerätetyp (Mobil)?");
		stepMobileDeviceType.addFollowUpStep(stepOperatingSystemComputer, MobileDeviceCategory.LAPTOP.NAME);
		stepMobileDeviceType.addFollowUpStep(stepOperatingSystemMobile, MobileDeviceCategory.TABLET.NAME);
		
		// Mobiles Arbeiten Schritt ((Ja/Nein)
		stepMobileUsageYesNo = new ScenarioTreeStepTwoOptions("Mobiles Arbeiten?", stepMobileDeviceType, "Ja", stepOperatingSystemComputer, "Nein");
	
		// Budgetschritt 
		stepBudget = new ScenarioTreeStepSimpleList("Budget:");
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.LOW.NAME);
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.MIDDLE.NAME);
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.HIGH.NAME);

		// Anwendungsbereich
		stepMainUsage = new ScenarioTreeStepSimpleList("Anwendungsbereich:");
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.OFFICE.NAME);
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.ENTERTAINMENT_MEDIA.NAME);
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.ENTERTAINMENT_GAMES.NAME);
		stepMainUsage.addFollowUpStep(stepMobileUsageYesNo, MainUsage.MEDIA_EDITING.NAME);
		stepMainUsage.addFollowUpStep(stepMobileUsageYesNo, MainUsage.CAD.NAME);
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.SOFTWARE_DEVELOPMENT.NAME);
		
		// StartSchritt
		stepBeginning = new ScenarioTreeStepBeginning("Beginn");
		stepBeginning.addFollowUpStep(stepMainUsage);
	}
	
	
	/**
	 * Wandert zum nächsten Schritt. Die nötigen Parameter müssen vorher von 
	 * z.B. der GUI mit setMethoden gesetzt worden sein.
	 * 
	 */
	public void goToNextStep(){
		
		ScenarioTreeStep nextStep;
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		// Parametrierung des aktuellen Schrittes mit den Werten, die vorher die GUI in den Attributen dieser Klasse
		// gesetztz haben muss und Abruf des Folgeschrittes, der auf diesen Parametern basiert
		
		if (currentStep == stepFinish){
			// Endschritt keine Fortsetzung
			return;
			
		} else if (currentStep == this.stepAdditionalAccessoryFromDB){
			// Nur ein Folgeschritt bei der Zubehör DB-Abfrage
			nextStep = this.stepAdditionalAccessoryFromDB.getNextStep();
		
		} else if (currentStep == this.stepAdditionalAccessoryYesNo){
			// Zubehör Ja/Nein 0 = Ja, 1 = Nein
			this.stepAdditionalAccessoryYesNo.setSelection(this.additionalAccessoryNeeded ? 0 : 1);
			nextStep = this.stepAdditionalAccessoryYesNo.getNextStep();
			
		} else if (currentStep == this.stepAdditionalScreenRequirementType){
			// Typ des Zusatzbildschirms
			this.stepAdditionalScreenRequirementType.setSelection(this.additionalScreenCategoryId);
			nextStep = this.stepAdditionalScreenRequirementType.getNextStep();
		
		} else if (currentStep == this.stepAdditionalScreenRequirementsYesNo){
			// Zusatzbildschirm besondere Anforderung Ja/Nein
			this.stepAdditionalScreenRequirementsYesNo.setSelection(this.additionalScreenRequirements ? 0 : 1);
			nextStep = this.stepAdditionalScreenRequirementsYesNo.getNextStep();
			
		} else if (currentStep == this.stepAdditionalScreenYesNo){
			// Zusatzbildschirm Ja/Nein
			this.stepAdditionalScreenYesNo.setSelection(this.additionalScreenNeeded ? 0 : 1);
			nextStep = this.stepAdditionalScreenYesNo.getNextStep();
			
		} else if (currentStep == this.stepMobileInternetYesNo){
			// Mobiles Internet Ja/Nein
			this.stepMobileInternetYesNo.setSelection(this.mobileInternetRequested ? 0 : 1);
			nextStep = this.stepMobileInternetYesNo.getNextStep();
			
		} else if (currentStep == this.stepWLANYesNo){
			// WLAN Ja/Nein
			this.stepWLANYesNo.setSelection(this.wlanAvailable ? 0 : 1);
			nextStep = this.stepWLANYesNo.getNextStep();
			
		} else if (currentStep == this.stepSSDYesNo){
			// SSD Schnellstart Ja/Nein
			this.stepSSDYesNo.setSelection(this.fastBootSSDRequested ? 0 : 1);
			nextStep = this.stepSSDYesNo.getNextStep();
			
		} else if (currentStep == this.stepHighAmountOfData){
			// Viel Speicher Ja/Nein
			this.stepHighAmountOfData.setSelection(this.highAmountOfStorageRequested ? 0 : 1);
			nextStep = this.stepHighAmountOfData.getNextStep();
			
		} else if (currentStep == this.stepOperatingSystemComputer) {
			// Betriebssystem Computer
			this.stepOperatingSystemComputer.setSelection(this.operatingSystemComputerId);
			nextStep = this.stepOperatingSystemComputer.getNextStep();
			
		} else if (currentStep == this.stepOperatingSystemMobile) {
			// Betriebssystem Mobil
			this.stepOperatingSystemMobile.setSelection(this.operatingSystemMobileId);
			nextStep = this.stepOperatingSystemMobile.getNextStep();
			
		} else if (currentStep == this.stepMobileDeviceType) {
			// Mobiler Gerätetyp
			this.stepMobileDeviceType.setSelection(this.mobileDeviceCategoryId);
			nextStep = this.stepMobileDeviceType.getNextStep();
			
		} else if (currentStep == this.stepMobileUsageYesNo) {
			// Mobile Benutzung Ja/Nein
			this.stepMobileUsageYesNo.setSelection(this.mobileUsageRequested ? 0 : 1);
			nextStep = this.stepMobileUsageYesNo.getNextStep();
			
		} else if (currentStep == this.stepBudget) {
			// Budget
			this.stepBudget.setSelection(this.budgetId);
			nextStep = this.stepBudget.getNextStep();
			
		} else if (currentStep == this.stepMainUsage) {
			// Hauptnutzung
			this.stepMainUsage.setSelection(this.mainUsageId);
			nextStep = this.stepMainUsage.getNextStep();

		} else if (currentStep == this.stepBeginning) {
			// Beginnschritt
			nextStep = this.stepBeginning.getNextStep();
		
		} else {
			throw new RuntimeException("ScenarioHardwareBerater goToNextStep: ungültiger Schritt in currentStep!");
		}
		
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Schrittliste
		this.stepPath.add(nextStep);
				
		/////////////////////////////////////////////////////////////////////////////////////
		// Parametrierung von Folgeschritten, die beim Aufruf erfolgen muss (Datenbankschritte)
		// Diese müssen bereits beim Betritt des Schrittes die Filterparameter kennen, um die
		// tabellarische Ausgabe zu filtern. Folgeschritte gibt es dann sogar nur einen,
		// da die Auswahl sich nicht darauf auswirkt.
		
		// Hier wird der in nextStep eingesetzte Schritt geprüft
		if (nextStep == this.stepAdditionalAccessoryFromDB) {
			
			// SetOperationen
		} else if (true){
			
			// Weitere DB Schritte
		}
		
	}
	
	/**
	 * Geht zum vorherigen Schritt. Dabei werden keine Parameter zurückgesetzt oder verändert.
	 * 
	 * Diese beeinflusst nicht ein späteres erneutes Vorwärtsgehen. Muss überprüft werden, ob
	 * ein Schritt zum bisherigen Pfad gehörte, dann erfolgt dies durch die Pfadliste. 
	 * Dort wird der aktuelle Schritt beim Rückwärtsgehen wieder gelöscht. Bei direkt parameterabhängigen
	 * Schritten (DB-Abfragen) sind die Grundschritte entweder stets dabei oder man prüft
	 * auch hier die Pfadliste.
	 * 
	 */
	public void goToPreviousStep(){
		
		// Letzten Schritt entfernen und dann den neuen letzten Schritt abrufen.
		stepPath.remove(stepPath.size() - 1);
		currentStep = stepPath.get(stepPath.size() - 1);
		
	}
	
	
	////////////////////////////// Getter und Setter auto-generiert //////////////////////
	
	
	

	
	
}
