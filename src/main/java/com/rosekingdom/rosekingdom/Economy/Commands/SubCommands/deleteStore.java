package com.rosekingdom.rosekingdom.Economy.Commands.SubCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class deleteStore extends subCommandRK {

    public deleteStore(int arg){
        super(arg);
        setName("delete");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        List<String> stores = StoreStatement.getStores(player);
        if(args.length == 2 && stores.contains(args[1])){
            Bukkit.getEntity(UUID.fromString(StoreStatement.getStoreId(player, args[1]))).remove();
            StoreStatement.deleteStore(args[1]);
        }
    }
}
