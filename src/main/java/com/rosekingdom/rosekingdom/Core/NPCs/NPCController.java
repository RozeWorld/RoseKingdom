package com.rosekingdom.rosekingdom.Core.NPCs;

import com.rosekingdom.rosekingdom.Core.CommandManager.CommandRK;
import com.rosekingdom.rosekingdom.Core.NPCs.Options.*;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class NPCController extends CommandRK {

    public NPCController(){
        setName("npc");
        setArgumentRequirement(true);
        addSubCommand(new removeNPC(0));
        addSubCommand(new spawnNPC(0));
        addSubCommand(new tabNPC(0));
        addSubCommand(new tabTest(0));
        addSubCommand(new clearNPCs(0));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> tabs = new ArrayList<>();
        if(args.length == 1){
            tabs.add("remove");
            tabs.add("tab");
            tabs.add("spawn");
            tabs.add("test");
            tabs.add("clear");
        }
        if(args.length == 2 && (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("rm"))){
            for(int ids : NPCHandler.getIds()){
                tabs.add(Integer.toString(ids));
            }
        }
        if(args.length == 3 && args[0].equalsIgnoreCase("spawn")){
            tabs.add("true");
            tabs.add("false");
        }
        return tabs;
    }
}
