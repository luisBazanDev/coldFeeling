package pe.bazan.luis.plugins.coldfeeling.utils;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
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

  public static int calculateTemperature(Player player) {
    int armorTotal = 0;
    if(player.getInventory().getHelmet() != null)
      armorTotal += getTemperature(player.getInventory().getHelmet().getType());
    if(player.getInventory().getChestplate() != null)
      armorTotal += getTemperature(player.getInventory().getChestplate().getType());
    if(player.getInventory().getLeggings() != null)
      armorTotal += getTemperature(player.getInventory().getLeggings().getType());
    if(player.getInventory().getBoots() != null)
      armorTotal += getTemperature(player.getInventory().getBoots().getType());
    return armorTotal;
  }
}
