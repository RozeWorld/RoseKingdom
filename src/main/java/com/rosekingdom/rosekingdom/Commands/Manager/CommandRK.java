package com.rosekingdom.rosekingdom.Commands.Manager;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public abstract class CommandRK {
    private final ArrayList<String> aliases = new ArrayList<>();
    private final ArrayList<subCommandRK> subCommands = new ArrayList<>();

    public void setName(String name) {
        aliases.add(name);
    }
    public ArrayList<subCommandRK> getSubCommands() {
        return subCommands;
    }

    public void addAlias(String alias) {
        if (aliases.size() == 0) {
            throw new RuntimeException("Missing command name!");
        }
        aliases.add(alias);
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public String getName() {
        return aliases.get(1);
    }

    public boolean hasSubCommands(){
        return subCommands.size() > 0;
    }

    public void addSubCommand(subCommandRK subCommand) {
        subCommands.add(subCommand);
    }

    public abstract void execute(CommandSender sender, String[] args);
}
