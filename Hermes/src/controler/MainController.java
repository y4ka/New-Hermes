package controler;

import java.util.Timer;
import java.util.TimerTask;

import javax.script.ScriptException;

import messageAdapter.MessageSender;
import modele.Modele;
import modele.scnObjects.Scenario;
import modele.tools.XmlTool;
import vue.View;


public class MainController
{
	
	private Modele model;
	private View view;
	
	private ScriptReader scriptReader =  new ScriptReader(this);
	private XmlTool xmlTool = new XmlTool();
	private TimerTask task;
	private Timer timer = new Timer();
	private int nbTick = 0;
	
	public MainController()
	{
		//Creation du Timer:
		task = new TimerTask()
		{
			@Override
			public void run() 
			{
				try 
				{
					scriptReader.invokeTick(nbTick);
					scriptReader.checkVictory();
					nbTick++;
				} 
				catch (NoSuchMethodException | ScriptException e) 
				{
					e.printStackTrace();
				}
			}
			
		};
		
		//TEST SCENARIO:
		//scriptReader.invokeButtonInput(0, "BLUE");
	}
	
//	   =================================================
//	   ===== ===== METHODES D'INITIALISATION ===== =====
//	   =================================================
	
	public void initScenario(String fileName)
	{
		//Initialisation du ScriptReader:
		scriptReader.loadEngine();
		
		//Chargement du fichier de Script
		scriptReader.loadScript(fileName);
	}
	
	public void initModel(String fileName)
	{
		//Recuperation des données du fichier XML:
		Scenario scenarioLoaded = xmlTool.getScenarioParameterFile(fileName);
		
		//Ajout des données récupérées au modèle:
		model.addScenario(scenarioLoaded);
		
		//Injection des données récupérées dans le script:
		scriptReader.injectScenarioIntoScript(scenarioLoaded);
	}
	
	public void initLinks(MessageSender messageSender)
	{
		//Injection du messageSender dans le script afin de faire la liaison SERVEUR -> PYLONE:
		scriptReader.injectMessageSenderIntoScript(messageSender);
	}
	
	public void addModel(Modele m)
	{
		this.model = m;
		this.scriptReader.addModele(m);
	}

	public void addView(View v)
	{
		this.view = v;
	}
	
//	   =========================================
//	   ===== ===== METHODES DU TIMER ===== =====
//	   =========================================
	
	public void launchTimer()
	{
		//Lancement du Timer:
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
	public void stopTimer()
	{
		//Fin du Timer:
		timer.cancel();
	}
	
//	   =======================================
//	   ===== ===== AUTRES METHODES ===== =====
//	   =======================================	
	
	public ScriptReader getScriptReader()
	{
		return scriptReader;
	}
}
