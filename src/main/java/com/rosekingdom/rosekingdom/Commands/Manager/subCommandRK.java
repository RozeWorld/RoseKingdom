package com.rosekingdom.rosekingdom.Commands.Manager;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class subCommandRK {
    private final List<String> aliases = new ArrayList<>();
    private final List<subCommandRK> subCommands = new ArrayList<>();

    public int subCommandArg;
    public subCommandRK(int arg){
        subCommandArg = arg;
    }
    public void setName(String name){
        aliases.add(name);
    }
    public void addAlias(String alias){
        if(aliases.size()==0){
            throw new RuntimeException("Missing command name!");
        }
        aliases.add(alias);
    }
    public List<String> getAliases(){
        return aliases;
    }
    public String getName(){
        return aliases.get(0);
    }
    public boolean correctArg(int arg) {
        return subCommandArg == arg;
    }
    public List<subCommandRK> getSubCommands() {
        return subCommands;
    }
    public void addSubCommand(subCommandRK subCommand){
        subCommands.add(subCommand);
    }
    public boolean hasSubCommands(){
        return subCommands.size() > 0;
    }
    public abstract void executeSub(CommandSender sender, String[] args);
    public abstract List<String> tabComplete(CommandSender sender, String[] args);
}
