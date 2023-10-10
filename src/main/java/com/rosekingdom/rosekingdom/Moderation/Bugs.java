package com.rosekingdom.rosekingdom.Moderation;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Bugs extends CommandRK {

    public Bugs(){
        this.setName("bugs");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        player.sendMessage(Component.text()
                        .append(Component.text("Report any bugs or issues ", TextColor.fromHexString("#ebb22f")))
                        .append(Component.text("here!", TextColor.fromHexString("#ebb22f")).clickEvent(ClickEvent.openUrl("https://github.com/RozeWorld/RoseKingdom/issues")).decorate(TextDecoration.UNDERLINED)));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
