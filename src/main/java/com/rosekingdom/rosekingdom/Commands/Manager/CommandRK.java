package com.rosekingdom.rosekingdom.Commands.Manager;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public abstract class CommandRK {
    public HashMap<Integer, String> aliases = new HashMap<>();
    public HashMap<Integer, subCommandRK> subCommands = new HashMap<>();

    public void setName(String name) {
        aliases.put(1, name);
    }

    public void addAlias(String alias) {
        if (aliases.size() == 0) {
            throw new RuntimeException("Missing command name!");
        }
        aliases.put(aliases.size() + 1, alias);
    }

    public String getName() {
        return aliases.get(1);
    }

    public void addSubCommand(subCommandRK subCommand) {
        subCommands.put(subCommands.size() + 1, subCommand);
    }

    public abstract void execute(CommandSender sender, String[] args);
}
