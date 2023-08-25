package com.rosekingdom.rosekingdom.Database;

import com.rosekingdom.rosekingdom.Config.Config;
import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final boolean fullurl = Config.getInstance().USE_FULLURL;
    private static String host;
    private static String database;
    private static String username;
    private static String password;
    private static Connection connection;

    public static void connect() throws ClassNotFoundException, SQLException{
        if(!isConnected()){
            if(fullurl){
                connection = DriverManager.getConnection(Config.getInstance().JDBC);
                return;
            }
            host = Config.getInstance().HOST;
            database = Config.getInstance().DATABASE;
            username = Config.getInstance().USERNAME;
            password = Config.getInstance().PASSWORD;
            connection = DriverManager.getConnection("jdbc:mysql://" + username + ":" + password + "@" + host +"/"+ database);
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

    public static boolean isConnected(){
        return (connection != null);
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
