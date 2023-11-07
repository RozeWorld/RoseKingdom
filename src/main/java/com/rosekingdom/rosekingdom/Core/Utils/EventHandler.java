package com.rosekingdom.rosekingdom.Core.Utils;

import com.rosekingdom.rosekingdom.Core.Events.onChatEvent;
import com.rosekingdom.rosekingdom.Core.Events.onJoin;
import com.rosekingdom.rosekingdom.Core.Events.onLeave;
import com.rosekingdom.rosekingdom.Graves.onDead;
import com.rosekingdom.rosekingdom.Profiles.Events.DailyStreakChecker;
import com.rosekingdom.rosekingdom.Profiles.Events.GUIhandler;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class EventHandler {
    public static void events(Plugin plugin){
        getServer().getPluginManager().registerEvents(new onDead(), plugin);
        getServer().getPluginManager().registerEvents(new onJoin(), plugin);
        getServer().getPluginManager().registerEvents(new onLeave(), plugin);
        getServer().getPluginManager().registerEvents(new onChatEvent(), plugin);
        getServer().getPluginManager().registerEvents(new GUIhandler(), plugin);
        getServer().getPluginManager().registerEvents(new DailyStreakChecker(), plugin);
    }
}
