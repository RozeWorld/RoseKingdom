package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandManager;
import com.rosekingdom.rosekingdom.Core.Config.Config;
import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Events.EventHandler;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Graves.Grave;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Tab.RankHandler;
import com.rosekingdom.rosekingdom.Tab.TabSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public final class RoseKingdom extends JavaPlugin {

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

//        AFKstatus.check(this);

        RankHandler.registerBaseRanks();

        getLogger().info("RoseKingdom Loaded!");
        if(!Bukkit.getOnlinePlayers().isEmpty()){
            for(Player player : Bukkit.getOnlinePlayers()){
                TabSystem.join(player);
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

        for(int npc : NPCHandler.getIds()){
            NPCHandler.removeNPC(npc);
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
