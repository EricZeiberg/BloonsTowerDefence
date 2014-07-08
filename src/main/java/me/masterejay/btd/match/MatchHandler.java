package me.masterejay.btd.match;

import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author MasterEjay
 */
public class MatchHandler{

	public static boolean TOWERS_ENABLED = false;
	public static int coins = 600;

	public static void setupBar(){
		for (Player p : Bukkit.getOnlinePlayers()){
			BarAPI.setMessage(p, ChatColor.GREEN + "You have " + ChatColor.YELLOW + coins + ChatColor.GREEN + " coins!", 100F);
		}
	}

	public static int getCoins(){
		return coins;
	}

	public static void setCoins(int coins){
		MatchHandler.coins = coins;
		for (Player p : Bukkit.getOnlinePlayers()){
			BarAPI.setMessage(p, ChatColor.GREEN + "You have " + ChatColor.YELLOW + coins + ChatColor.GREEN + " coins!", 100F);
		}
	}


	public static boolean isTOWERS_ENABLED(){
		return TOWERS_ENABLED;
	}

	public static void setTowersEnabled(boolean TOWERS_ENABLED){
		MatchHandler.TOWERS_ENABLED=TOWERS_ENABLED;
	}
}
