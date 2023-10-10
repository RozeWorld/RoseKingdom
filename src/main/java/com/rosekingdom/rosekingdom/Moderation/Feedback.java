package com.rosekingdom.rosekingdom.Moderation;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Feedback extends CommandRK {

    public Feedback(){
        setName("feedback");
        addAlias("suggest");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        player.sendMessage(Component.text()
                .append(Component.text("Suggest something we can change or add ", TextColor.fromHexString("#ebb22f")))
                .append(Component.text("here!", TextColor.fromHexString("#ebb22f")).clickEvent(ClickEvent.openUrl("hi")).decorate(TextDecoration.UNDERLINED)));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
