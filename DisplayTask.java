import java.util.TimerTask;

//This class is a Timertask, which will let the TimePanel class schedule a task at a regular interval

public class DisplayTask extends TimerTask {
	
	private TimePanel myPanel;
	
	public DisplayTask(TimePanel myPanel) {
		this.myPanel = myPanel;
		
	}
	
	public void run() {
		myPanel.run();
		
	}

}
