package com.rosekingdom.rosekingdom.Locations;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Locations.Statements.LocationStatement;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Locations extends CommandRK {

    public Locations(){
        setName("locations");
        addAlias("loc");
        addAlias("l");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }

        int id = UserStatement.getId(player.getUniqueId());
        if(args.length==1 && LocationStatement.getLocations(id).contains(args[0])){
            Location loc = LocationStatement.getLocation(id, args[0]);
            player.sendMessage(Component.text()
                    .append(Component.text(loc.getBlockX()))
                    .append(Component.text(" " + loc.getBlockY() + " "))
                    .append(Component.text(loc.getBlockZ())));
        }
        if(args.length==2 && args[0].equalsIgnoreCase("create")){
            LocationStatement.insertLocation(id, args[1], player.getLocation(),false);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return null;
        }
        int id = UserStatement.getId(player.getUniqueId());
        if(args.length == 1){
            return LocationStatement.getLocations(id);
        }
        return null;
    }
}
