package com.rosekingdom.rosekingdom.Core.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Discord extends CommandRK {

    public Discord() {
        setName("discord");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(Component.text("Join our discord server! ", TextColor.fromHexString("#7289da"))
                .append(Component.text("[Here]", TextColor.fromHexString("#5865f2")).clickEvent(ClickEvent.openUrl("https://discord.gg/WCPcF8zbus"))));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return List.of();
    }
}
