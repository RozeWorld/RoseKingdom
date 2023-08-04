package com.rosekingdom.rosekingdom.Commands.tests;

import com.rosekingdom.rosekingdom.Commands.Manager.subCommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.List;

public class sub extends subCommandRK {

    public sub(int arg){
        super(arg);
        this.setName("sub");
        this.addSubCommand(new subsub(1));
        this.addAlias("s");
    }
    @Override
    public void executeSub(CommandSender sender, String[] args) {
        sender.sendMessage(Component.text("I'm the first Sub Command!"));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
