package com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Demoscreen extends CommandRK {

    public Demoscreen(){
        this.setName("demo");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            player.showDemoScreen();
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
