/**
 * 
 */
package com.acercraft.acerdonatorperks.acercrzyfeet.Commands.Auto;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.acercraft.acerdonatorperks.Main;


/**
 * @author Pete Wicken
 *
 */
public class CrazyAutoSmoke implements CommandExecutor {

	private Main p;
	
	public CrazyAutoSmoke(Main instance) {
		p = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		ChatColor yellow = ChatColor.YELLOW;
		ChatColor red = ChatColor.RED;
		
		if(args.length < 1) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("CrazyFeet.crazysmoke.autosmoke")) {
					if(p.getASmokePlayers().contains(player.getName())) {
						p.getASmokePlayers().remove(player);
						p.getASmokePlayers().saveAutoSmokePlayers();
						player.sendMessage(yellow+"You will no longer have "+red+"CrazySmoke "+yellow+"enabled when joining!");
						return true;
					} else {
						p.getASmokePlayers().add(player);
						p.getASmokePlayers().saveAutoSmokePlayers();
						player.sendMessage(yellow+"You will now have "+red+"CrazySmoke "+yellow+"enabled when joining!");
						return true;
					}
				} else {
					player.sendMessage(red+"No permission.");
					return true;
				}
			} else {
				sender.sendMessage(red+"You must be an ingame player to do this!");
				return true;
			}
		} else if(args.length == 1) {
			if(sender.hasPermission("CrazyFeet.crazysmoke.autosmokeother")) {
				if(Bukkit.getServer().getPlayer(args[0]) != null) {
					Player targ = Bukkit.getServer().getPlayer(args[0]);
					if(p.getASmokePlayers().contains(targ.getName())) {
						p.getASmokePlayers().remove(targ);
						p.getASmokePlayers().saveAutoSmokePlayers();
						targ.sendMessage(red+sender.getName()+yellow+" has enabled automatic "+red+"CrazySmoke"+yellow+" on you when you join!");
						sender.sendMessage(red+targ.getDisplayName()+yellow+" now has automatic "+red+"CrazySmoke"+yellow+" when they join.");
						return true;
					} else {
						p.getASmokePlayers().remove(targ);
						p.getASmokePlayers().saveAutoSmokePlayers();
						targ.sendMessage(red+sender.getName()+yellow+" has disabled automatic "+red+"CrazySmoke"+yellow+" on you when you join!");
						sender.sendMessage(red+targ.getDisplayName()+yellow+" no longer has automatic "+red+"CrazySmoke"+yellow+" when they join.");
						return true;
					}
				} else {
					sender.sendMessage(red+"The player "+yellow+args[0]+red+" is either offline or does not exist!");
					return true;
				}
			} else {
				sender.sendMessage(red+"No permission");
				return true;
			}
		} else {
			sender.sendMessage(red+"Incorrect usage. Use /crazyfeet for help!");
			return true;
		}
	}
}
