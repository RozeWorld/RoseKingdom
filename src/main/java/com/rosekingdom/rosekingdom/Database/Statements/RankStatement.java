package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RankStatement {

    public static void insert(String rank, Boolean hasOp){
        PreparedStatement ps;
        try {
            ps = Database.getConnection().prepareStatement("INSERT INTO ranks (rk_rank, hasOp) VALUES (?, ?)");
            ps.setString(1, rank);
            ps.setBoolean(2, hasOp);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean exists(String rank){
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM ranks WHERE rk_rank=?");
            ps.setString(1, rank);

            ResultSet result = ps.executeQuery();
            return result.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static List<String> getRanks(){
        List<String> ranks = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM ranks");
            ResultSet rs = ps.executeQuery();
            rs.next();
            ranks.add(rs.getString("rk_rank"));
            ps.close();
            return ranks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
