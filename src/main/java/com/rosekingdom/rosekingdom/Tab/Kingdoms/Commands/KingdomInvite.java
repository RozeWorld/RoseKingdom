package com.rosekingdom.rosekingdom.Tab.Kingdoms.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
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
            target.sendMessage(Component.text("You are invited to join ", TextColor.fromHexString("#5ae630"))
                    .append(Component.text(kingdom.getName(), TextColor.fromHexString("#5ae630")))
                    .append(Component.text(" kingdom! ", TextColor.fromHexString("#5ae630")))
                    .append(Component.text("[Join]", TextColor.fromHexString("#e3af20"))
                            .hoverEvent(HoverEvent.showText(Component.text("Click to join!\n", TextColor.fromHexString("#e3af20"))
                                    .append(Component.text("Invite Code: ", TextColor.fromHexString("#555555")))
                                    .append(Component.text(invite, TextColor.fromHexString("#AAAAAA")))))
                            .clickEvent(ClickEvent.runCommand("/kingdom " + invite))));
            player.sendMessage(Component.text("Invite sent to ").append(Component.text(target.getName())).color(TextColor.fromHexString("#e3af20")));
        }
    }
}
