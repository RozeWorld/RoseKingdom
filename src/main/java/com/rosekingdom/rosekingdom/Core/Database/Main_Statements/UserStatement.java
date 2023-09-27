package com.rosekingdom.rosekingdom.Core.Database.Main_Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class UserStatement extends Database {
    public static void insert(String name, String uuid){
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_user (name, uuid, rk_rank) VALUES (?, ?, ?)")){
            ps.setString(1, name);
            ps.setString(2, uuid);
            ps.setString(3, "default");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return id;
    }

    public static String getRank(String uuid){
        String rank = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT rk_rank FROM rk_user WHERE uuid=?")){
            ps.setString(1, uuid);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                rank = rs.getString("rk_rank");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return uuid;
    }
}
