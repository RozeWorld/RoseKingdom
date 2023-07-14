package com.rosekingdom.rosekingdom.Commands.Manager;

import com.rosekingdom.rosekingdom.Commands.test;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommandManager implements CommandExecutor {
    JavaPlugin plugin;
    ArrayList<CommandRK> commands;
    public CommandManager(JavaPlugin plugin){
        this.plugin = plugin;
        commands = new ArrayList<>();
        commandList();
        registerCommands();
    }

    private void commandList(){
        addCommand(new test());
    }

    public ArrayList<CommandRK> getCommands() {
        return commands;
    }

    public void addCommand(CommandRK command){
        this.commands.add(command);
    }


    private void registerCommands(){
        for(CommandRK command : getCommands()){
            try {
                Objects.requireNonNull(plugin.getCommand(command.getName())).setExecutor(this);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        plugin.getLogger().info("Commands loaded successfully!");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for(CommandRK rk : getCommands()){
            rk.execute(sender, args);
            return true;
        }
        return false;
    }
}
