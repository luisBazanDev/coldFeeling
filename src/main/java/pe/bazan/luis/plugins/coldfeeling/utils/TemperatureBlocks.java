package pe.bazan.luis.plugins.coldfeeling.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;

import java.util.HashMap;

public class TemperatureBlocks {
  private static HashMap<Material, Integer> customTemperatures = new HashMap<>();
  private static int all = 0;

  public static void reloadBlocks() {
    ConfigurationSection config = ColdFeeling.getInstance().getConfig().getConfigurationSection("blocks");
    for(String key : config.getKeys(false)) {
      if(key.equalsIgnoreCase("all")) {
        all = config.getInt(key);
      } else {
        Material material = Material.valueOf(key);
        if(material == null) {
          ColdFeeling.PrintError("Error in the config for blocks."+key);
          continue;
        };
        customTemperatures.put(material, config.getInt(key));
      }
    }
  }

  public static int getTemperature(Material material) {
    Integer value = customTemperatures.get(material);
    if(value == null) return all;
    return customTemperatures.get(material);
  }

  public static int calculateTemperature(Location loc) {
    final World world =  loc.getWorld();
    final int x = loc.getBlockX();
    final int y = loc.getBlockY();
    final int z = loc.getBlockZ();
    int floorTotal = 0;
    floorTotal += getTemperature(world.getBlockAt(x, y + 1, z).getType());
    floorTotal += getTemperature(loc.getBlock().getType());
    floorTotal += getTemperature(world.getBlockAt(x, y - 1, z).getType());
    floorTotal += getTemperature(world.getBlockAt(x, y - 2, z).getType());
    floorTotal += getTemperature(world.getBlockAt(x, y - 3, z).getType());
    floorTotal += getTemperature(world.getBlockAt(x, y - 1, z - 1).getType());
    floorTotal += getTemperature(world.getBlockAt(x, y - 1, z + 1).getType());
    floorTotal += getTemperature(world.getBlockAt(x - 1, y - 1, z).getType());
    floorTotal += getTemperature(world.getBlockAt(x + 1, y - 1, z).getType());
    return floorTotal;
  }
}
