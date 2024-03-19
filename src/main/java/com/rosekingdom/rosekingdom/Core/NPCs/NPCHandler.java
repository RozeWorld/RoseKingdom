package com.rosekingdom.rosekingdom.Core.NPCs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCHandler {
    private static Map<Integer, NPC> npcList = new HashMap<>();

    public static void addNPC(NPC npc){
        npcList.put(npc.getId(), npc);
    }

    public static void removeNPC(int id){
        npcList.get(id).despawn();
        npcList.remove(id);
    }

    public static List<Integer> getIds(){
        return npcList.keySet().stream().toList();
    }

    public static List<NPC> getNPCs(){
        return new ArrayList<>(npcList.values());
    }

}
