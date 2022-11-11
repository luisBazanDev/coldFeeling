package pe.bazan.luis.plugins.coldfeeling.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;
import pe.bazan.luis.plugins.coldfeeling.instances.ColdPlayer;
import pe.bazan.luis.plugins.coldfeeling.utils.TemperatureBlocks;

public class PlayerMoveDetect implements Listener {
  ColdFeeling coldFeeling = ColdFeeling.getInstance();

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    ColdPlayer coldPlayer = coldFeeling.getColdPlayers().get(e.getPlayer().getName());
    if(coldPlayer == null) return;
    Location loc = e.getTo();
    coldPlayer.setLight(loc.getBlock().getLightLevel() * 2 - 15);
    coldPlayer.setBiome((int) Math.round(loc.getBlock().getTemperature() * 10));
    coldPlayer.setBiomeName(loc.getBlock().getBiome().name());

    // blocks on the floor
    World world = loc.getWorld();
    int floorTotal = 0;
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ()).getType());
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()).getType());
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ()).getType());
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX(), loc.getBlockY() - 2, loc.getBlockZ()).getType());
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX(), loc.getBlockY() - 3, loc.getBlockZ()).getType());
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() - 1).getType());
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ() + 1).getType());
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX() - 1, loc.getBlockY() - 1, loc.getBlockZ()).getType());
    floorTotal += TemperatureBlocks.getTemperature(world.getBlockAt(loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ()).getType());

    coldPlayer.setFloor(floorTotal);
  }
}
