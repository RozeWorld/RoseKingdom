package com.rosekingdom.rosekingdom.Tab.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Tab.AFKstatus;
import com.rosekingdom.rosekingdom.Tab.RankHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AFK extends CommandRK {

    public AFK() {
        setName("afk");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) {
            return;
        }
        RankHandler.setStatusAFK(player);
        AFKstatus.setAFK(player);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return List.of();
    }
}
