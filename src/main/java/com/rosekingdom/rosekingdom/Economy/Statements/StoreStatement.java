package com.rosekingdom.rosekingdom.Economy.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class StoreStatement extends Database {

    public static void createStore(Player player, UUID uuid){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_store(owner, store_id, x, y, z, dim, yaw) VALUES (?,?,?,?,?,?,?)")) {
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, uuid.toString());
            ps.setDouble(3, player.getX());
            ps.setDouble(4, player.getY());
            ps.setDouble(5, player.getZ());
            ps.setString(6, player.getWorld().getName());
            ps.setFloat(7, player.getYaw());
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Unable to create a store");
            Message.Console(e.getMessage());
        }
    }
    public static void deleteStore(Player player){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_store WHERE owner=?")) {
            ps.setString(1, player.getUniqueId().toString());
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Unable to delete store");
            Message.Console(e.getMessage());
        }
    }


    //TODO: Finish Statements
    public static boolean isStore(UUID uniqueId) {
        boolean isStore = false;
        return isStore;
    }

    public static boolean owner(Player player) {
        boolean isOwner = false;
        return isOwner;
    }
    //TODO: Add remove and update statements
}
