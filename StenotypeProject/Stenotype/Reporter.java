package Stenotype;

// average

public class Reporter extends Thread{
	long period;
	 
	public Reporter() {
		this.period=period;
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(period);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
	}
}
