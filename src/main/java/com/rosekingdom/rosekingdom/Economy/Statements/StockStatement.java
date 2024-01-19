package com.rosekingdom.rosekingdom.Economy.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.inventory.ItemStack;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockStatement extends Database {
    public static void addItemToStore(ItemStack item, String store){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_stock(store, item, stock) VALUES (?, ?, ?)")) {
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setString(1, store);
            ps.setBlob(2, blob);
            ps.setInt(3, 0);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Unable to add item to store", e);
        }
    }

    public static void removeItemFromStore(ItemStack item, String store){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_stock WHERE item=? AND store=?")) {
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Unable to remove from the store", e);
        }
    }

    public static int getStock(ItemStack item, String store){
        int stock = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT stock FROM rk_stock WHERE store=? AND item=?")) {
            ps.setString(1, store);
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(2, blob);
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                stock = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception("Can't get the stock value", e);
        }
        return stock;
    }

    public static void addStock(ItemStack item, String store){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_stock SET stock = stock + ? WHERE store=? AND item=?")){
            ps.setInt(1, item.getAmount());
            ps.setString(2, store);
            item.setAmount(1);
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(3, blob);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't add stock in the store", e);
        }
    }

    public static void removeStock(ItemStack item, int amount, String store){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_stock SET stock = stock - ? WHERE store=? AND item=?")){
            ps.setInt(1, amount);
            ps.setString(2, store);
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(3, blob);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't add stock in the store", e);
        }
    }

    public static List<ItemStack> getItems(String store){
        List<ItemStack> items = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT item FROM rk_stock WHERE store=?")) {
            ps.setString(1, store);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Blob blob = rs.getBlob(1);
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    blob.free();
                    items.add(ItemStack.deserializeBytes(blobAsBytes));
                }
            }
        }catch (SQLException e){
            Message.Exception("Can't get the stock items", e);
        }
        return items;
    }

    public static Map<ItemStack, Integer> getItemsStock(String store){
        Map<ItemStack, Integer> items = new HashMap<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT item,stock FROM rk_stock WHERE store=?")) {
            ps.setString(1, store);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Blob blob = rs.getBlob("item");
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    blob.free();
                    items.put(ItemStack.deserializeBytes(blobAsBytes), rs.getInt("stock"));
                }
            }
        }catch (SQLException e){
            Message.Exception("Can't get the stock items", e);
        }
        return items;
    }

    public static boolean exists(ItemStack item, String store) {
        boolean exists = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_stock WHERE item=? AND store=?")){
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            try(ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        }catch (SQLException e){
            Message.Exception("Couldn't add stock in the store", e);
        }
        return exists;
    }
}
