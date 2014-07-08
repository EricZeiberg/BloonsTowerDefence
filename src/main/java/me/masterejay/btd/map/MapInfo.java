package me.masterejay.btd.map;

import org.bukkit.Location;

import java.util.List;

/**
 * @author MasterEjay
 */
public class MapInfo{

	List<Location> locationsToWalkTo;
	Location startLocation;
	Location endLocation;

	public MapInfo(List<Location> locationsToWalkTo,Location startLocation,Location endLocation){
		this.locationsToWalkTo=locationsToWalkTo;
		this.startLocation=startLocation;
		this.endLocation=endLocation;
	}

	public List<Location> getLocationsToWalkTo(){
		return locationsToWalkTo;
	}

	public Location getStartLocation(){
		return startLocation;
	}

	public Location getEndLocation(){
		return endLocation;
	}
}
