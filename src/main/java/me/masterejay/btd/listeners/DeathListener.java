package me.masterejay.btd.listeners;

import me.masterejay.btd.BloonsTowerDefence;
import me.masterejay.btd.enums.Tier;
import me.masterejay.btd.match.MatchHandler;
import me.masterejay.btd.utils.MobUtil;
import me.masterejay.btd.waves.WaveHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.meta.LeatherArmorMeta;

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

	@EventHandler
	public void onTowerDestroyBloon(EntityDeathEvent event){
		if (event.getEntity().getType() == EntityType.ZOMBIE){
			LeatherArmorMeta meta = (LeatherArmorMeta) event.getEntity().getEquipment().getHelmet().getItemMeta();
			Tier tier = null;
			for (Tier t : Tier.values()){
				if (t.getColor().asRGB() == meta.getColor().asRGB()){
					tier = t;
				}
			}
			MatchHandler.setCoins(MatchHandler.getCoins() + tier.getMoney());
			WaveHandler.setKILLED_ZOMBIES(WaveHandler.getKILLED_ZOMBIES() + 1);
			event.getDrops().clear();
		}
	}
}
