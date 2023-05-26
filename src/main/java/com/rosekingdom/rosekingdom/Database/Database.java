package com.rosekingdom.rosekingdom.Database;

import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    //TODO get credentials from config
    private String host = "localhost";
    private String port = "3306";
    private String database = "test";
    private String username = "root";
    private String password = "asdasd";

    private Connection connection;

    public boolean isConnected(){
        return (connection != null);
    }
    public void connect() throws ClassNotFoundException, SQLException{
        if(!isConnected()){
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
        }
    }

    public void disconnect(){
        if(isConnected()){
            try{
                connection.close();
            }catch (SQLException e){

                e.printStackTrace();
            }
            JavaPlugin.getPlugin(RoseKingdom.class).getLogger().info("Database is disconnected!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void createDatabaseTables(){
        try {
            if(isConnected()){
                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS user(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),name varchar(16),uuid varchar(64))");
                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
