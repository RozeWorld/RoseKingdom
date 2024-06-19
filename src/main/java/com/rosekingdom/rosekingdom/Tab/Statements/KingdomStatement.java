package com.rosekingdom.rosekingdom.Tab.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.Kingdom;
import com.rosekingdom.rosekingdom.Tab.Kingdoms.KingdomHandler;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class KingdomStatement extends Database {
    public static void insertKingdom(Kingdom kingdom){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_kingdom(name,owner,members,public) VALUES (?,?,?,?)")){
            ps.setString(1, kingdom.getName());
            ps.setString(2, kingdom.getOwner().toString());
            ps.setInt(3, kingdom.getMembers().size());
            ps.setBoolean(4, kingdom.isPublic());
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't save kingdom!", e);
        }
    }

    public static void insertMembers(Kingdom kingdom){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_kMember(kingdom,member) VALUES (?,?)")){
            ps.setString(1, kingdom.getName());
            for(UUID member: kingdom.getMembers()){
                ps.setString(2, member.toString());
                ps.executeUpdate();
            }
        }catch (SQLException e){
            Message.Exception("Couldn't save kingdom member!", e);
        }
    }

    public static void loadKingdoms(){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_kingdom");
            PreparedStatement psm = connection.prepareStatement("SELECT * FROM rk_kMember");
            PreparedStatement ds = connection.prepareStatement("DELETE FROM rk_kingdom WHERE name=?");
            PreparedStatement dsm = connection.prepareStatement("DELETE FROM rk_kMember WHERE kingdom=? AND member=?");
            ResultSet rs = ps.executeQuery();
            ResultSet rsm = psm.executeQuery()){
            while (rs.next()){
                Kingdom kingdom = new Kingdom(rs.getString("name"), Bukkit.getOfflinePlayer(UUID.fromString(rs.getString("owner"))), rs.getBoolean("public"));
                ds.setString(1, kingdom.getName());
                ds.executeUpdate();
            }
            while (rsm.next()){
                Kingdom kingdom = KingdomHandler.getKingdom(rsm.getString("kingdom"));
                if (kingdom != null) {
                    kingdom.addMember(UUID.fromString(rsm.getString("member")));
                    dsm.setString(1, kingdom.getName());
                    dsm.setString(2, rsm.getString("member"));
                    dsm.executeUpdate();
                }
            }
        }catch (SQLException | NullPointerException e){
            Message.Exception("Couldn't load kingdom or member!", e);
        }
    }

    public static void deleteKingdom(Kingdom kingdom){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_kingdom WHERE name=?");
            PreparedStatement psm = connection.prepareStatement("DELETE FROM rk_kMember WHERE kingdom=?")){
            ps.setString(1, kingdom.getName());
            psm.setString(1, kingdom.getName());
            ps.executeUpdate();
            psm.executeUpdate();
        }catch (SQLException e){
            Message.Exception("Couldn't delete kingdom!", e);
        }
    }
}
