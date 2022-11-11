package pe.bazan.luis.plugins.coldfeeling.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;
import pe.bazan.luis.plugins.coldfeeling.instances.ColdPlayer;

public class ColdPlayersPlaceholder extends PlaceholderExpansion {
  private ColdFeeling plugin;

  public ColdPlayersPlaceholder(ColdFeeling plugin) {
    this.plugin = plugin;
  }

  @Override
  public String getAuthor() {
    return "Luis Bazán";
  }

  @Override
  public String getIdentifier() {
    return "coldfeeling";
  }

  @Override
  public String getVersion() {
    return "${project.version}";
  }

  @Override
  public String onRequest(OfflinePlayer player, String params) {
    ColdPlayer coldPlayer = plugin.getColdPlayers().get(player.getName());
    String[] parts = params.split("_");
    if(coldPlayer == null) return "ColdFeeling: dont player";

    if(parts.length >= 2 && parts[0].equalsIgnoreCase("value")) {
      if(parts[1].equalsIgnoreCase("biomename")) return coldPlayer.getBiomeName();
      int value = 0;
      switch (parts[1]) {
        case "armor": value = coldPlayer.getArmor();
          break;
        case "biome": value = coldPlayer.getBiome();
          break;
        case "floor": value = coldPlayer.getFloor();
          break;
        case "environment": value = coldPlayer.getEnvironment();
          break;
        case "light": value = coldPlayer.getLight();
          break;
        case "total": value = coldPlayer.getTotal();
          break;
        default:
          break;
      }
      if(parts.length >= 3 && parts[2].equalsIgnoreCase("color")) {
        if(value == 0)
          return ChatColor.YELLOW + String.valueOf(value);
        return (value < 0 ? ChatColor.RED : ChatColor.GREEN) + String.valueOf(value);
      }
      return String.valueOf(value);
    }
    return "none";
  }
}
