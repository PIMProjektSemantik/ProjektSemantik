package de.htwsaarland.scenario;

import java.util.ArrayList;

import de.htwsaarland.scenario.selectionLists.AdditionalScreenCategory;
import de.htwsaarland.scenario.selectionLists.MainUsage;
import de.htwsaarland.scenario.selectionLists.MobileDeviceCategory;
import de.htwsaarland.scenario.selectionLists.OperatingSystemComputer;
import de.htwsaarland.scenario.selectionLists.OperatingSystemMobile;
import de.htwsaarland.scenario.selectionLists.PriceBudgetGlobal;

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
	ScenarioTreeStepDBOWLSoftwareAndOS	stepSoftwareAndOS						= null;
	ScenarioTreeStepDBOWLComputerComponents stepComputerComponents				= null;
	ScenarioTreeStepDBOWLTablet			stepDeviceTablet						= null;
	ScenarioTreeStepDBOWLNotebook		stepDeviceNotebook						= null;
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
	private MainUsage					mainUsage					= null;
	private MobileDeviceCategory 		mobileDeviceCategory		= null;
	private AdditionalScreenCategory	additionalScreenCategory	= null;
	private OperatingSystemComputer		operatingSystemComputer		= null;
	private OperatingSystemMobile		operatingSystemMobile		= null;
	private PriceBudgetGlobal			budget						= null;

	
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
//	private List<Integer>				accessoryList				= new ArrayList<Integer>();
//	private int							tabletId					= -1;
//	private int 						notebookId					= -1;
//	private int							storageId					= -1;
//	private int							cpuId						= -1;
//	private int							ramId						= -1;
//	private int							graphicsId					= -1;
	
	
	public ScenarioHardwareBerater(){
		
		// Konstruktion des Szenario-Ablaufs
		this.buildScenario();
		
		// Startschritt setzen
		this.currentStep = this.stepBeginning;
		this.stepPath = new ArrayList<ScenarioTreeStep>();
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
		stepFinish = new ScenarioTreeStepFinish("Abschluss", "Zubehörabfrage fehlt noch!");
		
		// Computerschritt test
		stepComputerComponents = new ScenarioTreeStepDBOWLComputerComponents("Computerteile auswählen", "aus ihren Antworten gefiltert ->", this);
		stepComputerComponents.addFollowUpStep(stepFinish);
		
		// Notebookschritt test
		stepDeviceNotebook = new ScenarioTreeStepDBOWLNotebook("Gerät (Notebook) wählen", "autom. anhand bish. Auswahl gefiltert ->", this);
		stepDeviceNotebook.addFollowUpStep(stepFinish);
		
		// Tabletschritt test
		stepDeviceTablet = new ScenarioTreeStepDBOWLTablet("Gerät (Tablet) wählen", "autom. gefiltert anhand Antworten ->", this);
		stepDeviceTablet.addFollowUpStep(stepFinish);
		
		// Zubehörschritt (Ja/Nein)
		stepAdditionalAccessoryYesNo = new ScenarioTreeStepTwoOptions("Zubehör", stepDeviceTablet, "Ja", stepDeviceTablet, "Nein", "Weiteres Zubehör?");
			
		// Monitorschritt (besondere Anforderung wählen)
		stepAdditionalScreenRequirementType = new ScenarioTreeStepSimpleList("Monitoranforderungen", "Berührumgsempflindlich oder Farbecht für Grafiker");
		stepAdditionalScreenRequirementType.addFollowUpStep(stepAdditionalAccessoryYesNo, AdditionalScreenCategory.TOUCH_SCREEN.NAME);
		stepAdditionalScreenRequirementType.addFollowUpStep(stepAdditionalAccessoryYesNo, AdditionalScreenCategory.TRUE_COLOR.NAME);
		
		// Monitorschritt (Besondere Anforderungen Ja/Nein)
		stepAdditionalScreenRequirementsYesNo = new ScenarioTreeStepTwoOptions("Monitor: Besonders?", stepAdditionalScreenRequirementType, "Ja", stepAdditionalAccessoryYesNo, "Nein", "Standard oder Touch/Farbecht");

		// Monitorschritt (Zusätzlicher Monitor Ja/Nein)
		stepAdditionalScreenYesNo = new ScenarioTreeStepTwoOptions("Zusatzmonitor?", stepAdditionalScreenRequirementsYesNo, "Ja", stepAdditionalAccessoryYesNo, "Nein", "Bildschirm mitbestellen oder schon Geeignetes vorhanden?");
		
		// Internetschritt (Mobiles Internet Ja/Nein)
		stepMobileInternetYesNo = new ScenarioTreeStepTwoOptions("Internet unterwegs?", stepAdditionalScreenYesNo, "Ja", stepAdditionalScreenYesNo, "Nein", "Internet bei Kunden und unterwegs?");

		// Internetschritt (WLAN vorhanden Ja/Nein)
		stepWLANYesNo = new ScenarioTreeStepTwoOptions("WLAN Router vorhanden?", stepMobileInternetYesNo, "Ja", stepMobileInternetYesNo, "Nein", "Netzwerkzugriff ohne Kabel");
		
		// Datenspeicherschritt (Schnellstart SSD Ja/Nein)
		stepSSDYesNo = new ScenarioTreeStepTwoOptions("Schneller PC-Start?", stepWLANYesNo, "Ja", stepWLANYesNo, "Nein", "PC-Start in unter 20 Sekunden");
		
		// Datenspeicherschritt (Viele Daten Ja/Nein)
		stepHighAmountOfData = new ScenarioTreeStepTwoOptions("Große Datenmenge", stepWLANYesNo, "Ja", stepSSDYesNo, "Nein", "Große Video und Fotosammlungen, Backups, usw.?");
		
		// Softwareauswahl (nur Windows/Linux auf Laptop/PC)
		stepSoftwareAndOS = new ScenarioTreeStepDBOWLSoftwareAndOS("Software und Betriebssysteme", "gefilterte Tabelle ->", this);
		stepSoftwareAndOS.addFollowUpStep(stepHighAmountOfData);
		
		// BetriebssystemSchritt (PC/Laptop)
		stepOperatingSystemComputer = new ScenarioTreeStepSimpleList("Betriebssystem (PC)", "Je nach BS gibt es bestimmte Anwendungen nicht.");
		stepOperatingSystemComputer.addFollowUpStep(stepSoftwareAndOS, OperatingSystemComputer.WINDOWS.NAME);
		stepOperatingSystemComputer.addFollowUpStep(stepHighAmountOfData, OperatingSystemComputer.MAC_OS_X.NAME);
		stepOperatingSystemComputer.addFollowUpStep(stepHighAmountOfData, OperatingSystemComputer.LINUX.NAME);
				
		// Betriebssystemschritt (Mobil/Tablet)
		stepOperatingSystemMobile = new ScenarioTreeStepSimpleList("Betriebssystem (Mobil)", "Je nach BS gibt es bestimmte Apps nicht.");
		stepOperatingSystemMobile.addFollowUpStep(stepHighAmountOfData, OperatingSystemMobile.WINDOWS.NAME);
		stepOperatingSystemMobile.addFollowUpStep(stepHighAmountOfData, OperatingSystemMobile.IOS.NAME);
		stepOperatingSystemMobile.addFollowUpStep(stepHighAmountOfData, OperatingSystemMobile.ANDROID.NAME);
		
		// Mobiler Gerätetyp Schritt
		stepMobileDeviceType = new ScenarioTreeStepSimpleList("Gerätetyp (Mobil)?", "Laptops mit voller PC Flexibilität oder kompakt mit Apps.");
		stepMobileDeviceType.addFollowUpStep(stepOperatingSystemComputer, MobileDeviceCategory.LAPTOP.NAME);
		stepMobileDeviceType.addFollowUpStep(stepOperatingSystemMobile, MobileDeviceCategory.TABLET.NAME);
		
		// Mobiles Arbeiten Schritt ((Ja/Nein)
		stepMobileUsageYesNo = new ScenarioTreeStepTwoOptions("Mobiles Arbeiten?", stepMobileDeviceType, "Ja", stepOperatingSystemComputer, "Nein", "Arbeit ohne Kabel, beim Kunden, usw.");
	
		// Budgetschritt 
		stepBudget = new ScenarioTreeStepSimpleList("Budget:", "<html>Budgetkategorien aus Ontologie:<br>" +
													"Notebook:			Gering: <= 400 €. Mittel 400-800 €. Hoch >= 800<br>" +
													"Tablet:			Gering: <= 400 €. Mittel 400-800 €. Hoch >= 800<br>" +
													"Software:			Gering: <= 100 €. Mittel <= 200 €. Hoch -> unbegrenzt <br>" +
													"Betriebssystem:	Gering: <= 80 €. Mittel <= 150€. Hoch -> unbegrenzt <br>" +
													"CPU:				Gering: <= 200 €. Mittel 200-400 €. Hoch >= 400<br>" +
													"RAM:				Gering: <= 80 €. Mittel 80-160 €. Hoch >= 160<br>" +
													"Grafikkarte:		Gering: <= 200 €. Mittel 200-400 €. Hoch >= 400<br>" +
													"Festplatte:		Gering: <= 50 €. Mittel 50-100 €. Hoch >= 100</html>");
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.LOW.NAME);
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.MIDDLE.NAME);
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.HIGH.NAME);

		// Anwendungsbereich
		stepMainUsage = new ScenarioTreeStepSimpleList("Anwendungsbereich:", "Hauptnutzungsbereich ihres Gerätes.");
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.OFFICE.NAME);
		stepMainUsage.addFollowUpStep(stepMobileUsageYesNo, MainUsage.MEDIA_EDITING.NAME);
		stepMainUsage.addFollowUpStep(stepMobileUsageYesNo, MainUsage.CAD.NAME);
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.ENTERTAINMENT_MEDIA.NAME);
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.ENTERTAINMENT_GAMES.NAME);
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.SOFTWARE_DEVELOPMENT.NAME);
		
		// StartSchritt
		stepBeginning = new ScenarioTreeStepBeginning("Beginn", "'Schritt runter drücken, um fortzufahren.'");
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
			
		} else if (currentStep == this.stepAdditionalAccessoryYesNo){
			// Zubehör Ja/Nein 0 = Ja, 1 = Nein
			this.additionalAccessoryNeeded = this.stepAdditionalAccessoryYesNo.getSelection() == 0 ? true : false;
			
			// Mobil nein -> nur PC Teile! Mobil ja -> Laptop oder Tablets anbieten
			if (this.mobileUsageRequested == false){
				nextStep = this.stepComputerComponents;
			} else if (this.mobileDeviceCategory == MobileDeviceCategory.LAPTOP){
				nextStep = this.stepDeviceNotebook;
			} else {
				nextStep = this.stepDeviceTablet;
			}
			
		} else if (currentStep == this.stepAdditionalScreenRequirementType){
			// Typ des Zusatzbildschirms
			this.setAdditionalScreenCategory(AdditionalScreenCategory.values()[this.stepAdditionalScreenRequirementType.getSelection()]);
			nextStep = this.stepAdditionalScreenRequirementType.getNextStep();
		
		} else if (currentStep == this.stepAdditionalScreenRequirementsYesNo){
			// Zusatzbildschirm besondere Anforderung Ja/Nein
			this.additionalScreenRequirements = this.stepAdditionalScreenRequirementsYesNo.getSelection() == 0 ? true : false;
			nextStep = this.stepAdditionalScreenRequirementsYesNo.getNextStep();
			
		} else if (currentStep == this.stepAdditionalScreenYesNo){
			// Zusatzbildschirm Ja/Nein
			this.additionalScreenNeeded = this.stepAdditionalScreenYesNo.getSelection() == 0 ? true : false;
			nextStep = this.stepAdditionalScreenYesNo.getNextStep();
			
		} else if (currentStep == this.stepMobileInternetYesNo){
			// Mobiles Internet Ja/Nein
			this.mobileInternetRequested = this.stepMobileInternetYesNo.getSelection() == 0 ? true : false;
			nextStep = this.stepMobileInternetYesNo.getNextStep();
			
		} else if (currentStep == this.stepWLANYesNo){
			// WLAN Ja/Nein
			this.wlanAvailable = this.stepWLANYesNo.getSelection() == 0 ? true : false;
			if(this.mobileUsageRequested){
				nextStep = this.stepMobileInternetYesNo;
			} else {
				nextStep = this.stepAdditionalScreenYesNo;
			}
			//nextStep = this.stepWLANYesNo.getNextStep();
			
		} else if (currentStep == this.stepSSDYesNo){
			// SSD Schnellstart Ja/Nein
			this.fastBootSSDRequested = this.stepSSDYesNo.getSelection() == 0 ? true : false;
			nextStep = this.stepSSDYesNo.getNextStep();
			
		} else if (currentStep == this.stepHighAmountOfData){
			// Viel Speicher Ja/Nein
			this.highAmountOfStorageRequested = this.stepHighAmountOfData.getSelection() == 0 ? true : false;
			if(this.highAmountOfStorageRequested == true){
				this.fastBootSSDRequested = true;
			}
			nextStep = this.stepHighAmountOfData.getNextStep();
			
		} else if (currentStep == this.stepOperatingSystemComputer) {
			// Betriebssystem Computer
			int pcOs = this.stepOperatingSystemComputer.getSelection();
			this.setOperatingSystemComputer(OperatingSystemComputer.values()[pcOs]);
			nextStep = this.stepOperatingSystemComputer.getNextStep();
			
		} else if (currentStep == this.stepOperatingSystemMobile) {
			// Betriebssystem Mobil
			this.setOperatingSystemMobile(OperatingSystemMobile.values()[this.stepOperatingSystemMobile.getSelection()]);
			nextStep = this.stepOperatingSystemMobile.getNextStep();
			
		} else if (currentStep == this.stepMobileDeviceType) {
			// Mobiler Gerätetyp
			this.setMobileDeviceCategory(MobileDeviceCategory.values()[this.stepMobileDeviceType.getSelection()]);
			nextStep = this.stepMobileDeviceType.getNextStep();
			
		} else if (currentStep == this.stepMobileUsageYesNo) {
			// Mobile Benutzung Ja/Nein
			this.mobileUsageRequested = this.stepMobileUsageYesNo.getSelection() == 0 ? true : false;

			nextStep = this.stepMobileUsageYesNo.getNextStep();
			
		} else if (currentStep == this.stepBudget) {
			// Budget
			this.setBudget(PriceBudgetGlobal.values()[this.stepBudget.getSelection()]);
			nextStep = this.stepBudget.getNextStep();
			
		} else if (currentStep == this.stepMainUsage) {
			// Hauptnutzung
			this.setMainUsage(MainUsage.values()[this.stepMainUsage.getSelection()]);
			if (this.mainUsage == MainUsage.CAD || this.mainUsage == MainUsage.MEDIA_EDITING){
				this.budget = PriceBudgetGlobal.HIGH;
			}
			nextStep = this.stepMainUsage.getNextStep();

		} else {
			// Schritte ohne Parametrierung
			nextStep = this.currentStep.getNextStep();
		}
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////
		// Schrittliste
		this.stepPath.add(nextStep);
		currentStep = nextStep;	
		
		
		/////////////////////////////////////////////////////////////////////////////////////
		// Parametrierung von Folgeschritten, die beim Aufruf erfolgen muss (Datenbankschritte)
		// Diese müssen bereits beim Betritt des Schrittes die Filterparameter kennen, um die
		// tabellarische Ausgabe zu filtern. Folgeschritte gibt es dann sogar nur einen,
		// da die Auswahl sich nicht darauf auswirkt.
		
		
		
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
	
	public ScenarioTreeStep getCurrentStep(){
		return this.currentStep;
	}
	
	/**
	 * @return the additionalAccessoryNeeded
	 */
	public boolean getIsAdditionalAccessoryNeeded() {
		return additionalAccessoryNeeded;
	}

	/**
	 * @param additionalAccessoryNeeded the additionalAccessoryNeeded to set
	 */
	public void setAdditionalAccessoryNeeded(boolean additionalAccessoryNeeded) {
		this.additionalAccessoryNeeded = additionalAccessoryNeeded;
	}

	/**
	 * @return the additionalScreenNeeded
	 */
	public boolean getIsAdditionalScreenNeeded() {
		return additionalScreenNeeded;
	}

	/**
	 * @param additionalScreenNeeded the additionalScreenNeeded to set
	 */
	public void setAdditionalScreenNeeded(boolean additionalScreenNeeded) {
		this.additionalScreenNeeded = additionalScreenNeeded;
	}

	/**
	 * @return the additionalScreenRequirements
	 */
	public boolean getIsAdditionalScreenRequirementRequested() {
		return additionalScreenRequirements;
	}

	/**
	 * @param additionalScreenRequirements the additionalScreenRequirements to set
	 */
	public void setAdditionalScreenRequirements(boolean additionalScreenRequirements) {
		this.additionalScreenRequirements = additionalScreenRequirements;
	}

	/**
	 * @return the wlanAvailable
	 */
	public boolean getIsWlanAvailable() {
		return wlanAvailable;
	}

	/**
	 * @param wlanAvailable the wlanAvailable to set
	 */
	public void setWlanAvailable(boolean wlanAvailable) {
		this.wlanAvailable = wlanAvailable;
	}

	/**
	 * @return the mobileInternetRequested
	 */
	public boolean getIsMobileInternetRequested() {
		return mobileInternetRequested;
	}

	/**
	 * @param mobileInternetRequested the mobileInternetRequested to set
	 */
	public void setMobileInternetRequested(boolean mobileInternetRequested) {
		this.mobileInternetRequested = mobileInternetRequested;
	}

	/**
	 * @return the fastBootSSDRequested
	 */
	public boolean getIsFastBootSSDRequested() {
		return fastBootSSDRequested;
	}

	/**
	 * @param fastBootSSDRequested the fastBootSSDRequested to set
	 */
	public void setFastBootSSDRequested(boolean fastBootSSDRequested) {
		this.fastBootSSDRequested = fastBootSSDRequested;
	}

	/**
	 * @return the highAmountOfStorageRequested
	 */
	public boolean getIsHighAmountOfStorageRequested() {
		return highAmountOfStorageRequested;
	}

	/**
	 * @param highAmountOfStorageRequested the highAmountOfStorageRequested to set
	 */
	public void setHighAmountOfStorageRequested(boolean highAmountOfStorageRequested) {
		this.highAmountOfStorageRequested = highAmountOfStorageRequested;
	}

	/**
	 * @return the mobileUsageRequested
	 */
	public boolean getIsMobileUsageRequested() {
		return mobileUsageRequested;
	}

	/**
	 * @param mobileUsageRequested the mobileUsageRequested to set
	 */
	public void setMobileUsageRequested(boolean mobileUsageRequested) {
		this.mobileUsageRequested = mobileUsageRequested;
	}


	/**
	 * @return the mainUsage
	 */
	public MainUsage getMainUsage() {
		return mainUsage;
	}

	/**
	 * @param mainUsage the mainUsage to set
	 */
	public void setMainUsage(MainUsage mainUsage) {
		this.mainUsage = mainUsage;
	}

	/**
	 * @return the additionalScreenCategory
	 */
	public AdditionalScreenCategory getAdditionalScreenCategory() {
		return additionalScreenCategory;
	}

	/**
	 * @param additionalScreenCategory the additionalScreenCategory to set
	 */
	public void setAdditionalScreenCategory(AdditionalScreenCategory additionalScreenCategory) {
		this.additionalScreenCategory = additionalScreenCategory;
	}

	/**
	 * @return the operatingSystemComputer
	 */
	public OperatingSystemComputer getOperatingSystemComputer() {
		return operatingSystemComputer;
	}

	/**
	 * @param operatingSystemComputer the operatingSystemComputer to set
	 */
	public void setOperatingSystemComputer(OperatingSystemComputer operatingSystemComputer) {
		this.operatingSystemComputer = operatingSystemComputer;
	}

	/**
	 * @return the mobileDeviceCategory
	 */
	public MobileDeviceCategory getMobileDeviceCategory() {
		return mobileDeviceCategory;
	}

	/**
	 * @param mobileDeviceCategory the mobileDeviceCategory to set
	 */
	public void setMobileDeviceCategory(MobileDeviceCategory mobileDeviceCategory) {
		this.mobileDeviceCategory = mobileDeviceCategory;
	}

	/**
	 * @return the operatingSystemMobile
	 */
	public OperatingSystemMobile getOperatingSystemMobile() {
		return operatingSystemMobile;
	}

	/**
	 * @param operatingSystemMobile the operatingSystemMobile to set
	 */
	public void setOperatingSystemMobile(OperatingSystemMobile operatingSystemMobile) {
		this.operatingSystemMobile = operatingSystemMobile;
	}

	/**
	 * @return the budget
	 */
	public PriceBudgetGlobal getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(PriceBudgetGlobal budget) {
		this.budget = budget;
	}
	
	
}
