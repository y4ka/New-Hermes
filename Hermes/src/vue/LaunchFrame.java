package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import messageAdapter.MessageAdapter;
import messageAdapter.MessageReceiver;
import modele.tools.ViewLogger;
import controler.MainController;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;
import java.awt.Toolkit;

public class LaunchFrame extends JFrame implements ActionListener, ViewLogger {

	// GENERATED CLASS VARIABLES:
	
	private JPanel contentPane;
	private JTextField pathJavascript;
	private JPanel loadPanel;
	private JButton btnBrowseJavascript;
	private JPanel launchPanel;
	private JPanel statusPanel;
	private JLabel lblLoadJavascript;
	private JPanel panelLoadJavascript;
	private JPanel panelLoadXML;
	private JLabel lblLoadXML;
	private JTextField pathXML;
	private JButton btnBrowseXML;
	private JTextPane statusTextPane;
	private JButton btnLaunchScenario;
	private JButton btnLoadJavascript;
	private JButton btnLoadXML;
	private JButton btnStopScenario;
	private JScrollPane scrollPane;
	private JPanel messagePanel;
	private JPanel receiverPanel;
	private JPanel senderPanel;
	private JScrollPane scrollPaneReceiver;
	private JScrollPane scrollPaneSender;
	private JTextPane receiverTextPane;
	private JTextPane senderTextPane;
	
	// MANUAL CLASS VARIABLES:
	
	private MainController controller;
	private File xmlFile;
	private File jsFile;
	
