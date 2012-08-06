package info.plugmania.utnt.listeners;

import info.plugmania.utnt.Util;
import info.plugmania.utnt.undergroundTNT;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

	undergroundTNT plugin;

	public PlayerListener(undergroundTNT instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.isCancelled()) return;
		
		/*
		if(event.getClickedBlock().getType().equals(Material.TNT)) {
			if(event.getItem().getType().equals(Material.FLINT_AND_STEEL)) {
					player.sendMessage("TNT ignition certification started ...");
					event.setCancelled(true);
					
					if(event.isCancelled()) {
						player.sendMessage("Certification denied. Housing or surface too in range of explosion!");
						event.getClickedBlock().breakNaturally();
					}
				}
			}
		}
		*/
	}
	
	
}