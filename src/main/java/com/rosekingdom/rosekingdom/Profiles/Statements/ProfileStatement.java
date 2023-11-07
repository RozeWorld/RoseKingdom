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

    public static void createProfile(Player player, long online){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_profile(id, streak, last_online, streak_highscore) VALUES (?,?,?,?)")) {
            ps.setInt(1, UserStatement.getId(player));
            ps.setInt(2, 1);
            ps.setLong(3, online);
            ps.setInt(4, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception(e.getMessage());
        }
    }

    public static boolean exists(Player player){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM rk_profile WHERE id=?")) {
            ps.setInt(1, UserStatement.getId(player));
            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }catch (SQLException e){
            Message.Exception(e.getMessage());
        }
        return false;
    }

    public static void setLastOnline(Player player, long time){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE rk_profile SET last_online=? WHERE id=?")){
            ps.setInt(2, UserStatement.getId(player));
            ps.setLong(1, time);
            ps.executeUpdate();
        }catch (SQLException e){
            Message.Exception(e.getMessage());
        }
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

    public static long getLast_Online(OfflinePlayer player){
        long time = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT last_online FROM rk_profile WHERE id=?")) {
            ps.setInt(1, UserStatement.getId(player));
            try(ResultSet rs = ps.executeQuery()) {
                rs.next();
                time = rs.getLong(1);
            }
        }catch (SQLException e){
            Message.Exception(e.getMessage());
        }
        return time;
    }

    public static int getHighscore(OfflinePlayer player) {
        int highscore = 0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT streak_highscore FROM rk_profile WHERE id=?")) {
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
