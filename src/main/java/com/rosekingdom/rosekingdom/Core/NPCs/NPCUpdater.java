package com.rosekingdom.rosekingdom.Core.NPCs;

import io.papermc.paper.event.packet.PlayerChunkLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCUpdater implements Listener {

    @EventHandler
    public void onChunkUpdate(PlayerChunkLoadEvent e){
        for(NPC npc : NPCHandler.getNPCs()){
            if(npc.getLocation().getChunk().equals(e.getChunk()) && !npc.isShown()){
                npc.spawn();
            }
        }
    }
}
