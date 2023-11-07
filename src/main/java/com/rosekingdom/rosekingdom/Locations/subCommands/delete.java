package com.rosekingdom.rosekingdom.Locations.subCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Locations.Statements.LocationStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delete extends subCommandRK {
        public delete(int arg){
            super(arg);
            setName("delete");
        }

        @Override
        public void executeSub(CommandSender sender, String[] args) {
            if(!(sender instanceof Player player)){
                return;
            }
            int id = UserStatement.getId(player.getUniqueId());
            if(args.length==2){
                LocationStatement.deleteLocation(id, args[1]);
            }

        }

    }
