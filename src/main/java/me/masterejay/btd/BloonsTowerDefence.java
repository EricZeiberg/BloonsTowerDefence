package me.masterejay.btd;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;
import me.masterejay.btd.commands.StartCommand;
import me.masterejay.btd.enums.GameStatus;
import me.masterejay.btd.listeners.ConnectionListener;
import me.masterejay.btd.listeners.DeathListener;
import me.masterejay.btd.listeners.InteractListener;
import me.masterejay.btd.map.MapInfo;
import me.masterejay.btd.map.MapLoader;
import me.masterejay.btd.match.MatchHandler;
import me.masterejay.btd.waves.Wave;
import me.masterejay.btd.waves.WaveBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @author MasterEjay
 */
public class BloonsTowerDefence extends JavaPlugin {
	private CommandsManager<CommandSender> commands;
	private static GameStatus gameStatus = GameStatus.WAITING;
	private static MapInfo mapInfo;
	private static BloonsTowerDefence plugin;
	public static Wave currentWave;

	@Override
	public void onEnable(){
		super.onEnable();
		plugin = this;
		setupCommands();
		regListeners();
		parse();
		WaveBuilder.setupWaves();
		clearEntities();

	}

	public static BloonsTowerDefence get(){
		return plugin;
	}

	private void clearEntities(){
		for (Entity e : Bukkit.getWorlds().get(0).getEntities()){
			e.remove();
		}
	}

	private void parse(){
		SAXReader reader = new SAXReader();
		Document doc = null;
		try{
			 doc = reader.read(new File(getDataFolder(), "map.xml"));
		}catch(DocumentException e){
			e.printStackTrace();
			Bukkit.shutdown();
		}
		mapInfo = MapLoader.parse(doc);
	}

	@Override
	public void onDisable(){
		super.onDisable();
	}

	public void setupCommands() {
		commands = new CommandsManager<CommandSender>() {
			@Override
			public boolean hasPermission(CommandSender sender, String permission) {
				return sender instanceof ConsoleCommandSender|| sender.hasPermission(permission);
			}
		};

		CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, commands);
	    cmdRegister.register(StartCommand.class);
	}

	private void regListeners(){
	   Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
	   Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			this.commands.execute(cmd.getName(), args, sender, sender);
		} catch (CommandPermissionsException ex) {
			sender.sendMessage(ChatColor.RED + "You don't have permission.");
		} catch (MissingNestedCommandException ex) {
			sender.sendMessage(ChatColor.RED + ex.getUsage());
		} catch (CommandUsageException ex) {
			sender.sendMessage(ChatColor.RED + ex.getMessage());
			sender.sendMessage(ChatColor.RED + ex.getUsage());
		} catch (WrappedCommandException ex) {
			if (ex.getCause() instanceof NumberFormatException) {
				sender.sendMessage(ChatColor.RED + "Number expected, string received instead.");
			} else {
				sender.sendMessage(ChatColor.RED + "An error has occurred running command " + ChatColor.DARK_RED + cmd.getName());
				ex.printStackTrace();
			}
		} catch (CommandException ex) {
			sender.sendMessage(ChatColor.RED + ex.getMessage());
		}

		return true;
	}

	public static MapInfo getMapInfo(){
		return mapInfo;
	}

	public static GameStatus getGameStatus(){
		return gameStatus;
	}

	public static void setGameStatus(GameStatus status){
		gameStatus=status;
	}

	public static Wave getCurrentWave(){
		return currentWave;
	}

	public static void setCurrentWave(Wave currentWave){
		BloonsTowerDefence.currentWave=currentWave;
	}
}
