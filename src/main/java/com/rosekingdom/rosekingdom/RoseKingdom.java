package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandManager;
import com.rosekingdom.rosekingdom.Core.Config.Config;
import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Premissions.Teams;
import com.rosekingdom.rosekingdom.Core.Utils.EventHandler;
import com.rosekingdom.rosekingdom.Graves.Grave;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import org.bukkit.plugin.java.JavaPlugin;


public final class RoseKingdom extends JavaPlugin {
    public static int players=0;
    @Override
    public void onEnable() {
        EventHandler.setProtocolManager();
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
        for(Grave grave : Grave.getGraveList()){
            grave.save();
        }

        getLogger().info("Successful shutdown!");
    }

    private void loadGraves(){
        int total_graves_loaded = 0;

        getLogger().info("Loading graves...");
        for(int id : DeathStatement.getGraveOwners()){
            for(String graveId : DeathStatement.getGraves(id)){
                Grave grave = new Grave(id, graveId);
                grave.timer(DeathStatement.getTime(id, graveId));
                Grave.addGrave(grave);
                total_graves_loaded++;
            }
        }
        getLogger().info("Loaded " + total_graves_loaded + " graves!");
    }


}
