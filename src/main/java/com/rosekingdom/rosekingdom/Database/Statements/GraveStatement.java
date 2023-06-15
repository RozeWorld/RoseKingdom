package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GraveStatement extends Database{



    public static void insert(int id, byte[] data, int num){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("INSERT INTO rk_grave(id, grave_num, data) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setInt(2, num);
            Blob blob = new SerialBlob(data);
            ps.setBlob(3, blob);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertInventory(Player player){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO rk_grave(id, grave_num, data) VALUES (?, ?, ?)");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setInt(2, DeathStatement.getDeaths(player));
            for(ItemStack i : player.getInventory().getContents()) {
                if (i != null) {
                    Blob blob = new SerialBlob(i.serializeAsBytes());
                    ps.setBlob(3, blob);
                    ps.executeUpdate();
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateInventory(Player player,Inventory grave, int grave_num){
        deleteGrave(UserStatement.getId(player.getUniqueId()), grave_num);
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO rk_grave(id, grave_num, data) VALUES (?, ?, ?)");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setInt(2, grave_num);
            for(ItemStack i : grave.getContents()) {
                if (i != null) {
                    Blob blob = new SerialBlob(i.serializeAsBytes());
                    ps.setBlob(3, blob);
                    ps.executeUpdate();
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ItemStack> getItems(Player player, int graveNum){
        List<ItemStack> items = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM rk_grave WHERE id=? AND grave_num=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setInt(2, graveNum);
            ResultSet rs = ps.executeQuery();
            for(int k = 0; k<items(player, graveNum);k++){
                rs.next();
                Blob blob = rs.getBlob("data");
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                blob.free();
                items.add(ItemStack.deserializeBytes(blobAsBytes));
            }
            return items;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void removeItem(byte[] bytes, int num){
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM rk_grave WHERE data=? AND grave_num=?");
            Blob blob = new SerialBlob(bytes);
            ps.setBlob(1, blob);
            ps.setInt(2, num);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int items(Player player, int num){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("SELECT count(id) FROM rk_grave WHERE id=? AND grave_num=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setInt(2,num);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getGraves(int id){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("SELECT max(grave_num) FROM rk_grave WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void deleteGrave(int id, int num){
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM rk_grave WHERE id=? AND grave_num=?");
            ps.setInt(1, id);
            ps.setInt(2, num);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
