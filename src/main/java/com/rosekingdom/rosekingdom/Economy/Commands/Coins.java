package com.rosekingdom.rosekingdom.Economy.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Coins extends CommandRK {
    public Coins(){
        setName("Coins");
        addSubCommand(new addCoins(0));
        addSubCommand(new remCoins(0));
        addSubCommand(new transCoins(0));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }
        if (args.length == 0) {
            player.sendMessage(Component.text(EconomyStatement.getCoins(player)));
        }
        if(args.length == 1) {
            if(!getSubCommands().contains(args[0])){
                player.sendMessage(Component.text("No such subcommand"));
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> sgt = new ArrayList<>();
        if(args.length == 1){
            sgt.add("add");
            sgt.add("remove");
            sgt.add("transfer");
            return sgt;
        }
        if(args.length == 2){
            for(Player player : Bukkit.getOnlinePlayers()){
                sgt.add(player.getName());
            }
            return sgt;
        }
        return null;
    }
}
