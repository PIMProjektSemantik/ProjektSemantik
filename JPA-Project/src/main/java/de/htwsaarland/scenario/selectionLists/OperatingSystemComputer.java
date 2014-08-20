package de.htwsaarland.scenario.selectionLists;

public enum OperatingSystemComputer {
	
	WINDOWS("Microsoft Windows"),
	MAC_OS_X("Apple Mac OS X"),
	LINUX("Linux");
	
	public final String NAME;
	
	private OperatingSystemComputer(String name) {
		this.NAME = name;
	}
	
}
