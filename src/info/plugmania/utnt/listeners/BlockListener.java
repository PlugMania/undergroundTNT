package info.plugmania.utnt.listeners;

import info.plugmania.utnt.Util;
import info.plugmania.utnt.undergroundTNT;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {

	undergroundTNT plugin;
	public Util util;
	
	public BlockListener(undergroundTNT instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.TNT)) {
			Location currentLoc = event.getBlock().getLocation();

			for(Player player : plugin.store.matchLoc(currentLoc)) {
				plugin.store.placed.remove(player);
				plugin.store.stored.remove(player);
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getBlock().getType().equals(Material.TNT)) {
			Player player = event.getPlayer();
			Location loc = event.getBlock().getLocation();
			boolean denied = false; 
			
			if(loc.getWorld().getSeaLevel()<loc.getBlockY()) {
				event.getPlayer().sendMessage("Placement denied. Explosions above Sealevel isn't allowed!");
				denied = true;
			} else if(plugin.store.stored.contains(player)) {
				Location storedLoc = plugin.store.placed.get(player);
				
				if(storedLoc.getBlock().getType().equals(Material.TNT) || plugin.store.detonated.contains(storedLoc)) {
					event.getPlayer().sendMessage("Placement denied. You cannot place more than one explosive!");
					denied = true;	
				}
			}
			if(denied) {
				plugin.inspect.removeBlock(player, loc);
			} else {
				plugin.store.placed.put(player, loc);
				plugin.store.stored.add(player);
				
				event.getPlayer().sendMessage("Placement approved.");				
			}
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) { 
		if(event.getEntity() instanceof TNTPrimed) {
			Location currentLoc = event.getLocation();
			plugin.store.detonated.remove(currentLoc);
			util.toLog("Detonation end", true);
			if(!plugin.inspect.InspectTNT(currentLoc, null)) {
				event.setCancelled(true);

				for(Player player : plugin.store.matchLoc(currentLoc)) {
					if(!player.getGameMode().equals(GameMode.CREATIVE)) {
						ItemStack itemstack = new ItemStack(Material.TNT, 1);
						currentLoc.getWorld().dropItemNaturally(currentLoc, itemstack);
						plugin.store.placed.remove(player);
						plugin.store.stored.remove(player);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onExplosionPrime(ExplosionPrimeEvent event) {
		/*
		if(event.getEntity() instanceof TNTPrimed) {
			TNTPrimed entity = (TNTPrimed) event.getEntity();
			
			util.toLog("Primed", true);
			
		}
		*/
	}
} 