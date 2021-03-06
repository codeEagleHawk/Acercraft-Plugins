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
public class CrazyAutoPearl implements CommandExecutor {

	private Main p;
	
	public CrazyAutoPearl(Main instance) {
		p = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		ChatColor yellow = ChatColor.YELLOW;
		ChatColor red = ChatColor.RED;
		
		if(args.length < 1) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("CrazyFeet.crazypearl.autopearl")) {
					if(p.getAPearlPlayers().contains(player.getName())) {
						p.getAPearlPlayers().remove(player);
						p.getAPearlPlayers().saveAutoPearlPlayers();
						player.sendMessage(yellow+"You will no longer have "+red+"CrazyPearl "+yellow+"enabled when joining!");
						return true;
					} else {
						p.getAPearlPlayers().add(player);
						p.getAPearlPlayers().saveAutoPearlPlayers();
						player.sendMessage(yellow+"You will now have "+red+"CrazyPearl "+yellow+"enabled when joining!");
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
			if(sender.hasPermission("CrazyFeet.crazypearl.autopearlother")) {
				if(Bukkit.getServer().getPlayer(args[0]) != null) {
					Player targ = Bukkit.getServer().getPlayer(args[0]);
					if(p.getAPearlPlayers().contains(targ.getName())) {
						p.getAPearlPlayers().remove(targ);
						p.getAPearlPlayers().saveAutoPearlPlayers();
						targ.sendMessage(red+sender.getName()+yellow+" has enabled automatic "+red+"CrazyPearl"+yellow+" on you when you join!");
						sender.sendMessage(red+targ.getDisplayName()+yellow+" now has automatic "+red+"CrazyPearl"+yellow+" when they join.");
						return true;
					} else {
						p.getAPearlPlayers().remove(targ);
						p.getAPearlPlayers().saveAutoPearlPlayers();
						targ.sendMessage(red+sender.getName()+yellow+" has disabled automatic "+red+"CrazyPearl"+yellow+" on you when you join!");
						sender.sendMessage(red+targ.getDisplayName()+yellow+" no longer has automatic "+red+"CrazyPearl"+yellow+" when they join.");
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
