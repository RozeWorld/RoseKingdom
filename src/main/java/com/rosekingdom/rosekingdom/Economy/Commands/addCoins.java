package com.rosekingdom.rosekingdom.Economy.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class addCoins extends subCommandRK {
    public addCoins(int arg){
        super(arg);
        setName("add");
    }
    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        EconomyStatement.addCoins(player, Integer.parseInt(args[1]));
    }
}