package me.masterejay.btd.match;

/**
 * @author MasterEjay
 */
public class MatchHandler{

	public static boolean TOWERS_ENABLED = false;

	public static boolean isTOWERS_ENABLED(){
		return TOWERS_ENABLED;
	}

	public static void setTowersEnabled(boolean TOWERS_ENABLED){
		MatchHandler.TOWERS_ENABLED=TOWERS_ENABLED;
	}
}
