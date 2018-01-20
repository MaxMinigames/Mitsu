package fr.Maxime3399.Mitsu;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import fr.Maxime3399.Mitsu.utils.DataUtils;

public class MitsuAPI {
	
	public static String getServer(){
		return MainClass.getInstance().getConfig().getString("Srv1");
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
	
	public static void teleportPlayer(Player p, String server){
		
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
				
				p.sendMessage("§cUne erreur est survenue !");
				p.playSound(p.getLocation(), Sound.ARROW_HIT, 100, 1);
				
			}
			
		}else{
			
			p.sendMessage("§cUne erreur est survenue !");
			p.playSound(p.getLocation(), Sound.ARROW_HIT, 100, 1);
			
		}
		
	}

}
