package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Commands.Manager.CommandManager;
import com.rosekingdom.rosekingdom.Database.Database;
import com.rosekingdom.rosekingdom.Events.EventHandler;
import com.rosekingdom.rosekingdom.Premissions.Teams;
import com.rosekingdom.rosekingdom.Utils.Grave;
import com.rosekingdom.rosekingdom.Database.Statements.GraveStatement;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class RoseKingdom extends JavaPlugin {
    public static int players=0;
    @Override
    public void onEnable() {
        getLogger().info("RoseKingdom Started Loading!");
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
        loadGraves();
        Teams.createTeams();

        getLogger().info("RoseKingdom Loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Started Shutting Down!");

        getLogger().info("Saving Grave times...");
        for(Grave grave : Grave.graveList){
            grave.save();
        }

        Database.disconnect();
        getLogger().info("Successful shutdown!");
    }

    private void loadGraves(){
        int total_graves_loaded = 0;

        getLogger().info("Loading grave timers...");
        for(int id : GraveStatement.getGraveOwners()){
            for(int grave_num : GraveStatement.getGraves(id)){
                Grave grave = new Grave(id, grave_num);
                grave.timer(GraveStatement.getTime(id, grave_num));
                Grave.addGrave(grave);
                total_graves_loaded++;
            }
        }
        getLogger().info("Loaded " + total_graves_loaded + " timers!");
    }


}
