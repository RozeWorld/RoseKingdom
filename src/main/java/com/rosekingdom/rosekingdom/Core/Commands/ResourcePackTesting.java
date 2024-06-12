package com.rosekingdom.rosekingdom.Core.Commands;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.Utils.ResourcePackLoader;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ResourcePackTesting extends CommandRK {

    public ResourcePackTesting(){
        setName("TextureTesting");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        ResourcePackLoader packLoader = new ResourcePackLoader();
        if(args.length == 1 && args[0].equals("true")){
            packLoader.removeResourcePack(player);
        }
        if(args.length == 1 && args[0].equals("false")){
            packLoader.setResourcePack(player);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if(args.length == 1){
            List<String> tabs = new ArrayList<>();
            tabs.add("false");
            tabs.add("true");
            return tabs;
        }
        return null;
    }
}
