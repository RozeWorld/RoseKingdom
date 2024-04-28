package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KingdomChat extends subCommandRK {

    public KingdomChat(int arg){
        super(arg);
        setName("chat");
        addAlias("c");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        Kingdom kingdom = KingdomHandler.getKingdom(player);
        if(kingdom != null){
            if(!kingdom.getInChat().contains(player)) {
                kingdom.joinChat(player);
                KingdomHandler.addKingdomChatter(player, kingdom);
                player.sendMessage(Component.text("Switched to the kingdom's chat!", TextColor.fromHexString("#5ae630")));
                return;
            }
            kingdom.leaveChat(player);
            KingdomHandler.removeKingdomChatter(player);
            player.sendMessage(Component.text("Switched to the general chat!", TextColor.fromHexString("#5ae630")));
        }else {
            player.sendMessage(Message.Warning("You're not in a kingdom!"));
        }
    }
}
