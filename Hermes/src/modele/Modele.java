package modele;

import java.util.Observable;

import modele.scnObjects.Scenario;

public class Modele extends Observable
{
	Scenario scenarioLoaded;
	
	public Modele()
	{
		
	}
	
	public void addScenario(Scenario newScenario)
	{
		this.scenarioLoaded = newScenario;
		setChanged();
		notifyObservers(newScenario);
	}
}
