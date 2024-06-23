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
        if(args.length == 0){
            for(BukkitTask task : scheduler.getPendingTasks()){
                sender.sendMessage(Component.text(task.getTaskId() + " : ")
                        .append(Component.text(task.getOwner().getName())));
            }
        }
        if(args.length == 1){
            try {
                scheduler.cancelTask(Integer.parseInt(args[0]));
            }catch (Exception e){
                sender.sendMessage(Component.text("Not a number"));
            }
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
