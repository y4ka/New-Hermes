package messageAdapter;

public class MessageSender 
{
	private MessageAdapter messageAdapter;
	
	public MessageSender(MessageAdapter messageAdapter)
	{
		this.messageAdapter = messageAdapter;
	}
	
	public void sendMessage(String formatedMessage)
	{
		messageAdapter.sendTCP(formatedMessage);
	}
	
//	   ==================================================
//	   ===== ===== METHODES SERVEUR -> PYLONE ===== =====
//	   ==================================================
	
	public void allumerSirene(int pylonID, int duration, int period, int cycle)
	{
		//On forme le message en fonction des parametres recup�r�s du scen�rio
		String formatedMessage = "ALLUMER SIRENE "+pylonID+"/"+duration+"/"+period+"/"+cycle;
		
		//On envoie le message au serveur TCP:
		sendMessage(formatedMessage);
	}
	
	public void allumerLED(int pylonID, String color)
	{
		//On forme le message en fonction des parametres recup�r�s du scen�rio
		String formatedMessage = "ALLUMER LED "+pylonID+"/"+color;
		
		//On envoie le message au serveur TCP:
		sendMessage(formatedMessage);
	}
}
