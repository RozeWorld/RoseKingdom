package com.rosekingdom.rosekingdom.Economy.Commands.SubCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.Items.Coin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveCoins extends subCommandRK {
    public giveCoins(int arg) {
        super(arg);
        setName("give");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("rk.MOD")){
            if(args.length == 2){
                try {
                    player.getInventory().addItem(new Coin(Integer.parseInt(args[1])));
                }catch (NumberFormatException e){
                    player.sendMessage(Message.Warning("Not a number!"));
                }
            }else{
                player.sendMessage(Message.Warning("Missing arguments!"));
            }
        }
    }
}
