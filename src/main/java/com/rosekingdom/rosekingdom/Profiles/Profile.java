package com.rosekingdom.rosekingdom.Profiles;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.RoseKingdom;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Profile extends CommandRK {

    public Profile(){
        setName("profile");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }

        if(args.length == 1){
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
            if(target.hasPlayedBefore()){
                RoseKingdom.getGuiManager().openGUI(new UserGUI(target), player);
            }else{
                player.sendMessage(Message.Warning(target.getName() + " hasn't played before!"));
            }
            return;
        }
        RoseKingdom.getGuiManager().openGUI(new UserGUI(player), player);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 1){
            List<String> tabs = new ArrayList<>();
            for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                tabs.add(player.getName());
            }
            return tabs;
        }
        return null;
    }
}
