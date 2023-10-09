package com.rosekingdom.rosekingdom.Graves;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraveCommand extends CommandRK {
    HashMap<String, String> graves;
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

        int id = UserStatement.getId(player.getUniqueId());
        List<String> grave = DeathStatement.getGraves(id);

        int index = 0;
        for(String i : grave){
            index++;
            graves.put(player.getName() + index, i);
        }
        GraveGUI gui = new GraveGUI(player, graves.get(args[0]));
        player.openInventory(gui.getInventory());
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        int id = UserStatement.getId(player.getUniqueId());
        List<String> grave = DeathStatement.getGraves(id);
        List<String> tabcomp = new ArrayList<>();
        int index = 0;
        for(String i : grave){
            index++;
            graves.put(player.getName() + index, i);
        }
        for(String a : graves.keySet()){
            tabcomp.add(graves.get(a));
        }
        return tabcomp;
    }
}
