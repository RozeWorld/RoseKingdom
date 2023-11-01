package com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayTime extends CommandRK {

    public PlayTime(){
        setName("playtime");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        int rawTime = player.getStatistic(Statistic.PLAY_ONE_MINUTE)/20;
        int hours = rawTime/3600;
        int minutes = (rawTime%3600) / 60;
        int seconds = rawTime % 60;
        player.sendMessage(Component.text("H: "+hours +" M: "+minutes+" S: "+seconds).color(TextColor.fromHexString("#17fc32")));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
