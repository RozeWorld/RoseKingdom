package com.rosekingdom.rosekingdom.Moderation.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Moderation.Utils.vanish;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class vanishCommand extends CommandRK {

    public vanishCommand(){
        setName("vanish");
        addAlias("vh");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) return;
        if(UserStatement.isVanished(player)){
            vanish.changePlayerVisibility(player, false);
            UserStatement.vanish(player, false);
            player.sendMessage(Component.text("You're are now un-vanished!", TextColor.fromHexString("#04cfde")));
            vanish.removeVanished(player);
        }else {
            vanish.changePlayerVisibility(player, true);
            UserStatement.vanish(player, true);
            player.sendMessage(Component.text("You're now vanish!", TextColor.fromHexString("#04cfde")));
            vanish.addVanished(player);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
