package pe.bazan.luis.plugins.coldfeeling.instances;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import pe.bazan.luis.plugins.coldfeeling.ColdFeeling;

public class ColdPlayer {
  private final Player bukkitPlayer;
  private int armor = 0; // Armor equiped
  private int biome = 0; // temperature of the block
  private int floor = 0; // temperature of the block at the player's feet
  private int environment = 0; // rain / snow / dimension
  private int light = 0; // Light
  private int temperature = 0;
  private int maxTemperature = 1000;
  private String biomeName = "";
  private final BossBar bossBar;

  public ColdPlayer(Player bukkitPlayer) {
    this.bukkitPlayer = bukkitPlayer;
    this.bossBar = ColdFeeling.getInstance().getServer().createBossBar("Cold", BarColor.BLUE, BarStyle.SOLID);
    bossBar.addPlayer(bukkitPlayer);
    setBossBarVisible(true);
  }

  public int getArmor() {
    return armor;
  }

  public int getBiome() {
    return biome;
  }

  public int getFloor() {
    return floor;
  }

  public int getEnvironment() {
    return environment;
  }

  public int getLight() {
    return light;
  }

  public int getTotal() {
    return armor + biome + floor + environment + light;
  }

  public int getMaxTemperature() {
    return maxTemperature;
  }

  public int getTemperature() {
    return temperature;
  }

  public String getBiomeName() {
    return biomeName;
  }

  public Player getBukkitPlayer() {
    return bukkitPlayer;
  }

  public BossBar getBossBar() {
    return bossBar;
  }

  public double getPercentageCold() {
    return ((double) Math.negateExact(temperature) + (double) maxTemperature) / (double) maxTemperature;
  }
  public void setArmor(int armor) {
    this.armor = armor;
  }

  public void setBiome(int biome) {
    this.biome = biome;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public void setEnvironment(int environment) {
    this.environment = environment;
  }

  public void setLight(int light) {
    this.light = light;
  }

  public void setBiomeName(String biomeName) {
    this.biomeName = biomeName;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public void setMaxTemperature(int maxTemperature) {
    this.maxTemperature = maxTemperature;
  }

  public void setBossBarVisible(boolean visible) {
    bossBar.setVisible(visible);
  }
}
