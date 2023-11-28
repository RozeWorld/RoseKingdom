package com.rosekingdom.rosekingdom.Core.Database;

import com.rosekingdom.rosekingdom.Core.Config.Config;
import com.rosekingdom.rosekingdom.Core.Utils.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static boolean fullurl;
    private static String host;
    private static String database;
    private static String username;
    private static String password;
    public static void readData(){
        fullurl = Config.getInstance().USE_FULLURL;
        host = Config.getInstance().HOST;
        database = Config.getInstance().DATABASE;
        username = Config.getInstance().USERNAME;
        password = Config.getInstance().PASSWORD;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection;
        if(fullurl){
            connection = DriverManager.getConnection(Config.getInstance().JDBC);
        }else {
            connection = DriverManager.getConnection("jdbc:mysql://" + username + ":" + password + "@" + host +"/"+ database);
        }
        return connection;
    }

    public static void createDatabaseTables(){
        try(Connection connection = getConnection();
        Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_user(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),name varchar(16),uuid varchar(64),rk_rank varchar(100), join_date timestamp,INDEX(rowid))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_grave(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),id int,graveId varchar(64),data longblob)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_profile(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid), id int, streak int, last_online long, streak_highscore int)");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_death(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),id int,graveId varchar(64),x double,y double,z double,dim varchar(100),yaw float,IA_uuid varchar(64),BD_uuid varchar(64),TBR int, INDEX(id,graveId,IA_uuid))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_location(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid), id int, name varchar(32), x int, y int, z int, dim varchar(100), public boolean, INDEX(id,public,name,x,y,z))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS rk_economy(rowid int NOT NULL AUTO_INCREMENT, PRIMARY KEY(rowid),  id int, coins int)");
        }catch (SQLException e){
            Message.Exception("Unable to initialize tables!" + e.getMessage());
        }
    }
}
