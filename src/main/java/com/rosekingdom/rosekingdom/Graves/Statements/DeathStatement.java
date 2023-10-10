package com.rosekingdom.rosekingdom.Graves.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DeathStatement extends Database {

    public static String insert(Player player, Location loc, UUID BD, UUID IA) {
        String id = String.valueOf(UUID.randomUUID());
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_death(id,graveId,x,y,z,dim,IA_uuid,BD_uuid) VALUES(?,?,?,?,?,?,?,?)")) {
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setString(2, id);
            ps.setInt(3, loc.getBlockX());
            ps.setInt(4, loc.getBlockY());
            ps.setInt(5, loc.getBlockZ());
            ps.setString(6, loc.getWorld().getName());
            ps.setString(7, IA.toString());
            ps.setString(8, BD.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unsuccessful Insertion!\n" + e.getMessage());
        }
        return id;
    }

    public static String getGrave(Entity interaction) {
        String grave = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT graveId FROM rk_death WHERE x=? AND y=? AND z=? AND dim=?")){
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
            Message.Exception("Bad Connection to the DB");
        }
        return grave;
    }

    public static int total_graves(Player player) {
        int graves = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT count(graveId) FROM rk_death WHERE id=?")) {
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                graves = rs.getInt(1);
            }
        } catch (SQLException e) {
            Message.Exception("Bad Connection to the DB");
        }
        return graves;
    }

    public static List<String> getGraves(int id) {
        List<String> graveId = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT graveId FROM rk_death WHERE id=?")) {
            ps.setInt(1, id);
            try(ResultSet rs =  ps.executeQuery()){
                while(rs.next()){
                    graveId.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            Message.Exception("Bad Connection to the DB");
        }
        return graveId;
    }

    public static List<Integer> getGraveOwners() {
        List<Integer> ids = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM rk_death");
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                ids.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            Message.Exception("Bad Connection to the DB");
        }
        return ids;
    }

    public static Location getLocation(int id, String graveId) {
        Location loc = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT x,y,z,dim FROM rk_death WHERE id=? AND graveId=?")){
            ps.setInt(1, id);
            ps.setString(2, graveId);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    loc = new Location(Bukkit.getWorld(rs.getString(4)), rs.getInt(1), rs.getInt(2), rs.getInt(3));
                }
            }
        } catch (SQLException e) {
            Message.Exception("Bad Connection to the DB");
        }
        return loc;
    }
    public static boolean isGrave(Location loc){
        boolean isGraveOnLocation = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_death WHERE x=? AND y=? AND z=?")) {
            ps.setDouble(1, loc.getBlockX());
            ps.setDouble(2, loc.getBlockY());
            ps.setDouble(3, loc.getBlockZ());
            try(ResultSet rs = ps.executeQuery()) {
                isGraveOnLocation = rs.next();
            }
        } catch (SQLException e) {
            Message.Exception("Bad Connection to the DB");
        }
        return isGraveOnLocation;
    }

    public static int getTime(int id, String graveId){
        int time = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT TBR FROM rk_death WHERE id=? AND graveId=?")){
            ps.setInt(1, id);
            ps.setString(2, graveId);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                time = rs.getInt(1);
            }
        } catch (SQLException e) {
            Message.Exception("Bad Connection to the DB");
        }
        return time;
    }

    public static void saveTime(int id, String graveId, int time) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_death SET TBR=? WHERE id=? AND graveId=?")) {
            ps.setInt(1, time);
            ps.setInt(2, id);
            ps.setString(3, graveId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Bad Connection to the DB");
        }
    }

    public static void purge(int id, String graveId) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_death WHERE id=? AND graveId=?")) {
            ps.setInt(1, id);
            ps.setString(2, graveId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Objects.requireNonNull(Bukkit.getEntity(UUID.fromString(rs.getString("IA_uuid")))).remove();
                    Objects.requireNonNull(Bukkit.getEntity(UUID.fromString(rs.getString("BD_uuid")))).remove();
                }
            }
        }catch (SQLException e){
            Message.Exception("Bad Connection to the DB");
        }
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_death WHERE id=? AND graveId=?")) {
            ps.setInt(1, id);
            ps.setString(2, graveId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Bad Connection to the DB");
        }
    }
}
