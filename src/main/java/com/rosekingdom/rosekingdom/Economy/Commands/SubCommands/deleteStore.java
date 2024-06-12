package com.rosekingdom.rosekingdom.Economy.Commands.SubCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.NPCHandler;
import com.rosekingdom.rosekingdom.Core.NPCs.Statements.NPCStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.PricingStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StockStatement;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

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
            int id = NPCStatement.getId(args[1]);
            NPCStatement.removeNPC(id);
            NPCHandler.removeNPC(id);
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
