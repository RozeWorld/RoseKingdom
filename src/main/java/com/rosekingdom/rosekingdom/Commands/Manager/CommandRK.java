package com.rosekingdom.rosekingdom.Commands.Manager;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandRK {
    private final List<String> aliases = new ArrayList<>();
    private final List<subCommandRK> subCommands = new ArrayList<>();

    public void setName(String name) {
        aliases.add(name);
    }
    public List<subCommandRK> getSubCommands() {
        return subCommands;
    }

    public void addAlias(String alias) {
        if (aliases.size() == 0) {
            throw new RuntimeException("Missing command name!");
        }
        aliases.add(alias);
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getName() {
        return aliases.get(0);
    }

    public boolean hasSubCommands(){
        return subCommands.size() > 0;
    }

    public void addSubCommand(subCommandRK subCommand) {
        subCommands.add(subCommand);
    }

    public abstract void execute(CommandSender sender, String[] args);
    public abstract List<String> tabComplete(CommandSender sender, String[] args);
}
