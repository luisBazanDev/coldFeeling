package pe.bazan.luis.plugins.coldfeeling.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;
import pe.bazan.luis.plugins.coldfeeling.instances.ColdPlayer;

public class PlayerDetect implements Listener {
  private ColdFeeling coldFeeling = ColdFeeling.getInstance();

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    coldFeeling.getColdPlayers().put(e.getPlayer().getName(), new ColdPlayer(e.getPlayer()));
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
    if(coldFeeling.getColdPlayers().containsKey(e.getPlayer().getName()))
      coldFeeling.getColdPlayers().remove(e.getPlayer().getName());
  }
}
