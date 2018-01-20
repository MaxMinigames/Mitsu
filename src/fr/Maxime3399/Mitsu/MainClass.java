package fr.Maxime3399.Mitsu;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.Maxime3399.Mitsu.schedulers.SchedulersManager;
import fr.Maxime3399.Mitsu.utils.DataUtils;
import fr.Maxime3399.Mitsu.utils.MySQLUtils;

public class MainClass extends JavaPlugin{
	
	private static Plugin plugin;
	
	public void onEnable(){
		
		plugin = this;
		
		File f = new File(getDataFolder(), "config.yml");
		if(!f.exists()){
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
			Bukkit.getConsoleSender().sendMessage("§eMitsu §d: §aThe configuration file has been created ! You have to configure the plugin with the file \"config.yml\".");
		}
		MySQLUtils.connect();
		
		if(MySQLUtils.state == null){
			
			Bukkit.getConsoleSender().sendMessage("§eMitsu §d: §cAn error occurred while connecting to the database ! Please check the \"config.yml\" file.");
			Bukkit.getPluginManager().disablePlugin(this);
			
		}else{
			
			DataUtils.registerPlugin();
			if(!DataUtils.serverExist(getConfig().getString("Server"))){
				
				Bukkit.getConsoleSender().sendMessage("§eMitsu §d: §cThis server was not found in the database ! Please add it or modify the file \"config.yml\".");
				Bukkit.getPluginManager().disablePlugin(this);
				
			}else{
				
				if(!DataUtils.typeExist(getConfig().getString("Type"))){
					
					Bukkit.getConsoleSender().sendMessage("§eMitsu §d: §cThe type of this server is invalid ! Please add it to the database or modify the file \"config.yml\".");
					Bukkit.getPluginManager().disablePlugin(this);
					
				}else{
					
					if(!DataUtils.serverExist(DataUtils.getServerStringInfo(getConfig().getString("Server"), "next"))){
						
						Bukkit.getConsoleSender().sendMessage("§eMitsu §d: §cThe following server for this server is invalid ! Please modify it in the database.");
						Bukkit.getPluginManager().disablePlugin(this);
						
					}else{
						
						if(!DataUtils.serverExist(DataUtils.getServerConnect())){
							
							Bukkit.getConsoleSender().sendMessage("§eMitsu §d: §cThe general following server is invalid ! Please modify it in the database.");
							Bukkit.getPluginManager().disablePlugin(this);
							
						}else{
							
							if(getConfig().getString("Directory").equalsIgnoreCase("")){
								
								Bukkit.getConsoleSender().sendMessage("§eMitsu §d: §cPlease fill in the directory field in the \"config.yml\" file.");
								Bukkit.getPluginManager().disablePlugin(this);
								
							}else{
								
								String server = getConfig().getString("Server");
								
								DataUtils.setServerIntInfo(server, "players", 0);
								DataUtils.setServerStringInfo(server, "type", getConfig().getString("Type"));
								SchedulersManager.registerSchedulers();
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public void onDisable(){
		
		DataUtils.setServerStringInfo(getConfig().getString("Server"), "status", "offline");
		
	}
	
	public static Plugin getInstance(){
		return plugin;
	}

}
