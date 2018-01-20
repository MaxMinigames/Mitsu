package fr.Maxime3399.Mitsu.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.Maxime3399.Mitsu.MainClass;

public class DataUtils {
	
	private static String database = MainClass.getInstance().getConfig().getString("Database");
	
	public static void registerPlugin(){
		
		try{
			MySQLUtils.state.executeUpdate("CREATE TABLE IF NOT EXISTS `"+database+"`.`mitsu_types` ( `type` VARCHAR(255) NOT NULL , PRIMARY KEY (`type`)) ENGINE = MyISAM;");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		try{
			MySQLUtils.state.executeUpdate("CREATE TABLE IF NOT EXISTS `"+database+"`.`mitsu_servers` ( `id` VARCHAR(255) NOT NULL , `status` VARCHAR(255) NOT NULL , `players` INT(255) NOT NULL , `maxPlayers` INT(255) NOT NULL , `type` VARCHAR(255) NOT NULL , `next` VARCHAR(255) NOT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM;");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static boolean serverExist(String server){
		
		boolean result = false;
		
		for(String servers : getServers()){
			
			if(servers.equalsIgnoreCase(server)){
				
				result = true;
				
			}
			
		}
		
		return result;
		
	}
	
	public static boolean typeExist(String type){
		
		boolean result = false;
		
		for(String types : getTypes()){
			
			if(types.equalsIgnoreCase(types)){
				
				result = true;
				
			}
			
		}
		
		return result;
		
	}
	
	public static ArrayList<String> getServers(){
		
	    ArrayList<String> list = new ArrayList<String>();
	    try {
	        Statement stmt = MySQLUtils.state;
	        ResultSet result = stmt.executeQuery("SELECT * FROM `mitsu_servers`");
	        while(result.next()){
	        	list.add(result.getString(1));
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
		
	}
	
	public static ArrayList<String> getTypes(){
		
	    ArrayList<String> list = new ArrayList<String>();
	    try {
	        Statement stmt = MySQLUtils.state;
	        ResultSet result = stmt.executeQuery("SELECT * FROM `mitsu_types`");
	        while(result.next()){
	        	list.add(result.getString(1));
	        }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
		
	}
	
	public static String getServerStringInfo(String server, String info){
		
		String result = null;
		
		try{
			ResultSet r = MySQLUtils.state.executeQuery("SELECT * FROM `"+database+"`.`mitsu_servers` WHERE id = '"+server+"'");
			r.next();
			result = r.getString(info);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static int getServerIntInfo(String server, String info){
		
		int result = 0;
		
		try{
			ResultSet r = MySQLUtils.state.executeQuery("SELECT * FROM `"+database+"`.`mitsu_servers` WHERE id = '"+server+"'");
			r.next();
			result = r.getInt(info);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static void setServerStringInfo(String server, String info, String value){
		
		try{
			MySQLUtils.state.executeUpdate("UPDATE `"+database+"`.`mitsu_servers` SET `info-"+info+"` = '"+value+"' WHERE `mitsu_servers`.`id` = '"+server+"';");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static void setServerIntInfo(String server, String info, int value){
		
		try{
			MySQLUtils.state.executeUpdate("UPDATE `"+database+"`.`mitsu_servers` SET `info-"+info+"` = '"+value+"' WHERE `mitsu_servers`.`id` = '"+server+"';");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
