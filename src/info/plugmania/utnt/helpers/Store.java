package info.plugmania.utnt.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import info.plugmania.utnt.undergroundTNT;

public class Store {
	public List<Player> stored = new ArrayList<Player>();
	public List<Location> detonated = new ArrayList<Location>();
	
	public HashMap<Player, Location> placed = new HashMap<Player, Location>();
	
	undergroundTNT plugin;

	public Store(undergroundTNT instance) {
		plugin = instance;
	}

	public List<Player> matchLoc(Location currentLoc) {
		List<Player> matched = new ArrayList<Player>();
		for(Player player : plugin.store.stored) { 
			Location storedLoc = plugin.store.placed.get(player);
			if(currentLoc.equals(storedLoc)) {
				matched.add(player);
			}
		}
		
		return matched;
	}
	
	public void cleanup() {
		for(Player player : plugin.store.stored) {
			Location storedLoc = plugin.store.placed.get(player);
			if(storedLoc.getBlock().getType().equals(Material.TNT)) {
				storedLoc.getBlock().setType(Material.AIR);
			}
		}
	}
	
	
	
}
