package com.rosekingdom.rosekingdom.Commands.tests;

import com.rosekingdom.rosekingdom.Commands.Manager.CommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.List;

public class testtwo extends CommandRK {

    public testtwo(){
        this.setName("testtwo");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(Component.text("I don't work"));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length > 0){
            return List.of();
        }
        return null;
    }
}
