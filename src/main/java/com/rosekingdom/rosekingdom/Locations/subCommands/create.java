package com.rosekingdom.rosekingdom.Locations.subCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Locations.Statements.LocationStatement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
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
        if(args.length==2){
            LocationStatement.insertLocation(id, args[1], player.getLocation(),false);
            player.sendMessage(Component.text("Created " + args[1] + " location!", TextColor.fromHexString("#6be649")));
        }
    }
}
