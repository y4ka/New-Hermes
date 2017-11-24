package modele.scnObjects;

import java.util.ArrayList;
import java.util.Hashtable;
import modele.tools.ViewLogger.Logger;

public class Scenario
{
	private Pylon[] pylons;
	private Player[] players;
	private Team[] teams;
	
	public Scenario(int numberOfPylons, int numberOfPlayers, int numberOfTeams)
	{	
		this.pylons = new Pylon[numberOfPylons];
		this.players = new Player[numberOfPlayers];
		this.teams = new Team[numberOfTeams];
	}

	public int getNumberOfPylons() {
		return pylons.length;
	}

	public int getNumberOfPlayers() {
		return players.length;
	}

	public int getNumberOfTeams() {
		return teams.length;
	}

	public Pylon[] getPylons() {
		return pylons;
	}

	public void setPylons(Pylon[] pylons) {
		this.pylons = pylons;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Team[] getTeams() {
		return teams;
	}

	public void setTeams(Team[] teams) {
		this.teams = teams;
	}

	//REWORK NEEDEED:
	public void addPylon(Pylon pylon)
	{
		int pylonId = pylon.getId();
		if (pylonId < pylons.length)
		{
			pylons[pylonId] = pylon;
		}
		else
		{
			Logger.instance.log("WARNING: pylonId = "+pylonId+", NbPylonDeclared = "+pylons.length);
		}
	}
		
	public void addPlayer(Player player)
	{
		int playerId = player.getId();
		if (playerId < players.length)
		{
			players[playerId] = player;
		}
		else
		{
			Logger.instance.log("WARNING: playerId = "+playerId+", NbPlayersDeclared = "+players.length);
		}
	}
	
	public void addTeam(Team team)
	{
		int teamId = team.getId();
		if (teamId < teams.length)
		{
			teams[teamId] = team;
		}
		else
		{
			Logger.instance.log("WARNING: teamId = "+teamId+", NbTeamDeclared = "+teams.length);
		}
	}
}
