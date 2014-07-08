package me.masterejay.btd.map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MasterEjay
 */
public class MapLoader{

	public static MapInfo parse(Document document){
		List<Location> locationsToWalkTo = new ArrayList<>();
		Location startLocation;
		Location endLocation;
		Element defenceElement = document.getRootElement();
		String[] startStrings = defenceElement.element("start").getText().split(",");
		String[] endStrings = defenceElement.element("end").getText().split(",");
		for (Object e : defenceElement.element("locations").elements("location")){
			if (e instanceof Element){
				Element el = (Element) e;
				String[] locationStrings = el.getText().split(",");
				Location loc = stringToLocation(Integer.parseInt(locationStrings[0]),Integer.parseInt(locationStrings[1]),Integer.parseInt(locationStrings[2]));
				locationsToWalkTo.add(loc);
			}
		}
		endLocation =  stringToLocation(Integer.parseInt(endStrings[0]), Integer.parseInt(endStrings[1]), Integer.parseInt(endStrings[2]));
		startLocation =  stringToLocation(Integer.parseInt(startStrings[0]), Integer.parseInt(startStrings[1]), Integer.parseInt(startStrings[2]));

		return new MapInfo(locationsToWalkTo, startLocation, endLocation);
	}
	private static Location stringToLocation(int x, int y, int z) {
		return new Location(Bukkit.getWorlds().get(0), x, y, z);
	}

}
