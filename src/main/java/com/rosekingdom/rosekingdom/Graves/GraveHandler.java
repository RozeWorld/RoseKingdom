package com.rosekingdom.rosekingdom.Graves;

import com.rosekingdom.rosekingdom.Graves.Statements.DeathStatement;
import com.rosekingdom.rosekingdom.Graves.Statements.GraveStatement;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GraveHandler {
    public static List<Grave> graveList = new ArrayList<>();
    public static void addGrave(Grave grave){
        graveList.add(grave);
    }
    public static void removeGrave(int id, String graveId) {
        Location loc = DeathStatement.getLocation(id, graveId);
        Grave.stopTimer();
        if(!loc.isChunkLoaded()){
            loc.getChunk().load();
        }
        for(ItemStack items : GraveStatement.getItems(id, graveId)){
            loc.getWorld().dropItemNaturally(loc, items);
        }
        GraveStatement.deleteGrave(id, graveId);
        DeathStatement.purge(id, graveId);
        for(Grave grave : getGraveList()){
            if(grave.graveId.equals(graveId)){
                getGraveList().remove(grave);
                break;
            }
        }
    }
    public static List<Grave> getGraveList(){
        return graveList;
    }

    public static List<Grave> getGraves(int id) {
        List<Grave> graves = new ArrayList<>();
        for(Grave grave : graveList){
            if(grave.id == id){
                graves.add(grave);
            }
        }
        return graves;
    }


}
