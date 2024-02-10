package com.rosekingdom.rosekingdom.Moderation.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
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
            if(!player.getGameMode().equals(GameMode.CREATIVE)){
                player.setAllowFlight(false);
            }
            player.setInvisible(false);
            player.setInvulnerable(false);
            player.setCanPickupItems(true);
            player.setSleepingIgnored(false);
            UserStatement.vanish(player, false);
        }else {
            player.setAllowFlight(true);
            player.setInvisible(true);
            player.setInvulnerable(true);
            player.setCanPickupItems(false);
            player.setSleepingIgnored(true);
            UserStatement.vanish(player, true);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
