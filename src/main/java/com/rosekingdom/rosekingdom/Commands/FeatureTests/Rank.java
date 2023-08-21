package com.rosekingdom.rosekingdom.Commands.FeatureTests;

import com.rosekingdom.rosekingdom.Commands.Manager.CommandRK;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Rank extends CommandRK {

    public Rank(){
        this.setName("rank");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
