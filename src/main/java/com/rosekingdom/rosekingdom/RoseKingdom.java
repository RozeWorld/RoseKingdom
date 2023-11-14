package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandManager;
import com.rosekingdom.rosekingdom.Core.Config.Config;
import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Utils.EventHandler;
import com.rosekingdom.rosekingdom.Graves.Grave;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Ranks.AFKstatus;
import com.rosekingdom.rosekingdom.Ranks.RankSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


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

        RankSystem.registerAllRanks();
        AFKstatus.check(this);

        getLogger().info("RoseKingdom Loaded!");
        if(!Bukkit.getOnlinePlayers().isEmpty()){
            for(Player player : Bukkit.getOnlinePlayers()){
                RankSystem.loadRank(player);
            }
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Started Shutting Down!");

        getLogger().info("Saving Graves...");
        if(!Grave.getGraveList().isEmpty()){
            for(Grave grave : Grave.getGraveList()){
                grave.save();
            }
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
