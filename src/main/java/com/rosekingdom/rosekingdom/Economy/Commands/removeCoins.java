package com.rosekingdom.rosekingdom.Economy.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class removeCoins extends subCommandRK {
    public removeCoins(int arg) {
        super(arg);
        setName("remove");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!sender.hasPermission("rk.MOD")){
            sender.sendMessage(Message.Warning("You don't have permissions to perform this command."));
            return;
        }
        if(args.length == 2){
            if(!UserStatement.exists(args[1])){
                sender.sendMessage(Component.text("No such player"));
                return;
            }else{
                sender.sendMessage(Component.text("Missing amount of coins!"));
            }
        }
        if (args.length == 3) {
            try {
                Integer.parseInt(args[2]);
                EconomyStatement.remCoins(Bukkit.getOfflinePlayer(args[1]), Integer.parseInt(args[2]));
            } catch (Exception e) {
                sender.sendMessage(Component.text("Invalid variable!"));
            }
        }
    }
}