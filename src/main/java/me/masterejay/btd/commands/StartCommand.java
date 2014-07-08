package me.masterejay.btd.commands;

import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import me.masterejay.btd.BloonsTowerDefence;
import me.masterejay.btd.countdowns.Countdown;
import me.masterejay.btd.countdowns.CountdownMethods;
import me.masterejay.btd.countdowns.MatchStartCountdown;
import me.masterejay.btd.enums.GameStatus;
import me.masterejay.btd.match.MatchHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * @author MasterEjay
 */
public class StartCommand {

	@Command(aliases = {"start"}, desc = "Starts the game", max = 0)
	public static void start(CommandContext args, CommandSender sender) throws CommandException{
		BloonsTowerDefence.setGameStatus(GameStatus.STARTING);
		CountdownMethods.start(new MatchStartCountdown(), 30);
		Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "You have 30 seconds to place your initial towers down!");
		MatchHandler.setTowersEnabled(true);
		MatchHandler.setupBar();
	}
}
