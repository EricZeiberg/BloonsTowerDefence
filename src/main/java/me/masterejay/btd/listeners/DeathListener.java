package me.masterejay.btd.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author MasterEjay
 */
public class DeathListener implements Listener{

	@EventHandler
	public void onEntityDBEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Projectile) {
			Projectile proj = (Projectile)event.getDamager();
			event.setCancelled(true);
			LivingEntity e = (LivingEntity) event.getEntity();
			e.damage(event.getDamage());
			proj.remove();
		}
	}
}
