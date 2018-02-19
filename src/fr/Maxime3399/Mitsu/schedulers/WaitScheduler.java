package fr.Maxime3399.Mitsu.schedulers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.Maxime3399.Mitsu.MainClass;
import fr.Maxime3399.Mitsu.MitsuAPI;

public class WaitScheduler {
	
	public static HashMap<Player, String> list = new HashMap<>();
	public static HashMap<Player, Integer> time = new HashMap<>();
	
	public static void startScheduler() {
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(MainClass.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				
				for(Player pls : list.keySet()) {
					
					if(time.containsKey(pls)) {
						time.put(pls, time.get(pls)+1);
					}else {
						time.put(pls, 1);
					}
					MitsuAPI.teleportPlayer(pls, list.get(pls), true);
					
				}
				
			}
			
		}, 20, 20);
		
	}

}
