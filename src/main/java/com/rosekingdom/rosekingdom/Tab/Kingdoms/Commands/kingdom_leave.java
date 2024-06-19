package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kingdom_leave extends subCommandRK {

    public kingdom_leave(int arg){
        super(arg);
        setName("leave");
        addAlias("l");
        addAlias("lv");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        Kingdom kingdom = KingdomHandler.getKingdom(player);
        if(kingdom == null) {
            player.sendMessage(Message.Info("You are not in a kingdom!"));
            return;
        }
        player.sendMessage(Component.text("You left ").append(Component.text(kingdom.getName()).append(Component.text("!"))).color(TextColor.fromHexString("#5ae630")));
        kingdom.leaveKingdom(player);
    }
}
