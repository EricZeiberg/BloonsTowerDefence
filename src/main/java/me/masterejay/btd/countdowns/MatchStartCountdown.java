package me.masterejay.btd.countdowns;

import me.masterejay.btd.waves.WaveHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * @author MasterEjay
 */
public class MatchStartCountdown extends Countdown{
    @Override
    public void onEnd() {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE+"############");
        Bukkit.broadcastMessage(ChatColor.GREEN+"Round 1 has started!");
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE+"############");
	    WaveHandler.startWave(1);
    }

    @Override
    public void tick(int secs) {
        if (secs % 5 == 0 || secs < 5) {
            Bukkit.broadcastMessage(ChatColor.GREEN+"Match starting in "+ChatColor.DARK_RED+""+secs+ChatColor.GREEN+" second"+(secs==1?"":"s")+"!");
        }

    }
}
