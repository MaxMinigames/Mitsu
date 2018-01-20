package fr.Maxime3399.Mitsu.schedulers;

import org.bukkit.Bukkit;

import fr.Maxime3399.Mitsu.MainClass;
import fr.Maxime3399.Mitsu.utils.DataUtils;

public class ConnectScheduler {
	
	public static void startScheduler(){
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(MainClass.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				
				String server = MainClass.getInstance().getConfig().getString("Server");
				String next = DataUtils.getServerConnect();
				
				if(server.equals(next)){
					
					//TEST AND RUN
					
				}
				
			}
			
		}, 100, 100);
		
	}

}
