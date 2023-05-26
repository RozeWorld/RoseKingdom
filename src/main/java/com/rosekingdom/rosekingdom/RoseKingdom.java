package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Database.Database;
import com.rosekingdom.rosekingdom.Events.onDead;
import com.rosekingdom.rosekingdom.Events.onJoin;
import com.rosekingdom.rosekingdom.Events.onLeave;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class RoseKingdom extends JavaPlugin {
    public static int players=0;
    public Database sql;
    @Override
    public void onEnable() {
        getLogger().info("RoseKindgom Started Loading!");

        this.sql = new Database();
        try {
            sql.connect();
        }catch (ClassNotFoundException | SQLException e){
            getLogger().info("Database connection unsuccessful!");
        }

        if(sql.isConnected()){
            getLogger().info("Database is connected!");
        }

        sql.createDatabaseTables();

        getServer().getPluginManager().registerEvents(new onDead(), this);
        getServer().getPluginManager().registerEvents(new onLeave(), this);
        getServer().getPluginManager().registerEvents(new onJoin(this), this);
        getLogger().info("RoseKindgom Loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Started Shutting Down!");
        sql.disconnect();
        getLogger().info("Successful shutdown!");
    }

}
