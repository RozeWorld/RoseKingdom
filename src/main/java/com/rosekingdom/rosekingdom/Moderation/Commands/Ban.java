package com.rosekingdom.rosekingdom.Moderation.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ban extends CommandRK {

    public Ban(){
        setName("ban");
        setArgumentRequirement(true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        OfflinePlayer target = null;
        if(args.length >= 1) {
            target = Bukkit.getOfflinePlayer(args[0]);
            KingdomHandler.lastOnline((Player) target);
        }
        switch (args.length){
            case 1 -> {
                target.ban("Not specified", (Date) null, sender.getName());
            }
            case 2 -> {
                if(getTime(args)==null){
                    target.ban(args[1], (Date) null, sender.getName());
                    return;
                }
                target.ban("Not specified", getTime(args), sender.getName());
            }
            default -> {
                if(getTime(args)==null){
                    sender.sendMessage(Message.Warning("Incorrect time format"));
                    return;
                }
                StringBuilder reason = new StringBuilder(args[2]);
                for(int str = 3; str < args.length; str++){
                    reason.append(" ").append(args[str]);
                }
                target.ban(reason.toString(), getTime(args), sender.getName());
            }
        }

    }

    private Date getTime(String[] args){
        Date date = null;
        try {
            int number = Integer.parseInt(args[1].replaceAll("[^0-9]", ""));
            switch (args[1].charAt(args[1].length()-1)){
                case 's' -> date = Date.from(Instant.now().plus(number, ChronoUnit.SECONDS));
                case 'm' -> date = Date.from(Instant.now().plus(number, ChronoUnit.MINUTES));
                case 'h' -> date = Date.from(Instant.now().plus(number, ChronoUnit.HOURS));
                case 'd' -> date = Date.from(Instant.now().plus(number, ChronoUnit.DAYS));
            }
        }catch (NumberFormatException e){
            return null;
        }
        return date;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            for(Player player : Bukkit.getOnlinePlayers()){
                tabs.add(player.getName());
            }
            return tabs;
        }
        return null;
    }
}
