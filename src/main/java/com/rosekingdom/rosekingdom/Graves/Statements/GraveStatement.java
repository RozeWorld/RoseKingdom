package com.rosekingdom.rosekingdom.Graves.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraveStatement extends Database {

    public static void insertInventory(int id, String graveId, Player player){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_grave(id, graveId, data) VALUES (?, ?, ?)")){
            ps.setInt(1, id);
            ps.setString(2, graveId);
            for(ItemStack i : player.getInventory().getContents()) {
                if (i != null) {
                    Blob blob = new SerialBlob(i.serializeAsBytes());
                    ps.setBlob(3, blob);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdateInventory(int id, Inventory grave, String graveId){
        deleteGrave(id, graveId);
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_grave(id, graveId, data) VALUES (?, ?, ?)")) {
            ps.setInt(1, id);
            ps.setString(2, graveId);
            for(ItemStack i : grave.getContents()) {
                if (i != null) {
                    Blob blob = new SerialBlob(i.serializeAsBytes());
                    ps.setBlob(3, blob);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ItemStack> getItems(int id, String graveId){
        List<ItemStack> items = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_grave WHERE id=? AND graveId=?")) {
            ps.setInt(1, id);
            ps.setString(2, graveId);
            try (ResultSet rs = ps.executeQuery()){
                for(int k = 0; k<items(id, graveId);k++){
                    rs.next();
                    Blob blob = rs.getBlob("data");
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    blob.free();
                    items.add(ItemStack.deserializeBytes(blobAsBytes));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    public static int items(int id, String graveId){
        int items = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT count(id) FROM rk_grave WHERE id=? AND graveId=?")) {
            ps.setInt(1, id);
            ps.setString(2, graveId);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                items = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static void deleteGrave(int id, String graveId){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_grave WHERE id=? AND graveId=?")) {
            ps.setInt(1, id);
            ps.setString(2, graveId);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
