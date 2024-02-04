package com.rosekingdom.rosekingdom.Economy.Commands.SubCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Economy.Statements.PricingStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
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
            Map<ItemStack, Integer> stock = StockStatement.getItemsStock(args[1]);
            Location loc = StoreStatement.getLocation(args[1]);
            for(ItemStack item : stock.keySet()){
                item.setAmount(stock.get(item));
                loc.getWorld().dropItemNaturally(loc, item);
            }
            StoreStatement.deleteStore(args[1]);
            PricingStatement.clearAll(args[1]);
        }
    }
}
