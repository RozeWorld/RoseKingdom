package com.rosekingdom.rosekingdom.Economy.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Economy.Commands.SubCommands.addCoins;
import com.rosekingdom.rosekingdom.Economy.Commands.SubCommands.giveCoins;
import com.rosekingdom.rosekingdom.Economy.Commands.SubCommands.removeCoins;
import com.rosekingdom.rosekingdom.Economy.Commands.SubCommands.transferCoins;
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
        addAlias("Wallet");
        addAlias("Money");
        addSubCommand(new addCoins(0));
        addSubCommand(new removeCoins(0));
        addSubCommand(new transferCoins(0));
        addSubCommand(new giveCoins(0));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }
        if (args.length == 0) {
            player.sendMessage(Component.text(EconomyStatement.getCoins(player)));
        }
        if(args.length >= 1 && !getSubAliases(0).contains(args[0])){
            player.sendMessage(Component.text("Unknown subcommand"));
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> sgt = new ArrayList<>();
        if(args.length == 1){
            if(sender.hasPermission("rk.MOD")){
                sgt.add("add");
                sgt.add("remove");
                sgt.add("give");
            }
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
