package me.masterejay.btd.utils;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMob;
import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobs;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AIAttackRanged;
import de.ntcomputer.minecraft.controllablemobs.api.ai.behaviors.AITargetNearest;
import me.masterejay.btd.BloonsTowerDefence;
import me.masterejay.btd.enums.Tier;
import me.masterejay.btd.map.MapInfo;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author MasterEjay
 */
public class MobUtil{

	public static ControllableMob spawnZombie(Tier tier){
		Entity rawZombie= Bukkit.getWorlds().get(0).spawnEntity(BloonsTowerDefence.getMapInfo().getStartLocation(),EntityType.ZOMBIE);
		Zombie z = (Zombie) rawZombie;
		z.getEquipment().setHelmet(tierToHelmet(tier));
		ControllableMob zombie=ControllableMobs.getOrPutUnderControl((LivingEntity)z,true);
		for (Location l : BloonsTowerDefence.getMapInfo().getLocationsToWalkTo()){
			zombie.getActions().moveTo(l, true);
		}
		return zombie;
	}

	public static void placeSkeleton(Player player){
		Entity rawSkele = Bukkit.getWorlds().get(0).spawnEntity(player.getTargetBlock(null,30).getLocation().add(0,1,0),EntityType.SKELETON);
		Skeleton s = (Skeleton) rawSkele;
		s.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
		s.getEquipment().setItemInHand(new ItemStack(Material.BOW));
		s.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000000, 200), true);
		ControllableMob skeleton = ControllableMobs.getOrPutUnderControl((LivingEntity)s, true);
		skeleton.getAI().addBehavior(new AIAttackRanged(0, 0, 5, 10));
		skeleton.getAI().addBehavior(new AITargetNearest(0, 5.0, true, 60, null, Zombie.class));
	}


	public static ItemStack tierToHelmet(Tier tier){
		ItemStack helment = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta meta = (LeatherArmorMeta) helment.getItemMeta();
		if (tier == Tier.RED){
			meta.setColor(Color.RED);
		}
		else if (tier == Tier.BLUE){
			meta.setColor(Color.BLUE);
		}
		else if (tier == Tier.GREEN){
			meta.setColor(Color.GREEN);
		}
		helment.setItemMeta(meta);
		return helment;

	}


}
