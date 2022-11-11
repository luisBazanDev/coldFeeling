package pe.bazan.luis.plugins.coldfeeling.utils;

import org.bukkit.Material;
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
        ColdFeeling.PrintError("Error in the config for blocks."+key);
        if(material == null) continue;
        customTemperatures.put(material, config.getInt(key));
      }
    }
  }

  public static int getTemperature(Material material) {
    Integer value = customTemperatures.get(material);
    if(value == null) return all;
    return customTemperatures.get(material);
  }
}
