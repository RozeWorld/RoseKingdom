package com.rosekingdom.rosekingdom.Core.Events;

import com.rosekingdom.rosekingdom.Core.NPCs.NPCInteractions;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCUpdater;
import com.rosekingdom.rosekingdom.Economy.Events.GUI.sOwnerPanel;
import com.rosekingdom.rosekingdom.Economy.Events.GUI.sPlayerPanel;
import com.rosekingdom.rosekingdom.Graves.GraveEvents;
import com.rosekingdom.rosekingdom.Moderation.Events.BanEvent;
import com.rosekingdom.rosekingdom.Moderation.Events.KickEvent;
import com.rosekingdom.rosekingdom.Moderation.Events.vanishEvents;
import com.rosekingdom.rosekingdom.Profiles.Events.GUI.eProfile;
import com.rosekingdom.rosekingdom.Tab.AFKstatus;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class EventHandler {
    public static void events(Plugin plugin){
        getServer().getPluginManager().registerEvents(new GraveEvents(), plugin);
        getServer().getPluginManager().registerEvents(new onJoin(), plugin);
        getServer().getPluginManager().registerEvents(new onLeave(), plugin);
        getServer().getPluginManager().registerEvents(new onChatEvent(), plugin);
        getServer().getPluginManager().registerEvents(new eProfile(), plugin);
        getServer().getPluginManager().registerEvents(new AFKstatus(), plugin);

        //Store events
        getServer().getPluginManager().registerEvents(new sPlayerPanel(), plugin);
        getServer().getPluginManager().registerEvents(new sOwnerPanel(), plugin);

        //Moderation
        getServer().getPluginManager().registerEvents(new BanEvent(), plugin);
        getServer().getPluginManager().registerEvents(new KickEvent(), plugin);
        getServer().getPluginManager().registerEvents(new vanishEvents(), plugin);

        //Testing
        getServer().getPluginManager().registerEvents(new NPCInteractions(), plugin);
        getServer().getPluginManager().registerEvents(new NPCUpdater(), plugin);
    }
}
