package com.rosekingdom.rosekingdom.Economy.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.inventory.ItemStack;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;

public class StockStatement extends Database {
    public void addItemToStore(ItemStack item, String store){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_stock(item, store_id) VALUES (?, ?)")) {
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Unable to add item to store", e);
        }
    }

    public void removeItemFromStore(ItemStack item, String store){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_stock WHERE item=? AND store_id=?")) {
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(1, blob);
            ps.setString(2, store);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Unable to remove from the store", e);
        }
    }

    public int getStock(ItemStack item, String store){
        int stock = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT stock FROM rk_stock WHERE store_id=? AND item=?")) {
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

    public void addStock(ItemStack item, String store, int count){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_stock SET stock = stock + ? WHERE store_id=? AND item=?")){
            ps.setInt(1, count);
            ps.setString(2, store);
            Blob blob = new SerialBlob(item.serializeAsBytes());
            ps.setBlob(3, blob);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't add stock in the store", e);
        }
    }
}
