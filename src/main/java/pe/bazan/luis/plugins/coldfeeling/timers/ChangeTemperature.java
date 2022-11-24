package pe.bazan.luis.plugins.coldfeeling.timers;

import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;

public class ChangeTemperature extends BukkitRunnable {
  final ColdFeeling coldFeeling = ColdFeeling.getInstance();

  public ChangeTemperature() {
    runTaskTimer(coldFeeling, 0, 1);
  }

  @Override
  public void run() {
    coldFeeling.getColdPlayers().forEach((index, coldPlayer) -> {
      int oldTemperature = coldPlayer.getTemperature();
      int newTemperature = oldTemperature + coldPlayer.getTotal() / 5;

      if(newTemperature > coldPlayer.getMaxTemperature()) {
        coldPlayer.setTemperature(coldPlayer.getMaxTemperature());
      } else if(newTemperature > 0) {
        coldPlayer.setTemperature(newTemperature);
      } else {
        coldPlayer.setTemperature(0);
      }
    });
  }
}
