package com.rosekingdom.rosekingdom.Commands.Manager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {
    ArrayList<CommandRK> commands;

    public ArrayList<CommandRK> getCommands() {
        return commands;
    }

    public void setCommands(CommandRK command){
        this.commands.add(command);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return false;
    }
}
