package info.plugmania.utnt.listeners;

import info.plugmania.utnt.undergroundTNT;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	undergroundTNT plugin;

	public PlayerListener(undergroundTNT instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		if(plugin.store.stored.contains(player)) {
			Location storedLoc = plugin.store.placed.get(player);
			if(storedLoc.getBlock().getType().equals(Material.TNT)) {
				storedLoc.getBlock().setType(Material.AIR);
				plugin.store.placed.remove(player);
				plugin.store.stored.remove(player);
			}
		}
	}
		
}