package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GraveStatement extends Database {

    public static String insert(Player player, Location loc, UUID BD, UUID IA) {
        String id = String.valueOf(UUID.randomUUID());
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO rk_death(id,graveId,x,y,z,dim,IA_uuid,BD_uuid) VALUES(?,?,?,?,?,?,?,?)");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setString(2, id);
            ps.setInt(3, loc.getBlockX());
            ps.setInt(4, loc.getBlockY());
            ps.setInt(5, loc.getBlockZ());
            ps.setString(6, loc.getWorld().getName());
            ps.setString(7, IA.toString());
            ps.setString(8, BD.toString());
            ps.executeUpdate();
            ps.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int total_graves(Player player) {
        int graves = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT count(graveId) FROM rk_death WHERE id=?");
        ) {
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                graves = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return graves;
    }


    public static String getGrave(Entity interaction) {
        String grave = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT graveId FROM rk_death WHERE x=? AND y=? AND z=? AND dim=?");
        ){
            Location loc = interaction.getLocation();
            ps.setInt(1, loc.getBlockX());
            ps.setInt(2, loc.getBlockY());
            ps.setInt(3, loc.getBlockZ());
            ps.setString(4, loc.getWorld().getName());
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                grave = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grave;
    }

//==============================
//
//    Grave items DB
//
//==============================
    public static void insert(int id, byte[] data, String graveId){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("INSERT INTO rk_grave(id, graveId, data) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, graveId);
            Blob blob = new SerialBlob(data);
            ps.setBlob(3, blob);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void insertInventory(int id, String graveId, Player player){
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO rk_grave(id, graveId, data) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, graveId);
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

    public static void UpdateInventory(int id, Inventory grave, String graveId){
        deleteGrave(id, graveId);
        try {
            PreparedStatement ps = getConnection().prepareStatement("INSERT INTO rk_grave(id, graveId, data) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, graveId);
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

    public static List<ItemStack> getItems(int id, String graveId){
        List<ItemStack> items = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_grave WHERE id=? AND graveId=?");
        ) {
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

    public static void removeItem(byte[] bytes, String graveId){
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM rk_grave WHERE data=? AND graveId=?");
            Blob blob = new SerialBlob(bytes);
            ps.setBlob(1, blob);
            ps.setString(2, graveId);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int items(int id, String graveId){
        int items = 0;
        try(Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT count(id) FROM rk_grave WHERE id=? AND graveId=?");
        ) {
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
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM rk_grave WHERE id=? AND graveId=?");
            ps.setInt(1, id);
            ps.setString(2, graveId);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void purge(int id, String graveId) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_death WHERE id=? AND graveId=?");
        ) {
            ps.setInt(1, id);
            ps.setString(2, graveId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Bukkit.getEntity(UUID.fromString(rs.getString("IA_uuid"))).remove();
                    Bukkit.getEntity(UUID.fromString(rs.getString("BD_uuid"))).remove();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_death WHERE id=? AND graveId=?"))
        {
            ps.setInt(1, id);
            ps.setString(2, graveId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Location getLocation(int id, String graveId) {
        Location loc = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT x,y,z,dim FROM rk_death WHERE id=? AND graveId=?");
        ){
            ps.setInt(1, id);
            ps.setString(2, graveId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    loc = new Location(Bukkit.getWorld(rs.getString(4)), rs.getInt(1), rs.getInt(2), rs.getInt(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loc;
    }

    public static void saveTime(int id, String graveId, int time) {
        try {
            PreparedStatement ps = getConnection().prepareStatement("UPDATE rk_death SET TBR=? WHERE id=? AND graveId=?");
            ps.setInt(1, time);
            ps.setInt(2, id);
            ps.setString(3, graveId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getGraveOwners() {
        List<Integer> ids = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT id FROM rk_death");
             ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                ids.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public static List<String> getGraves(int id) {
        List<String> graveId = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT graveId FROM rk_death WHERE id=?");
        ) {
            ps.setInt(1, id);
            try(ResultSet rs =  ps.executeQuery()){
                while(rs.next()){
                    graveId.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return graveId;
    }

    public static int getTime(int id, String graveId){
        int time = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT TBR FROM rk_death WHERE id=? AND graveId=?");
        ){
            ps.setInt(1, id);
            ps.setString(2, graveId);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                time = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return time;
    }
}
