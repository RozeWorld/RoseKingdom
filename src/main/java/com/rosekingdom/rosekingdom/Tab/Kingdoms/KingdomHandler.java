package com.rosekingdom.rosekingdom.Tab.Kingdoms;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KingdomHandler {
    private static final List<Kingdom> kingdomList = new ArrayList<>();

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
                k.removeMember(member);
                k.joinKingdom(member);
            }
            order++;
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
