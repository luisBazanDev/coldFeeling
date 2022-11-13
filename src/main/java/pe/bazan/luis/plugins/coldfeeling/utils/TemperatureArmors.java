package pe.bazan.luis.plugins.coldfeeling.utils;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;

import java.util.HashMap;

public class TemperatureArmors {
  private static HashMap<Material, Integer> armorsTemperatures = new HashMap<>();
  private static int all = 0;

  public static void reloadArmors() {
    ConfigurationSection config = ColdFeeling.getInstance().getConfig().getConfigurationSection("armors");
    if(config == null) return;
    for(String key : config.getKeys(false)) {
      if(key.equalsIgnoreCase("all")) {
        all = config.getInt(key);
        continue;
      }
      try {
        Material material = Material.valueOf(key);
        armorsTemperatures.put(material, config.getInt(key));
      } catch (IllegalArgumentException | NullPointerException e) {
        ColdFeeling.PrintError("Error in the config for armors."+key);
      }
    }
  }

  public static int getTemperature(Material material) {
    Integer value = armorsTemperatures.get(material);
    if(value != null) return value;
    return all;
  }
}
