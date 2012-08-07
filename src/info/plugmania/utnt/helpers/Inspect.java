package info.plugmania.utnt.helpers;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import info.plugmania.utnt.undergroundTNT;

public class Inspect {

	undergroundTNT plugin;

	public Inspect(undergroundTNT instance) {
		plugin = instance;
	}
	
	public boolean InspectTNT(Location loc, Block block) {
		
		// EXPLOSION REACHES SEALEVEL
		if(loc.getY()+7>=loc.getWorld().getSeaLevel()) return false;
		
		return true;
	}
	
	public void removeBlock(Player p, Location loc) {
		if(p.getGameMode().equals(GameMode.CREATIVE)) loc.getBlock().setType(Material.AIR);
		else loc.getBlock().breakNaturally();
	}
	
}
