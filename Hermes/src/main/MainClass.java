package main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import messageAdapter.MessageAdapter;
import modele.Modele;
import modele.tools.KeyPressThread;
import vue.View;
import controler.MainController;

public class MainClass 
{
	public static void main (String [] args) throws NoSuchMethodException
	{		
		MainClass mainClass = new MainClass(args);
	}
	
	public MainClass(String [] args)
	{
		//Launch a thread to lister user inputs in console:
		//launchScanner();
		
		//Scan parameters and launch in the right configuration:
		inputParameters(args);
	}
	
	private void launchScanner()
	{
		KeyPressThread keyPressThread = new KeyPressThread();
        keyPressThread.start();
	}
	
	private void launchWithHmi()
	{
		//Create Model and View:
		Modele model = new Modele();
		View view = new View();
		
		//Tell Model about View. 
		model.addObserver(view);
		
		//Create Controller. tell it about Model and View, initialise model
		MainController controller = new MainController();
		controller.addModel(model);
		controller.addView(view);
		
		//Create Message Adapter
		MessageAdapter messageAdapter = new MessageAdapter(controller);
		
		//Tell View about Controller 
		view.addController(controller);
		view.addMessageAdapter(messageAdapter);
		
		//On lance le serveur de messagerie:
		messageAdapter.launchServer("TCP");
	}
	
	private void launchNoHmi(String jsFilePath, String xmlFilePath)
	{
		//Create Model and View:
		Modele model = new Modele();
		
		//Create Controller. tell it about Model and View, initialise model
		MainController controller = new MainController();
		controller.addModel(model);
		
		//Create the KeyPressThread:
		KeyPressThread keyPressThread = new KeyPressThread();
		keyPressThread.addContoller(controller);
        keyPressThread.start();
		
		//Create Message Adapter
		MessageAdapter messageAdapter = new MessageAdapter(controller);
		
		//Lancement sans IHM:
		controller.initScenario(jsFilePath);
		controller.initModel(xmlFilePath);
		controller.initLinks(messageAdapter.getMessageSender());
		//controller.launchTimer();
		
		//On lance le serveur de messagerie:
		messageAdapter.launchServer("TCP");
	}
	
	private void inputParameters(String [] args)
	{
		//Create the command line parser
		CommandLineParser parser = new DefaultParser();

		//Create the Options
		Options options = new Options();
		
		Option nohmiOption = new Option("nohmi", true, "Lancement sans IHM, préciser le fichier de scenario et le fichier de configuration." );
		nohmiOption.setArgs(2);
		
		options.addOption(nohmiOption);

		try
		{
			//Parse the command line arguments
			CommandLine line = parser.parse(options, args);

			//If the program has to be launch without HMI:
			if (line.hasOption("nohmi"))
			{
				if (line.getOptionValues("nohmi").length == 2)
				{
					String param1 = line.getOptionValues("nohmi")[0];
					String param2 = line.getOptionValues("nohmi")[1];
					
					String xmlFilePath = param1;
					String jsFilePath = param2;
					
					//On vérifie que l'ordre des parametres inscrits au lancement du jar:
					if (param1.endsWith(".xml") && param2.endsWith(".js"))
					{
						System.out.println("LAUNCH PARAMETERS: -nohmi, XML: "+xmlFilePath+", JS: "+jsFilePath);
						launchNoHmi(jsFilePath, xmlFilePath);
					}
					else if (param1.endsWith(".js") && param2.endsWith(".xml"))
					{
						xmlFilePath = param2;
						jsFilePath = param1;
						
						System.out.println("LAUNCH PARAMETERS: -nohmi, XML: "+xmlFilePath+", JS: "+jsFilePath);
						launchNoHmi(jsFilePath, xmlFilePath);
					}
					else
					{
						System.out.println("WRONG INPUT PARAMETERS FOR -nohmi OPTION");
					}					
				}
			}
			
			//If the program has to be launch with HMI:
			else
			{
				launchWithHmi();
			}
		}
		catch (ParseException exp)
		{
			System.out.println("Unexpected exception:" + exp.getMessage());
		}
	}
	
	@Deprecated
	private void oldLauncher()
	{
		//Create Model and View:
		Modele model = new Modele();
		View view = new View();
		
		//Tell Model about View. 
		model.addObserver(view);
		
		//Create Controller. tell it about Model and View, initialise model
		MainController controller = new MainController();
		controller.addModel(model);
		controller.addView(view);
		
		//Create Message Adapter
		MessageAdapter messageAdapter = new MessageAdapter(controller);
		
		//Lancement sans IHM:
		controller.initScenario("counterStrike.js");
		controller.initModel("counterStrike.xml");
		controller.initLinks(messageAdapter.getMessageSender());
		controller.launchTimer();

		//Tell View about Controller 
		view.addController(controller);
		view.addMessageAdapter(messageAdapter);
		
		//On lance le serveur de messagerie:
		messageAdapter.launchServer("TCP");
	}
	
}
