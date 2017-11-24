package modele.scnObjects;

import java.util.ArrayList;

public class Team 
{
	private int id;
	private String name;
	private String shortName;
	private boolean spawnAllowed;
	private int spawnNumber;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public Team(int id, String name, String shortName, boolean spawnAllowed, int spawnNumber)
	{
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.spawnAllowed = spawnAllowed;
		this.spawnNumber = spawnNumber;
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

	public int getNbPlayers() {
		return players.size();
	}
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public boolean isSpawnAllowed() {
		return spawnAllowed;
	}

	public void setSpawnAllowed(boolean spawnAllowed) {
		this.spawnAllowed = spawnAllowed;
	}

	public int getSpawnNumber() {
		return spawnNumber;
	}

	public void setSpawnNumber(int spawnNumber) {
		this.spawnNumber = spawnNumber;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void addPlayer(Player player)
	{
		players.add(player);
	}
}
