package fr.Maxime3399.Mitsu.schedulers;

public class SchedulersManager {
	
	public static void registerSchedulers(){
		
		ConnectScheduler.startScheduler();
		WaitScheduler.startScheduler();
		
	}

}
