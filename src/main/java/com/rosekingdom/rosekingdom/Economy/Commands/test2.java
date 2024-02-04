package com.rosekingdom.rosekingdom.Economy.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Economy.GUIs.MerchantGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class test2 extends CommandRK {
    public test2(){
        setName("neshto");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }
        MerchantGUI gui = new MerchantGUI();
        player.openInventory(gui.getInventory());
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
