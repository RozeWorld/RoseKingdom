package com.rosekingdom.rosekingdom.Commands.tests;

import com.rosekingdom.rosekingdom.Commands.Manager.CommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> tabs = new ArrayList<>();
        switch (args.length){
            case 1 -> {
                tabs.add("sub");
                tabs.add("s");
            }
            case 2 -> tabs.add("subsub");
            case 3 -> tabs.add("megasub");
        }
        return tabs;
    }
}
