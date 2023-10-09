package com.rosekingdom.rosekingdom.Locations;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesBC extends CommandRK {

    public CoordinatesBC(){
        this.setName("Coordinates");
        this.addAlias("Coords");
        this.addAlias("xyz");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        Location loc = player.getLocation();
        String xyz = "x: " + loc.getBlockX() + " y: " + loc.getBlockY() + " z: " + loc.getBlockZ();
        switch (args.length) {
            case 0 -> {
                player.sendMessage(Component.text(
                        "Your coordinates are: "
                ).append(Component.text(xyz)));
            }
            case 1 -> {
                List<String> onlinePlayers = new ArrayList<>();
                for (Player players : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.add(players.getName());
                }
                if(args[0].equals(player.getName())){
                    player.sendMessage(Component.text(
                            "Your coordinates are: "
                    ).append(Component.text(xyz)));
                    return;
                }
                if (onlinePlayers.contains(args[0])) {
                    Bukkit.getPlayer(args[0]).sendMessage(Component.text(player.getName())
                            .append(Component.text("'s current coordinates: "))
                            .append(Component.text(xyz))
                    );
                    return;
                }
                if (args[0].equals("all")) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if(player.getName().equals(players.getName())) continue;
                        players.sendMessage(Component.text(player.getName())
                                .append(Component.text("'s current coordinates: "))
                                .append(Component.text(xyz))
                        );
                    }
                    return;
                }
                player.sendMessage(Component.text("Incorrect argument or unexciting player!"));
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> sgt = new ArrayList<>();
        if(args.length == 1){
            for(Player player : Bukkit.getOnlinePlayers()){
                sgt.add(player.getName());
            }
            sgt.add("all");
            return sgt;
        }
        return null;
    }
}
