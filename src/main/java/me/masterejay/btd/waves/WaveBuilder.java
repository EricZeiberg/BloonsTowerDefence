package me.masterejay.btd.waves;

import me.masterejay.btd.enums.Tier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MasterEjay
 */
public class WaveBuilder{

	public static List<Wave> waves = new ArrayList<>();

	public static void setupWaves(){
	   List<Tier> wave1 = new ArrayList<>();
		wave1.add(Tier.RED);
		wave1.add(Tier.RED);
		wave1.add(Tier.RED);
		wave1.add(Tier.RED);
		wave1.add(Tier.RED);
		wave1.add(Tier.RED);
		wave1.add(Tier.RED);
		wave1.add(Tier.RED);
		wave1.add(Tier.RED);
		waves.add(new Wave(1, wave1, "This is a test", 40L));
	}
}
