package com.rosekingdom.rosekingdom.Core.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.MillisToTime;
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
        player.sendMessage(Component.text(MillisToTime.withWord(rawTime)).color(TextColor.fromHexString("#17fc32")));
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
