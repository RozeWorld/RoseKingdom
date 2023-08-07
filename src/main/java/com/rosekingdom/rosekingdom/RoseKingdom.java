package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Commands.Manager.CommandManager;
import com.rosekingdom.rosekingdom.Database.Database;
import com.rosekingdom.rosekingdom.Events.EventHandler;
import com.rosekingdom.rosekingdom.Premissions.Teams;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class RoseKingdom extends JavaPlugin {
    public static int players=0;
    @Override
    public void onEnable() {
        getLogger().info("RoseKindgom Started Loading!");
        new CommandManager(this);


        try {
            Database.connect();
        }catch (ClassNotFoundException | SQLException e){
            getLogger().info("Database connection unsuccessful!");
        }

        if(Database.isConnected()){
            getLogger().info("Database is connected!");
        }

        Database.createDatabaseTables();

        EventHandler.events(this);

        Teams.createTeams();

        getLogger().info("RoseKindgom Loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Started Shutting Down!");
        Database.disconnect();
        getLogger().info("Successful shutdown!");
    }
}
