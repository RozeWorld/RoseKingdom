package com.rosekingdom.rosekingdom.Locations.subCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Locations.Statements.LocationStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class create extends subCommandRK {

    public create(int arg){
        super(arg);
        setName("create");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        int id = UserStatement.getId(player.getUniqueId());
        if(!LocationStatement.exists(id, args[1])){
            switch (args.length){
                case 2 -> {
                    LocationStatement.insertLocation(id, args[1], player.getLocation(),false);
                    player.sendMessage(Component.text("Created " + args[1] + " location!", TextColor.fromHexString("#6be649")));
                }
                case 5 -> {
                    Location loc = new Location(player.getWorld(), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                    LocationStatement.insertLocation(id, args[1], loc, false);
                    player.sendMessage(Component.text("Created " + args[1] + " location!", TextColor.fromHexString("#6be649")));
                }
                case 6 -> {
                    World world = null;
                    switch (args[5]){
                        case "Overworld" -> world = Bukkit.getWorlds().get(0);
                        case "Nether" -> world = Bukkit.getWorlds().get(1);
                        case "End" -> world = Bukkit.getWorlds().get(2);
                    }
                    Location loc = new Location(world, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                    LocationStatement.insertLocation(id, args[1], loc, false);
                    player.sendMessage(Component.text("Created " + args[1] + " location!", TextColor.fromHexString("#6be649")));
                }
                default -> player.sendMessage(Component.text("Missing Arguments!", TextColor.fromHexString("#e30000")));
            }
        } else {
            player.sendMessage(Component.text("There is already a location with this name!", TextColor.fromHexString("#e30000")));
        }
    }
}
