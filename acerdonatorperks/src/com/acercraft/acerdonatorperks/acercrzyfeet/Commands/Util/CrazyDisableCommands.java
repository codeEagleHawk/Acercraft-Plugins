/**
 * 
 */
package com.acercraft.acerdonatorperks.acercrzyfeet.Commands.Util;

import java.util.ArrayList;


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
public class CrazyDisableCommands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args){
		
		final ChatColor yellow = ChatColor.YELLOW;
		final ChatColor red = ChatColor.RED;
		final ArrayList<Player> cFire = Main.CrazyFire;
		final ArrayList<Player> cSmoke = Main.CrazySmoke;
		final ArrayList<Player> cMagic = Main.CrazyMagic;
		final ArrayList<Player> cPearl = Main.CrazyPearl;
		
		if(args.length < 1) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("CrazyFeet.crazyfire") || player.hasPermission("CrazyFeet.crazysmoke") || player.hasPermission("CrazyFeet.crazymagic") || player.hasPermission("CrazyFeet.crazypearl")) {
					if(cFire.contains(player)) {
						cFire.remove(player);
					} else {
						//return true;
					}
					if(cSmoke.contains(player)) {
						cSmoke.remove(player);
					} else {
						//return true;
					}
					if(cMagic.contains(player)) {
						cMagic.remove(player);
					} else {
						//return true;
					}
					if(cPearl.contains(player)) {
						cPearl.remove(player);
					} else {
						//return true;
					}
					player.sendMessage(yellow+"All of your CrazyFeet modes have been cleared!");
					return true;
				} else {
					player.sendMessage(red+"No permission");
					return true;
				}
			} else {
				sender.sendMessage(red+"You must be an ingame player to do this!");
				return true;
			}
		} else if (args.length == 1) {
			if(sender.hasPermission("CrazyFeet.disableothers")) {
				if(args[0].equals("*")) {
					for(Player onlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
						if(cFire.contains(onlinePlayers)) {
							cFire.remove(onlinePlayers);
						} else {
							//return true;
						}
						if(cSmoke.contains(onlinePlayers)) {
							cSmoke.remove(onlinePlayers);
						} else {
							//return true;
						}
						if(cMagic.contains(onlinePlayers)) {
							cMagic.remove(onlinePlayers);
						} else {
							//return true;
						}
						if(cPearl.contains(onlinePlayers)) {
							cPearl.remove(onlinePlayers);
						} else {
							//return true;
						}
						Bukkit.getServer().broadcastMessage(red+sender.getName()+yellow+" has disabled everyone's CrazyFeet modes!");
						return true;
					}
				} else if(Bukkit.getServer().getPlayer(args[0]) != null) {
					Player targ = Bukkit.getServer().getPlayer(args[0]);
					if(cFire.contains(targ)) {
						cFire.remove(targ);
					} else {
						//return true;
					}
					if(cSmoke.contains(targ)) {
						cSmoke.remove(targ);
					} else {
						//return true;
					}
					if(cMagic.contains(targ)) {
						cMagic.remove(targ);
					} else {
						//return true;
					}
					if(cPearl.contains(targ)) {
						cPearl.remove(targ);
					} else {
						//return true;
					}
					targ.sendMessage(yellow+"All of your CrazyFeet modes have been cleared by "+sender.getName()+"!");
					sender.sendMessage(yellow+"All of "+targ.getDisplayName()+"'s CrazyFeet modes have been cleared!");
					return true;
				} else {
					sender.sendMessage(red+"The player "+yellow+args[0]+red+" is either offline or does not exist!");
					return true;
				}
			} else {
				sender.sendMessage(red+"You do not have permission to disable other people's CrazyFeet modes!");
				return true;
			}
		} else {
			sender.sendMessage(red+"Incorrect usage. Use /crazyfeet for help!");
			return true;
		}
		return false;
	}
}
