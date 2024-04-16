package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class KingdomCommand extends CommandRK {

    public KingdomCommand(){
        setName("kingdom");
        addAlias("kd");
        addSubCommand(new createKingdom(0));
        addSubCommand(new joinKingdom(0));
        addSubCommand(new removeKingdom(0));
        addSubCommand(new leaveKingdom(0));
        addSubCommand(new KingdomInvite(0));
        addSubCommand(new KingdomChat(0));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        if(args.length == 1 && KingdomHandler.getInvites().contains(args[0])){
            KingdomHandler.acceptInvite(args[0], player);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return null;
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            tabs.add("join");
            tabs.add("create");
            tabs.add("delete");
            tabs.add("leave");
            tabs.add("invite");
            tabs.add("chat");
        }
        if(args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "delete" -> {
                    for (Kingdom kingdom : KingdomHandler.getKingdoms()) {
                        if(kingdom.getOwner().equals(player.getUniqueId())){
                            tabs.add(kingdom.getName());
                        }
                    }
                }
                case "join" -> {
                    for (Kingdom kingdom : KingdomHandler.getKingdoms()) {
                        if(kingdom.isPublic()){
                            tabs.add(kingdom.getName());
                        }
                    }
                }
                case "invite" -> {
                    for(Player online : Bukkit.getOnlinePlayers()){
                        tabs.add(online.getName());
                    }
                }
            }
        }
        return tabs;
    }
}
