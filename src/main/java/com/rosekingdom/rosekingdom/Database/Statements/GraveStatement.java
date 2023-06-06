package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GraveStatement {

    public static void insert(int id, byte[] data){
        PreparedStatement ps;
        try {
            ps = Database.getConnection().prepareStatement("INSERT INTO rk_grave(id, data) VALUES (?, ?)");
            ps.setInt(1, id);
            ps.setBytes(2, data);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getData(int id){
        byte[] bytes;
        try {
            PreparedStatement ps = Database.getConnection().prepareStatement("SELECT * FROM rk_grave WHERE id=? LIMIT 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            bytes = rs.getBytes("data");
            ps = Database.getConnection().prepareStatement("DELETE FROM rk_grave WHERE data=? LIMIT 1");
            ps.setBytes(1, bytes);
            ps.executeUpdate();
            return bytes;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int items(int id){
        PreparedStatement ps;
        try {
            ps = Database.getConnection().prepareStatement("SELECT count(id) FROM rk_grave WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
