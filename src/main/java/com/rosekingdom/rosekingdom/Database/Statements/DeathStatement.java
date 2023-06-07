package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeathStatement extends Database {

    public static void insert(Player player, Location loc){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("INSERT INTO rk_death(id,numberOfDeaths,x,y,z,dim) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            if(!exists(player)){
                ps.setInt(2, getDeaths(player)+1);
            }else{
                ps.setInt(2, 1);
            }
            ps.setInt(3, loc.getBlockX());
            ps.setInt(4, loc.getBlockY());
            ps.setInt(5, loc.getBlockZ());
            ps.setString(6, loc.getWorld().getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean exists(Player player){
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM rk_death WHERE id=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getDeaths(Player player){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("SELECT max(numberOfDeaths) FROM rk_death WHERE id=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ResultSet set = ps.executeQuery();
            set.next();
            return set.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Location> getLocation(Player player){
        PreparedStatement ps;
        List<Location> locations = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM rk_death WHERE id=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ResultSet rs = ps.executeQuery();

            for(int k = 0; k < getDeaths(player); k++){
                rs.next();
                locations.add(new Location(
                        Bukkit.getServer().getWorld(rs.getString("dim")),
                        rs.getInt("x"),
                        rs.getInt("y"),
                        rs.getInt("z")
                ));
            }
            return locations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
}
