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

public class PricingStatement extends Database {
    public static List<ItemStack> getItems(ItemStack item, String store){
        List<ItemStack> items = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT pricedItem FROM rk_price WHERE store=? AND rawItem=?")) {
            ps.setString(1, store);
            Blob rawblob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(2, rawblob);
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
            Message.Exception("Can't get the set items", e);
        }
        return items;
    }

    public static void addItem(ItemStack item, int price, String store, ItemStack rawItem, boolean options) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_price(pricedItem, price, store, rawItem, options) VALUES(?, ?, ?, ?, ?)")) {
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setInt(2, price);
            ps.setString(3, store);
            blob = new SerialBlob(rawItem.serializeAsBytes());
            ps.setBlob(4, blob);
            ps.setBoolean(5, options);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Cannot add item to the priced items", e);
        }
    }

    public static void addPrice(ItemStack item, String store) {
    }

    public static void removeItem(ItemStack item, String store) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_price WHERE pricedItem=? AND store=?")) {
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't delete item!", e);
        }
    }

    public static void clearAll(ItemStack rawItem, String store) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_price WHERE rawItem=? AND store=?")) {
            Blob blob = new SerialBlob(rawItem.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't delete items!", e);
        }
    }

    public static void clearAll(String store) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_price WHERE store=?")) {
            ps.setString(1, store);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't delete items!", e);
        }
    }

    public static boolean hasOptions(ItemStack item, String store) {
        boolean options = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT options FROM rk_price WHERE rawItem=? AND store=?")){
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                options = rs.getBoolean(1);
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the value", e);
        }
        return options;
    }

    public static boolean exists(ItemStack item, String store) {
        boolean options = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT pricedItem FROM rk_price WHERE pricedItem=? AND store=?")){
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            try(ResultSet rs = ps.executeQuery()) {
                options = rs.next();
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the value", e);
        }
        return options;
    }

    public static Map<Integer, Integer> getItemPrices(ItemStack item, String store) {
        Map<Integer, Integer> prices = new HashMap<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT price,pricedItem FROM rk_price WHERE store=? AND rawItem=?")) {
            ps.setString(1, store);
            Blob rawblob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(2, rawblob);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    Blob blob = rs.getBlob(2);
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    blob.free();
                    prices.put(ItemStack.deserializeBytes(blobAsBytes).getAmount(), rs.getInt(1));
                }
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the prices", e);
        }
        return prices;
    }

    public static int getItemPrice(ItemStack item, String store) {
        int price = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT price FROM rk_price WHERE store=? AND pricedItem=?")) {
            ps.setString(1, store);
            Blob rawblob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(2, rawblob);
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                price = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the price", e);
        }
        return price;
    }

    public static ItemStack getRawItem(ItemStack pricedItem, String store) {
        ItemStack item = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT rawItem FROM rk_price WHERE store=? AND pricedItem=?")) {
            ps.setString(1, store);
            Blob rawblob = new SerialBlob(pricedItem.serializeAsBytes());
            ps.setBlob(2, rawblob);
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                Blob blob = rs.getBlob(1);
                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                blob.free();
                item = ItemStack.deserializeBytes(blobAsBytes);
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the prices", e);
        }
        return item;
    }

    public static boolean itemSizeExists(ItemStack item, int size, String store) {
        boolean exists = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT pricedItem FROM rk_price WHERE rawItem=? AND store=?")){
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()){
                    blob = rs.getBlob(1);
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    blob.free();
                    if(ItemStack.deserializeBytes(blobAsBytes).getAmount() == size){
                        exists = true;
                    }
                }
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the value", e);
        }
        return exists;
    }

    public static boolean hasOffers(ItemStack item, String store) {
        boolean hasOffers = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT pricedItem FROM rk_price WHERE rawItem=? AND store=?")) {
            Blob rawblob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, rawblob);
            ps.setString(2, store);
            try(ResultSet rs = ps.executeQuery()) {
                hasOffers = rs.next();
            }
        }catch (SQLException e){
            Message.Exception("Couldn't fetch the price", e);
        }
        return hasOffers;
    }

    public static int getOffers(ItemStack item, String store) {
        int offers = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(pricedItem) FROM rk_price WHERE rawItem=? AND store=?")) {
            Blob rawblob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, rawblob);
            ps.setString(2, store);
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                offers = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception("Can't get number of offers", e);
        }
        return offers;
    }
}
