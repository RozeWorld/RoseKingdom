package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class kingdom_main_command extends CommandRK {

    public kingdom_main_command(){
        setName("kingdom");
        addAlias("kd");
        addSubCommand(new kingdom_create(0));
        addSubCommand(new kingdom_join(0));
        addSubCommand(new kingdom_remove(0));
        addSubCommand(new kingdom_leave(0));
        addSubCommand(new kingdom_invite(0));
        addSubCommand(new kingdom_chat(0));
        addSubCommand(new kingdom_publicity(0));
        addSubCommand(new kingdom_rename(0));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        if(args.length == 0){
            player.sendMessage(Component.text("Kingdom Commands:", TextColor.fromHexString("#ffb114")));
            player.sendMessage(Component.text("/kingdom create <name>", TextColor.fromHexString("#FFF522")));
            player.sendMessage(Component.text("/kingdom rename <name>", TextColor.fromHexString("#FFF522")));
            player.sendMessage(Component.text("/kingdom delete <kingdom>", TextColor.fromHexString("#FFF522")));
            player.sendMessage(Component.text("/kingdom invite <player>", TextColor.fromHexString("#FFF522")));
            player.sendMessage(Component.text("/kingdom public <true/false>", TextColor.fromHexString("#FFF522")));
            player.sendMessage(Component.text("/kingdom join <kingdom>", TextColor.fromHexString("#FFF522")));
            player.sendMessage(Component.text("/kingdom leave", TextColor.fromHexString("#FFF522")));
            player.sendMessage(Component.text("/kingdom chat", TextColor.fromHexString("#FFF522")));

        }
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
            tabs.add("public");
            tabs.add("rename");
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
                case "public" -> {
                    tabs.add("true");
                    tabs.add("false");
                }
            }
        }
        return tabs;
    }
}
