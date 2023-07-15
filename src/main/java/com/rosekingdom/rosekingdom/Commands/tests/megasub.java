package com.rosekingdom.rosekingdom.Commands.tests;

import com.rosekingdom.rosekingdom.Commands.Manager.subCommandRK;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;

public class megasub extends subCommandRK {

    public megasub(int arg){
        super(arg);
        this.setName("megasub");
    }

    @Override
    public void executeSub(CommandSender sender, String[] args) {
        sender.sendMessage(Component.text("And I'm the Ultra Mega Sub Command!", TextColor.fromHexString("#0cedaa")));
    }
}
