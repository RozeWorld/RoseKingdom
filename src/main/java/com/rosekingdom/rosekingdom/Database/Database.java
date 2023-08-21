package com.rosekingdom.rosekingdom.Database;

import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    //TODO get credentials from config
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String database = "test";
    private static final String username = "root";
    private static final String password = "asdasd12";

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
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_user(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),name varchar(16),uuid varchar(64),rk_rank varchar(100),INDEX(rowid))");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_grave(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),id int,graveId varchar(64),data longblob)");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_death(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),id int,graveId varchar(64),x double,y double,z double,dim varchar(100),IA_uuid varchar(64),BD_uuid varchar(64),TBR int, INDEX(id,graveId,IA_uuid))");


                statement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
