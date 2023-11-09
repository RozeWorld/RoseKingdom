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

import java.util.ArrayList;
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

        if (args.length == 0) {
            player.sendMessage(Component.text("Locations commands:", TextColor.fromHexString("#fff522")));
            player.sendMessage(Component.text("/locations create", TextColor.fromHexString("#FFF522")));
            player.sendMessage(Component.text("/locations delete", TextColor.fromHexString("#FFF522")));
        }

        int id = UserStatement.getId(player.getUniqueId());
        if (args.length == 1 && LocationStatement.getLocations(id).contains(args[0])) {
            Location loc = LocationStatement.getLocation(id, args[0]);
            String world = null;
            switch (loc.getWorld().getEnvironment()) {
                case NORMAL -> world = "Overworld";
                case NETHER -> world = "Nether";
                case THE_END -> world = "The End";
            }
            String text = String.format("%s's coordinates: %d %d %d in %s", args[0], loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), world);

            player.sendMessage(Component.text(text, TextColor.fromHexString("#6be649")));
        } else if (args.length == 1 && LocationStatement.getLocations(id).isEmpty()) {
            player.sendMessage(Component.text("There aren't any locations saved!", TextColor.fromHexString("#e30000")));
        }else if(args.length == 1){
            player.sendMessage(Component.text("No such location!", TextColor.fromHexString("#e30000")));
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return null;
        }
        int id = UserStatement.getId(player.getUniqueId());
        if((args[0].equals("delete") || args[0].equals("remove"))|| args.length == 1){
            return LocationStatement.getLocations(id);
        }
        List<String> tab = new ArrayList<>();
        if(args.length==3){
            tab.add(String.valueOf(player.getLocation().getBlockX()));
            return tab;
        }
        if(args.length==4){
            tab.add(String.valueOf(player.getLocation().getBlockY()));
            return tab;
        }
        if(args.length==5){
            tab.add(String.valueOf(player.getLocation().getBlockZ()));
            return tab;
        }
        if(args.length==6){
            tab.add("Overworld");
            tab.add("Nether");
            tab.add("End");
            return tab;
        }
        return null;
    }
}
