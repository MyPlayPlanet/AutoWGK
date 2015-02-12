package de.fly4lol.autowgk.fightmanager;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalConfiguration;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;

import de.fly4lol.autowgk.Main;
import de.fly4lol.autowgk.util.Config;
import de.fly4lol.autowgk.util.Schematic;
import de.pro_crafting.wg.arena.Arena;

public class Util {
	
	private final Main		plugin;
	@SuppressWarnings("unused")
	private final Config	config;
	
	public Util(Main plugin) {
	
		this.plugin = plugin;
		this.config = plugin.getAutoWGKConfig();
	}
	
	public AutoArena getArenaAt(Location location) {
	
		Arena arena = plugin.wg.getArenaManager().getArenaAt(location);
		if (arena == null) {
			return null;
		} else {
			String arenaName = arena.getName();		
			return plugin.loadedArenen.get(arenaName);
		}
	}
	
	public Team getTeamByPlayer(Player player) {
	
		List<AutoArena> autoArenen = plugin.getAutoWGKConfig().getAutoArenen();
		for (AutoArena arena : autoArenen) {
			Team team1 = plugin.loadedArenen.get(arena.getName()).getTeam1();
			Team team2 = plugin.loadedArenen.get(arena.getName()).getTeam2();
			if (team1 != null && team1.getLeader() == player) {
				return team1;
			}
			if (team2 != null && team2.getLeader() == player) {
				return team2;
			}
		}
		return null;
	}
	
	public void pasteSchematic(Schematic schematic, Direction direction, Location location, Player player) {
	
		try {
			String directionString = schematic.getName().substring(schematic.getName().length() - 2, schematic.getName().length());
			boolean rotate = direction != Direction.parse(directionString);

			WorldEdit we = ((WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit")).getWorldEdit();
			LocalConfiguration config = we.getConfiguration();
			
			File dir = we.getWorkingDirectoryFile(config.saveDir);
			File schematicFile = new File(dir, schematic.getPath());
			
			EditSession es = we.getEditSessionFactory().getEditSession(BukkitUtil.getLocalWorld(location.getWorld()), 32000000);
			CuboidClipboard cc = MCEditSchematicFormat.MCEDIT.load(schematicFile);
			
			if (rotate) {
				cc.rotate2D(180);
			}
			
			es.enableQueue();
			cc.paste(es, BukkitUtil.toVector(location), false);
			es.flushQueue();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
