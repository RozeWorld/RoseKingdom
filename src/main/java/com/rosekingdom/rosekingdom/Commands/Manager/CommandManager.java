package com.rosekingdom.rosekingdom.Commands.Manager;

import com.rosekingdom.rosekingdom.Commands.tests.test;
import com.rosekingdom.rosekingdom.Commands.tests.testtwo;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommandManager implements TabExecutor {
    JavaPlugin plugin;
    List<CommandRK> commands;
    public CommandManager(JavaPlugin plugin){
        this.plugin = plugin;
        commands = new ArrayList<>();
        commandList();
        registerCommands();
    }

    private void commandList(){
        addCommand(new testtwo());
        addCommand(new test());
    }

    public List<CommandRK> getCommands() {
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
        try {
            for (CommandRK rk : getCommands()) {
                if(rk.getAliases().contains(label)) {
                    if (args.length > 0 && rk.hasSubCommands()) {
                        subCommandManager(sender, args, rk.getSubCommands());
                        return true;
                    }
                    rk.execute(sender, args);
                    return true;
                }
            }
        }catch (Exception e){
            sender.sendMessage(Component.text("Something have gone wrong!"));
            e.printStackTrace();
        }
        return false;
    }

    private void subCommandManager(CommandSender sender, String[] args, List<subCommandRK> sub){
        for(subCommandRK rk : sub){
            for(int arg = 0; arg < args.length; arg++){
                for(String alias : rk.getAliases()) {
                    if ((args[arg].equals(alias) && rk.correctArg(arg)) && arg == args.length - 1) {
                        rk.executeSub(sender, args);
                        return;
                    } else if ((args[arg].equals(alias) && rk.correctArg(arg)) && rk.hasSubCommands()) {
                        subCommandManager(sender, args, rk.getSubCommands());
                    }
                }
            }
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for(CommandRK cmd : getCommands()){
            if(cmd.getAliases().contains(label)){
                return cmd.tabComplete(sender, args);
            }
        }
        return null;
    }
}
