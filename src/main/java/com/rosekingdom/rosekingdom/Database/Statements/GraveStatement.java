package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
            ps.setBytes(3, data);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ItemStack> getItems(Player player){
        List<ItemStack> items = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM rk_grave WHERE id=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ResultSet rs = ps.executeQuery();
            for(int k = 0; k<items(player, 1);k++){
                rs.next();
                items.add(ItemStack.deserializeBytes(rs.getBytes("data")));
            }
            return items;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void removeData(byte[] bytes, int num){
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM rk_grave WHERE data=? AND grave_num=? LIMIT 1");
            ps.setBytes(1, bytes);
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
