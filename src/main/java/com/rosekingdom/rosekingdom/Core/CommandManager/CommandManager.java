package com.rosekingdom.rosekingdom.Core.CommandManager;

import com.rosekingdom.rosekingdom.Core.Commands.*;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCOptions;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.Commands.Coins;
import com.rosekingdom.rosekingdom.Economy.Commands.Store;
import com.rosekingdom.rosekingdom.Graves.GraveCommand;
import com.rosekingdom.rosekingdom.Moderation.Commands.*;
import com.rosekingdom.rosekingdom.Profiles.Profile;
import com.rosekingdom.rosekingdom.Tab.Commands.AFK;
import com.rosekingdom.rosekingdom.Tab.Commands.Ranking;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands.kingdom_main_command;
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
        addCommand(new Ranking());
        addCommand(new CheckPermissions());
        addCommand(new Bugs());
        addCommand(new Feedback());
        addCommand(new GraveCommand());
        addCommand(new Profile());
        addCommand(new ResourcePackTesting());
        addCommand(new PlayTime());
        addCommand(new Coins());
        addCommand(new Store());
        addCommand(new Kick());
        addCommand(new Ban());
        addCommand(new BanIp());
        addCommand(new vanishCommand());
        addCommand(new TimersChecker());
        addCommand(new NPCOptions());
        addCommand(new kingdom_main_command());
        addCommand(new getGuideBook());
        addCommand(new AFK());
        addCommand(new Discord());
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
                    if(rk.getArgumentRequirement() && args.length==0){
                        sender.sendMessage(Message.Warning("Missing Arguments!"));
                        return false;
                    }
                    if (args.length > 0 && rk.hasSubCommands()) {
                        subCommandManager(sender, args, rk.getSubCommands());
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
        for(int arg = args.length-1; arg>=0; arg--){
            for(subCommandRK rk : sub){
                if(rk.getAliases().contains(args[arg]) && rk.correctArg(arg)){
                    rk.executeSub(sender, args);
                    return;
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
