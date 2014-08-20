package de.htwsaarland.scenario.selectionLists;

public enum OperatingSystemMobile {

	WINDOWS("Microsoft Windows"),
	//WINDOWS_PHONE("Microsoft Windows Phone"),
	IOS("Apple IOS"),
	ANDROID("Android");
	
	public final String NAME;
	
	private OperatingSystemMobile(String name) {
		this.NAME = name;
	}
		
}
