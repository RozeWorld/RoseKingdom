package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


public class UserStatement {

    public static void addRank(String uuid, String rank){
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
            ps = connection.prepareStatement("INSERT INTO rk_user (name, uuid) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setString(2, uuid);
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

    public static int getId(UUID uuid){
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM rk_user WHERE uuid=?");
            ps.setString(1, uuid.toString());
            ResultSet result = ps.executeQuery();
            result.next();
            return result.getInt("rowid");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
