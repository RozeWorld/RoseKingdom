package com.rosekingdom.rosekingdom.Core.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Items.GuideBook;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class getGuideBook extends CommandRK {

    public getGuideBook() {
        setName("guidebook");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player player) {
            player.getInventory().addItem(new GuideBook());
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return List.of();
    }
}
