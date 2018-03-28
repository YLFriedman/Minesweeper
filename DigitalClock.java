public class DigitalClock{

private int seconds, minutes;

	//This class allows you to store a specific amount of time in minutes and seconds, and then display it in the style of a digital clock
	
	public DigitalClock(int seconds) {
		this.seconds = seconds%60;
		this.minutes = seconds/60;

		
}
	
	public void setTime(int seconds) {
		this.seconds = seconds;
	}
	
	public void addTime() {
		if(this.seconds == 59) {
			this.seconds = 0;
			this.minutes++;
		}
		else {
			this.seconds++;
		}
	}
	
	public String toString() {
		String timeFormat;
		if(seconds>9) {
			if(minutes> 9) {
				timeFormat = new String(minutes + " : " +  seconds);
			}
			else {
				timeFormat = new String("0" + minutes + " : " + seconds);
			}
		}
		else if(minutes > 9) {
			timeFormat = new String(minutes + " : " + "0" + seconds);
		}
		else{
			timeFormat = new String("0" + minutes + " : " + "0" + seconds);
		}
		return timeFormat;
	}
		
		
}
