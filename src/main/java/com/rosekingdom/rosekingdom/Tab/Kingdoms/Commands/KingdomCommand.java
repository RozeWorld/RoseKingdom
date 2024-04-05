package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class KingdomCommand extends CommandRK {

    public KingdomCommand(){
        setName("kingdom");
        addAlias("kd");
        addSubCommand(new createKingdom(0));
        addSubCommand(new joinKingdom(0));
        addSubCommand(new removeKingdom(0));
        addSubCommand(new leaveKingdom(0));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            tabs.add("join");
            tabs.add("create");
            tabs.add("delete");
            tabs.add("leave");
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("delete")){
            for(Kingdom kingdom : KingdomHandler.getKingdoms()){
                tabs.add(kingdom.getName());
            }
        }
        return tabs;
    }
}
