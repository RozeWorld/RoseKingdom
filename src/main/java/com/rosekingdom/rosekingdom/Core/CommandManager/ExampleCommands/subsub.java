package com.rosekingdom.rosekingdom.Core.CommandManager.ExampleCommands;

import com.rosekingdom.rosekingdom.Core.CommandManager.subCommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class subsub extends subCommandRK {

    public subsub(int arg){
        super(arg);
        this.setName("subsub");
    }
    @Override
    public void executeSub(CommandSender sender, String[] args) {
        sender.sendMessage(Component.text("I'm the sub sub command from the first sub command!"));
    }
}
