package com.rosekingdom.rosekingdom.Database;

import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    //TODO get credentials from config
    private static String host = "localhost";
    private static String port = "3306";
    private static String database = "test";
    private static String username = "root";
    private static String password = "asdasd12";

    private static Connection connection;

    public static boolean isConnected(){
        return (connection != null);
    }
    public static void connect() throws ClassNotFoundException, SQLException{
        if(!isConnected()){
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        }
    }

    public static void disconnect(){
        if(isConnected()){
            try{
                connection.close();
            }catch (SQLException e){

                e.printStackTrace();
            }
            JavaPlugin.getPlugin(RoseKingdom.class).getLogger().info("Database is disconnected!");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void createDatabaseTables(){
        try {
            if(isConnected()){
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_user(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),name varchar(16),uuid varchar(64),rk_rank varchar(100))");
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
