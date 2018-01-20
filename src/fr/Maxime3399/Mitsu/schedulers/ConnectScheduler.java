package fr.Maxime3399.Mitsu.schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;

import fr.Maxime3399.Mitsu.MainClass;
import fr.Maxime3399.Mitsu.utils.DataUtils;
import fr.Maxime3399.Mitsu.utils.FoldersUtils;

public class ConnectScheduler {
	
	public static void startScheduler(){
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(MainClass.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				
				String server = MainClass.getInstance().getConfig().getString("Server");
				String next = DataUtils.getServerConnect();
				
				if(server.equals(next)){
					
					ArrayList<String> types = DataUtils.getTypes();
					HashMap<String, ArrayList<String>> list = new HashMap<>();
					String newType = null;
					
					for(String s : types){
						ArrayList<String> srvs = new ArrayList<>();
						list.put(s, srvs);
					}
					
					for(String s : DataUtils.getServers()){

						if(DataUtils.getServerStringInfo(s, "status").equalsIgnoreCase("join")){

							int limit = DataUtils.getServerIntInfo(s, "maxPlayers");
							if(limit >= 5){
								limit = limit-2;
							}
							if(DataUtils.getServerIntInfo(s, "players") < limit){

								ArrayList<String> al = list.get(DataUtils.getServerStringInfo(s, "type"));
								al.add(s);
								list.put(DataUtils.getServerStringInfo(s, "type"), al);
							}
						}
					}
					
					for(String ls : list.keySet()){
						
						if(list.get(ls).size() < 1){
							
							newType = ls;
							
						}
						
					}
					
					if(list.get(MainClass.getInstance().getConfig().getString("Type")).size() == 1){
						
						newType = MainClass.getInstance().getConfig().getString("Type");
						
					}
					
					if(newType != null){

						if(!newType.equals(MainClass.getInstance().getConfig().getString("Type"))){
							
							if(DataUtils.typeExist(newType)){
								
								if(FoldersUtils.typeExist(newType)){
									
									if(Bukkit.getOnlinePlayers().size() < 1){
										
										try {
											Runtime.getRuntime().exec(MainClass.getInstance().getConfig().getString("Directory")+"\\"+newType+"\\"+MainClass.getInstance().getConfig().getString("File"));
										} catch (IOException e) {
											e.printStackTrace();
										}
										Bukkit.getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new Runnable() {
											
											@Override
											public void run() {
												
												Bukkit.getServer().shutdown();
												
											}
											
										}, 30);
										
									}
									
								}
								
							}
							
						}
						
					}
					
					DataUtils.setServerConnect(DataUtils.getServerStringInfo(server, "next"));
					
				}
				
			}
			
		}, 100, 100);
		
	}

}
