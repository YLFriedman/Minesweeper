import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
*
*The class TimePanel is an extension of JPanel, and allows for the minesweeper application to display the play time to the use
*
*@author Yehuda Friedman, YouYang Xu
*
*/

public class TimePanel extends JPanel{

	//This class is an extension of JPanel, and uses the Timer class and my custom DisplayTask class to display a timer which 
	//updates itself every second
	
	private DisplayTask task;
	private JButton start, stop, reset;
	private JLabel digitalDisplay;
	private DigitalClock clock;
	private Timer myThread;
	private boolean on = false;
	private boolean wasActivated = false;
	
	public TimePanel() {
		myThread = new Timer(); //The thread which will run the DisplayTask
		task = new DisplayTask(this); //Assigns the DisplayTask to this TimePanel
		clock = new DigitalClock(0);
		digitalDisplay = new JLabel(clock.toString());

		digitalDisplay.setFont(new Font("Calibri", Font.BOLD, 20)); //Setting the font for the timer
		
		
		this.setLayout(new FlowLayout());
	
		add(digitalDisplay);
		
		
	}
	
	public void run() {
		//the task that will be carried out when the thread is activated
		clock.addTime();
		digitalDisplay.setText(clock.toString());
	}
	/**
	* a method that starts the timer, and adjusts the appropriate Booleans to reflect the state of the timer
	*/
	public void activate() {
		if(!on){
			//if the clock isn't on, turn it on
			myThread.scheduleAtFixedRate(task, 0, 1000);
			on = true;
			wasActivated = true;
		}
		
	}
	/**
	*A method to stop the timer, and terminate its TimerTask and Timer objects
	*/
	public void pause(){
		if(on){
			task.cancel();
			myThread.cancel();
			on = false;
		}
	}

	/**
	*returns true if the timer has been turned on, false if not
	*
	*@return the boolean instance variable wasActivated
	*/

	public boolean hasBeenActivated(){
		return wasActivated;
	}
}
