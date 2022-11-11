package pe.bazan.luis.plugins.coldfeeling;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.coldfeeling.events.PlayerPrevent;
import pe.bazan.luis.plugins.coldfeeling.events.PlayerMoveDetect;
import pe.bazan.luis.plugins.coldfeeling.events.PlayerDetect;
import pe.bazan.luis.plugins.coldfeeling.instances.ColdPlayer;
import pe.bazan.luis.plugins.coldfeeling.placeholders.ColdPlayersPlaceholder;
import pe.bazan.luis.plugins.coldfeeling.utils.TemperatureBlocks;

import java.util.HashMap;

public final class ColdFeeling extends JavaPlugin {
  private static ColdFeeling instance;
  private HashMap<String, ColdPlayer> coldPlayers = new HashMap<>();
  private ColdPlayersPlaceholder coldPlayersPlaceholder;

  @Override
  public void onEnable() {
    saveDefaultConfig();
    setupVariables();
  }

  public void setupVariables() {
    this.instance = this;
    coldPlayersPlaceholder = new ColdPlayersPlaceholder(this);
    coldPlayersPlaceholder.register();
    TemperatureBlocks.reloadBlocks();

    // Events
    getServer().getPluginManager().registerEvents(new PlayerDetect(), this);
    getServer().getPluginManager().registerEvents(new PlayerMoveDetect(), this);
    getServer().getPluginManager().registerEvents(new PlayerPrevent(), this);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  public static ColdFeeling getInstance() {
    return instance;
  }

  public static void PrintError(String msg) {
    instance.getLogger().info(ChatColor.RED + msg);
  }

  public static void PrintWarn(String msg) {
    instance.getLogger().info(ChatColor.YELLOW + msg);
  }

  public HashMap<String, ColdPlayer> getColdPlayers() {
    return coldPlayers;
  }
}
