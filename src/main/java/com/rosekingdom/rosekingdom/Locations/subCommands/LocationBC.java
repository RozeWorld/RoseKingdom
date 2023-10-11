package com.rosekingdom.rosekingdom.Locations.subCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Locations.Statements.LocationStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocationBC extends subCommandRK {
    public LocationBC(int arg){
        super(arg);
        setName("location");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        int id = UserStatement.getId(player.getUniqueId());
        if(args.length==3 && LocationStatement.getLocations(id).contains(args[2])){
            Location loc = LocationStatement.getLocation(id, args[2]);
            String xyz = "x: " + loc.getBlockX() + " y: " + loc.getBlockY() + " z: " + loc.getBlockZ();
            if(args[0].equals("all")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(players.getName())) continue;
                    players.sendMessage(Component.text(player.getName())
                            .append(Component.text(" shared his coordinates for " + args[2] + ":\n"))
                            .append(Component.text(xyz))
                            .color(TextColor.fromHexString("#6be649")));
                }
            }else
                    Bukkit.getPlayer(args[0]).sendMessage(Component.text(player.getName())
                            .append(Component.text(" shared his coordinates for " + args[2] + ":\n"))
                            .append(Component.text(xyz))
                            .color(TextColor.fromHexString("#6be649")));
        }else{
            player.sendMessage(Component.text("No such location!", TextColor.fromHexString("#e30000")));
        }

    }

}