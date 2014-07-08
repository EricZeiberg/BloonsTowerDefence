package me.masterejay.btd.waves;

import me.masterejay.btd.BloonsTowerDefence;
import me.masterejay.btd.enums.GameStatus;
import me.masterejay.btd.enums.Tier;
import me.masterejay.btd.match.MatchHandler;
import me.masterejay.btd.utils.ChatUtil;
import me.masterejay.btd.utils.MobUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * @author MasterEjay
 */
public class WaveHandler{

	static int SPAWN_ID = 0;
	static int END_ID = 0;
	public static int KILLED_ZOMBIES;
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
		BloonsTowerDefence.setGameStatus(GameStatus.IN_ROUND);
		MatchHandler.setTowersEnabled(false);
		endDetector();
		setKILLED_ZOMBIES(0);
		final Wave finalWave = wave;
		SPAWN_ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(BloonsTowerDefence.get(), new Runnable(){
			int zombie = 0;
			@Override
			public void run(){
				MobUtil.spawnZombie(finalWave.getZombies().get(zombie));
				zombie++;
				if (zombie + 1 > finalWave.getZombies().size()){
					Bukkit.getScheduler().cancelTask(SPAWN_ID);
				}
			}
		}, 0L, wave.getInterval());

	}


	public static void endWave(int waveNumber){
		ChatUtil.broadcastBlockMessage("Round " + waveNumber + " has ended!");
		BloonsTowerDefence.setCurrentWave(null);
		BloonsTowerDefence.setGameStatus(GameStatus.BETWEEN_ROUND);
		MatchHandler.setTowersEnabled(true);
	}

	private static void endDetector(){
		END_ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(BloonsTowerDefence.get(), new Runnable(){
			@Override
			public void run(){
				for (Entity e : Bukkit.getWorlds().get(0).getEntities()){
					if (e.getType() == EntityType.ZOMBIE){
					   if (e.getLocation().distance(BloonsTowerDefence.getMapInfo().getEndLocation()) < 1){
						   e.remove();
						   KILLED_ZOMBIES++;
						   if (KILLED_ZOMBIES == BloonsTowerDefence.getCurrentWave().getZombies().size()){
							   BloonsTowerDefence.getCurrentWave().setFinished(true);
							   Bukkit.getScheduler().cancelTask(END_ID);
						   }
					   }
					}
				}
			}
		}, 0L, 5L);
	}

	public static int getKILLED_ZOMBIES(){
		return KILLED_ZOMBIES;
	}

	public static void setKILLED_ZOMBIES(int KILLED_ZOMBIES){
		WaveHandler.KILLED_ZOMBIES=KILLED_ZOMBIES;
	}
}
