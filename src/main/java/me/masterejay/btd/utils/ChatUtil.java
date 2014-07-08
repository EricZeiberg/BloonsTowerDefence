package me.masterejay.btd.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * @author MasterEjay
 */
public class ChatUtil{

	public static void broadcastBlockMessage(String message){
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE+"############");
		Bukkit.broadcastMessage(ChatColor.GREEN + message);
		Bukkit.broadcastMessage(ChatColor.DARK_PURPLE+"############");
	}
}
