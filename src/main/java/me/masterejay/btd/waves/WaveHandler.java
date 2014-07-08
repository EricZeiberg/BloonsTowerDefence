package me.masterejay.btd.waves;

import me.masterejay.btd.BloonsTowerDefence;
import me.masterejay.btd.enums.GameStatus;
import me.masterejay.btd.enums.Tier;
import me.masterejay.btd.utils.MobUtil;
import org.bukkit.Bukkit;

/**
 * @author MasterEjay
 */
public class WaveHandler{

	static int ID = 0;
	public static void startWave(int waveNumber){

      Wave wave = null;

	  for (Wave w : WaveBuilder.waves){
		  if (w.getWaveNumber() == waveNumber){
			wave = w;
		  }
	  }
	   if (wave == null){
		   try{
			   throw new Exception("Wave Number sent to startWave is invalid. Shutting down....");
		   }catch(Exception e){
			   e.printStackTrace();
			   Bukkit.shutdown();
			   return;
		   }
	   }
		BloonsTowerDefence.setCurrentWave(wave);
		final Wave finalWave = wave;
		ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(BloonsTowerDefence.get(), new Runnable(){
			int zombie = 0;
			@Override
			public void run(){
				MobUtil.spawnZombie(finalWave.getZombies().get(zombie));
				zombie++;
			}
		}, 0L, wave.getInterval());

		Bukkit.getScheduler().scheduleSyncDelayedTask(BloonsTowerDefence.get(), new Runnable(){
			@Override
			public void run(){
			   Bukkit.getScheduler().cancelTask(ID);
			}
		}, finalWave.getZombies().size() * wave.getInterval());
	}


}
