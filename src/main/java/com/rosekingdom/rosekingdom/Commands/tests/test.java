package com.rosekingdom.rosekingdom.Commands.tests;

import com.rosekingdom.rosekingdom.Commands.Manager.CommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class test extends CommandRK {
    public test(){
        this.setName("test");
        this.addSubCommand(new sub(0));
        this.addAlias("t");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        player.sendMessage(Component.text("Args.size:" + args.length));
        if(args.length >= 1){
            for(String a : args){
                player.sendMessage(Component.text("arg: " + a));
            }
        }

        player.sendMessage(Component.text("Tested"));
    }
}
