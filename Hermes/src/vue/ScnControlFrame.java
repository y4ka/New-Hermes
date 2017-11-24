package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import controler.MainController;
import modele.scnObjects.Scenario;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class ScnControlFrame extends JFrame {

	private JPanel contentPane;
	private JPanel mainPanel;
	private JPanel panelPylon;
	private JPanel panelTeam;
	private JPanel panelPlayers;

	/**
	 * Create the frame.
	 */
	public ScnControlFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("hermes.jpg"));
		setTitle("HERMES - ScnControlFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		panelPylon = new JPanel();
		panelPylon.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pylons", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mainPanel.add(panelPylon);
		panelPylon.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelTeam = new JPanel();
		panelTeam.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Teams", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mainPanel.add(panelTeam);
		panelTeam.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelPlayers = new JPanel();
		panelPlayers.setBorder(new TitledBorder(null, "Players", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mainPanel.add(panelPlayers);
		panelPlayers.setLayout(new GridLayout(0, 1, 0, 0));
		setVisible(true);
	}
	
	public void update(Observable obs, Object obj) 
	{
		Scenario scenario = (Scenario) obj;
		
		//Remove all previous components (update)
		panelPlayers.removeAll();
		panelPylon.removeAll();
		panelTeam.removeAll();
		
		//Affichage dynamique des Players:
		for (int i = 0 ; i < scenario.getNumberOfPlayers() ; i++)
		{
			int playerId = scenario.getPlayers()[i].getId();
			String playerName = scenario.getPlayers()[i].getName();
			
			JPanel panelPlayer = new JPanel();
			panelPlayer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player no "+playerId, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPlayer.setLayout(new GridLayout(0, 1, 0, 0));
			JLabel labelPlayerName = new JLabel("<html><u>Name:</u> "+playerName+"</html>");
			
			panelPlayer.add(labelPlayerName);
			
			//Ajout des labels créés dynamiquement à la fenetre:
			panelPlayers.add(panelPlayer);
		}
		
		//Affichage dynamique des Pylons:
		for (int i = 0 ; i < scenario.getNumberOfPylons() ; i++)
		{
			int pylonId = scenario.getPylons()[i].getId();
			String pylonName = scenario.getPylons()[i].getName();
			int nbOwners = scenario.getPylons()[i].getNbOwners();
			int batteryVoltage = scenario.getPylons()[i].getBatteryVoltage();
			int duskLevel = scenario.getPylons()[i].getDuskLevel();
			int radioLinkQuality = scenario.getPylons()[i].getRadioLinkQuality();
			int wifiLinkQuality = scenario.getPylons()[i].getWifiLinkQuality();
			
			JPanel panelPylons = new JPanel();
			panelPylons.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pylon no "+pylonId, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPylons.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel labelPlayerName = new JLabel("<html><u>Name:</u> "+pylonName+"</html>");
			JLabel labelNbOwners = new JLabel("<html><u>Nb Owners:</u> "+nbOwners+"</html>");
			JLabel labelBatteryVoltage = new JLabel("<html><u>Tension batterie:</u> "+batteryVoltage+" V</html>");
			JLabel labelDuskLevel = new JLabel("<html><u>Niveau Crepuscule:</u> "+duskLevel+" %</html>");
			JLabel labelRadioLinkQuality = new JLabel("<html><u>Liaison Radio:</u> "+radioLinkQuality+" %</html>");
			JLabel labelWifiLinkQuality = new JLabel("<html><u>Liaison Wifi:</u> "+wifiLinkQuality+" %</html>");
			
			panelPylons.add(labelPlayerName);
			panelPylons.add(labelNbOwners);
			panelPylons.add(labelBatteryVoltage);
			panelPylons.add(labelDuskLevel);
			panelPylons.add(labelRadioLinkQuality);
			panelPylons.add(labelWifiLinkQuality);
			
			//Ajout des labels créés dynamiquement à la fenetre:
			panelPylon.add(panelPylons);
		}
		
		//Affichage dynamique des Teams:
		for (int i = 0 ; i < scenario.getNumberOfTeams() ; i++)
		{
			int teamId = scenario.getTeams()[i].getId();
			String teamName = scenario.getTeams()[i].getName();
			String teamShortName = scenario.getTeams()[i].getShortName();
			boolean teamSpawnAllowed = scenario.getTeams()[i].isSpawnAllowed();
			int teamSpawnNumber = scenario.getTeams()[i].getSpawnNumber();
			int teamNbPlayers = scenario.getTeams()[i].getNbPlayers();
			
			JPanel panelTeams = new JPanel();
			panelTeams.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Team no "+teamId, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelTeams.setLayout(new GridLayout(0, 1, 0, 0));
			JLabel labelTeamName = new JLabel("<html><u>Name:</u> "+teamName+"</html>");
			JLabel labelTeamShortName = new JLabel("<html><u>Short Name:</u> "+teamShortName+"</html>");
			JLabel labelTeamSpawnAllowed = new JLabel("<html><u>Spawn Allowed:</u> "+teamSpawnAllowed+"</html>");
			JLabel labelTeamSpawnNumber = new JLabel("<html><u>Spawn Number:</u> "+teamSpawnNumber+"</html>");
			JLabel labelTeamNbPlayers = new JLabel("<html><u>Nb Players:</u> "+teamNbPlayers+"</html>");
			
			panelTeams.add(labelTeamName);
			panelTeams.add(labelTeamShortName);
			panelTeams.add(labelTeamSpawnAllowed);
			panelTeams.add(labelTeamSpawnNumber);
			panelTeams.add(labelTeamNbPlayers);
			
			//Ajout des joueurs:
			for (int j = 0 ; j < scenario.getTeams()[i].getPlayers().size() ; j++)
			{
				String playerName = scenario.getTeams()[i].getPlayers().get(j).getName();
				JLabel labelPlayerName = new JLabel("- "+playerName);
				panelTeams.add(labelPlayerName);
			}
			
			//Ajout des labels créés dynamiquement à la fenetre:
			panelTeam.add(panelTeams);
		}
		
		//On rafraichit la fenetre:
		this.revalidate();
	}
	
	public void addController(MainController controller)
	{
		
	}
}
