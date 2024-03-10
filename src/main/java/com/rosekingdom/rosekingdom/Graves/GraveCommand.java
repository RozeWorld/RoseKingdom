package com.rosekingdom.rosekingdom.Graves;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GraveCommand extends CommandRK {
    public GraveCommand(){
        setName("Grave");
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)) {
            return;
        }
        if(args.length==0){
            return;
        }
        if(args.length == 2){
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
            int id = UserStatement.getId(p.getUniqueId());
            List<String> grave = DeathStatement.getGraves(id);
            if(grave.contains(args[1])){
                GraveGUI gui = new GraveGUI(p, args[1]);
                player.openInventory(gui.getInventory());
            }
        }

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            for(Player p : Bukkit.getOnlinePlayers()){
                tabs.add(p.getName());
            }
        }
        if(args.length == 2){
            OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
            int id = UserStatement.getId(p.getUniqueId());
            tabs.addAll(DeathStatement.getGraves(id));
        }
        return tabs;
    }
}
