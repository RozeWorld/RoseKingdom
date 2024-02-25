package com.rosekingdom.rosekingdom.Core.Database.Main_Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Ranks.Rank;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;


public class UserStatement extends Database {
    public static void insert(String name, String uuid){
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_user (name, uuid, rk_rank, join_date) VALUES (?, ?, ?, ?)")){
            ps.setString(1, name);
            ps.setString(2, uuid);
            ps.setString(3, Rank.DEFAULT.name());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unsuccessful Insertion!", e);
        }
    }

    public static boolean exists(UUID uuid){
        boolean exists = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_user WHERE uuid=?")) {
            ps.setString(1, uuid.toString());
            try(ResultSet result = ps.executeQuery()){
                exists = result.next();
            }
        } catch (SQLException e){
            Message.Exception("Invalid request or insufficient connection", e);
        }
        return exists;
    }

    public static boolean hasRank(String uuid, String rank){
        boolean hasRank = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_user WHERE uuid=? AND rk_rank=?")) {
            ps.setString(1, uuid);
            ps.setString(2, rank);
            try(ResultSet result = ps.executeQuery()){
                hasRank = result.next();
            }
        }catch (SQLException e){
            Message.Exception("Invalid request or insufficient connection", e);
        }
        return hasRank;
    }

    public static void setRank(String uuid, String rank){
        try(Connection connection = Database.getConnection();
        PreparedStatement ps = connection.prepareStatement("UPDATE rk_user SET rk_rank=? WHERE uuid=?")) {
            ps.setString(1, rank);
            ps.setString(2, uuid);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unable to set rank", e);
        }
    }

    public static int getId(UUID uuid){
        int id = 0;
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_user WHERE uuid=?")) {
            ps.setString(1, uuid.toString());
            try(ResultSet result = ps.executeQuery()) {
                result.next();
                id = result.getInt("rowid");
            }
        }catch (SQLException e){
            Message.Exception("Non-existing or broken connection", e);
        }
        return id;
    }

    public static int getId(OfflinePlayer player){
        UUID uuid = player.getUniqueId();
        int id = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_user WHERE uuid=?")) {
            ps.setString(1, uuid.toString());
            try(ResultSet result = ps.executeQuery()) {
                result.next();
                id = result.getInt("rowid");
            }
        }catch (SQLException e){
            Message.Exception("Non-existing or broken connection", e);
        }
        return id;
    }

    public static String getRank(UUID uuid){
        String rank = Rank.DEFAULT.name();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT rk_rank FROM rk_user WHERE uuid=?")){
            ps.setString(1, uuid.toString());
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    rank = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            Message.Exception("Unable to fetch player rank", e);
        }
        return rank;
    }

    public static String getName(int id) {
        String name = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT name FROM rk_user WHERE rowid=?")) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            Message.Exception("Non-existing or broken connection", e);
        }
        return name;
    }

    public static String getUUID(int id) {
        String uuid = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT uuid FROM rk_user WHERE rowid=?")) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                uuid = rs.getString(1);
            }
        } catch (SQLException e) {
            Message.Exception("Non-existing or broken connection", e);
        }
        return uuid;
    }

    public static String getUUID(String name) {
        String uuid = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT uuid FROM rk_user WHERE name=?")) {
            ps.setString(1, name);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                uuid = rs.getString(1);
            }
        } catch (SQLException e) {
            Message.Exception("Non-existing or broken connection", e);
        }
        return uuid;
    }

    public static String getJoinDate(int id){
        String date = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT join_date FROM rk_user WHERE rowid=?")) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                date = rs.getTimestamp(1).toString();
            }
        }catch (SQLException e){
            Message.Exception("Non-existing or broken connection", e);
        }
        return date;
    }

    public static boolean exists(String name) {
        boolean exists = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT name FROM rk_user WHERE name=?")){
            ps.setString(1, name);
            try(ResultSet rs = ps.executeQuery()){
                exists = rs.next();
            }
        }catch (Exception e){
            Message.Exception("Unable to fetch data!", e);
        }
        return exists;
    }

    public static void vanish(Player player, boolean off){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_user SET vanished=? WHERE uuid=?")){
            ps.setBoolean(1, off);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't update user", e);
        }
    }
    public static boolean isVanished(OfflinePlayer player){
        boolean isVanished = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT vanished FROM rk_user WHERE uuid=?")) {
            ps.setString(1, player.getUniqueId().toString());
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                isVanished = rs.getBoolean(1);
            }
        }catch (SQLException e){
            Message.Exception("Cannot fetch data!", e);
        }
        return isVanished;
    }
}
