package modele.scnObjects;

import java.util.ArrayList;

public class Pylon
{
	private int id;
	private String name;
	private boolean enabled;
	private boolean hitDetectorEnabled;
	private boolean keyboardEnabled;
	private ArrayList<Team> owners = new ArrayList<Team>();
	
	private int batteryVoltage;
	private int duskLevel;
	private int radioLinkQuality;
	private int wifiLinkQuality;
	
	public Pylon(int id, String name, boolean enabled, boolean hitDetectorEnabled, boolean keyboardEnabled)
	{
		this.id = id;
		this.name = name;
		this.enabled = enabled;
		this.hitDetectorEnabled = hitDetectorEnabled;
		this.keyboardEnabled = keyboardEnabled;
		
		this.batteryVoltage = 10;
		this.duskLevel = 100;
		this.radioLinkQuality = 100;
		this.wifiLinkQuality = 100;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbOwners() {
		return owners.size();
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public ArrayList<Team> getOwners() {
		return owners;
	}

	public void setOwners(ArrayList<Team> owners) {
		this.owners = owners;
	}
	
	public boolean isHitDetectorEnabled() {
		return hitDetectorEnabled;
	}

	public void setHitDetectorEnabled(boolean hitDetectorEnabled) {
		this.hitDetectorEnabled = hitDetectorEnabled;
	}

	public boolean isKeyboardEnabled() {
		return keyboardEnabled;
	}

	public void setKeyboardEnabled(boolean keyboardEnabled) {
		this.keyboardEnabled = keyboardEnabled;
	}

	public void addOwner(Team team)
	{
		owners.add(team);
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getDuskLevel() {
		return duskLevel;
	}

	public void setDuskLevel(int duskLevel) {
		this.duskLevel = duskLevel;
	}

	public int getRadioLinkQuality() {
		return radioLinkQuality;
	}

	public void setRadioLinkQuality(int radioLinkQuality) {
		this.radioLinkQuality = radioLinkQuality;
	}

	public int getWifiLinkQuality() {
		return wifiLinkQuality;
	}

	public void setWifiLinkQuality(int wifiLinkQuality) {
		this.wifiLinkQuality = wifiLinkQuality;
	}
}
