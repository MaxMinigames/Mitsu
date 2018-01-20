package fr.Maxime3399.Mitsu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import fr.Maxime3399.Mitsu.MainClass;

public class MySQLUtils {
	
    private static String url = "jdbc:mysql://"+MainClass.getInstance().getConfig().getString("Adress")+":"+MainClass.getInstance().getConfig().getInt("Port")+"/"+MainClass.getInstance().getConfig().getString("Database")+"?useSSL="+MainClass.getInstance().getConfig().getString("UseSSL")+"";
    private static String user = MainClass.getInstance().getConfig().getString("User");
    private static String passwd = MainClass.getInstance().getConfig().getString("Password");
    
    public static Statement state;
	
    public static void connect(){

        try {
        	
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException e1) {
        	
            e1.printStackTrace();

        }

        try {

			Connection conn = DriverManager.getConnection(url, user, passwd);
            state = conn.createStatement();
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }

    }

}
