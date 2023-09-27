package com.rosekingdom.rosekingdom.Core.CommandManager.FeatureTesting;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ProtocolTest extends CommandRK {

    public ProtocolTest(){
        setName("pt");
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player player)){
            return;
        }
        //Left for testing and experimenting with packets
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
