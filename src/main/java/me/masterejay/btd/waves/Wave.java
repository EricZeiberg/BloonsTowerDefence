package me.masterejay.btd.waves;

import org.bukkit.entity.Zombie;

import java.util.List;

/**
 * @author MasterEjay
 */
public class Wave {

	int waveNumber;
	List<Zombie> zombies;
	String message;
	long interval;

	public Wave(int waveNumber,List<Zombie> zombies,String message,long interval){
		this.waveNumber=waveNumber;
		this.zombies=zombies;
		this.message=message;
		this.interval=interval;
	}

	public int getWaveNumber(){
		return waveNumber;
	}

	public List<Zombie> getZombies(){
		return zombies;
	}

	public String getMessage(){
		return message;
	}

	public long getInterval(){
		return interval;
	}
}
