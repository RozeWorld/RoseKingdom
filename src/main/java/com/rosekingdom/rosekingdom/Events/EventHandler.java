package com.rosekingdom.rosekingdom.Events;

import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class EventHandler {
    public static void events(Plugin plugin){
        getServer().getPluginManager().registerEvents(new onDead(), plugin);
        getServer().getPluginManager().registerEvents(new onJoin(), plugin);
        getServer().getPluginManager().registerEvents(new onLeave(), plugin);
        getServer().getPluginManager().registerEvents(new onRightClick(), plugin);
        getServer().getPluginManager().registerEvents(new onRespawn_test(), plugin);
        getServer().getPluginManager().registerEvents(new onInventoryMoveItem(), plugin);
    }

}
