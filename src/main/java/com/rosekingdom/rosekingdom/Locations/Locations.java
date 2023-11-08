package com.rosekingdom.rosekingdom.Locations;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Locations.Statements.LocationStatement;
import com.rosekingdom.rosekingdom.Locations.subCommands.create;
import com.rosekingdom.rosekingdom.Locations.subCommands.delete;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Locations extends CommandRK {

    public Locations(){
        setName("locations");
        addAlias("loc");
        addAlias("l");
        addSubCommand(new create(0));
        addSubCommand(new delete(0));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if(args.length==0){
            player.sendMessage(Component.text("Locations commands:", TextColor.fromHexString("#e7ff4a")));
            player.sendMessage(Component.text("/locations create", TextColor.fromHexString("#e7ff4a")));
            player.sendMessage(Component.text("/locations delete", TextColor.fromHexString("#e7ff4a")));
        }

        int id = UserStatement.getId(player.getUniqueId());
        if(args.length==1 && LocationStatement.getLocations(id).contains(args[0])){
            Location loc = LocationStatement.getLocation(id, args[0]);
            player.sendMessage(Component.text()
                    .append(Component.text(loc.getBlockX()))
                    .append(Component.text(" " + loc.getBlockY() + " "))
                    .append(Component.text(loc.getBlockZ())));
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return null;
        }
        int id = UserStatement.getId(player.getUniqueId());
        if(args[0].equals("delete") || args.length == 1){
            return LocationStatement.getLocations(id);
        }

        return null;
    }
}
