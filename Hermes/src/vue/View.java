package vue;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import messageAdapter.MessageAdapter;
import controler.MainController;

public class View implements Observer
{
	ScnControlFrame scnControlFrame;
	LaunchFrame launchFrame;
	InputFrame inputFrame;
	
	public View()
	{
		scnControlFrame = new ScnControlFrame();
		launchFrame = new LaunchFrame();
		inputFrame = new InputFrame();
	}
	
	//Called from the Model
	public void update(Observable obs, Object obj) 
	{
		scnControlFrame.update(obs, obj);
		inputFrame.update(obs, obj);
	}
	
	public void addController(MainController controller)
	{
		scnControlFrame.addController(controller);
		launchFrame.addController(controller);
	}
	
	public void addMessageAdapter(MessageAdapter messageAdapter)
	{
		inputFrame.addMessageAdapter(messageAdapter);
	}
}
