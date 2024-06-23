package com.rosekingdom.rosekingdom.Moderation.Utils;

import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class vanish {
    public static List<Player> vanished = new ArrayList<>();

    public static void addVanished(Player player){
        vanished.add(player);
    }
    public static void removeVanished(Player player){
        vanished.remove(player);
    }
    public static List<Player> getVanished(){
        return vanished;
    }

    public static void changePlayerVisibility(Player player, boolean state){
        for(Player online : Bukkit.getOnlinePlayers()){
            if(state){
                player.setAllowFlight(true);
                online.hidePlayer(RoseKingdom.getPlugin(RoseKingdom.class), player);
            }else {
                player.setAllowFlight(player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR));
                online.showPlayer(RoseKingdom.getPlugin(RoseKingdom.class), player);
            }
        }
        player.setInvulnerable(state);
        player.setCanPickupItems(!state);
        player.setSleepingIgnored(state);
    }
}
