package de.fly4lol.autowgk.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import de.fly4lol.autowgk.Main;
import de.pro_crafting.sql.api.Connection;

public class MySQLMethods {
	
	static Connection conn = Main.conn;
	
	/*
	 * 
	 * Methods for sign zeugs
	 * 
	 */
	
	
	public void addSign(Player player, Location location, String Arenam, String Type){
		try {
			PreparedStatement prep = conn.prepare("Insert Into signs.signs (uuid, World, X, Y, Z , Type) Values (?, ?, ?, ?, ?, uuid)");
			prep.setString(1, player.getUniqueId().toString());
			prep.setString(2, location.getWorld().getName());
			prep.setInt(3, location.getBlockX());
			prep.setInt(4, location.getBlockY());
			prep.setInt(5, location.getBlockZ());
			prep.setString(6, Type);
			prep.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean existSignAtLocation(Location location){
		try {
			PreparedStatement prep = conn.prepare("Select * From signs.signs Where world=? AND X=? AND Y=? AND Z=?");
			String world = location.getWorld().getName();
			int X = location.getBlockX();
			int Y = location.getBlockY();
			int Z = location.getBlockZ();
			prep.setString(1, world);
			prep.setInt(2, X);
			prep.setInt(3, Y);
			prep.setInt(4, Z);
			ResultSet res = prep.executeQuery();
			return res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Location> getSignsByType(String Type){
		List<Location> signs = new ArrayList <Location>();
		try {
			PreparedStatement prep = conn.prepare("Select * From signs.signs Where Type=?");
			prep.setString(1, Type);
			ResultSet res = prep.executeQuery();
			while(res.next()){
				World world = Bukkit.getWorld(res.getString("world"));
				int X = res.getInt("X");
				int Y = res.getInt("Y");
				int Z = res.getInt("Z");
				Location location = new Location(world, X, Y, Z);
				signs.add( location );
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return signs;
	}
	
	public List<Location> getSignsByOwner(OfflinePlayer player){
		List<Location> signs = new ArrayList <Location>();
		try {
			PreparedStatement prep = conn.prepare("Select * From signs.signs Where uuid=?");
			prep.setString(1, player.getUniqueId().toString());
			ResultSet res = prep.executeQuery();
			while(res.next()){
				World world = Bukkit.getWorld(res.getString("world"));
				int X = res.getInt("X");
				int Y = res.getInt("Y");
				int Z = res.getInt("Z");
				Location location = new Location(world, X, Y, Z);
				signs.add( location );
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return signs;
	}
	
	public List<Location> getSignsByWorld(String worldname){
		List<Location> signs = new ArrayList <Location>();
		try {
			PreparedStatement prep = conn.prepare("Select * From signs.signs Where world=?");
			prep.setString(1, worldname);
			ResultSet res = prep.executeQuery();
			while(res.next()){
				World world = Bukkit.getWorld(res.getString("world"));
				int X = res.getInt("X");
				int Y = res.getInt("Y");
				int Z = res.getInt("Z");
				Location location = new Location(world, X, Y, Z);
				signs.add( location );
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return signs;
	}
	

}
