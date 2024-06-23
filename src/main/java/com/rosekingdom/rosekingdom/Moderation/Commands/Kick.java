package com.rosekingdom.rosekingdom.Moderation.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.List;

public class Kick extends CommandRK {

    public Kick() {
        setName("kick");
        setArgumentRequirement(true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Message.Red("No player found with name " + args[0]));
            return;
        }
        if(args.length == 1) {
            target.kick(Component.text("============================================\n\n", TextColor.fromHexString("#E36414"))
                    .append(Component.text("You were kicked from the server!\n", TextColor.fromHexString("#FB8B24")))
                    .append(Component.text("Reason: ", TextColor.fromHexString("#FB8B24")).append(Component.text("Not specified").color(TextColor.fromHexString("#9A031E"))))
                    .append(Component.text("\n\n============================================", TextColor.fromHexString("#E36414"))), PlayerKickEvent.Cause.KICK_COMMAND);
        }
        if(args.length >= 2){
            StringBuilder reason = new StringBuilder(args[1]);
            for(int str = 2; str < args.length; str++){
                reason.append(" ").append(args[str]);
            }
            target.kick(Component.text("============================================\n\n", TextColor.fromHexString("#E36414"))
                    .append(Component.text("You were kicked from the server!\n", TextColor.fromHexString("#FB8B24")))
                    .append(Component.text("Reason: ", TextColor.fromHexString("#FB8B24")).append(Component.text(reason.toString()).color(TextColor.fromHexString("#9A031E"))))
                    .append(Component.text("\n\n============================================", TextColor.fromHexString("#E36414"))), PlayerKickEvent.Cause.KICK_COMMAND);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return List.of();
    }
}
