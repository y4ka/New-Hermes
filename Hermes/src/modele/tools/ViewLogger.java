package modele.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public interface ViewLogger 
{
	void log(String log);

	enum Logger 
	{
		instance;

		private List<JTextPane> listeners = new LinkedList<JTextPane>();
		private List<JTextPane> listenersReceiver = new LinkedList<JTextPane>();
		private List<JTextPane> listenersSender = new LinkedList<JTextPane>();
		
		public void addListener(JTextPane statusTextPane)
		{
			synchronized (listeners) 
			{
				listeners.add(statusTextPane);
			}
		}
		
		public void addListenerReceiver(JTextPane statusTextPane)
		{
			synchronized (listenersReceiver) 
			{
				listenersReceiver.add(statusTextPane);
			}
		}
		
		public void addListenerSender(JTextPane statusTextPane)
		{
			synchronized (listenersSender) 
			{
				listenersSender.add(statusTextPane);
			}
		}

		public void log(String log) 
		{
			synchronized (listeners) 
			{
				//Sortie des logs dans la vue:
				for (JTextPane jtextPane : listeners)
				{
					StyledDocument doc = jtextPane.getStyledDocument();
					SimpleAttributeSet keyWord = new SimpleAttributeSet();
					
					try 
					{
						doc.insertString(doc.getLength(), log+"\n", keyWord);
					} 
					catch (BadLocationException e) 
					{
						e.printStackTrace();
					}
				}
				
				//Sortie des logs en console:
				System.out.println(log);
			}
		}
		
		public void logReceiver(String log)
		{
			synchronized (listenersReceiver) 
			{
				//Sortie des logs dans la vue:
				for (JTextPane jtextPane : listenersReceiver)
				{
					StyledDocument doc = jtextPane.getStyledDocument();
					SimpleAttributeSet keyWord = new SimpleAttributeSet();
					
					//On recupere l'heure courante et on la formatte:
					SimpleDateFormat d = new SimpleDateFormat ("dd/MM/yyyy");
					SimpleDateFormat h = new SimpleDateFormat ("hh:mm");
					Date currentTime = new Date();
					String dateString = d.format(currentTime);
					String hourString = h.format(currentTime);
					
					try 
					{
						doc.insertString(doc.getLength(), dateString+" "+hourString+" : "+log+"\n", keyWord);
					} 
					catch (BadLocationException e) 
					{
						e.printStackTrace();
					}
				}
				
				//Sortie des logs en console:
				System.out.println(log);
			}
		}
		
		public void logSender(String log)
		{
			synchronized (listenersSender) 
			{
				//Sortie des logs dans la vue:
				for (JTextPane jtextPane : listenersSender)
				{
					StyledDocument doc = jtextPane.getStyledDocument();
					SimpleAttributeSet keyWord = new SimpleAttributeSet();
					
					//On recupere l'heure courante et on la formatte:
					SimpleDateFormat d = new SimpleDateFormat ("dd/MM/yyyy");
					SimpleDateFormat h = new SimpleDateFormat ("hh:mm");
					Date currentTime = new Date();
					String dateString = d.format(currentTime);
					String hourString = h.format(currentTime);
					
					try 
					{
						doc.insertString(doc.getLength(), dateString+" "+hourString+" : "+log+"\n", keyWord);
					} 
					catch (BadLocationException e) 
					{
						e.printStackTrace();
					}
				}
				
				//Sortie des logs en console:
				System.out.println(log);
			}
		}
	}
}
