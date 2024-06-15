package com.rosekingdom.rosekingdom.Moderation.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Vanish extends CommandRK {

    public Vanish(){
        setName("vanish");
        addAlias("vh");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        if(UserStatement.isVanished(player)){
            player.setAllowFlight(player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR));
            vanish(player, false);
            UserStatement.vanish(player, false);
        }else {
            player.setAllowFlight(true);
            vanish(player, true);
            UserStatement.vanish(player, true);
        }
    }

    private void vanish(Player player, boolean state){
        for(Player online : Bukkit.getOnlinePlayers()){
            if(state){
                online.hidePlayer(RoseKingdom.getPlugin(RoseKingdom.class), player);
            }else {
                online.showPlayer(RoseKingdom.getPlugin(RoseKingdom.class), player);
            }
        }
        player.setInvulnerable(state);
        player.setCanPickupItems(!state);
        player.setSleepingIgnored(state);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
