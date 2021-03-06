/**
 * 
 */
package com.acercraft.acerdonatorperks.acercrzyfeet.Commands.Util.Auto;

import java.util.ArrayList;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.acercraft.acerdonatorperks.Main;
import com.acercraft.acerdonatorperks.acercrzyfeet.Util.Files.CrazyAutoFireFile;
import com.acercraft.acerdonatorperks.acercrzyfeet.Util.Files.CrazyAutoMagicFile;
import com.acercraft.acerdonatorperks.acercrzyfeet.Util.Files.CrazyAutoPearlFile;
import com.acercraft.acerdonatorperks.acercrzyfeet.Util.Files.CrazyAutoSmokeFile;
/**
 * @author Pete Wicken
 *
 */
public class CrazyAutoDisable implements CommandExecutor {
	
	private Main p;
	
	public CrazyAutoDisable(Main instance) {
		p = instance;
	}
	
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor red = ChatColor.RED;
	
	public final ArrayList<String> cFPlayers = CrazyAutoFireFile.cFPlayers;
	public final ArrayList<String> cSPlayers = CrazyAutoSmokeFile.cSPlayers;
	public final ArrayList<String> cPPlayers = CrazyAutoPearlFile.cPPlayers;
	public final ArrayList<String> cMPlayers = CrazyAutoMagicFile.cMPlayers;
	
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args){

		if(args.length < 1) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("CrazyFeet.crazyfire.autofire") || player.hasPermission("CrazyFeet.crazysmoke.autosmoke") || player.hasPermission("CrazyFeet.crazymagic.automagic") || player.hasPermission("CrazyFeet.crazypearl.autopearl")) {
					if(cFPlayers.contains(player.getName())) {
						cFPlayers.remove(player.getName());
					} else {
						//return true;
					}
					if(cSPlayers.contains(player.getName())) {
						cSPlayers.remove(player.getName());
					} else {
						//return true;
					}
					if(cMPlayers.contains(player.getName())) {
						cMPlayers.remove(player.getName());
					} else {
						//return true;
					}
					if(cPPlayers.contains(player.getName())) {
						cPPlayers.remove(player.getName());
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
			if(args[0].equals("*")) {
				if(sender.hasPermission("CrazyFeet.autodisableothers.disableall")) {
					cFPlayers.remove(p.getAFirePlayers());
					p.getAFirePlayers().saveAutoFirePlayers();
					cSPlayers.remove(p.getASmokePlayers());
					p.getASmokePlayers().saveAutoSmokePlayers();
					cPPlayers.remove(p.getAPearlPlayers());
					p.getAPearlPlayers().saveAutoPearlPlayers();
					cMPlayers.remove(p.getAMagicPlayers());
					p.getAMagicPlayers().saveAutoMagicPlayers();
					Bukkit.getServer().broadcastMessage(red+sender.getName()+yellow+" has disabled everyone's automatic CrazyFeet modes!");
					return true;
				}
			} else if(Bukkit.getServer().getPlayer(args[0]) != null) {
				if(sender.hasPermission("CrazyFeet.autodisableothers")) {
					Player targ = Bukkit.getServer().getPlayer(args[0]);
					if(cFPlayers.contains(targ.getName())) {
						cFPlayers.remove(targ.getName());
					} else {
						//return true;
					}
					if(cSPlayers.contains(targ.getName())) {
						cSPlayers.remove(targ.getName());
					} else {
						//return true;
					}
					if(cMPlayers.contains(targ.getName())) {
						cMPlayers.remove(targ.getName());
					} else {
						//return true;
					}
					if(cPPlayers.contains(targ.getName())) {
						cPPlayers.remove(targ.getName());
					} else {
						//return true;
					}
					targ.sendMessage(yellow+"All of your CrazyFeet modes have been cleared by "+sender.getName()+"!");
					sender.sendMessage(yellow+"All of "+targ.getDisplayName()+"'s CrazyFeet modes have been cleared!");
					return true;
				} else {
					sender.sendMessage(red+"You do not have permission to disable other people's automatic CrazyFeet modes!");
				}
			} else {
				sender.sendMessage(red+"The player "+yellow+args[0]+red+" is either offline or does not exist!");
				return true;
			}
		} else {
			sender.sendMessage(red+"Incorrect usage. Use /crazyfeet for help!");
			return true;
		}
		return false;
	}

}
