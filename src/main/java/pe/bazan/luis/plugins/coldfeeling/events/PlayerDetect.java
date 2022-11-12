package pe.bazan.luis.plugins.coldfeeling.events;

import dev.dbassett.skullcreator.SkullCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.skinsrestorer.api.SkinsRestorerAPI;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;
import pe.bazan.luis.plugins.coldfeeling.instances.ColdPlayer;

public class PlayerDetect implements Listener {
  private ColdFeeling coldFeeling = ColdFeeling.getInstance();
  private SkinsRestorerAPI restorerAPI = SkinsRestorerAPI.getApi();

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    coldFeeling.getColdPlayers().put(e.getPlayer().getName(), new ColdPlayer(e.getPlayer()));
  }

  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
    if(coldFeeling.getColdPlayers().containsKey(e.getPlayer().getName()))
      coldFeeling.getColdPlayers().remove(e.getPlayer().getName());
  }

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent e) {
    ItemStack head = SkullCreator.itemFromBase64(restorerAPI.getSkinData(restorerAPI.getSkinName(e.getPlayer().getName())).getValue());
    e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), head);
  }
}
