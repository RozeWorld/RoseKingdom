package com.rosekingdom.rosekingdom.Economy.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreStatement extends Database {

    public static void createStore(Player player, int id, String name){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_store(name, owner, store_id, x, y, z, dim, yaw, bank) VALUES (?,?,?,?,?,?,?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, player.getUniqueId().toString());
            ps.setInt(3, id);
            ps.setDouble(4, player.getX());
            ps.setDouble(5, player.getY());
            ps.setDouble(6, player.getZ());
            ps.setString(7, player.getWorld().getName());
            ps.setFloat(8, player.getYaw());
            ps.setInt(9, 0);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Unable to create a store", e);
        }
    }

    public static boolean isStore(int store) {
        boolean isStore = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT store_id FROM rk_store WHERE store_id=?")){
            ps.setInt(1, store);
            try(ResultSet rs = ps.executeQuery()) {
                isStore = rs.next();
            }
        }catch (SQLException e){
            Message.Exception("Unable to fetch and check data", e);
        }
        return isStore;
    }

    public static boolean owner(Player player, int store) {
        boolean isOwner = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_store WHERE store_id=? AND owner=?")){
            ps.setInt(1, store);
            ps.setString(2, player.getUniqueId().toString());
            try(ResultSet rs = ps.executeQuery()) {
                isOwner = rs.next();
            }
        }catch (SQLException e){
            Message.Exception("Unable to fetch and check data", e);
        }
        return isOwner;
    }

    public static String getStore(int storeId) {
        String store = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT name FROM rk_store WHERE store_id=?")){
            ps.setInt(1, storeId);
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                store = rs.getString(1);
            }
        }catch (SQLException e){
            Message.Exception("Unable to fetch and check data", e);
        }
        return store;
    }

    public static boolean hasStore(Player player) {
        boolean hasStore = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_store WHERE owner=?")){
            ps.setString(1, player.getUniqueId().toString());
            try(ResultSet rs = ps.executeQuery()) {
                hasStore = rs.next();
            }
        }catch (SQLException e){
            Message.Exception("Unable to fetch and check data", e);
        }
        return hasStore;
    }

    public static void deleteStore(String store){
        try(Connection connection = getConnection()){
            try(PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_store WHERE name=?")) {
                ps.setString(1, store);
                ps.executeUpdate();
            }
            try(PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_stock WHERE store=?")) {
                ps.setString(1, store);
                ps.executeUpdate();
            }
        }catch (SQLException e){
            Message.Exception("Unsuccessful Deletion!", e);
        }
    }

    public static List<String> getStores(Player player) {
        List<String> stores = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT name FROM rk_store WHERE owner=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()){
                    stores.add(rs.getString(1));
                }
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the stores!", e);
        }
        return stores;
    }

    public static boolean existsName(String name) {
        boolean exists = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_store WHERE name=?")){
            ps.setString(1, name);
            try(ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        }catch (SQLException e){
            Message.Exception("Unable to fetch and check data", e);
        }
        return exists;
    }

    public static int getStoreId(Player player, String name) {
        int storeId = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT store_id FROM rk_store WHERE owner=? AND name=?")){
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, name);
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                storeId = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception("Unable to fetch and check data", e);
        }
        return storeId;
    }

    public static int numberOfStores(Player player) {
        int totalStores = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(name) FROM rk_store WHERE owner=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                totalStores = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the stores!", e);
        }
        return totalStores;
    }

    public static Location getLocation(String store) {
        Location location = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT x,y,z,dim FROM rk_store WHERE name=?")){
            ps.setString(1, store);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                location = new Location(Bukkit.getWorld(rs.getString(4)), rs.getDouble(1), rs.getDouble(2), rs.getDouble(3));
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the location!", e);
        }
        return location;
    }


    public static int getMoney(String store){
        int money = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT bank FROM rk_store WHERE name=?")){
            ps.setString(1, store);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                money = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch bank value!", e);
        }
        return money;
    }

    public static void addMoney(int amount, String store){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_store SET bank = bank + ? WHERE name=?")){
            ps.setInt(1, amount);
            ps.setString(2, store);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't update the store bank!", e);
        }
    }
    public static int transferMoney(Player player, String store){
        int money = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT bank FROM rk_store WHERE name=?")){
            ps.setString(1, store);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                money = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch bank value!", e);
        }
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_store SET bank=0 WHERE name=?")){
            ps.setString(1, store);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Cannot reset the store bank!", e);
        }
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_economy SET coins = coins + ? WHERE id=?")){
            ps.setInt(1, money);
            ps.setInt(2, UserStatement.getId(player.getUniqueId()));
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Can't update player's coins!", e);
        }
        return money;
    }

    public static void updateId(int id, String name){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_store SET store_id = ? WHERE name = ? ")) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't update the store id!", e);
        }
    }

}
