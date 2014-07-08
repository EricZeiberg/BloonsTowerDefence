package me.masterejay.btd.listeners;

import me.masterejay.btd.BloonsTowerDefence;
import me.masterejay.btd.enums.GameStatus;
import me.masterejay.btd.utils.MobUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author MasterEjay
 */
public class InteractListener implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if (event.getItem().getType() == Material.STONE_SWORD ){
				if (!(BloonsTowerDefence.getGameStatus()== GameStatus.STARTING)|| !(BloonsTowerDefence.getGameStatus()== GameStatus.BETWEEN_ROUND)) {
					event.getPlayer().sendMessage(ChatColor.RED + "The round is in progress, you cannot place towers now");
					return;
				}
				if (!(event.getPlayer().getTargetBlock(null, 30).getType()== Material.GRASS)){
					event.getPlayer().sendMessage(ChatColor.RED + "You can't place a tower there!");
				}
				MobUtil.placeSkeleton(event.getPlayer());
			}


		}
	}

}
