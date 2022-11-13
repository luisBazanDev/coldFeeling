package pe.bazan.luis.plugins.coldfeeling.events;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;
import pe.bazan.luis.plugins.coldfeeling.instances.ColdPlayer;
import pe.bazan.luis.plugins.coldfeeling.utils.TemperatureArmors;

public class PlayerArmorDetect implements Listener {
  ColdFeeling coldFeeling = ColdFeeling.getInstance();

  @EventHandler
  public void onPlayerEquipArmor(PlayerArmorChangeEvent e) {
    Player player = e.getPlayer();
    ColdPlayer coldPlayer = coldFeeling.getColdPlayers().get(player.getName());
    int armorTotal = 0;
    if(player.getInventory().getHelmet() != null)
      armorTotal += TemperatureArmors.getTemperature(player.getInventory().getHelmet().getType());
    if(player.getInventory().getChestplate() != null)
      armorTotal += TemperatureArmors.getTemperature(player.getInventory().getChestplate().getType());
    if(player.getInventory().getLeggings() != null)
      armorTotal += TemperatureArmors.getTemperature(player.getInventory().getLeggings().getType());
    if(player.getInventory().getBoots() != null)
      armorTotal += TemperatureArmors.getTemperature(player.getInventory().getBoots().getType());
    coldPlayer.setArmor(armorTotal);
  }

}
