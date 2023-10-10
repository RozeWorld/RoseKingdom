package com.rosekingdom.rosekingdom.Locations.Statements;

import com.rosekingdom.rosekingdom.Core.Database.Database;
import com.rosekingdom.rosekingdom.Core.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationStatement extends Database {

    public static void insertLocation(int id, String name, Location location, boolean publicity){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO rk_location(id,name,x,y,z,dim,public) VALUES(?,?,?,?,?,?,?)")){
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, location.getBlockX());
            ps.setInt(4, location.getBlockY());
            ps.setInt(5, location.getBlockZ());
            ps.setString(6, location.getWorld().getName());
            ps.setBoolean(7, publicity);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Trying to save data to the DB failed!" + e.getMessage());
        }
    }

    public static Location getLocation(int id, String name){
        int x, y, z;
        World world;
        Location location = null;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM rk_location WHERE id=? AND name=?")) {
            ps.setInt(1, id);
            ps.setString(2, name);
            try(ResultSet rs = ps.executeQuery()){
                rs.next();
                world = Bukkit.getWorld(rs.getString("dim"));
                x = rs.getInt("x");
                y = rs.getInt("y");
                z = rs.getInt("z");
                location = new Location(world, x, y, z);
            }
        } catch (SQLException e) {
            Message.Exception("Failed fetching the location!");
        }
        return location;
    }

    public static List<String> getLocations(int id){
        List<String> locations = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT name FROM rk_location WHERE id=?")) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
               while (rs.next()){
                   locations.add(rs.getString(1));
               }
            }
        } catch (SQLException e) {
            Message.Exception("Unable to get the locations");
        }
        return locations;
    }

    public static void deleteLocation(int id,String name){
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM rk_location WHERE id=? AND name=?")){
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            Message.Exception("Unsuccessful deletion!");
        }
    }















}
