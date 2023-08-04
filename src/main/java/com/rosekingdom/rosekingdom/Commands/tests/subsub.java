package com.rosekingdom.rosekingdom.Commands.tests;

import com.rosekingdom.rosekingdom.Commands.Manager.subCommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.List;

public class subsub extends subCommandRK {

    public subsub(int arg){
        super(arg);
        this.setName("subsub");
        this.addSubCommand(new megasub(2));
    }
    @Override
    public void executeSub(CommandSender sender, String[] args) {
        sender.sendMessage(Component.text("I'm the sub sub command from the first sub command!"));
    }
}
