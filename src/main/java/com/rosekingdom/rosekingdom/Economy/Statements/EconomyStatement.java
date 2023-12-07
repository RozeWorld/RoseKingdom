package com.rosekingdom.rosekingdom.Economy.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EconomyStatement extends Database {
    public static void insert(Player id){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_economy (id, coins) VALUES (?, ?)")){
            ps.setInt(1, UserStatement.getId(id));
            ps.setInt(2, 0);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unsuccessful Insertion!");
        }
    }
    public static void setCoins(Player id, int coins){
        try(Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_economy SET coins=? WHERE id=?")) {
            ps.setInt(1, UserStatement.getId(id));
            ps.setInt(2, coins);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unable to set the coins");
        }
    }
    public static int getCoins(Player id){
        int coins=0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT coins FROM rk_economy WHERE id=?")) {
            ps.setInt(1, UserStatement.getId(id));
            try(ResultSet result = ps.executeQuery()) {
                result.next();
                coins = result.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception("Non-existing or broken connection");
        }
        return coins;
    }
    public static void addCoins(OfflinePlayer id, int amount){
        try(Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_economy SET coins = coins + ? WHERE id=?")) {
            ps.setInt(1, amount);
            ps.setInt(2, UserStatement.getId(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unable to set the money");
        }
    }
    public static void removeCoins(OfflinePlayer id, int amount){
        try(Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_economy SET coins = coins - ? WHERE id=?")) {
            ps.setInt(1, amount);
            ps.setInt(2, UserStatement.getId(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unable to remove coins");
        }
    }
    public static void reset(Player id){
        try(Connection connection = Database.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_economy SET coins=0 WHERE id=?")) {
            ps.setInt(1, UserStatement.getId(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unable to set the AssignRank");
        }
    }


}
