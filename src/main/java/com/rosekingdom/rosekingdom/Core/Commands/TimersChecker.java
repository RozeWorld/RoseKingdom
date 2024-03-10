package com.rosekingdom.rosekingdom.Core.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class TimersChecker extends CommandRK {

    public TimersChecker(){
        setName("timers");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        for(BukkitTask task : scheduler.getPendingTasks()){
            sender.sendMessage(Component.text(task.getTaskId() + " : ")
                    .append(Component.text(task.getOwner().getName())));
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
