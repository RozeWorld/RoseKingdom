package com.rosekingdom.rosekingdom.Economy.Commands.SubCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.NPCs.Statements.NPCStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class createStore extends subCommandRK {

    public createStore(int arg){
        super(arg);
        setName("create");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if(args.length == 2){
            if(!StoreStatement.existsName(args[1]) && StoreStatement.numberOfStores(player) < 3){
                NPC npc = new NPC(args[1], player.getLocation());
                NPCStatement.insertNPC(npc);
                npc.spawn();
                StoreStatement.createStore(player, npc.getId(), args[1]);
            }else {
                player.sendMessage(Message.Warning("You can't create more stores!"));
            }
        }
    }
}
