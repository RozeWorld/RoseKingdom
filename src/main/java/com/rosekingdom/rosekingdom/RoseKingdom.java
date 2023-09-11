package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Commands.Manager.CommandManager;
import com.rosekingdom.rosekingdom.Config.Config;
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

        new Config();

        Database.readData();
        Database.createDatabaseTables();
        getLogger().info("Database is connected!");
        loadGraves();

        EventHandler.events(this);

        Teams.createTeams();

        getLogger().info("RoseKingdom Loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Started Shutting Down!");

        getLogger().info("Saving Graves...");
        for(Grave grave : Grave.graveList){
            grave.save();
        }

        getLogger().info("Successful shutdown!");
    }

    private void loadGraves(){
        int total_graves_loaded = 0;

        getLogger().info("Loading graves...");
        for(int id : GraveStatement.getGraveOwners()){
            for(String graveId : GraveStatement.getGraves(id)){
                Grave grave = new Grave(id, graveId);
                grave.timer(GraveStatement.getTime(id, graveId));
                Grave.addGrave(grave);
                total_graves_loaded++;
            }
        }
        getLogger().info("Loaded " + total_graves_loaded + " graves!");
    }


}
