package controler;

import java.io.File;
import java.util.List;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import messageAdapter.MessageSender;
import modele.Modele;
import modele.scnObjects.Player;
import modele.scnObjects.Pylon;
import modele.scnObjects.Scenario;
import modele.scnObjects.Team;
import modele.tools.FileTool;
import modele.tools.ViewLogger.Logger;

public class ScriptReader 
{
	private ScriptEngine moteur;
	private String scriptString;
	private Invocable moteurInvocable;
	
	private Modele modele;
	private MainController controller;
	
	public ScriptReader(MainController controller)
	{
		this.controller = controller;
	}
	
//	   ===================================================
//	   ===== ===== METHODES D'APPEL JAVASCRIPT ===== =====
//	   ===================================================
	
	public void invokeButtonInput(int idPylon, String colorButton)
	{
		try 
		{
			Object result = moteurInvocable.invokeFunction("inputButton", idPylon, colorButton);
			Logger.instance.log(""+result);
		} 
		catch (NoSuchMethodException e) {
			Logger.instance.log("Javascript Method not found !");
			e.printStackTrace();
		} catch (ScriptException e) {
			Logger.instance.log("Script Exception !");
			e.printStackTrace();
		}
	}
	
	public void invokeTargetInput(int idPylon)
	{
		try 
		{
			Object result = moteurInvocable.invokeFunction("inputTarget", idPylon);
			Logger.instance.log(""+result);
		}
		catch (NoSuchMethodException e) {
			Logger.instance.log("Javascript Method not found !");
			e.printStackTrace();
		} catch (ScriptException e) {
			Logger.instance.log("Script Exception !");
			e.printStackTrace();
		}
	}
	
	public void invokeKeyboardInput(int idPylon, String keyboardString)
	{
		try 
		{
			Object result = moteurInvocable.invokeFunction("inputKeyboard", idPylon, keyboardString);
			Logger.instance.log(""+result);
		} 
		catch (NoSuchMethodException e) {
			Logger.instance.log("Javascript Method not found !");
			e.printStackTrace();
		} catch (ScriptException e) {
			Logger.instance.log("Script Exception !");
			e.printStackTrace();
		}
	}
	
	public void invokeTick(int nbTick) throws NoSuchMethodException, ScriptException
	{
		Object result = moteurInvocable.invokeFunction("tick", nbTick);
		Logger.instance.log(""+result);
		
		// On recupere les valeurs en sortie et on met à jour le modèle:
		Scenario scenario = (Scenario)moteur.get("scenario");
		modele.addScenario(scenario);
	}
	
	public void checkVictory() throws NoSuchMethodException, ScriptException
	{
		Object result = moteurInvocable.invokeFunction("checkVictory");
		
		boolean booleanResult = (Boolean) result;
		
		if (booleanResult == true)
		{
			Logger.instance.log("Victory, fin du scenario.");
			controller.stopTimer();
		}
	}
	
	
//	   =================================================
//	   ===== ===== METHODES D'INITIALISATION ===== =====
//	   =================================================
	
	public boolean injectScenarioIntoScript(Scenario scenario)
	{
		if (moteur != null)
		{
			moteur.put("scenario", scenario);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean injectMessageSenderIntoScript(MessageSender messageSender)
	{
		if (moteur != null)
		{
			moteur.put("messageSender", messageSender);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void loadEngine()
	{
		Logger.instance.log("\n***** LOAD SCRIPT ENGINE *****");
		
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		
		for (ScriptEngineFactory factory : factories) 
		{
			Logger.instance.log("Name : " + factory.getEngineName());
			Logger.instance.log("Version : " + factory.getEngineVersion());
			Logger.instance.log("Language name : " + factory.getLanguageName());
			Logger.instance.log("Language version : " + factory.getLanguageVersion());
			Logger.instance.log("Extensions : " + factory.getExtensions());
			Logger.instance.log("Mime types : " + factory.getMimeTypes());
			Logger.instance.log("Names : " + factory.getNames());
		}
		
		//CHARGEMENT DU SCRIPT ENGINE:
		String engineName = "javascript";
		this.moteur = manager.getEngineByName(engineName);
		if (moteur == null) 
		{ 
	        Logger.instance.log("Impossible de trouver le moteur "+engineName+"."); 
	    }
		else
		{
			Logger.instance.log("Moteur "+engineName+" chargé.");
		}
		
		//On vérifie si le moteur est Invocable:
		if (moteur instanceof Invocable) 
		{
			moteurInvocable = (Invocable) moteur;
		}
		else 
		{
			System.err.println("Le moteur n'implemente pas l'interface Invocable");
		}
		
	}
	
	public void loadScript(String scenarioFileName)
	{
		Logger.instance.log("\n***** LOAD SCRIPT FILE *****");
		
		//CHARGEMENT DU SCRIPT:
		FileTool fileTool = new FileTool();
		this.scriptString = fileTool.readFile(scenarioFileName);
		
		//EVALUATION DU SCRIPT:
		try 
		{
			moteur.eval(scriptString);
		} 
		catch (ScriptException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void addModele(Modele modele)
	{
		this.modele = modele;
	}
}
