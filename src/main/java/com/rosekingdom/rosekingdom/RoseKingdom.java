package com.rosekingdom.rosekingdom;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandManager;
import com.rosekingdom.rosekingdom.Core.Config.Config;
import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Events.EventHandler;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Core.NPCs.Statements.NPCStatement;
import com.rosekingdom.rosekingdom.Core.gui.GUIManager;
import com.rosekingdom.rosekingdom.Graves.Grave;
import com.rosekingdom.rosekingdom.Graves.GraveHandler;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Tab.AFKstatus;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import com.rosekingdom.rosekingdom.Tab.Rank;
import com.rosekingdom.rosekingdom.Tab.RankHandler;
import com.rosekingdom.rosekingdom.Tab.Statements.KingdomStatement;
import com.rosekingdom.rosekingdom.Tab.Tab;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        AFKstatus.check(this);

        UserStatement.setRank("96022bb0-c25b-45da-8537-f323edbba03a", Rank.OWNER.name());

        RankHandler.registerBaseRanks();

        KingdomStatement.loadKingdoms();
        NPCStatement.loadNPCs();

        if(!Bukkit.getOnlinePlayers().isEmpty()){
            for(Player player : Bukkit.getOnlinePlayers()){
                Tab.join(player);
            }
        }
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

        KingdomHandler.saveKingdoms();

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
