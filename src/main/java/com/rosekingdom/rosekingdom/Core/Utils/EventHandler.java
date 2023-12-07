package com.rosekingdom.rosekingdom.Core.Utils;

import com.rosekingdom.rosekingdom.Core.Events.onChatEvent;
import com.rosekingdom.rosekingdom.Core.Events.onJoin;
import com.rosekingdom.rosekingdom.Core.Events.onLeave;
import com.rosekingdom.rosekingdom.Economy.Events.TestHandler;
import com.rosekingdom.rosekingdom.Graves.GraveEvents;
import com.rosekingdom.rosekingdom.Profiles.Events.GUIhandler;
import com.rosekingdom.rosekingdom.Ranks.AFKstatus;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class EventHandler {
    public static void events(Plugin plugin){
        getServer().getPluginManager().registerEvents(new GraveEvents(), plugin);
        getServer().getPluginManager().registerEvents(new onJoin(), plugin);
        getServer().getPluginManager().registerEvents(new onLeave(), plugin);
        getServer().getPluginManager().registerEvents(new onChatEvent(), plugin);
        getServer().getPluginManager().registerEvents(new GUIhandler(), plugin);
        getServer().getPluginManager().registerEvents(new TestHandler(), plugin);
        getServer().getPluginManager().registerEvents(new AFKstatus(), plugin);
    }
}
