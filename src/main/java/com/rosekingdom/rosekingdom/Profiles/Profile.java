package com.rosekingdom.rosekingdom.Profiles;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Profile extends CommandRK {

    public Profile(){
        setName("profile");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }

        if(args.length == 1){
            OfflinePlayer target = Bukkit.getOfflinePlayer(UUID.fromString(UserStatement.getUUID(args[0])));
            Inventory inventory = new UserGUI(target).getInventory();
            player.openInventory(inventory);
            return;
        }
        Inventory inventory = new UserGUI(player).getInventory();
        player.openInventory(inventory);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 1){
            List<String> tabs = new ArrayList<>();
            for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
                tabs.add(player.getName());
            }
            return tabs;
        }
        return null;
    }
}
