package com.rosekingdom.rosekingdom.Moderation.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
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
        player.sendMessage(Message.Info("Suggest something we can change or add ")
                .append(Component.text("here!").clickEvent(ClickEvent.openUrl("hi")).decorate(TextDecoration.UNDERLINED)));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
