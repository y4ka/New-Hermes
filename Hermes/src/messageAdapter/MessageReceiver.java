package messageAdapter;

import modele.tools.ViewLogger.Logger;
import controler.MainController;

public class MessageReceiver
{
	private MainController controller;
	
	public MessageReceiver(MainController controller)
	{
		this.controller = controller;
	}
	
	public void decode(String trame)
	{
		//On parse le message et on appelle les méthodes ci-dessous.
	}
	
//	   ==================================================
//	   ===== ===== METHODES PYLONE -> SERVEUR ===== =====
//	   ==================================================
	
	public void inputButton(int idPylon, String colorButton)
	{
		controller.getScriptReader().invokeButtonInput(idPylon, colorButton);
		Logger.instance.logReceiver("INPUT BUTTON: "+idPylon+", "+colorButton);
	}
	
	public void keyboardInput(int idPylon, String keyboardString)
	{
		controller.getScriptReader().invokeKeyboardInput(idPylon, keyboardString);
		Logger.instance.logReceiver("INPUT KEYBOARD: "+idPylon+", "+keyboardString);
	}
	
	public void targetInput(int idPylon)
	{
		controller.getScriptReader().invokeTargetInput(idPylon);
		Logger.instance.logReceiver("INPUT TARGET: "+idPylon);
	}
	
	public void batteryVoltage(int idPylon, int value)
	{
		Logger.instance.logReceiver("BATTERY VOLTAGE: "+idPylon+", "+value);
	}
	
	public void duskLevel(int idPylon, int value)
	{
		Logger.instance.logReceiver("DUSK LEVEL: "+idPylon+", "+value);
	}
	
	public void radioLinkQuality(int idPylon, int value)
	{
		Logger.instance.logReceiver("RADIO LINK QUALITY: "+idPylon+", "+value);
	}
	
	public void wifiLinkQuality(int idPylon, int value)
	{
		Logger.instance.logReceiver("WIFI LINK QUALITY: "+idPylon+", "+value);
	}	
}
