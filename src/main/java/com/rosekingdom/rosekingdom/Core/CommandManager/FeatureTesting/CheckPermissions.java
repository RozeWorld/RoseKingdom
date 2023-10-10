package com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CheckPermissions extends CommandRK {

    public CheckPermissions(){
        this.setName("perms");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        player.sendMessage(Component.text(String.valueOf(player.hasPermission("rk.owner"))));
        player.sendMessage(Component.text(String.valueOf(player.hasPermission("rk.admin"))));
        player.sendMessage(Component.text(String.valueOf(player.hasPermission("rk.mod"))));
        player.sendMessage(Component.text(String.valueOf(player.hasPermission("rk.default"))));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
