package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.script.ScriptException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controler.MainController;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import messageAdapter.MessageAdapter;
import messageAdapter.MessageReceiver;
import modele.scnObjects.Scenario;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.Toolkit;

public class InputFrame extends JFrame {

	private JPanel contentPane;
	private JPanel mainPanel;
	
	private MessageAdapter messageAdapter;
	private MessageReceiver messageReceiver;

	/**
	 * Create the frame.
	 */
	public InputFrame() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("hermes.jpg"));
		setTitle("HERMES - InputFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(1, 0, 0, 0));
		setVisible(true);
	}
	
	public void update(Observable obs, Object obj) 
	{
		Scenario scenario = (Scenario) obj;
		
		//Remove all previous components (update)
		mainPanel.removeAll();
		
		//Affichage Dynamique des Pylons:
		for (int i = 0 ; i < scenario.getNumberOfPylons() ; i++)
		{
			int pylonId = scenario.getPylons()[i].getId();
			String pylonName = scenario.getPylons()[i].getName();
			boolean enabled = scenario.getPylons()[i].isEnabled();
			boolean hitDetectorEnabled = scenario.getPylons()[i].isHitDetectorEnabled();
			boolean keyboardEnabled = scenario.getPylons()[i].isKeyboardEnabled();
			int nbOwners = scenario.getPylons()[i].getNbOwners();
			
			JPanel panelPylons = new JPanel();
			panelPylons.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pylon no "+pylonId, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPylons.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel labelPlayerName = new JLabel("<html><u>Name:</u> "+pylonName+"</html>");
			JLabel labelNbOwners = new JLabel("<html><u>Nb Owners:</u> "+nbOwners+"</html>");
			JButton buttonRed = new JButton("Red");
			JButton buttonBlue = new JButton("Blue");
			JButton buttonBlack = new JButton("Black");
			JButton buttonYellow = new JButton("Yellow");
			JButton buttonGreen = new JButton("Green");
			JLabel labelHitDetector = new JLabel("<html><u>Target:</u></html>");
			JButton buttonHitDetector = new JButton("Hit Detector");
			JLabel labelKeyboard = new JLabel("<html><u>Keyboard:</u></html>");
			JTextField textFieldKeyboard = new JTextField();
			JButton buttonKeyboard = new JButton("Envoyer");
			
			//Creation des Listeners des boutons:
			buttonRed.addActionListener(createDynamicActionListeners(pylonId, "RED"));
			buttonBlue.addActionListener(createDynamicActionListeners(pylonId, "BLUE"));
			buttonBlack.addActionListener(createDynamicActionListeners(pylonId, "BLACK"));
			buttonYellow.addActionListener(createDynamicActionListeners(pylonId, "YELLOW"));
			buttonGreen.addActionListener(createDynamicActionListeners(pylonId, "GREEN"));
			
			//Creation du Listener de la target:
			buttonHitDetector.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					messageReceiver.targetInput(pylonId);
				}
			});
			
			//Creation du Listener du keyboard:
			buttonKeyboard.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					messageReceiver.keyboardInput(pylonId, textFieldKeyboard.getText());
				}
			});
			
			//Activation - Desactivation des composants:
			if (hitDetectorEnabled == false || enabled == false)
			{
				buttonHitDetector.setEnabled(false);
			}
			if (keyboardEnabled == false || enabled == false)
			{
				textFieldKeyboard.setEnabled(false);
				buttonKeyboard.setEnabled(false);
			}
			
			//Ajout des composants au panel:
			panelPylons.add(labelPlayerName);
			panelPylons.add(labelNbOwners);
			panelPylons.add(buttonRed);
			panelPylons.add(buttonBlue);
			panelPylons.add(buttonBlack);
			panelPylons.add(buttonYellow);
			panelPylons.add(buttonGreen);
			panelPylons.add(labelHitDetector);
			panelPylons.add(buttonHitDetector);
			panelPylons.add(labelKeyboard);
			panelPylons.add(textFieldKeyboard);
			panelPylons.add(buttonKeyboard);
			
			//Ajout des composants créés dynamiquement à la fenetre:
			mainPanel.add(panelPylons);
			
			//On rafraichit la fenetre:
			this.revalidate();
		}
	}
	
	private ActionListener createDynamicActionListeners(int pylonId, String colorButton)
	{
		ActionListener listenerButton = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				messageReceiver.inputButton(pylonId, colorButton);
			}
		};
		
		return listenerButton;
	}
	
	public void addMessageAdapter(MessageAdapter messageAdapter)
	{
		this.messageAdapter = messageAdapter;
		this.messageReceiver = messageAdapter.getMessageReceiver();
	}
}
