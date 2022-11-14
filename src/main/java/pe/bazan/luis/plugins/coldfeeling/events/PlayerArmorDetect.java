package pe.bazan.luis.plugins.coldfeeling.events;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;
import pe.bazan.luis.plugins.coldfeeling.instances.ColdPlayer;
import pe.bazan.luis.plugins.coldfeeling.utils.TemperatureArmors;

public class PlayerArmorDetect implements Listener {
  ColdFeeling coldFeeling = ColdFeeling.getInstance();

  @EventHandler
  public void onPlayerEquipArmor(PlayerArmorChangeEvent e) {
    ColdPlayer coldPlayer = coldFeeling.getColdPlayers().get(e.getPlayer().getName());
    coldPlayer.setArmor(TemperatureArmors.calculateTemperature(e.getPlayer()));
  }

}
