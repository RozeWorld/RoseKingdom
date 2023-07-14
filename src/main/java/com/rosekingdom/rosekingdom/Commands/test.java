package com.rosekingdom.rosekingdom.Commands;

import com.rosekingdom.rosekingdom.Commands.Manager.CommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class test extends CommandRK {
    public test(){
        this.setName("test");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player){
            player.sendMessage(Component.text("ti si gey"));
        }
    }
}
