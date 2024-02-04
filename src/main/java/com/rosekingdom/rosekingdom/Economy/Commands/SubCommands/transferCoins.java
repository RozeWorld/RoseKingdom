package com.rosekingdom.rosekingdom.Economy.Commands.SubCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.Statements.EconomyStatement;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class transferCoins extends subCommandRK{
        public transferCoins(int arg){
            super(arg);
            setName("transfer");
        }
    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }
        if (args.length == 2 && !UserStatement.exists(args[1])) {
            player.sendMessage(Message.Warning("No such player"));
            return;
        }
        if (args.length == 3) {
            try {
                Integer.parseInt(args[2]);
                EconomyStatement.removeCoins(player, Integer.parseInt(args[2]));
                EconomyStatement.addCoins(Bukkit.getOfflinePlayer(args[1]), Integer.parseInt(args[2]));
            } catch (NumberFormatException e) {
                player.sendMessage(Message.Warning("Incorrect type! The required type is number"));
            }
        }
    }
}
