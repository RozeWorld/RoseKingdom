package com.rosekingdom.rosekingdom.Core.NPCs;

import com.rosekingdom.rosekingdom.Core.Utils.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCHandler {
    private static final Map<Integer, NPC> npcList = new HashMap<>();

    public static void addNPC(NPC npc){
        npcList.put(npc.getId(), npc);
    }

    public static void removeNPC(int id){
        Message.Console("NPC №" + id + " was removed!");
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
