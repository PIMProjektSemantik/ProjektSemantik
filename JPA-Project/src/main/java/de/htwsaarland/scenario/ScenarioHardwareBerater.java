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
	ScenarioTreeStepDBOWLTablet			stepDeviceTablet						= null;
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
	
		// Tabletschritt test
		stepDeviceTablet = new ScenarioTreeStepDBOWLTablet("Gerät (Tablet) wählen", "autom. gefiltert anhand Antworten");
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
		
		// BetriebssystemSchritt (PC/Laptop)
		stepOperatingSystemComputer = new ScenarioTreeStepSimpleList("Betriebssystem (PC)", "Je nach BS gibt es bestimmte Anwendungen nicht.");
		stepOperatingSystemComputer.addFollowUpStep(stepHighAmountOfData, OperatingSystemComputer.WINDOWS.NAME);
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
		stepBudget = new ScenarioTreeStepSimpleList("Budget:", "Gering: < 500 €. Mittel < 1200 €. Hoch > 1200€");
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.LOW.NAME);
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.MIDDLE.NAME);
		stepBudget.addFollowUpStep(stepMobileUsageYesNo, PriceBudgetGlobal.HIGH.NAME);

		// Anwendungsbereich
		stepMainUsage = new ScenarioTreeStepSimpleList("Anwendungsbereich:", "Hauptnutzungsbereich ihres Gerätes.");
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.OFFICE.NAME);
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.ENTERTAINMENT_MEDIA.NAME);
		stepMainUsage.addFollowUpStep(stepBudget, MainUsage.ENTERTAINMENT_GAMES.NAME);
		stepMainUsage.addFollowUpStep(stepMobileUsageYesNo, MainUsage.MEDIA_EDITING.NAME);
		stepMainUsage.addFollowUpStep(stepMobileUsageYesNo, MainUsage.CAD.NAME);
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
			this.stepAdditionalAccessoryYesNo.setSelection(this.additionalAccessoryNeeded ? 0 : 1);
			nextStep = this.stepAdditionalAccessoryYesNo.getNextStep();
			
		} else if (currentStep == this.stepAdditionalScreenRequirementType){
			// Typ des Zusatzbildschirms
			this.additionalScreenCategoryId = this.stepAdditionalScreenRequirementType.getSelection();
			this.stepAdditionalScreenRequirementType.setSelection(this.additionalScreenCategoryId);
			nextStep = this.stepAdditionalScreenRequirementType.getNextStep();
		
		} else if (currentStep == this.stepAdditionalScreenRequirementsYesNo){
			// Zusatzbildschirm besondere Anforderung Ja/Nein
			this.additionalScreenRequirements = this.stepAdditionalScreenRequirementsYesNo.getSelection() == 0 ? true : false;
			this.stepAdditionalScreenRequirementsYesNo.setSelection(this.additionalScreenRequirements ? 0 : 1);
			nextStep = this.stepAdditionalScreenRequirementsYesNo.getNextStep();
			
		} else if (currentStep == this.stepAdditionalScreenYesNo){
			// Zusatzbildschirm Ja/Nein
			this.additionalScreenNeeded = this.stepAdditionalScreenYesNo.getSelection() == 0 ? true : false;
			this.stepAdditionalScreenYesNo.setSelection(this.additionalScreenNeeded ? 0 : 1);
			nextStep = this.stepAdditionalScreenYesNo.getNextStep();
			
		} else if (currentStep == this.stepMobileInternetYesNo){
			// Mobiles Internet Ja/Nein
			this.mobileInternetRequested = this.stepMobileInternetYesNo.getSelection() == 0 ? true : false;
			this.stepMobileInternetYesNo.setSelection(this.mobileInternetRequested ? 0 : 1);
			nextStep = this.stepMobileInternetYesNo.getNextStep();
			
		} else if (currentStep == this.stepWLANYesNo){
			// WLAN Ja/Nein
			this.wlanAvailable = this.stepWLANYesNo.getSelection() == 0 ? true : false;
			this.stepWLANYesNo.setSelection(this.wlanAvailable ? 0 : 1);
			nextStep = this.stepWLANYesNo.getNextStep();
			
		} else if (currentStep == this.stepSSDYesNo){
			// SSD Schnellstart Ja/Nein
			this.fastBootSSDRequested = this.stepSSDYesNo.getSelection() == 0 ? true : false;
			this.stepSSDYesNo.setSelection(this.fastBootSSDRequested ? 0 : 1);
			nextStep = this.stepSSDYesNo.getNextStep();
			
		} else if (currentStep == this.stepHighAmountOfData){
			// Viel Speicher Ja/Nein
			this.highAmountOfStorageRequested = this.stepHighAmountOfData.getSelection() == 0 ? true : false;
			this.stepHighAmountOfData.setSelection(this.highAmountOfStorageRequested ? 0 : 1);
			nextStep = this.stepHighAmountOfData.getNextStep();
			
		} else if (currentStep == this.stepOperatingSystemComputer) {
			// Betriebssystem Computer
			this.operatingSystemComputerId = this.stepOperatingSystemComputer.getSelection();
			this.stepOperatingSystemComputer.setSelection(this.operatingSystemComputerId);
			nextStep = this.stepOperatingSystemComputer.getNextStep();
			
		} else if (currentStep == this.stepOperatingSystemMobile) {
			// Betriebssystem Mobil
			this.operatingSystemMobileId = this.stepOperatingSystemMobile.getSelection();
			this.stepOperatingSystemMobile.setSelection(this.operatingSystemMobileId);
			nextStep = this.stepOperatingSystemMobile.getNextStep();
			
		} else if (currentStep == this.stepMobileDeviceType) {
			// Mobiler Gerätetyp
			this.mobileDeviceCategoryId = this.stepMobileDeviceType.getSelection();
			this.stepMobileDeviceType.setSelection(this.mobileDeviceCategoryId);
			nextStep = this.stepMobileDeviceType.getNextStep();
			
		} else if (currentStep == this.stepMobileUsageYesNo) {
			// Mobile Benutzung Ja/Nein
			this.mobileUsageRequested = this.stepMobileUsageYesNo.getSelection() == 0 ? true : false;
			this.stepMobileUsageYesNo.setSelection(this.mobileUsageRequested ? 0 : 1);
			nextStep = this.stepMobileUsageYesNo.getNextStep();
			
		} else if (currentStep == this.stepBudget) {
			// Budget
			this.budgetId = this.stepBudget.getSelection();
			this.stepBudget.setSelection(this.budgetId);
			nextStep = this.stepBudget.getNextStep();
			
		} else if (currentStep == this.stepMainUsage) {
			// Hauptnutzung
			this.mainUsageId = this.stepMainUsage.getSelection();
			this.stepMainUsage.setSelection(this.mainUsageId);
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
	 * @return the mainUsageId
	 */
	public int getMainUsageId() {
		return mainUsageId;
	}

	/**
	 * @param mainUsageId the mainUsageId to set
	 */
	public void setMainUsageId(int mainUsageId) {
		this.mainUsageId = mainUsageId;
	}

	/**
	 * @return the mobileDeviceCategoryId
	 */
	public int getMobileDeviceCategoryId() {
		return mobileDeviceCategoryId;
	}

	/**
	 * @param mobileDeviceCategoryId the mobileDeviceCategoryId to set
	 */
	public void setMobileDeviceCategoryId(int mobileDeviceCategoryId) {
		this.mobileDeviceCategoryId = mobileDeviceCategoryId;
	}

	/**
	 * @return the additionalScreenCategoryId
	 */
	public int getAdditionalScreenCategoryId() {
		return additionalScreenCategoryId;
	}

	/**
	 * @param additionalScreenCategoryId the additionalScreenCategoryId to set
	 */
	public void setAdditionalScreenCategoryId(int additionalScreenCategoryId) {
		this.additionalScreenCategoryId = additionalScreenCategoryId;
	}

	/**
	 * @return the operatingSystemComputerId
	 */
	public int getOperatingSystemComputerId() {
		return operatingSystemComputerId;
	}

	/**
	 * @param operatingSystemComputerId the operatingSystemComputerId to set
	 */
	public void setOperatingSystemComputerId(int operatingSystemComputerId) {
		this.operatingSystemComputerId = operatingSystemComputerId;
	}

	/**
	 * @return the operatingSystemMobileId
	 */
	public int getOperatingSystemMobileId() {
		return operatingSystemMobileId;
	}

	/**
	 * @param operatingSystemMobileId the operatingSystemMobileId to set
	 */
	public void setOperatingSystemMobileId(int operatingSystemMobileId) {
		this.operatingSystemMobileId = operatingSystemMobileId;
	}

	/**
	 * @return the budgetId
	 */
	public int getBudgetId() {
		return budgetId;
	}

	/**
	 * @param budgetId the budgetId to set
	 */
	public void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
	}

	/**
	 * @return the cpuPrice
	 */
	public CPUPrice getCpuPrice() {
		return cpuPrice;
	}

	/**
	 * @param cpuPrice the cpuPrice to set
	 */
	public void setCpuPrice(CPUPrice cpuPrice) {
		this.cpuPrice = cpuPrice;
	}

	/**
	 * @return the cpuSpeed
	 */
	public CPUSpeed getCpuSpeed() {
		return cpuSpeed;
	}

	/**
	 * @param cpuSpeed the cpuSpeed to set
	 */
	public void setCpuSpeed(CPUSpeed cpuSpeed) {
		this.cpuSpeed = cpuSpeed;
	}

	/**
	 * @return the graphicsPrice
	 */
	public GraphicsPrice getGraphicsPrice() {
		return graphicsPrice;
	}

	/**
	 * @param graphicsPrice the graphicsPrice to set
	 */
	public void setGraphicsPrice(GraphicsPrice graphicsPrice) {
		this.graphicsPrice = graphicsPrice;
	}

	/**
	 * @return the graphicsSpeed
	 */
	public GraphicsSpeed getGraphicsSpeed() {
		return graphicsSpeed;
	}

	/**
	 * @param graphicsSpeed the graphicsSpeed to set
	 */
	public void setGraphicsSpeed(GraphicsSpeed graphicsSpeed) {
		this.graphicsSpeed = graphicsSpeed;
	}

	/**
	 * @return the ramPrice
	 */
	public RAMPrice getRamPrice() {
		return ramPrice;
	}

	/**
	 * @param ramPrice the ramPrice to set
	 */
	public void setRamPrice(RAMPrice ramPrice) {
		this.ramPrice = ramPrice;
	}

	/**
	 * @return the ramSize
	 */
	public RAMSize getRamSize() {
		return ramSize;
	}

	/**
	 * @param ramSize the ramSize to set
	 */
	public void setRamSize(RAMSize ramSize) {
		this.ramSize = ramSize;
	}

	/**
	 * @return the ramSpeed
	 */
	public RAMSpeed getRamSpeed() {
		return ramSpeed;
	}

	/**
	 * @param ramSpeed the ramSpeed to set
	 */
	public void setRamSpeed(RAMSpeed ramSpeed) {
		this.ramSpeed = ramSpeed;
	}

	/**
	 * @return the additionalAccessoryNeeded
	 */
	public boolean isAdditionalAccessoryNeeded() {
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
	public boolean isAdditionalScreenNeeded() {
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
	public boolean isAdditionalScreenRequirements() {
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
	public boolean isWlanAvailable() {
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
	public boolean isMobileInternetRequested() {
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
	public boolean isFastBootSSDRequested() {
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
	public boolean isHighAmountOfStorageRequested() {
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
	public boolean isMobileUsageRequested() {
		return mobileUsageRequested;
	}

	/**
	 * @param mobileUsageRequested the mobileUsageRequested to set
	 */
	public void setMobileUsageRequested(boolean mobileUsageRequested) {
		this.mobileUsageRequested = mobileUsageRequested;
	}

	/**
	 * @return the tabletId
	 */
	public int getTabletId() {
		return tabletId;
	}

	/**
	 * @param tabletId the tabletId to set
	 */
	public void setTabletId(int tabletId) {
		this.tabletId = tabletId;
	}

	/**
	 * @return the notebookId
	 */
	public int getNotebookId() {
		return notebookId;
	}

	/**
	 * @param notebookId the notebookId to set
	 */
	public void setNotebookId(int notebookId) {
		this.notebookId = notebookId;
	}

	/**
	 * @return the storageId
	 */
	public int getStorageId() {
		return storageId;
	}

	/**
	 * @param storageId the storageId to set
	 */
	public void setStorageId(int storageId) {
		this.storageId = storageId;
	}

	/**
	 * @return the cpuId
	 */
	public int getCpuId() {
		return cpuId;
	}

	/**
	 * @param cpuId the cpuId to set
	 */
	public void setCpuId(int cpuId) {
		this.cpuId = cpuId;
	}

	/**
	 * @return the ramId
	 */
	public int getRamId() {
		return ramId;
	}

	/**
	 * @param ramId the ramId to set
	 */
	public void setRamId(int ramId) {
		this.ramId = ramId;
	}

	/**
	 * @return the graphicsId
	 */
	public int getGraphicsId() {
		return graphicsId;
	}

	/**
	 * @param graphicsId the graphicsId to set
	 */
	public void setGraphicsId(int graphicsId) {
		this.graphicsId = graphicsId;
	}
	
	
}
