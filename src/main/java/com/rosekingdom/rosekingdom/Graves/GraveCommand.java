package com.rosekingdom.rosekingdom.Graves;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        int id = UserStatement.getId(player.getUniqueId());
        List<String> grave = DeathStatement.getGraves(id);
        if(grave.contains(args[0])){
            GraveGUI gui = new GraveGUI(player, args[0]);
            player.openInventory(gui.getInventory());
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        int id = UserStatement.getId(player.getUniqueId());
        return DeathStatement.getGraves(id);
    }
}
