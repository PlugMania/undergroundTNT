package info.plugmania.utnt;

import info.plugmania.utnt.listeners.BlockListener;
import info.plugmania.utnt.listeners.PlayerListener;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class undergroundTNT extends JavaPlugin {

	public final Util util;
	public final Commands cmds;
	public boolean debug;
	
	public undergroundTNT() {
		this.util = new Util(this);
		this.cmds = new Commands(this);
	}
	
	public void onDisable (){

	}
	
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new BlockListener(this), this);
		
		this.getConfig().options().copyDefaults(true);
        this.saveConfig();
		
        util.checkVersion(false, null, null);
		if(this.getConfig().getBoolean("debug")) this.debug = true;
		if(this.debug) getLogger().info("Debug enabled.");
		
	}
	
public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		boolean err = false;
		
		try {
			if (args.length == 0) args = new String[] { "help" };
			
			if(sender.hasPermission("utnt.admin")){
				if (args[0].equalsIgnoreCase("help")) {
									
				} else err = true;
				if(err) sender.sendMessage(ChatColor.RED + "[utnt] Either you had an error in your command or your config.yml is broken.");
			}
			else {
				sender.sendMessage(ChatColor.RED + "[utnt] You don't have the permissions to use this command!");
			}
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "An error occured.");
			if(this.debug) sender.sendMessage(ChatColor.RED + "" + e);
		}
		
		return true;
	}
	
}
