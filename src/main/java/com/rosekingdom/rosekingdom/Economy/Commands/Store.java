package com.rosekingdom.rosekingdom.Economy.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Economy.Commands.SubCommands.createStore;
import com.rosekingdom.rosekingdom.Economy.Commands.SubCommands.deleteStore;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Store extends CommandRK {
    public Store(){
        setName("store");
        addSubCommand(new createStore(0));
        addSubCommand(new deleteStore(0));
        setArgumentRequirement(true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            tabs.add("create");
            tabs.add("delete");
        }
        if(args.length == 2 && args[0].equals("delete")){
            tabs.addAll(StoreStatement.getStores(player));
        }
        return tabs;
    }
}
