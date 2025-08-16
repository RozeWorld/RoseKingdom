package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandManager;
import com.rosekingdom.rosekingdom.Core.Config.Config;
import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Events.EventHandler;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Core.NPCs.Statements.NPCStatement;
import com.rosekingdom.rosekingdom.Core.gui.GUIManager;
import com.rosekingdom.rosekingdom.Core.gui.InventoryLocker;
import com.rosekingdom.rosekingdom.Graves.Grave;
import com.rosekingdom.rosekingdom.Graves.GraveHandler;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;


public final class RoseKingdom extends JavaPlugin {
    static GUIManager guiManager;

    @Override
    public void onEnable() {
        getLogger().info("RoseKingdom Started Loading!");
        guiManager = new GUIManager();
        new CommandManager(this);
        new Config();

        Database.readData();
        Database.createDatabaseTables();
        getLogger().info("Database is connected!");
        loadGraves();

        EventHandler.events(this);

        NPCStatement.loadNPCs();

        getLogger().info("RoseKingdom Loaded!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Started Shutting Down!");

        getLogger().info("Saving Graves...");
        if(!GraveHandler.getGraveList().isEmpty()){
            for(Grave grave : GraveHandler.getGraveList()){
                grave.save();
            }
        }

        for(int npc : NPCHandler.getIds()){
            NPCHandler.removeNPC(npc);
        }

        for(Player player : Bukkit.getOnlinePlayers()){
            InventoryLocker.restore(player);
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
                GraveHandler.addGrave(grave);
                total_graves_loaded++;
            }
        }
        getLogger().info("Loaded " + total_graves_loaded + " graves!");
    }

    public static GUIManager getGuiManager() {
        return guiManager;
    }
}
