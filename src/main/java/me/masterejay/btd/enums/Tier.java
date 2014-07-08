package me.masterejay.btd.enums;

import org.bukkit.Color;

/**
 * @author MasterEjay
 */
public enum Tier{

	RED(Color.RED, 20), BLUE(Color.BLUE, 30), GREEN(Color.GREEN, 40);

	private final Color color;
	private final int money;

	private Tier(Color color, int money){
		this.color = color;
		this.money = money;
	}

	public Color getColor(){
		return color;
	}

	public int getMoney(){
		return money;
	}
}
