package com.rosekingdom.rosekingdom.Core.CommandManager;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class CommandRK {
    private final List<String> aliases = new ArrayList<>();
    private final List<subCommandRK> subCommands = new ArrayList<>();
    private boolean requiredArguments = false;
    public void setName(String name) {
        aliases.add(name);
    }
    public List<subCommandRK> getSubCommands() {
        return subCommands;
    }

    public void addAlias(String alias) {
        if (aliases.isEmpty()) {
            throw new RuntimeException("Missing command name!");
        }
        aliases.add(alias);
    }

    public List<String> getAliases() {
        ListIterator<String> iterator = aliases.listIterator();
        while (iterator.hasNext()){
            iterator.set(iterator.next().toLowerCase());
        }
        return aliases;
    }

    public String getName() {
        return aliases.get(0);
    }

    public boolean hasSubCommands(){
        return !subCommands.isEmpty();
    }

    public void addSubCommand(subCommandRK subCommand) {
        subCommands.add(subCommand);
    }

    public void setArgumentRequirement(boolean requirement){
        requiredArguments = requirement;
    }

    public boolean getArgumentRequirement(){
        return requiredArguments;
    }

    public abstract void execute(CommandSender sender, String[] args);
    public abstract List<String> tabComplete(CommandSender sender, String[] args);
}
