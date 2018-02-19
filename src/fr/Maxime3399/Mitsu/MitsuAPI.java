package fr.Maxime3399.Mitsu;

import java.text.SimpleDateFormat;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import fr.Maxime3399.Mitsu.schedulers.WaitScheduler;
import fr.Maxime3399.Mitsu.utils.DataUtils;

public class MitsuAPI {
	
	public static String getServer(){
		return MainClass.getInstance().getConfig().getString("Server");
	}
	
	public static int getMaxPlayers(){
		return DataUtils.getServerIntInfo(MainClass.getInstance().getConfig().getString("Server"), "maxPlayers");
	}
	
	public static void setStatus(String status){
		DataUtils.setServerStringInfo(MainClass.getInstance().getConfig().getString("Server"), "status", status);
	}
	
	public static String getStatus(){
		return DataUtils.getServerStringInfo(MainClass.getInstance().getConfig().getString("Server"), "status");
	}
	
	public static void teleportPlayer(Player p, String server, boolean wait){
		
		if(DataUtils.typeExist(server)){
			
			String newSrv = null;
			
			for(String servers : DataUtils.getServers()){
				
				if(DataUtils.getServerStringInfo(servers, "type").equalsIgnoreCase(server)){
					
					if(DataUtils.getServerStringInfo(servers, "status").equalsIgnoreCase("join")){
						
						if(DataUtils.getServerIntInfo(servers, "players") <= DataUtils.getServerIntInfo(servers, "maxPlayers")){
							
							newSrv = servers;
							
						}
						
					}
					
				}
				
			}
			
			if(newSrv != null){
				
				MainClass.teleportToServer(p, newSrv);
				
			}else{
				
				if(!wait) {
					
					p.sendMessage("§e§oVous avez rejoint la file d'attente.");
					p.playSound(p.getLocation(), Sound.DOOR_OPEN, 100, 1);
					WaitScheduler.list.put(p, server);
					
				}else {
					
				    String time = "§4§lERREUR";
			    	if(WaitScheduler.time.get(p) > 60){
			    		time = new SimpleDateFormat("m:ss").format(WaitScheduler.time.get(p)*1000);
			    	}else if(WaitScheduler.time.get(p) > 9){
			    		time = new SimpleDateFormat("ss").format(WaitScheduler.time.get(p)*1000);
			    	}else{
			    		time = new SimpleDateFormat("s").format(WaitScheduler.time.get(p)*1000);
			    	}
					p.sendMessage("§b§l[§r§eFile d'attente§r§b§l]§r  §6Destination : §a"+server+"    §0§l|§r    §6Attente : §a"+time);
					
				}
				
			}
			
		}else{
			
			p.sendMessage("§cUne erreur est survenue !");
			p.playSound(p.getLocation(), Sound.ARROW_HIT, 100, 1);
			
		}
		
	}

}
