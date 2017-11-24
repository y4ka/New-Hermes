package messageAdapter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import modele.tools.ViewLogger.Logger;
import controler.MainController;

public class MessageAdapter 
{
	private MainController controller;
	private MessageReceiver messageReceiver;
	private MessageSender messageSender;
	
	private ArrayList<TCPServer> socketList = new ArrayList<TCPServer>();
	
	private int port = 9632;
	private int taille = 1024;
	
	public MessageAdapter(MainController controller)
	{
		this.controller = controller;
		
		this.messageReceiver = new MessageReceiver(controller);
		this.messageSender = new MessageSender(this);
	}
	
	public void launchServer(String protocol)
	{
		try 
		{
			switch(protocol)
			{
				case "UDP":
					this.launchUDP();
					break;
				case "TCP":
					this.launchTCP();
					break;
				default:
					this.launchTCP();
					break;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
//	   ==================================
//	   ===== ===== TCP SERVER ===== =====
//	   ==================================
	
	private void launchTCP() throws IOException 
	{
		ServerSocket socketServeur = new ServerSocket(port);
		
		Logger.instance.logReceiver("LAUNCHING TCP SERVER");
		
		while (true) 
		{
			//On ouvre une nouvelle connexion TCP:
			Socket socketClient = socketServeur.accept();
			TCPServer t = new TCPServer(socketClient, this);
			t.start();
			
			//On ajoute cette connexion dans une liste de clients:
			socketList.add(t);
		}
	}
	
	public void sendTCP(String message)
	{
		if (!socketList.isEmpty())
		{
			for (TCPServer socket : socketList)
			{
				socket.send(message);
			}
		}
		else
		{
			Logger.instance.logSender("NO SOCKET OPEN, MESSAGE NOT SENT");
		}
	}
	
//	   ==================================
//	   ===== ===== UDP SERVER ===== =====
//	   ==================================
	
	@Deprecated
	private void launchUDP() throws IOException
	{
		byte buffer[] = new byte[taille];
		DatagramSocket socket = new DatagramSocket(port);
	    String donnees = "";
	    String messageRetour = "";
	    int taille = 0;
	    
		Logger.instance.logReceiver("LAUNCHING UDP SERVER");
	    
		while (true) 
	    {
	      DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
	      DatagramPacket envoi = null;
	      socket.receive(paquet);
	      
	      taille = paquet.getLength();
	      donnees = new String(paquet.getData(),0, taille);
	      
	      //On envoie le message reçu aux methodes pour le traiter:
	      //this.messageDecryption(donnees);
	      
	      Logger.instance.logReceiver(donnees);
	      
	      messageRetour = "ACK: "+donnees;
	      envoi = new DatagramPacket(messageRetour.getBytes(), messageRetour.length(), paquet.getAddress(), paquet.getPort());
	      socket.send(envoi);
	    }
	}
	
	
	
//	   =======================================
//	   ===== ===== GETTER / SETTER ===== =====
//	   =======================================

	public MessageReceiver getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
}
