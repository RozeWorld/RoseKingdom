package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GraveStatement {

    public static void insert(int id, byte[] data, int amount){
        PreparedStatement ps;
        try {
            ps = Database.getConnection().prepareStatement("INSERT INTO rk_grave(id, data, amount) VALUES (?, ?, ?)");
            ps.setInt(1, id);
            ps.setBytes(2, data);
            ps.setInt(3, amount);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getData(){
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM rk_grave WHERE id=? LIMIT 1");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getBytes("data");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getAmount(){
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM rk_grave WHERE id=? LIMIT 1");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getBytes("amount");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
