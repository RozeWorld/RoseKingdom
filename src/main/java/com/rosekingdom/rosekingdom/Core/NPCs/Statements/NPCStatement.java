package com.rosekingdom.rosekingdom.Core.NPCs.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.NPCs.NPC;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Economy.Statements.StoreStatement;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NPCStatement extends Database {

    public static void insertNPC(NPC npc) {
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO rk_npc(name, id, onTab, shown, x, y, z, dim) VALUES (?,?,?,?,?,?,?,?)")){
            statement.setString(1, npc.getName());
            statement.setInt(2, npc.getId());
            statement.setBoolean(3, npc.isOnTabList());
            statement.setBoolean(4, npc.isShown());
            statement.setDouble(5, npc.getLocation().getX());
            statement.setDouble(6, npc.getLocation().getY());
            statement.setDouble(7, npc.getLocation().getZ());
            statement.setString(8, npc.getLocation().getWorld().getName());
            statement.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Error inserting npc", e);
        }
    }

    public static void loadNPCs() {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT name, onTab, shown, x, y, z, dim FROM rk_npc");
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                String name = rs.getString("name");
                Location location = new Location(Bukkit.getWorld(rs.getString("dim")), rs.getDouble("x"), rs.getDouble("y"), rs.getDouble("z"));
                NPC npc = new NPC(name, location);
                npc.setOnTabList(rs.getBoolean("onTab"));
                npc.shown(rs.getBoolean("shown"));
                npc.spawn();
                updateID(npc);
                StoreStatement.updateId(npc.getId(), npc.getName());
            }
        }catch (SQLException e){
            Message.Exception("Error loading npcs", e);
        }
    }


    public static void updateID(NPC npc) {
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_npc SET id = ? WHERE name = ?")){
            ps.setInt(1, npc.getId());
            ps.setString(2, npc.getName());
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Error updating npc", e);
        }
    }

    public static void removeNPC(int id) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_npc WHERE id = ?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Error removing npc", e);
        }
    }

    public static int getId(String name) {
        int id = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM rk_npc WHERE name = ?")){
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                id = rs.getInt("id");
            }
        }catch (SQLException e){
            Message.Exception("Error removing npc", e);
        }
        return id;
    }
}
