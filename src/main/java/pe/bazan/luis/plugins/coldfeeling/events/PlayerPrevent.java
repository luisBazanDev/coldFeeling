package pe.bazan.luis.plugins.coldfeeling.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;

public class PlayerPrevent implements Listener {
  private ColdFeeling coldFeeling = ColdFeeling.getInstance();

  @EventHandler (priority = EventPriority.HIGHEST)
  public void onPlayerFreezeDamage(EntityDamageEvent e) {
    if(!e.getEntityType().equals(EntityType.PLAYER)) return;
    if(e.getCause().equals(EntityDamageEvent.DamageCause.FREEZE)) {
      e.setCancelled(true);
      return;
    }
  }
}
