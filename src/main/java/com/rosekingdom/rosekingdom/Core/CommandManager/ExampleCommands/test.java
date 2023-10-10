package com.rosekingdom.rosekingdom.Core.CommandManager.ExampleCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class test extends CommandRK {
    public test(){
        setName("test");
        addAlias("t");
        addSubCommand(new sub(0));
        addSubCommand(new subsub(1));
        addSubCommand(new megasub(2));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        player.sendMessage(Component.text("Args.size:" + args.length, TextColor.fromHexString("#e80c25")));
        if(args.length >= 1){
            for(String a : args){
                player.sendMessage(Component.text("arg: " + a , TextColor.fromHexString("#e80c25")));
            }
        }
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
