package com.rosekingdom.rosekingdom.Profiles.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Database.Main_Statements.UserStatement;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileStatement extends Database {

    public static void createProfile(Player player){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_profile(id, streak, highscore) VALUES (?,?,?)")) {
            ps.setInt(1, UserStatement.getId(player));
            ps.setInt(2, 1);
            ps.setInt(3, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception(e.getMessage());
        }
    }

    public static boolean exists(Player player){
        boolean exists = false;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM rk_profile WHERE id=?")) {
            ps.setInt(1, UserStatement.getId(player));
            try(ResultSet rs = ps.executeQuery()){
                exists = rs.next();
            }
        }catch (SQLException e){
            Message.Exception(e.getMessage());
        }
        return exists;
    }

    public static void updateStreak(Player player, int streak){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_profile SET streak=? WHERE id=?")) {
            ps.setInt(1, streak);
            ps.setInt(2, UserStatement.getId(player));
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception(e.getMessage());
        }
    }

    public static int getStreak(OfflinePlayer player){
        int streak = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT streak FROM rk_profile WHERE id=?")){
            ps.setInt(1, UserStatement.getId(player));
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                streak = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception(e.getMessage());
        }
        return streak;
    }

    public static int getHighscore(OfflinePlayer player) {
        int highscore = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT highscore FROM rk_profile WHERE id=?")) {
            ps.setInt(1, UserStatement.getId(player));
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                highscore = rs.getInt(1);
            }
        }catch (SQLException e){
            Message.Exception(e.getMessage());
        }
        return highscore;
    }

    public static void setHighscore(Player player, int score) {
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_profile SET streak_highscore=? WHERE id=?")) {
            ps.setInt(1, score);
            ps.setInt(2, UserStatement.getId(player));
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception(e.getMessage());
        }
    }
}
