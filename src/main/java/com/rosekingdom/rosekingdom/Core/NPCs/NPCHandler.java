package com.rosekingdom.rosekingdom.Core.NPCs;

import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCHandler {
    private static final Map<Integer, NPC> npcList = new HashMap<>();
    private static final Map<Location, NPC> locationList = new HashMap<>();

    public static void addNPC(NPC npc){
        npcList.put(npc.getId(), npc);
        locationList.put(npc.getLocation(), npc);
    }

    public static void removeNPC(int id){
        Message.Console("NPC No:" + id + " was removed!");
        locationList.remove(npcList.get(id).getLocation());
        npcList.get(id).despawn();
        npcList.remove(id);
    }

    public static List<Location> getNPCLocations(){
        return locationList.keySet().stream().toList();
    }
    public static List<Integer> getIds(){
        return npcList.keySet().stream().toList();
    }
    public static List<NPC> getNPCs(){
        return new ArrayList<>(npcList.values());
    }
    public static NPC getNPC(int id) {
        return npcList.get(id);
    }
}
