package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class UserStatement {

    public static void setRank(String uuid, String rank){
        PreparedStatement ps;
        try {
            ps = Database.getConnection().prepareStatement("UPDATE rk_user SET rk_rank=? WHERE uuid=?");
            ps.setString(1, rank);
            ps.setString(2, uuid);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(Connection connection, String name, String uuid){
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("INSERT INTO rk_user (name, uuid, rk_rank) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, uuid);
            ps.setString(3, "default");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean exists(Connection connection, UUID uuid){
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_user WHERE uuid=?");
            ps.setString(1, uuid.toString());

            ResultSet result = ps.executeQuery();
            return result.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean hasRank(String uuid, String rank){
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM rk_user WHERE uuid=? AND rk_rank=?");
            ps.setString(1, uuid);
            ps.setString(2, rank);
            ResultSet result = ps.executeQuery();
            return result.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static String getRank(String uuid){
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT rk_rank FROM rk_user WHERE uuid=?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("rk_rank");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
