package info.plugmania.utnt.listeners;

import info.plugmania.utnt.Util;
import info.plugmania.utnt.undergroundTNT;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
			Util.toLog("TNT Broken", true);
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getBlock().getType().equals(Material.TNT)) {
			Location loc = event.getBlock().getLocation();
			if(loc.getWorld().getSeaLevel()<loc.getBlockY()) {
				event.getBlock().breakNaturally();
				event.getPlayer().sendMessage("Placement denied. Explosions above Sealevel isn't allowed!");
			}
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		
		if((event.getEntity() instanceof TNTPrimed)) {
			event.setCancelled(true);

		Location loc = event.getLocation();
		ItemStack itemstack = new ItemStack(Material.TNT, 1);
		loc.getWorld().dropItemNaturally(loc, itemstack);
		}
	}
} 