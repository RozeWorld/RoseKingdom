package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandle;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KingdomInvite extends subCommandRK {

    public KingdomInvite(int arg){
        super(arg);
        setName("invite");
        addAlias("inv");
        addAlias("i");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        Kingdom kingdom = KingdomHandler.getKingdom(player);
        String invite = kingdom.createInvite();
        if(args.length == 2){
            Player target = Bukkit.getPlayer(args[1]);
            target.sendMessage(Component.text().clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "kingdom " + invite)));
        }
    }
}
