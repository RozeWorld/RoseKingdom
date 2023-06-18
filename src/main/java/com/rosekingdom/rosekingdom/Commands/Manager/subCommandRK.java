package com.rosekingdom.rosekingdom.Commands.Manager;

import org.bukkit.command.CommandSender;

import java.util.HashMap;

public abstract class subCommandRK {
    public HashMap<Integer, String> aliases;
    public HashMap<Integer, subCommandRK> subCommands;

    public void setName(String name){
        aliases.put(1, name);
    }
    public void addAlias(String alias){
        if(aliases.size()==0){
            throw new RuntimeException("Missing command name!");
        }
        aliases.put(aliases.size()+1, alias);
    }
    public void addSubCommand(subCommandRK subCommand){
        subCommands.put(subCommands.size()+1, subCommand);
    }

    public abstract void execute(CommandSender sender, String[] args);
}