	/**
	 * Create the frame.
	 */
	public LaunchFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("hermes.jpg"));
		setTitle("HERMES - Load and Launch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		loadPanel = new JPanel();
		loadPanel.setBorder(new TitledBorder(null, "Load", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(loadPanel);
		loadPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelLoadJavascript = new JPanel();
		loadPanel.add(panelLoadJavascript);
		
		lblLoadJavascript = new JLabel("Scenario Javascript File:");
		panelLoadJavascript.add(lblLoadJavascript);
		
		pathJavascript = new JTextField();
		pathJavascript.setEditable(false);
		panelLoadJavascript.add(pathJavascript);
		pathJavascript.setColumns(10);
		
		btnBrowseJavascript = new JButton("...");
		btnBrowseJavascript.addActionListener(this);
		panelLoadJavascript.add(btnBrowseJavascript);
		
		btnLoadJavascript = new JButton("Load");
		btnLoadJavascript.setEnabled(false);
		btnLoadJavascript.addActionListener(this);
		panelLoadJavascript.add(btnLoadJavascript);
		
		panelLoadXML = new JPanel();
		loadPanel.add(panelLoadXML);
		
		lblLoadXML = new JLabel("Entities XML File");
		
		pathXML = new JTextField();
		pathXML.setEditable(false);
		pathXML.setColumns(10);
		
		btnBrowseXML = new JButton("...");
		btnBrowseXML.setEnabled(false);
		btnBrowseXML.addActionListener(this);
		panelLoadXML.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelLoadXML.add(lblLoadXML);
		panelLoadXML.add(pathXML);
		panelLoadXML.add(btnBrowseXML);
		
		btnLoadXML = new JButton("Load");
		btnLoadXML.setEnabled(false);
		btnLoadXML.addActionListener(this);
		panelLoadXML.add(btnLoadXML);
		
		launchPanel = new JPanel();
		launchPanel.setBorder(new TitledBorder(null, "Launch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(launchPanel);
		
		btnLaunchScenario = new JButton("Launch Scenario");
		btnLaunchScenario.setEnabled(false);
		btnLaunchScenario.addActionListener(this);
		launchPanel.add(btnLaunchScenario);
		
		btnStopScenario = new JButton("Stop Scenario");
		btnStopScenario.addActionListener(this);
		btnStopScenario.setEnabled(false);
		launchPanel.add(btnStopScenario);
		
		statusPanel = new JPanel();
		statusPanel.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(statusPanel);
		
		statusTextPane = new JTextPane();
		statusPanel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane(statusTextPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		statusPanel.add(scrollPane);
		
		// ===== MESSAGE ADAPTER PANEL =====
		
		messagePanel = new JPanel();
		messagePanel.setBorder(new TitledBorder(null, "Message Adapter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		messagePanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.add(messagePanel);
			
			// ===== RECEIVER =====
			
			receiverPanel = new JPanel();
			receiverPanel.setBorder(new TitledBorder(null, "Receiver", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			receiverPanel.setLayout(new BorderLayout(0, 0));
			messagePanel.add(receiverPanel);
			
			receiverTextPane = new JTextPane();
			
			scrollPaneReceiver = new JScrollPane(receiverTextPane);
			scrollPaneReceiver.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			receiverPanel.add(scrollPaneReceiver);
			
			// ===== SENDER =====
		
			senderPanel = new JPanel();
			senderPanel.setBorder(new TitledBorder(null, "Sender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			senderPanel.setLayout(new BorderLayout(0, 0));
			messagePanel.add(senderPanel);
			
			senderTextPane = new JTextPane();
			
			scrollPaneSender = new JScrollPane(senderTextPane);
			scrollPaneSender.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			senderPanel.add(scrollPaneSender);
		
		setVisible(true);
		
		//Add TextPane to Logger listener list:
		Logger.instance.addListener(statusTextPane);
		Logger.instance.addListenerReceiver(receiverTextPane);
		Logger.instance.addListenerSender(senderTextPane);
	}

	public void actionPerformed(ActionEvent e) 
	{
		String currentPath = ".";
		try 
		{
			currentPath = LaunchFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} 
		catch (URISyntaxException e1) 
		{	
			e1.printStackTrace();
		}
		
		if (e.getSource().equals(btnBrowseJavascript))
		{
			JFileChooser fileChooserJavascript = new JFileChooser();
			fileChooserJavascript.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooserJavascript.setDialogTitle("Select Scenario Javascript File");
			fileChooserJavascript.setCurrentDirectory(new File(currentPath));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Javascript Files", "js");
			fileChooserJavascript.setFileFilter(filter);
			int result = fileChooserJavascript.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) 
			{
			    jsFile = fileChooserJavascript.getSelectedFile();
			    pathJavascript.setText(jsFile.getName());
			    btnLoadJavascript.setEnabled(true);
			    Logger.instance.log("Javascript Scenario File selected: "+jsFile);
			}
		}
		else if (e.getSource().equals(btnBrowseXML))
		{
			JFileChooser fileChooserXML = new JFileChooser();
			fileChooserXML.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooserXML.setDialogTitle("Select Parameter XML File");
			fileChooserXML.setCurrentDirectory(new File(currentPath));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
			fileChooserXML.setFileFilter(filter);
			int result = fileChooserXML.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) 
			{
			    xmlFile = fileChooserXML.getSelectedFile();
			    pathXML.setText(xmlFile.getName());
			    btnLoadXML.setEnabled(true);
			    Logger.instance.log("XML Parameter File selected: "+xmlFile);
			}
		}
		else if (e.getSource().equals(btnLoadJavascript))
		{
			if (jsFile != null)
			{
				controller.initScenario(jsFile.getAbsolutePath());
				btnBrowseXML.setEnabled(true);
				Logger.instance.log("Scenario from Javascript file loaded");
			}
			else
			{
				Logger.instance.log("Javascript File not selected");
			}
		}
		else if (e.getSource().equals(btnLoadXML))
		{
			if (xmlFile != null)
			{
				controller.initModel(xmlFile.getAbsolutePath());
				btnLaunchScenario.setEnabled(true);
				Logger.instance.log("Data from XML file loaded");
			}
			else
			{
				Logger.instance.log("XML file not selected");
			}
		}
		else if (e.getSource().equals(btnLaunchScenario))
		{
			//On lance le Timer du script:
			controller.launchTimer();
			btnStopScenario.setEnabled(true);
			Logger.instance.log("Launching scenario");
		}
		else if (e.getSource().equals(btnStopScenario))
		{
			controller.stopTimer();
			btnLaunchScenario.setEnabled(false);
			Logger.instance.log("Stopping scenario");
		}
	}
	
	public void addController(MainController controller)
	{
		this.controller = controller;
	}
	
	@Override
	public void log(String log) {
		// TODO Auto-generated method stub
		
	}
}
