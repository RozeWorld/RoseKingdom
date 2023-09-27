package com.rosekingdom.rosekingdom.Core.Utils;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.rosekingdom.rosekingdom.Core.Utils.Events.onChatEvent;
import com.rosekingdom.rosekingdom.Core.Utils.Events.onJoin;
import com.rosekingdom.rosekingdom.Core.Utils.Events.onLeave;
import com.rosekingdom.rosekingdom.Graves.onDead;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class EventHandler {

    private static ProtocolManager protocolManager;

    public static void events(Plugin plugin){
        getServer().getPluginManager().registerEvents(new onDead(), plugin);
        getServer().getPluginManager().registerEvents(new onJoin(), plugin);
        getServer().getPluginManager().registerEvents(new onLeave(), plugin);
        getServer().getPluginManager().registerEvents(new onChatEvent(), plugin);
    }

    public static void setProtocolManager(){
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public static ProtocolManager getProtocol(){
        return protocolManager;
    }

}
