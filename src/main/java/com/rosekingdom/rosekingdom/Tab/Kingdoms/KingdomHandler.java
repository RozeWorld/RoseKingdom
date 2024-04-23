package com.rosekingdom.rosekingdom.Tab.Kingdoms;

import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.entity.Player;

import java.util.*;

public class KingdomHandler {
    private static final List<Kingdom> kingdomList = new ArrayList<>();
    public static Map<Kingdom, String> invites = new HashMap<>();
    public static Map<Player, Kingdom> inChats = new HashMap<>();

    public static List<Kingdom> getKingdoms(){
        return kingdomList;
    }

    public static void addKingdom(Kingdom kingdom){
        kingdomList.add(kingdom);
    }

    public static void removeKingdom(Kingdom kingdom) {
        kingdomList.remove(kingdom);
        int order = 1;
        for(Kingdom k : getKingdoms()){
            k.deleteSeparator();
            k.deleteRanks();
            k.setKingdomNumber(order);
            k.createSeparator();
            k.createRanks();
            for(Player member : k.getMembers()){
                k.joinKingdom(member);
            }
            order++;
        }
    }

    public static void addKingdomChatter(Player player, Kingdom kingdom){
        inChats.put(player, kingdom);
    }

    public static void removeKingdomChatter(Player player){
        inChats.remove(player);
    }

    public static Set<Player> getKingdomChatters(){
        return inChats.keySet();
    }

    public static Kingdom getChatterKingdom(Player player){
        return inChats.get(player);
    }

    public static Collection<String> getInvites(){
        return invites.values();
    }

    public static void addInvite(Kingdom kingdom, String invite){
        invites.put(kingdom, invite);
    }

    public static void removeInvite(Kingdom kingdom, String invite){
        invites.remove(kingdom, invite);
    }

    public static void acceptInvite(String invite, Player player){
        if(isInKingdom(player)){
            player.sendMessage(Message.Warning("You cannot join this kingdom because you're already in a kingdom!"));
            return;
        }
        for(Kingdom kingdom : invites.keySet()){
            if(invites.get(kingdom).equals(invite)){
                kingdom.joinKingdom(player);
            }
        }
    }

    public static boolean isInKingdom(Player player){
        return getKingdom(player) != null;
    }

    public static Kingdom getKingdom(Player player){
        for(Kingdom kingdom : getKingdoms()) {
            for (Player members : kingdom.getMembers()) {
                if (members.getName().equals(player.getName())) {
                    return kingdom;
                }
            }
        }
        return null;
    }

    public static void lastOnline(Player player){
        Kingdom kingdom = getKingdom(player);
        if(kingdom == null) return;
        if(kingdom.getOnlinePlayers().size()<=1){
            kingdom.hideSeparator();
        }
    }
}
