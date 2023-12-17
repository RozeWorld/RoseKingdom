package com.rosekingdom.rosekingdom.Economy.Commands.SubCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class createStore extends subCommandRK {

    public createStore(int arg){
        super(arg);
        setName("create");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length == 2){
            if(!StoreStatement.existsName(args[1]) && StoreStatement.numberOfStores(player) <= 3){
                Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                zombie.setAI(false);
                zombie.setShouldBurnInDay(false);
                zombie.setRemoveWhenFarAway(false);
                StoreStatement.createStore(player, zombie.getUniqueId(), args[1]);
            }else {
                player.sendMessage(Message.Warning("You can't create more stores!"));
            }
        }
    }
}
