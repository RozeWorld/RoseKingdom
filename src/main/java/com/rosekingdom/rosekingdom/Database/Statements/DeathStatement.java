package com.rosekingdom.rosekingdom.Database.Statements;

import com.rosekingdom.rosekingdom.Database.Database;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeathStatement extends Database {

    public static void insert(Player player, Location loc, LocalTime time, UUID interaction, UUID grave){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("INSERT INTO rk_death(id,numberOfDeaths,time,x,y,z,dim,inter_uuid,grave_uuid) VALUES (?,?,?,?,?,?,?,?)");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            if(exists(player)){
                ps.setInt(2, getDeaths(player)+1);
            }else{
                ps.setInt(2, 1);
            }
            ps.setTime(3, Time.valueOf(time));
            ps.setDouble(5, loc.x());
            ps.setDouble(6, loc.y());
            ps.setDouble(7, loc.z());
            ps.setString(8, loc.getWorld().getName());
            ps.setString(3, interaction.toString());
            ps.setString(4, grave.toString());
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

    public static UUID getInteraction(Player player, int grave_num) {
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM rk_death WHERE id=? AND numberOfDeaths=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setInt(2, grave_num);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return UUID.fromString(rs.getString("inter_uuid"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static UUID getDisplayGrave(Player player, int grave_num) {
        try {
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM rk_death WHERE id=? AND numberOfDeaths=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setInt(2, grave_num);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return UUID.fromString(rs.getString("grave_uuid"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
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

    public static boolean hasDiedOnLocation(Player player, Location loc){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("SELECT * FROM rk_death WHERE id=? AND x=? AND y=? AND z=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setDouble(2, loc.x());
            ps.setDouble(3, loc.y());
            ps.setDouble(4, loc.z());
            ResultSet set = ps.executeQuery();
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteDeath(Player player, int num){
        try {
            PreparedStatement ps = getConnection().prepareStatement("DELETE FROM rk_death WHERE id=? AND numberOfDeaths=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setInt(2, num);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getGraveNum(Player player, Location loc){
        PreparedStatement ps;
        try {
            ps = getConnection().prepareStatement("SELECT * FROM rk_death WHERE id=? AND x=? AND y=? AND z=?");
            ps.setInt(1, UserStatement.getId(player.getUniqueId()));
            ps.setDouble(2, loc.x());
            ps.setDouble(3, loc.y());
            ps.setDouble(4, loc.z());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("numberOfDeaths");
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
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getDouble("z")
                ));
            }
            return locations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
}
