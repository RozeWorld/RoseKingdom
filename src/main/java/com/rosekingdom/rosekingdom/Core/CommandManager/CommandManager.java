package com.rosekingdom.rosekingdom.Core.CommandManager;

import com.rosekingdom.rosekingdom.CoordinatesShare;
import com.rosekingdom.rosekingdom.Core.CommandManager.ExampleCommands.test;
import com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting.CheckPermissions;
import com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting.Demoscreen;
import com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting.ProtocolTest;
import com.rosekingdom.rosekingdom.Graves.GraveCommand;
import com.rosekingdom.rosekingdom.Locations.Locations;
import com.rosekingdom.rosekingdom.Moderation.Bugs;
import com.rosekingdom.rosekingdom.Moderation.Feedback;
import com.rosekingdom.rosekingdom.Rank;
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
        addCommand(new test());
        addCommand(new CoordinatesShare());
        addCommand(new Demoscreen());
        addCommand(new Rank());
        addCommand(new CheckPermissions());
        addCommand(new Bugs());
        addCommand(new Feedback());
        addCommand(new ProtocolTest());
        addCommand(new Locations());
        addCommand(new GraveCommand());
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
                List<String> tabs = cmd.tabComplete(sender, args);
                if(tabs != null){
                    return List.copyOf(tabs);
                }else {
                    return List.of();
                }
            }
        }
        return null;
    }
}
