package me.masterejay.btd.waves;

import me.masterejay.btd.enums.Tier;
import org.bukkit.entity.Zombie;

import java.util.List;

/**
 * @author MasterEjay
 */
public class Wave {

	int waveNumber;
	List<Tier> zombies;
	String message;
	long interval;

	public Wave(int waveNumber,List<Tier> zombies,String message,long interval){
		this.waveNumber=waveNumber;
		this.zombies=zombies;
		this.message=message;
		this.interval=interval;
	}

	public int getWaveNumber(){
		return waveNumber;
	}

	public List<Tier> getZombies(){
		return zombies;
	}

	public String getMessage(){
		return message;
	}

	public long getInterval(){
		return interval;
	}
}
