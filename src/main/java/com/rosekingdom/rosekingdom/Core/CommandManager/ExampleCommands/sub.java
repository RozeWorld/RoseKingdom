package com.rosekingdom.rosekingdom.Core.CommandManager.ExampleCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class sub extends subCommandRK {

    public sub(int arg){
        super(arg);
        setName("sub");
        addAlias("s");
    }
    @Override
    public void executeSub(CommandSender sender, String[] args) {
        sender.sendMessage(Component.text("I'm the first Sub Command!"));
    }
}