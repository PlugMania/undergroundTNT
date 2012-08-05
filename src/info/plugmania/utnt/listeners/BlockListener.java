package info.plugmania.utnt.listeners;

import info.plugmania.utnt.undergroundTNT;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {

	undergroundTNT plugin;

	public BlockListener(undergroundTNT instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
	}
	
}
