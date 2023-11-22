package com.rosekingdom.rosekingdom.Economy.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class remCoins extends subCommandRK {
    public remCoins(int arg) {
        super(arg);
        setName("remove");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(args.length == 2 && !UserStatement.exists(args[1])){
            sender.sendMessage(Component.text("No such player"));
            return;
        }
        if (args.length == 3) {
            try {
                Integer.parseInt(args[2]);
                EconomyStatement.remCoins(Bukkit.getOfflinePlayer(args[1]), Integer.parseInt(args[2]));
            } catch (Exception e) {
                sender.sendMessage(Component.text("Tupanar napishi chislo"));
            }
        }
    }
}