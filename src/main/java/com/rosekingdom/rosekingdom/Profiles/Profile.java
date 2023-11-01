package com.rosekingdom.rosekingdom.Profiles;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class Profile extends CommandRK {

    public Profile(){
        setName("profile");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }

        Inventory inventory = new UserGUI(player).getInventory();
        player.openInventory(inventory);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
